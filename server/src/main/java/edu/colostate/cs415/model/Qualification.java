package edu.colostate.cs415.model;

import java.util.Set;
import java.security.InvalidParameterException;
import java.util.HashSet;

import edu.colostate.cs415.dto.QualificationDTO;

public class Qualification {

	private String description;
	private Set<Worker> workers;

	public Qualification(String description) {
		if(description == null || description.trim().isEmpty()){
			throw new IllegalArgumentException("Description cannot be null or empty");
		}
		
		this.description = description;
		this.workers = new HashSet<>();
	}

	

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Qualification)){ 
			return false;
		}
		Qualification o = (Qualification) other;
		if (this.description.compareTo(o.description) == 0) {
			return true;

		}

		return false;
	}

	@Override
	public int hashCode() {
		if(!description.isEmpty() && description != null){
			return description.hashCode();
		}
		return 0;
	}

	@Override
	public String toString() {
		return description;
	}

	public Set<Worker> getWorkers() {
		return this.workers;
	}
	
	/*
	 * TODO: Is this check for null supposed to happen? It doesnt say not to in the documentation, 
	 * ensure that other add methods match where applicable
	 */
	public void addWorker(Worker worker) {
		if(!(worker instanceof Worker) || worker == null){
			throw new InvalidParameterException("Worker must be of type worker and not null");
		} else if (!this.workers.contains(worker)){
			this.workers.add(worker);
		}
	}

	public void removeWorker(Worker worker) {
		if (worker == null) {
			throw new InvalidParameterException("Worker cannot be null");
		}
		this.workers.remove(worker);
	}

	public QualificationDTO toDTO() {
		String[] workerNames = workers.stream().map(Worker::getName).toArray(String[]::new);
		
		return new QualificationDTO(this.description, workerNames);
	}
}
