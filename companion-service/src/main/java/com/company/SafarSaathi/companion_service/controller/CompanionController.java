package com.company.SafarSaathi.companion_service.controller;

import com.company.SafarSaathi.companion_service.dtos.request.CreateCompanionPreferenceRequest;
import com.company.SafarSaathi.companion_service.dtos.request.CreateCompanionRequest;
import com.company.SafarSaathi.companion_service.dtos.request.SendCompanionRequest;
import com.company.SafarSaathi.companion_service.dtos.request.UpdateCompanionPreferenceRequest;
import com.company.SafarSaathi.companion_service.dtos.request.UpdateCompanionRequest;
import com.company.SafarSaathi.companion_service.dtos.response.CompanionPreferenceResponse;
import com.company.SafarSaathi.companion_service.dtos.response.CompanionRequestResponse;
import com.company.SafarSaathi.companion_service.dtos.response.CompanionResponse;
import com.company.SafarSaathi.companion_service.service.CompanionPreferenceService;
import com.company.SafarSaathi.companion_service.service.CompanionRequestService;
import com.company.SafarSaathi.companion_service.service.CompanionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companions")
@RequiredArgsConstructor
@Slf4j
public class CompanionController {

    private final CompanionService companionService;
    private final CompanionPreferenceService preferenceService;
    private final CompanionRequestService companionRequestService;


    @PostMapping
    public ResponseEntity<CompanionResponse> createCompanion(
            @Valid @RequestBody CreateCompanionRequest request
    ) {

        log.info("Creating companion profile");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companionService.createCompanion(request));
    }

    @PutMapping("/{companionId}")
    public ResponseEntity<CompanionResponse> updateCompanion(
            @PathVariable Long companionId,
            @Valid @RequestBody UpdateCompanionRequest request
    ) {

        log.info(
                "Updating companion profile. companionId={}",
                companionId
        );

        return ResponseEntity.ok(
                companionService.updateCompanion(
                        companionId,
                        request
                )
        );
    }

    @DeleteMapping("/{companionId}")
    public ResponseEntity<Void> deleteCompanion(
            @PathVariable Long companionId
    ) {

        log.info(
                "Deleting companion profile. companionId={}",
                companionId
        );

        companionService.deleteCompanion(companionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CompanionResponse>> getMyCompanions() {

        log.info("Fetching companion profiles");

        return ResponseEntity.ok(
                companionService.getAllCompanions()
        );
    }

    @GetMapping("/{companionId}")
    public ResponseEntity<CompanionResponse> getCompanionById(
            @PathVariable Long companionId
    ) {

        return ResponseEntity.ok(
                companionService.getCompanionById(companionId)
        );
    }


    @PostMapping("/preferences")
    public ResponseEntity<CompanionPreferenceResponse> createPreference(
            @Valid @RequestBody CreateCompanionPreferenceRequest request
    ) {

        log.info("Creating companion preference");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(preferenceService.createPreference(request));
    }

    @PutMapping("/preferences")
    public ResponseEntity<CompanionPreferenceResponse> updatePreference(
            @Valid @RequestBody UpdateCompanionPreferenceRequest request
    ) {

        log.info("Updating companion preference");

        return ResponseEntity.ok(
                preferenceService.updatePreference(request)
        );
    }

    @GetMapping("/preferences/me")
    public ResponseEntity<CompanionPreferenceResponse> getMyPreference() {

        return ResponseEntity.ok(
                preferenceService.getMyPreference()
        );
    }


    @PostMapping("/requests")
    public ResponseEntity<CompanionRequestResponse> sendRequest(
            @Valid @RequestBody SendCompanionRequest request
    ) {

        log.info(
                "Sending companion request to receiverId={}",
                request.getReceiverId()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        companionRequestService.sendRequest(
                                request
                        )
                );
    }

    @PostMapping("/requests/{requestId}/accept")
    public ResponseEntity<CompanionRequestResponse> acceptRequest(
            @PathVariable Long requestId
    ) {

        log.info(
                "Accepting companion request. requestId={}",
                requestId
        );

        return ResponseEntity.ok(
                companionRequestService.acceptRequest(
                        requestId
                )
        );
    }

    @PostMapping("/requests/{requestId}/reject")
    public ResponseEntity<CompanionRequestResponse> rejectRequest(
            @PathVariable Long requestId
    ) {

        log.info(
                "Rejecting companion request. requestId={}",
                requestId
        );

        return ResponseEntity.ok(
                companionRequestService.rejectRequest(
                        requestId
                )
        );
    }

    @GetMapping("/requests/received")
    public ResponseEntity<List<CompanionRequestResponse>>
    getReceivedRequests() {

        return ResponseEntity.ok(
                companionRequestService.getReceivedRequests()
        );
    }

    @GetMapping("/requests/sent")
    public ResponseEntity<List<CompanionRequestResponse>>
    getSentRequests() {

        return ResponseEntity.ok(
                companionRequestService.getSentRequests()
        );
    }
}