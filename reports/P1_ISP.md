# Sprint 1 Input Space Partitioning Scheme
**Strategies we used for creating these test cases:**
1. Include values that represent "normal use"
2. Try to balance the number of blocks in each characteristic
3. Check for completeness and disjointness
4. Include valid and invalid values
5. Explore boundaries of domains
6. Mix of Functionality-based and Interface-based approaches???
---------------------------------------------------------
# `Qualification` Class Methods:
1. Method: `Qualification(description: String): constructor`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| description: String | A) length | A1) = 0 | 0 |
|  |  | `A2) >0 && <=1000` | 12 |
|  |  | A3) >1000 | 5000 |
|  |  | A4) null | null|


3. BCC Table:
   
A2) Being the best case

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Fail | qualificationTest01() |
| T2 | A2 | Pass | qualificationTest02() |
| T3 | A3 | Fail | qualificationTest03() |
| T4 | A4 | Pass | qualificationTest04() |

---------------------------------------------------------
1. Method: `equals(o: Object): boolean`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| o: Object | A) type | A1) = null | null |
|  |  | A2) = string | "abcd" |
|  |  | A3) = o | new Qualification "Test Qual" |
| description: String | B) description equality | B1) description is equal to o | description = "Test Qual" |
|  |  | B2) description is null | description = null |
|  |  | B3 ) description is not null and is not equal | description = "Test Q" |

A3) Being the best case
B1) Being the best case

3. BCC Table:

| Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 | `A3` | `B1` | Pass | equalsTest01() |
| T2 | A1 | `B1` | Fail | equalsTest02() |
| T3 | A2 | `B1` | Fail | equalsTest03() |
| T4 | `A3` | B2 | Fail | N/A |
| T5 | `A3` | B3 | Fail | equalsTest05() |

Note: Test 4 (T4) is not possible since Qualification.description cannot be null.


---------------------------------------------------------
1. Method: `hashcode(): int`
2. ISP Table:
   
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| description: String | A) value | A1) = null | null |
|  |  | A2) = "" | "" |
|  |  | `A3) = "Software Engineer"` | "Software Engineer" |
|  | B) hashcode is determined by description | B1) Workers with same name return same hashcode | Call hashCode() twice, once each for Qualifications: descriptions: description1 = “Qual1 !/$f”   descriptions2 = “Qual1 !/$f” |
|  |  | `B2) Qualifications with different descriptions` |  Call hashCode() twice, once each for Qualifications: descriptions: description1 = “Qual1”   descriptions2 = “Qual2”  |

A3) Being the best case

3. BCC Table: 

| Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 | `A3` | `B2` | Pass | hashCodeTest01() |
| T2 | A1 | `B2` | Fail | N/A |
| T3 | A2 | `B2` | Fail | N/A |
| T4 | `A3` | B1 | Fail | hashCodeTest04() |

Note: Tests 2 and 3 are not able to be reached due to the Qualification constructor ensuring the description is not empty or null.


---------------------------------------------------------
1. Method `toString(): String`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| Qualification | A) description | A1) = null | null |
|  |  | A2) = "" | "" |
|  |  | A3) = "Software Dev"| "Software Dev" |

A3) Being the best case

3. BCC Table: 

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Fail | qualificationTest01() |
| T2 | `A2` | Pass | N/A |
| T3 | A3 | Fail | N/A |


Note: Tests 1 and 2 are not able to be reached due to the Qualification constructor ensuring the description is not empty or null.

---------------------------------------------------------
1. Method `getWorkers() Set<Worker>`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| Set | A) Size | A1) Empty | Empty Set |
| | | `A2) > 0 && <= 100` | Set with 75 elemets |
| | | A3) > 101 | Set with 800 elements |
| | B) Type | B1) null | Null set |
| | | `B2) Worker object` | Worker object |
| | | B3) Random object | Object object |

3. BCC Table:

| Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 | `A2` | `B2` | Pass | getWorkersTest01() |
| T2 | `A2` | B1 | Fail | N/A |
| T3 | `A2` | B3 | Fail | N/A |
| T4 | A1 | `B2` | Fail | getWorkersTest04() |
| T5 | A3 | `B2` | Pass | getWorkersTest05() |

Note: Tests T2 and T3 are unreachable since the worker set is defined to be of type worker, and it gets initalized in the Qualification constructor.

---------------------------------------------------------
1. Method `addWorker(w: Worker): void`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| w: Worker interaction | A) Work is not in set before execution | `A1) = True` | Pass a worker who has not been added to this Qualification object |
| | | A2) = False | Add a worker to the qualification object and then pass that same worker to this method |
| | B) Worker is null | B1) = True | null |
| | | `B2) = False` | Worker object |

3. BCC Table:

| Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 | `A1` | `B2` | Pass | addWorkerTest01() |
| T2 | A2 | `B2` | Fail | addWorkerTest02() |
| T3 | `A1` | B1 | Fail | addWorkerTest03() |


---------------------------------------------------------
1. Method `removeWorker(w: Worker): void`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| ws: Set< Worker > / w: Worker interaction | A) Worker is in Set before execution | `A1) = True` | Add worker to Qualification object, then pass in that same worker to this method  |
|  |  | A2) = False | Pass in a worker who has not been added to this Qualification object |
|  | B) Worker is in Set after execution | `B1) False` | Add worker to Qualification object, then pass in that same worker to this method |
| w: Worker | C) Worker is null | C1) True | null |
|  |  | `C2) = False` | Worker object |

3. BCC Table:

| Tests |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 | A1 | B1 | C2 | Pass | removeWorkerTest01() |
| T2 | `A2` | B1 | C2 | Pass | removeWorkerTest02() |
| T3 | A1 | B1 | `C1` | Fail | removeWorkerTest03() |

---------------------------------------------------------
1. Method `toDTO( ): QualificationDTO`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| ws: Set< Worker > | A) Workers’ names present in DTO | `A1) All workers’ names present in DTO` | Set with 15 workers |
|  |  | A2) No workers’ names present in DTO | Set with no workers |
| description: String | B) Description string in DTO matches description string class variable | `B1) True` | Description string |

3. BCC Table:

| Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 | `A1` | B1 | Pass | toDTOTest01() |
| T2 | A2 | B1 | Pass | toDTOTest02() |

---------------------------------------------------------
# `Worker` Class Methods:
1.  Method: `Worker(name: String, qs: Set<Qualification>, salary: double): constructor`
2.  ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| name: String | A) length | A1) = 0 | "" |
|  |  | `A2) > 0 && < 20` | “EthanBottoms” |
|  |  | A3) >= 20 | “ThatsMyName,Don\’tWearItOut” |
| qs: Set < Qualification > | B) type | B1) Object not of type Qualification | Object object |
|  |  | `B2) Qualification object` | Qualification object |
|  |  | B3) Null | Null |
|  | C) size | C1) Empty | Empty set |
|  |  | `C2) > 0 && <= 50` | Set of size 25 |
|  |  | C3) > 50 | Set of size 250 |
|  | D) Presence in company qualifications | `D1) All qualifications are present in company qualifications` | qs has same qualifications as company |
|  |  | D2) Some qualifications are present in company qualifications | qs has some of the same qualifications as company |
|  |  | D3) None of the qualifications are present in company qualifications | qs has none of the same qualifications |
| salary: Double | E) sign | E1) < 0 | -1 |
|  |  | `E2) > 0` | 1 |
|  | F) size | F1) = 0 | 0 |
|  |  | `F2) > 0 && <= 200000` | 100000 |
|  |  | F3) > 200000 && <= double.MAX_VALUE | 10000000 |

3.  BCC Table:

| Tests |  |  |  |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|:---|:---|:---|
| T1 | A2 | B2 | C2 | D1 | E2 | E2 | Pass | constructorValidParametersTest01() |
| T2 | `A1` | B2 | C2 | D1 | E2 | F2 | Exception | constructorInvalidNameTest02() |
| T3 | `A3` | B2 | C2 | D1 | E2 | F2 | Pass | N/A |
| T4 | A2 | `B1` | C2 | D1 | E2 | F2 | Exception | N/A|
| T5 | A2 | `B3` | C2 | D1 | E2 | F2 | Exception | constructorNullQualificationsSetTest05() |
| T6 | A2 | B2 | `C1` | D1 | E2 | F2 | Exception | constructorEmptyQualificationsSetTest06() |
| T7 | A2 | B2 | `C3` | D1 | E2 | F2 | Pass | N/A | 
| T8 | A2 | B2 | C2 | `D2` | E2 | F2 | Exception | N/A |
| T9 | A2 | B2 | C2 | `D3` | E2 | F2 | Exception | N/A |
| T10 | A2 | B2 | C2 | D1 | `E1` | F2 | Exception | constructorNegativeSalaryTest10() | 
| T11 | A2 | B2 | C2 | D1 | E2 | `F1` | Pass | constructorZeroSalaryTest11() |
| T12 | A2 | B2 | C2 | D1 | E2 | `F3` | Pass | constructorMaxDoubleSalaryTest12() |

---------------------------------------------------------
1. Method: `equals(o: Object): boolean`
2.  ISP Table:
   
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| o: Object | A) Type | `A1) Worker object` | Worker object |
|  |  | A2) Non-worker object | String s = "Non-worker object" |
|  |  | A3) Null | null |
| name: String | B) Name of this Worker and name of Worker parameter object are equivalent | `B1) True` | this.name = “Bob@ !\`654”, o.name = “Bob@ !\`654” |
|  |  | B2) False | this.name = “Bob”, o.name = “Bob-ert” |

3.  BCC Table:

| Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 | A1 | B1 | True | testWorkerEquals01() |
| T2 | `A2` | B1 | False | testWorkerEquals02() |
| T3 | `A3` | B1 | False | testWorkerEquals03() |
| T4 | A1 | `B2` | False | testWorkerEquals04() |
   
---------------------------------------------------------
1. Method: `hashCode(): int`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| name: String | A) hashcode is determined by name | A1) Workers with same name return same hashcode | Call hashCode() twice, once each for Workers of the same name: name1 = “Bob@ !\`654”   name2 = “Bob@ !\`654” |
|  |  | `A2) Workers with different names return different hashcodes` |  Call hashCode() twice, once each for Workers with different names: name1 = “Bob”   name2 = “Bob-ert”  |

3. BCC Table:

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | `A1` | Equivalent hashcodes | testWorkerHashCode01() |
| T2 | A2 | Non-equivalent hashcodes | testWorkerHashCode02() |

---------------------------------------------------------
1. Method: `toString(): String`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| name: String | A) length | `A1) > 0 && < 20` | “EthanBottoms” |
|  |  | A2) >= 20 | “ThatsMyName,Don\’tWearItOut” |
| ps: Set < Project > | B) size | `B1) > 0 && <= 12` | Set with 6 projects |
| qs: Set< Qualification > | C) size | `C1) > 0 && <= 50` | Set with 25 qualifications |
|  |  | C2) > 50 | Set with 60 qualifications |
| salary: Double | D) Truncated on output | `D1) Salary with decimal` | salary = 100000.20 |
|  |  | D2) Salary without decimal | salary = 100000 |

3.  BCC Table:

| Tests |  |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|:---|
| T1 | A1 | B1 | C1 | D1 | Pass | testWorkerToString01() |
| T2 | `A2` | B1 | C1 | D1 | Pass | testWorkerToString02() |
| T3 | A1 | B1 | `C2` | D1 | Pass | testWorkerToString03() |
| T4 | A1 | B1 | C1 | `D2` | Pass | testWorkerToString04() |

---------------------------------------------------------
1. Method: `getName(): String`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| name: String | A) length | A1) null | null |
|  |  |  A2) length = 0 | "   " |
|  |  |  `A3) length > 0` | "worker" |

3. BCC Table: 

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Fail | N/A |
| T2 | A2 | Fail | N/A |
| T3 | `A3` | Pass | getNameTest03() |

---------------------------------------------------------
1. Method: `getSalary(): double`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| salary: double | A) sign | A1) < 0 | -100 |
|  |  | A2) = 0 | 0 |
|  |  | `A3) > 0` | 100 |


3. BCC Table: **N/A**

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Fail | N/A |
| T2 | A2 | Fail | getSalaryZeroTest02() |
| T3 | `A3` | Pass | getSalaryTest03() |

---------------------------------------------------------
1. Method: `setSalary(salary: double): double`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| salary: double | A) sign | A1) < 0 | -100 |
|  |  | A2) = 0 | 0 |
|  |  | `A3) > 0` | 100 |

3. BCC Table: 

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Fail | setSalaryTest01() |
| T2 | A2 | Fail | setSalaryTest02() |
| T3 | `A3` | Pass | setSalaryTest03() |

---------------------------------------------------------
1. Method: `getQualifications(): Set<Qualification>`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| qs: Set<Qualification> | A) type | `A1) Qualification object` | Qualification object |
|  |  | A2) Null | Null |
|  | B) size | B1) Empty | Empty set |
|  |  | `B2) > 0 && <= 50` | Set with 25 elements |
|  |  | B3) > 50 | Set with 101 elements |

3. BCC Table:
   
| Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 (Base test) | A1 | B2 | Pass | getQualificationsTest01() |
| T2 | A1 | B1 | Fail | N/A |
| T3 | A1 | B3 | Pass | getLargeQualificationsTest03() |
| T4 | A2 | B2 | Fail | getQualificationsTest04() |
---------------------------------------------------------
1. Method: `addQualification(q: Qualification): void`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| q: qualification | A) type | `A1) Qualification object` | Qualification object |
|  |  | A2) Null | Null |
|  |  | A3) Object not of type Qualification | new Object() |

3. BCC Table: 

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | `A1` | Pass | addQualifiationTestValidInputTest01() |
| T2 | A2 | Fail | addNullQualificationTest02() |
| T3 | A3 | Fail | N/A |

---------------------------------------------------------
1. Method: `getProjects(): Set(Project)`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| ps: Set<Project> | A) type | `A1) Project object` | Project object |
|  |  | A2) Null | Null |
|  | B) size | B1) Empty | Empty set |
|  |  | `B2) > 0 && <= 10` | Set with 10 elements |
|  |  | B3) > 10 | Set with 15 elements |

3. BCC Table:

| Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 (Base test) | A1 | B2 | Pass | getProjectsTest01() |
| T2 | A2 | B2 | Fail | N/A |
| T3 | A1 | B1 | Pass |  getEmptyProjectTest03() |
| T4 | A1 | B3 | Pass | getLargeProjectTest04() |
---------------------------------------------------------
1. Method: `addProject(p : Project): void`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| p: Project | self | A1) Uninitalized `Project` | Null |
|  |  | 'A2) Initalized `Project`' | Project obj |
|  |  | A3) Object not of type Project | new Object() |

3. BCC Table: 

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | `A1` | Fail | addProjectTestValidInputTest01() |
| T2 | A2 | Pass | addNullProjectTest02() |
| T3 | A3 | Fail | N/A |

---------------------------------------------------------
1. Method: `removeProject(p: Project): void`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| p: Project | self | A1) Uninitalized `Project` | Null |
|  |  | A2) Initalized `Project` | Project obj |
|  |  | A3) Object not of type `Project` | Object obj |
| projects Set<Project> | size | B1) <= 0 | size = 0 |
|  |  | B2) > 0 | size = 10 |

3. BCC Table:

| Tests | | | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 (Base test) | `A2` | `B2` | Pass | removeProjectTest01() |
| T2 | A1 | `B2` | Fail | removeProjectTest02() |
| T3 | A3 | `B2` | Fail | removeProjectTest03() |
| T4 | `A2` | B1 | Pass | removeProjectTest04() |

---------------------------------------------------------
1. Method: `getWorkload(): int`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| workload: int | Workload Amount | A1) Empty Workload | 0 |
|  |  | `A2) Partial Workload` | 6 |
|  |  | A3) Full Workload | 12 |
| p: Project | Project status | B1) Project finished | ProjectStatus.FINISHED |
|  |  | `B2) Project not finished` | ProjectStatus.ACTIVE |

3. BCC Table:

| Tests | | | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 (Base test) | A2 | B2 | Pass | testGetPartialWorkloadTest01() |
| T2 | A1 | B2 | Fail | testAssertEmptyWorkloadTest02() |
| T3 | A3 | B2 | Pass | testGetFullWorkloadTest03() | 
| T4 | A2 | B1 | Fail | testAssertPartialWorkloadTest04() |

---------------------------------------------------------
1. Method: `willOverload(p: Project): boolean`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| p: Project | A) p.Size | A1) Small Project | ProjectSize.SMALL |
|  |  | 'A2) Medium Project' | ProjectSize.MEDIUM |
|  |  | A3) Big Project | ProjectSize.BIG |
| self: Worker | B) self.getWorkload | 'B1) > 0 && <= 12' | 6 |
|  |  | B2) > 12 | 12 |
|  | C) self.contains(p) | C1) Contains p | True |
|  |  | 'C2) Does not contain p' | False |
|  | D) self.isAvailable | 'D1) Worker is available' | True |
|  |  | D2) Worker is not available | False |
|  | E) p.isHelpful(self) | 'E1) Worker is helpful' | True |
|  |  | E2) Worker is not helpful | False |

3. BCC Table:

| Tests |  |  |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|:---|:---|
| T1 (Base test) | A2 | B1 | C2 | D1 | E1 | Pass | testWillNotOverloadTest01() |
| T2 | A1 | B1 | C2 | D1 | E1 | Pass | testWillOverloadSmallTest02() |
| T3 | A3 | B1 | C2 | D1 | E1 | Pass | testWillOverloadBigTest03() |
| T4 | A2 | B2 | C2 | D1 | E1 | Pass | testWillOverloadTest04() |
| T5 | A2 | B1 | C1 | D1 | E1 | Fail | testWillOverloadContainsProjectTest05() |
| T6 | A2 | B1 | C2 | D2 | E1 | Fail | testWillOverloadNotAvailableTest06() |
| T7 | A2 | B1 | C2 | D2 | E2 | Fail | testWillOverloadNotHelpfulTest07() |

---------------------------------------------------------
1. Method: `isAvailable(): boolean`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| self: Worker | self.getWorkload | A1) = 0 | 0 |
|  |  | A2) > 0 && < 12 | 6 |
|  |  | A3) >= 12 | 12 |

3. BCC Table:

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Pass | testIsAvailable01() |
| T2 | `A2` | Pass | testIsAvailable02() |
| T3 | A3 | Pass | testIsAvailable03() |

---------------------------------------------------------
1. Method: `toDTO(): WorkerDTO`
2. ISP Table:
  
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| self: Worker | self | A1) Uninitalized `WorkerDTO` | Null |
|  |  | A2) Initalized `WorkerDTO` | Worker obj |
|  |  | A3) Object not of type `WorkerDTO` | Object obj |
| projects: Set<Projects> | size | B1) <= 0 | size = 0 |
|  |  | B2) > 0 | size = 3 |
| qualifications: Set<Qualification> | size | C1) <= 0 | size = 0 |
|  |  | C2) > 0 | size = 3 |


3. BCC Table:

| Tests |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 (Base test) | `A2` | `B2` | `C2` | Pass | toDTOTest01() |
| T2 | A1 | `B2` | `C2` | Fail | N/A |
| T3 | A3 | `B2` | `C2` | Fail | N/A |
| T4 | `A2` | B1 | `C2` | Pass | toDTOTest04() |
| T5 | `A2` | `B2` | C1 | Pass | toDTOTest05() |

Note: Tests T2 & T3 are unreachable.


---------------------------------------------------------
# `Project` Class Methods:
1. Method: `Project(name: String, qs: Set(Qualification), size: ProjectSize): constructor`
2.  ISP Table:
   

| **Variable**          | **Characteristic** | **Partition**                    | **Value**           |
|-----------------------|--------------------|----------------------------------|------------------------------------|
| **`name: String`**    | Length             | **A1)** = 0 (empty)             | `""` (empty string)                |
|                       |                    | **A2)** >0 && <=50              | `"ProjectX"` (length ~ 8)          |
|                       |                    | **A3)** >50                     | A string of length 60+             |
|                       |                    | **A4)** = null                  | 'null                               |
| **`qs: Set<Qualification>`** | Validity/Size | **B1)** null                    | `null`                             |
|                       |                    | **B2)** empty                   | `Collections.emptySet()`           |
|                       |                    | **B3)** non-empty && valid      | A set of 3 valid `Qualification` objects |
|                       |                    | **B4)** invalid contents        | A set with non-`Qualification` objects (e.g., `String`) |
| **`size: ProjectSize`** | Enumerated Value | **C1)** SMALL                   | `ProjectSize.SMALL`                |
|                       |                    | **C2)** MEDIUM                  | `ProjectSize.MEDIUM`               |
|                       |                    | **C3)** BIG                     | `ProjectSize.BIG`                  |


3.  BCC Table:

| **Tests** | **`name`**       | **`qs`**            | **`size`** | **Oracle**                                                                 | Test Name |
|-------------|------------------|---------------------|------------|-------------------------------------------------------------------------------------|---------------------------|
| **T1** (Base) | A2 (`"ProjectX"`) | B3 (valid set)       | C2 (MEDIUM) | **Pass**: Valid inputs → Project created.                                          | testAllValidInputs01() |
| **T2**        | **A1** (empty)    | B3 (valid set)       | C2 (MEDIUM) | **Fail**: Empty name → `IllegalArgumentException`.                                 | testEmptyName02() |
| **T3**        | **A3** (60+ chars)| B3 (valid set)       | C2 (MEDIUM) | **Pass**: Long name allowed (no explicit length constraint in problem statement).  | testLongName03() |
| **T4**        | A2 (`"ProjectX"`) | **B1** (`null`)      | C2 (MEDIUM) | **Fail**: `null` qualifications → `NullPointerException`/validation failure.       | testNullQualifications04() |
| **T5**        | A2 (`"ProjectX"`) | **B2** (empty set)   | C2 (MEDIUM) | **Fail**: Empty qualifications → `IllegalArgumentException`.                      | testEmptyQualifications05() |
| **T6**        | A2 (`"ProjectX"`) | **B4** (invalid set) | C2 (MEDIUM) | **Fail**: Invalid `Qualification` type → `ClassCastException`/validation failure.  | implicitly tested by Java |
| **T7**        | A2 (`"ProjectX"`) | B3 (valid set)       | **C1** (SMALL) | **Pass**: Valid size → Project created.                                            | testSmallProject07() |
| **T8**        | A2 (`"ProjectX"`) | B3 (valid set)       | **C3** (BIG) | **Pass**: Valid size → Project created.                                            | testBigProject08() |
| **T9**        | **A4** (null)     | B3 (valid set)       | C2 (MEDIUM)  | **Fail**: Null name input -> IllegalArgumentException                            | testNullName09() |


---------------------------------------------------------
1. Method: `equals(o: Object): boolean`
2.  ISP Table:

| **Variable**      | **Characteristic** | **Partition**                    | **Value**                           |
|-------------------|--------------------|----------------------------------|---------------------------------------------------|
| **`o: Object`**   | Type/Value         | **D1)** `null`                  | `null`                                            |
|                   |                    | **D2)** Different class         | `"NotAProject"` (type `String`)                   |
|                   |                    | **D3)** Same class, same name   | `new Project("ProjectX", validSet, MEDIUM)`       |
|                   |                    | **D4)** Same class, different name | `new Project("OtherProject", validSet, MEDIUM)`  |


3.  BCC Table:

| **Tests** |  | **Oracle**              | Test Name |
|-------------|--------------------------|-------------------------------------------------------|-------------|
| **T1** (Base) | D3 (same name `Project`) | `true` (same name → equality).                        | testEquals_SameName01() |
| **T2**        | **D1** (`null`)          | `false` (null → not equal).                           | testEquals_Null02() |
| **T3**        | **D2** (`String`)        | `false` (different type → not equal).                 | testEquals_DifferentClass03() |
| **T4**        | **D4** (different name)  | `false` (different name → not equal).                 | testEquals_DifferentName04() |
---------------------------------------------------------
1. Method: `hashCode(): int`
2.  ISP Table:

| Variable   | Characteristic           | Partition        | Value |
|------------|--------------------------|------------------|-------|
| `name` (String) | A) Null or Non-null    | A1) `null` | `null` |
|            |                            | A2) Empty String | `""` |
|            |                            | A3) Non-empty String | `"ProjectA"` |
|            | B) Length                  | B1) Short Name | `"A"` |
|            |                            | B2) Long Name | `"ThisIsAReallyLongProjectNameThatExceeds50Characters"` |
|            | C) Character Composition   | C1) Alphabetic | `"Project"` |
|            |                            | C2) Alphanumeric | `"Project123"` |
|            |                            | C3) Special Characters | `"Project_@!%"` |

3.  BCC Table:

| Tests | Oracle | Test Name |
|:---|:---|:---|
| T1 | Pass | `testHashCode_Consistency()` |
| T2 | Fail | `N/A` |
| T3 | Fail | `N/A` |
| T4 | Pass | `testHashCode_DistinctHashCodesForManyProjects()` |

---------------------------------------------------------
1. Method `getName(): String`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| name: String | A) length | A1) null | null |
|  |  |  A2) length = 0 | "   " |
|  |  |  `A3) length > 0` | "worker" |


3. BCC Table:

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 (Base test) | `A3` | Pass | testGetName_Valid01() |
| T2 | A1 | Fail | testGetName_Null02() |
| T3 | A2 | Fail | testGetName_Empty03() |

---------------------------------------------------------
1. Method `toString(): String`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| `name: String` | A) Project Name Validity | A1) `null` | `null` |
|  |  | A2) `length = 0` | `""` (Empty String) |
|  |  | A3) `length > 0` | `"CS5Anniv"` |
| `workers: Set<Worker>` | B) Number of Workers | B1) `0 workers` | `{}` (Empty Set) |
|  |  | B2) `1 worker` | `{Worker("Alan")}` |
|  |  | B3) `Multiple workers` | `{Worker("Alice"), Worker("Dan"), ..., Worker("Zara")}` |
| `status: ProjectStatus` | C) Project Status Enum | C1) `PLANNED` | `ProjectStatus.PLANNED` |
|  |  | C2)`SUSPENDED` | `ProjectStatus.SUSPENDED` |
|  |  | C3)  `ACTIVE` | `ProjectStatus.ACTIVE`  |
|  |  | C4) `FINISHED` | `ProjectStatus.FINISHED` |


3. BCC Table

| Tests |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 | A3 | B2 | C1 | Pass | testGetSize_Small() |
| T2 | A3 | B2 | C2 | Pass | testGetSize_Medium() |
| T3 | A3 | B2 | C3 | Pass | testGetSize_Big() |
| T4 | A3 | B3 | C2 | Pass | testGetSize_Default() |
| T5 | A3 | B3 | C3 | Pass | testGetSize_MultipleQualifications() |

---------------------------------------------------------

1. Method `getStatus(): ProjectStatus`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| status: ProjectStatus enum | A) Type | A1) PLANNED | ProjectStatus.PLANNED  |
|  |  | A2) SUSPENDED | ProjectStatus.PLANNED  |
|  |  | `A3) ACTIVE` | ProjectStatus.ACTIVE  |
|  |  | A4) FINISHED | ProjectStatus.FINISHED  |
|  |  | A5) Null | null  |


3. BCC Table:

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | ProjectStatus.PLANNED | setAndGetStatus() |
| T1 | A2 | ProjectStatus.SUSPENDED | setAndGetStatus() |
| T1 | `A3` | ProjectStatus.ACTIVE | setAndGetStatus() |
| T1 | A4 | ProjectStatus.FINISHED | setAndGetStatus() |
| T1 | A5 | Exception # Note: Probably don’t need to test this since the project constructor / status setter should handle a null project status | Tested by Project constructor |

---------------------------------------------------------
1. Method `setStatus(): ProjectStatus`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| status: ProjectStatus enum | A) Type | A1) PLANNED | ProjectStatus.PLANNED  |
|  |  | A2) SUSPENDED | ProjectStatus.PLANNED  |
|  |  | `A3) ACTIVE` | ProjectStatus.ACTIVE  |
|  |  | A4) FINISHED | ProjectStatus.FINISHED  |
|  |  | A5) Null | null  |


3. BCC Table:

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | project.status == ProjectStatus.PLANNED | setAndGetStatus() |
| T1 | A2 | project.status == ProjectStatus.SUSPENDED | setAndGetStatus() |
| T1 | `A3` | project.status == ProjectStatus.ACTIVE | setAndGetStatus() |
| T1 | A4 | project.status == ProjectStatus.FINISHED | setAndGetStatus() |
| T1 | A5 | Exception | setAndGetStatus() |

---------------------------------------------------------
1. Method `toString(): String`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| `name: String` | A) Project Name Validity | A1) `null` | `null` |
|  |  | A2) `length = 0` | `""` (Empty String) |
|  |  | A3) `length > 0` | `"CS5Anniv"` |
| `workers: Set<Worker>` | B) Number of Workers | B1) `0 workers` | `{}` (Empty Set) |
|  |  | B2) `1 worker` | `{Worker("Alan")}` |
|  |  | B3) `Multiple workers` | `{Worker("Alice"), Worker("Dan"), ..., Worker("Zara")}` |
| `status: ProjectStatus` | C) Project Status Enum | C1) `PLANNED` | `ProjectStatus.PLANNED` |
|  |  | C2)`SUSPENDED` | `ProjectStatus.SUSPENDED` |
|  |  | C3)  `ACTIVE` | `ProjectStatus.ACTIVE`  |
|  |  | C4) `FINISHED` | `ProjectStatus.FINISHED` |

### 3. BCC Table:

| Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T3 | A3 | B1 | Pass | testToString_EmptyProject() |
| T4 | A3 | B2 | Pass | testToString_ProjectWithWorkers() |
| T5 | A3 | B1 | C3 | Pass | testToString_ActiveProject() |
| T6 | A3 | B1 | C4 | Pass | testToString_FinishedProject() |

---------------------------------------------------------
1. Method `addWorker(w: Worker): void`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| status: ProjectStatus enum | A) identity | A1) Worker already on project | Worker already on project |
|  |  | `A2) Worker not on project` | Worker not on project  |
| sw: Set< Worker > | B) set exists | `B1) set is of type Set<Worker>` | set of type Set< Worker > |
|  |  | B2) Null | null  |
| w: Worker | C) worker type | `C1) worker is Worker object` | Worker object |
|  |  | C2) worker is null | null |
|  |  | C3) worker is a non-Worker object | String s = “notAWorker” |


3. BCC Table:

| Tests |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 | A2 | B1 | C1 | project.getWorkers(w) returns set containing worker passed into method | addWorkerBaseChoice01() |
| T2 | `A1` | B1 | C1 | project.getWorkers(w) returns set containing worker passed into method | addWorkerAlreadyOnProject02() |
| T3 | A2 | `B2` | C1 | Exception | Tested by Project Constructor |
| T4 | A2 | B1 | `C2` | Exception | addWorkerNull04() |
| T5 | A2 | B1 | `C3` | Exception | Tested by Project Constructor |


---------------------------------------------------------
1. Method `removeWorker(w: Worker): void`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| status: ProjectStatus enum | A) identity | `A1) Worker on project` | Worker on project |
|  |  | A2) Worker not on project | Worker not on project  |
| sw: Set< Worker > | B) set exists | `B1) set is of type Set<Worker>` | set of type Set< Worker > |
|  |  | B2) Null | null  |
| w: Worker | C) worker type | `C1) worker is Worker object` | Worker object |
|  |  | C2) worker is null | null |
|  |  | C3) worker is a non-Worker object | String s = “notAWorker” |


3. BCC Table:

| Tests |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 | A1 | B1 | C1 | project.getWorkers(w) returns set without worker passed into method | removeWorkerBaseChoice01() |
| T2 | `A2` | B1 | C1 | project.getWorkers(w) returns set without worker passed into method | removeWorkerNotOnProject02() |
| T3 | A1 | `B2` | C1 | Exception | Tested by Project Constructor |
| T4 | A1 | B1 | `C2` | Exception | removeWorkerNull04() |
| T5 | A1 | B1 | `C3` | Exception | Tested by Project Constructor |

---------------------------------------------------------
1. Method `getWorkers(): Set< Worker >`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| status: ProjectStatus enum | A) Type | A1) null | null |
|  |  | `A2) Set< Worker >` | Set<Worker> |
|  |  | A3) Object not of Set<Worker> type | s = “NotAWorkerSet” |


3. BCC Table:

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Exception | Tested by Project Constructor |
| T1 | `A2` | Worker set from method contains all workers previously added to project | getWorkersBaseChoice01() |
| T1 | A3 | Exception | Tested by Project Constructor |


---------------------------------------------------------
1. Method `removeAllWorkers(): void`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| status: ProjectStatus enum | A) size | A1) empty | Empty Set< Worker > |
|  |  | `A2) some workers in set` | Set<Worker> with 5 workers |


3. BCC Table:

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Project’s set of workers is empty after execution | removeAllWorkersNoWorkers01() |
| T1 | `A2` | Project’s set of workers is empty after execution | removeAllWorkersSomeWorkers02() |


---------------------------------------------------------
1. Method `getRequiredQualifications(): Set<Qualification>`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| Set< Qualification > | A) Size | A1) Empty | Empty Set |
| | | `A2) > 0` | Set with 5 elements |

3. BCC Table:

|  Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | `A2` | Set with all qualifications that were present in project | getRequiredQualifications01() |
| T2 | A1 | Empty set / no qualifications | getRequiredQualificationsEmpty02() |


---------------------------------------------------------
1. Method: `addQualification(q: Qualification): void`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| q: Qualification | A) Type | `A1) = Qualification Object` | Qualification object |
| | | A2) != Qualification Object | Object object |
| | | A3) = null | null |
| qs: Set < Qualification > | B) Duplicates | `B1) No duplicates` | Add two different qualifications |
| | | B2) Duplicates | Add two of the same qualification |
|  | C) Size | C1) No qualifications | Add no qualifications |
| | | `C2) Some qualifications` | Add three qualifications | 

3. BCC Table:

|  Tests |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 | `A1` | `B1` | `C2` | Qualification set contains all qualifications | addQualificationBaseChoice01() |
| T2 | A2 | `B1` | `C2` | Exception | Checked by constructor |
| T3 | A3 | `B1` | `C2` | Exception | addQualificationNull03() |
| T4 | `A1` | B2 | `C2` | Only one qualification present in set | addQualificationNoDuplicates04() |
| T5 | `A1` | `B1` | C1 | Qualification set is empty | Checked by constructor |

---------------------------------------------------------
1. Method: `getMissingQualifications(): Set<Qualification>`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| Set<Qualification> | A) Size | A1) Empty | Empty Set |
| | | `A2) > 0 && <= 50` | Set with 50 elements |
| | | A3) > 50 | Set with more than 100 elements |
| | B) Type | B1) = null | null |
| | | `B2) = Qualification Object` | Qualification Object |
| | | B3) != Qualification object | Object object |

3. BCC Table:

|  Tests |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 | `A2` | `B2` | Pass | testGetMissingQualifications() |
| T2 | A1 | `B2` | Pass | testGetMissingQualificationsEmptySet() |
| T3 | A3 | `B2` | Pass | testGetMissingQualificationsLarge() |
| T4 | `A2` | B1 | Fail | N/A |
| T5 | `A2` | B3 | Fail | N/A |

---------------------------------------------------------
1. Method: `isHelpful(w: Worker): boolean`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| w: Worker | A) Type | `A1) = Worker object` | Worker Object |
| | | A2) = Object object | Object object|
| | | A3) = null | null |
| | B) Helpfulness | `B1) = Helpful worker` | Worker has qualification missing in project |
| | | B2) = Unhelpful worker | Worker does not have a qualification missing in project |
| missingQualifications: Set < Qualification > | C) size | C1) Empty | No missing qualifications |
| | | `C2) Some missing qualifications` | Some missing qualifications |

3. BCC Table:

|  Tests |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 | `A1` | `B1` | `C2` | True | isHelpfulHelpfulWorker01() |
| T2 | A2 | `B1` | `C2` | Exception | Tested by constructor |
| T3 | A3 | `B1` | `C2` | Exception | isHelpfulNullWorker03() |
| T4 | `A1` | B2 | `C2` | False | isHelpfulUnhelpfulWorker04() |
| T5 | `A1` | `B1` | C1 | False | isHelpfulNoMissingQualifications05() |

---------------------------------------------------------
1. Method: `toDTO(): ProjectDTO`
2. ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| Set<Worker> | A) Workers names present | `A1) = true` | All names present |
| | | A2) = False | Not all names present | 

3. BCC Table:
Singular test checks for DTO matching the project state: toDTO()

---------------------------------------------------------

# `Company` Class Methods:
  

1. Method: `Company(name: String): constructor`

2. ISP Table:

| **Variable**   | **Characteristic** | **Partition**       | **Value**               |
|:---------------|:-------------------|:--------------------|:------------------------|
| name: String   | Vaildity           | A1) = 0 (empty)     | `""` (empty string)      |
|                |                    | A2) >0 (non empty)  | `"CompanyX"` (length 9)  |
|                |                    | A3) Whitespace         |  " "              |
|                |                    | A4) null         |  null             |

3. BCC Table:

| **Tests** |  | **Oracle**                           | Test Name |
|:----------|:---------------------|:-------------------------------------|:---------------------|
| T1 (Base) | A2 (`"CompanyX"`)    | **Pass**: Valid company created.    | testValidName() |
| T2        | A1 (=0 empty)        | **Fail**: Empty name → Exception.    | testEmptyName() |
| T3        | A3 (invaild)         | **Fail**: " " → Exception.       | testWhitespaceName() |
| T4        | A3 (invaild)         | **Fail**: null → Exception.       | testNullName() |

---------------------------------------------------------

1. Method: `equals(o: Object): boolean`

2. ISP Table:

| **Variable** | **Characteristic** | **Partition**        | **Value**                             |
|:-------------|:-------------------|:---------------------|:--------------------------------------|
| o: Object    | Type               | B1) Null             | `null`                               |
|              |                    | B2) Non-Company      | `"NotACompany"` (type `String`)       |
|              |                    | B3) Company object   | `new Company("CompanyX")`            |
| name: String | Name Equality      | C1) Names match      | `"CompanyX"`                         |
|              |                    | C2) Names differ     | `"AnotherCompany"`                    |

3. BCC Table:

| **Tests** |  | **Oracle**                         |
|:----------|:---------------------|:------------------------------|
| T1 (Base) |D3 (Company with matching name)| **true** (same name → equal).  |
| T2        | D1 (Null)             | **false** (null → not equal).   |
| T3        | D2 (Non-Company)      | **false** (wrong type → not equal). |
| T4        | D3 (Company)          | **false** (different name → not equal).|


---------------------------------------------------------
 1. Method: `toString() : String`
 2.  ISP Table:

 | Variable | Characteristic | Partition | Value |
 |:---|:---|:---|:---|
 | name: String | length of company name | A1) name.len <= 0 | name = "" |
 |  |  | A2) name.len > 0 | "Test Company" |
 | availableWorkers: Set<Worker> | amount of workers | B1) availableWorkers.count =< 0 | availableWorkers.count = 0 |
 |  |  | B2) 0 < availableWorkers.count < 1000 | availableWorkers.count = 1 |
 |  |  | B3) availableWorkers.count >= 1000 | availableWorkers.count = 1005 |
 | projectsCarriedOut: Set<Project> | amount of projects | C1) projectsCarriedOut.count =< 0 | projectsCarriedOut.count = 0 |
 |  |  | C2) 0 < projectsCarriedOut.count < 1000 | projectsCarriedOut.count = 1 |
 |  |  | C3) projectsCarriedOut.count >= 1000 | projectsCarriedOut.count = 1005 |

 3.  BCC Table:

 | Tests |  |  |  | Oracle | Test Name |
 |:---|:---|:---|:---|:---|:---|
 | T1 (Base test) | A2 | B3 | C3 | Pass | toStringTest01() |
 | T2 | A1 | B3 | C3 | Pass | toStringTest02() |
 | T3 | A2 | B1 | C3 | Pass | toStringTest03() |
 | T4 | A2 | B2 | C3 | Pass | toStringTest04() |
 | T5 | A2 | B3 | C1 | Pass | toStringTest05() |
 | T6 | A2 | B3 | C2 | Pass | toStringTest06() |

 ---------------------------------------------------------
 1. Method: `getName(): String`
 2.  ISP Table:

 | Variable | Characteristic | Partition | Value |
 |:---|:---|:---|:---|
 | name: String | length of name | name.len <= 0 | name = "" |
 |  |  | 'name.len > 0' | name = "Test Company" |
 |  |  | name is null | name = null |

 3.  BCC Table:

| Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Fail | getNameTest01() |
| T2 | `A2` | Pass | getNameTest02() |
| T3 | A3 | Fail | getNameTest03() |


 ---------------------------------------------------------
 1. Method: `getEmployedWorkers(): Set<Worker>`
 2.  ISP Table:

 | Variable | Characteristic | Partition | Value |
 |:---|:---|:---|:---|
 | employedWorkers: Set<Worker> | number of workers | employedWorkers.count <= 0 | employedWorkers = {} |
 |  |  | employedWorkers.count = 1 | employedWorkers = {"Mark"} (use worker object) |
 |  |  | employedWorkers.count > 1 | employedWorkers = {"Mark", "Joe", "Bill"} (use worker objects) |

 3.  BCC Table:

 | Tests |  | Oracle | Test Name |
|:---|:---|:---|:---|
| T1 | A1 | Pass | getEmployedWorkersTest01() |
| T2 | `A2` | Pass | getEmployedWorkersTest02() |
| T3 | A3 | Pass | testGetEmployedWorkers_MultipleWorkers() |


 ---------------------------------------------------------
 1. Method: `getAvailableWorkers(): Set<Worker>`
 2.  ISP Table:

 | Variable | Characteristic | Partition | Value | Test Name |
 |:---|:---|:---|:---|:---|
 | availableWorkers: Set<Worker> | number of workers | availableWorkers.count <= 0 | availableWorkers = {} | getAvailableWorkerstest01() |
 |  |  | availableWorkers.count = 1 | availableWorkers = {"Mark"} (use worker object) | getAvailableWorkerstest02() |
 |  |  | availableWorkers.count > 1 | availableWorkers = {"Mark", "Joe", "Bill"} (use worker objects) | getAvailableWorkers03() |


 3.  BCC Table:

 ---------------------------------------------------------
 1. Method: `getUnavailableWorkers(): Set<Worker>`
 2.  ISP Table:

 | Variable | Characteristic | Partition | Value | Test Name |
 |:---|:---|:---|:---|:---|
 | unavailableWorkers: Set<Worker> | number of workers | unavailableWorkers.count <= 0 | unavailableWorkers = {} | getUnavailableWorkersTest01() |
 |  |  | unavailableWorkers.count = 1 | unavailableWorkers = {"Mark"} (use worker object) | getUnavailableWorkersTest02() |
 |  |  | unavailableWorkers.count > 1 | unavailableWorkers = {"Mark", "Joe", "Bill"} (use worker objects) | getUnavailableWorkersTest03() |

 3.  BCC Table:
---------------------------------------------------------
 1. Method: `getUnassignedWorkers(): Set<Worker>`
 2.  ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
| `employees: Set<Worker>` | Number of employees | A1) 0 employees | `{}` (empty set) |
|  |  | A2) 1 employee | `{Cole}` |
|  |  | A3) Multiple employees | `{"Cole", "Anna", "Jay"}` |
| `assignedWorkers: Set<Worker>` | Number of assigned workers | B1) 0 workers | `{}` (empty set) |
|  |  | B2) 1 worker assigned | `{"Cole"}` |
|  |  | B3) Multiple workers assigned | `{"Cole", "Anna"}` |


 3.  BCC Table:


| Tests |  |  | Oracle | Pass/Fail | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 (Base test) | A1 | B1 | `{}` (empty set) | Pass | testGetUnassignedWorkers_EmptyCompany() |
| T2 | A2 | B1 | `{"Cole"}` | Pass | testGetUnassignedWorkers_AllUnassigned() |
| T3 | A2 | B2 | `{}` (empty set) | Pass | testGetUnassignedWorkers_SomeAssigned() |
| T4 | A3 | B1 | `{"Cole", "Anna", "Jay"}` | Pass | testGetUnassignedWorkers_AllAssigned() |
| T5 | A3 | B3 | `{"Jay"}` (remaining unassigned) | Pass |  |


 ---------------------------------------------------------
 1. Method: `getProjects(): Set(Project)`
 2. ISP Table:
  
 | Variable | Characteristic | Partition | Value |
 |:---|:---|:---|:---|
 | ps: Set<Project> | A) type | `A1) Project object` | Project object |
 |  |  | A2) Null | Null |
 |  | B) size | B1) Empty | Empty set |
 |  |  | `B2) > 0 && <= 10` | Set with 10 elements |
 |  |  | B3) > 10 | Set with 15 elements |
  
 3. BCC Table:

| Tests          |   |   | Oracle | Test Name            |
|:--------------|:--|:--|:-------|:---------------------|
| T1 (Base test) | A1 | B2 | Pass  | `getProjectTest01()` |
| T2            | A2 | B2 | Fail  | `N/A`                |
| T3            | A1 | B1 | Pass  | `getProjectTest02()` |
| T4            | A1 | B3 | Pass  | `getProjectTest03()` |

---------------------------------------------------------

 1. Method: `createWorker(name: String, qs: Set<Qualification>, salary: double): Worker`
 2. ISP Table:

| **Variable**    | **Characteristic**       | **Partition**                | **Value**                  |
|----------------|-------------------------|-----------------------------|----------------------------|
| `name: String` | Validity                 | A1) Empty string            | `""` (empty string)        |
|                |                          | A2) Valid name              | `"Elon "`               |
|                |                          | A3) Null                    | `null`                     |
| `qs: Set<Qualification>` | Number of qualifications | B1) 0 qualifications  | `{}` (empty set)           |
|                                |                          | B2) 1 qualification      | `{qualification1}`         |
|                                |                          | B3) Multiple qualifications | `{qualification1, qualification2}` |
| `salary: double` | Salary range            | C1) Negative salary        | `-5000.0`                  |
|                 |                          | C2) Zero salary            | `0.0`                      |
|                 |                          | C3) Positive salary        | `50000.0`                  |

 3.  BCC Table:

| Tests |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 (Base test) | A2 | B3 | C3 | Pass | testCreateWorker_ValidWorker() |
| T2 | A1 | B2 | C3 | Fail | testCreateWorker_InvalidName() |
| T3 | A3 | B2 | C3 | Fail | testCreateWorker_NullName() |
| T4 | A2 | B1 | C3 | Fail | testCreateWorker_NoQualifications() |
| T5 | A2 | B2 | C1 | Fail | testCreateWorker_NegativeSalary() |

---------------------------------------------------------
 1. Method: `getAssignedWorkers(): Set<Worker>`
 2. ISP Table:

 | Variable | Characteristic | Partition | Value |
 |:---|:---|:---|:---|
 | unavailableWorkers: Set<Worker> | number of workers | unavailableWorkers.count <= 0 | unavailableWorkers = {} |
 |  |  | unavailableWorkers.count = 1 | unavailableWorkers = {"Mark"} (use worker object) |
 |  |  | unavailableWorkers.count > 1 | unavailableWorkers = {"Mark", "Joe", "Bill"} (use worker objects) |

 3. BCC Table:
 
 | Tests | Oracle | Test Name |
 |:---|:---|:---|
 | T1 (Base test) | getAssignedWorkersTest02() |
 | T2 | getAssignedWorkersTest01() |
 | T3 | getAssignedWorkersTest03() | 

---------------------------------------------------------

1. Method `getQualifications(): Set<Qualification>`
2. ISP Table:

 | Variable | Characteristic | Partition | Value |
 |:---|:---|:---|:---|
 | qualifications: Set<Qualification> | Number of qualifications | A1) 0 qualifications | `{}` (empty set) |
 |  |  | A2) 1 qualification | `{qualification1}` |
 |  |  | A3) Multiple qualifications | `{qualification1, qualification2, qualification3}` |

3. BCC Table:

 | Tests |  | Oracle | Test Name |
 |:---|:---|:---|:---|
 | T1 (Base test) | A1 | Pass | testGetQualifications_NoQualificationsTest01() |
 | T2 | A2 | Pass | testGetQualifications_OneQualificationTest02() |
 | T3 | A3 | Pass | testGetQualifications_MultipleQualificationsTest03() |


---------------------------------------------------------
1. Method: `createQualification(description: String)`
2.  ISP Table:
   
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
|  description: String |  A) length |  A1) length = 0 | description.len = 0 |
|  |  | ‘A2) length > 0 && length <= 30’  | description.len = 16 |
|  |  | A3) length > 30 | description.len = 50 |
|  | B) nullness | 'B1) not null’  | description = "Test Description" |
|  |  | B2) null  | description = null |
| qualifications: Set<>| C) nullness | 'C1) not null' | qualifications = null |
|  |  | 'C2) null' | qualifications = null |
|  | D) contents | D1) has same qualification | qualifications = {"Test Description"} |
|  |  | 'D2) doesnt have same qualification' | qualifications = {"Designer"}  |
|  |  | D3) doesnt have any qualifications | qualifications = {}  |
|  | E) size | E1) qualifications.size <= 0 | size = 0 |
|  |  | 'E2) 0 < qualifications.size <= 20' | size = 10 |
|  |  | E3)  qualifications.size > 20 | size = 100 |

3.  BCC Table:
   
|  Tests |  |  |  |  |  | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|:---|:---|
| T1 (Base test) | `A2` | `B1` | `C1` | `D2` | `E2` | Pass | createQualificationTest01() |
| T2 | A1 | `B1` | `C1` | `D2` | `E2` | Fail | createQualificationTest02() |
| T3 | A3 | `B1` | `C1` | `D2` | `E2` | Pass | createQualificationTest03() |
| T4 | `A2` | B2 | `C1` | `D2` | `E2` | Fail | createQualificationTest04() |
| T5 | `A2` | `B1` | C2 | `D2` | `E2` | Pass | createQualificationTest05() |
| T5 | `A2` | `B1` | `C1` | D1 | `E2` | Pass | createQualificationTest06() |
| T4 | `A2` | `B1` | `C1` | D3 | `E2` | Pass | createQualificationTest07() |
| T4 | `A2` | `B1` | `C1` | `D2` | E1 | Pass | createQualificationTest08() |
| T4 | `A2` | `B1` | `C1` | `D2` | E3 | Pass | createQualificationTest09() |






---------------------------------------------------------
1. Method: `createProject(name: String, qs: Set(Qualification), size: ProjectSize): Project`
2.  ISP Table:
   
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
|  name: String |  A) length |  A1) length = 0 | String of length 0 |
|  |  | ‘A2) length > 0 && length <= 30’  | String of length 30 |
|  |  | A3) length > 30 | String of length 50 |
| qs: Set<Qualification> | B) type | B1) Null object | Null |
|   |   | ‘B2) Qualification object’ | Qualification object  |
|   |   | B3) Object | new Object() |
| this.qualifications: Qualification | C) Company qualifications | ‘C1) company has all qualifications’ | company.qualifications = qs  |
|   |   | C2) company contains some qualifications | company.qualifications != qualifications|
|   |   | C3) company contains no qualifications | ct  |
| | D) size | D1) size = 0 | Empty set |
| | | ‘D2) size > 0 && size < =50’ | Set with 50 elements |
| | | D3) size >  50 | Set with 75 elements |
| size: ProjectSize | E) Enum ProjectSize | E1) Small | ProjectSize.SMALL |
| | | ‘E2) Medium’ | ProjectSize.MEDIUM |
| | | E3) Big | ProjectSize.BIG | 

3.  BCC Table:
   
|  Tests |  |  |  |  |  | Oracle | Test Name | 
|:---|:---|:---|:---|:---|:---|:---|:---|
| T1 (Base test) | A2 | B2 | C1 | D2 | E2 | Pass | createProjectTest01() |
| T2 | A1 | B2 | C1 | D2 | E2 | Fail | createProjectTest02() |
| T3 | A3 | B2 | C1 | D2 | E2 | Pass | createProjectTest03() |
| T4 | A2 | B1 | C1 | D2 | E2 | Fail | createProjectTest04() |
| T5 | A2 | B3 | C1 | D2 | E2 | Fail | createProjectTest05() |
| T6 | A2 | B2 | C2 | D2 | E2 | Pass | createProjectTest06() |
| T7 | A2 | B2 | C3 | D2 | E2 | Pass | createProjectTest07() |
| T8 | A2 | B2 | C1 | D1 | E2 | Pass | createProjectTest08() |
| T9 | A2 | B2 | C1 | D3 | E2 | Pass | createProjectTest09() |
| T10 | A2 | B2 | C1 | D2 | E1 | Pass | createProjectTest10() |
| T11 | A2 | B2 | C1 | D2 | E3 | Pass | createProjectTest11() |

---------------------------------------------------------
1. Method: `start(p : Project): void`
2.  ISP Table:
   
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
|  p: Project | A) type | A1) Null object | Null |
|  |  |  ‘A2) Project object’ | Project object |
|  | B) Enum Status | ‘B1) Planned’ | ProjectStatus.PLANNED |
|  |  | B2) Suspended | ProjectStatus.SUSPENDED |
|  |  | B3) Active | ProjectStatus.ACTIVE |
|  |  | B4) Finished | ProjectStatus.FINISHED |
|  | C) Remaining qualifications | 'C1) 0 remaining' | Empty set returned from getMissingQualifications() |
|  |  |  C2) Qualifications > 0 && <= 10 | Set of 10 missing qualifications |
|  |  |  C3) Qualifications > 10 | Set of 50 missing qualifications |

3.  BCC Table:

|  Tests | | | | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 (Base test) | A2 | B1 | C1 | Pass | testStartValidInput() |
| T2 | A1 | B1 | C1 | Fail | testStartNull() |
| T3 | A2 | B2 | C1 | Pass | testStartSuspended() |
| T4 | A2 | B3 | C1 | Fail | testStartActive() |
| T5 | A2 | B1 | C2 | Fail | testStartMissingSomeQualifications() |
| T6 | A2 | B2 | C3 | Fail | testStartMissingLargeQualifications() |
| T7 | A2 | B4 | C1 | Fail | testStartFinished() |


---------------------------------------------------------
1. Method: `finish(p : Project): void`
2.  ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
|  p: Project | A) type | A1) Null object | Null |
|  |  |  ‘A2) Project object’ | Project object |
|  |  | A3) Object | new Object() |
|  | B) Enum Status | ‘B1) Planned’ | ProjectStatus.PLANNED |
|  |  | B2) Suspended | ProjectStatus.SUSPENDED |
|  |  | B3) Active | ProjectStatus.ACTIVE |

3.  BCC Table:
   
|  Tests | | | Oracle | Test Name |
|:---|:---|:---|:---|:---|
| T1 (Base test) | A2 | B1 | ProjectStatus == planned, contains workers | finishPlanned01() |
| T2 | A1 | B1 | Exception | finishNullProject02() |
| T3 | A3 | B1 | Handled by constructor |  |
| T4 | A2 | B2 | ProjectStatus == suspended, contains workers | finishSuspended04() |
| T5 | A2 | B3 | ProjectStatus == active, active workers | finishActive05() |

---------------------------------------------------------
1. Method: `assign(w : Worker, p: Project): void`
2.  ISP Table:
   
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
|  w: Worker | A) type |  A1) Null object | Null |
| | | 'A2) Worker object' | Worker object |
| | | A3) Object | new Object() |
| | B) Is available | B1) Worker is not available | isAvailable = False |
| | | 'B2) Worker is available' | isAvailable = True |
| | C) Is assigned | C1) Worker is assigned | projects.contains(project) |
| | | 'C2) Worker is not assigned' | !project.contains(project) |
| | D) Will overload | D1) Worker will overload | willOverload = True |
| | | 'D2) Worker will not overload' | willOverload = False |
| | E) Is helpful | 'E1) Worker is helpful' | isHelpful = True |
| | | E2) Worker is not helpful | isHelpful = False |
| | H) Is employed | H1) Worker is not an employee | !Employed.contains(worker) |
| | | 'H2) Worker is an employee' | Employed.contains(worker) |
|  p: Project | F) type | F1) Null object | Null |
|  |  |  ‘F2) Project object’ | Project object |
|  |  | F3) Object | new Object() |
|  | G) Enum Status | ‘G1) Planned’ | ProjectStatus.PLANNED |
|  |  | G2) Suspended | ProjectStatus.SUSPENDED |
|  |  | G3) Active | ProjectStatus.ACTIVE |
|  |  | G4) Finished | ProjectStatus.FINISHED |

3.  BCC Table:

| Tests | | | | | | | | | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|:---|:---|:---|:---|:---|
| T1 (Base test) | A2 | B2 | C2 | D2 | E1 | F2 | G1 | H2 | Pass | testAssignValidInput() |
| T2 | A1 | B2 | C2 | D2 | E1 | F2 | G1 | H2 | Fail | testAssignNullWorker() |
| T3 | A3 | B2 | C2 | D2 | E1 | F2 | G1 | H2 | Fail | N/A |
| T4 | A2 | B1 | C2 | D2 | E1 | F2 | G1 | H2 | Fail | testAssignNotAvailable() |
| T5 | A2 | B2 | C1 | D2 | E1 | F2 | G1 | H2 | Fail | testAssignWorkerAssigned() |
| T6 | A2 | B2 | C2 | D1 | E1 | F2 | G1 | H2 | Fail | testAssignWorkerOverload() |
| T7 | A2 | B2 | C2 | D2 | E2 | F2 | G1 | H2 | Fail | testAssignNotHelpful() |
| T8 | A2 | B2 | C2 | D2 | E1 | F1 | G1 | H2 | Fail | testAssignNullProject() |
| T9 | A2 | B2 | C2 | D2 | E1 | F3 | G1 | H2 | Fail | N/A |
| T10 | A2 | B2 | C2 | D2 | E1 | F2 | G2 | H2 | Pass | testAssignStatusSuspended() |
| T11 | A2 | B2 | C2 | D2 | E1 | F2 | G3 | H2 | Fail | testAssignStatusActive() |
| T12 | A2 | B2 | C2 | D2 | E1 | F2 | G4 | H2 | Fail | testAssignNotFinished() |
| T13 | A2 | B2 | C2 | D2 | E1 | F2 | G4 | H1 | Fail | testAssignNotEmployee() |

---------------------------------------------------------
1. Method: `unassign(w: Worker, p: Project): void`
2.  ISP Table:
   
| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
|  w: Worker | A) name |  A1) name length = 0 | Empty String  |
|  |  |  ‘A2) name length > 0 && name length < 20’ | String of length 10 |
|  |  |  A3) name length >= 20 | String of length 20 |
| | B) Qualifications | B1) Empty set | Empty set |
| | | ‘B2) Qualifications > 0 && Qualifications < 10’ | Set of 5 qualifications |
| | | B3) Qualifications >= 10 | Set of 10 qualifications |
|  p: Project | C) type | C1) Null object | Null |
|  |  |  ‘C2) Project object’ | Project object |
|  |  | C3) Object | new Object() |
|  | D) Enum Status | D1) Planned | ProjectStatus.PLANNED |
|  |  | D2) Suspended | ProjectStatus.SUSPENDED |
|  |  | ‘D3) Active’ | ProjectStatus.ACTIVE |
|  |  | D4) Finished | ProjectStatus.FINISHED |

3.  BCC Table:
   
| Tests | | | | | Oracle | Test Results |
|:---|:---|:---|:---|:---|:---|:---|
| T1 (Base test) | A2 | B2 | C2 | D3 | Pass | unassignTest01() |
| T2 | A1 | B2 | C2 | D3 | Fail | N/A |
| T3 | A3 | B2 | C2 | D3 | Pass | unassignTest01() |
| T4 | A2 | B1 | C2 | D3 | Fail | unassignTest02() |
| T5 | A2 | B3 | C2 | D3 | Pass | unassignTest02() |
| T6 | A2 | B2 | C1 | D3 | Fail | unassignTest05() |
| T7 | A2 | B2 | C3 | D3 | Fail | unassignTest05() |
| T8 | A2 | B2 | C2 | D1 | Pass | unassignTest01() |
| T9 | A2 | B2 | C2 | D2 | Pass | unassignTest02() |
| T10 | A2 | B2 | C2 | D4 | Pass | N/A |

---------------------------------------------------------
1. Method: `unassignAll(w: Worker): void`
2.  ISP Table:

| Variable | Characteristic | Partition | Value |
|:---|:---|:---|:---|
|  w: Worker | A) name |  A1) name length = 0 | Empty String  |
|  |  |  ‘A2) name length > 0 && name length < 20’ | String of length 10 |
|  |  |  A3) name length >= 20 | String of length 20 |
| | B) Qualifications | B1) Empty set | Empty set |
| | | ‘B2) Qualifications > 0 && Qualifications < 10’ | Set of 5 qualifications |
| | | B3) Qualifications >= 10 | Set of 10 qualifications |
| ps: Set<Project> | C) size | C1) size = 0 | Set of 0 projects |
| | | ‘C2) size > 0 && size <= 10’  | Set of 5 projects |
| | | C3) size > 10 | Set of 11 projects | 

3.  BCC Table:
   
| Tests | | | | Oracle | Test Name |
|:---|:---|:---|:---|:---|:---|
| T1 (Best test) | A2 | B2 | C2 | Pass | unassignAllTest01() |
| T2 | A1 | B2 | C2 | Fail | unassignAllTest02() | 
| T3 | A3 | B2 | C2 | Pass | unassignAllTest01() |
| T4 | A2 | B1 | C2 | Pass | unassignAllTest04() |
| T5 | A2 | B3 | C2 | Pass | unassignAllTest04() |
| T6 | A2  | B2 | C1 | Fail | unassignAllTest03() |
| T7 | A2 | B2 | C3 | Pass | N/A |
