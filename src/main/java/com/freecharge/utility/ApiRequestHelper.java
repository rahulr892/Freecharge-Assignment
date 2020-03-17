package com.freecharge.utility;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class ApiRequestHelper {

    public String getRequest(String url) {
        RequestSpecification httpRequest = RestAssured.given();
        // Making GET request directly by RequestSpecification.get() method
        Response response = httpRequest.get(url);
        String body = response.getBody().asString();
        return body;
    }

    public String postRequest(String url, String paramaters) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject jsonObj = new JSONObject(paramaters);

        request.body(jsonObj.toString());
        Response response = request.post(url);

        int statusCode = response.getStatusCode();
        System.out.println("Returned Status Code : " + statusCode);

        return Integer.toString(statusCode);
    }

    public String putRequest(String url, String paramaters) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject jsonObj = new JSONObject(paramaters);

        request.body(jsonObj.toString());
        Response response = request.put(url);

        int statusCode = response.getStatusCode();
        System.out.println("Returned Status Code : " + statusCode);

        return Integer.toString(statusCode);
    }

    public String deleteRequest(String url) {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.delete(url);

        int statusCode = response.getStatusCode();
        System.out.println("Returned Status Code : " + statusCode);

        return Integer.toString(statusCode);
    }

}
