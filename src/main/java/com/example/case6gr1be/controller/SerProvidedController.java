package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.Role;
import com.example.case6gr1be.model.SerProvided;
import com.example.case6gr1be.model.ServiceProvided;
import com.example.case6gr1be.model.User;
import com.example.case6gr1be.service.RoleService;
import com.example.case6gr1be.service.SerProvidedService;
import com.example.case6gr1be.service.ServiceProvidedService;
import com.example.case6gr1be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class SerProvidedController {
    @Autowired
    private ServiceProvidedService serviceProvidedService;
    @Autowired
    private SerProvidedService serProvinderService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/service")
    public ResponseEntity<Iterable<SerProvided>> findAll() {
        return new ResponseEntity<>(serProvinderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/serviceFree")
    public ResponseEntity<ArrayList<SerProvided>> findAllFree() {
        Iterable<SerProvided> serProvideds = serProvinderService.findAll();
        ArrayList<SerProvided> serviceFree = new ArrayList<>();
        serProvideds.forEach(new Consumer<SerProvided>() {
            @Override
            public void accept(SerProvided ser) {
                if (ser.getCategory() == 1) {
                    serviceFree.add(ser);
                }
            }
        });
        return new ResponseEntity<>(serviceFree, HttpStatus.OK);
    }

    @GetMapping("/serviceExtend")
    public ResponseEntity<ArrayList<SerProvided>> findAllExtend() {
        Iterable<SerProvided> serProvideds = serProvinderService.findAll();
        ArrayList<SerProvided> serviceExtend = new ArrayList<>();
        serProvideds.forEach(new Consumer<SerProvided>() {
            @Override
            public void accept(SerProvided ser) {
                if (ser.getCategory() == 2) {
                    serviceExtend.add(ser);
                }
            }
        });
        return new ResponseEntity<>(serviceExtend, HttpStatus.OK);
    }

    @GetMapping("/serviceMinTime")
    public ResponseEntity<ArrayList<SerProvided>> SerMinTime() {
        Iterable<SerProvided> serProvideds = serProvinderService.findAll();
        ArrayList<SerProvided> serviceExtend = new ArrayList<>();
        serProvideds.forEach(new Consumer<SerProvided>() {
            @Override
            public void accept(SerProvided ser) {
                if (ser.getCategory() == 0) {
                    serviceExtend.add(ser);
                }
            }
        });
        return new ResponseEntity<>(serviceExtend, HttpStatus.OK);
    }

    @GetMapping("/servicePro")
    public ResponseEntity<Iterable<ServiceProvided>> findAllService() {
        return new ResponseEntity<>(serviceProvidedService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/servicePro/{id}")
    public ResponseEntity<Iterable<ServiceProvided>> findAllServiceByIdUser(@PathVariable Long id) {
        return new ResponseEntity<>(serviceProvidedService.findAllByIdUser(id), HttpStatus.OK);
    }


    @GetMapping("/serviceName/{id}")
    public ArrayList<String> serName(@PathVariable Long id, Long idService[]) {
        ArrayList<String> name = new ArrayList<>();

        Iterable<ServiceProvided> serviceProvided = serviceProvidedService.findAllByIdUser(id);
        Iterable<SerProvided> serProvideds = serProvinderService.findAll();
        serProvideds.forEach(new Consumer<SerProvided>() {
            @Override
            public void accept(SerProvided ser) {
                serviceProvided.forEach(new Consumer<ServiceProvided>() {
                    @Override
                    public void accept(ServiceProvided serPro) {
                        if (ser.getId() == serPro.getIdService()) {
                            name.add(ser.getName());
                        }
                    }
                });
            }
        });
        return name;
    }

    @DeleteMapping("/deleteService/{idU}")
    public void delete(@PathVariable Long idU) {
        serviceProvidedService.findAll().forEach(new Consumer<ServiceProvided>() {
            @Override
            public void accept(ServiceProvided serviceProvided) {
                if (serviceProvided.getIdUser() == idU ) {
                    serviceProvidedService.delete(serviceProvided);
                }
            }
        });
    }

    @PostMapping("/actService")
    public void actService(@RequestBody ServiceProvided idService[]) {
        for (int i = 0; i < idService.length; i++) {
            ServiceProvided serPro = idService[i];
            serviceProvidedService.add(serPro);
        }
        long idU = idService[0].getIdUser();
        User user = userService.findById(idU).get();
        Set<Role> roleSet = user.getRoles();

        Role role = roleService.findByName("ROLE_PROVIDER");

        roleSet.add(role);
        user.setRoles(roleSet);
        userService.save(user);
    }

}
