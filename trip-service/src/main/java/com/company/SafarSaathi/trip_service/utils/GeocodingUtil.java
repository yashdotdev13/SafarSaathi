package com.company.SafarSaathi.trip_service.utils;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class GeocodingUtil {

    @Value("${opencage.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.opencagedata.com/geocode/v1/json?q=%s&key=%s";

    public Coordinates getCoordinates(String location) {
        try {
            String url = String.format(BASE_URL, location.replace(" ", "%20"), apiKey);
            RestTemplate restTemplate = new RestTemplate();
            OpenCageResponse response = restTemplate.getForObject(url, OpenCageResponse.class);
            if (response != null && !response.results.isEmpty()) {
                double lat = response.results.get(0).geometry.lat;
                double lng = response.results.get(0).geometry.lng;
                return new Coordinates(lat, lng);
            }
        } catch (Exception e) {
            log.error("Error fetching coordinates for location: {}", location, e);
        }
        return null;
    }

    @Data
    public static class Coordinates {
        private final double lat;
        private final double lng;
    }

    @Data
    public static class OpenCageResponse {
        private List<Result> results;

        @Data
        public static class Result {
            private Geometry geometry;
        }

        @Data
        public static class Geometry {
            private double lat;
            private double lng;
        }
    }
}
