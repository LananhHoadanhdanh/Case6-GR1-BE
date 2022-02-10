package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.Image;
import com.example.case6gr1be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Iterable<Image> findAllByUser(User user);
}
