package com.codecool.barbershop.barbershop.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    List<BookingModel> getAllBooking(){
        return bookingService.getAllBookings();
    }

    @PostMapping
    public BookingModel saveNewBooking(@RequestBody BookingDTO bookingDTO) throws Exception {
        return bookingService.saveNewBooking(bookingDTO);
    }

    @PutMapping("/{bookingId}/{newBookingStatus}")
    public BookingModel updateStatusBooking(@PathVariable int bookingId, @PathVariable String newBookingStatus) throws IllegalArgumentException{
        return bookingService.updateStatusBooking(bookingId,newBookingStatus);
    }

}