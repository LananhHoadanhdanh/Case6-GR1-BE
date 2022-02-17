package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.Message;
import com.example.case6gr1be.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository  extends JpaRepository<Message,Long> {
    @Query("select o from Message o where o.idUs.id = :id")
    Iterable<Message> getAllMessByRenter(@Param("id") Long id);
    @Query("select o from Message o where o.idUs.id = :id group by o.idPro")
    Iterable<Message> getMessByRenter(@Param("id") Long id);
    @Query("select o from Message o where o.idPro.id = :idPro and o.idUs.id = :idRe order by o.id asc ")
    Iterable<Message> getMess(@Param("idPro") Long idPro,@Param("idRe") Long idRe);
}
