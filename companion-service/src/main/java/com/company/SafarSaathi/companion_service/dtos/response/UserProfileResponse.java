package com.company.SafarSaathi.companion_service.dtos.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private Long userId;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String gender;

    private int age;

    private String bio;

    private String country;

    private String city;

    private boolean smoker;

    private boolean drinker;

    private String lifestyle;

    private String travelStyle;

    private String profileImageUrl;
}