package stepdefs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class UserStepDefinitions {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;

	private String ENDPOINT_GET_USER_BY_ID = "https://reqres.in/api/users/2";


	@Given("User exists with an ID of (.*)")
	public void a_book_exists_with_isbn(String ID){
		request = given().param("q", "id:" + ID);
	}

	@When("User retrieves details by ID")
	public void a_user_retrieves_the_book_by_isbn(){
		response = request.when().get(ENDPOINT_GET_USER_BY_ID);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("The status code is (\\d+)")
	public void verify_status_code(int statusCode){
		json = response.then().statusCode(statusCode);
	}

	@And("Response includes the following$")
	public void response_equals(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), is(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), is(field.getValue()));
			}
		}
	}

	@And("Response includes the following in any order")
	public void response_contains_in_any_order(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), containsInAnyOrder(field.getValue()));
			}
		}
	}
}


