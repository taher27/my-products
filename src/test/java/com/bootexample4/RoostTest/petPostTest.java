


@Test
public void petPost_Test() throws JSONException {
    this.setUp();
    Integer testNumber = 1;
    for (Map<String, String> testData : envList) {
      RestAssured.baseURI = (testData.get("BASE_URL") != null && !testData.get("BASE_URL").isEmpty()) ? testData.get("BASE_URL"): "https://petstore.swagger.io/v2";

            // Removed 'undefined' and replaced it with a valid request specification
            Response responseObj = given()
            .contentType(ContentType.JSON)
            .when()
            .post("/pet")
            .then()
            .extract().response();
          JsonPath response;
          String contentType = responseObj.getContentType();

          System.out.printf("Test Case %d: petPost_Test \n", testNumber++);
          System.out.println("Request: POST /pet");
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
  List<Integer> expectedStatusCodes = Arrays.asList(405);
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

            if(stringifiedStatusCode.equals("405")){                  System.out.println("Description: Invalid input");
        }


        }
}

