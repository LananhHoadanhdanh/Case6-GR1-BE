package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.SerProvided;
import com.example.case6gr1be.model.ServiceProvided;
import com.example.case6gr1be.service.SerProvidedService;
import com.example.case6gr1be.service.ServiceProvidedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class SerProvidedController {
    @Autowired
    private ServiceProvidedService serviceProvidedService;
    @Autowired
    private SerProvidedService serProvinderService;

    @GetMapping("/service")
    public ResponseEntity<Iterable<SerProvided>> findAll() {
        return new ResponseEntity<>(serProvinderService.findAll(), HttpStatus.OK);
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
                        if (ser.getId()==serPro.getIdService()){
                            name.add(ser.getName());
                        }
                    }
                });
            }
        });
        return name;
    }

    @PostMapping("/actService/{id}")
    public void actService(@PathVariable Long id, Long idService[]) {
        for (int i = 0; i < idService.length; i++) {
            ServiceProvided serPro = new ServiceProvided(idService[i], id);
            serviceProvidedService.add(serPro);
        }
    }

}
