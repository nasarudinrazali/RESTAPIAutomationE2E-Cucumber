package resources;

public class APIResources {

	String loginAPI = "/rest/auth/1/session";
	String createIssueAPI = "/rest/api/2/issue/";
	String addCommentAPI = "/rest/api/2/issue/issueKey/comment";
	String updateCommentAPI = "/rest/api/2/issue/issueKey/comment/commentId";
	String deleteIssueAPI = "/rest/api/2/issue/issueKey";

	
	
	
	public String loginAPI()
	{
		
		return loginAPI;
	}
	
	public String createIssueAPI()
	{
		
		return createIssueAPI;
	}

	public String addCommentAPI()
	{
		
		return addCommentAPI;
	}

	public String updateCommentAPI()
	{
		
		return updateCommentAPI;
	}

	public String deleteIssueAPI()
	{
		
		return deleteIssueAPI;
	}

	


}
