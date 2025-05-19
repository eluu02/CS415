package edu.colostate.cs415.server;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.redirect;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import com.google.gson.Gson;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.dto.AssignmentDTO;
import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashSet;
import java.util.Set;
import edu.colostate.cs415.model.*;

public class RestController {

	private static Logger log = Logger.getLogger(RestController.class.getName());
	private static String OK = "OK";
	private static String KO = "KO";

	private DBConnector dbConnector;
	private Company company;
	private Gson gson;

	public RestController(int port, DBConnector dbConnector) {
		port(port);
		this.dbConnector = dbConnector;
		gson = new Gson();
	}

	public void start() {
		// Load data from DB
		company = dbConnector.loadCompanyData();

		// Redirect
		redirect.get("/", "/helloworld");

		// Logging
		after("/*", (req, res) -> logRequest(req, res));
		exception(Exception.class, (exc, req, res) -> handleException(exc, res));

		// Hello World
		get("/helloworld", (req, res) -> helloWorld());

		// API
		path("/api", () -> {
			// Enable CORS
			options("/*", (req, res) -> optionsCORS(req, res));
			after("/*", (req, res) -> enableCORS(res));

			// Qualifications
			path("/qualifications", () -> {
				get("", (req, res) -> getQualifications(), gson::toJson);
				get("/:description", (req, res) -> getQualification(req.params("description")),
						gson::toJson);
				post("/:description", (req, res) -> createQualification(req));
			});

			// Projects
			path("/projects", () -> {
				get("", (req, res) -> getProjects(), gson::toJson);
				get("/:name", (req, res) -> getProject(req.params("name")), gson::toJson);
				post("/:name", (req,  res) -> createProject(req));
			});

			// Workers
			path("/workers", () -> {
				get("", (req, res) -> getWorkers(), gson::toJson);
				get("/:name", (req, res) -> getWorker(req.params("name")), gson::toJson);
				post("/:name", (req, res) -> createWorker(req));
			});

			// Company
			put("/assign", (req, res) -> doAssign(req, res));
			put("/unassign", (req, res) -> doUnassign(req, res));
			put("/start", (req, res) -> startProject(req, res));
			put("/finish", (req, res) -> finish(req, res)); 

		});
	}

	public void stop() {
		Spark.stop();
	}

	private String helloWorld() {
		return "Hello World!";
	}

	private QualificationDTO[] getQualifications() {
		HashSet<Qualification> qualifications = (HashSet<Qualification>) company.getQualifications();
		QualificationDTO[] DTOs = new QualificationDTO[qualifications.size()];
		int index = 0;
		for (Qualification qualification : qualifications) {
			DTOs[index] = qualification.toDTO();
			index++;
		}
		return DTOs;
	}

	private QualificationDTO getQualification(String description) {
		description = description.replace("%20", " ");
		HashSet<Qualification> qualifications = (HashSet<Qualification>) company.getQualifications();
		for (Qualification qualification : qualifications) {
			if (qualification.toString().equals(description)) {
				return qualification.toDTO();
			}
		}
		throw new RuntimeException("Description does not match any qualifications.");
	}

	private String createQualification(Request request) {
		QualificationDTO QualificationDTO = gson.fromJson(request.body(), QualificationDTO.class);
		String paramDescription = request.params("description").replace("%20", " ");
		String QualificationDTODescription = QualificationDTO.getDescription().replace("%20", " ");
		if (paramDescription.equals(QualificationDTODescription)) {
			company.createQualification(QualificationDTO.getDescription());
		} else
			throw new RuntimeException("Qualification descriptions do not match.");
		return OK;
	}

	private String doAssign(Request req, Response res) {
        AssignmentDTO assignment = gson.fromJson(req.body(), AssignmentDTO.class);
        if (assignment.getWorker() == null || assignment.getProject() == null) {
            res.status(400);
            return "Missing required fields: worker and project";
        }

        // Lookup the Worker by name.
        Worker foundWorker = null;
        for (Worker w : company.getEmployedWorkers()) {
			String workerName = w.getName().replace("%20", " ");
			String assignmentName = assignment.getWorker().replace("%20", " ");
            if (workerName.equals(assignmentName)) {
                foundWorker = w;
                break;
            }
        }
        if (foundWorker == null) {
            res.status(404);
            return "Worker not found";
        }

        // Lookup the Project by name.
        Project foundProject = null;
        for (Project p : company.getProjects()) {
			String projectName = p.getName().replace("%20", " ");
			String assignmentName = assignment.getProject().replace("%20", " ");
            if (projectName.equals(assignmentName)) {
                foundProject = p;
                break;
            }
        }
        if (foundProject == null) {
            res.status(404);
            return "Project not found";
        }

        company.assign(foundWorker, foundProject);
        return OK;
    }

	private String doUnassign(Request req, Response res) {
    	// Deserialize AssignmentDTO from the request body.
    	AssignmentDTO assignment = gson.fromJson(req.body(), AssignmentDTO.class);
    	if (assignment.getWorker() == null || assignment.getProject() == null) {
        	res.status(400);
        	return "Missing required fields: worker and project";
    	}

    	// Lookup the Worker by name.
    	Worker foundWorker = null;
    	for (Worker w : company.getEmployedWorkers()) {
			String workerName = w.getName().replace("%20", " ");
			String assignmentName = assignment.getWorker().replace("%20", " ");
        	if (workerName.equals(assignmentName)) {
            	foundWorker = w;
            	break;
        	}
    	}
    	if (foundWorker == null) {
        	res.status(404);
        	return "Worker not found";
    	}

    	// Lookup the Project by name.
    	Project foundProject = null;
    	for (Project p : company.getProjects()) {
        	// Assuming Project has a getName() method.
			String projectName = p.getName().replace("%20", " ");
			String assignmentName = assignment.getProject().replace("%20", " ");
        	if (projectName.equals(assignmentName)) {
            	foundProject = p;
            	break;
        	}
    	}
    	if (foundProject == null) {
        	res.status(404);
        	return "Project not found";
    	}

    	// Unassign the worker from the project.
    	company.unassign(foundWorker, foundProject);
    	return OK;
	}

	private String startProject(Request req, Response res) {
		// Deserialize ProjectDTO from request body
		ProjectDTO projectDTO = gson.fromJson(req.body(), ProjectDTO.class);
		
		// Validate that the required field 'name' is provided
		if (projectDTO.getName() == null || projectDTO.getName().replace("%20", " ").isEmpty()) {
			res.status(400);
			throw new RuntimeException("Missing required field: name");
		}
		
		// Lookup the Project in the company's project list by name
		Project foundProject = null;
		for (Project p : company.getProjects()) {
			String projectName = p.getName().replace("%20", " ");
			String DTOname = projectDTO.getName().replace("%20", " ");
			if (projectName.equals(DTOname)) {
				foundProject = p;
				break;
			}
		}
		if (foundProject == null) {
			res.status(404);
			throw new RuntimeException("Project cannot be found");
		}
		
		// Attempt to start the project and handle potential errors
		try {
			company.start(foundProject);
		} catch (IllegalArgumentException e) {
			res.status(400);
			throw new RuntimeException("Project cannot be started");
		}
		
		return OK;
	}

	private String finish(Request req, Response res) {
		// Deserialize ProjectDTO from the request body.
		ProjectDTO projectDTO = gson.fromJson(req.body(), ProjectDTO.class);
		// Check that the project name is present
		if (projectDTO.getName() == null || projectDTO.getName().replace("%20", " ").isEmpty()) {
			res.status(400);
			return "Missing required fields: project name";
		}
		// Find the real Project in the Company by matching the name:
		Project foundProject = null;
		for (Project p : company.getProjects()) {
			String projectName = p.getName().replace("%20", " ");
			String DTOname = projectDTO.getName().replace("%20", " ");
			if (projectName.equals(DTOname)) {
				foundProject = p;
				break;
			}
		}
		// Check if the project was found
		if (foundProject == null) {
			throw new RuntimeException("Project cannot be found");
		}
		// Let Company handle the logic of transitioning to FINISHED:
		try {
			company.finish(foundProject);
		} catch (IllegalArgumentException e) {
			res.status(400);
			return "Project cannot be finished";
		}
		return "OK";
	}

	private String createProject(Request req) {
		// Deserialize ProjectDTO from request body
		ProjectDTO dto = gson.fromJson(req.body(), ProjectDTO.class);
	
		// Validation
		if (dto.getName() == null || dto.getName().replace("%20", " ").trim().isEmpty() ||
			dto.getSize() == null || dto.getQualifications() == null || dto.getQualifications().length == 0) {
			throw new IllegalArgumentException("Missing required fields");
		}
	
		String paramName = req.params("name").replace("%20", " ");
		String DTOname = dto.getName().replace("%20", " ");
		if (!paramName.equals(DTOname)) {
			throw new RuntimeException("Project name mismatch in URL and body.");
		}
	
		Set<Qualification> quals = new HashSet<>();
		for (String qDesc : dto.getQualifications()) {
			boolean found = false;
			for (Qualification q : company.getQualifications()) {
				if (q.toString().equals(qDesc.replace("%20", " "))) {
					quals.add(q);
					found = true;
					break;
				}
			}
			if (!found) throw new RuntimeException("Unknown qualification: " + qDesc);
		}
	
		company.createProject(dto.getName(), quals, dto.getSize());
		return "OK";
	}
	

	private WorkerDTO[] getWorkers() {
		HashSet<Qualification> qualifications = (HashSet<Qualification>) company.getQualifications();
		if (qualifications.isEmpty()) {
			return new WorkerDTO[0];
		}
		HashSet<WorkerDTO> workerDTOs = new HashSet<WorkerDTO>();
		for (Qualification qualification : qualifications) {
			HashSet<Worker> workers = (HashSet<Worker>) qualification.getWorkers();
			for (Worker worker : workers) {
				WorkerDTO workerDTO = worker.toDTO();
				if (!workerDTOs.contains(workerDTO)) {
					workerDTOs.add(workerDTO);
				}
			}
		}
		WorkerDTO[] DTOs = new WorkerDTO[workerDTOs.size()];
		int index = 0;
		for (WorkerDTO workerDTO : workerDTOs) {
			DTOs[index] = workerDTO;
			index++;
		}
		return DTOs;
	}

	private WorkerDTO getWorker(String name) {
		name = name.replace("%20", " ");
		HashSet<Qualification> qualifications = (HashSet<Qualification>) company.getQualifications();
		if (qualifications.size() == 0) {
			throw new RuntimeException("There are no workers.");
		}
		for (Qualification qualification : qualifications) {
			HashSet<Worker> workers = (HashSet<Worker>) qualification.getWorkers();
			for (Worker worker : workers) {
				if (worker.getName().equals(name)) {
					return worker.toDTO();
				}
			}
		}
		throw new RuntimeException("Name does not match any workers.");
	}

	


	private String createWorker(Request request) {
		WorkerDTO workerDTO = gson.fromJson(request.body(), WorkerDTO.class);
		String paramName = request.params("name").replace("%20", " ");
		String DTOname = workerDTO.getName().replace("%20", " ");
		if (!paramName.equals(DTOname)) {
			throw new RuntimeException("Worker name mismatch in URL and body.");
		}
		HashSet<Qualification> qualifications = new HashSet<Qualification>();
		for (String description : workerDTO.getQualifications()) {
			boolean found = false;
			for (Qualification q : company.getQualifications()) {
				if (q.toString().equals(description.replace("%20", " "))) {
					qualifications.add(q); // reuse the company's Qualification object instead of creating a new one,  because creating a new instance makes different objects in Java memory.
					found = true;
					break;
				}
			} // This ensures only existing company qualifications are used
			if (!found) { // If no matching qualification was found in the company -> throw exception
				throw new RuntimeException("Worker qualification not found: " + description);
			}
		}
		company.createWorker(DTOname, qualifications, workerDTO.getSalary());
		return OK;
	}

	// Logs every request received
	private void logRequest(Request request, Response response) {
		log.info(request.requestMethod() + " " + request.pathInfo() + "\nREQUEST:\n" + request.body() + "\nRESPONSE:\n"
				+ response.body());
	}

	// Exception handling
	private void handleException(Exception exception, Response response) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace();
		exception.printStackTrace(pw);
		log.severe(sw.toString());
		response.body(KO);
		response.status(500);
	}

	// Enable CORS
	private void enableCORS(Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	}

	// Enable CORS
	private String optionsCORS(Request request, Response response) {
		String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
		if (accessControlRequestHeaders != null)
			response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);

		String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
		if (accessControlRequestMethod != null)
			response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
		return OK;
	}

	// Project methods
	private ProjectDTO[] getProjects() {
		HashSet<Project> projects = (HashSet<Project>) company.getProjects();
		ProjectDTO[] DTOs = new ProjectDTO[projects.size()];
		int i = 0;
		for (Project project : projects) {
			DTOs[i] = project.toDTO();
			i++;
		}
		return DTOs;
	}

	private ProjectDTO getProject(String name) {
		name = name.replace("%20", " ");
        HashSet<Project> projects = (HashSet<Project>) company.getProjects();
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project.toDTO();
            }
        }
        throw new RuntimeException("Name does not match any project names");
    }

}