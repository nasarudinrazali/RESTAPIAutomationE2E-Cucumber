package resources;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqSpec;

	public RequestSpecification requestSpecification() throws IOException
	{
		
		if(reqSpec == null) {
		
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		
		reqSpec=new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri(getGlobalValue("baseUri")).addHeader("content-type", "application/json")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setAccept(ContentType.JSON).build();	
		
		return reqSpec;
		
		}
		
		return reqSpec;
	}


	
	public String getGlobalValue(String baseUri) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\60113\\eclipse-workspace\\JIRA_API_Automation\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(baseUri);
	}
	
	
	public String getJsonPath(Response res, String extractedVal)
	{
		String respo=res.asString();
		JsonPath js=new JsonPath(respo);
		return js.get(extractedVal).toString();
		
	}

}