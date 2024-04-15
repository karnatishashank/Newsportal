// FitnessCalculatorModel.java
package com.ksr.newsportal.core.models;

import com.ksr.newsportal.core.services.FitnessCalculatorService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.commons.json.JSONObject;

@Model(adaptables = SlingHttpServletRequest.class)
public class FitnessCalculatorModel {

    @SlingObject
    private SlingHttpServletRequest request;

    @OSGiService
    private FitnessCalculatorService fitnessCalculatorService;

    public JSONObject getApiData() {
        // Get user-provided parameters from request
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        double height = Double.parseDouble(request.getParameter("height"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        String activityLevel = request.getParameter("activitylevel");

        // Check if the data is already available in the request attribute
        JSONObject apiData = (JSONObject) request.getAttribute("apiData");

        // If not available, fetch data from the API using the service and user-provided parameters
        if (apiData == null) {
            apiData = fitnessCalculatorService.fetchDataFromApi(age, gender, height, weight, activityLevel);
        }

        return apiData;
    }
}
