package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;
import java.security.InvalidParameterException;

import edu.colostate.cs415.dto.WorkerDTO;

public class Worker {

	public static final int MAX_WORKLOAD = 12;

	private String name;
	private double salary;
	private Set<Project> projects;
	private Set<Qualification> qualifications;

	public Worker(String name, Set<Qualification> qualifications, double salary) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Worker name must not be null, empty, or only whitespace.");
		}
		if (qualifications == null) {
			throw new IllegalArgumentException("Worker qualifications set must not be null.");
		}
		if (qualifications.isEmpty()) {
			throw new IllegalArgumentException("Worker must have at least one qualification.");
		}
		if (salary < 0) {
			throw new IllegalArgumentException("Worker must have a salary greater than or equal to zero.");
		}
		this.name = name;
		this.qualifications = qualifications;
		this.salary = salary;
		this.projects = new HashSet<>();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Worker)){
			return false;
		}
		Worker worker = (Worker) other;
		if (this.name.equals(worker.name)){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (name != null){
			return name.hashCode();
		}
		return 0;
	}

	@Override
	public String toString() {
		int projectCount = getProjects().size();
		int qualificationCount = getQualifications().size();
		long truncatedSalary = (long) salary;
		return String.format("%s:%d:%d:%d", name, projectCount, qualificationCount, truncatedSalary);
	}

	public String getName() {
		return this.name;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		if (salary < 0) {
			throw new IllegalArgumentException("Salary name must not be negative");
		}
		this.salary = salary;
	}

	public Set<Qualification> getQualifications() {
		return this.qualifications;
	}

	public void addQualification(Qualification qualification) {
		if (!(qualification instanceof Qualification) || qualification == null) {
			throw new IllegalArgumentException("Qualifications must be of type qualification and not null");
		} else if (!this.qualifications.contains(qualification)) {
			this.qualifications.add(qualification);
		}
	}

	public Set<Project> getProjects() {
		return this.projects;
	}

	public void addProject(Project project) {
		if (!(project instanceof Project) || project == null) {
			throw new IllegalArgumentException("Project must be of type project and not null");
		} else if (!this.projects.contains(project)) {
			projects.add(project);
		}
	}

	public void removeProject(Project project) {
		if (project == null) {
			throw new InvalidParameterException("Worker cannot be null");
		}
		this.projects.remove(project);
	}

	public int getWorkload() {
		int workload = 0;
		for (Project p : projects) {
            if (!p.getStatus().equals(ProjectStatus.FINISHED)) {
                workload += p.getSize().getValue();
            }
        }
        return workload;
	}

	public boolean willOverload(Project project) {
		if (!isAvailable()) {
			return true;
		}
		if (project == null) {
			return false;
		}
		if (projects.contains(project)) {
			return false;
		}
		if (!project.isHelpful(this)) {
			return false;
		}
		if ((getWorkload() + project.getSize().getValue()) <= 12) {
			return false;
		}
		return true;
	}

	public boolean isAvailable() {
        if (getWorkload() < 12){
            return true;
        }
        return false;
	}

	public WorkerDTO toDTO() {
		String[] projectNames = projects.stream().map(Project::getName).toArray(String[]::new);
                                    
		String[] qualificationNames = qualifications.stream()
													.map(q -> q.toDTO().getDescription())
													.toArray(String[]::new);
													
		int workload = projects.stream()
							.filter(p -> !p.getStatus().equals(ProjectStatus.FINISHED))
							.mapToInt(p -> p.getSize().getValue())
							.sum();

		return new WorkerDTO(this.name, this.salary, workload, projectNames, qualificationNames);
	}
}
