package com.company.SafarSaathi.user_service.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserProfileRequest {

    @Size(min = 3, max = 100)
    private String fullName;
    private String phoneNumber;
    private String gender;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age cannot exceed 100")
    private Integer age;

    @Size(max = 500)
    private String bio;
    private String country;
    private String city;
    private boolean smoker;
    private boolean drinker;
    private String lifestyle;
    private String travelStyle;
    private String profileImageUrl;
}