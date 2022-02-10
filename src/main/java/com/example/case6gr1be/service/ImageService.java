package com.example.case6gr1be.service;

import com.example.case6gr1be.model.Image;
import com.example.case6gr1be.model.User;

public interface ImageService extends GeneralService<Image>{
    Iterable<Image> findAllByUser(User user);
}
