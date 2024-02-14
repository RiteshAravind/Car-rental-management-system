package com.masai.controllers;

import com.masai.models.Car;
import com.masai.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car savedCar = carService.addCar(car);
        return ResponseEntity.ok(savedCar);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() throws FileNotFoundException {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id) throws FileNotFoundException {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable Integer id) throws IOException {
        Boolean deleted = carService.deleteCar(id);
        return ResponseEntity.ok(deleted);
    }




}
