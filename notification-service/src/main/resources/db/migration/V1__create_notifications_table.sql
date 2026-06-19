CREATE TABLE notifications
(
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,

    type VARCHAR(50),

    message TEXT,

    read BOOLEAN DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL
);