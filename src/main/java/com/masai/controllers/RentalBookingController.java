package com.masai.controllers;

import com.masai.models.RentalBooking;
import com.masai.services.RentalBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RentalBookingController {

    @Autowired
    private RentalBookingService rentalBookingService;

    @PostMapping
    public ResponseEntity<RentalBooking> addBooking(@RequestBody RentalBooking rentalBooking) {
        RentalBooking savedBooking = rentalBookingService.addBooking(rentalBooking);
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping
    public ResponseEntity<List<RentalBooking>> getBookings() {
        List<RentalBooking> bookings = rentalBookingService.getAllBooking();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalBooking> getBooking(@PathVariable Integer id) {
        RentalBooking booking = rentalBookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBooking(@PathVariable Integer id) {
        Boolean deleted = rentalBookingService.deleteById(id);
        return ResponseEntity.ok(deleted);
    }

}
