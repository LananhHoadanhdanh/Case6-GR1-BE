package com.example.case6gr1be.service;

import com.example.case6gr1be.model.Message;

import java.util.Optional;

public interface MessageService extends GeneralService<Message> {
    Iterable<Message> getAllMessByRenter(Long id);
    Iterable<Message> getMessByRenter(Long id);
    Iterable<Message> getMess(Long idPro,Long idRe);
}
