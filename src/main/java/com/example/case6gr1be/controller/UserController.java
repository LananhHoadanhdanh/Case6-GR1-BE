package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.*;
import com.example.case6gr1be.service.ImageService;
import com.example.case6gr1be.service.RoleService;
import com.example.case6gr1be.service.StatusUserService;
import com.example.case6gr1be.service.UserService;
import com.example.case6gr1be.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private Environment env;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageService imageService;

    @Autowired
    private StatusUserService statusUserService;


    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> showAllUser() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/usersByStatus")
    public ResponseEntity<Iterable<User>> getAllUserByStatus(Long statusId) {
        Iterable<User> users = userService.getUsersByStatus(statusId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (user.getRoles() != null) {
            Role role = roleService.findByName("ROLE_ADMIN");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        } else {
            Role role1 = roleService.findByName("ROLE_USER");
            Set<Role> roles1 = new HashSet<>();
            roles1.add(role1);
            user.setRoles(roles1);
        }
        user.setAvatar("assets/img/ava_default.png");
        user.setStartTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));

        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername());
        JwtResponse jwtResponse = new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities(), currentUser.getStatus());
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity("Hello World", HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        Optional<User> userOptional = this.userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        user.setRoles(userOptional.get().getRoles());
        user.setPassword(userOptional.get().getPassword());
        user.setConfirmPassword(userOptional.get().getConfirmPassword());
        user.setUsername(userOptional.get().getUsername());
        user.setEmail(userOptional.get().getEmail());
        user.setPhoneNumber(userOptional.get().getPhoneNumber());
        user.setStartTime(userOptional.get().getStartTime());
        user.setView(userOptional.get().getView());
        user.setRentCount(userOptional.get().getRentCount());
        user.setPrice(userOptional.get().getPrice());
        user.setEnabled(userOptional.get().isEnabled());
        user.setStatus(userOptional.get().getStatus());
        user.setRoles(userOptional.get().getRoles());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/uploadPrice")
    public ResponseEntity<User> userPriceUpdate(@PathVariable Long id, long price) {
        Optional<User> userOptional = userService.findById(id);
        User user = userOptional.get();
        user.setPrice(price);
        user.setId(id);
        userService.save(user);
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/users/{id}/increaseViews")
    public ResponseEntity<User> increaseViews(@PathVariable Long id) {
        User user = userService.findById(id).get();
        int view = user.getView() + 1;
        user.setView(view);
        user.setId(id);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/increaseRentCount")
    public ResponseEntity<User> increaseRentCount(@PathVariable Long id) {
        User user = userService.findById(id).get();
        int rentCount = user.getRentCount() + 1;
        user.setRentCount(rentCount);
        user.setId(id);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/loadImage")
    public ResponseEntity<Image> loadImage(@RequestBody Image image) {
        imageService.save(image);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/12newServiceProvider")
    public ResponseEntity<Iterable<User>> newServiceProvider() {
        Iterable<User> newServiceProvider = userService.newServiceProvider();
        return new ResponseEntity<>(newServiceProvider, HttpStatus.OK);
    }

    //    @GetMapping("/findAllImageByUser/{id}")
//    public ResponseEntity<Iterable<Image>> findAllImageByUser(@PathVariable Long id){
//        Iterable<Image> images=imageService.findAllByUser(userService.findById(id).get());
//        return new ResponseEntity<>(images,HttpStatus.OK);
//    }
    @GetMapping("/findAllImageByUser/{id}")
    public ResponseEntity<Iterable<Image>> findAllImageByUser(@PathVariable Long id) {
        Iterable<Image> images = imageService.findAllImageByUserId(id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/activeAndVipUsers")
    public ResponseEntity<Iterable<User>> getActiveAndVipUsers() {
        Iterable<User> users = userService.getActiveAndVipUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/usersByView")
    public ResponseEntity<Iterable<User>> getAllUserByView() {
        Iterable<User> users = userService.get6UserByView();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/rent8Female")
    public ResponseEntity<Iterable<User>> getUserByRentCount8female() {
        Iterable<User> users = userService.getUserByRentCount8female();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/rent4Male")
    public ResponseEntity<Iterable<User>> getUserByRentCount4male() {
        Iterable<User> users = userService.getUserByRentCount4male();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/serProvidedByUser")
    public ResponseEntity<ArrayList<SerProvided>> get3SerProviderRandom(Long userId) {
        ArrayList<SerProvided> serProvideds = userService.get3Service(userId);
        return new ResponseEntity<>(serProvideds, HttpStatus.OK);
    }

    @GetMapping("/list6UserVip")
    public ResponseEntity<Iterable<User>> list6UserVip() {
        Iterable<User> users = userService.find6UserVIP();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/list12UserSuitableForGender/{gender}")
    public ResponseEntity<Iterable<User>> list12UserSuitableForGender(@PathVariable String gender) {
        if (gender.equals("male")) {
            gender = "female";
        } else if (gender.equals("female")) {
            gender = "male";
        }
        Iterable<User> users = userService.list12UserSuitableForGender(gender);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findUserAllByFullName")
    public ResponseEntity<Iterable<User>> findUserAllByFullName(String queryName) {
        Iterable<User> users = userService.findUserAllByFullName('%' + queryName + '%');
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
