CREATE TABLE companions (

                            id BIGSERIAL PRIMARY KEY,

                            user_id BIGINT NOT NULL,

                            trip_id BIGINT NOT NULL,

                            status VARCHAR(30) NOT NULL,

                            message VARCHAR(500),

                            created_at TIMESTAMP NOT NULL,

                            updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_companion_user_id
    ON companions(user_id);

CREATE INDEX idx_companion_trip_id
    ON companions(trip_id);

CREATE INDEX idx_companion_status
    ON companions(status);


CREATE TABLE companion_preferences (

                                       id BIGSERIAL PRIMARY KEY,

                                       user_id BIGINT NOT NULL UNIQUE,

                                       preferred_age_min INTEGER,

                                       preferred_age_max INTEGER,

                                       preferred_gender VARCHAR(20),

                                       smoker_ok BOOLEAN,

                                       drinker_ok BOOLEAN,

                                       preferred_trip_mode VARCHAR(100),

                                       created_at TIMESTAMP NOT NULL,

                                       updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_preference_user_id
    ON companion_preferences(user_id);



CREATE TABLE companion_requests (

                                    id BIGSERIAL PRIMARY KEY,

                                    sender_id BIGINT NOT NULL,

                                    receiver_id BIGINT NOT NULL,

                                    trip_id BIGINT NOT NULL,

                                    status VARCHAR(30) NOT NULL,

                                    message VARCHAR(500),

                                    time_stamp TIMESTAMP NOT NULL,

                                    CONSTRAINT uk_sender_receiver_trip
                                        UNIQUE(sender_id, receiver_id, trip_id)
);


CREATE INDEX idx_request_sender
    ON companion_requests(sender_id);

CREATE INDEX idx_request_receiver
    ON companion_requests(receiver_id);

CREATE INDEX idx_request_trip
    ON companion_requests(trip_id);

CREATE INDEX idx_request_status
    ON companion_requests(status);



CREATE TABLE companion_matches (

                                   companion_id BIGINT NOT NULL,

                                   matched_user_id BIGINT NOT NULL,

                                   CONSTRAINT fk_companion_match
                                       FOREIGN KEY (companion_id)
                                           REFERENCES companions(id)
                                           ON DELETE CASCADE
);

CREATE INDEX idx_companion_match
    ON companion_matches(companion_id);
