// FitnessCalculatorServlet.java
package com.ksr.newsportal.core.servlets;

import com.ksr.newsportal.core.services.FitnessCalculatorService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Component;

import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {javax.servlet.Servlet.class}, property = {"sling.servlet.paths=/bin/fitness-calculator"})
public class FitnessCalculatorServlet extends SlingSafeMethodsServlet {

    @OSGiService
    private FitnessCalculatorService fitnessCalculatorService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        // Get user-provided parameters
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        double height = Double.parseDouble(request.getParameter("height"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        String activityLevel = request.getParameter("activitylevel");

        // Fetch data from the API using the service and user-provided parameters
        JSONObject apiData = fitnessCalculatorService.fetchDataFromApi(age, gender, height, weight, activityLevel);

        // Set the API data as a request attribute
        request.setAttribute("apiData", apiData);
    }
}
