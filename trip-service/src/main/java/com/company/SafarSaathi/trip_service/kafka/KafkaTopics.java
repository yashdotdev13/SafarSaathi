package com.company.SafarSaathi.trip_service.kafka;

public class KafkaTopics {

    private KafkaTopics(){}

    public static final String TRIP_CREATED = "trip-created";

    public static final String TRIP_UPDATED = "trip-updated";

    public static final String TRIP_CANCELLED = "trip-cancelled";

    public static final String TRIP_COMPLETED = "trip-completed";
}