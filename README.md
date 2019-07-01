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

## Update: 27.06.2019

- for the jxbrowser it was necessary to get a licence renewal
- every jxbrowser file and the licence is in the lib folder now 

Usecases:

- create a report: click the dropdown of the runMain-button -> Run configuration -> choose tab Arguments
			-> type in "report" in the field below Program arguments -> click Run
			-> close application -> refresh the project in Eclipse -> Report.txt is created in the top project folder
			-> Reports can have a filename by typing in the field below Program arguments "report *filename*"
			-> there is a report in the program by default with the filename Report.txt

- to login HR users use following credentials:
	+ username: TestuserHR
	+ password: est
	- click 'Submit'	
	- HR View is opening 

- in order to create a new user type a 'username' and 'password' in the line 'Add user:', then click 'Add'
	- user is going to appear in the list below
	- user can be deleted by clicking on the 'Loeschen' button (DON´T delete Testuser and TestuserHR otherwise login is going to be impossible)
 	- to leave the HR view close the program

- to edit an order click 'Bearbeiten'; then same process as you would create a new order
	- 'Loeschen' button is working now after access by clicking on 'Neuer Auftrag' button or 'Bearbeiten' button ('Bearbeiten' button is only there if an order has already been created)



