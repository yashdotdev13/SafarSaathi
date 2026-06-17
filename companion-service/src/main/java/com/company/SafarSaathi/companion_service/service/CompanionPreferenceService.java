package com.company.SafarSaathi.companion_service.service;

import com.company.SafarSaathi.companion_service.auth.UserContextHolder;
import com.company.SafarSaathi.companion_service.dtos.request.CreateCompanionPreferenceRequest;
import com.company.SafarSaathi.companion_service.dtos.request.UpdateCompanionPreferenceRequest;
import com.company.SafarSaathi.companion_service.dtos.response.CompanionPreferenceResponse;
import com.company.SafarSaathi.companion_service.entity.CompanionPreference;
import com.company.SafarSaathi.companion_service.exceptions.ResourceNotFoundException;
import com.company.SafarSaathi.companion_service.repository.CompanionPreferenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CompanionPreferenceService {

    private final CompanionPreferenceRepository preferenceRepository;
    private final ModelMapper modelMapper;

    public CompanionPreferenceResponse createPreference(
            CreateCompanionPreferenceRequest request
    ) {

        Long userId = UserContextHolder.getCurrentUserId();

        log.info(
                "Creating companion preference for userId={}",
                userId
        );

        if (preferenceRepository.findByUserId(userId).isPresent()) {
            throw new IllegalStateException(
                    "Preference already exists for userId: " + userId
            );
        }

        CompanionPreference preference =
                CompanionPreference.builder()
                        .userId(userId)
                        .preferredAgeMin(request.getPreferredAgeMin())
                        .preferredAgeMax(request.getPreferredAgeMax())
                        .preferredGender(request.getPreferredGender())
                        .smokerOk(request.getSmokerOk())
                        .drinkerOk(request.getDrinkerOk())
                        .preferredTripMode(request.getPreferredTripMode())
                        .build();

        CompanionPreference saved =
                preferenceRepository.save(preference);

        return modelMapper.map(
                saved,
                CompanionPreferenceResponse.class
        );
    }

    public CompanionPreferenceResponse updatePreference(
            UpdateCompanionPreferenceRequest request
    ) {

        Long userId = UserContextHolder.getCurrentUserId();

        CompanionPreference preference =
                preferenceRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Preference not found for userId: "
                                                + userId
                                )
                        );

        if (request.getPreferredAgeMin() != null) {
            preference.setPreferredAgeMin(
                    request.getPreferredAgeMin()
            );
        }

        if (request.getPreferredAgeMax() != null) {
            preference.setPreferredAgeMax(
                    request.getPreferredAgeMax()
            );
        }

        if (request.getPreferredGender() != null) {
            preference.setPreferredGender(
                    request.getPreferredGender()
            );
        }

        if (request.getSmokerOk() != null) {
            preference.setSmokerOk(
                    request.getSmokerOk()
            );
        }

        if (request.getDrinkerOk() != null) {
            preference.setDrinkerOk(
                    request.getDrinkerOk()
            );
        }

        if (request.getPreferredTripMode() != null) {
            preference.setPreferredTripMode(
                    request.getPreferredTripMode()
            );
        }

        CompanionPreference updated =
                preferenceRepository.save(preference);

        log.info(
                "Updated companion preference for userId={}",
                userId
        );

        return modelMapper.map(
                updated,
                CompanionPreferenceResponse.class
        );
    }

    @Transactional(readOnly = true)
    public CompanionPreferenceResponse getMyPreference() {

        Long userId = UserContextHolder.getCurrentUserId();

        CompanionPreference preference =
                preferenceRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Preference not found for userId: "
                                                + userId
                                )
                        );

        return modelMapper.map(
                preference,
                CompanionPreferenceResponse.class
        );
    }
}