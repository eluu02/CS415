## Use Case Name: `View Company Qualifications`
* **Summary**: Employee uses application to view the company's current list of qualifications.
* **Actor**: Company employee
* **Precondition**: Employee's computer is on the Home page of the application for their company.
* **Description**:

    1. Employee clicks on the "Qualifications" tab
    2. The Qualifications page loads with a list of dropdowns, each with the name of a qualification on it. There is also text which says "This page displays a table containing all the qualifications".
    3. Employee scrolls with either their input device or by click on the scroll element.
    4. Employee clicks on the "Home" tab
    5. The Home page loads and displays the name of the company.

* **Alternatives**:

    * N/A

* **Postcondition**: Employee's computer is back on the Home page.



## Use Case Name: `View Qualification Details`
* **Summary**: Employee uses application to view the company's current list of qualifications and, more specifically, which workers have the "Java" qualification, for example.
* **Actor**: Company employee
* **Precondition**: Employee's computer is on the Home page of the application for their company, which displays the company.
* **Description**:

    1. Employee clicks on the "Qualifications" tab
    2. The Qualifications page loads with a list of dropdowns, each with the name of a qualification on it. There is also text which says "This page displays a table containing all the qualifications".
    3. Employee clicks the "Java" qualification dropdown.
    4. The Java dropdown expands, displaying the Java qualification's description and the names of employees which possess the qualification in the form of an unordered list.
    5. Employee clicks the part of the dropdown that says "Java"
    6. The Java qualification dropdown then contracts to the state it was in on step 2 of this description.
    7. Employee clicks on the "Home" tab
    8. The Home page loads and displays the name of the company.

* **Alternatives**:

    * If there is no Java qualification, employee instead returns to the home page.
    * If there are no workers which possess the Java qualification, the dropdown will display an empty list.
    * If the employee has a qualification dropdown open and wants to see another qualification, they click on another qualification, which closes the dropdown of the previous qualification and opens the new one.

* **Postcondition**: Employee's computer is back on the Home page.



## Use Case Name: `View Company Projects`
* **Summary**: Employee uses application to view the company's current list of projects.
* **Actor**: Company employee
* **Precondition**: Employee's computer is on the Home page of the application for their company.
* **Description**:

    1. Employee clicks on the "Projects" tab
    2. The Projects page loads with a list of dropdowns, each with the name of a project on it. There is also text which says "This page displays a table containing all the projects".
    3. Employee scrolls with either their input device or by click on the scroll element.
    4. Employee clicks on the "Home" tab
    5. The Home page loads and displays the name of the company.

* **Postcondition**: Employee's computer is back on the Home page.




## Use Case Name: `View Company Employed Worker`
* **Summary**: Employee uses application to view the company’s current list of employed workers.
* **Actor**: Company employee
* **Precondition**: Employee’s computer is on the Home page of the application for their company.
* **Description**:
  
    1. Employee clicks on the **“Workers”** tab.  
    2. The Workers page loads with a list of dropdowns, each labeled with a worker’s name. There is also text which says “All Company Workers.”  
    3. Employee scrolls the list as needed.  
    4. Employee clicks on the **“Home”** tab.  
    5. The Home page loads and displays the company’s main landing content.

* **Alternatives**:
    * If there are no employed workers, the Workers page still loads and displays the “All Company Workers” heading with no dropdowns beneath it.
* **Postcondition**: Employee’s computer is back on the Home page.



## Use Case Name: `View Worker Details`
* **Summary**: Employee uses application to view the details of a worker, "Jamie Burgess" in this example.
* **Actor**: Company employee
* **Precondition**: Employee's computer is on the Workers page of the application for their company which displays a list of workers.
* **Description**:
    1. Employee clicks the "Jamie Burgess" worker dropdown.
    2. The Jamie Burgess dropdown expands, displaying Jamie's name, salary, current workload value, projects they are assigned to, and their qualifications.
    3. Employee clicks the part of the dropdown that says "Jamie Burgess"
    4. The Jamie Burgess worker dropdown then contracts to the state it was in on the precondition of this description.

* **Alternatives**:

    * If the worker has no qualifications, worker dropdown shows no qualifications.
    * If the worker has no assigned projects, worker dropdown shows no assigned projects.

* **Postcondition**: Employee's computer is on the Workers page of the application for their company which displays a list of workers.

## Use Case Name: `Create New Worker`

* **Summary**: Company employee creates a new worker record, supplying name, salary, and one or more qualifications.
* **Actor**: Company employee
* **Precondition**: Employee is on the Workers page and at least one qualification already exists in the system.
* **Description**: 
  1. Employee clicks the “Create Worker” button in the top left corner of the page
  2. A modal form appears containing fields Name, Salary, and a multi‑select Qualifications list
  3. Employee enters a unique name ("Abraham Lincoln"), a positive salary (95000), and selects at least one qualification (JavaScript)
  4. Employee clicks confirm
  5. Front end sends a post request with the body formatted to include the name, salary, and qualification(s)
  6. On Success
    * Modal closes
    * Worker list refreshes and now contains "Abraham Lincoln"
  7. Employee can now click on the new worker to verify the details are correct

* **Alternatives**:
  * **Validation Failure**
    * If any required field is blank, Confirm remains disabled and inline error text appears.
  * **Duplicate Name**
    * No error will be thrown. However, the "new" employee will not be added and the orginal employee will remain. 
  * **Backend error**
    * On unexpected 5xx, modal displays “Unable to create worker. Please try again.”
* **Postcondition**
  * The new worker is persisted, appears in GET /api/workers, and is initially available for assignment.

## Use Case Name: `Assign Worker`

* **Summary**: Company employee uses the application to assign a specific worker to a chosen project.
* **Actor**: Company employee
* **Precondition**:

  * Employee is signed in and on the **Projects** page.
  * The list of projects is visible, and at least one project exists.
* **Description**:

  1. Employee clicks on the **“Projects”** tab.
  2. The Projects page loads, showing a list of dropdowns labeled with each project’s name.
  3. Employee clicks the on the title of the desired project (“Project Alpha”).
  4. The **Project Alpha** section expands to reveal details: project description, current status, assigned employees list, required qualifications, and an **“Assign Worker”** button.
  5. Employee clicks **“Assign Worker”**.
  6. The system displays a modal (or inline form) containing:

     * A dropdown of **Available Workers** (those not already assigned).
     * A **Confirm** button and a **Cancel** button.
  7. Employee selects the desired worker (“Jane Doe”) from the Available Workers dropdown.
  8. Employee clicks **Confirm**.
  9. The system sends a request to the backend to associate “Jane Doe” with **Project Alpha**.
  10. On success:

      1. The modal closes.
      2. The **Assigned Employees** list updates to include “Jane Doe.”
      3. The **Missing Qualifications** section re-evaluates and color-codes any newly unmet requirements.
      4. A brief success message appears (“Worker assigned successfully”).
  11. Employee may continue assigning additional workers or navigate elsewhere.
* **Alternatives**:

  * **No Available Workers**:

    * If the Available Workers dropdown is empty, the **Assign Worker** button is disabled and a tooltip or inline message reads “No unassigned workers available.”
  * **Cancel Assignment**:

    * If the employee clicks **Cancel** in the modal, the modal closes and no changes are made.
  * **Assignment Failure**:

    * If the backend request fails (network error), the modal displays an error message (“Unable to assign worker. Please try again.”) with options to **Retry** or **Cancel**.
* **Postcondition**:

  * On success: the selected worker is listed under the project’s Assigned Employees.
  * Missing qualifications and workload metrics update accordingly.






## Use Case Name: `Unassign Worker`

* **Summary**: Employee uses the application to remove (unassign) a worker from a specific project.

* **Actor**: Company employee

* **Precondition**: Employee’s computer is on the **Project Details** page for the project from which they intend to unassign a worker.

* **Description**:

  1. Employee clicks on the **“Projects”** tab.
  2. The **Projects** page loads, showing a list of dropdowns, each labeled with a project’s name.
  3. Employee clicks the on the title of the desired project (“Project Alpha”).
  4. The **Project Details** view opens, displaying project name, size, status, assigned employees, required qualifications, etc.
  5. Employee locates the **Assigned Employees** section and expands it (if collapsed).
  6. Next to the name of the worker to be removed, Employee clicks the **“Unassign”** button (or trash‐can icon).
  7. A confirmation dialog appears:

     > **“Are you sure you want to unassign \[Worker Name] from project \[Project Name]?”**
  8. Employee clicks **“Confirm”**.
  9. The **Assigned Employees** list refreshes, no longer showing the unassigned worker.

* **Alternatives**:

  * If Employee clicks **“Cancel”** in the confirmation dialog, the dialog closes and no changes are made.
  * If the unassign request fails (e.g., network/server error), the system displays an error message (“Unable to unassign worker. Please try again.”) and the worker remains in the list.

* **Postcondition**: The selected worker is no longer listed under **Assigned Employees** for the project, and the **Project Details** page reflects the updated assignment list.





## Use Case Name: `Create New Qualification`

* **Summary**: Employee uses the application to add a new qualification to the company’s list.
* **Actor**: Company employee
* **Precondition**: The employee is on the Qualifications page (reachable via the “Qualifications” tab) and sees the current list of qualifications.

* **Description**:
    1. Employee clicks on the “Qualifications” tab.  
    2. The Qualifications page displays a list of existing qualifications and an input form labeled “Enter new qualification” with an “Add Qualification” button below it.  
    3. Employee clicks into the “Enter new qualification” text field.  
    4. Employee types in a new qualification name, e.g. “AWS.”  
    5. Employee clicks the “Add Qualification” button (or presses Enter).  
    6. The system sends a POST to `/api/qualifications/AWS`, creates the new qualification, refetches the list, and re-renders the list in alphabetical order (now including “AWS”).  
    7. Employee clicks on the “Home” tab.  
    8. The Home page loads and displays the company name.

* **Alternatives**:
    - **Invalid input**: If the employee submits an empty or whitespace-only name, the “Add Qualification” button is disabled (or the system shows an inline error).  
    - **Duplicate**: If the qualification already exists, the server returns an error and the page displays a message like “Qualification already exists.”  
    - **Server error**: If the POST fails (network/server error), the page shows “Failed to create qualification” and the input remains populated for retry.

* **Postcondition**: The new qualification appears in the list (if creation succeeded) and the employee can navigate away (e.g., back to Home).  


## Use Case Name: `Start Project`
* **Summary**: Employee uses the application to transition a project from PLANNED or SUSPENDED to ACTIVE once all its workers and their specfic qualification requirements are met.
* **Actor**: Company employee
* **Precondition**: 
  - Employee’s computer is on the Projects page, which lists all projects with their current statuses.
  - The selected project exists in PLANNED or SUSPENDED state.
  - All its required qualifications are already covered by its assigned workers.


* **Description**:
    1. Employee clicks on a project dropdown to expand its details.
    2. They click the **“Start Project”** when status is PLANNED or SUSPENDED.
    3. System checks:
       - Project is not null.
       - Status is neither ACTIVE nor FINISHED.
       - `getMissingQualifications()` returns an empty set.
    4. If checks pass, system sets project status to **ACTIVE**.
    5. The project’s detail view updates to show **Status: ACTIVE**.

* **Alternatives**:
    - If the project is already ACTIVE or FINISHED, system throws an error and displays “Only PLANNED or SUSPENDED projects can be started.”
    - If there are still missing qualifications, system does nothing (remains in PLANNED/SUSPENDED) and could optionally display “Cannot start: missing qualifications.”
* **Postcondition**: The project’s status is ACTIVE and is reflected immediately in the UI.

## Use Case Name: `Finish Project`

* **Summary**: Company employee marks an active project as finished, automatically releasing all assigned workers.

* **Actor**: Company employee

* **Precondition**:

  * Employee is on the **Projects** page.
  * The target project is currently in **ACTIVE** status.

* **Description**:

  1. Employee clicks on the **“Projects”** tab.
  2. The Projects page loads, showing a list of dropdowns labeled with each project’s name.
  3. Employee clicks on the title of the desired project (e.g., “Project Zeta”).
  4. The **Project Zeta** section expands to reveal details: project description, status, assigned workers, and the **“Finish Project”** button.
  5. The **Finish Project** button is enabled (since status is **ACTIVE**).
  6. Employee clicks **Finish Project**.
  7. The front-end sends a `PUT` request to `/api/finish` with body:  
     `{ "name": "Project Zeta" }`
  8. On success (HTTP 200):

     1. The project’s status updates to **FINISHED**.
     2. The list of assigned workers is cleared (becomes empty).
     3. The workload for each previously assigned worker is recalculated and updated accordingly.
     4. A toast message appears: “Project finished.”
  9. Employee may collapse the project dropdown or navigate to another page.

* **Alternatives**:

  * **Project Not ACTIVE**:
    * Backend returns HTTP 400.


## Use Case Name:  `Create new project`
* **Summary**: Employee uses the application to create a new project from the projects page.

* **Actor**: Company employee

* **Precondition**: Employee’s computer is on the **Projects** page.
* **Description**:

  1. Employee clicks on the **“Projects”** tab.
  2. The **Projects** page loads, showing a list of dropdowns, each labeled with a project’s name if they exist.
  3. Employee clicks on the **Create New Project** button at the top of the page
  4. A **Create New Project** menu opens prompting user to input a project name, qualifications, and size.
  5. Employee clicks in the **Project Name** text box and enters a name
  6. Employee clicks on the Qualifications dropdown menu
  7. Dropdown menu displays with all unselected company qualifications
  8. Employee clicks on desired qualification
  9. Qualifications box displays all selected qualifications
  10. Employee clicks on the **Project Size** dropdown menu displaying **"Select size"**
  11. Dropdown menu displays with project sizes
  12. Employee clicks on desired size
  13. Employee clicks on the **Create** button
  14. Created project is displayed under the section **"This page displays a table containing all the projects."**

* **Alternatives**:

  * If Employee clicks **“Cancel”** in the create new project menu, the dialog closes and no changes are made.
  * If any name field or project size field is empty/null when clicking **Create**, then page displays error prompting user to complete field
  * If qualifications field is empty, then system throws an Axios/Network error for missing required fields(IllegalArgumentException in server)

* **Postcondition**: The project created is displayed under the section **"This page displays a table containing all the projects."** and clicking on project displays project information as well as buttons for assigning/unassigning workers



## Use Case Name: `View Project Details`

* **Summary**: Employee uses the application to view detailed information for a specific project.

* **Actor**: Company employee

* **Precondition**: Employee’s computer is on the Home page of the application for their company.

* **Description**:

  1. Employee clicks on the **“Projects”** tab.
  2. The Projects page loads with a list of dropdowns, each labeled with a project's name and a heading “This page displays a table containing all the projects.”
  3. Employee scrolls the list as needed and locates the dropdown for the desired project (**“Project Alpha”**).
  4. Employee clicks the project dropdown header (**“Project Alpha”**).
  5. The **Project Alpha** dropdown expands, displaying:

     * **Name**: Project Alpha
     * **Size**:  Large
     * **Status**:  In Progress
     * **Workers**: an unordered list of worker names
     * **Qualifications**: an unordered list of satisfied qualifications.
     * **Missing Qualifications**: any qualifications not met.
  6. Employee reviews the project details.
  7. Employee clicks the project dropdown header (**“Project Alpha”**) again to collapse the details.
  8. Employee clicks on the **“Home”** tab.
  9. The Home page loads and displays the company’s main landing content.

* **Alternatives**:

  * If there is no such project in the list, the Projects page shows no matching dropdown; the employee may instead click **“Home”**.
  * If the project has no assigned employees, the **Assigned Employees** section displays an empty list.
  * If the project has no required qualifications, the **Required Qualifications** section displays “None” and no **Missing Qualifications** appear.
  * If all required qualifications are satisfied by the assigned employees, the **Qualifications** list appears with all items and no items are shown in the **Missing Qualifications** section.

* **Postcondition**: Employee’s computer is back on the Home page.
