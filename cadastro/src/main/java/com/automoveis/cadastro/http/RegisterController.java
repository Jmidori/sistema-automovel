package com.automoveis.cadastro.http;

import com.automoveis.cadastro.CarRepository;
import com.automoveis.cadastro.entity.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/automoveis")
public class RegisterController {

    private final CarRepository repository;

    public RegisterController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        try{
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK) ;
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastroAutomoveis")
    public ResponseEntity<Object> createCar(@RequestBody @Validated Car car){
        try {
            car.setRegisterDate(LocalDate.now());
            repository.save(car);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}