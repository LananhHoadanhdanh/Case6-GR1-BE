package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.SerProvided;
import com.example.case6gr1be.model.User;
import com.example.case6gr1be.model.UserPrinciple;
import com.example.case6gr1be.repository.SerProvidedRepository;
import com.example.case6gr1be.repository.UserRepository;
import com.example.case6gr1be.service.SerProvidedService;
import com.example.case6gr1be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SerProvidedRepository serProvidedService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (this.checkLogin(user)) {
            return UserPrinciple.build(user);
        }
        boolean enable = false;
        boolean accountNonExpired = false;
        boolean credentialsNonExpired = false;
        boolean accountNonLocked = false;
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), enable, accountNonExpired, credentialsNonExpired,
                accountNonLocked, null);
    }


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        User user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        user = this.findByUsername(userName);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NullPointerException();
        }
        return UserPrinciple.build(user.get());
    }

    @Override
    public boolean checkLogin(User user) {
        Iterable<User> users = this.findAll();
        boolean isCorrectUser = false;
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())
                    && user.getPassword().equals(currentUser.getPassword())&&
                    currentUser.isEnabled()) {
                isCorrectUser = true;
            }
        }
        return isCorrectUser;
    }

    @Override
    public boolean isRegister(User user) {
        boolean isRegister = false;
        Iterable<User> users = this.findAll();
        for (User currentUser : users) {
            if (user.getUsername().equals(currentUser.getUsername())) {
                isRegister = true;
                break;
            }
        }
        return isRegister;
    }

    @Override
    public boolean isCorrectConfirmPassword(User user) {
        boolean isCorrentConfirmPassword = false;
        if(user.getPassword().equals(user.getConfirmPassword())){
            isCorrentConfirmPassword = true;
        }
        return isCorrentConfirmPassword;
    }

    @Override
    public Iterable<User> newServiceProvider() {
        return userRepository.newServiceProvider();
    }

    @Override
    public Iterable<User> getUsersByStatus(Long id) {
        return userRepository.getUsersByStatus(id);
    }

    @Override
    public Iterable<User> find6UserVIP() {
        return userRepository.find6UserVIP();
    }

    @Override
    public Iterable<User> get6UserByView() {
        return userRepository.get6UserByView();
    }

    @Override
    public Iterable<User> getUserByRentCount8female() {
        return userRepository.getUserByRentCount8female();
    }

    @Override
    public Iterable<User> getUserByRentCount4male() {
        return userRepository.getUserByRentCount4male();
    }

    @Override
    public ArrayList<SerProvided> get3Service(Long id) {
        ArrayList<SerProvided> serProvideds = new ArrayList<>();

        ArrayList<BigInteger> serviceIdList = (ArrayList<BigInteger>) userRepository.get3Service(id);
        for (int i = 0; i < serviceIdList.size(); i++) {
            Long id1 = serviceIdList.get(i).longValue();
            Optional<SerProvided> serProvided = serProvidedService.findById(id1);
            serProvideds.add(serProvided.get());
        }
        return serProvideds;
    }

    @Override
    public Iterable<User> list12UserSuitableForGender(String gender) {
        return userRepository.list12UserSuitableForGender(gender);
    }

    @Override
    public Iterable<User> getActiveAndVipUsers() {
        return userRepository.getActiveAndVipUsers();
    }
}
