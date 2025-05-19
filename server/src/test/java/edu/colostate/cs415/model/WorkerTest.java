package edu.colostate.cs415.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import java.util.Arrays;

import java.security.InvalidParameterException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import edu.colostate.cs415.dto.WorkerDTO;

public class WorkerTest {
	@Test
	public void constructorValidParametersTest01() {
		HashSet<Qualification> qs = new HashSet<Qualification>();
		for (int i = 0; i < 25; i++) {
			Qualification q = new Qualification("q" + i);
			qs.add(q);
		}
		Worker w = null;
		try {
			w = new Worker("EthanielBottoms", qs, 100000.0);
		} catch (Exception e) {
			assert(false);
		}
		// TODO: Uncomment these tests when the corresponding methods are implemented
		
		assert(w.getQualifications() == qs);
		assert(w.getName() == "EthanielBottoms");
		assert(w.getSalary() == 100000.0);
		
		assert(true);
	}

	@Test
	public void constructorInvalidNameTest02() {
		HashSet<Qualification> qs = new HashSet<Qualification>();
		for (int i = 0; i < 25; i++) {
			Qualification q = new Qualification("q" + i);
			qs.add(q);
		}
		
		try {
			Worker w = new Worker(null, qs, 100000.0);
			assert(false);
		} catch (IllegalArgumentException e) {
			assert(true);
		} catch (Exception e) {
			assert(false);
		}
		try {
			Worker w = new Worker("", qs, 100000.0);
			assert(false);
		} catch (IllegalArgumentException e) {
			assert(true);
		} catch (Exception e) {
			assert(false);
		}
		try {
			Worker w = new Worker("	", qs, 100000.0);
			assert(false);
		} catch (IllegalArgumentException e) {
			assert(true);
		} catch (Exception e) {
			assert(false);
		}
	}

	@Test
	public void constructorNullQualificationsSetTest05() {
		try {
			Worker w = new Worker("EthanielBottoms", null, 100000.0);
			assert(false);
		} catch (IllegalArgumentException e) {
			assert(true);
		} catch (Exception e) {
			assert(false);
		}
	}

	@Test
	public void constructorEmptyQualificationsSetTest06() {
		HashSet<Qualification> qs = new HashSet<Qualification>();
		try {
			Worker w = new Worker("EthanielBottoms", qs, 100000.0);
			assert(false);
		} catch (IllegalArgumentException e) {
			assert(true);
		} catch (Exception e) {
			assert(false);
		}
	}

	@Test
	public void constructorNegativeSalaryTest10() {
		HashSet<Qualification> qs = new HashSet<Qualification>();
		for (int i = 0; i < 25; i++) {
			Qualification q = new Qualification("q" + i);
			qs.add(q);
		}

		try {
			Worker w = new Worker("EthanielBottoms", qs, -1.0);
			assert(false);
		} catch (IllegalArgumentException e) {
			assert(true);
		} catch (Exception e) {
			assert(false);
		}
	}

	@Test
	public void constructorZeroSalary11() {
		HashSet<Qualification> qs = new HashSet<Qualification>();
		for (int i = 0; i < 25; i++) {
			Qualification q = new Qualification("q" + i);
			qs.add(q);
		}

		Worker w = null;
		try {
			w = new Worker("EthanielBottoms", qs, 0.0);
		} catch (Exception e) {
			assert(false);
		}
		// TODO: Uncomment these tests when the corresponding methods are implemented
		
		assert(w.getQualifications() == qs);
		assert(w.getName() == "EthanielBottoms");
		assert(w.getSalary() == 0.0);
		
		assert(true);
	}

	@Test
	public void constructorMaxDoubleSalaryTest12() {
		HashSet<Qualification> qs = new HashSet<Qualification>();
		for (int i = 0; i < 25; i++) {
			Qualification q = new Qualification("q" + i);
			qs.add(q);
		}

		Worker w = null;
		try {
			w = new Worker("EthanielBottoms", qs, Double.MAX_VALUE);
		} catch (Exception e) {
			assert(false);
		}
		// TODO: Uncomment these tests when the corresponding methods are implemented
		
		assert(w.getQualifications() == qs);
		assert(w.getName() == "EthanielBottoms");
		assert(w.getSalary() == Double.MAX_VALUE);
		
		assert(true);
	}

		@Test
	public void testWorkerEquals01(){
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Qualification"));
		Worker worker1 = new Worker("Bob@ !654", qualifications, 50000);
		Worker worker2 = new Worker("Bob@ !654", qualifications, 60000);
		assertTrue(worker1.equals(worker2));
	}

	@Test
	public void testWorkerEquals02(){
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Qualification"));
		Worker worker1 = new Worker("Alice", qualifications, 50000);
		String nonWorkerObject = "Non-Worker object";
		assertFalse(worker1.equals(nonWorkerObject));
	}

	@Test
	public void testWorkerEquals03(){
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Qualification"));
		Worker worker1 = new Worker("Charlie", qualifications, 55000);
		assertFalse(worker1.equals(null));
	}

	@Test
	public void testWorkerEquals04(){
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Qualification"));
		Worker worker1 = new Worker("Bob", qualifications, 50000);
		Worker worker2 = new Worker("Bob-ert", qualifications, 50000);
		assertFalse(worker1.equals(worker2));
	}

	@Test
	public void getNameTest03() {
		Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
		Worker worker = new Worker("worker2", qualifications, 5.0);
		assertEquals(worker.getName(), "worker2");
	}


	@Test
    public void testWorkerHashCode01(){
		Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
        Worker worker1 = new Worker("Bob@ !654", qualifications, 50000);
        Worker worker2 = new Worker("Bob@ !654", qualifications, 65000);
        assertEquals(worker1.hashCode(), worker2.hashCode());
    }

    @Test
    public void testWorkerHashCode02(){
		Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
        Worker worker1 = new Worker("Bob-ert", qualifications, 50000);
        Worker worker2 = new Worker("Bob@ !654", qualifications, 65000);
        assertNotEquals(worker1.hashCode(), worker2.hashCode());
    }


	@Test
	public void testWorkerToString01(){

		//25 qualifications
		Set<Qualification> qualifications = new HashSet<>();
        for (int i = 1; i <= 25; i++) {
			qualifications.add(new Qualification("Qualification" + i));
		}

		Worker worker = new Worker("EthanBottoms", qualifications, 100000.20);

		//6 projects
		for (int i = 1; i <= 6; i++) {
			worker.addProject(new Project("Project" + i, qualifications, ProjectSize.SMALL));
		}
		
		assertEquals("EthanBottoms:6:25:100000", worker.toString());
	}

	@Test
	public void testWorkertoString02(){

		//25 qualifications
		Set<Qualification> qualifications = new HashSet<>();
        for (int i = 1; i <= 25; i++) {
			qualifications.add(new Qualification("Qualification" + i));
		}

		Worker worker = new Worker("ThatsMyName,Don\\'tWearItOut", qualifications, 100000.20);
		
		//6 projects
		for (int i = 1; i <= 6; i++) {
			worker.addProject(new Project("Project" + i, qualifications, ProjectSize.SMALL));
		}

		assertEquals("ThatsMyName,Don\\'tWearItOut:6:25:100000", worker.toString());
	}

	@Test
	public void testWorkerToString03(){

		//60 qualifications
		Set<Qualification> qualifications = new HashSet<>();
        for (int i = 1; i <= 60; i++) {
			qualifications.add(new Qualification("Qualification" + i));
		}

		Worker worker = new Worker("EthanBottoms", qualifications, 100000.20);

		//6 projects
		for (int i = 1; i <= 6; i++) {
			worker.addProject(new Project("Project" + i, qualifications, ProjectSize.SMALL));
		}
		
		assertEquals("EthanBottoms:6:60:100000", worker.toString());
	}
	
	@Test
	public void testWorkerToString04(){

		//25 qualifications
		Set<Qualification> qualifications = new HashSet<>();
        for (int i = 1; i <= 25; i++) {
			qualifications.add(new Qualification("Qualification" + i));
		}

		//No decimal on the salary
		Worker worker = new Worker("EthanBottoms", qualifications, 100000);

		//6 projects
		for (int i = 1; i <= 6; i++) {
			worker.addProject(new Project("Project" + i, qualifications, ProjectSize.SMALL));
		}
		
		assertEquals("EthanBottoms:6:25:100000", worker.toString());
	}

	@Test
	public void getSalaryTest03() {
		Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
		Worker worker = new Worker("worker1", qualifications, 5.0);
		assertEquals(worker.getSalary(), 5.0, 0);
		worker.setSalary(10.0);
		assertEquals(worker.getSalary(), 10.0, 0);
	}

	@Test
	public void getSalaryZeroTest02() {
		Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
		Worker worker = new Worker("worker1", qualifications, 0);
		assertEquals(worker.getSalary(), 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setSalaryTest01() {
        double testValue = -100.58;
		Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
        Worker worker = new Worker("Mike", qualifications, 0);
        worker.setSalary(testValue);
		// Once Get Salary is implemented Potentially swap
		
		assertEquals(testValue, worker.getSalary(), 0);
		 
        
    }

	@Test
	public void setSalaryTest02() {
        double testValue = 0;
		Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
        Worker worker = new Worker("Mike", qualifications, 0);
        worker.setSalary(testValue);
		// Once Get Salary is implemented Potentially swap
		
		assertEquals(testValue, worker.getSalary(), 0);
		 

        try {
            Field salaryField = Worker.class.getDeclaredField("salary");
            salaryField.setAccessible(true);
            double salary = (Double) salaryField.get(worker);
            assertEquals(testValue, salary, 0);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }
    }

	@Test
	public void setSalaryTest03() {
        double testValue = 100.58;
		Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
        Worker worker = new Worker("Mike", qualifications, 0);
        worker.setSalary(testValue);
		// Once Get Salary is implemented Potentially swap
		
		assertEquals(testValue, worker.getSalary(), 0);
		 

        try {
            Field salaryField = Worker.class.getDeclaredField("salary");
            salaryField.setAccessible(true);
            double salary = (Double) salaryField.get(worker);
            assertEquals(testValue, salary, 0);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }
    }

	/*
	 * getQualifications() tests
	 */

	// T1: Base test
	@Test
	public void getQualificationsTest01() {
		Set<Qualification> qualifications = new HashSet<>();
		Qualification qualification1 = new Qualification("qualification1");
		qualifications.add(qualification1);
        Worker worker = new Worker("worker1", qualifications, 1);
        assertEquals(worker.getQualifications().contains(qualification1), true);
        assertEquals(worker.getQualifications().size(), 1);
	}


	// T3: Test with a set of 25 qualifications
	@Test
    public void getLargeQualificationsTest03() {
        Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification"));
        Worker worker = new Worker("worker1", qualifications, 1);
		qualifications = generateAndAddQualifications(100, worker);
        assertEquals(worker.getQualifications().size(), 101);
    }
	

	// T4: Test with a large set of qualifications, additionally tests adding large amounts of qualifications
	@Test
    public void getQualificationsTest04() {
        Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification"));
        Worker worker = new Worker("worker1", qualifications, 1);
		qualifications = generateAndAddQualifications(24, worker);
        assertEquals(worker.getQualifications().size(), 25);
    }
	

	
	/*
	 * addQualifications() tests
	 */

	// Base test
	@Test
	public void addQualifiationTestValidInputTest01() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
        Worker worker = new Worker("worker1", qualifications, 1);
		Qualification qualification2 = new Qualification("qualification2");
		worker.addQualification(qualification2);
		assertEquals(worker.getQualifications().contains(qualification2), true);
	}

	// Test adding null object to qualification set
	@Test (expected = IllegalArgumentException.class)
	public void addNullQualificationTest02() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
        Worker worker = new Worker("worker1", qualifications, 1);
		worker.addQualification(null);
	}


    /*
     * getProjects() tests
     */

	// T1: Base test
    @Test
    public void getProjectsTest01() {
        Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.MEDIUM);
        Worker worker = new Worker("worker1", qualifications, 1);
        worker.addProject(project);
        assertEquals(worker.getProjects().contains(project), true);
        assertEquals(worker.getProjects().size(), 1);
    }

	// T3: Test getting empty project set
    @Test
    public void getEmptyProjectTest03() {
        Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
        Worker worker = new Worker("worker1", qualifications, 1);
        assertEquals(worker.getProjects().size(), 0);
        assertEquals(worker.getProjects(), Collections.emptySet());
    }

	// T4: Test getting large project set
    @Test
    public void getLargeProjectTest04() {
        Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
        Worker worker = new Worker("worker1", qualifications, 1);
        Set<Project> p = generateAndAddProjects(50, worker);
        assertEquals(p.size(), 50);
    }

    /*
     * addProject(Project project) tests
     */
    
	// Base test
    @Test
    public void addProjectTestValidInputTest01() {
        Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.MEDIUM);
        Worker worker = new Worker("worker1", qualifications, 1);
        worker.addProject(project);
        assertEquals(worker.getProjects().contains(project), true);
    }
	
	@Test (expected = IllegalArgumentException.class)
	public void addNullProjectTest02() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
        Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(null);
	}

	/*
	 * getWorkload() tests
	 */

	// T1: Base test
	@Test
	public void testGetPartialWorkloadTest01() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.BIG);
		Project project2 = new Project("project2", qualifications, ProjectSize.BIG);
        Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(project);
		worker.addProject(project2);
		assertEquals(worker.getWorkload(), 6);
	}

	// T2: Test empty workload and not finished projects, expected to not be equal
	@Test
	public void testAssertEmptyWorkloadTest02() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.BIG);
		Project project2 = new Project("project2", qualifications, ProjectSize.BIG);
		Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(project);
		worker.addProject(project2);
		assertNotEquals(worker.getWorkload(), 0);
	}

	// T3: Test getting full workload
	@Test 
	public void testGetFullWorkloadTest03() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.BIG);
		Project project2 = new Project("project2", qualifications, ProjectSize.BIG);
		Project project3 = new Project("project3", qualifications, ProjectSize.BIG);
		Project project4 = new Project("project4", qualifications, ProjectSize.BIG);
		Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(project);
		worker.addProject(project2);
		worker.addProject(project3);
		worker.addProject(project4);
		assertEquals(worker.getWorkload(), 12);
	}

	// T4: Test getting partial workload with finished projects, expected to not be equal
	@Test
	public void testAssertPartialWorkloadTest04() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.BIG);
		Project project2 = new Project("project2", qualifications, ProjectSize.BIG);
		Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(project);
		worker.addProject(project2);
		project.setStatus(ProjectStatus.FINISHED);
		project2.setStatus(ProjectStatus.FINISHED);
		assertNotEquals(worker.getWorkload(), 6);
	}

	/*
	 * willOverload(Project project) tests
	 */

	// T1: Base test
	@Test
	public void testWillNotOverloadTest01() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.MEDIUM);
		Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(project);
		Project project2 = new Project("project2", qualifications, ProjectSize.MEDIUM);
		assertEquals(worker.willOverload(project2), false);
	}

	// T2: Test with small project
	@Test
	public void testWillOverloadSmallTest02() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.SMALL);
		Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(project);
		Project project2 = new Project("project2", qualifications, ProjectSize.SMALL);
		assertEquals(worker.willOverload(project2), false);
	}

	// T3: Test with big project
	@Test
	public void testWillOverloadBigTest03() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.BIG);
		Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(project);
		Project project2 = new Project("project2", qualifications, ProjectSize.BIG);
		assertEquals(worker.willOverload(project2), false);
	}

	// T4: Test that worker will get overloaded
	@Test
	public void testWillOverloadTest04() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(new Project("project1", qualifications, ProjectSize.BIG));
		worker.addProject(new Project("project2", qualifications, ProjectSize.BIG));
		worker.addProject(new Project("project3", qualifications, ProjectSize.BIG));
		worker.addProject(new Project("project4", qualifications, ProjectSize.MEDIUM));
		Project project = new Project("project5", qualifications, ProjectSize.BIG);
		assertEquals(worker.willOverload(project), true);
	}

	// T5: Test with worker already on project
	@Test
	public void testWillOverloadContainsProjectTest05() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Worker worker = new Worker("worker1", qualifications, 1);
		Project project = new Project("project1", qualifications, ProjectSize.MEDIUM);
		worker.addProject(project);
		assertFalse(worker.willOverload(project));
	}

	// T6: Test with worker not available
	@Test
	public void testWillOverloadNotAvailableTest06() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Worker worker = new Worker("worker1", qualifications, 1);
		worker.addProject(new Project("project1", qualifications, ProjectSize.BIG));
		worker.addProject(new Project("project2", qualifications, ProjectSize.BIG));
		worker.addProject(new Project("project3", qualifications, ProjectSize.BIG));
		worker.addProject(new Project("project4", qualifications, ProjectSize.BIG));
		Project project = new Project("project5", qualifications, ProjectSize.BIG);
		assertEquals(worker.willOverload(project), true);
	}

	// T7: Test with worker not helpful to project
	@Test 
	public void testWillOverloadNotHelpfulTest07() {
		Set<Qualification> workerQualifications = new HashSet<>();
		workerQualifications.add(new Qualification("qualification1"));
		Worker worker = new Worker("worker1", workerQualifications, 1);
		Set<Qualification> projectQualifications = new HashSet<>();
		projectQualifications.add(new Qualification("qualification2"));
		Project project = new Project("project1", projectQualifications, ProjectSize.MEDIUM);
		assertFalse(worker.willOverload(project));
	}

	@Test
    public void testIsAvailable01(){
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Worker worker = new Worker("Alice", qualifications, 50000);
        assertTrue(worker.isAvailable());
    }

    @Test
    public void testIsAvailable02(){
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Worker worker = new Worker("Alice", qualifications, 50000);

        for (int i = 0; i < 6; i++){
            worker.addProject(new Project("project" + i, qualifications, ProjectSize.SMALL));
        }

        assertTrue(worker.isAvailable());
    }

    @Test
    public void testIsAvailable03(){
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Worker worker = new Worker("Alice", qualifications, 50000);

        for (int i = 0; i < 12; i++){
            worker.addProject(new Project("project" + i, qualifications, ProjectSize.SMALL));
        }

        assertFalse(worker.isAvailable());
    }

    /*
     * Helper function to generate large project sets
     */

    public Set<Project> generateAndAddProjects(int n, Worker w) {
        Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification"));
        Set<Project> p = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Project project = new Project(String.format("project%d", i), qualifications, ProjectSize.MEDIUM);
            w.addProject(project);
            p.add(project);
        }
        return p; 
    } 

	/*
	 * Helper function to generate large qualification sets
	 */

	public Set<Qualification> generateAndAddQualifications(int n, Worker w) {
		Set<Qualification> qualifications = new HashSet<>();
		for (int i = 0; i < n; i++) {
            Qualification qualification = new Qualification(String.format("qualification%d", i));
			w.addQualification(qualification);
			qualifications.add(qualification);
        }
		return qualifications;
	}




	// T1 (Base test): A2, B2, C2 (Properly initialized Worker, 3 projects, 3 qualifications)
    @Test
    public void toDTOTest01() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qual1"));
        qualifications.add(new Qualification("Qual2"));
        qualifications.add(new Qualification("Qual3"));
        
        Worker worker = new Worker("Worker1", qualifications, 1000.0);
        
        Qualification projQual = qualifications.iterator().next();
        Set<Qualification> projQuals = new HashSet<>();
        projQuals.add(projQual);
        
        Project p1 = new Project("Project1", projQuals, ProjectSize.SMALL);
        Project p2 = new Project("Project2", projQuals, ProjectSize.SMALL);
        Project p3 = new Project("Project3", projQuals, ProjectSize.SMALL);
        
        worker.addProject(p1);
        worker.addProject(p2);
        worker.addProject(p3);
        
        // Call toDTO and check its values.
        WorkerDTO dto = worker.toDTO();
        assertEquals("Worker1", dto.getName());
        assertEquals(1000.0, dto.getSalary(), 0.001);
        // Workload is sum of sizes of non-finished projects; each SMALL is 1 so total = 3.
        assertEquals(3, dto.getWorkload());
        
        // Because projects are stored in a set, order is not guaranteed.
        String[] projectNames = dto.getProjects();
        List<String> projectList = Arrays.asList(projectNames);
        assertEquals(3, projectNames.length);
        assertTrue(projectList.contains("Project1"));
        assertTrue(projectList.contains("Project2"));
        assertTrue(projectList.contains("Project3"));
        
        String[] qualificationNames = dto.getQualifications();
        List<String> qualList = Arrays.asList(qualificationNames);
        assertEquals(3, qualificationNames.length);
        assertTrue(qualList.contains("Qual1"));
        assertTrue(qualList.contains("Qual2"));
        assertTrue(qualList.contains("Qual3"));
    }



	// T4: A2 (Initialized Worker), B1 (empty projects set), C2 (3 qualifications)
    @Test
    public void toDTOTest04() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qual1"));
        qualifications.add(new Qualification("Qual2"));
        qualifications.add(new Qualification("Qual3"));
        
        Worker worker = new Worker("Worker1", qualifications, 1000.0);
        // Do not add any projects.
        WorkerDTO dto = worker.toDTO();
        
        // Expect workload = 0 and an empty projectNames array
        assertEquals(0, dto.getWorkload());
        assertEquals(0, dto.getProjects().length);
        
        // The qualifications remain
        String[] qualificationNames = dto.getQualifications();
        assertEquals(3, qualificationNames.length);
        List<String> qualList = Arrays.asList(qualificationNames);
        assertTrue(qualList.contains("Qual1"));
        assertTrue(qualList.contains("Qual2"));
        assertTrue(qualList.contains("Qual3"));
    }
    
    // T5: A2 (Initialized Worker), B2 (3 projects), C1 (empty qualifications)
    @Test
    public void toDTOTest05() {
        Set<Qualification> initialQuals = new HashSet<>();
        Qualification dummy = new Qualification("Dummy");
        initialQuals.add(dummy);
        
        Worker worker = new Worker("Worker1", initialQuals, 1000.0);
        worker.getQualifications().clear();
        
        Set<Qualification> projQuals = new HashSet<>();
        projQuals.add(new Qualification("ProjectQual"));
        Project p1 = new Project("Project1", projQuals, ProjectSize.SMALL);
        Project p2 = new Project("Project2", projQuals, ProjectSize.SMALL);
        Project p3 = new Project("Project3", projQuals, ProjectSize.SMALL);
        
        worker.addProject(p1);
        worker.addProject(p2);
        worker.addProject(p3);
        
        WorkerDTO dto = worker.toDTO();
        
        // Expect qualifications array to be empty.
        assertEquals(0, dto.getQualifications().length);
        
        // Projects are still present.
        String[] projectNames = dto.getProjects();
        List<String> projectList = Arrays.asList(projectNames);
        assertEquals(3, projectNames.length);
        assertTrue(projectList.contains("Project1"));
        assertTrue(projectList.contains("Project2"));
        assertTrue(projectList.contains("Project3"));
        
        // Workload: each project is SMALL (value 1), total = 3.
        assertEquals(3, dto.getWorkload());
    }
	
	

	@Test
	public void removeProjectTest01(){
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Worker w = new Worker("Mike", qualifications, 1000);
		Project p = new Project("Proj1", qualifications, ProjectSize.MEDIUM);
		w.addProject(p);
		// Ensure the project was added
		assertTrue(w.getProjects().contains(p));

		// Remove the project
		w.removeProject(p);

		// Assert it is no longer present
		assertFalse(w.getProjects().contains(p));
	}

	@Test(expected = InvalidParameterException.class)
    public void removeProjectTest02() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Worker w = new Worker("Mike", qualifications, 1000);
        // Prepopulate projects (B2 condition)
        Project p = new Project("Proj1", qualifications, ProjectSize.MEDIUM);
        w.addProject(p);
        // Attempt to remove null, expecting an exception.
        w.removeProject(null);
    }

	// T3: A3 (Object not of type Project) with B2 (projects set size > 0)
    // Oracle: Passing an object not of type Project should cause a ClassCastException.
    @Test(expected = ClassCastException.class)
    public void removeProjectTest03() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Worker w = new Worker("Mike", qualifications, 1000);
        // Prepopulate projects (B2 condition)
        Project p = new Project("Proj1", qualifications, ProjectSize.MEDIUM);
        w.addProject(p);
        // Create an object that is not a Project.
        Object notAProject = new Object();
        // Force a cast to Project; this should throw a ClassCastException.
        w.removeProject((Project) notAProject);
    }

	// T4: A2 (Initialized Project) with B1 (projects set size = 0)
    // Oracle: Removing a project from an empty set should pass without exception.
    @Test
    public void removeProjectTest04() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Worker w = new Worker("Mike", qualifications, 1000);
        Project p = new Project("Proj1", qualifications, ProjectSize.MEDIUM);
        // Ensure the projects set is empty (B1)
        assertTrue("Projects set should be empty", w.getProjects().isEmpty());
        // Removing a project from an empty set should not throw an exception.
        w.removeProject(p);
        // And the project should not be present.
        assertFalse("Project should not be in the projects set", w.getProjects().contains(p));
    }

}
