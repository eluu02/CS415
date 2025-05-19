4.1. Randoop

# Capture a screenshot (or log) showing that you generated the tests.Randoop may generate the test files in other folders. 
juneau:~/git/cs415/t15/server$ java -cp "tools/randoop-all-4.3.2.jar:target/classes" randoop.main.Main gentests --classlist=tools/classes.txt --time-limit=60
Randoop for Java version "4.3.2, local changes, branch master, commit df17bc8, 2023-01-08".

Will try to generate tests for 4 classes.
PUBLIC MEMBERS=60
Explorer = ForwardGenerator(steps: 0, null steps: 0, num_sequences_generated: 0;
    allSequences: 0, regresson seqs: 0, error seqs: 0=0=0, invalid seqs: 0, subsumed_sequences: 0, num_failed_output_test: 0;
    sideEffectFreeMethods: 1115, runtimePrimitivesSeen: 38)

Progress update: steps=1, test inputs generated=0, failing inputs=0      (2025-04-08T18:29:31.395233     33.1M used)
Progress update: steps=1000, test inputs generated=672, failing inputs=0      (2025-04-08T18:29:42.470021Z     371M used)


Progress update: steps=2000, test inputs generated=1332, failing inputs=0      (2025-04-08T18:29:52.532595Z     685M used)
Progress update: steps=3000, test inputs generated=1955, failing inputs=0      (2025-04-08T18:30:01.904381Z     87.7M used)
Progress update: steps=4000, test inputs generated=2570, failing inputs=0      (2025-04-08T18:30:11.240664Z     467M used)^Cjuneau:~/git/cs415/t15/server$ 
juneau:~/git/cs415/t15/server$ git checkout -b randoop
Switched to a new branch 'randoop'
juneau:~/git/cs415/t15/server$ java -cp "tools/randoop-all-4.3.2.jar:target/classes" randoop.main.Main gentests --classlist=tools/classes.txt --time-limit=60
Randoop for Java version "4.3.2, local changes, branch master, commit df17bc8, 2023-01-08".

Will try to generate tests for 4 classes.
PUBLIC MEMBERS=60
Explorer = ForwardGenerator(steps: 0, null steps: 0, num_sequences_generated: 0;
    allSequences: 0, regresson seqs: 0, error seqs: 0=0=0, invalid seqs: 0, subsumed_sequences: 0, num_failed_output_test: 0;
    sideEffectFreeMethods: 1115, runtimePrimitivesSeen: 38)

Progress update: steps=1, test inputs generated=0, failing inputs=0      (2025-04-08T18:30:31.406893Z     33.1M used)
Progress update: steps=1000, test inputs generated=672, failing inputs=0      (2025-04-08T18:30:42.628339Z     492M used)
Progress update: steps=2000, test inputs generated=1332, failing inputs=0      (2025-04-08T18:30:52.731434Z     89.2M used)
Progress update: steps=3000, test inputs generated=1955, failing inputs=0      (2025-04-08T18:31:02.144925Z     251M used)
Progress update: steps=4000, test inputs generated=2570, failing inputs=0      (2025-04-08T18:31:11.560884Z     656M used)
Progress update: steps=5000, test inputs generated=3206, failing inputs=0      (2025-04-08T18:31:21.410892Z     314M used)
Progress update: steps=6000, test inputs generated=3823, failing inputs=0      (2025-04-08T18:31:30.971587Z     262M used)
Progress update: steps=6059, test inputs generated=3854, failing inputs=0      (2025-04-08T18:31:31.414063Z     297M used)
Normal method executions: 24062326
Exceptional method executions: 248

Average method execution time (normal termination):      3.26e-05
Average method execution time (exceptional termination): 0.0279
Approximate memory usage 297M
Explorer = ForwardGenerator(steps: 6059, null steps: 2205, num_sequences_generated: 3854;
    allSequences: 3854, regresson seqs: 3853, error seqs: 0=0=0, invalid seqs: 0, subsumed_sequences: 0, num_failed_output_test: 1;
    sideEffectFreeMethods: 1115, runtimePrimitivesSeen: 78)

No error-revealing tests to output.

About to look for failing assertions in 1956 regression sequences.

Regression test output:
Regression test count: 1956
Writing regression JUnit tests...
                                                                                                                                                                Created file /s/chopin/n/under/brown02/git/cs415/t15/server/RegressionTest0.java
Created file /s/chopin/n/under/brown02/git/cs415/t15/server/RegressionTest1.java
Created file /s/chopin/n/under/brown02/git/cs415/t15/server/RegressionTest2.java
Created file /s/chopin/n/under/brown02/git/cs415/t15/server/RegressionTest3.java
Created file /s/chopin/n/under/brown02/git/cs415/t15/server/RegressionTest.java
Wrote regression JUnit tests.
About to look for flaky methods.

Invalid tests generated: 0

# Capture a screenshot (or log) showing that you could run these generated tests (maven must not fail).
juneau:~/git/cs415/t15/server/src/test/java/edu/colostate/cs415/server$ ls
RegressionTest0.java  RegressionTest1.java  RegressionTest2.java  RegressionTest3.java  RegressionTest.java  RestControllerTest.java
juneau:~/git/cs415/t15/server/src/test/java/edu/colostate/cs415/server$ These are the Randoop test cases
bash: These: command not found
juneau:~/git/cs415/t15/server/src/test/java/edu/colostate/cs415/server$ cd ..
juneau:~/git/cs415/t15/server/src/test/java/edu/colostate/cs415$ cd ..
juneau:~/git/cs415/t15/server/src/test/java/edu/colostate$ cd ..
juneau:~/git/cs415/t15/server/src/test/java/edu$ cd ..
juneau:~/git/cs415/t15/server/src/test/java$ cd ..
juneau:~/git/cs415/t15/server/src/test$ cd ..
juneau:~/git/cs415/t15/server/src$ cd ..
juneau:~/git/cs415/t15/server$ mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------< edu.colostate.cs415:company_management >---------------
[INFO] Building company_management 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.8:prepare-agent (default) @ company_management ---
[INFO] argLine set to -javaagent:/s/chopin/n/under/brown02/.m2/repository/org/jacoco/org.jacoco.agent/0.8.8/org.jacoco.agent-0.8.8-runtime.jar=destfile=/s/chopin/n/under/brown02/git/cs415/t15/server/target/jacoco.exec
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ company_management ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /s/chopin/n/under/brown02/git/cs415/t15/server/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ company_management ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ company_management ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /s/chopin/n/under/brown02/git/cs415/t15/server/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ company_management ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ company_management ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running edu.colostate.cs415.server.RestControllerTest
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Apr 08, 2025 12:44:36 PM edu.colostate.cs415.server.RestController logRequest
INFO: GET /helloworld
REQUEST:

RESPONSE:
Hello World!
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.314 s - in edu.colostate.cs415.server.RestControllerTest
[INFO] Running edu.colostate.cs415.server.RegressionTest
[INFO] Tests run: 1956, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.269 s - in edu.colostate.cs415.server.RegressionTest
[INFO] Running edu.colostate.cs415.model.QualificationTest
[INFO] Tests run: 22, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in edu.colostate.cs415.model.QualificationTest
[INFO] Running edu.colostate.cs415.model.ProjectTest
[INFO] Tests run: 53, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in edu.colostate.cs415.model.ProjectTest
[INFO] Running edu.colostate.cs415.model.WorkerTest
[INFO] Tests run: 54, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in edu.colostate.cs415.model.WorkerTest
[INFO] Running edu.colostate.cs415.model.CompanyTest
[INFO] Tests run: 92, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in edu.colostate.cs415.model.CompanyTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2178, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.8:report (report) @ company_management ---
[INFO] Loading execution data file /s/chopin/n/under/brown02/git/cs415/t15/server/target/jacoco.exec
[INFO] Analyzed bundle 'company_management' with 13 classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.159 s
[INFO] Finished at: 2025-04-08T12:44:37-06:00
[INFO] ------------------------------------------------------------------------
juneau:~/git/cs415/t15/server$ 

4.2. Evosuite
# Capture a screenshot (or log) showing that you generated the tests.Randoop may generate the test files in other folders. 

pepper:~/CS415/t15/server$ java -jar tools/evosuite-1.0.6.jar -prefix edu.colostate.cs415.model -projectCP target/classes
* EvoSuite 1.0.6
* Analyzing classpath (generating inheritance tree)
  - target/classes
* Found 6 matching classes for prefix edu.colostate.cs415.model
* Current class: edu.colostate.cs415.model.Worker
* Going to generate test cases for class: edu.colostate.cs415.model.Worker
* Starting client
* Connecting to master process on port 17193
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree6932798753422511446.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.Worker
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 77
  - Branch 64
  - Exception 0
  - MutationFactory 126
  - Output 37
  - Method 16
  - MethodNoException 16
  - CBranchFitnessFactory 64
* Using seed 1744326788262
* Starting evolution
[Progress:===================>          66%] [Cov:===============================>   90%][MASTER] 17:13:49.675 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
[Progress:==============================100%] [Cov:===============================>   90%]
* Search finished after 61s and 529 generations, 219877 statements, best individual has fitness: 43.88928571300457
* Minimizing test suite
* Going to analyze the coverage criteria
* Coverage analysis for criterion LINE
* Coverage of criterion LINE: 95%
* Total number of goals: 77
* Number of covered goals: 73
* Coverage analysis for criterion BRANCH
* Coverage of criterion BRANCH: 91%
* Total number of goals: 64
* Number of covered goals: 58
* Coverage analysis for criterion EXCEPTION
* Coverage of criterion EXCEPTION: 100%
* Total number of goals: 7
* Number of covered goals: 7
* Coverage analysis for criterion WEAKMUTATION
* Coverage of criterion WEAKMUTATION: 90%
* Total number of goals: 126
* Number of covered goals: 114
* Coverage analysis for criterion OUTPUT
* Coverage of criterion OUTPUT: 59%
* Total number of goals: 37
* Number of covered goals: 22
* Coverage analysis for criterion METHOD
* Coverage of criterion METHOD: 100%
* Total number of goals: 16
* Number of covered goals: 16
* Coverage analysis for criterion METHODNOEXCEPTION
* Coverage of criterion METHODNOEXCEPTION: 100%
* Total number of goals: 16
* Number of covered goals: 16
* Coverage analysis for criterion CBRANCH
* Coverage of criterion CBRANCH: 91%
* Total number of goals: 64
* Number of covered goals: 58
* Generated 39 tests with total length 210
* Resulting test suite's coverage: 91% (average coverage for all fitness functions)
* Generating assertions
* Resulting test suite's mutation score: 64%
* Compiling and checking tests
* Writing JUnit test case 'Worker_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.Project
* Going to generate test cases for class: edu.colostate.cs415.model.Project
* Starting client
* Connecting to master process on port 8205
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree6932798753422511446.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.Project
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 72
  - Branch 54
  - Exception 0
  - MutationFactory 79
  - Output 36
  - Method 17
  - MethodNoException 17
  - CBranchFitnessFactory 54
* Using seed 1744326856696
[Progress:>                             0%] [Cov:>                                  0%]* Starting evolution
[Progress:=======>                      26%] [Cov:===============================>   90%][MASTER] 17:14:34.037 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
[Progress:==============================100%] [Cov:===============================>   90%]
* Search finished after 61s and 1124 generations, 251467 statements, best individual has fitness: 35.458333333333336
* Minimizing test suite
* Going to analyze the coverage criteria
* Coverage analysis for criterion LINE
* Coverage of criterion LINE: 97%
* Total number of goals: 72
* Number of covered goals: 70
* Coverage analysis for criterion BRANCH
* Coverage of criterion BRANCH: 91%
* Total number of goals: 54
* Number of covered goals: 49
* Coverage analysis for criterion EXCEPTION
* Coverage of criterion EXCEPTION: 100%
* Total number of goals: 7
* Number of covered goals: 7
* Coverage analysis for criterion WEAKMUTATION
* Coverage of criterion WEAKMUTATION: 87%
* Total number of goals: 79
* Number of covered goals: 69
* Coverage analysis for criterion OUTPUT
* Coverage of criterion OUTPUT: 61%
* Total number of goals: 36
* Number of covered goals: 22
* Coverage analysis for criterion METHOD
* Coverage of criterion METHOD: 100%
* Total number of goals: 17
* Number of covered goals: 17
* Coverage analysis for criterion METHODNOEXCEPTION
* Coverage of criterion METHODNOEXCEPTION: 100%
* Total number of goals: 17
* Number of covered goals: 17
* Coverage analysis for criterion CBRANCH
* Coverage of criterion CBRANCH: 91%
* Total number of goals: 54
* Number of covered goals: 49
* Generated 33 tests with total length 161
* Resulting test suite's coverage: 91% (average coverage for all fitness functions)
* Generating assertions
* Resulting test suite's mutation score: 57%
* Compiling and checking tests
* Writing JUnit test case 'Project_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.Qualification
* Going to generate test cases for class: edu.colostate.cs415.model.Qualification
* Starting client
* Connecting to master process on port 14539
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree6932798753422511446.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.Qualification
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 28
  - Branch 25
  - Exception 0
  - MutationFactory 24
  - Output 13
  - Method 8
  - MethodNoException 8
  - CBranchFitnessFactory 25
* Using seed 1744326923125
* Starting evolution
[Progress:============>                 41%] [Cov:===============================>   89%][MASTER] 17:15:49.368 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
[Progress:==============================100%] [Cov:===============================>   89%]
* Search finished after 61s and 1776 generations, 296405 statements, best individual has fitness: 16.583333333333332
* Minimizing test suite
* Going to analyze the coverage criteria
* Coverage analysis for criterion LINE
* Coverage of criterion LINE: 96%
* Total number of goals: 28
* Number of covered goals: 27
* Coverage analysis for criterion BRANCH
* Coverage of criterion BRANCH: 88%
* Total number of goals: 25
* Number of covered goals: 22
* Coverage analysis for criterion EXCEPTION
* Coverage of criterion EXCEPTION: 100%
* Total number of goals: 3
* Number of covered goals: 3
* Coverage analysis for criterion WEAKMUTATION
* Coverage of criterion WEAKMUTATION: 83%
* Total number of goals: 24
* Number of covered goals: 20
* Coverage analysis for criterion OUTPUT
* Coverage of criterion OUTPUT: 62%
* Total number of goals: 13
* Number of covered goals: 8
* Coverage analysis for criterion METHOD
* Coverage of criterion METHOD: 100%
* Total number of goals: 8
* Number of covered goals: 8
* Coverage analysis for criterion METHODNOEXCEPTION
* Coverage of criterion METHODNOEXCEPTION: 100%
* Total number of goals: 8
* Number of covered goals: 8
* Coverage analysis for criterion CBRANCH
* Coverage of criterion CBRANCH: 88%
* Total number of goals: 25
* Number of covered goals: 22
* Generated 15 tests with total length 41
* Resulting test suite's coverage: 90% (average coverage for all fitness functions)
* Generating assertions
* Resulting test suite's mutation score: 55%
* Compiling and checking tests
* Writing JUnit test case 'Qualification_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.ProjectStatus
* Going to generate test cases for class: edu.colostate.cs415.model.ProjectStatus
* Starting client
* Connecting to master process on port 17406
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree6932798753422511446.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.ProjectStatus
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 0
  - Branch 0
  - Exception 0
  - MutationFactory 0
  - Output 0
  - Method 0
  - MethodNoException 0
  - CBranchFitnessFactory 0
* Using seed 1744326987758
* Starting evolution
[Progress:>                             0%] [Cov:===================================100%]
* Search finished after 0s and 0 generations, 585 statements, best individual has fitness: 1.0
* Minimizing test suite
* Going to analyze the coverage criteria
* Coverage analysis for criterion LINE
* Coverage of criterion LINE: 100% (no goals)
* Coverage analysis for criterion BRANCH
* Coverage of criterion BRANCH: 100% (no goals)
* Coverage analysis for criterion EXCEPTION
* Coverage of criterion EXCEPTION: 100% (no goals)
* Coverage analysis for criterion WEAKMUTATION
* Coverage of criterion WEAKMUTATION: 100% (no goals)
* Coverage analysis for criterion OUTPUT
* Coverage of criterion OUTPUT: 100% (no goals)
* Coverage analysis for criterion METHOD
* Coverage of criterion METHOD: 100% (no goals)
* Coverage analysis for criterion METHODNOEXCEPTION
* Coverage of criterion METHODNOEXCEPTION: 100% (no goals)
* Coverage analysis for criterion CBRANCH
* Coverage of criterion CBRANCH: 100% (no goals)
* Generated 0 tests with total length 0
* Resulting test suite's coverage: 100% (average coverage for all fitness functions)
* Generating assertions
* Resulting test suite's mutation score: 100%
* Compiling and checking tests
* Writing JUnit test case 'ProjectStatus_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.Company
* Going to generate test cases for class: edu.colostate.cs415.model.Company
* Starting client
* Connecting to master process on port 17850
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree6932798753422511446.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.Company
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 138
  - Branch 149
  - Exception 0
  - MutationFactory 171
  - Output 58
  - Method 20
  - MethodNoException 20
  - CBranchFitnessFactory 149
* Using seed 1744326989507
* Starting evolution
[Progress:===============>              51%] [Cov:=============================>     83%][MASTER] 17:17:02.487 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
[Progress:==============================100%] [Cov:==============================>    88%]
* Search finished after 61s and 237 generations, 117231 statements, best individual has fitness: 131.38141025175366
* Minimizing test suite
* Going to analyze the coverage criteria
* Coverage analysis for criterion LINE
* Coverage of criterion LINE: 91%
* Total number of goals: 138
* Number of covered goals: 125
* Coverage analysis for criterion BRANCH
* Coverage of criterion BRANCH: 84%
* Total number of goals: 149
* Number of covered goals: 125
* Coverage analysis for criterion EXCEPTION
* Coverage of criterion EXCEPTION: 100%
* Total number of goals: 7
* Number of covered goals: 7
* Coverage analysis for criterion WEAKMUTATION
* Coverage of criterion WEAKMUTATION: 79%
* Total number of goals: 171
* Number of covered goals: 135
* Coverage analysis for criterion OUTPUT
* Coverage of criterion OUTPUT: 67%
* Total number of goals: 58
* Number of covered goals: 39
* Coverage analysis for criterion METHOD
* Coverage of criterion METHOD: 100%
* Total number of goals: 20
* Number of covered goals: 20
* Coverage analysis for criterion METHODNOEXCEPTION
* Coverage of criterion METHODNOEXCEPTION: 100%
* Total number of goals: 20
* Number of covered goals: 20
* Coverage analysis for criterion CBRANCH
* Coverage of criterion CBRANCH: 84%
* Total number of goals: 149
* Number of covered goals: 125
* Generated 56 tests with total length 249
* Resulting test suite's coverage: 88% (average coverage for all fitness functions)
* Generating assertions
* Resulting test suite's mutation score: 55%
* Compiling and checking tests
* Writing JUnit test case 'Company_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.ProjectSize
* Going to generate test cases for class: edu.colostate.cs415.model.ProjectSize
* Starting client
* Connecting to master process on port 9877
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree6932798753422511446.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.ProjectSize
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 4
  - Branch 1
  - Exception 0
  - MutationFactory 3
  - Output 10
  - Method 1
  - MethodNoException 1
  - CBranchFitnessFactory 1
* Using seed 1744327060373
* Starting evolution
[Progress:==============================100%] [Cov:=============================>     83%]
* Search finished after 61s and 4850 generations, 321014 statements, best individual has fitness: 8.75
* Minimizing test suite
* Going to analyze the coverage criteria
* Coverage analysis for criterion LINE
* Coverage of criterion LINE: 25%
* Total number of goals: 4
* Number of covered goals: 1
* Coverage analysis for criterion BRANCH
* Coverage of criterion BRANCH: 100%
* Total number of goals: 1
* Number of covered goals: 1
* Coverage analysis for criterion EXCEPTION
* Coverage of criterion EXCEPTION: 100% (no goals)
* Coverage analysis for criterion WEAKMUTATION
* Coverage of criterion WEAKMUTATION: 100%
* Total number of goals: 3
* Number of covered goals: 3
* Coverage analysis for criterion OUTPUT
* Coverage of criterion OUTPUT: 30%
* Total number of goals: 10
* Number of covered goals: 3
* Coverage analysis for criterion METHOD
* Coverage of criterion METHOD: 100%
* Total number of goals: 1
* Number of covered goals: 1
* Coverage analysis for criterion METHODNOEXCEPTION
* Coverage of criterion METHODNOEXCEPTION: 100%
* Total number of goals: 1
* Number of covered goals: 1
* Coverage analysis for criterion CBRANCH
* Coverage of criterion CBRANCH: 100%
* Total number of goals: 1
* Number of covered goals: 1
* Generated 3 tests with total length 4
* Resulting test suite's coverage: 82% (average coverage for all fitness functions)
* Generating assertions
* Resulting test suite's mutation score: 50%
* Compiling and checking tests
* Writing JUnit test case 'ProjectSize_ESTest' to evosuite-tests
* Done!

* Computation finished

# Capture a screenshot (or log) showing that you could run these generated tests (maven must not fail).

pepper:~/CS415/t15/server$ mvn test
[INFO] Scanning for projects...
[WARNING] 
[WARNING] Some problems were encountered while building the effective model for edu.colostate.cs415:company_management:jar:1.0-SNAPSHOT
[WARNING] 'dependencies.dependency.systemPath' for evosuite:evosuite:jar should not point at files within the project directory, ${project.basedir}/tools/evosuite-1.0.6.jar will be unresolvable by dependent projects @ line 43, column 19
[WARNING] 
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING] 
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING] 
[INFO] 
[INFO] ---------------< edu.colostate.cs415:company_management >---------------
[INFO] Building company_management 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.8:prepare-agent (default) @ company_management ---
[INFO] argLine set to -javaagent:/s/parsons/n/under/trowbsco/.m2/repository/org/jacoco/org.jacoco.agent/0.8.8/org.jacoco.agent-0.8.8-runtime.jar=destfile=/s/parsons/n/under/trowbsco/CS415/t15/server/target/jacoco.exec
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ company_management ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /s/parsons/n/under/trowbsco/CS415/t15/server/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ company_management ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ company_management ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /s/parsons/n/under/trowbsco/CS415/t15/server/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ company_management ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ company_management ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running edu.colostate.cs415.model.QualificationTest
[INFO] Tests run: 22, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.04 s - in edu.colostate.cs415.model.QualificationTest
[INFO] Running edu.colostate.cs415.model.ProjectTest
[INFO] Tests run: 53, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 s - in edu.colostate.cs415.model.ProjectTest
[INFO] Running edu.colostate.cs415.model.WorkerTest
[INFO] Tests run: 54, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.015 s - in edu.colostate.cs415.model.WorkerTest
[INFO] Running edu.colostate.cs415.model.CompanyTest
[INFO] Tests run: 92, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.032 s - in edu.colostate.cs415.model.CompanyTest
[INFO] Running edu.colostate.cs415.server.RestControllerTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in edu.colostate.cs415.server.RestControllerTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 222, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.8:report (report) @ company_management ---
[INFO] Loading execution data file /s/parsons/n/under/trowbsco/CS415/t15/server/target/jacoco.exec
[INFO] Analyzed bundle 'company_management' with 13 classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.183 s
[INFO] Finished at: 2025-04-10T17:19:53-06:00
[INFO] ------------------------------------------------------------------------
pepper:~/CS415/t15/server$ mvn test
[INFO] Scanning for projects...
[WARNING] 
[WARNING] Some problems were encountered while building the effective model for edu.colostate.cs415:company_management:jar:1.0-SNAPSHOT
[WARNING] 'dependencies.dependency.systemPath' for evosuite:evosuite:jar should not point at files within the project directory, ${project.basedir}/tools/evosuite-1.0.6.jar will be unresolvable by dependent projects @ line 43, column 19
[WARNING] 
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING] 
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING] 
[INFO] 
[INFO] ---------------< edu.colostate.cs415:company_management >---------------
[INFO] Building company_management 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.8:prepare-agent (default) @ company_management ---
[INFO] argLine set to -javaagent:/s/parsons/n/under/trowbsco/.m2/repository/org/jacoco/org.jacoco.agent/0.8.8/org.jacoco.agent-0.8.8-runtime.jar=destfile=/s/parsons/n/under/trowbsco/CS415/t15/server/target/jacoco.exec
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ company_management ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /s/parsons/n/under/trowbsco/CS415/t15/server/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ company_management ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ company_management ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /s/parsons/n/under/trowbsco/CS415/t15/server/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ company_management ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 17 source files to /s/parsons/n/under/trowbsco/CS415/t15/server/target/test-classes
[INFO] /s/parsons/n/under/trowbsco/CS415/t15/server/src/test/java/edu/colostate/cs415/model/CompanyTest.java: Some input files use unchecked or unsafe operations.
[INFO] /s/parsons/n/under/trowbsco/CS415/t15/server/src/test/java/edu/colostate/cs415/model/CompanyTest.java: Recompile with -Xlint:unchecked for details.
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ company_management ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running edu.colostate.cs415.model.QualificationTest
[INFO] Tests run: 22, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.04 s - in edu.colostate.cs415.model.QualificationTest
[INFO] Running edu.colostate.cs415.model.Company_ESTest
17:20:17.175 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Company_ESTest
17:20:17.182 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Company_ESTest'.
17:20:17.231 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Company_ESTest_scaffolding
17:20:17.232 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Company_ESTest_scaffolding'.
17:20:17.235 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Company_ESTest_scaffolding
17:20:17.235 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Company_ESTest
17:20:17.305 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Reference Handler"
17:20:17.305 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Finalizer"
17:20:17.305 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Signal Dispatcher"
17:20:17.305 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "process reaper"
17:20:17.305 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Java2D Disposer"
17:20:17.305 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "main"
17:20:17.305 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "surefire-forkedjvm-command-thread"
17:20:17.305 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "surefire-forkedjvm-ping-30s"
17:20:17.345 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.QualificationDTO
17:20:17.345 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.QualificationDTO'.
17:20:17.355 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/QualificationDTO
17:20:17.488 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/QualificationDTO
17:20:17.489 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.QualificationDTO
17:20:17.489 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.ProjectDTO
17:20:17.489 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.ProjectDTO'.
17:20:17.490 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/ProjectDTO
17:20:17.492 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/ProjectDTO
17:20:17.492 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.ProjectDTO
17:20:17.493 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Worker
17:20:17.493 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Worker'.
17:20:17.495 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Worker
17:20:17.500 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Worker
17:20:17.500 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Adding bytecode for initializing field MAX_WORKLOAD
17:20:17.501 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Worker
17:20:17.501 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.WorkerDTO
17:20:17.502 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.WorkerDTO'.
17:20:17.502 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/WorkerDTO
17:20:17.504 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/WorkerDTO
17:20:17.504 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.WorkerDTO
17:20:17.504 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Project
17:20:17.504 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Project'.
17:20:17.505 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Project
17:20:17.509 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Project
17:20:17.509 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Project
17:20:17.510 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Qualification
17:20:17.510 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Qualification'.
17:20:17.511 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Qualification
17:20:17.513 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Qualification
17:20:17.513 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Qualification
17:20:17.513 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectStatus
17:20:17.513 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectStatus'.
17:20:17.514 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectStatus
17:20:17.514 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectStatus
17:20:17.515 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectStatus
17:20:17.516 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Company
17:20:17.516 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Company'.
17:20:17.517 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Company
17:20:17.522 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Company
17:20:17.523 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Company
17:20:17.523 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectSize
17:20:17.523 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectSize'.
17:20:17.524 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectSize
17:20:17.524 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectSize
17:20:17.525 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectSize
[INFO] Tests run: 56, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.534 s - in edu.colostate.cs415.model.Company_ESTest
[INFO] Running edu.colostate.cs415.model.ProjectSize_ESTest
17:20:17.626 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectSize_ESTest
17:20:17.626 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectSize_ESTest'.
17:20:17.627 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectSize_ESTest_scaffolding
17:20:17.627 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectSize_ESTest_scaffolding'.
17:20:17.628 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectSize_ESTest_scaffolding
17:20:17.628 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectSize_ESTest
17:20:17.630 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Reference Handler"
17:20:17.630 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Finalizer"
17:20:17.630 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Signal Dispatcher"
17:20:17.630 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "process reaper"
17:20:17.630 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Java2D Disposer"
17:20:17.630 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "main"
17:20:17.630 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "surefire-forkedjvm-command-thread"
17:20:17.630 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "surefire-forkedjvm-ping-30s"
17:20:17.630 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectSize
17:20:17.630 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectSize'.
17:20:17.632 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectSize
17:20:17.632 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectSize
17:20:17.633 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectSize
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 s - in edu.colostate.cs415.model.ProjectSize_ESTest
[INFO] Running edu.colostate.cs415.model.ProjectTest
[INFO] Tests run: 53, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.012 s - in edu.colostate.cs415.model.ProjectTest
[INFO] Running edu.colostate.cs415.model.ProjectStatus_ESTest
17:20:17.651 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectStatus_ESTest
17:20:17.651 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectStatus_ESTest'.
17:20:17.652 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectStatus_ESTest_scaffolding
17:20:17.652 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectStatus_ESTest_scaffolding'.
17:20:17.653 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectStatus_ESTest_scaffolding
17:20:17.653 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectStatus_ESTest
17:20:17.654 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Reference Handler"
17:20:17.654 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Finalizer"
17:20:17.654 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Signal Dispatcher"
17:20:17.654 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "process reaper"
17:20:17.654 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "Java2D Disposer"
17:20:17.654 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "main"
17:20:17.654 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "surefire-forkedjvm-command-thread"
17:20:17.654 [main] DEBUG o.e.runtime.sandbox.MSecurityManager - Adding privileged thread: "surefire-forkedjvm-ping-30s"
17:20:17.655 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectStatus
17:20:17.655 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectStatus'.
17:20:17.655 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectStatus
17:20:17.656 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectStatus
17:20:17.656 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectStatus
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 s - in edu.colostate.cs415.model.ProjectStatus_ESTest
[INFO] Running edu.colostate.cs415.model.WorkerTest
[INFO] Tests run: 54, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.01 s - in edu.colostate.cs415.model.WorkerTest
[INFO] Running edu.colostate.cs415.model.CompanyTest
[INFO] Tests run: 92, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.034 s - in edu.colostate.cs415.model.CompanyTest
[INFO] Running edu.colostate.cs415.model.Worker_ESTest
17:20:17.711 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Worker_ESTest
17:20:17.711 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Worker_ESTest'.
17:20:17.715 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Worker_ESTest_scaffolding
17:20:17.715 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Worker_ESTest_scaffolding'.
17:20:17.716 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Worker_ESTest_scaffolding
17:20:17.716 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Worker_ESTest
17:20:17.720 [main] WARN  org.evosuite.runtime.sandbox.Sandbox - Sandbox can be initalized only once
17:20:17.720 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.QualificationDTO
17:20:17.720 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.QualificationDTO'.
17:20:17.721 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/QualificationDTO
17:20:17.722 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/QualificationDTO
17:20:17.723 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.QualificationDTO
17:20:17.723 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.ProjectDTO
17:20:17.723 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.ProjectDTO'.
17:20:17.724 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/ProjectDTO
17:20:17.725 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/ProjectDTO
17:20:17.725 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.ProjectDTO
17:20:17.725 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Worker
17:20:17.725 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Worker'.
17:20:17.726 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Worker
17:20:17.729 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Worker
17:20:17.729 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Adding bytecode for initializing field MAX_WORKLOAD
17:20:17.729 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Worker
17:20:17.730 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.WorkerDTO
17:20:17.730 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.WorkerDTO'.
17:20:17.730 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/WorkerDTO
17:20:17.735 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/WorkerDTO
17:20:17.736 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.WorkerDTO
17:20:17.736 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Project
17:20:17.736 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Project'.
17:20:17.737 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Project
17:20:17.740 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Project
17:20:17.741 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Project
17:20:17.741 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Qualification
17:20:17.741 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Qualification'.
17:20:17.742 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Qualification
17:20:17.744 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Qualification
17:20:17.745 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Qualification
17:20:17.745 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectStatus
17:20:17.745 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectStatus'.
17:20:17.746 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectStatus
17:20:17.746 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectStatus
17:20:17.747 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectStatus
17:20:17.747 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectSize
17:20:17.747 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectSize'.
17:20:17.748 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectSize
17:20:17.748 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectSize
17:20:17.749 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectSize
[INFO] Tests run: 39, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.089 s - in edu.colostate.cs415.model.Worker_ESTest
[INFO] Running edu.colostate.cs415.model.Project_ESTest
17:20:17.807 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Project_ESTest
17:20:17.807 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Project_ESTest'.
17:20:17.809 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Project_ESTest_scaffolding
17:20:17.809 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Project_ESTest_scaffolding'.
17:20:17.810 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Project_ESTest_scaffolding
17:20:17.810 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Project_ESTest
17:20:17.813 [main] WARN  org.evosuite.runtime.sandbox.Sandbox - Sandbox can be initalized only once
17:20:17.813 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.QualificationDTO
17:20:17.813 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.QualificationDTO'.
17:20:17.814 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/QualificationDTO
17:20:17.815 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/QualificationDTO
17:20:17.815 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.QualificationDTO
17:20:17.815 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.ProjectDTO
17:20:17.815 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.ProjectDTO'.
17:20:17.816 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/ProjectDTO
17:20:17.817 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/ProjectDTO
17:20:17.817 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.ProjectDTO
17:20:17.817 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Worker
17:20:17.817 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Worker'.
17:20:17.818 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Worker
17:20:17.820 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Worker
17:20:17.820 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Adding bytecode for initializing field MAX_WORKLOAD
17:20:17.820 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Worker
17:20:17.821 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.WorkerDTO
17:20:17.821 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.WorkerDTO'.
17:20:17.821 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/WorkerDTO
17:20:17.822 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/WorkerDTO
17:20:17.822 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.WorkerDTO
17:20:17.823 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Project
17:20:17.823 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Project'.
17:20:17.823 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Project
17:20:17.825 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Project
17:20:17.825 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Project
17:20:17.825 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Qualification
17:20:17.826 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Qualification'.
17:20:17.826 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Qualification
17:20:17.827 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Qualification
17:20:17.828 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Qualification
17:20:17.828 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectStatus
17:20:17.828 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectStatus'.
17:20:17.828 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectStatus
17:20:17.829 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectStatus
17:20:17.829 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectStatus
17:20:17.829 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectSize
17:20:17.829 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectSize'.
17:20:17.830 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectSize
17:20:17.830 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectSize
17:20:17.830 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectSize
[INFO] Tests run: 33, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.056 s - in edu.colostate.cs415.model.Project_ESTest
[INFO] Running edu.colostate.cs415.model.Qualification_ESTest
17:20:17.867 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Qualification_ESTest
17:20:17.867 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Qualification_ESTest'.
17:20:17.869 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Qualification_ESTest_scaffolding
17:20:17.869 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Qualification_ESTest_scaffolding'.
17:20:17.871 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Qualification_ESTest_scaffolding
17:20:17.871 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Qualification_ESTest
17:20:17.874 [main] WARN  org.evosuite.runtime.sandbox.Sandbox - Sandbox can be initalized only once
17:20:17.874 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.QualificationDTO
17:20:17.874 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.QualificationDTO'.
17:20:17.875 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/QualificationDTO
17:20:17.876 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/QualificationDTO
17:20:17.876 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.QualificationDTO
17:20:17.876 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.ProjectDTO
17:20:17.876 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.ProjectDTO'.
17:20:17.877 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/ProjectDTO
17:20:17.878 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/ProjectDTO
17:20:17.879 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.ProjectDTO
17:20:17.879 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Worker
17:20:17.879 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Worker'.
17:20:17.880 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Worker
17:20:17.883 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Worker
17:20:17.883 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Adding bytecode for initializing field MAX_WORKLOAD
17:20:17.883 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Worker
17:20:17.884 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.dto.WorkerDTO
17:20:17.884 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.dto.WorkerDTO'.
17:20:17.885 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/dto/WorkerDTO
17:20:17.885 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/dto/WorkerDTO
17:20:17.886 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.dto.WorkerDTO
17:20:17.886 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Project
17:20:17.886 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Project'.
17:20:17.887 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Project
17:20:17.888 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Project
17:20:17.889 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Project
17:20:17.889 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Qualification
17:20:17.889 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Qualification'.
17:20:17.890 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/Qualification
17:20:17.891 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Creating brand-new static initializer in class edu/colostate/cs415/model/Qualification
17:20:17.891 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.Qualification
17:20:17.891 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectStatus
17:20:17.891 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectStatus'.
17:20:17.892 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectStatus
17:20:17.892 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectStatus
17:20:17.892 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectStatus
17:20:17.892 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.ProjectSize
17:20:17.892 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectSize'.
17:20:17.893 [main] INFO  o.e.r.i.MethodCallReplacementClassAdapter - Adding mock interface to class edu/colostate/cs415/model/ProjectSize
17:20:17.893 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectSize
17:20:17.893 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectSize
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.038 s - in edu.colostate.cs415.model.Qualification_ESTest
[INFO] Running edu.colostate.cs415.server.RestControllerTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in edu.colostate.cs415.server.RestControllerTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 369, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.8:report (report) @ company_management ---
[INFO] Loading execution data file /s/parsons/n/under/trowbsco/CS415/t15/server/target/jacoco.exec
[INFO] Analyzed bundle 'company_management' with 13 classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.663 s
[INFO] Finished at: 2025-04-10T17:20:18-06:00
[INFO] ------------------------------------------------------------------------
pepper:~/CS415/t15/server$ 