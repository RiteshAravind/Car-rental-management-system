package com.masai.services;

import com.masai.exceptions.EntityNotFound;
import com.masai.models.Car;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CarService {

    private final String filePath = "Car.ser";
    public Car addCar(Car car) {
        File file = new File(filePath);

        if(!file.exists()){
            try {
                file.createNewFile();
            }catch(IOException ex){
                ex.printStackTrace();

            }
        }

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath,true))) {
            outputStream.writeObject(car);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    public List<Car> getAllCars() throws FileNotFoundException {

        List<Car> listOfCars = new ArrayList<>();

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true){
                try {
                    Car car = (Car) objectInputStream.readObject();
                    listOfCars.add(car);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfCars;
    }

    public Car getCarById(Integer id) throws FileNotFoundException {
        List<Car> carList = getAllCars();

        for(Car car : carList){
            if(car.getId() == id){
                return car;
            }
        }

        throw new EntityNotFound("No car found");
    }

    public Boolean deleteCar(Integer id) throws IOException {
        List<Car> carList = getAllCars();

        boolean carExists = carList.stream().anyMatch(car -> Objects.equals(car.getId(),id));

        if (!carExists){
            throw new EntityNotFound("No car found");
        }

        carList.removeIf(car -> car.getId().equals(id));

        addCars(carList);
        return true;



    }

    private void addCars(List<Car> carList) throws IOException {

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))){
            for (Car car : carList){
                outputStream.writeObject(car);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
