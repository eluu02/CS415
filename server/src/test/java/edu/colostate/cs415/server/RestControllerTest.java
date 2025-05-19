package edu.colostate.cs415.server;
import edu.colostate.cs415.model.*;
import edu.colostate.cs415.db.*;
import edu.colostate.cs415.dto.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;

import spark.Spark;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;


public class RestControllerTest {

    private static final int PORT = 4567;
    private static DBConnector dbConnectorMock = mock(DBConnector.class);
    private static RestController controller = new RestController(PORT, dbConnectorMock);
    private static Company company;
    private static Gson gson = new Gson();


    private static Company mockCompany() {
        /// Create company
        Company company = new Company("Tech Solutions Inc.");
        
        // Create qualifications (each qualification must be created through the company)
        Qualification javaQualification = company.createQualification("Java");
        Qualification sqlQualification = company.createQualification("SQL");
        Qualification managementQualification = company.createQualification("Management");
        Qualification designQualification = company.createQualification("Design");
        
        // Create workers with their qualification sets
        Set<Qualification> worker1Qualifications = new HashSet<>();
        worker1Qualifications.add(javaQualification);
        worker1Qualifications.add(sqlQualification);
        Worker alice = company.createWorker("Alice", worker1Qualifications, 80000);
        
        Set<Qualification> worker2Qualifications = new HashSet<>();
        worker2Qualifications.add(javaQualification);
        worker2Qualifications.add(managementQualification);
        Worker bob = company.createWorker("Bob", worker2Qualifications, 90000);
        
        Set<Qualification> worker3Qualifications = new HashSet<>();
        worker3Qualifications.add(designQualification);
        Worker charlie = company.createWorker("Charlie", worker3Qualifications, 70000);
        
        
        // Create a project that requires Java and Management skills.
        Set<Qualification> projectQualifications = new HashSet<>();
        projectQualifications.add(javaQualification);
        projectQualifications.add(managementQualification);
        Project projectA = company.createProject("Project A", projectQualifications, ProjectSize.MEDIUM);
        
        return company;
    }


    @BeforeClass
    public static void beforeTests() {
        when(dbConnectorMock.loadCompanyData()).thenAnswer((i) -> company);
    }

    @AfterClass
    public static void afterTests() {
        controller.stop();
    }

    @Before
    public void initData() {
        company = mockCompany();
    }

    @Test
    public void testHelloWorldEndpoint() throws Exception {
        String url = "http://localhost:" + PORT + "/helloworld";
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        assertEquals("Hello World!", response);
    }

    @Test
    public void testRootEndpoint() throws Exception {
        company = mockCompany();
        controller.start();
        String url = "http://localhost:" + PORT + "/";
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        assertEquals("Hello World!", response);
    }

    @Test
    public void testGetQualificationsEndpointZeroQualifications() throws Exception {
        company = new Company("company");
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/qualifications";
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        QualificationDTO[] responseDTOs = gson.fromJson(response, QualificationDTO[].class);

        // Test
        assertTrue(responseDTOs.length == 0);
    }

    @Test
    public void testGetQualificationsEndpointManyQualifications() throws Exception {
        // Set Up
        company = (new DBConnector()).loadCompanyData();
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/qualifications";
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        QualificationDTO[] responseDTOs = gson.fromJson(response, QualificationDTO[].class);

        // Test
        HashSet<Qualification> qualifications = (HashSet<Qualification>) company.getQualifications();
        HashSet<QualificationDTO> expectedDTOs = new HashSet<QualificationDTO>();
        for (Qualification qualification : qualifications) {
            expectedDTOs.add(qualification.toDTO());
        }
        for (int i = 0; i < expectedDTOs.size(); i++) {
            assertTrue(expectedDTOs.contains(responseDTOs[i]));
        }
    }

    @Test
    public void testGetQualificationEndpointNoQualifications() throws Exception {
        String description = "Bob";
        
        company = new Company("DummyCompany");
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/qualifications/" + description;
        
        // Test
        boolean exceptionCaught = false;
        try {
            String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        } catch (Exception e) {
            exceptionCaught = true;
        }

        assertTrue(exceptionCaught);
    }

    @Test
    public void testGetQualificationEndpointNoMatch() throws Exception {
        String description = "Bob";
        
        // Setup
        company = mockCompany();
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/qualifications/" + description;
        
        // Test
        boolean exceptionCaught = false;
        try {
            String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        } catch (Exception e) {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
    }

    @Test
    public void testGetQualificationEndpointMatch() throws Exception {
        String description = "Bob";

        company = new Company("DummyCompany");
        company.createQualification(description);
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/qualifications/" + description;
        
        // Test
        HashSet<Qualification> qualifications = (HashSet<Qualification>) company.getQualifications();
        QualificationDTO expectedQualificationDTO = null;
        for (Qualification qualification : qualifications) {
			if (qualification.toString().equals(description)) {
				expectedQualificationDTO = qualification.toDTO();
			}
		}

        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();

        assertEquals(expectedQualificationDTO, gson.fromJson(response, QualificationDTO.class));
    }

    @Test
    public void testPostQualificationEndpointDescriptionMatch() throws Exception {
        String description = "Bob";

        company = new Company("DummyCompany");
        controller.start();

        // POST Request
        String url = ("http://localhost:" + PORT + "/api/qualifications/" + description).replace(" ", "%20");

        QualificationDTO qualificationDTO = (new Qualification(description)).toDTO();
        String json = gson.toJson(qualificationDTO);

        String response = Request.post(url)
                .bodyString(json, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

        // Validate the endpoint returns "OK".
        assertEquals("OK", response);

        // GET Request
        String url2 = "http://localhost:" + PORT + "/api/qualifications";
        String response2 = Request.get(url2)
                                 .execute()
                                 .returnContent()
                                 .asString();
        QualificationDTO[] responseDTOs = gson.fromJson(response2, QualificationDTO[].class);

        // Test
        HashSet<Qualification> qualifications = (HashSet<Qualification>) company.getQualifications();
        HashSet<QualificationDTO> expectedDTOs = new HashSet<QualificationDTO>();
        for (Qualification qualification : qualifications) {
            expectedDTOs.add(qualification.toDTO());
        }
        for (int i = 0; i < expectedDTOs.size(); i++) {
            assertTrue(expectedDTOs.contains(responseDTOs[i]));
        }
    }

    @Test
    public void testGetProjectsEndpointNoProjects() throws Exception {
        company = new Company("company1");
        controller.start();

        String url = "http://localhost:" + PORT + "/api/projects";
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        ProjectDTO[] responseDTOs = gson.fromJson(response, ProjectDTO[].class);
        assertEquals(responseDTOs.length, 0);
    }

    @Test
    public void testGetProjectsEndpoint() throws Exception {
        company = new Company("company1");
        Set<Qualification> projectQualifications = new HashSet<>();
        projectQualifications.add(new Qualification("qualification1"));
        company.createProject("project1", projectQualifications, ProjectSize.MEDIUM);
        HashSet<Project> projects = (HashSet<Project>) company.getProjects();
        HashSet<ProjectDTO> projectDTOs = new HashSet<ProjectDTO>();
        for (Project project : projects) {
            projectDTOs.add(project.toDTO());
        }

        controller.start();

        String url = "http://localhost:" + PORT + "/api/projects";
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        ProjectDTO[] responseDTOs = gson.fromJson(response, ProjectDTO[].class);

        for (int i = 0; i < responseDTOs.length; i++) { 
            assertTrue(projectDTOs.contains(responseDTOs[i]));
        }
        assertEquals(responseDTOs.length, projectDTOs.size());
    }

    @Test
    public void testGetProjectEndpointNoProject() throws Exception {
        company = new Company("company1");
        controller.start();

        String url = "http://localhost:" + PORT + "/api/projects/name";  
        boolean exceptionCaught = false;
        try {
            String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        } catch (Exception e) {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
    }

    @Test
    public void testGetProjectsEndpointNoMatch() {
        String pName = "project1";
        company = new Company("company1");
        Set<Qualification> projectQualifications = new HashSet<>();
        projectQualifications.add(new Qualification("qualification1"));
        company.createQualification("qualification1");
        Worker worker = company.createWorker("Alice", projectQualifications, 1000000);
        Project project1 = company.createProject(pName, projectQualifications, ProjectSize.SMALL);
        
        controller.start();

        String url = "http://localhost:" + PORT + "/api/projects/name";  
        boolean exceptionCaught = false;
        try {
            String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        } catch (Exception e) {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
    }

    @Test
    public void testGetProjectEndpoint() throws Exception {
        String pName = "project1";
        company = new Company("company1");
        Set<Qualification> projectQualifications = new HashSet<>();
        projectQualifications.add(new Qualification("qualification1"));
        company.createQualification("qualification1");
        Worker worker = company.createWorker("Alice", projectQualifications, 1000000);
        Project project1 = company.createProject(pName, projectQualifications, ProjectSize.SMALL);

        controller.start();
        
        String url = "http://localhost:" + PORT + "/api/projects/" + pName;
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        ProjectDTO responseDTO = gson.fromJson(response, ProjectDTO.class);
        assertEquals(project1.toDTO(), responseDTO);
        assertEquals(project1.getName(), responseDTO.getName());
    }

    @Test
    public void testAssignEndpoint() throws Exception {
        company = new Company("Test Company");
        
        Qualification qual = company.createQualification("Java");
        
        Set<Qualification> workerQuals = new HashSet<>();
        workerQuals.add(qual);
        Worker alice = company.createWorker("Alice", workerQuals, 80000);
        
        Set<Qualification> projectQuals = new HashSet<>();
        projectQuals.add(qual);
        Project projectA = company.createProject("Project A", projectQuals, ProjectSize.MEDIUM);
        
        assertTrue("Project A should start with no workers assigned", projectA.getWorkers().isEmpty());
        
        controller.start();
        
        String url = "http://localhost:" + PORT + "/api/assign";
        AssignmentDTO assignment = new AssignmentDTO("Alice", "Project A");
        String jsonBody = gson.toJson(assignment);
        
        String response = Request.put(url)
                .bodyString(jsonBody, org.apache.hc.core5.http.ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
        
        assertEquals("OK", response);
        
        boolean aliceAssigned = false;
        for (Worker w : projectA.getWorkers()) {
            if (w.getName().equals("Alice")) {
                aliceAssigned = true;
                break;
            }
        }
        assertTrue("Alice should be assigned to Project A", aliceAssigned);
    }


    @Test
    public void testUnassignEndpoint() throws Exception {
        // Set up a dummy Company with a worker and a project
        company = new Company("Test Company");
        Qualification qual = company.createQualification("Java");
        Set<Qualification> workerQuals = new HashSet<>();
        workerQuals.add(qual);
        Worker alice = company.createWorker("Alice", workerQuals, 80000);
        Set<Qualification> projectQuals = new HashSet<>();
        projectQuals.add(qual);
        Project projectA = company.createProject("Project A", projectQuals, ProjectSize.MEDIUM);

        company.assign(alice, projectA);
        controller.start();

        // Build the PUT request with an AssignmentDTO JSON body.
        String url = "http://localhost:" + PORT + "/api/unassign";
        AssignmentDTO assignment = new AssignmentDTO("Alice", "Project A");
        String jsonBody = gson.toJson(assignment);

        String response = Request.put(url)
                .bodyString(jsonBody, org.apache.hc.core5.http.ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

        // Validate the endpoint returns "OK".
        assertEquals("OK", response);

        // Check that the worker is no longer assigned to the project.
        assertFalse("Alice should be unassigned from the project", projectA.getWorkers().contains(alice));

        // Check that the project is still in the same state.
        assertEquals("Project A should still be in the same state", ProjectStatus.PLANNED, projectA.getStatus());

        // Check that the worker is still in the company.
        assertTrue("Alice should still be in the company", company.getAvailableWorkers().contains(alice));

        // Check that the worker no longer has the project assigned to them. 
        assertFalse("Alice should no longer have Project A in her set of projects", alice.getProjects().contains(projectA));

    }

    @Test
    public void testGetWorkersEndpointZeroQualifications() throws Exception {
        company = new Company("company");
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/workers";
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        WorkerDTO[] responseDTOs = gson.fromJson(response, WorkerDTO[].class);

        // Test
        assertEquals(0, responseDTOs.length);
    }

    @Test
    public void testGetWorkersEndpointZeroWorkers() throws Exception {
        company = new Company("company");
        company.createQualification("TechnoBablist");
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/workers";
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        WorkerDTO[] responseDTOs = gson.fromJson(response, WorkerDTO[].class);

        // Test
        assertEquals(0, responseDTOs.length);
    }

    @Test
    public void testGetWorkersEndpointManyWorkers() throws Exception {
        // Set Up
        company = (new DBConnector()).loadCompanyData();
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/workers";
        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();
        WorkerDTO[] responseDTOs = gson.fromJson(response, WorkerDTO[].class);

        // Test
        HashSet<Qualification> qualifications = (HashSet<Qualification>) company.getQualifications();
        HashSet<WorkerDTO> expectedDTOs = new HashSet<WorkerDTO>();
		for (Qualification qualification : qualifications) {
			HashSet<Worker> workers = (HashSet<Worker>) qualification.getWorkers();
			for (Worker worker : workers) {
				WorkerDTO workerDTO = worker.toDTO();
				if (!expectedDTOs.contains(workerDTO)) {
					expectedDTOs.add(workerDTO);
				}
			}
		}
        for (int i = 0; i < expectedDTOs.size(); i++) {
            assertTrue(expectedDTOs.contains(responseDTOs[i]));
        }
    }

    @Test
    public void testGetWorkerEndpointNoQualifications() throws Exception {
        String name = "Bob";
        
        company = new Company("DummyCompany");
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/workers/" + name;
        
        // Test
        boolean exceptionCaught = false;
        try {
            String response = Request.get(url)
                                     .execute()
                                     .returnContent()
                                     .asString();
        } catch (Exception e) {
            exceptionCaught = true;
        }

        assertTrue(exceptionCaught);
    }

    @Test
    public void testGetWorkerEndpointNoMatch() throws Exception {
        String name = "XorgonTheConquerer";
        
        // Setup
        company = mockCompany();
        controller.start();

        // Request
        String url = "http://localhost:" + PORT + "/api/workers/" + name;
        
        // Test
        boolean exceptionCaught = false;
        try {
            String response = Request.get(url)
                                     .execute()
                                     .returnContent()
                                     .asString();
        } catch (Exception e) {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
    }

    @Test
    public void testGetWorkerEndpointMatch() throws Exception {
        String name = "Tim Conner";

        company = (new DBConnector()).loadCompanyData();
        controller.start();

        // Request
        String url = ("http://localhost:" + PORT + "/api/workers/" + name).replace(" ", "%20");
        
        // Test
        HashSet<Qualification> qualifications = (HashSet<Qualification>) company.getQualifications();
        WorkerDTO expectedWorkerDTO = null;
        for (Qualification qualification : qualifications) {
			HashSet<Worker> workers = (HashSet<Worker>) qualification.getWorkers();
            for (Worker worker : workers) {
                if (worker.getName().equals(name)) {
                    expectedWorkerDTO = worker.toDTO();
                }
            }
		}

        String response = Request.get(url)
                                 .execute()
                                 .returnContent()
                                 .asString();

        assertEquals(expectedWorkerDTO, gson.fromJson(response, WorkerDTO.class));
    }

    @Test
    public void testStartEndpoint() throws Exception {
        company = new Company("Test Company for Start");

        // Create the qualification needed.
        Qualification javaQualification = company.createQualification("Java");
        
        // Create a project that requires the Java qualification.
        Set<Qualification> projQuals = new HashSet<>();
        projQuals.add(javaQualification);
        Project project = company.createProject("Project Start", projQuals, ProjectSize.MEDIUM);
        
        // Create a worker with the proper qualification.
        Set<Qualification> workerQuals = new HashSet<>();
        workerQuals.add(javaQualification);
        Worker qualifiedWorker = company.createWorker("Alice", workerQuals, 80000);

        company.assign(qualifiedWorker, project);
        
        assertEquals("Project should be PLANNED initially", ProjectStatus.PLANNED, project.getStatus());
        
        controller.start();
        
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("Project Start");
        String jsonBody = gson.toJson(projectDTO);
        
        String url = "http://localhost:" + PORT + "/api/start";
        String response = Request.put(url)
                .bodyString(jsonBody, org.apache.hc.core5.http.ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
        
        assertEquals("OK", response);
        
        assertEquals("Project should now be ACTIVE after start", ProjectStatus.ACTIVE, project.getStatus());
    }

  
    @Test
    public void testFinishEndpoint() throws Exception {
        // Create a new Company
        company = new Company("Jordan Brand");

        // Create a qualification
        Qualification javaQualification = company.createQualification("NBA Player");

        // Create a project requiring that qualification
        Set<Qualification> projectQuals = new HashSet<>();
        projectQuals.add(javaQualification);
        Project project = company.createProject("NBA Finals", projectQuals, ProjectSize.MEDIUM);

        // Create a worker with the same qualification
        Set<Qualification> workerQuals = new HashSet<>();
        workerQuals.add(javaQualification);
        Worker MJ = company.createWorker("MJ", workerQuals, 100000);

        // Assign the worker to the project (so the project has 1 worker, 1 qualification)
        company.assign(MJ, project);
        company.start(project);

        // confirms the project is still PLANNED at this point
        assertEquals("Project should be ACTIVE initially",
                    ProjectStatus.ACTIVE, project.getStatus());

        // Start the Spark controller
        controller.start();

        // Build a ProjectDTO from the project
        ProjectDTO projectDTO = project.toDTO();
        String jsonBody = gson.toJson(projectDTO);

        // Send the PUT request to /api/finish
        String url = "http://localhost:" + PORT + "/api/finish";
        String response = Request.put(url)
                                .bodyString(jsonBody, ContentType.APPLICATION_JSON)
                                .execute()
                                .returnContent()
                                .asString();
        
        // Verify the server response is "OK"
        assertEquals("OK", response);

        // Check that the project status is now FINISHED
        assertEquals("Project should now be FINISHED after finish", ProjectStatus.FINISHED, project.getStatus());

        // Check that the project has no workers assigned
        assertTrue("Project should have no workers assigned after finish", project.getWorkers().isEmpty());

        // Check that the worker is still in the company
        assertTrue("Worker should still be in the company", company.getAvailableWorkers().contains(MJ));
    }

    @Test
    public void testPostProjectCreatesAndValidatesProject() throws Exception {
        company = new Company("Test Company");
        Qualification q1 = company.createQualification("Security");
        Qualification q2 = company.createQualification("DevOps");

        controller.start();
        Spark.awaitInitialization();

        // Set only required fields
        ProjectDTO dto = new ProjectDTO();
        dto.setName("Infra Build");
        dto.setSize(ProjectSize.SMALL);
        dto.setQualifications(new String[]{"Security", "DevOps"});

        String jsonBody = gson.toJson(dto);

        String url = "http://localhost:" + PORT + "/api/projects/Infra%20Build";
        String response = Request.post(url)
            .bodyString(jsonBody, ContentType.APPLICATION_JSON)
            .execute()
            .returnContent()
            .asString();

        assertEquals("OK", response);

        // Confirm creation of project 
        Project createdProject = null;
        for (Project p : company.getProjects()) {
            if (p.getName().equals("Infra Build")) {
                createdProject = p;
                break;
            }
        }

        /*  Verify that the project was successfully created after the POST request.
         Instead of just checking that the response equals "OK", here we validate the internal state of the company to ensure that the project was created correctly
        ,also helps fulfill the requirement to check the contents returned by PUT/POST requests rather than relying solely on the "OK" response. */
        
        // Validate that the project exists.
        assertTrue("Project should have been created", createdProject != null);
        
        // Check that the project size is correctly set as provided in the request.
        assertEquals(ProjectSize.SMALL, createdProject.getSize());
        
        // Confirm that the project status is set to PLANNED by default upon creation.
        assertEquals(ProjectStatus.PLANNED, createdProject.getStatus()); // default
        
        // Retrieve the set of qualifications required for the project.
        Set<Qualification> requiredQuals = createdProject.getRequiredQualifications();
        
        // Verify that the project has exactly the two required qualifications.
        assertEquals(2, requiredQuals.size());
        
        // Check that the created project includes the "Security" qualification.
        assertTrue(requiredQuals.contains(q1));
        
        // Check that the created project includes the "DevOps" qualification.
        assertTrue(requiredQuals.contains(q2));
    }
}