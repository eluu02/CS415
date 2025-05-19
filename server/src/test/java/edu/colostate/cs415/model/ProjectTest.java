package edu.colostate.cs415.model;

import java.util.*;
import edu.colostate.cs415.dto.ProjectDTO;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest {

    private Project projectX;
    private Project projectWithWorkers;
    private Project otherProject;
    private Set<Qualification> validSet;

    @Before
    public void setUp() {
        validSet = new HashSet<>();
        validSet.add(new Qualification("Qualification1"));

        projectX = new Project("ProjectX", validSet, ProjectSize.MEDIUM);
        projectWithWorkers = new Project("ProjectY", validSet, ProjectSize.SMALL);
        otherProject = new Project("OtherProject", validSet, ProjectSize.MEDIUM);

        Worker worker1 = new Worker("AI-bot", validSet, 50000.0);
        Worker worker2 = new Worker("Remote-worker", validSet, 60000.0);
        projectWithWorkers.addWorker(worker1);
        projectWithWorkers.addWorker(worker2);
    }
	/*
	 * Project(String name, Set<Qualification> qualifications, ProjectSize size) constructor tests
	 */

	// T1: Base test
	@Test
	public void testAllValidInputs01() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.MEDIUM);
		assertNotNull(project);
	}

	// T2: Test creating project with empty name(whitespace)
	@Test (expected = IllegalArgumentException.class)
	public void testEmptyName02() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		new Project("   ", qualifications, ProjectSize.MEDIUM);
	}

	// T9: Test creating project with null name
	@Test (expected = IllegalArgumentException.class)
	public void testNullName09() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		new Project(null, qualifications, ProjectSize.MEDIUM);
	}

	// T3: Test creating project with name of length 60
	@Test
	public void testLongName03() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		String name = "XwGpAZtMdLfCYvNqRsKJBoThVxUEkPyWzDcmFNgYarbjLQHSYnhLDuwWvjPt";
		Project project = new Project(name, qualifications, ProjectSize.MEDIUM);
		assertNotNull(project);
	}

	// T4: Test creating project with null qualification set
	@Test (expected = IllegalArgumentException.class)
	public void testNullQualifications04() {
		new Project("project", null, ProjectSize.MEDIUM);
	}

	// T5: Test creating project with empty qualification set
	@Test (expected = IllegalArgumentException.class)
	public void testEmptyQualifications05() {
		Set<Qualification> qualifications = new HashSet<>();
		Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
		assertNotNull(project);
	}

	// T7: Test creating project with enum ProjectSize.SMALL
	@Test
	public void testSmallProject07() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.SMALL);
		assertNotNull(project);
	}

	// T8: Test creating project with enum ProjectSize.BIG
	@Test
	public void testBigProject08() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		assertNotNull(project);
	}




	 // boolean equals(Object other) test cases

	  // T1 (Base Test Case): D3 (Same class, same name)
	 @Test
	 public void testEquals_SameName01 (){
		 assertFalse("Projects with the same name", projectX.equals(new Object()));
	 }
 
	  
	 @Test    // T2: D1 (null)
	 public void testEquals_Null02() {
		 assertFalse(" A company should not be equal to null", projectX.equals(null));
	 }
	// T3: D2 (Different class) fixed the test case *

	@Test
	public void testEquals_DifferentClass03() {
		assertFalse("Project should not be equal to an object of a different class", projectX.equals("SomeString"));
	}
	  
 
	// T4: D4 (Same class, different name)
 
	@Test 
	   public void testEquals_DifferentName04() {
		  assertFalse("Projects with different names should not be equal", projectX.equals(otherProject));
	   }



// test cases for hashCode() method

/**
     * Test Case: Same Name → Same HashCode
     * 
     * Projects with the same name should have the same hashCode, regardless of their 
     * qualifications or size. Since `hashCode()` is based only on the `name` attribute, 
     * changing other attributes should not affect it.
     */
    @Test
    public void testHashCode_SameName03() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Database"));

        Project project1 = new Project("AI-Research", qualifications, ProjectSize.MEDIUM);
        Project project2 = new Project("AI-Research", qualifications, ProjectSize.SMALL);

        assertEquals("Projects with the same name should have the same hashCode", 
                     project1.hashCode(), project2.hashCode());
    }

    /**
     * Test Case: Different Names → Different HashCode
     * 
     * Projects with different names should ideally have different hash codes, 
     * since `hashCode()` is computed using only the `name` attribute. This test 
     * ensures that changing the name produces a different hash code.
     */
    @Test
    public void testHashCode_DifferentNames() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Database"));

        Project projectA1 = new Project("CyberSecurity", qualifications, ProjectSize.BIG);
        Project projectB1 = new Project("AI-Research", qualifications, ProjectSize.BIG);

        assertNotEquals("Projects with different names should have different hashCodes", 
                        projectA1.hashCode(), projectB1.hashCode());
    }

    /**
     * Test Case: Consistency of HashCode
     * 
     * The `hashCode()` method should always return the same value for the same object 
     * across multiple calls, provided the object does not change. This test ensures 
     * that `hashCode()` remains stable for a single project instance.
     */
    @Test
    public void testHashCode_Consistency() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Networking"));

        Project project = new Project("CloudComputing", qualifications, ProjectSize.SMALL);

        int hashCode1 = project.hashCode();
        int hashCode2 = project.hashCode();
        int hashCode3 = project.hashCode();

        assertEquals("HashCode should be consistent across multiple calls", 
                     hashCode1, hashCode2);
        assertEquals("HashCode should be consistent across multiple calls", 
                     hashCode1, hashCode3);
    }

    /**
     * Test Case: Multiple Unique Projects Should Have Different HashCodes
     * 
     * This test verifies that multiple different project names generate different hash codes. 

     */
    @Test
    public void testHashCode_DistinctHashCodesForManyProjects() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Software Engineering"));

        Project project1 = new Project("Alpha", qualifications, ProjectSize.SMALL);
        Project project2 = new Project("Beta", qualifications, ProjectSize.MEDIUM);
        Project project3 = new Project("Gamma", qualifications, ProjectSize.BIG);

        assertNotEquals("Different projects should have different hashCodes", 
                        project1.hashCode(), project2.hashCode());
        assertNotEquals("Different projects should have different hashCodes", 
                        project2.hashCode(), project3.hashCode());
        assertNotEquals("Different projects should have different hashCodes", 
                        project1.hashCode(), project3.hashCode());
    }

      /* getName() Tests */

    @Test(expected = IllegalArgumentException.class)
    public void testGetName_Null02() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project(null, qualifications, ProjectSize.SMALL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetName_Empty03() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project(" ", qualifications, ProjectSize.SMALL);
    }

    @Test
    public void testGetName_Valid01() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project1", qualifications, ProjectSize.SMALL);
        assertEquals("project1", project.getName());
    }
   



    public void getName01(){
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project(null, qualifications, ProjectSize.SMALL);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getName02(){
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project(" ", qualifications, ProjectSize.SMALL);
    }

    @Test
    public void getName03(){
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project1", qualifications, ProjectSize.SMALL);
        assertEquals(project.getName(), "project1");
    }



	   // public ProjectSize getSize() test cases

	@Test
	public void testGetSize_Small() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("scheduled")); // Ensure it's not empty
		Project project = new Project("TestProject", qualifications, ProjectSize.SMALL);
		assertEquals(ProjectSize.SMALL, project.getSize());
		
		assert(true);
	}
	
	@Test
	public void testGetSize_Medium() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("urgent"));
		Project project = new Project("TestProject", qualifications, ProjectSize.MEDIUM);
		assertEquals(ProjectSize.MEDIUM, project.getSize());
		
		assert(true);
	}
	
	@Test
	public void testGetSize_Big() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("priority"));
		Project project = new Project("TestProject", qualifications, ProjectSize.BIG);
		assertEquals(ProjectSize.BIG, project.getSize());
		assert(true);
	}

	@Test
	public void testGetSize_Default() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("default"));

		Project project = new Project("DefaultProject", qualifications, ProjectSize.MEDIUM);
		
		assertNotNull("Project size should not be null", project.getSize());
		assertEquals("Expected ProjectSize.MEDIUM", ProjectSize.MEDIUM, project.getSize());
	}
	@Test
	public void testGetSize_MultipleQualifications() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Database"));
		qualifications.add(new Qualification("Networking"));
		qualifications.add(new Qualification("Security"));
	
		Project project = new Project("MultiQualificationProject", qualifications, ProjectSize.BIG);
	
		assertEquals("Expected ProjectSize.BIG", ProjectSize.BIG, project.getSize());
	}

	
    /* toString() tests */

    @Test
    public void testToString_EmptyProject() {
        String expected = "ProjectX:0:PLANNED";
        assertEquals("Empty project should have zero workers and PLANNED status.", expected, projectX.toString());
    }

    @Test
    public void testToString_ProjectWithWorkers() {
        String expected = "ProjectY:2:PLANNED";
        assertEquals("Project with 2 workers should be formatted correctly.", expected, projectWithWorkers.toString());
    }

    @Test
    public void testToString_ActiveProject() {
        projectX.setStatus(ProjectStatus.ACTIVE);
        String expected = "ProjectX:0:ACTIVE";
        assertEquals("Project should reflect ACTIVE status in toString().", expected, projectX.toString());
    }

    @Test
    public void testToString_FinishedProject() {
        projectX.setStatus(ProjectStatus.FINISHED);
        String expected = "ProjectX:0:FINISHED";
        assertEquals("Project should reflect FINISHED status in toString().", expected, projectX.toString());
    }
	/* addWorker() tests */

	// T1: A2, B1, C1 -- base choice test
	@Test
	public void addWorkerBaseChoice01() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		Worker w = new Worker("Boble", qualifications, 1.50);
		try {
			project.addWorker(w);
		} catch (Exception e) {
			assert(false);
		}
		assert(project.getWorkers().contains(w));
	}

	// T2: A1, B1, C1 -- worker already on project
	@Test
	public void addWorkerAlreadyOnProject02() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		Worker w = new Worker("Boble", qualifications, 1.50);
		try {
			project.addWorker(w);
			project.addWorker(w);
			project.addWorker(w);
			project.addWorker(w);
		} catch (Exception e) {
			assert(false);
		}
		assert(project.getWorkers().contains(w));
	}

	// T4: A2, B1, C2 -- worker is null
	@Test(expected = IllegalArgumentException.class)
	public void addWorkerNull04() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		Worker w = null;
		project.addWorker(w);
	}
	
	/* Single test for getWorkers */
	
	// Only testing the base choice because other tests are not possible unless the constructor fails
	@Test
	public void getWorkersBaseChoice01() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		Worker w1 = new Worker("w1", qualifications, 0);
		Worker w2 = new Worker("w2", qualifications, 0);
		Worker w3 = new Worker("w3", qualifications, 0);
		project.addWorker(w1);
		project.addWorker(w2);
		project.addWorker(w3);
		assert(project.getWorkers().contains(w1));
		assert(project.getWorkers().contains(w2));
		assert(project.getWorkers().contains(w3));
  }

	/* Tests for both the setter and getter methods. */
  
	// These methods are both tested here because they require each other to be tested.
	// I.e. You cannot test the getter without calling the setter and vice versa.
	// Their ISP / BCC tables are also essentially identical.
	@Test
	public void setAndGetStatus() {
		ProjectStatus PLANNED = ProjectStatus.PLANNED;
		ProjectStatus SUSPENDED = ProjectStatus.SUSPENDED;
		ProjectStatus ACTIVE = ProjectStatus.ACTIVE;
		ProjectStatus FINISHED = ProjectStatus.FINISHED;
		Qualification q = new Qualification("testQ");
		HashSet<Qualification> qs = new HashSet<>();
		qs.add(q);
		Project p = new Project("testProject", qs, ProjectSize.BIG);

		try {
			p.setStatus(PLANNED);
		} catch (Exception e) {
			assert(false);
		}
		assertEquals(p.getStatus(), PLANNED);

		try {
			p.setStatus(SUSPENDED);
		} catch (Exception e) {
			assert(false);
		}
		assertEquals(p.getStatus(), SUSPENDED);

		try {
			p.setStatus(ACTIVE);
		} catch (Exception e) {
			assert(false);
		}
		assertEquals(p.getStatus(), ACTIVE);

		try {
			p.setStatus(FINISHED);
		} catch (Exception e) {
			assert(false);
		}
		assertEquals(p.getStatus(), FINISHED);

		boolean passed = false;
		try {
			p.setStatus(null);
		} catch (IllegalArgumentException e) {
			assertEquals(p.getStatus(), FINISHED);
			passed = true;
		}
		assert(passed);
	}

	/* getRequiredQualifications() tests */

	// T1: A2 -- Base Choice of 3 qualifications
	@Test
	public void getRequiredQualifications01() {
		Set<Qualification> qualifications = new HashSet<>();
		Qualification q1 = new Qualification("q1");
		Qualification q2 = new Qualification("q2");
		Qualification q3 = new Qualification("q3");
		qualifications.add(q1);
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		project.addQualification(q2);
		project.addQualification(q3);
		assert(project.getRequiredQualifications().contains(q1));
		assert(project.getRequiredQualifications().contains(q2));
		assert(project.getRequiredQualifications().contains(q3));
	}

	// T2: A1 -- Empty set
	@Test (expected = IllegalArgumentException.class)
	public void getRequiredQualificationsEmpty02() {
		//Note: Test depends on an empty qualifications set being possible. The Project constructor makes this impossible. However, the docs don't specify that. Pls change.
		Set<Qualification> qualifications = new HashSet<>();
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		assert(project.getRequiredQualifications().isEmpty());
		
	}

	/* addQualification(Qualification qualification) tests */

	// T1: A1 B1 C2 -- Base Choice
	@Test
	public void addQualificationBaseChoice01() {
		Set<Qualification> qualifications = new HashSet<>();
		Qualification q1 = new Qualification("q1");
		Qualification q2 = new Qualification("q2");
		Qualification q3 = new Qualification("q3");
		qualifications.add(q1);
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		project.addQualification(q2);
		project.addQualification(q3);
		assert(project.getRequiredQualifications().contains(q1));
		assert(project.getRequiredQualifications().contains(q2));
		assert(project.getRequiredQualifications().contains(q3));
	}

	// T3: A3 B1 C2 -- Null
	@Test(expected = IllegalArgumentException.class)
	public void addQualificationNull03() {
		Set<Qualification> qualifications = new HashSet<>();
		Qualification q1 = new Qualification("q1");
		Qualification q2 = null;
		qualifications.add(q1);
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		project.addQualification(q2);
	}

	// T4: A1 B2 C2 -- No Duplicates
	@Test
	public void addQualificationNoDuplicates04() {
		Set<Qualification> qualifications = new HashSet<>();
		Qualification q1 = new Qualification("sameDescription");
		Qualification q2 = new Qualification("sameDescription");
		qualifications.add(q1);
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		project.addQualification(q2);
		assert(project.getRequiredQualifications().size() == 1);
	}

	// T5: A1 B1 C1 -- Empty
	@Test (expected = IllegalArgumentException.class)
	public void addQualificationEmpty05() {
		Set<Qualification> qualifications = new HashSet<>();
		//Note: Test depends on an empty qualifications set being possible. The Project constructor makes this impossible. However, the docs don't specify that. Pls change.
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		
		assert(project.getRequiredQualifications().isEmpty());
		
    }
  
	/* removeWorker(Worker worker) tests */

	// T1: A1, B1, C1 -- base choice test
	@Test
	public void removeWorkerBaseChoice01() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		Worker w = new Worker("Boble", qualifications, 1.50);

		project.addWorker(w);
		assert(project.getWorkers().contains(w));
		project.removeWorker(w);
		assertFalse(project.getWorkers().contains(w));
	}

	// T2: A2, B1, C1 -- remove worker who is already not on the project
	@Test
	public void removeWorkerNotOnProject02() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		Worker w = new Worker("Boble", qualifications, 1.50);
		try {
			project.removeWorker(w);
		} catch (Exception e) {
			assert(false);
		}
		assertFalse(project.getWorkers().contains(w));
	}

	// T4: A1, B1, C2 -- remove null worker
	@Test(expected = IllegalArgumentException.class)
	public void removeWorkerNull04() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		Worker w = null;
		project.removeWorker(w);
	}
  
  /* removeAllWorkers() tests */

	// T1: A1 -- removeAllWorkers() from a project with no workers
	@Test
	public void removeAllWorkersNoWorkers01() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		try {
			project.removeAllWorkers();
		} catch (Exception e) {
			assert(false);
		}
		assert(project.getWorkers().isEmpty());
	}

	// T2: A2 -- removeAllWorkers() from a project with some workers
	@Test
	public void removeAllWorkersSomeWorkers02() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project", qualifications, ProjectSize.BIG);
		Worker w1 = new Worker("w1", qualifications, 0);
		Worker w2 = new Worker("w2", qualifications, 0);
		Worker w3 = new Worker("w3", qualifications, 0);
		project.addWorker(w1);
		project.addWorker(w2);
		project.addWorker(w3);
		try {
			project.removeAllWorkers();
		} catch (Exception e) {
			assert(false);
		}
		assert(project.getWorkers().isEmpty());
  }

	/*
	 * Set<Qualification> getMissingQualifications() tests
	 */

	// T1: Base test
	@Test
	public void testGetMissingQualifications() {
		Set<Qualification> qualifications = generateAndAddQualifications(25);
		Project project = new Project("project1", qualifications, ProjectSize.MEDIUM);
		assertEquals(project.getMissingQualifications().size(), 25);
	}

	// T2: Test returning empty set of missing qualifications
	@Test
	public void testGetMissingQualificationsEmptySet() {
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("qualification1"));
		Project project = new Project("project1", qualifications, ProjectSize.MEDIUM);
		Worker worker = new Worker("worker1", qualifications, 1);
		project.addWorker(worker);
		assertEquals(project.getMissingQualifications().size(), 0);
		assertEquals(project.getMissingQualifications(), Collections.emptySet());
	}

	// T3: Test returning large set of missing qualifications (> 50)
	@Test
	public void testGetMissingQualificationsLarge() {
		Set<Qualification> qualifications = generateAndAddQualifications(100);
		Project project = new Project("project1", qualifications, ProjectSize.MEDIUM);
		assertEquals(project.getMissingQualifications().size(), 100);
	}

	/*
	 * Helper function to generate large qualification sets
	 */

	 public Set<Qualification> generateAndAddQualifications(int n) {
		Set<Qualification> qualifications = new HashSet<>();
		for (int i = 0; i < n; i++) {
            Qualification qualification = new Qualification(String.format("qualification%d", i));
			qualifications.add(qualification);
        }
		return qualifications;
	}

	/* removeWorker(Worker worker) tests */

	// T1: A1, B1, C2 -- test Helpful worker
	@Test
	public void isHelpfulHelpfulWorker01() {
		Qualification pq = new Qualification("ProjectQualification");
		Set<Qualification> projectQualifications = new HashSet<>();
		projectQualifications.add(pq);
		Project project = new Project("project", projectQualifications, ProjectSize.BIG);

		Worker workerHelpful = new Worker("Boble", projectQualifications, 1.50);

		assert(project.isHelpful(workerHelpful));
	}

	// T3: A3, B1, C2 -- test null worker

	@Test
	public void isHelpfulNullWorker03() {

		Qualification pq = new Qualification("ProjectQualification");
		Set<Qualification> projectQualifications = new HashSet<>();
		projectQualifications.add(pq);
		Project project = new Project("project", projectQualifications, ProjectSize.BIG);
		
		Worker workerNull = null;

		assertFalse(project.isHelpful(workerNull));
	}

	// T4: A1, B2, C2 -- test unhelpful worker
	@Test
	public void isHelpfulUnhelpfulWorker04() {
		Qualification pq = new Qualification("ProjectQualification");
		Set<Qualification> projectQualifications = new HashSet<>();
		projectQualifications.add(pq);
		Project project = new Project("project", projectQualifications, ProjectSize.BIG);
		
		Qualification wq = new Qualification("WorkerQualification");
		Set<Qualification> workerQualifications = new HashSet<>();
		workerQualifications.add(wq);
		Worker workerUnhelpful = new Worker("Boble", workerQualifications, 1.50);

		assertFalse(project.isHelpful(workerUnhelpful));
	}

	// T5: A1, B1, C1 -- test no missing qualifications
	@Test
	public void isHelpfulNoMissingQualifications05() {
		Qualification pq = new Qualification("ProjectQualification");
		Set<Qualification> projectQualifications = new HashSet<>();
		projectQualifications.add(pq);
		Project project = new Project("project", projectQualifications, ProjectSize.BIG);
		
		Worker workerHelpful = new Worker("Boble", projectQualifications, 1.50);
		project.addWorker(workerHelpful);

		Worker workerRedundant = new Worker("Boble", projectQualifications, 1.50);

		assertFalse(project.isHelpful(workerRedundant));
	}

	/* toDTO() tests */

	// Test only checks that DTO matches project's state
	@Test
	public void toDTO() {
		Set<Qualification> qualifications = new HashSet<>();
		Qualification q1 = new Qualification("qualification");
		qualifications.add(q1);
		String projectName = "project";
		ProjectSize projectSize = ProjectSize.BIG;
		Project project = new Project(projectName, new HashSet<>(qualifications), projectSize);
		Worker Boble = new Worker("Boble", new HashSet<>(qualifications), 1.50);
		Worker Bobert = new Worker("Bobert", new HashSet<>(qualifications), 1.50);
		Worker Bobra = new Worker("Bobra", new HashSet<>(qualifications), 1.50);
		project.addWorker(Bobra);
		project.addWorker(Bobert);
		project.addWorker(Boble);
		Qualification q2 = new Qualification("missingQualification");
		project.addQualification(q2);

		ProjectDTO dto = project.toDTO();
		assertEquals(dto.getName(), projectName);
		assertEquals(dto.getSize(), projectSize);
		assertEquals(dto.getStatus(), ProjectStatus.PLANNED);

		Set<String> workersNames = convertArrayToSet(dto.getWorkers());
		assert(workersNames.contains(Bobra.getName()));
		assert(workersNames.contains(Bobert.getName()));
		assert(workersNames.contains(Boble.getName()));

		Set<String> qualificationNames = convertArrayToSet(dto.getQualifications());
		assert(qualificationNames.contains(q1.toString()));
		assert(qualificationNames.contains(q2.toString()));

		Set<String> missingQualificationNames = convertArrayToSet(dto.getMissingQualifications());
		assert(missingQualificationNames.contains(q2.toString()));
	}

	/*
     * Refactoring tests/Tests for missed faults
     */

    @Test (expected = IllegalArgumentException.class)
    public void testProjectConstructorNullProjectSize() {
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project1", qualifications, null);
    }


	// Helper method for converting arrays to sets
	// Credit to Geeks4Geeks for this method
	private static <T> Set<T> convertArrayToSet(T array[]) { 
        Set<T> set = new HashSet<>(); 
        for (T t : array) { 
            set.add(t); 
        }
        return set; 
    } 
}
