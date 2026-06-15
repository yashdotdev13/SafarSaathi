package com.company.SafarSaathi.common.constants;


public final class KafkaTopics {

    private KafkaTopics() {}

    public static final String USER_REGISTERED =
            "user-registered-topic";

    public static final String USER_PROFILE_CREATED =
            "user-profile-created-topic";

    public static final String TRIP_CREATED =
            "trip-created-topic";

    public static final String COMPANION_REQUESTED =
            "companion-requested-topic";

    public static final String MATCH_FOUND =
            "match-found-topic";

    public static final String NOTIFICATION_EVENT =
            "notification-topic";
}
