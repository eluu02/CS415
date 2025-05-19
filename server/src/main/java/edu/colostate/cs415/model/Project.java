package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;

import edu.colostate.cs415.dto.ProjectDTO;

public class Project {

	private String name;
	private ProjectSize size;
	private ProjectStatus status;
	private Set<Worker> workers;
	private Set<Qualification> qualifications;

	public Project(String name, Set<Qualification> qualifications, ProjectSize size) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Project name must not be null, empty, or only whitespace.");
		}
		if (qualifications == null || qualifications.isEmpty()) {
			throw new IllegalArgumentException("Qualifications set must not be null or empty");
		}
		if (size == null) {
			throw new IllegalArgumentException("Project size must not be null");
		}

		this.name = name;
		this.qualifications = qualifications;
		this.size = size;
		this.status = ProjectStatus.PLANNED;
		this.workers = new HashSet<Worker>();
	}
	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Project)) {
			return false;
		}
		Project otherProject = (Project) other;
		return this.name.equals(otherProject.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode(); //since P1 currently specifies only name
	}



	//Returns a String that includes the company name, colon, number of available workers, colon, and number of projects carried out.
    // For example, a company called ABC that has 20 available workers and 10 projects will result in the string ABC:20:10.
    @Override
    public String toString() {
        return String.format("%s:%d:%s", this.name, this.workers.size(), this.status);
    }

	public String getName() {
		return this.name;
	}

	public ProjectSize getSize() {
		return size;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		if (status == null) throw new IllegalArgumentException("Status cannot be set to null.");
		this.status = status;
	}

	public void addWorker(Worker worker) {
		if (worker == null) throw new IllegalArgumentException("Worker cannot be null.");
		this.workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		if (worker == null) throw new IllegalArgumentException();
		this.workers.remove(worker);
	}

	public Set<Worker> getWorkers() {
		return this.workers;
	}

	public void removeAllWorkers() {
		this.workers.clear();
	}

	public Set<Qualification> getRequiredQualifications() {
		return qualifications;
	}

	public void addQualification(Qualification qualification) {
		if (qualification == null) throw new IllegalArgumentException("Cannot add null as a qualification.");
		this.qualifications.add(qualification);
	}

	public Set<Qualification> getMissingQualifications() {
		if (this.workers.isEmpty()) {
			return qualifications;
		}
		
		Set<Qualification> missingQualifications = new HashSet<>(this.qualifications);
		for (Qualification q : this.qualifications) {
			boolean isMissing = true;
			for (Worker w : this.workers) {
				if (isMissing && w.getQualifications().contains(q)) {
					missingQualifications.remove(q);
					isMissing = false;
				}
			}
		}
		return missingQualifications;
	}

	public boolean isHelpful(Worker worker) {
		if (worker == null) return false;
		Set<Qualification> workerQualifications = worker.getQualifications();
		Set<Qualification> missingQualifications = this.getMissingQualifications();
		for (Qualification missingQualification : missingQualifications) {
			if (workerQualifications.contains(missingQualification)) {
				return true;
			}
		}
		return false;
	}

	public ProjectDTO toDTO() {
		Set<Worker> workers = getWorkers();
		String[] workersNames = new String[workers.size()];
		int i = 0;
		for (Worker worker : workers) {
			workersNames[i] = worker.getName();
			i++;
		}
		Set<Qualification> qualifications = getRequiredQualifications();
		String[] qualificationDescriptions = new String[qualifications.size()];
		i = 0;
		for (Qualification qualification : qualifications) {
			qualificationDescriptions[i] = qualification.toString();
			i++;
		}
		Set<Qualification> missingQualifications = getMissingQualifications();
		String[] missingQualificationsDescriptions = new String[missingQualifications.size()];
		i = 0;
		for (Qualification missingQualification : missingQualifications) {
			missingQualificationsDescriptions[i] = missingQualification.toString();
			i++;
		}
		return new ProjectDTO(getName(), getSize(), getStatus(), workersNames, qualificationDescriptions, missingQualificationsDescriptions);
	}
}
