CREATE TABLE app_user (

                          id BIGINT PRIMARY KEY,

                          full_name VARCHAR(100) NOT NULL,

                          email VARCHAR(150) NOT NULL UNIQUE,

                          phone_number VARCHAR(20),

                          gender VARCHAR(20),

                          age INTEGER,

                          bio VARCHAR(500),

                          country VARCHAR(100),

                          city VARCHAR(100),

                          smoker BOOLEAN NOT NULL DEFAULT FALSE,

                          drinker BOOLEAN NOT NULL DEFAULT FALSE,

                          lifestyle VARCHAR(100),

                          travel_style VARCHAR(100),

                          profile_image_url VARCHAR(500),

                          created_at TIMESTAMP NOT NULL,

                          updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_user_email
    ON app_user(email);

CREATE INDEX idx_user_country
    ON app_user(country);

CREATE INDEX idx_user_city
    ON app_user(city);