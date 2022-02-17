package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.Message;
import com.example.case6gr1be.repository.MessageRepository;
import com.example.case6gr1be.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Iterable<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Iterable<Message> getAllMessByRenter(Long id) {
        return messageRepository.getAllMessByRenter(id);
    }

    @Override
    public Iterable<Message> getMessByRenter(Long id) {
        return messageRepository.getMessByRenter(id);
    }

    @Override
    public Iterable<Message> getMess(Long idPro, Long idRe) {
        return messageRepository.getMess(idPro, idRe);
    }
}
