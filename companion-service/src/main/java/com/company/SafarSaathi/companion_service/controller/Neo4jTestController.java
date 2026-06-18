package com.company.SafarSaathi.companion_service.controller;

import com.company.SafarSaathi.companion_service.neo4j_entities.UserNode;
import com.company.SafarSaathi.companion_service.neo4j_entities.UserNodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/neo4j-test")
public class Neo4jTestController {

    private final UserNodeRepository userNodeRepository;

    @GetMapping
    public String test() {

        try {

            UserNode node =
                    UserNode.builder()
                            .userId(999L)
                            .build();

            userNodeRepository.save(node);

            return "saved";

        } catch (Exception e) {

            e.printStackTrace();

            return e.getClass().getName()
                    + " -> "
                    + e.getMessage();
        }
    }
}