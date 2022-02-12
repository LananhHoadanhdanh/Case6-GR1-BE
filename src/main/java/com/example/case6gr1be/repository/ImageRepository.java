package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.Image;
import com.example.case6gr1be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Long> {


    Iterable<Image> findAllByUser(User user);
//    @Query("select  i from Image i where i.user.id= :id ")
    @Query(value = "select * from image where image.user_id= :id order by id desc limit 3",nativeQuery = true)
    Iterable<Image> findAllImageByUserId(@Param("id") Long id);
}
