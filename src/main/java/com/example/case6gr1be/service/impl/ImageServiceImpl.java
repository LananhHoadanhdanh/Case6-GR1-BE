package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.Image;
import com.example.case6gr1be.repository.ImageRepository;
import com.example.case6gr1be.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }
}
