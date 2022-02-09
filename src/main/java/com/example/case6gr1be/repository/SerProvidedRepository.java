package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.SerProvided;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SerProvidedRepository extends JpaRepository<SerProvided,Long> {


    void activeService(@Param("id") Long id,int idService);
}
