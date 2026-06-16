CREATE TABLE app_user (
                          id BIGSERIAL PRIMARY KEY,

                          full_name VARCHAR(255) NOT NULL,

                          email VARCHAR(255) NOT NULL UNIQUE,

                          password VARCHAR(255) NOT NULL,

                          role VARCHAR(50) NOT NULL,

                          created_at TIMESTAMP NOT NULL,

                          updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_user_email
    ON app_user(email);

CREATE INDEX idx_user_role
    ON app_user(role);