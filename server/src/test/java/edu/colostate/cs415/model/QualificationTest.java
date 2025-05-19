package edu.colostate.cs415.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import java.security.InvalidParameterException;

import java.util.HashSet;
import java.util.Set;


import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.model.Qualification;

public class QualificationTest {

    @Test
    public void qualificationTest01() {
        String description = "Software Dev";
        Qualification qualification = new Qualification(description);
        assertEquals(description, qualification.toDTO().getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void qualificationTest02() {
        Qualification qualification = new Qualification(null);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void qualificationTest03() {
        Qualification qualification = new Qualification("");
        
    }

    @Test
    public void qualificationTest04() {
        
        String longDescription = repeat("A", 5000);
        
        Qualification qualification = new Qualification(longDescription);
        
        // Validate that the length is exactly 1000 characters
        assertEquals(longDescription, qualification.toDTO().getDescription());
    }

    // using a manual repeat method to get a string length of 1000 since .repeat(int) isn't supported in Java 8
    public static String repeat(String str, int times) {
        StringBuilder repeated = new StringBuilder();
        for (int i = 0; i < times; i++) {
            repeated.append(str);
        }
        return repeated.toString();
    }



	@Test
	public void equalsTest01() {
		Qualification q1 = new Qualification("Test Qual");
		Qualification q2 = new Qualification("Test Qual");;

		assertTrue(q1.equals(q2));
	}

	@Test
	public void equalsTest02() {
		Qualification q1 = new Qualification("Test Qual");
		Qualification q2 = null;

		
        assertFalse(q1.equals(q2));
        
	}

	@Test
	public void equalsTest03() {
		Qualification q1 = new Qualification("Test Qual");
		String q2 = "abcd";

		assertFalse(q1.equals(q2));
	}

	@Test
	public void equalsTest05() {
		Qualification q1 = new Qualification("Test Q");
		Qualification q2 = new Qualification("Test Qual");

		assertFalse(q1.equals(q2));
	}



    @Test
    public void hashCodeTest01(){
        Qualification q1 = new Qualification("Qual1");
        Qualification q2 = new Qualification("Qual2");
        assertNotEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void hashCodeTest04(){
        Qualification q1 = new Qualification("Qual1 !/$f");
        Qualification q2 = new Qualification("Qual1 !/$f");
        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void toStringTest03(){
        Qualification q = new Qualification("Qual1 !/$f");
        
        assertEquals(q.toString(), "Qual1 !/$f");
    }

    private Qualification addManyWorkers(Qualification q, int numWorkers) throws Exception{
        Set<Worker> ws = new HashSet();
        Set<Qualification> qs = new HashSet<>();
        qs.add(q);
        for(int i = 0; i < numWorkers; i++){
            Worker w = new Worker(("Worker " + i), qs, 10000);
            ws.add(w);
        }

        if(numWorkers > 0){ 
            Field qualField = Qualification.class.getDeclaredField("workers");
            qualField.setAccessible(true);
            qualField.set(q, ws);

        }

        return q;
    }


    @Test
    // T1: A2) Set With 75 workers B2) Set of Type Worker
    public void getWorkersTest01(){
        try {
            Qualification q = new Qualification("Qualification");
            q = addManyWorkers(q, 75);
            Set<Worker> ws = q.getWorkers();

            Field qualField = Qualification.class.getDeclaredField("workers");
            qualField.setAccessible(true);
            @SuppressWarnings("unchecked")

            Set<Worker> testWS = (Set<Worker>) qualField.get(q);
            
            assertEquals(75, ws.size());
            assertEquals(testWS, ws);
        } catch (Exception e) {
            // Failed Preforming Test
            assertTrue(false);
        }
    }

    @Test
    // T4: A1) Set With 0 workers B2) Set of Type Worker
    public void getWorkersTest04(){
        try {
            Qualification q = new Qualification("Qualification");
            q = addManyWorkers(q, 0);
            Set<Worker> ws = q.getWorkers();

            Field qualField = Qualification.class.getDeclaredField("workers");
            qualField.setAccessible(true);
            @SuppressWarnings("unchecked")
            
            Set<Worker> testWS = (Set<Worker>) qualField.get(q);

            assertEquals(0, ws.size());
            assertEquals(testWS, ws);
        } catch (Exception e) {
            // Failed Preforming Test
            assertTrue(false);
        }
    }

    @Test
    // T5: A2) Set With 800 workers B2) Set of Type Worker
    public void getWorkersTest05(){
        try {
            Qualification q = new Qualification("Qualification");
            q = addManyWorkers(q, 800);
            Set<Worker> ws = q.getWorkers();

            Field qualField = Qualification.class.getDeclaredField("workers");
            qualField.setAccessible(true);
            @SuppressWarnings("unchecked")
            
            Set<Worker> testWS = (Set<Worker>) qualField.get(q);

            assertEquals(800, ws.size());
            assertEquals(testWS, ws);
        } catch (Exception e) {
            // Failed Preforming Test
            assertTrue(false);
        }
    }

    // T1: A1 (worker not in set before execution), B2 (worker is non-null)
    // Oracle: Pass
    @Test
    public void addWorkerTest01() {
        Set<Qualification> qs = new HashSet<>();
        Qualification qualification = new Qualification("Test Qualification");
        qs.add(qualification);
        Worker worker = new Worker("John Doe", qs, 100);
        qualification.addWorker(worker);
        try {
            Field workersField = Qualification.class.getDeclaredField("workers");
            workersField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> ws = (Set<Worker>) workersField.get(qualification);
            assertNotNull("Worker set should not be null", ws);
            assertTrue("Worker should be added", ws.contains(worker));
            assertEquals("Worker set should contain exactly 1 worker", 1, ws.size());
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    // TODO: Do we want this to throw an exception or just not update the list or add the worker twice

    // T2: A2 (worker is already in the set), B2 (worker is non-null)
    // Oracle: Fail (expect an exception on duplicate addition)
    @Test
    public void addWorkerTest02() {
        Set<Qualification> qs = new HashSet<>();
        Qualification qualification = new Qualification("Test Qualification");
        qs.add(qualification);
        Worker worker = new Worker("John Doe", qs, 100);
        qualification.addWorker(worker); // First addition
        try {
            Field workersField = Qualification.class.getDeclaredField("workers");
            workersField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> ws = (Set<Worker>) workersField.get(qualification);
            assertNotNull("Worker set should not be null", ws);
            assertTrue("Worker should be added", ws.contains(worker));
        } catch (Exception e) {
            assertTrue(false);
        }

        // Second addition should return the same list.
        qualification.addWorker(worker);
            try {
            Field workersField = Qualification.class.getDeclaredField("workers");
            workersField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> ws = (Set<Worker>) workersField.get(qualification);
            assertNotNull("Worker set should not be null", ws);
            // Check that the set still contains exactly one worker
            assertEquals("Worker should be added only once", 1, ws.size());
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    // T3: B1 (worker is null) along with A1 (since null is not in the set)
    // Oracle: Fail (expect an exception)
    @Test(expected = InvalidParameterException.class)
    public void addWorkerTest03() {
        Set<Qualification> qs = new HashSet<>();
        Qualification qualification = new Qualification("Test Qualification");
        qs.add(qualification);
        qualification.addWorker(null);
    }


    @Test
    public void toDTOTest01() {
        String description = "Test Qualification";
        Qualification qualification = new Qualification(description);
        Set<Qualification> qs = new HashSet<>();
        qs.add(qualification);
        for (int i = 1; i <= 15; i++) {
            Worker worker = new Worker("Worker" + i, qs, 1000);
            qualification.addWorker(worker);
        }

        QualificationDTO dto = qualification.toDTO();
        // Check that the description is correctly set
        assertEquals(description, dto.getDescription());

        // Check that all 15 worker names are present in the DTO
        String[] workerNames = dto.getWorkers();
        assertNotNull(workerNames);
        assertEquals(15, workerNames.length);

        // Verify that each expected worker name is present
        for (int i = 1; i <= 15; i++) {
            String expectedName = "Worker" + i;
            boolean found = false;
            for (String name : workerNames) {
                if (expectedName.equals(name)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
    }

    /**
     * T2: A2 + B1
     * No workers’ names should be present in the DTO, and the description should match.
     */
    @Test
    public void toDTOTest02() {
        String description = "Test Qualification";
        Qualification qualification = new Qualification(description);

        // No workers are added

        QualificationDTO dto = qualification.toDTO();
        // Check that the description is correctly set
        assertEquals(description, dto.getDescription());

        // Check that the workers array is empty
        String[] workerNames = dto.getWorkers();
        assertNotNull(workerNames);
        assertEquals(0, workerNames.length);
    }

    /**
     * T1: A1 = True, B1 = False, C2 = Worker object.
     * Add worker to Qualification, then remove. 
     * Oracle: Worker is not in Set after execution.
     */
    @Test
    public void removeWorkerTest01() {
        Qualification qualification = new Qualification("Test Qualification");
        Set<Qualification> qs = new HashSet<>();
        qs.add(qualification);
        Worker worker = new Worker("John Doe", qs, 100);
        qualification.addWorker(worker); // First addition
        // A1: Worker is added before removal.
        qualification.addWorker(worker);
        // Execute removeWorker.
        qualification.removeWorker(worker);
        // B1: Worker should no longer be in the set.
        assertFalse(qualification.getWorkers().contains(worker));
    }

    /**
     * T2: A2 = False, B1 = False, C2 = Worker object.
     * Pass in a worker who has not been added.
     * Oracle: Worker is not in Set after execution.
     */
    @Test
    public void removeWorkerTest02() {
        Qualification qualification = new Qualification("Test Qualification");
        Set<Qualification> qs = new HashSet<>();
        qs.add(qualification);
        Worker worker1 = new Worker("John Doe", qs, 100);
        Worker worker = new Worker("John Doe", qs, 100);
        // Do not add the worker to qualification (A2)
        // Execute removeWorker.
        qualification.addWorker(worker1);
        qualification.removeWorker(worker);
        // B1: Worker should not be in the set.
        assertFalse(qualification.getWorkers().contains(worker));
    }

    
    /**
     * T3: A1 = True, B1 = False, C1 = null.
     * Pass in a null worker.
     * Oracle: Exception is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeWorkerTest03() {
        Qualification qualification = new Qualification("Test Qualification");
        qualification.removeWorker(null);
        
    }
    

}
