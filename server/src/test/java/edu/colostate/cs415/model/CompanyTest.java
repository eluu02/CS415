package edu.colostate.cs415.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashSet;

public class CompanyTest {
    private Company company;
    private Qualification q1, q2;
    private Worker worker1, worker2;
    private Project project;

    @Before
    public void setUp() {
        company = new Company("Test Company");
    }

	  @Test
    public void testValidName() {
        assertNotNull(this.company);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
        new Company(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
        new Company("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhitespaceName() {
        new Company("   ");
    }

/*
 * public boolean equals(Object other) Test Cases */


    // T1: Base Case - Two companies with the same name should be equal
    @Test
    public void testEquals_SameCompanyName() {
        Company company1 = new Company("CompanyX");
        Company company2 = new Company("CompanyX");

        assertTrue("Expected companies with the same name to be equal", company1.equals(company2));
    }

    // T2: Null comparison - Should return false
    @Test
    public void testEquals_NullObject() {
        Company company = new Company("CompanyX");

        assertFalse("Expected comparison with null to return false", company.equals(null));
    }

    // T3: Non-Company Object - Should return false
    @Test
    public void testEquals_NonCompanyObject() {
        Company company = new Company("CompanyX");
        String notACompany = "NotACompany";

        assertFalse("Expected comparison with a non-Company object to return false", company.equals(notACompany));
    }

    // T4: Different company names - Should return false
    @Test
    public void testEquals_DifferentCompanyNames() {
        Company company1 = new Company("CompanyX");
        Company company2 = new Company("AnotherCompany");

        assertFalse("Expected companies with different names to not be equal", company1.equals(company2));
    }

 

    /*
     * setAvailableWorkers(Company company, int count)
     * Method to set up tests for toString 
     * Feel free to use them if applicable
     * DO NOT MODIFY
     */
    private void setAvailableWorkers(Company company, int count) throws Exception {
        Field availableField = Company.class.getDeclaredField("available");
        availableField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Set<Worker> available = (Set<Worker>) availableField.get(company);
        available.clear();
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
        
        for (int i = 0; i < count; i++) {
            String name = String.format("Test Worker %d", i);
            available.add(new Worker(name, qualifications, 0.0));
        }
    }

    /*
     * setProjects(Company company, int count)
     * Method to set up tests for toString 
     * Feel free to use them if applicable
     * DO NOT MODIFY
     */
    private void setProjects(Company company, int count) throws Exception {
        Field projectsField = Company.class.getDeclaredField("projects");
        projectsField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Set<Project> projects = (Set<Project>) projectsField.get(company);
        projects.clear();
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Qualification"));
        for (int i = 0; i < count; i++) {
            String name = String.format("Test Project %d", i);
            projects.add(new Project(name, qualifications, ProjectSize.SMALL));
        }
    }

    // T1 (Base test): A2 (name > 0), B3 (available count = 1005), C3 (projects count = 1005)
    @Test
    public void toStringTest01() {
        Company company = new Company("Test Company");
        try {
            setAvailableWorkers(company, 1005);
            setProjects(company, 1005);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }
        String expected = "Test Company:1005:1005"; 
        assertEquals(expected, company.toString());
    }

    // T2: A1 (empty name), B3 (available count = 1005), C3 (projects count = 1005)
    @Test
    public void toStringTest02() {
        Company company = new Company("Test Company");
        try {
            setAvailableWorkers(company, 1005);
            setProjects(company, 1005);
            Field nameField = Company.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(company, "");
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }
        String expected = ":1005:1005";
        assertEquals(expected, company.toString());
    }

    // T3: A2 (name > 0), B1 (available count = 0), C3 (projects count = 1005)
    @Test
    public void toStringTest03() {
        Company company = new Company("Test Company");
        try {   
            setAvailableWorkers(company, 0);
            setProjects(company, 1005);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }
        String expected = "Test Company:0:1005";
        assertEquals(expected, company.toString());
    }

    // T4: A2 (name > 0), B2 (available count = 1), C3 (projects count = 1005)
    @Test
    public void toStringTest04() {
        Company company = new Company("Test Company");
        try {
            setAvailableWorkers(company, 1);
            setProjects(company, 1005);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }
        String expected = "Test Company:1:1005";
        assertEquals(expected, company.toString());
    }

    // T5: A2 (name > 0), B3 (available count = 1005), C1 (projects count = 0)
    @Test
    public void toStringTest05() {
        Company company = new Company("Test Company");
        try {
            setAvailableWorkers(company, 1005);
            setProjects(company, 0);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }
        String expected = "Test Company:1005:0";
        assertEquals(expected, company.toString());
    }

    // T6: A2 (name > 0), B3 (available count = 1005), C2 (projects count = 1)
    @Test
    public void toStringTest06() {
        Company company = new Company("Test Company 6");
        try {
            setAvailableWorkers(company, 1005);
            setProjects(company, 1);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }
        String expected = "Test Company 6:1005:1";
        assertEquals(expected, company.toString());
    }
    
  

 /*
    public Worker createWorker(String name, Set<Qualification> qualifications, double salary) Test Cases */


         // T1: Valid worker with multiple qualifications and positive salary
        @Test
        public void testCreateWorker_ValidWorker() {
            // Pre-populate qualifications inside the test
            Qualification java = company.createQualification("Java");
            Qualification python = company.createQualification("Python");

            Set<Qualification> qualifications = new HashSet<>();
            qualifications.add(java);
            qualifications.add(python);

            Worker worker = company.createWorker("Ben", qualifications, 70000.0);

            assertNotNull("Worker should be created", worker);
            assertTrue("Worker should be employed", company.getEmployedWorkers().contains(worker));
            assertTrue("Worker should be available", company.getAvailableWorkers().contains(worker));
        } 

            // T2: Invalid names (empty and null)
        @Test
        public void testCreateWorker_InvalidName() {
            // Pre-populate qualifications inside the test
            Qualification java = company.createQualification("Java");

            Set<Qualification> qualifications = new HashSet<>();
            qualifications.add(java);

            Worker worker1 = company.createWorker("", qualifications, 50000.0);
            Worker worker2 = company.createWorker(null, qualifications, 50000.0);

            assertNull("Worker should not be created due to empty name", worker1);
            assertNull("Worker should not be created due to null name", worker2);
        }


          // T3: Invalid name (null)
        @Test
        public void testCreateWorker_NullName() {
            Qualification java = company.createQualification("Java");

            Set<Qualification> qualifications = new HashSet<>();
            qualifications.add(java);

            Worker worker = company.createWorker(null, qualifications, 50000.0);

            assertNull("Worker should not be created due to null name", worker);
        }


        // T4: No qualifications provided, should return null
        @Test
        public void testCreateWorker_NoQualifications() {
            Set<Qualification> qualifications = new HashSet<>();
            Worker worker = company.createWorker("Joey", qualifications, 50000.0);
            assertNull("Worker should not be created due to missing qualifications", worker);
        }

            // T5: Negative salary, should return null
        @Test
        public void testCreateWorker_NegativeSalary() {
            // Pre-populate qualifications inside the test
            Qualification java = company.createQualification("Java");

            Set<Qualification> qualifications = new HashSet<>();
            qualifications.add(java);

            Worker worker = company.createWorker("John Doe", qualifications, -5000.0);
            assertNull("Worker should not be created due to negative salary", worker);
        }

     /*
     * Helper to register a qualification in the company.
     * 
     */
    private void registerQualification(Qualification q) {
        // 
        // company.createQualification(q);
        try {
            Field qualificationsField = Company.class.getDeclaredField("qualifications");
            qualificationsField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Qualification> qualifications = (Set<Qualification>) qualificationsField.get(company);
            qualifications.add(q);
        } catch (Exception e) {
            return;
        }
        return;
    }
    
    /*
     * Helper to create a set of qualifications.
     * 
     * @param totalCount: total number of Qualification objects to include in the set.
     * @param registerCount: how many of these qualifications should be registered
     *  
     *   we simply create new Qualification objects that are not registered.
     */
    private Set<Qualification> createQualificationSetWithRegistration(int totalCount, int registerCount) {
        Set<Qualification> qs = new HashSet<>();
        for (int i = 0; i < totalCount; i++) {
            String desc = "Qualification " + i;
            Qualification q = new Qualification(desc);
            if (i < registerCount) {
                registerQualification(q);
            } 
            qs.add(q);
            
            /*
            Substitute When implemented

            Qualification q = new Qualification(desc);
            if (i < registerCount) {
                q = company.createQualification(desc);
            } else {
                q = new Qualification(desc);
            }
            qs.add(q);
            */
        }
        return qs;
    }
    
    // --- Tests for createProject(…) following the new ISP and BCC table ---
    
    // T1 (Base test): A2, B2, C1, D2, E2
    // Name: valid 30-char string, qs: 50 valid qualifications and company has all (registerCount = 50), ProjectSize.MEDIUM.
    @Test
    public void createProjectTest01() {
        String name = String.format("%1$30s", "A"); // 30-character string
        Set<Qualification> qs = createQualificationSetWithRegistration(50, 50); // all qualifications registered (C1)
        Project project = company.createProject(name, qs, ProjectSize.MEDIUM); // E2
        assertNotNull("Project should be created successfully", project);
    }
    
    // T2: A1, B2, C1, D2, E2
    // Empty name string – should fail.
    @Test
    public void createProjectTest02() {
        String name = "";  // A1: length = 0
        Set<Qualification> qs = createQualificationSetWithRegistration(50, 50);
        Project project = company.createProject(name, qs, ProjectSize.MEDIUM);
        assertNull(project);
    }
    
    // T3: A3, B2, C1, D2, E2
    // 50-char name, 50 qualifications (all registered), valid size.
    @Test
    public void createProjectTest03() {
        String name = String.format("%1$50s", "A"); // 50-character string
        Set<Qualification> qs = createQualificationSetWithRegistration(50, 50);
        Project project = company.createProject(name, qs, ProjectSize.MEDIUM);
        assertNotNull("Project with a 50-char name should be created", project);
    }
    
    // T4: A2, B1, C1, D2, E2
    // Qualification set is null – should fail.
    @Test
    public void createProjectTest04() {
        String name = String.format("%1$30s", "A");
        Set<Qualification> qs = null;  // B1: null object
        Project project = company.createProject(name, qs, ProjectSize.MEDIUM);
        assertNull(project);
    }
    
    // T5: A2, B3, C1, D2, E2
    // Qualification set contains an element not of type Qualification – should fail.
    @Test(expected = ClassCastException.class) // or IllegalArgumentException, depending on implementation details
    public void createProjectTest05() {
        String name = String.format("%1$30s", "A");
        // Create a raw set and add a wrong-type object.
        Set qsRaw = new HashSet();
        qsRaw.add(new Object());  // B3: wrong type
        // Cast unchecked to Set<Qualification>
        @SuppressWarnings("unchecked")
        Set<Qualification> qs = qsRaw;
        company.createProject(name, qs, ProjectSize.MEDIUM);
    }
    
    // T6: A2, B2, C2, D2, E2
    // Name valid; qs: 50 qualifications, but company has only some (e.g. first 25 registered) – should pass.
    @Test
    public void createProjectTest06() {
        String name = String.format("%1$30s", "A");
        Set<Qualification> qs = createQualificationSetWithRegistration(50, 25); // C2: only some qualifications registered
        Project p = company.createProject(name, qs, ProjectSize.MEDIUM);
        assertNull(p);
    }
    
    // T7: A2, B2, C3, D2, E2
    // Name valid; qs: 50 qualifications; company has none registered – should pass.
    @Test
    public void createProjectTest07() {
        String name = String.format("%1$30s", "A");
        Set<Qualification> qs = createQualificationSetWithRegistration(50, 0); // C3: no qualifications registered in company
        Project p = company.createProject(name, qs, ProjectSize.MEDIUM);
        assertNull(p);
    }
    
    // T8: A2, B2, C1, D1, E2
    // Name valid; qs: empty set (D1); company registration is moot – should pass.
    @Test
    public void createProjectTest08() {
        String name = String.format("%1$30s", "A");
        Set<Qualification> qs = new HashSet<>();  // D1: empty set
        // (For an empty set, company qualifications don't matter.)
        Project project = company.createProject(name, qs, ProjectSize.MEDIUM);
        assertNull("Project with empty qualification set should be created", project);
    }
    
    // T9: A2, B2, C1, D3, E2
    // Name valid; qs: 75 qualifications (all registered) – should pass.
    @Test
    public void createProjectTest09() {
        String name = String.format("%1$30s", "A");
        Set<Qualification> qs = createQualificationSetWithRegistration(75, 75); // D3: 75 elements, C1: all registered
        Project project = company.createProject(name, qs, ProjectSize.MEDIUM);
        assertNotNull("Project with 75 qualifications should be created", project);
    }
    
    // T10: A2, B2, C1, D2, E1
    // Name valid; qs: 50 qualifications (all registered); size = SMALL.
    @Test
    public void createProjectTest10() {
        String name = String.format("%1$30s", "A");
        Set<Qualification> qs = createQualificationSetWithRegistration(50, 50);
        Project project = company.createProject(name, qs, ProjectSize.SMALL);
        assertNotNull("Project with size SMALL should be created", project);
    }
    
    // T11: A2, B2, C1, D2, E3
    // Name valid; qs: 50 qualifications (all registered); size = BIG.
    @Test
    public void createProjectTest11() {
        String name = String.format("%1$30s", "A");
        Set<Qualification> qs = createQualificationSetWithRegistration(50, 50);
        Project project = company.createProject(name, qs, ProjectSize.BIG);
        assertNotNull("Project with size BIG should be created", project);
    }



    @Test
    public void getUnavailableWorkersTest01(){
        Company company = new Company("Test Company");
        assertTrue("Expected an empty set of unavailable workers", company.getUnavailableWorkers().isEmpty());
    }

    @Test
    public void getUnavailableWorkersTest02(){
        Company company = new Company("Test Company");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Software Engineer"));

        Worker worker = new Worker("Mark", qualifications, 60000);

        try{
            Field employeesField = Company.class.getDeclaredField("employees");
            employeesField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> employees = (Set<Worker>) employeesField.get(company);
            employees.add(worker);

            Field availableField = Company.class.getDeclaredField("available");
            availableField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> available = (Set<Worker>) availableField.get(company);
            available.remove(worker);
        } catch (Exception e){
            fail("Failed to setup test: " + e.getMessage());
        }

        Set<Worker> unavailableWorkers = company.getUnavailableWorkers();
        assertEquals(1, unavailableWorkers.size());
        assertTrue(unavailableWorkers.contains(worker));
    }

    @Test
    public void getUnavailableWorkersTest03(){
        Company company = new Company("Test Company");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Software Engineer"));

        Worker worker1 = new Worker("Mark", qualifications, 60000);
        Worker worker2 = new Worker("Joe", qualifications, 55000);
        Worker worker3 = new Worker("Bill", qualifications, 50000);

        try {
            Field employeesField = Company.class.getDeclaredField("employees");
            employeesField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> employees = (Set<Worker>) employeesField.get(company);
            employees.add(worker1);
            employees.add(worker2);
            employees.add(worker3);

            Field availableField = Company.class.getDeclaredField("available");
            availableField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> available = (Set<Worker>) availableField.get(company);
            available.remove(worker1);
            available.remove(worker2);
            available.remove(worker3);
        } catch (Exception e){
            fail("Failed to setup test: " + e.getMessage());
        }

        Set<Worker> unavailableWorkers = company.getUnavailableWorkers();
        assertEquals(3, unavailableWorkers.size());
        assertTrue(unavailableWorkers.contains(worker1));
        assertTrue(unavailableWorkers.contains(worker2));
        assertTrue(unavailableWorkers.contains(worker3));
    }

    @Test
    public void getNameTest01() {
        Company company = new Company("Test Company");
        try {
            Field nameField = Company.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(company, "");
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }
        assertEquals("", company.getName());
    }

    @Test
    public void getNameTest02() {
        Company company = new Company("Test Company");
        
        assertEquals("Test Company", company.getName());
    }

    @Test
    public void getNameTest03() {
        Company company = new Company("Test Company");
        try {
            Field nameField = Company.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(company, null);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }

        assertNull(company.getName());
    }



    @Test
    public void getEmployedWorkersTest01(){
        Company company = new Company("Test Company");
        assertTrue("Expecting an empty set of employed workers", company.getEmployedWorkers().isEmpty());
    }

    @Test
    public void getEmployedWorkersTest02(){
        Company company = new Company("Test Company");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Java Developer"));
        
        Worker worker = new Worker("Mark", qualifications, 50000);
        
        try{
            Field employeesField = Company.class.getDeclaredField("employees");
            employeesField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> employees = (Set<Worker>) employeesField.get(company);
            employees.add(worker);
        }catch( Exception e){
            fail("Failed to setup test: " + e.getMessage());
        }

        Set<Worker> employedWorkers = company.getEmployedWorkers();
        assertEquals(1, employedWorkers.size());
        assertTrue(employedWorkers.contains(worker));
    }
    
    @Test
    public void testGetEmployedWorkers_MultipleWorkers() {
        Company company = new Company("Test Company");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Java Developer"));
    
        Worker worker1 = new Worker("Mark", qualifications, 50000);
        Worker worker2 = new Worker("Joe", qualifications, 60000);
        Worker worker3 = new Worker("Bill", qualifications, 55000);
    
        try {
            Field employeesField = Company.class.getDeclaredField("employees");
            employeesField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> employees = (Set<Worker>) employeesField.get(company);
            employees.add(worker1);
            employees.add(worker2);
            employees.add(worker3);
        } catch (Exception e) {
            fail("Failed setting up test: " + e.getMessage());
        }
    
        Set<Worker> employedWorkers = company.getEmployedWorkers();
        assertEquals(3, employedWorkers.size());
        assertTrue(employedWorkers.contains(worker1));
        assertTrue(employedWorkers.contains(worker2));
        assertTrue(employedWorkers.contains(worker3));
    }


        /*getUnassignedWorkers(): Set<Worker>
            /* getUnassignedWorkers(): Set<Worker> */

        // T1: No workers in the company, should return an empty set
        @Test
        public void testGetUnassignedWorkers_EmptyCompany() {
            Set<Worker> unassigned = company.getUnassignedWorkers();
            assertNotNull(unassigned);
            assertEquals("Expected empty unassigned workers set", 0, unassigned.size());
        }
 
        // T2: Workers exist but none are assigned, should return all workers as unassigned
        @Test
        public void testGetUnassignedWorkers_AllUnassigned() {
            Set<Qualification> qualifications = new HashSet<>();
            qualifications.add(new Qualification("Java"));

            Worker worker1 = new Worker("Jokic", qualifications, 50000);
            Worker worker2 = new Worker("Luka", qualifications, 55000);
            company.getEmployedWorkers().add(worker1);
            company.getEmployedWorkers().add(worker2);

            Set<Worker> unassigned = company.getUnassignedWorkers();
            assertNotNull(unassigned);
            assertEquals("Expected 2 unassigned workers", 2, unassigned.size());
            assertTrue(unassigned.contains(worker1));
            assertTrue(unassigned.contains(worker2));
        }
        
        // T3: Some workers are assigned and some are not
        // @Test
        // public void testGetUnassignedWorkers_SomeAssigned() {
        //     Set<Qualification> qualifications = new HashSet<>();
        //     qualifications.add(new Qualification("Python"));

        //     Worker assignedWorker = new Worker("Allen", qualifications, 60000);
        //     Worker unassignedWorker = new Worker("George", qualifications, 70000);

        //     Project project = new Project("AI-Research", qualifications, ProjectSize.MEDIUM);
        //     project.addWorker(assignedWorker);

        //     company.getEmployedWorkers().add(assignedWorker);
        //     company.getEmployedWorkers().add(unassignedWorker);
        //     company.getProjects().add(project);

        //     Set<Worker> unassigned = company.getUnassignedWorkers();
        //     assertNotNull(unassigned);
        //     assertEquals("Expected 1 unassigned worker", 1, unassigned.size()); // I need Modify getAssignedWorkers() to correctly return workers assigned to projects. its currently returning an empty set instead of actual assigned workers -> return assigned;
        //     assertTrue(unassigned.contains(unassignedWorker));
        //     assertFalse(unassigned.contains(assignedWorker));
        // }

        // // T4: All workers are assigned, should return an empty set
        // @Test
        // public void testGetUnassignedWorkers_AllAssigned() {
        //     Set<Qualification> qualifications = new HashSet<>();
        //     qualifications.add(new Qualification("C++"));

        //     Worker worker1 = new Worker("Charlie", qualifications, 60000);
        //     Worker worker2 = new Worker("Dana", qualifications, 70000);

        //     Project project1 = new Project("ProjectA", qualifications, ProjectSize.SMALL);
        //     Project project2 = new Project("ProjectB", qualifications, ProjectSize.BIG);

        //     project1.addWorker(worker1);
        //     project2.addWorker(worker2);

        //     company.getEmployedWorkers().add(worker1);
        //     company.getEmployedWorkers().add(worker2);
        //     company.getProjects().add(project1);
        //     company.getProjects().add(project2);

        //     Set<Worker> unassigned = company.getUnassignedWorkers();
        //     assertNotNull(unassigned);
        //     assertEquals("Expected 0 unassigned workers", 0, unassigned.size()); // I need Modify getAssignedWorkers() to correctly return workers assigned to projects. its currently returning an empty set instead of actual assigned workers -> return assigned;
        // } 
        

    @Test
    public void getProjectTest01(){
        Company company = new Company("Test Company");
        try {
            setProjects(company, 5);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        } 
        assertEquals(5, company.getProjects().size());
    }

    @Test 
    public void getProjectTest02(){
        Company company = new Company("Test Company");
        assertTrue(company.getProjects().isEmpty());
    }

    @Test
    public void getProjectTest03(){
        Company company = new Company("Test Company");
        try {
            setProjects(company, 15);
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        } 
        assertEquals(15, company.getProjects().size());
    }

    private Set<Qualification> generateQualifications(Company c, int numQuals){
        Set<Qualification> qs = new HashSet<>();
        for(int i = 0; i < numQuals; i++) {
            String s = "Qual " + i;
            Qualification q = new Qualification(s);
            qs.add(q);
        }

        return qs;
    }


        // T1: No qualifications in the company, should return an empty set
        @Test
        public void testGetQualifications_NoQualifications() {
            Set<Qualification> qualifications = company.getQualifications();
            assertNotNull(qualifications);
            assertEquals("Expected empty qualifications set", 0, qualifications.size());
        }
    
        // T2: One qualification exists in the company
        @Test
        public void testGetQualifications_OneQualification() {
            Qualification qualification = company.createQualification("Data Science");
    
            Set<Qualification> qualifications = company.getQualifications();
            assertNotNull(qualifications);
            assertEquals("Expected 1 qualification in the set", 1, qualifications.size());
            assertTrue(qualifications.contains(qualification));
        }
    
        // T3: Multiple qualifications exist in the company
        @Test
        public void testGetQualifications_MultipleQualifications() {
            Qualification qualification1 = company.createQualification("Machine Learning");
            Qualification qualification2 = company.createQualification("Cybersecurity");
    
            Set<Qualification> qualifications = company.getQualifications();
            assertNotNull(qualifications);
            assertEquals("Expected 2 qualifications in the set", 2, qualifications.size());
            assertTrue(qualifications.contains(qualification1));
            assertTrue(qualifications.contains(qualification2));
        }

    // T1 
    @Test
    public void createQualificationTest01(){
        Company c = new Company("TestCompany");
        Set<Qualification> qs = generateQualifications(c, 10);
        String qualDesc = "Test Description";
        try {
            Field qualField = Company.class.getDeclaredField("qualifications");
            qualField.setAccessible(true);
            qualField.set(c, qs);

            Qualification outQ = c.createQualification(qualDesc);

            @SuppressWarnings("unchecked")

            Set<Qualification> quals = (Set<Qualification>) qualField.get(c);

            assertEquals(quals.size(), 11);

            Qualification checkQual = new Qualification(qualDesc);
            assertTrue(quals.contains(checkQual));
            assertTrue(quals.contains(outQ));
        } catch (Exception e) {
            // Failed setting up tests
            assertTrue(false);
        }


    }

    // T2: A1: description length = 0; expected to fail (throws exception)
    @Test
    public void createQualificationTest02(){
        Company c = new Company("TestCompany");
        Set<Qualification> qs = generateQualifications(c, 10);
        String qualDesc = "";  // length = 0
        try {
            Field qualField = Company.class.getDeclaredField("qualifications");
            qualField.setAccessible(true);
            qualField.set(c, qs);

            // Expecting an exception due to empty description.
            
            
        } catch (Exception e) {
            fail("Test setup failed: " + e.getMessage());
        }
        Qualification q = c.createQualification(qualDesc);
        assertNull(q);
    }

    // T3: A3: description length > 30 (length = 50); expected to pass
    @Test
    public void createQualificationTest03(){
        Company c = new Company("TestCompany");
        Set<Qualification> qs = generateQualifications(c, 10);
        // Generate a 50-character description.
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 50; i++){
            sb.append("a");
        }
        String qualDesc = sb.toString();
        
        try {
            Field qualField = Company.class.getDeclaredField("qualifications");
            qualField.setAccessible(true);
            qualField.set(c, qs);

            Qualification outQ = c.createQualification(qualDesc);

            @SuppressWarnings("unchecked")
            Set<Qualification> quals = (Set<Qualification>) qualField.get(c);

            assertEquals(quals.size(), 11);

            Qualification checkQual = new Qualification(qualDesc);
            assertTrue(quals.contains(checkQual));
            assertTrue(quals.contains(outQ));
        } catch (Exception e) {
            fail("Test setup failed: " + e.getMessage());
        }
    }

    // T4: B2: description is null; expected to fail (throws exception)
    @Test
    public void createQualificationTest04(){
        Company c = new Company("TestCompany");
        Set<Qualification> qs = generateQualifications(c, 10);
        String qualDesc = null;
        
        try {
            Field qualField = Company.class.getDeclaredField("qualifications");
            qualField.setAccessible(true);
            qualField.set(c, qs);

            
        } catch (Exception e) {
            fail("Test setup failed: " + e.getMessage());
        }
        Qualification q = c.createQualification(qualDesc);
        assertNull(q);
    }

    // T5 (first): C2: qualifications is null; expected to pass (should initialize and add)
    @Test
    public void createQualificationTest05(){
        Company c = new Company("TestCompany");
        // Set qualifications to null.
        String qualDesc = "Test Description";
        try {
            Field qualField = Company.class.getDeclaredField("qualifications");
            qualField.setAccessible(true);
            qualField.set(c, null);

            Qualification outQ = c.createQualification(qualDesc);

            @SuppressWarnings("unchecked")
            Set<Qualification> quals = (Set<Qualification>) qualField.get(c);

            // Since no previous qualifications exist, expect set size 1.
            assertNotNull(quals);
            assertEquals(1, quals.size());

            Qualification checkQual = new Qualification(qualDesc);
            assertTrue(quals.contains(checkQual));
            assertTrue(quals.contains(outQ));
        } catch (Exception e) {
            fail("Test setup failed: " + e.getMessage());
        }
    }

    // T5 (second): D1: qualifications already has the same qualification; expected to pass
    @Test
    public void createQualificationTest06(){
        Company c = new Company("TestCompany");
        Set<Qualification> qs = generateQualifications(c, 10);
        String qualDesc = "Test Description";
        
        // Add a qualification with the same description.
        qs.add(new Qualification(qualDesc));
        
        try {
            Field qualField = Company.class.getDeclaredField("qualifications");
            qualField.setAccessible(true);
            qualField.set(c, qs);

            Qualification outQ = c.createQualification(qualDesc);

            @SuppressWarnings("unchecked")
            Set<Qualification> quals = (Set<Qualification>) qualField.get(c);

            
            assertEquals(11, quals.size());

            Qualification checkQual = new Qualification(qualDesc);
            assertTrue(quals.contains(checkQual));
            assertTrue(quals.contains(outQ));
        } catch (Exception e) {
            fail("Test setup failed: " + e.getMessage());
        }
    }

    // T7: D3/E1: qualifications is empty (size <= 0); expected to pass
    @Test
    public void createQualificationTest07(){
        Company c = new Company("TestCompany");
        Set<Qualification> qs = new HashSet<>();  // empty set
        String qualDesc = "Test Description";
        try {
            Field qualField = Company.class.getDeclaredField("qualifications");
            qualField.setAccessible(true);
            qualField.set(c, qs);

            Qualification outQ = c.createQualification(qualDesc);

            @SuppressWarnings("unchecked")
            Set<Qualification> quals = (Set<Qualification>) qualField.get(c);

            assertEquals(1, quals.size());

            Qualification checkQual = new Qualification(qualDesc);
            assertTrue(quals.contains(checkQual));
            assertTrue(quals.contains(outQ));
        } catch (Exception e) {
            fail("Test setup failed: " + e.getMessage());
        }
    }

    // T8: E3: qualifications size > 20; expected to pass
    @Test
    public void createQualificationTest08(){
        Company c = new Company("TestCompany");
        // Generate a set with 100 qualifications.
        Set<Qualification> qs = generateQualifications(c, 100);
        String qualDesc = "Test Description";
        try {
            Field qualField = Company.class.getDeclaredField("qualifications");
            qualField.setAccessible(true);
            qualField.set(c, qs);

            Qualification outQ = c.createQualification(qualDesc);

            @SuppressWarnings("unchecked")
            Set<Qualification> quals = (Set<Qualification>) qualField.get(c);

            // Expect size to increase by one.
            assertEquals(101, quals.size());

            Qualification checkQual = new Qualification(qualDesc);
            assertTrue(quals.contains(checkQual));
            assertTrue(quals.contains(outQ));
        } catch (Exception e) {
            fail("Test setup failed: " + e.getMessage());
        }
    }


    @Test
    public void getAssignedWorkersTest01(){
        Company company = new Company("Test Company");
        assertTrue("Expecting an empty set of employed workers", company.getAssignedWorkers().isEmpty());
    }

    @Test
    public void getAssignedWorkersTest02(){
        Company company = new Company("Test Company");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Java Developer"));

        Worker worker = new Worker("Mark", qualifications, 50000);

        try{
            Field assignedField = Company.class.getDeclaredField("assigned");
            assignedField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> assigned = (Set<Worker>) assignedField.get(company);
            assigned.add(worker);
        }catch( Exception e){
            fail("Failed to setup test: " + e.getMessage());
        }
        Set<Worker> assignedWorkers = company.getAssignedWorkers();
        assertEquals(1, assignedWorkers.size());
        assertTrue(assignedWorkers.contains(worker));
    }

    @Test
    public void getAssignedWorkersTest03(){
        Company company = new Company("Test Company");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Software Engineer"));

        Worker worker1 = new Worker("Mark", qualifications, 60000);
        Worker worker2 = new Worker("Joe", qualifications, 55000);
        Worker worker3 = new Worker("Bill", qualifications, 50000);

        try {
            Field assignedField = Company.class.getDeclaredField("assigned");
            assignedField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> assigned = (Set<Worker>) assignedField.get(company);
            assigned.add(worker1);
            assigned.add(worker2);
            assigned.add(worker3);
        } catch (Exception e) {
            fail("Failed to setup test: " + e.getMessage());
        }

        Set<Worker> assignedWorkers = company.getAssignedWorkers();
        assertEquals(3, assignedWorkers.size());
        assertTrue(assignedWorkers.contains(worker1));
        assertTrue(assignedWorkers.contains(worker2));
        assertTrue(assignedWorkers.contains(worker3));
    }

     /*
     * start(Project project) tests
     */

    // T1: Base test
    @Test
    public void testStartValidInput() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = new Worker("worker1", qualifications, 1);
        project.addWorker(worker);
        company.start(project);
        assertEquals(project.getStatus(), ProjectStatus.ACTIVE);
    }

    // T2: Test with null object input
    @Test (expected = IllegalArgumentException.class)
    public void testStartNull() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        company.start(null);
    }

    // T3: Test with ProjectStatus.SUSPENDED
    @Test
    public void testStartSuspended() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = new Worker("worker1", qualifications, 1);
        project.addWorker(worker);
        project.setStatus(ProjectStatus.SUSPENDED);
        company.start(project);
        assertEquals(project.getStatus(), ProjectStatus.ACTIVE);
    }

    // T4: Test with ProjectStatus.ACTIVE
    @Test (expected = IllegalArgumentException.class)
    public void testStartActive() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        project.setStatus(ProjectStatus.ACTIVE);
        company.start(project);
    }

    // T5: Test with some missing qualifications
    @Test
    public void testStartMissingSomeQualifications() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = generateQualifications(10);
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        company.start(project);
        assertEquals(project.getStatus(), ProjectStatus.PLANNED);
    }

    // T6: Test with large set of missing qualifications
    @Test 
    public void testStartMissingLargeQualifications() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = generateQualifications(50);
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        company.start(project);
        assertEquals(project.getStatus(), ProjectStatus.PLANNED);
    }

    // T7: Test with ProjectStatus.FINISHED
    @Test (expected = IllegalArgumentException.class)
    public void testStartFinished() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        project.setStatus(ProjectStatus.FINISHED);
        company.start(project);
    }

    //Base test for unassignAll
    @Test
    public void unassignAllTest01(){
        Company company = new Company("TestCompany");

        Qualification qJava = company.createQualification("Java");
        Qualification qPython = company.createQualification("Python");

        Set<Qualification> wQuals = new HashSet<>();
        wQuals.add(qJava);
        wQuals.add(qPython);

        Worker worker1 = company.createWorker("Alice", wQuals, 50000);
        Worker worker2 = company.createWorker("Bob", wQuals, 60000);

        Project projectX = company.createProject("ProjectX", wQuals, ProjectSize.MEDIUM);
        Project projectY = company.createProject("ProjectY", wQuals, ProjectSize.MEDIUM);

        projectX.setStatus(ProjectStatus.ACTIVE);
        projectY.setStatus(ProjectStatus.ACTIVE);

        projectX.addWorker(worker1);
        projectX.addWorker(worker2);
        projectY.addWorker(worker1);
        projectY.addWorker(worker2);

        worker1.addProject(projectX);
        worker1.addProject(projectY);
        worker2.addProject(projectY);
        worker2.addProject(projectX);

        company.getAssignedWorkers().add(worker1);
        company.getAssignedWorkers().add(worker2);

        company.unassignAll(worker1);

        assertFalse(projectX.getWorkers().contains(worker1));
        assertFalse(projectY.getWorkers().contains(worker1));

        assertEquals(ProjectStatus.ACTIVE, projectX.getStatus());
        assertEquals(ProjectStatus.ACTIVE, projectY.getStatus());

        assertFalse(company.getAssignedWorkers().contains(worker1));
        assertTrue(company.getAvailableWorkers().contains(worker1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unassignAllTest02(){
        Company company = new Company("TestCompany");
        company.unassignAll(null);
    }

    @Test
    public void unassignAllTest03(){
        Set<Qualification> wQuals = new HashSet<>();
        Qualification qJava = company.createQualification("Java");
        wQuals.add(qJava);

        Worker worker1 = company.createWorker("Alice", wQuals, 50000);
        
        assertTrue(worker1.getProjects().isEmpty());
        assertFalse(company.getAssignedWorkers().contains(worker1));

        company.unassignAll(worker1);

        assertFalse(company.getAssignedWorkers().contains(worker1));
        assertTrue(company.getAvailableWorkers().contains(worker1));
    }

    @Test
    public void unassignAllTest04(){
        Set<Qualification> wQuals = new HashSet<>();
        Qualification qJava = company.createQualification("Java");
        wQuals.add(qJava);

        Worker worker1 = company.createWorker("Alice", wQuals, 50000);
        Project projectX = company.createProject("ProjectX", wQuals, ProjectSize.MEDIUM);
        Project projectY = company.createProject("ProjectY", wQuals, ProjectSize.MEDIUM);

        projectX.setStatus(ProjectStatus.ACTIVE);
        projectY.setStatus(ProjectStatus.ACTIVE);

        projectX.addWorker(worker1);
        projectY.addWorker(worker1);
        worker1.addProject(projectY);
        worker1.addProject(projectX);

        company.unassignAll(worker1);

        assertEquals(ProjectStatus.SUSPENDED, projectX.getStatus());
        assertEquals(ProjectStatus.SUSPENDED, projectY.getStatus());
        assertFalse(company.getAssignedWorkers().contains(worker1));
    }

    @Test
    public void getAvailableWorkerstest01() {
        Company company = new Company("Test Company");
        assertTrue("Expected an empty set of available workers", company.getAvailableWorkers().isEmpty());
    }

    @Test
    public void getAvailableWorkerstest02() {
        Company company = new Company("Test Company");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Java Developer"));

        Worker worker = new Worker("Mark", qualifications, 50000);

        try {
            Field availableField = Company.class.getDeclaredField("available");
            availableField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> available = (Set<Worker>) availableField.get(company);
            available.add(worker);
        } catch ( Exception e){
            fail("Failed to setup test: " + e.getMessage());
        }

        Set<Worker> availableWorkers = company.getAvailableWorkers();
        assertEquals(1, availableWorkers.size());
        assertTrue(availableWorkers.contains(worker));
    }

    @Test
    public void getAvailableWorkers03(){
        Company company = new Company("Test Company");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Java Developer"));

        Worker worker1 = new Worker("Mark", qualifications, 50000);
        Worker worker2 = new Worker("Joe", qualifications, 60000);
        Worker worker3 = new Worker("Bill", qualifications, 55000);

        try {
            Field availableField = Company.class.getDeclaredField("available");
            availableField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<Worker> available = (Set<Worker>) availableField.get(company);
            available.add(worker1);
            available.add(worker2);
            available.add(worker3);
        } catch (Exception e) {
            fail("Failed to setup test: " + e.getMessage());
        }

        Set<Worker> availableWorkers = company.getAvailableWorkers();
        assertEquals(3, availableWorkers.size());
        assertTrue(availableWorkers.contains(worker1));
        assertTrue(availableWorkers.contains(worker2));
        assertTrue(availableWorkers.contains(worker3));
    }


    @Test
    public void unassignTest01(){
        Company company = new Company("TestCompany");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(company.createQualification("Java Developer"));

        Worker worker1 = company.createWorker("Alice", qualifications, 60000);
        Project projectX = company.createProject("ProjectX", qualifications, ProjectSize.MEDIUM);

        projectX.addWorker(worker1);

        //Once the assign method is up and working I can probably access the assigned workers a different way
        company.getAssignedWorkers().add(worker1);

        projectX.setStatus(ProjectStatus.PLANNED);
        assertEquals(ProjectStatus.PLANNED, projectX.getStatus());

        company.unassign(worker1, projectX);
        assertFalse(projectX.getWorkers().contains(worker1));
        assertFalse(company.getAssignedWorkers().contains(worker1));
        assertTrue(company.getAvailableWorkers().contains(worker1));
    }

    @Test
    public void unassignTest02(){
        Company company = new Company("TestCompany");
        Set<Qualification> qualification1 = new HashSet<>();
        Set<Qualification> qualification2 = new HashSet<>();
        qualification1.add(company.createQualification("Java"));
        qualification2.add(company.createQualification("Python"));

        Worker worker1 = company.createWorker("Alice", qualification1, 60000);
        Worker worker2 = company.createWorker("Bob", qualification2, 50000);
        Project projectX = company.createProject("ProjectX", qualification1, ProjectSize.MEDIUM);
        projectX.addQualification(company.createQualification("Python"));

        projectX.addWorker(worker1);
        projectX.addWorker(worker2);
        //Once the assign method is up and working I can probably access the assigned workers a different way
        company.getAssignedWorkers().add(worker1);
        company.getAssignedWorkers().add(worker2);

        projectX.setStatus(ProjectStatus.ACTIVE);
        assertEquals(ProjectStatus.ACTIVE, projectX.getStatus());

        company.unassign(worker1, projectX);
        assertEquals(ProjectStatus.SUSPENDED, projectX.getStatus());

        assertFalse(projectX.getWorkers().contains(worker1));
        assertFalse(company.getAssignedWorkers().contains(worker1));

    }

    @Test (expected = IllegalArgumentException.class)
    public void unassignTest03(){
        Company company = new Company("TestCompany");
        Set<Qualification> qualification1 = new HashSet<>();
        qualification1.add(company.createQualification("Java"));
        Worker worker1 = company.createWorker("Alice", qualification1, 60000);
        Project projectX = company.createProject("ProjectX", qualification1, ProjectSize.MEDIUM);
        company.unassign(worker1, projectX);
    }

    @Test (expected = IllegalArgumentException.class)
    public void unassignTest04(){
        Company company = new Company("TestCompany");
        Set<Qualification> qualification1 = new HashSet<>();
        qualification1.add(company.createQualification("Java"));
        Project projectX = company.createProject("ProjectX", qualification1, ProjectSize.MEDIUM);
        company.unassign(null, projectX);
    }

    @Test (expected = IllegalArgumentException.class)
    public void unassignTest05(){
        Company company = new Company("TestCompany");
        Set<Qualification> qualification1 = new HashSet<>();
        qualification1.add(company.createQualification("Java"));
        Worker worker1 = company.createWorker("Alice", qualification1, 60000);
        company.unassign(worker1, null);
    }

    //Having issues with this test. Will need to look into it more
    
    // @Test
    // public void unassignTest06(){
    //     Company company = new Company("TestCompany");
    //     Set<Qualification> qualification1 = new HashSet<>();
    //     qualification1.add(company.createQualification("Java"));

    //     Worker worker1 = company.createWorker("Alice", qualification1, 60000);
    //     Project projectX = company.createProject("ProjectX", qualification1, ProjectSize.MEDIUM);
    //     Project projectY = company.createProject("ProjectY", qualification1, ProjectSize.MEDIUM);

    //     projectX.addWorker(worker1);
    //     projectY.addWorker(worker1);
    //     //Once the assign method is up and working I can probably access the assigned workers a different way
    //     company.getAssignedWorkers().add(worker1);

    //     projectX.setStatus(ProjectStatus.ACTIVE);
    //     projectY.setStatus(ProjectStatus.ACTIVE);

    //     company.unassign(worker1, projectX);

    //     assertTrue(company.getAssignedWorkers().contains(worker1));
    //     assertFalse(projectX.getWorkers().contains(worker1));

    // }

    /*
     * assign(Worker worker, Project project) tests
     */

    // T1: Base test
    @Test
    public void testAssignValidInput() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = company.createWorker("worker1", qualifications, 1);
        company.assign(worker, project);
        assertEquals(company.getAssignedWorkers().contains(worker), true);
    }

    // T2: Test wth null worker parameter
    @Test (expected = IllegalArgumentException.class)
    public void testAssignNullWorker() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        company.assign(null, project);
    }

    // T4: Test worker is not available
    @Test (expected = IllegalArgumentException.class)
    public void testAssignNotAvailable() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Project project1 = new Project("project1", qualifications, ProjectSize.BIG);
        Project project2 = new Project("project2", qualifications, ProjectSize.BIG);
        Project project3 = new Project("project3", qualifications, ProjectSize.BIG);
        Project project4 = new Project("project4", qualifications, ProjectSize.BIG);
        Worker worker = company.createWorker("worker1", qualifications, 1);
        worker.addProject(project1);
        worker.addProject(project2);
        worker.addProject(project3);
        worker.addProject(project4);
        company.assign(worker, new Project("testProject", qualifications, ProjectSize.MEDIUM));
    }

    // T5: Test with worker already assigned to project
    @Test (expected = IllegalArgumentException.class)
    public void testAssignWorkerAssigned() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Project project1 = new Project("project1", qualifications, ProjectSize.BIG);
        Worker worker = company.createWorker("worker1", qualifications, 1);
        worker.addProject(project1);
        company.assign(worker, project1);
    }

    // T6: Test with worker will overload
    @Test (expected = IllegalArgumentException.class)
    public void testAssignWorkerOverload() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Project project1 = new Project("project1", qualifications, ProjectSize.BIG);
        Project project2 = new Project("project2", qualifications, ProjectSize.BIG);
        Project project3 = new Project("project3", qualifications, ProjectSize.BIG);
        Project project4 = new Project("project4", qualifications, ProjectSize.MEDIUM);
        Worker worker = company.createWorker("worker1", qualifications, 1);
        worker.addProject(project1);
        worker.addProject(project2);
        worker.addProject(project3);
        worker.addProject(project4);
        company.assign(worker, new Project("testProject", qualifications, ProjectSize.MEDIUM));
    }

    // T7: Test with worker not helpful to project
    @Test (expected = IllegalArgumentException.class)
    public void testAssignNotHelpful() {
        Company company = new Company("company1");
        Set<Qualification> workerQualifications = new HashSet<>();
        Set<Qualification> projectQualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        workerQualifications.add(qualification);
        projectQualifications.add(new Qualification("qualification2"));
        company.createQualification("qualification1");
        Project project = new Project("project", projectQualifications, ProjectSize.MEDIUM);
        Worker worker = company.createWorker("worker1", workerQualifications, 1);
        company.assign(worker, project);
    }

    // T8: Test with null project
    @Test (expected = IllegalArgumentException.class)
    public void testAssignNullProject() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Worker worker = company.createWorker("worker1", qualifications, 1);
        company.assign(worker, null);
    }

    // T10: Test with project status suspended
    @Test
    public void testAssignStatusSuspended() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = company.createWorker("worker1", qualifications, 1);
        project.setStatus(ProjectStatus.SUSPENDED);
        company.assign(worker, project);
    }

    // T11: Test with project status active
    @Test (expected = IllegalArgumentException.class)
    public void testAssignStatusActive() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = company.createWorker("worker1", qualifications, 1);
        project.setStatus(ProjectStatus.ACTIVE);
        company.assign(worker, project);
    }

    // T12: Test with project status finished
    @Test (expected = IllegalArgumentException.class)
    public void testAssignStatusFinished() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = company.createWorker("worker1", qualifications, 1);
        project.setStatus(ProjectStatus.FINISHED);
        company.assign(worker, project);
    }

    // T13: Test with worker not employee of company
    @Test (expected = IllegalArgumentException.class)
    public void testAssignNotEmployee() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qualification = new Qualification("qualification1");
        qualifications.add(qualification);
        company.createQualification("qualification1");
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = new Worker("worker", qualifications,1);
        company.assign(worker, project);
    }

    /*
	 * Helper function to generate large qualification sets
	 */

	 public Set<Qualification> generateQualifications(int n) {
		Set<Qualification> qualifications = new HashSet<>();
		for (int i = 0; i < n; i++) {
            Qualification qualification = new Qualification(String.format("qualification%d", i));
			qualifications.add(qualification);
        }
		return qualifications;
	}

    /*
     * finish(Project project) tests
     */

    // T1: Base test -- Planned project
    @Test
    public void finishPlanned01() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = new Worker("worker1", qualifications, 1);
        project.addWorker(worker);
        company.finish(project);
        assert(project.getStatus() == ProjectStatus.PLANNED);
        assert(project.getWorkers().contains(worker));
    }
 
    // T4: A2, B2 -- Suspended project
    @Test
    public void finishSuspended04() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = new Worker("worker1", qualifications, 1);
        project.addWorker(worker);
        project.setStatus(ProjectStatus.SUSPENDED);
        company.finish(project);
        assert(project.getStatus() == ProjectStatus.SUSPENDED);
        assert(project.getWorkers().contains(worker));
    }
 
    // T5: A2, B3 -- Active project
    @Test
    public void finishActive05() {
        Company company = new Company("company1");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("qualification1"));
        Project project = new Project("project", qualifications, ProjectSize.MEDIUM);
        Worker worker = new Worker("worker1", qualifications, 1);
        project.addWorker(worker);
        project.setStatus(ProjectStatus.ACTIVE);
        company.finish(project);
        assert(project.getStatus() == ProjectStatus.FINISHED);
        assert(project.getWorkers().isEmpty());
        assert(project.getRequiredQualifications().size() - project.getMissingQualifications().size() == 0);
        // instead of adding workers directly... company.assign them once the proper methods are implemented
        // assign and unassign are necessary for this method
    }
 
    // T2: A1, B1 -- Null project
    @Test(expected = IllegalArgumentException.class)
    public void finishNullProject02() {
        Company company = new Company("company1");
        Project project = null;
        company.finish(project);
    }

}
