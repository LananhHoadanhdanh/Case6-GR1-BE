package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query(value = "select * from user_table where user_table.id in (select user_id from (select user_id from user_role\n" +
            "                                                                      group by user_id\n" +
            "                                                                      having count(user_id) >= 2) as dem_role) " +
            "and (status_user_id = 4 or status_user_id = 2) order by id desc limit 12", nativeQuery = true)
    Iterable<User> newServiceProvider();

    @Query("select u from User u where u.status.id = :id")
    Iterable<User> getUsersByStatus(@Param("id") Long id);

    @Query ( value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4)  limit 6 ",nativeQuery = true)
    Iterable<User> find6UserVIP();

    @Query (value = "select * from user_table where user_table.id in (select user_id from (select user_id from user_role\n" +
            "group by user_id having count(user_id) >= 2) as dem_role) order by view desc limit 6 ",nativeQuery = true)
    Iterable<User> get6UserByView();

    @Query(value = "select id_service from (select id_service from service_provided where id_user = :id) as dich_vu_theo_user order by rand() limit 3;", nativeQuery = true)
    Iterable<BigInteger> get3Service(@Param("id") Long id);

    @Query(value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4 or status_user_id = 2) and user_table.gender like :gender limit 12",nativeQuery = true)
    Iterable<User> list12UserSuitableForGender(@Param("gender") String gender);

}
