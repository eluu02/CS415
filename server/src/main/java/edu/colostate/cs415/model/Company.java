package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;

public class Company {

	private String name;
	private Set<Worker> employees;
	private Set<Worker> available;
	private Set<Worker> assigned;
	private Set<Project> projects;
	private Set<Qualification> qualifications;

	public Company(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Company name must not be null, empty, or only whitespace.");
		}
		this.name = name;
		this.employees = new HashSet<>();
		this.available = new HashSet<>();
		this.assigned = new HashSet<>();
		this.projects = new HashSet<>();
		this.qualifications = new HashSet<>();
	}

	@Override
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Company)){
			return false;
		}

		Company otherCompany = (Company) other;
		return this.name.equals(otherCompany.name);
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		int availableWorkers = this.available.size();
		int projectsCarriedOut = this.projects.size();
		String output = String.format("%s:%d:%d",this.name, availableWorkers, projectsCarriedOut);
		return output;
	}

	public String getName() {
		return this.name;
	}

	public Set<Worker> getEmployedWorkers() {
		return employees;
	}

	public Set<Worker> getAvailableWorkers() {
		return available;
	}

	public Set<Worker> getUnavailableWorkers() {
		Set<Worker> unavailableWorkers = new HashSet<>(employees);
		unavailableWorkers.remove(available);
		return unavailableWorkers;
	}

	public Set<Worker> getAssignedWorkers() {
		return assigned;
	}



	public Set<Worker> getUnassignedWorkers() {
		Set<Worker> assignedWorkers = getAssignedWorkers();
		Set<Worker> unassignedWorkers = new HashSet<>();

		for (Worker worker : employees) {
			if (!assignedWorkers.contains(worker)) {
				unassignedWorkers.add(worker);
			}
		}
		return unassignedWorkers;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public Set<Qualification> getQualifications() {
		return this.qualifications;
	}

	public Worker createWorker(String name, Set<Qualification> qualifications, double salary) {
		if (name == null || name.trim().isEmpty()) {
			return null;
		}
		if (qualifications == null || qualifications.isEmpty()) {
			return null;
		}
		if (salary < 0) {
			return null;
		}
		if (!this.qualifications.containsAll(qualifications)) {
			return null; // Qualifications must be a subset of company's qualifications
		}
		Worker newWorker = new Worker(name, qualifications, salary);
		employees.add(newWorker);
		available.add(newWorker); // New worker starts unassigned and available
		for (Qualification q : qualifications) {
			q.addWorker(newWorker);
		}
		return newWorker;
	}

	public Qualification createQualification(String description) {
		if(description == null || description.isEmpty()) {
			return null;
		}
		Qualification q = new Qualification(description);
		if(qualifications == null) {
			this.qualifications = new HashSet<>();
		}
		if(!qualifications.contains(q)) {
			this.qualifications.add(q);
		}
		return q;
	}

	public Project createProject(String name, Set<Qualification> qualifications, ProjectSize size) {
		if (name == null || name.trim().isEmpty()) {
			return null;
		}
		if (qualifications == null || qualifications.size() == 0) {
			return null;
		}
		if (size == null) {
			return null;
		}
		for (Qualification q : qualifications) {
			if (!this.qualifications.contains(q)) {
				return null;
			}
		}

		try {
			Project project = new Project(name, qualifications, size);
			projects.add(project);
			return project;
		} catch (Exception e) {
			return null;
		}
	}

	public void start(Project project) {
		if (project == null) {
            throw new IllegalArgumentException("Project must not be null");
        }
        if (project.getStatus().equals(ProjectStatus.ACTIVE) || project.getStatus().equals(ProjectStatus.FINISHED)) {
            throw new IllegalArgumentException("Project that is active or finished can not be started");
        }
        if (project.getMissingQualifications().isEmpty()) {
            project.setStatus(ProjectStatus.ACTIVE);
        }
	}

	public void finish(Project project) {
		if (project == null) throw new IllegalArgumentException();
		if (project.getStatus() == ProjectStatus.ACTIVE) {
			project.setStatus(ProjectStatus.FINISHED);
			project.removeAllWorkers();
		}
	}

	public void assign(Worker worker, Project project) {
		if (worker == null || project == null) {
			throw new IllegalArgumentException("Worker and/or project must not be null");
		}
		if (!employees.contains(worker)) {
			throw new IllegalArgumentException("Worker is not an employee of this company");
		}
		if (!worker.isAvailable() || !available.contains(worker)) {
			throw new IllegalArgumentException("Worker is unavailable to be assigned to a project");
		}
		if (worker.getProjects().contains(project)) {
			throw new IllegalArgumentException("Worker is already assigned to this project");
		}
		if (project.getStatus().equals(ProjectStatus.ACTIVE) || project.getStatus().equals(ProjectStatus.FINISHED)) {
			throw new IllegalArgumentException("Project must not be in the ACTIVE or FINISHED state");
		}
		if (worker.willOverload(project)) {
			throw new IllegalArgumentException("Worker will overload by adding this project");
		}
		if (!project.isHelpful(worker)) {
			throw new IllegalArgumentException("Worker is not helpful to this project");
		}
		if (!assigned.contains(worker)) {
			assigned.add(worker);
		}

		project.addWorker(worker);
		worker.addProject(project);
		if (!worker.isAvailable()) {
			available.remove(worker);
		}
	}

	public void unassign(Worker worker, Project project) {

		if (worker == null || project == null) {
			throw new IllegalArgumentException("Worker or project cannot equal null");
		}

		if (!project.getWorkers().contains(worker)) {
			throw new IllegalArgumentException("Worker is not assigned to this project");
		}
		
		worker.removeProject(project);
		project.removeWorker(worker);

		if (worker.getProjects().isEmpty()){
			this.assigned.remove(worker);
			this.available.add(worker);
		} else{
			if (worker.getWorkload() < Worker.MAX_WORKLOAD){
				this.available.add(worker);
			} else{
				this.available.remove(worker);
			}
		}

		if (project.getStatus().equals(ProjectStatus.ACTIVE) && !project.getMissingQualifications().isEmpty()) {
				project.setStatus(ProjectStatus.SUSPENDED);
		}
		
	}

	public void unassignAll(Worker worker) {
		if (worker == null){
			throw new IllegalArgumentException("Worker cannot be null");
		}

		Set<Project> workerProjects = new HashSet<>(worker.getProjects());
		for (Project p: workerProjects){
			worker.removeProject(p);
			p.removeWorker(worker);

			if (p.getStatus().equals(ProjectStatus.ACTIVE) && !p.getMissingQualifications().isEmpty()){
				p.setStatus(ProjectStatus.SUSPENDED);
			}
		}

		this.assigned.remove(worker);

		if (worker.getProjects().isEmpty()){
			this.available.add(worker);
		}
		if (worker.getWorkload() < 12){
			this.available.add(worker);
		} else {
			this.available.remove(worker);
		}

	}
}
