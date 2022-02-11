package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query(value = "select * from user_table where user_table.id in (select user_id from (select user_id from user_role\n" +
            "                                                                      group by user_id\n" +
            "                                                                      having count(user_id) >= 2) as dem_role) order by id desc limit 12", nativeQuery = true)
    Iterable<User> newServiceProvider();

    @Query("select u from User u where u.status.id = :id")
    Iterable<User> getUsersByStatus(@Param("id") Long id);
}
