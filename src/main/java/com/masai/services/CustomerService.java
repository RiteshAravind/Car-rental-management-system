package com.masai.services;

import com.masai.models.Customer;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final String filePath = "User.ser";

    public List<Customer> getAllCustomers() throws FileNotFoundException {
        List<Customer> customers = new ArrayList<>();

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))){

            while (true){
                try {
                    Customer customer = (Customer) inputStream.readObject();
                    customers.add(customer);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }

        return customers;
    }



    public String customerLogin(Customer customer) throws Exception {
        List<Customer> customersList = getAllCustomers();

        for(Customer customer1 : customersList){
            if(customer1.getName().equalsIgnoreCase(customer.getName()) && customer1.getPasscode().equalsIgnoreCase(customer.getPasscode())){
                return "customer login successful";
            }
        }

        throw new Exception("Invalid User");


    }

    public String save(Customer customer) throws IOException {
        File file = new File(filePath);

        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e ){
                e.printStackTrace();
            }

        }
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath,true))){
            objectOutputStream.writeObject(customer);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return "user registered successfully";
    }


}
