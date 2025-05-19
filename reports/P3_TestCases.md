# Individual Use Cases

## Use Case Name: `View Company Qualifications`
* **Scenario 1**: Employee uses application to view the company's current list of qualifications, which are derived from the default DBConnector mock database.
    1. **Precondition**: Employee's computer is on the Home page of the application for the company, which displays the title "Company Management", followed by CS415 - Colorado State University Spring 2025.
    2. Employee clicks on the "Qualifications" tab
    3. The Qualifications page loads with a list of dropdowns, each with the name of a qualification on it. The qualifications displayed are as follows: Angular, Cyber Security, Java, JavaScript, MongoDB, Python, React, Spark, Spring, Sql, Tensorflow, TypeScript. There is also text which says "This page displays a table containing all the qualifications".
    4. Employee scrolls with either their input device or by click on the scroll element.
    5. Employee clicks on the "Home" tab.
    6. The Home page loads and displays the same info as in the precondition.

* **Scenario 2**: Employee uses application to view the company's current list of qualifications. In this scenario, there is only one qualification called "Python".
    1. **Precondition**: Employee's computer is on the Home page of the application for the company, which displays the title "Company Management", followed by CS415 - Colorado State University Spring 2025.
    2. Employee clicks on the "Qualifications" tab
    3. The Qualifications page loads with one dropdown, which says "Python" on it. There is also text which says "This page displays a table containing all the qualifications".
    4. Employee clicks on the "Home" tab.
    5. The Home page loads and displays the same info as in the precondition.

* **Scenario 3**: Employee uses application to view the company's current list of qualifications. In this scenario, there are no qualifications yet.
    1. **Precondition**: Employee's computer is on the Home page of the application for the company, which displays the title "Company Management", followed by CS415 - Colorado State University Spring 2025.
    2. Employee clicks on the "Qualifications" tab
    3. The Qualifications page loads with no dropdowns, only text that says "This page displays a table containing all the qualifications".
    4. Employee clicks on the "Home" tab.
    5. The Home page loads and displays the same info as in the precondition.



## Use Case Name: `View Company Qualification Details`
* **Scenario 1**: Employee uses application to view the details of the Java qualification. In this scenario, the company is using the default DBConnector mock database.
    1. **Precondition**: Browser is already on the company's Qualification screen with no details opened.
    2. Employee clicks on the "Java" qualification dropdown.
    3. The Java qualification dropdown expands and shows the names of workers (Terry Hampton, Tim Conner, Benjamin Guzman, Ron Logan, and Robert Lambert) who possess the qualification.
    4. Employee clicks on the Java qualification dropdown again.
    5. The dropdown retracts and leaves the screen in the same state is was in the precondition.

* **Scenario 2**: Employee uses application to view the details of the Java qualification. In this scenario, there are no workers who possess the Java qualification.
    1. **Precondition**: Browser is already on the company's Qualification screen with no details opened.
    2. Employee clicks on the "Java" qualification dropdown.
    3. The Java qualification dropdown expands and displays a "Workers:" section with no workers listed.
    4. Employee clicks on the Java qualification dropdown again.
    5. The dropdown retracts and leaves the screen in the same state is was in the precondition.

* **Scenario 3**: Employee uses application to view the details of the Java qualification, then the MongoDB qualification. In this scenario, the company is using the default DBConnector mock database.
    1. **Precondition**: Browser is already on the company's Qualification screen with no details opened.
    2. Employee clicks on the "Java" qualification dropdown.
    3. The Java qualification dropdown expands and shows the names of workers (Terry Hampton, Tim Conner, Benjamin Guzman, Ron Logan, and Robert Lambert) who possess the qualification.
    4. Employee clicks on the "MongoDB" qualification dropdown.
    5. The Java qualification closes and the MongoDB qualification dropdown expands and shows the name of the worker, Erika Johnston, who possesses the qualification.
    4. Employee clicks on the MongoDB qualification dropdown again.
    5. The dropdown retracts and leaves the screen in the same state is was in the precondition.

## Use Case Name: `View Company Projects`
* **Scenario 1**: Employee uses application to view the company's current list of projects, which are derived from the default DBConnector mock database.
    1. **Precondition**: Employee's computer is on the Home page of the application for the company, which displays the title "Company Management", followed by CS415 - Colorado State University Spring 2025.
    2. Employee clicks on the "Projects tab"
    3. The Projects page loads with a list of dropdowns, each with the name of a project on it. The projects displayed are as follows: aIShoppingSystem, androidTaskMonitoring, fingerprintBasedATMSystem, creditCardFraudDetection, weatherForecastingSystem, smartChatbot, financialBankingSystem, faceDetector, signatureVerificationSystem, lagacySoftwareMaintanance, ecommerceFakeProductReviewDetectionSystem. There is also text which says "This page displays a table containing all the projects".
    4. Employee scrolls with either their input device or by click on the scroll element.
    5. Employee clicks on the "Home" tab.
    6. The Home page loads and displays the same info as in the precondition.

* **Scenario 2**: Employee uses application to view the company's current list of projects. In this scenario, there is only one qualification called "financialBankingSystem".
    1. **Precondition**: Employee's computer is on the Home page of the application for the company, which displays the title "Company Management", followed by CS415 - Colorado State University Spring 2025.
    2. Employee clicks on the "Projects" tab
    3. The Projects page loads with one dropdown, which says "financialBankingSystem" on it. There is also text which says "This page displays a table containing all the projects".
    4. Employee clicks on the "Home" tab.
    5. The Home page loads and displays the same info as in the precondition.

* **Scenario 3**: Employee uses application to view the company's current list of projects. In this scenario, there are no projects yet.
    1. **Precondition**: Employee's computer is on the Home page of the application for the company, which displays the title "Company Management", followed by CS415 - Colorado State University Spring 2025.
    2. Employee clicks on the "Projects" tab
    3. The Projects page loads with no dropdowns, only text that says "This page displays a table containing all the projects".
    4. Employee clicks on the "Home" tab.
    5. The Home page loads and displays the same info as in the precondition.


# Workflows
* Example of a workflow, I think --> Use Case 1: View Company Qualifications, followed by Use Case 7: Create New Qualifications, followed by Use Case 4: View Qualification Details. Some combination of these use cases which describes a workflows someone might reasonably use in this software.



# Individual Use Cases

## Use Case Name: `View Company Employed Worker`

* **Scenario 1**: Multiple workers exist (default mock data).
    1. **Precondition**: Home page shows “Company Management” header.
    2. Employee clicks **Workers** tab.
    3. Workers page loads. The following worker names appear in random order as dropdowns:
       - Benjamin Guzman  
       - Erika Johnston  
       - Gene Robertson  
       - Jamie Burgess  
       - Marcus Schneider  
       - Nick Hubbard  
       - Nina Banks  
       - Omar Williamson  
       - Robert Lambert  
       - Ron Logan  
       - Terry Hampton  
       - Tim Conner  
    4. Employee scrolls to verify the full list.
    5. Employee clicks **Home** tab.
    6. Home page reloads with original content.

* **Scenario 2**: Exactly one worker exists.
    1. **Precondition**: Company has only “Alice Smith” as an employee.
    2. Employee clicks **Workers** tab.
    3. Workers page loads with a single dropdown labeled **Alice Smith**.
    4. Employee clicks **Home** tab.
    5. Home page reloads.

* **Scenario 3**: No workers exist.
    1. **Precondition**: Company has zero employed workers.
    2. Employee clicks **Workers** tab.
    3. Workers page loads showing the heading “All Company Workers” but no dropdowns.
    4. Employee clicks **Home** tab.
    5. Home page reloads.

# Workflows

* **Workflow A**: View & inspect one worker  
  1. Home → **Workers** (list appears)  
  2. Employee clicks dropdown “Jamie Burgess” → details expand  
  3. Employee reviews Salary, Workload, Projects, Qualifications  
  4. Employee clicks “Jamie Burgess” again → details collapse  
  5. Employee clicks **Home**

* **Workflow B**: Qualification lookup from worker list  
  1. Home → **Workers** → click “Omar Williamson” → expand  
  2. In expanded view, click one of Omar’s qualifications → navigates to that qualification’s details  
  3. Review qualification details → click **Qualifications** tab to return to full list  
  4. Click **Home**


## Use Case Name: `View Worker Details`
* **Scenario 1**: Employee uses application to view the details of a worker, "Jamie Burgess". The company in this scenario uses the defualt DBConnector database.
    1. **Precondition**: Employee's computer is on the Workers page of the application for their company which displays a list of workers.
    2. Employee clicks the "Jamie Burgess" worker dropdown.
    2. The Jamie Burgess dropdown expands, displaying Jamie's name, salary, current workload value, projects they are assigned to, and their qualifications.
    3. Employee clicks the part of the dropdown that says "Jamie Burgess"
    4. The Jamie Burgess worker dropdown then contracts to the state it was in on the precondition of this description.

* **Scenario 2**: Employee uses application to view the details of a worker, "Jamie Burgess", who has no qualifications. The company in this scenario uses the defualt DBConnector database, with the exception that worker Jamie Burgess has no qualifications.
    1. **Precondition**: Employee's computer is on the Workers page of the application for their company which displays a list of workers.
    2. Employee clicks the "Jamie Burgess" worker dropdown.
    2. The Jamie Burgess dropdown expands, displaying their salary of $150000, current workload value of 5, and the projects "Credit Card Fraud Detection" and "Fingerprint-Based ATM Systems" they are assigned to. No qualifications are listed.
    3. Employee clicks the part of the dropdown that says "Jamie Burgess"
    4. The Jamie Burgess worker dropdown then contracts to the state it was in on the precondition of this description.

* **Scenario 3**: Employee uses application to view the details of a worker, "Jamie Burgess", who has no assigned projects. The company in this scenario uses the defualt DBConnector database, with the exception that worker Jamie Burgess has no assigned projects.
    1. **Precondition**: Employee's computer is on the Workers page of the application for their company which displays a list of workers.
    2. Employee clicks the "Jamie Burgess" worker dropdown.
    2. The Jamie Burgess dropdown expands, displaying their salary of $150000, current workload value of 5, and their qualifications "Tensorflow", "Javascript", and "Python". No assigned projects are listed.
    3. Employee clicks the part of the dropdown that says "Jamie Burgess"
    4. The Jamie Burgess worker dropdown then contracts to the state it was in on the precondition of this description.



## Use Case Name: `Create new qualification.`
* **Scenario 1**: 
    1. **Test Precondition**: Navigate to Qualifications page.
    2. **Test Input**: Enter "AWS" in the description field.
    3. **Action**: Click Create.
    4. **Expected Result**: POST request to `/qualifications/AWS`; response 200 OK; "AWS" appears in list sorted alphabetically.

* **Scenario 2**:
    1. **Test Precondition**: Navigate to Qualifications page.
    2. **Test Input**: Leave description blank.
    3. **Action**: Click Create.
    4. **Expected Result**: Client-side error shown; no POST request; list unchanged.


## Use Case Name: `Create New Worker`

* **Scenario 1**: Successful creation (multiple qualifications exist).  
    1. **Precondition**: Home page shows “Company Management” header; *Workers* page lists existing employees; at least one qualification (e.g., **JavaScript**) exists.  
    2. Employee clicks **Workers** tab.  
    3. Workers page loads and shows existing worker dropdowns.  
    4. Employee clicks **Create Worker** button (top‑right).  
    5. “Create New Worker” modal appears with fields **Name**, **Salary**, and multi‑select **Qualifications**.  
    6. Employee types **Alex Quinn**, enters **95 000** for salary, and selects **JavaScript**.  
    7. Employee clicks **Confirm**.  
    8. Modal closes; Workers list refreshes and now contains a dropdown labeled **Alex Quinn**.  
    9. Toast “Worker created successfully.” appears.  
   10. Employee clicks **Home** tab.  
   11. Home page reloads.

* **Scenario 2**: Validation failure (missing required data).  
    1. **Precondition**: Employee is viewing the Create Worker modal (steps 4–5 of Scenario 1).  
    2. Employee leaves **Name** blank but fills salary and qualifications.  
    3. **Confirm** button is disabled; inline error “Name, salary, and at least one qualification are required.” appears.  
    4. Employee fills **Name** but removes all qualifications.  
    5. Error persists and **Confirm** remains disabled.  
    6. Employee clicks **Cancel**, modal closes; Workers page unchanged.


* **Scenario 3**: Unexpected backend error (5xx).  
    1. **Precondition**: Network or server issue causes backend to return *HTTP 500*.  
    2. Employee fills the modal correctly and clicks **Confirm**.  
    3. Modal remains open; banner “Unable to create worker. Please try again.” appears.  
    4. Employee clicks **Confirm** again after issue resolves → backend 200, modal closes, new worker appears.

# Workflows

* **Workflow C**: Hire and verify  
  1. Home → **Workers** → **Create Worker** (complete Scenario 1).  
  2. Expand **Alex Quinn** dropdown → confirm Salary, Workload = 0, Qualifications list shows **JavaScript**.  
  3. Collapse dropdown → click **Home**.


## Use Case Name: `Assign Worker`

* **Scenario 1**: Successfully assign an available worker to a project.

  1. **Precondition**: Employee’s computer is on the Home page showing the heading “Company Management,” with at least one project and at least one unassigned worker in the mock database.
  2. Employee clicks the **“Projects”** tab.
  3. The Projects page loads with a list of project dropdowns (“Project Alpha,” “Project Beta”).
  4. Employee clicks the dropdown for **Project Alpha**.
  5. The **Project Alpha** section expands, showing details and an enabled **“Assign Worker”** button.
  6. Employee clicks **“Assign Worker”**.
  7. The Assign Worker modal appears, containing a dropdown of available workers (“Jane Doe,” “John Smith”), plus **Confirm** and **Cancel** buttons.
  8. Employee selects **Jane Doe** from the dropdown.
  9. Employee clicks **Confirm**.
  10. The system sends the assignment request, closes the modal on success, updates the Assigned Employees list to include **Jane Doe**, re-evaluates and color-codes missing qualifications, and displays a success toast: “Worker assigned successfully.”
  11. Employee clicks the **“Home”** tab.
  12. The Home page reloads, displaying the original content.

* **Scenario 2**: Attempt to assign when no unassigned workers exist.

  1. **Precondition**: Employee is on the Projects page; all workers are already assigned—no unassigned workers remain.
  2. Employee clicks the dropdown for any project (steps 1–4 as in Scenario 1).
  3. Employee clicks **“Assign Worker”**.
  4. The Assign Worker modal appears; the worker dropdown is empty and the **Confirm** button is disabled.
  5. Employee reads the inline message “No unassigned workers available.”
  6. Employee clicks **Cancel**.
  7. The modal closes; the Assigned Employees list remains unchanged.

* **Scenario 3**: Backend failure during assignment.

  1. **Precondition**: Employee is on the Projects page with at least one unassigned worker available.
  2. Employee expands a project and opens the Assign Worker modal (steps 1–7 as in Scenario 1).
  3. Employee selects a worker and clicks **Confirm**.
  4. The backend request fails (HTTP 500).
  5. The modal remains open and displays an error message: “Unable to assign worker. Please try again.”
  6. Employee clicks **Retry**.
  7. On retry success, the modal closes, the Assigned Employees list updates accordingly, and a success message appears.
  8. Employee clicks **Home** to return to the Home page.



# Individual Use Cases

## Use Case Name: `Unassign Worker`

* **Scenario 1**: Employee successfully unassigns a worker from a project.

  1. **Precondition**: Employee’s computer is on the **Project Details** page for project “Apollo Mission”, which displays an **Assigned Employees** list containing **Alice Smith**.
  2. Employee clicks the **Unassign** button next to **Alice Smith**.
  3. Employee clicks **Confirm**.
  4. System sends the unassign request, returns a **200 OK** response.
  5. The **Assigned Employees** list refreshes and no longer shows **Alice Smith**.


* **Scenario 2**: Employee cancels the unassignment.

  1. **Precondition**: Same as Scenario 1.
  2. Employee clicks the **Unassign** button next to **Alice Smith**.
  3. Confirmation dialog appears asking for confirmation.
  4. Employee clicks **Cancel**.
  5. The confirmation dialog closes with no action taken.
  6. The **Assigned Employees** list remains unchanged, still showing **Alice Smith**.

* **Scenario 3**: Unassignment fails due to server error.

  1. **Precondition**: Same as Scenario 1.
  2. Employee clicks the **Unassign** button next to **Alice Smith**.
  3. Confirmation dialog appears.
  4. Employee clicks **Confirm**.
  5. System sends the unassign request but returns a **500 Internal Server Error**.
  6. The **Assigned Employees** list remains unchanged, still showing **Alice Smith**.

## Use Case Name: `Start Project`

* **Scenario 1 – valid start**

    1. **Precondition**: Project “Tablet” is in PLANNED state, assigned workers cover all required qualifications.
    2. Employee expands “Tablet” and clicks **Start Project**.
    3. System transitions Tablet status to ACTIVE.
    4. UI reflects **Status: ACTIVE**.

* **Scenario 2 – Missing Qualifications**

    1. **Precondition**: Project PS5 is PLANNED but still has missing qualifications.
    2. Employee expands “PS5” and clicks **Start Project**.
    3. System rejects action (no status change).
    4. UI optionally displays “Cannot start: missing qualifications.”
---

# Workflows

* **Workflow**: View Projects → Start a PLANNED Project → View that Project’s details showing ACTIVE.

## Use Case Name: `Finish Project`

* **Scenario 1**: Successfully finish an active project.
    1. **Precondition**: Employee is on the Projects page; "Smart Chatbot" is listed and has status **ACTIVE**.
    2. Employee expands the **Smart Chatbot** dropdown.
    3. Employee clicks **Finish Project**.
    4. Front-end sends a `PUT` request to `/api/finish` with body `{ "name": "Smart Chatbot" }`.
    5. Backend responds with HTTP 200.
    6. Status updates to **FINISHED**.
    7. Worker list becomes empty.
    8. Toast message appears: “Project finished.”

* **Scenario 2**: Attempt to finish a non-active project.
    1. **Precondition**: Employee is on the Projects page; "Android Task Monitoring" has status **PLANNED**.
    2. Employee expands the **Android Task Monitoring** dropdown.
    3. **Finish Project** button is disabled or, if clicked, the backend returns HTTP 400.
    4. Error banner appears: “Only active projects can be finished.”

* **Scenario 3**: Finish request for a nonexistent project.
    1. **Precondition**: Employee triggers a finish request manually (e.g., with dev tools) for a project that does not exist.
    2. Front-end sends a `PUT` request with a bogus project name.
    3. Backend returns HTTP 404.
    4. Toast message appears: “Project does not exist.”

* **Scenario 4**: Backend error when finishing a project.
    1. **Precondition**: Employee attempts to finish an active project, but the backend returns HTTP 500.
    2. **Finish Project** button becomes re-enabled.
    3. Error banner appears: “Unexpected error, please retry.”

# Workflows

* **Workflow C**: View and finish an active project  
  1. Home → **Projects** tab  
  2. Employee expands the **Smart Chatbot** dropdown (status = ACTIVE)  
  3. Employee clicks **Finish Project**  
  4. Backend returns HTTP 200 → status updates to **FINISHED**, worker list becomes empty  
  5. Toast message appears: “Project finished”  
  6. Employee clicks **Home**


## Use Case Name: `Create new project`
* **Scenario 1**: Employee successfully creates project
  1. **Precondition**: Employee is on the **Projects** page
  2. Employee clicks on the **Create New Project** button at the top of the page and create project menu displays
  3. Employee clicks in the **Project Name** field and enters "Company website"
  4. Employee clicks on the **Select Qualifications** dropdown menu and list of qualifications displays
  5. Employee clicks on "Java" and repeats step 4 to select "Python" and "Sql"
  6. System displays selected qualifications in the **Select Qualifications** field
  7. Employee clicks on the **Select Size** dropdown menu to select a project size
  8. Employee clicks on the **Create** button and project is successfully created and shown under company projects

* **Scenario 2**: Missing name or project size field
  1. **Precondition**: Employee is on the **Projects** page
  2. Employee clicks on the **Create New Project** button at the top of the page and create project menu displays
  3. Employee clicks on the **Select Qualifications** dropdown menu and list of qualifications displays
  4. Employee clicks on "Java"
  5. Employee clicks on the **Create** button with an empty project name and project size field
  6. System prompts user to **"Please fill out this field."

* **Scenario 3**: Missing qualifications field
  1. **Precondition**: Employee is on the **Projects** page
  2. Employee clicks on the **Create New Project** button at the top of the page and create project menu displays
  3. Employee clicks in the **Project Name** field and enters "Company website"
  4. Employee clicks on the **Select Size** dropdown menu to select a project size
  8. Employee clicks on the **Create** button with an empty qualifications field
  9. System throws an axios/network error for missing required fields. 

* **Scenario 4**: Employee cancels project creation
  1. **Precondition**: Employee is on the **Projects** page
  2. Employee clicks on the **Create New Project** button at the top of the page and create project menu displays
  3. Employee clicks in the **Project Name** field and enters "Company website"
  4. Employee clicks on the **Select Qualifications** dropdown menu and list of qualifications displays
  5. Employee clicks on "Java" and repeats step 4 to select "Python" and "Sql"
  6. System displays selected qualifications in the **Select Qualifications** field
  7. Employee clicks on the **Select Size** dropdown menu to select a project size
  8. Employee clicks on the **Cancel** button and no changes are made.


# Individual Use Cases

## Use Case Name: `View Project Details`

* **Scenario 1**: Multiple projects exist and the selected project has both satisfied and missing qualifications.

  1. **Precondition**: Employee’s computer is on the Home page of the application for the company, which displays the title “Company Management”, followed by CS415 – Colorado State University Spring 2025.
  2. Employee clicks on the **“Projects”** tab.
  3. The Projects page loads with a list of dropdowns, each labeled with a project’s name. The projects displayed are:

     * aIShoppingSystem
     * androidTaskMonitoring
     * fingerprintBasedATMSystem
     * creditCardFraudDetection
     * weatherForecastingSystem
     * smartChatbot
     * financialBankingSystem
     * faceDetector
     * signatureVerificationSystem
     * lagacySoftwareMaintanance
     * ecommerceFakeProductReviewDetectionSystem
       There is also text which says “This page displays a table containing all the projects.”
  4. Employee scrolls the list as needed.
  5. Employee clicks the **“smartChatbot”** dropdown header.
  6. The **smartChatbot** dropdown expands, displaying:

     * **Name**: smartChatbot
     * **Size**: Small
     * **Status**: Not Started
     * **Assigned Employees**: *(empty list)*
     * **Qualifications**:

       * Java 
     * **Missing Qualifications**:

       * React
  7. Employee clicks the **“smartChatbot”** dropdown header again to collapse the details.
  8. Employee clicks on the **“Home”** tab.
  9. The Home page loads and displays the same information as in the precondition.

* **Scenario 2**: Exactly one project exists and all qualifications are satisfied.

  1. **Precondition**: Company has only one project, **weatherForecastingSystem**, and its assigned employees meet every required qualification.
  2. Employee clicks on the **“Projects”** tab.
  3. The Projects page loads with a single dropdown labeled **weatherForecastingSystem** and the text “This page displays a table containing all the projects.”
  4. Employee clicks the **weatherForecastingSystem** dropdown header.
  5. The dropdown expands, displaying:

     * **Name**: weatherForecastingSystem
     * **Size**: Large
     * **Status**: In Progress
     * **Assigned Employees**: Alice Smith, Bob Jones
     * **Qualifications**:

       * Python 
       * SQL 
     * **Missing Qualifications** 
       * (Section appears empty since they are all are satisfied.)
  6. Employee clicks on the **“Home”** tab.
  7. The Home page loads and displays the same information as in the precondition.

* **Scenario 3**: No projects exist yet.

  1. **Precondition**: Company has zero projects.
  2. Employee clicks on the **“Projects”** tab.
  3. The Projects page loads showing only the text “This page displays a table containing all the projects.” and no dropdowns.
  4. Employee clicks on the **“Home”** tab.
  5. The Home page loads and displays the same information as in the precondition.
