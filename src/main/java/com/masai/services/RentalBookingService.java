package com.masai.services;

import com.masai.exceptions.EntityNotFound;
import com.masai.models.RentalBooking;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RentalBookingService {

    private final String filePath = "RentalBooking.ser";


    public RentalBooking addBooking(RentalBooking booking) {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            outputStream.writeObject(booking);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return booking;
    }


    public List<RentalBooking> getAllBooking()  {
        List<RentalBooking> bookings = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    RentalBooking booking = (RentalBooking) inputStream.readObject();
                    bookings.add(booking);

                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public RentalBooking getBookingById(Integer id) {
        List<RentalBooking> bookings = getAllBooking();
        System.out.println(bookings.size());
        for (RentalBooking booking : bookings) {
            if (booking.getId().equals(id)) {
                return booking;
            }
        }
        throw new EntityNotFound("Booking is not found ");
    }

    // Deletes the booking with the specified ID from the file and returns true upon successful deletion.
    public Boolean deleteById(Integer id) {
        List<RentalBooking> bookings = getAllBooking();
        boolean bookingExists = bookings.stream().anyMatch(booking -> Objects.equals(booking.getId(), id));
        if (!bookingExists) {
            throw new EntityNotFound("Booking not found");
        }
        bookings.removeIf(booking -> booking.getId().equals(id));
        saveBookings(bookings);
        return true;
    }

    // Helper method to save the list of bookings to the file.
    private void saveBookings(List<RentalBooking> bookings) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (RentalBooking booking : bookings) {
                outputStream.writeObject(booking);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
