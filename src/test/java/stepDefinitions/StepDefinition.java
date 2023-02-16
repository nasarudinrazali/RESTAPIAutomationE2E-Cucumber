package stepDefinitions;

import static io.restassured.RestAssured.given;


import java.io.IOException;



import org.junit.Assert;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;

import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.Utils;

public class StepDefinition extends Utils {
	;
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	static String sessionId;
	static String issueKey;
	static String commentId;
	
	
	
	//Login API

	@Given("login payload with {string} {string}")
	public void login_payload_with(String username, String password) throws IOException {
			
		reqSpec=given().spec(requestSpecification())
		.body("{\r\n    \"username\": \""+username+"\",\r\n    \"password\": \""+password+"\"\r\n}");	
		
		
	}
	
				
	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		
		APIResources resourceDetail= new APIResources();
		
		System.out.println(resource);
		
		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(resource.equalsIgnoreCase("loginAPI"))
			response=reqSpec.when().post(resourceDetail.loginAPI());
		else if(resource.equalsIgnoreCase("createIssueAPI"))
			response=reqSpec.when().post(resourceDetail.createIssueAPI());
		else if(resource.equalsIgnoreCase("addCommentAPI"))
			response=reqSpec.when().post(resourceDetail.addCommentAPI().replace("issueKey", issueKey));
		else if(resource.equalsIgnoreCase("updateCommentAPI"))
		{
			String updateissueKey=resourceDetail.updateCommentAPI().replace("issueKey", issueKey);
			String newRes =updateissueKey.replace("commentId", commentId);
			response=reqSpec.when().put(newRes);
		}
		else if(resource.equalsIgnoreCase("deleteIssueAPI"))
		{
			response=reqSpec.when().delete(resourceDetail.deleteIssueAPI().replace("issueKey", issueKey));
		} 
		

	}

		
	@Then("API call is successful with status code {string}")
	public void api_call_is_successful_with_status_code(String expectedCode) { //The data type for the expectedCode here is String,so we have to convert it to Integer
																			//Because the other value that we want to compare is in Integer
																			//Use Integer.parseInt to convert from String to int
		
		Assert.assertEquals(response.getStatusCode(),Integer.parseInt(expectedCode));
		
	}

	@Then("sessionId is generated")
	public void session_id_is_generated() {
		String jsession=getJsonPath(response,"session.name");
		String Id=getJsonPath(response,"session.value");
		sessionId=jsession+"="+Id;
		System.out.println(sessionId);
		
		Assert.assertNotNull(sessionId); //To assert whether sessionId is present or not
	   
	}


	//Create Issue API
	@Given("create issue payload with {string} {string} {string} {string}")
	public void create_issue_payload_with(String key, String summary, String description, String issueType) throws IOException {
			
		reqSpec=given().spec(requestSpecification().header("Cookie", sessionId)
	.body("{\r\n    \"fields\": {\r\n       \"project\": \r\n       {\r\n\r\n          \"key\": \""+key+"\"\r\n       },\r\n       \"summary\": \""+summary+"\",\r\n       \"description\": \""+description+"\",\r\n       \"issuetype\": {\r\n          \"name\": \""+issueType+"\"\r\n       }\r\n   }\r\n}"));
	
	
	}
	

	@Then("issue key is generated")
	public void issue_key_is_generated() {
		
		
		issueKey=getJsonPath(response,"key");
		System.out.println(issueKey);
		Assert.assertNotNull(issueKey); 
			
	}
	
	 //Add comment to existing Issue  


	@Given("add Comment payload with {string} {string} {string}")
	public void add_comment_payload_with(String body, String type, String value) throws IOException {
	    
		
		reqSpec=given().spec(requestSpecification()).header("Cookie", sessionId)
				.body("{\n    \"body\": \""+body+"\",\n    \"visibility\": {\n        \"type\": \""+type+"\",\n        \"value\": \""+value+"\"\n    }\n}");
		
		
	}
	
	

	@Then("comment id is generated")
	public void comment_id_is_generated() {
	   
	    commentId=getJsonPath(response,"id");
		System.out.println(commentId);
		Assert.assertNotNull(commentId); 
	    
	}



	@Given("update comment Payload with {string}  {string} {string}")
	public void update_comment_payload_with(String body, String type, String value) throws IOException {
	    
	    
		reqSpec=given().spec(requestSpecification()).header("Cookie", sessionId)
				.body("{\n    \"body\": \""+body+"\",\n    \"visibility\": {\n        \"type\": \""+type+"\",\n        \"value\": \""+value+"\"\n    }\n}");
		
	    
	 
	}


	@Given("delete issue API")
	public void delete_issue_api() throws IOException {
	   
		reqSpec=given().spec(requestSpecification()).header("Cookie", sessionId);
	}



		
	  	    
	    
	




	








}
