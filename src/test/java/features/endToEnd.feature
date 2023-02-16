Feature: Validating Jira APIs E2E 

	
Scenario Outline: Verify user can successfully log in
	Given login payload with "<username>" "<password>" 
	When user calls "loginAPI" with "POST" HTTP request
	Then  API call is successful with status code "200"
	And sessionId is generated
	
Examples:
	| username                      | password    									|
	| Insert your Jira username here| Insert your Jira password here|

Scenario Outline: Verify issue is succesfully created
	Given create issue payload with "<key>" "<summary>" "<description>" "<issueType>" 
	When user calls "createIssueAPI" with "POST" HTTP request
	Then  API call is successful with status code "201"
	And issue key is generated
	
	
Examples:
	| key  |     summary        |     description        | issueType |
	| TES  | REST sample issue  |    Good morning mate   | Bug       |
	

Scenario Outline: Verify comment is added to existing issue
	Given add Comment payload with "<body>" "<type>" "<value>"
	When user calls "addCommentAPI" with "POST" HTTP request
	Then  API call is successful with status code "201"
	And comment id is generated
	
Examples:
	|	         body                  | type | value          |
	|Submit button cannot be clicked | role | Administrators |
	
	
Scenario Outline: Verify new comment is updated to existing issue
	Given update comment Payload with "<body>"  "<type>" "<value>"
	When user calls "updateCommentAPI" with "PUT" HTTP request
	Then API call is successful with status code "200"
	
Examples:
	|	         body                  | type | value          |
	|	update comment                 | role | Administrators |


Scenario Outline: Verify existing issue is successfully deleted
	Given delete issue API
	When user calls "deleteIssueAPI" with "POST" HTTP request
	Then  API call is successful with status code "204"

	
	
