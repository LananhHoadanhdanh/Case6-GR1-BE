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
            "group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) " +
            "and (status_user_id = 4 or status_user_id = 2) order by id desc limit 12", nativeQuery = true)
    Iterable<User> newServiceProvider();

    @Query("select u from User u where u.status.id = :id")
    Iterable<User> getUsersByStatus(@Param("id") Long id);

    @Query("select u from User u where u.status.id = 2 or u.status.id = 4 order by u.id desc")
    Iterable<User> getActiveAndVipUsers();

    @Query(value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4) order by id desc limit 6 ", nativeQuery = true)
    Iterable<User> find6UserVIP();

    @Query(value = "select * from user_table where (user_table.id in (select user_id from (select user_id from user_role\n" +
            "group by user_id having count(user_id) >= 2) as dem_role))\n" +
            "and (status_user_id = 4 or status_user_id = 2) order by user_table.view desc limit 6", nativeQuery = true)
    Iterable<User> get6UserByView();

    @Query(value = "select * from user_table where (user_table.id in (select user_id from (select user_id from user_role\n" +
            "group by user_id having count(user_id) >= 2) as dem_role))\n" +
            "and (status_user_id = 4 or status_user_id = 2) and (user_table.gender LIKE 'female') order by rent_count desc limit 8", nativeQuery = true)
    Iterable<User> getUserByRentCount8female();

    @Query(value = "select * from user_table where (user_table.id in (select user_id from (select user_id from user_role\n" +
            "group by user_id having count(user_id) >= 2) as dem_role))\n" +
            "and (status_user_id = 4 or status_user_id = 2) and (user_table.gender LIKE 'male') order by rent_count desc limit 4", nativeQuery = true)
    Iterable<User> getUserByRentCount4male();

    @Query(value = "select id_service from (select id_service from service_provided where id_user = :id) as dich_vu_theo_user " +
            "order by rand() limit 3;", nativeQuery = true)
    Iterable<BigInteger> get3Service(@Param("id") Long id);

    @Query(value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4 or status_user_id = 2) " +
            "and user_table.gender like :gender order by id desc limit 12", nativeQuery = true)
    Iterable<User> list12UserSuitableForGender(@Param("gender") String gender);

    @Query(value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4 or status_user_id = 2) " +
            "and (user_table.full_name like :queryName )",nativeQuery = true)
    Iterable<User> findUserAllByFullName(@Param("queryName")String queryName);

    @Query(value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4 or status_user_id = 2) " +
            "and (user_table.age >= :formAge and user_table.age <= :toAge )" ,nativeQuery = true)
    Iterable<User> findAllByAgeTo(@Param("formAge") String formAge,@Param("toAge") String toAge);

    @Query(value = "select * from user_table where (user_table.id in (select user_id from (select user_id from user_role\n" +
            "group by user_id having count(user_id) >= 2) as dem_role))\n" +
            "and (status_user_id = 4 or status_user_id = 2) order by user_table.view desc ", nativeQuery = true)
    Iterable<User> findAllByViewDesc();

    @Query(value = "select * from user_table where (user_table.id in (select user_id from (select user_id from user_role\n" +
            "group by user_id having count(user_id) >= 2) as dem_role))\n" +
            "and (status_user_id = 4 or status_user_id = 2) order by user_table.view  ", nativeQuery = true)
    Iterable<User> findAllByViewAsc();

    @Query(value = "select * from user_table where (user_table.id in (select user_id from (select user_id from user_role\n" +
            "group by user_id having count(user_id) >= 2) as dem_role))\n" +
            "and (status_user_id = 4 or status_user_id = 2) order by  rent_count ", nativeQuery = true)
    Iterable<User> findAllByRentCountAsc();

    @Query(value = "select * from user_table where (user_table.id in (select user_id from (select user_id from user_role\n" +
            "group by user_id having count(user_id) >= 2) as dem_role))\n" +
            "and (status_user_id = 4 or status_user_id = 2) order by  rent_count desc ", nativeQuery = true)
    Iterable<User> findAllByRentCountDesc();

    @Query(value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4 or status_user_id = 2)" +
            " and user_table.city like :city order by id desc", nativeQuery = true)
    Iterable<User> listUserForAddress(@Param("city") String city);

    @Query(value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4 or status_user_id = 2) " +
            "and (user_table.city like :city or user_table.city like :city2 )order by id desc", nativeQuery = true)
    Iterable<User> listUserFor2Address(@Param("city") String city,@Param("city2") String city2);

    @Query(value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4 or status_user_id = 2)\n" +
            "and (user_table.age>= :fromAge and user_table.age<= :toAge) " +
            "and  user_table.full_name like :name",nativeQuery = true)
    Iterable <User> findAllByAgeAndName(@Param("fromAge") String fromAge,@Param("toAge") String toAge,@Param("name") String name);

    @Query(value = "select * from user_table where user_table.id in (select user_id\n" +
            "from (select user_id\n" +
            "from user_role  group by user_id\n" +
            "having count(user_id) >= 2) as dem_role) and (status_user_id = 4 or status_user_id = 2)\n" +
            "                           and (user_table.age>= :fromAge and user_table.age<= :toAge)\n" +
            "                           and  user_table.full_name like :name \n" +
            "                           and user_table.gender like :gender",nativeQuery = true)
    Iterable<User> findAllByAgeAndNameAndGender(@Param("fromAge") String fromAge,@Param("toAge") String toAge,
                                                @Param("name") String name,@Param("gender") String gender);




}
