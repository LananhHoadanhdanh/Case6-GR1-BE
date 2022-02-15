package com.example.case6gr1be.service;

import com.example.case6gr1be.model.SerProvided;
import com.example.case6gr1be.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void save(User user);

    Iterable<User> findAll();

    User findByUsername(String username);

    User getCurrentUser();

    Optional<User> findById(Long id);

    UserDetails loadUserById(Long id);

    boolean checkLogin(User user);

    boolean isRegister(User user);

    boolean isCorrectConfirmPassword(User user);

    Iterable<User> newServiceProvider();

    Iterable<User> getUsersByStatus(Long id);

    Iterable<User> find6UserVIP();

    Iterable<User> get6UserByView();

    Iterable<User> getUserByRentCount8female();

    Iterable<User> getUserByRentCount4male();

    ArrayList<SerProvided> get3Service(Long id);

    Iterable<User> list12UserSuitableForGender( String gender);

    Iterable<User> getActiveAndVipUsers();
    Iterable<User> findUserAllByFullName(String queryName);
    Iterable<User> findAllByAgeTo( String formAge, String toAge);
    Iterable<User> findAllByViewAsc();
    Iterable<User> findAllByViewDesc();
    Iterable<User> findAllByRentCountDesc();
    Iterable<User> findAllByRentCountAsc();
    Iterable<User> listUserFor2Address(String city, String city2);
    Iterable<User> listUserForAddress( String city);
    Iterable <User> findAllByAgeAndName( String fromAge,String toAge, String name);
    Iterable<User> findAllByAgeAndNameAndGender( String fromAge, String toAge, String name, String gender);


}
