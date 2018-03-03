# FleetMaintenanceAlg
Sample algorithm for calculating fleet maintenance


**Problem**
-------------
You are given a []int scooters, which has as many elements as there are
districts in Berlin that Coup operates in. For each i, scooters[i] is the
number of scooters in that district (0-based index).
During a work day, scooters are maintained (batteries changed, cleaned,
checked for damages) by the Fleet Manager (FM) and possibly other Fleet
Engineers (FEs). Each FE, as well as the FM, can only maintain scooters in
one district. Additionally, there is a limit on how many scooters a single
FE may supervise: the FM is able to maintain up to C scooters, and a FE is
able to maintain up to P scooters. Each scooter has to be maintained by some FE or the FM.

_How do we solve the problem?_

Find the minimum number of FEs which are required to help the FM so that every scooter in each district of Berlin is maintained. Note that you may choose which district the FM should go to.

*Input / Output*
* As input you are given the []int scooters, int C and int P
* Result should be int - the minimum number of FEs which are required to help the FM

*Constraints*
* []scooters will contain between 1 and 100 elements. Each element in scooters will be between 0 and 1000. C will be between 1 and 999.
* P will be between 1 and 1000.

*Examples*
* 1) input:
{ scooters: [15, 10],
C: 12,
P:5 }

expected output:
{ fleet_engineers: 3 }
 
* 2) input:
{ scooters: [11, 15, 13],
C: 9,
P:5 }
expected output:
{ fleet_engineers: 7 }

Please create an application (CLI or HTTP API) which solves this problem. Create a git repository and share the code with us through github or as a tar.gz. You can choose any programming language you are familiar with; java or ruby are preferred, though.


**Solution**
-------------

**Line of thought**

 Algorithm :
 
 Since we have only one Fleet Manager (FM), it's important to make sure he will be working on a district which can best benefit from his capacity of maintainig vehicles.
 That means trying to maximize the number of vehicles a FM can work on in a certain district, so there is as least as possible work left for other FE in the same district.
 
     * E.g  vehicles [11, 15, 13], vehiclesMaintainedByFM : 9, vehiclesMaintainedByFE: 5
     *      It's best if FM works in district vehicles[0] which has 11 vehicles.
     *      vehicles[0] district: 11 - 9 (1 FM) = 2 vehicles left to be managed (requires 1 FE).
     *      vehicles[1] district: 15 - 9 (1 FM) = 6 vehicles left to be managed (requires 2 FE).
     *      vehicles[2] district: 13 - 9 (1 FM) = 4 vehicles left to be managed (requires 1 FE).
     *
     *      From the above you can see that the best option for FM is to go in district 0 or 2
 
 The solution below runs in O(n) time.


**Build & Run**
-------------

You can run the application in the following manners

1. via CLI application :

$ ./gradlew runApp

and enter the requested parameters 
E.g:
````
Please enter number of districts:
3
Please enter array elements:
11
15
13
Please enter the number of scooters managed by Fleet Manager:
9
Please enter the number of scooters managed by Fleet Engineer:
5
{fleet_engineers : 7}
````

2. via Webserver by issuing HTTP POST requests:

$ ./gradlew bootRun

In a separate shell:

$ curl -XPOST -H "Content-Type: application/json" -d '{"scooters":[11, 15, 13], "C":9, "P":5}' localhost:8080/calculateFleetEngineers




-----

You can also run the tests, by doing ./gradlew test