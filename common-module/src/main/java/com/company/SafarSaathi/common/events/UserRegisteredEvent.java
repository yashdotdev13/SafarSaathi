package com.company.SafarSaathi.common.events;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisteredEvent {

    private Long userId;
    private String fullName;

    private String email;
}
