# Project Name


## Build

`ant compile`

## Integrate / Jar

`ant jar`

## Test

`ant test`

## Dependencies

### Manual Dependencies

The following Projects/Libraries are included in this project:
- JxBrowser for Java based HTML Frontend handling: 
    https://www.teamdev.com/jxbrowser
- Console Launcher for JUnit tests:
	https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher 
	https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.1.1/
- Junit5

## How to run the application

Since the GitLab upload does not allow to upload files of bigger sizes (like the jxbrowser dependency) please make sure 

- to include these dependencies into the *lib* folder of the project
- to add the dependencies to the classpath
- the dependencies should be still available from the last submission (prototype)
- just to be sure we will also upload the project as a zip as last time
 

In order for the tests to work please to the following (description based on Eclipse):
- right click on the project -> Build Path -> Add Libraries... -> Auswahl JUnit -> JUnit5 (Current Location ...) -> Finish

Usecases:

- to login use the following credentials:
    + username: Testuser
    + password: est
- in order to create a new Order, first a new Customer needs to be created
    + the Customer will not be saved in the background (since this was not the task), but the customer will be added visually
    + then click on **Neuer Auftrag** in the Customers row -> the Order view will be loaded
    + here you can add new Materials, Machines, Working Hours and their respective costs.
    + if you click on **Speichern** the Order will be saved in the background
    + if you click on **Angebot ausformuliert** the costs will be inserted into a template
        - if you go back from here the saved Order will not be loaded back into the Order view (since this was not the task)
- for your convenience a couple of Customers and Materials,Machines,Working Hours will be there by default.

## Notes

At the moment a little workaround is used to load new HTML views. Before a new view is loaded, the google search page will be loaded for a second. This can be ignored.
The reason is, that without this inbetween-load the application gets stuck. Since no support was found for this issue, a new StackOverflow post was created for that.
For more details of this problem see here: https://stackoverflow.com/questions/55929434/jxbrowser-timeoutexception-when-waiting-for-main-frame-to-load-invokeandwaitfin

## Tests
- The PDF with the tests is included in the codebase as Abgabe_Übungsblatt3.pdf
- additionally it is also uploaded on Ilias

## Update Abgabe zum 27.06.2019

- for the jxbrowser it was necessary to get a licence renewal
- Following implementations are done:
	+ HR user front end (view)
	+ user storage
	+ order storage
	+ customer storage
	+ user class extension (role)
	+ login adjustment (role)
	+ report ouput
	+ saving the customer
	+ user storage tests


## Commit ID´s

27 Jun, 2019 2 commits

Marco Streich's avatar
Update HRView.java
Marco Streich authored 1 hour ago
43d3951c

Joapfel's avatar
existing customers are loaded into the interface
Joapfel authored 2 hours ago
6daf5132

26 Jun, 2019 1 commit

Marco Streich's avatar
Updates
Marco Streich authored 22 hours ago
0addb6f6

25 Jun, 2019 9 commits

PhilippTeichert's avatar
console-testing update
PhilippTeichert authored 1 day ago
af878805

PhilippTeichert's avatar
printing all orders
PhilippTeichert authored 1 day ago
236aef31

PhilippTeichert's avatar
saving Orders reworked 
PhilippTeichert authored 1 day ago
e1a4cdbb

PhilippTeichert's avatar
showcasing getCustomer
PhilippTeichert authored 1 day ago
079bd7d2

Joapfel's avatar
added (uncommented) save user
Joapfel authored 2 days ago
da59185a

Joapfel's avatar
hr login working; add user working
Joapfel authored 2 days ago
ca397b38

Joapfel's avatar
fix for no argument
Joapfel authored 2 days ago
6c1213a0

Joapfel's avatar
implemented management report (commandline tool)
Joapfel authored 2 days ago
eb1d1561

Marco Streich's avatar
Update HTMLFiles.java
Marco Streich authored 2 days ago
db57648d

24 Jun, 2019 1 commit

Marco Streich's avatar
Update HRView.java
Marco Streich authored 2 days ago
a10385a3

23 Jun, 2019 1 commit

Marco Streich's avatar
Create HRView.java
Marco Streich authored 3 days ago
8ebc7529

20 Jun, 2019 3 commits

PhilippTeichert's avatar
added getAllCustomers and a few lines of comments
PhilippTeichert authored 6 days ago
8bf2769b

PhilippTeichert's avatar
added getAllOrders()
PhilippTeichert authored 6 days ago
92be643e

vogelmae's avatar
modified test
vogelmae authored 1 week ago
a7fb663e

19 Jun, 2019 3 commits

Joapfel's avatar
fixed browser loading -> google in-between-loading not needed anymore
Joapfel authored 1 week ago
d9831a39

Joapfel's avatar
bug fix (delete machine and delete working hours was not possible); more Umlaute fixed
Joapfel authored 1 week ago
88f51aa9

Joapfel's avatar
Umlaute angepasst
Joapfel authored 1 week ago
9516b397

14 Jun, 2019 1 commit

Benjamin Weiß's avatar
HR Interface view hinzugefügt
Benjamin Weiß authored 1 week ago
ed25e78e

08 Jun, 2019 2 commits

vogelmae's avatar
Added some Tests.
vogelmae authored 2 weeks ago
b6db3eab

PhilippTeichert's avatar
Update user-structure and user-storage
PhilippTeichert authored 2 weeks ago
03efa13a

06 May, 2019 1 commit

PhilippTeichert's avatar
moving initialize() for singleton-pattern
PhilippTeichert authored 1 month ago
65af2af1
