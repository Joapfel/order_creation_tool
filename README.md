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
 

In order for the tests to work please to the following (description based on Eclipse):
- right click on the project -> Build Path -> Add Libraries... -> Auswahl JUnit -> JUnit5 (Current Location ...) -> Finish

## Notes

At the moment a little workaround is used to load new HTML views. Before a new view is loaded, the google search page will be loaded for a second. This can be ignored.
The reason is, that without this inbetween-load the application gets stuck. Since no support was found for this issue, a new StackOverflow post was created for that.
For more details of this problem see here: https://stackoverflow.com/questions/55929434/jxbrowser-timeoutexception-when-waiting-for-main-frame-to-load-invokeandwaitfin

