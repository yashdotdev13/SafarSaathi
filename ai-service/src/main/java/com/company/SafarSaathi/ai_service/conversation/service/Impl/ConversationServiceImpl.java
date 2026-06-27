package com.company.SafarSaathi.ai_service.conversation.service.Impl;

import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import com.company.SafarSaathi.ai_service.conversation.enums.MessageRole;
import com.company.SafarSaathi.ai_service.conversation.repository.ConversationMessageRepository;
import com.company.SafarSaathi.ai_service.conversation.repository.ConversationRepository;
import com.company.SafarSaathi.ai_service.conversation.service.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationMessageRepository messageRepository;


    @Override
    public Conversation getOrCreateConversation(Long userId, String conversationId) {
        if (conversationId == null || conversationId.isBlank()) {

            log.info(
                    "Creating new conversation for userId={}",
                    userId
            );

            Conversation conversation = Conversation.builder()
                    .userId(userId)
                    .title("New Conversation")
                    .active(true)
                    .build();

            return conversationRepository.save(conversation);
        }

        UUID uuid = UUID.fromString(conversationId);

        return conversationRepository.findByConversationId(uuid)
                .orElseGet(() -> {

                    log.info(
                            "Conversation not found. Creating new conversation. conversationId={}",
                            conversationId
                    );

                    Conversation conversation = Conversation.builder()
                            .conversationId(uuid)
                            .userId(userId)
                            .title("New Conversation")
                            .active(true)
                            .build();

                    return conversationRepository.save(conversation);
                });
    }

    @Override
    public void saveMessage(Conversation conversation, MessageRole role, String content) {

        ConversationMessage message = ConversationMessage.builder()
                .conversation(conversation)
                .role(role)
                .content(content)
                .build();

        messageRepository.save(message);
        log.debug("saved {} message for conversation={}",role, conversation.getConversationId());

    }

    @Override
    public List<String> getConversationHistory(Conversation conversation) {

        return messageRepository
                .findByConversationOrderByCreatedAtAsc(conversation)
                .stream()
                .map(message ->
                        message.getRole() + ": " + message.getContent()
                )
                .toList();
    }
}
