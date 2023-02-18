
**This API Automation Framework is created using Java, Gherkin, Junit and Cucumber. It also supports Cucumber Reporting. This framework is built to test Jira APIs.**

**There is an end to end test stored in this Framework. The test covers the login of Jira account > Creating an issue in Jira project > Add comment to the existing issue > Update comment to the existing issue > Delete the existing issue.**
.
You can view the test scenarios in .feature file.

**How to run the test:**

Precondition: Java and Maven have been installed in your system

1) Insert your Jira url in global.properties file and save
2) Insert your Jira username and password in Endtoend.feature file and save.
4) Open terminal
3) Go to the project directory in the terminal
5) Type "mvn test verify" and hit enter.
6) To view the test result, go to "target" folder to open Cucumber reports.






