package com.example.case6gr1be.service;

import com.example.case6gr1be.model.Image;
import com.example.case6gr1be.model.User;
import org.springframework.data.jpa.repository.Query;

public interface ImageService extends GeneralService<Image>{
    Iterable<Image> findAllByUser(User user);
    Iterable<Image> findAllImageByUserId(Long id);

}
