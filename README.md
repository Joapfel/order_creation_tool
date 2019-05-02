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
        - if you go back from here the saved Oder will not be loaded back into the Order view (since this was not the task)
## Notes

At the moment a little workaround is used to load new HTML views. Before a new view is loaded, the google search page will be loaded for a second. This can be ignored.
The reason is, that without this inbetween-load the application gets stuck. Since no support was found for this issue, a new StackOverflow post was created for that.
For more details of this problem see here: https://stackoverflow.com/questions/55929434/jxbrowser-timeoutexception-when-waiting-for-main-frame-to-load-invokeandwaitfin

