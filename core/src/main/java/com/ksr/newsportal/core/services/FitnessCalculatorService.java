// FitnessCalculatorService.java
package com.ksr.newsportal.core.services;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

@Component(service = FitnessCalculatorService.class)
public class FitnessCalculatorService {

    private static final String API_URL = "https://rapidapi.com/malaaddincelik/api/fitness-calculator";

    public JSONObject fetchDataFromApi(int age, String gender, double height, double weight, String activityLevel) {
        try {
            // Modify the API URL to include user-provided parameters
            String apiUrlWithParams = API_URL + "?age=" + age + "&gender=" + gender +
                    "&height=" + height + "&weight=" + weight + "&activitylevel=" + activityLevel;

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(apiUrlWithParams);

            // Set headers if needed
            getRequest.setHeader("X-RapidAPI-Key", "YOUR_API_KEY");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() == 200) {
                String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
                return new JSONObject(jsonResponse);
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
