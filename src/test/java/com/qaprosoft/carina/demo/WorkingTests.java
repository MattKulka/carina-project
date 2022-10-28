package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.api.GetReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.Assert;  
import java.lang.invoke.MethodHandles;
import java.util.Properties;
    
    public class WorkingTests implements IAbstractTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
        private static Properties localProperties;
    
        @Test(priority = 3)
        @MethodOwner(owner = "MattKulka")
        public void testGetReport() {
        GetReport api = new GetReport();
        api.replaceUrlPlaceholder("api_request", "lat=25&lon=25");
        api.callAPIExpectSuccess();
        api.validateResponseAgainstSchema("api/users/_get/rs.schema");
    }
    
        @Test(priority = 4)
        @MethodOwner(owner = "MattKulka")
        public void testGetWeatherReportNoGeocode() {
            GetReport api = new GetReport();
            api.replaceUrlPlaceholder("api_request", "null");
            String rs = api.callAPI().asString();
            api.expectResponseStatus(HttpResponseStatusType.BAD_REQUEST_400);
            Assert.assertTrue(rs.contains("Nothing to geocode"), "Message: Nothing to geocode");
    }

        @Test(priority = 2)
        @MethodOwner(owner = "MattKulka")
        public void testChangeMeasurementUnits() {
            GetReport call1 = new GetReport();
            call1.replaceUrlPlaceholder("api_request", "lat=10&lon=10&units=imperial");
            String imperial = call1.callAPIExpectSuccess().asString();
            GetReport call2 = new GetReport();
            call2.replaceUrlPlaceholder("api_request", "lat=10&lon=10&units=metric");
            String metric = call2.callAPIExpectSuccess().asString();
            Assert.assertNotEquals(imperial, metric);
        }

        @Test(priority = 1)
        @MethodOwner(owner = "MattKulka")
        public void testNoGeocode() {
            GetReport api = new GetReport();
            api.replaceUrlPlaceholder("api_request", "null");
            String rs = api.callAPI().asString();
            api.expectResponseStatus(HttpResponseStatusType.BAD_REQUEST_400);
            Assert.assertTrue(rs.contains("Nothing to geocode"), "Message: Nothing to geocode");
    }

    //     @Test(priority = 2)
    //     @MethodOwner(owner = "MattKulka")


    //     @Test(priority = 2)
    //     @MethodOwner(owner = "MattKulka")


    //     @Test(priority = 2)
    //     @MethodOwner(owner = "MattKulka")


    //     @Test(priority = 2)
    //     @MethodOwner(owner = "MattKulka")
    // }


}
    