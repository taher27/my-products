// ********RoostGPT********
/*
Test generated by RoostGPT for test Test-report-rest-assured using AI Type Open AI and AI Model gpt-4

Test generated for /user/createWithArray_post for http method type POST in rest-assured framework

RoostTestHash=2fceff8367


*/

// ********RoostGPT********
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

public class userCreateWithArrayPostTest {

	List<Map<String, String>> envList = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		TestdataLoader dataloader = new TestdataLoader();
		String[] envVarsList = { "" };
		envList = dataloader.load("src\\test\\java\\com\\bootexample4\\RoostTest\\user_createWithArrayPostTest.csv",
				envVarsList);
	}

	@Test
    public void userCreateWithArrayPost_Test() throws JSONException {
        this.setUp();
        Integer testNumber = 1;
        for (Map<String, String> testData : envList) {
          RestAssured.baseURI = (testData.get("BASE_URL") != null && !testData.get("BASE_URL").isEmpty()) ? testData.get("BASE_URL"): "https://petstore.swagger.io/v2";

                Response responseObj = given()
				.header("body", testData.get("body") != null ? testData.get("body") : "")
                .when()
                .post("/user/createWithArray")
                .then()
                .extract().response();
              JsonPath response;
              String contentType = responseObj.getContentType();

              System.out.printf("Test Case %d: userCreateWithArrayPost_Test \n", testNumber++);
              System.out.println("Request: POST /user/createWithArray");
              System.out.println("Status Code: " + responseObj.statusCode());
              if (testData.get("statusCode") != null) {
                String statusCodeFromCSV = testData.get("statusCode");
                if (statusCodeFromCSV.contains("X")) {
                  MatcherAssert.assertThat(
                      "Expected a status code of category " + statusCodeFromCSV + ", but got "
                          + Integer.toString(responseObj.statusCode()) + " instead",
                      Integer.toString(responseObj.statusCode()).charAt(0), equalTo(statusCodeFromCSV.charAt(0)));
                } else {
                  MatcherAssert.assertThat(
                      Integer.toString(responseObj.statusCode()), equalTo(statusCodeFromCSV));
                }
              }
              				else {
      List<Integer> expectedStatusCodes = Arrays.asList(default);
				MatcherAssert.assertThat(responseObj.statusCode(), is(in(expectedStatusCodes)));
          }
				String stringifiedStatusCode = Integer.toString(responseObj.statusCode());
              if (contentType.contains("application/xml") || contentType.contains("text/xml")) {
                String xmlResponse = responseObj.asString();
                JSONObject jsonResponse = XML.toJSONObject(xmlResponse);
                JSONObject jsonData = jsonResponse.getJSONObject("xml");
                String jsonString = jsonData.toString();
                response = new JsonPath(jsonString);

              } else if(contentType.contains("application/json")){
                response = responseObj.jsonPath();
              } else {
                System.out.println("Response content type found: "+contentType+", but RoostGPT currently only supports the following response content types: application/json,text/xml,application/xml");
                continue;
              }

                if(stringifiedStatusCode.equals("default")){					System.out.println("Description: successful operation");
				}


            }
    }

}
