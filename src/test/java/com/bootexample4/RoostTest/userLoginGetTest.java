

package com.bootexample4.RoostTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;
import org.json.XML;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.Arrays;

public class userLoginGetTest {

	List<Map<String, String>> envList = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		//Possible error: TestdataLoader class might not exist or not imported
		//Possible error: user_loginGetTest.csv file might not exist in the specified path
		TestdataLoader dataloader = new TestdataLoader();
		String[] envVarsList = { "" };
		envList = dataloader.load("src\\test\\java\\com\\bootexample4\\RoostTest\\user_loginGetTest.csv", envVarsList);
	}

	@Test
	public void userLoginGet_Test() throws JSONException {
		this.setUp();
		Integer testNumber = 1;
		for (Map<String, String> testData : envList) {
			RestAssured.baseURI = (testData.get("BASE_URL") != null && !testData.get("BASE_URL").isEmpty())
					? testData.get("BASE_URL") : "https://petstore.swagger.io/v2";

			//Possible error: Incorrect endpoint or request parameters
			Response responseObj = given()
				.queryParam("username", testData.get("username") != null ? testData.get("username") : "")
				.queryParam("password", testData.get("password") != null ? testData.get("password") : "")
				.when()
				.get("/user/login")
				.then()
				.extract()
				.response();
			JsonPath response;
			String contentType = responseObj.getContentType();

			//Possible error: The response content type might not be as expected
			if (contentType.contains("application/xml") || contentType.contains("text/xml")) {
				String xmlResponse = responseObj.asString();
				JSONObject jsonResponse = XML.toJSONObject(xmlResponse);
				JSONObject jsonData = jsonResponse.getJSONObject("xml");
				String jsonString = jsonData.toString();
				response = new JsonPath(jsonString);

			}
			else if (contentType.contains("application/json")) {
				response = responseObj.jsonPath();
			}
			else {
				System.out.println("Response content type found: " + contentType
						+ ", but RoostGPT currently only supports the following response content types: application/json,text/xml,application/xml");
				continue;
			}

			//Possible error: The status code might not be as expected
			if (contentType.contains("200")) {
				System.out.println("Description: successful operation");
			}
			if (contentType.contains("400")) {
				System.out.println("Description: Invalid username/password supplied");
			}

		}
	}

}
