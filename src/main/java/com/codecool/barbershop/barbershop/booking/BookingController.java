package com.codecool.barbershop.barbershop.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
@CrossOrigin("*")
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

    @GetMapping("history/{clientId}")
    List<BookingModel> getAllBookingsByClientId(@PathVariable Long clientId){
        return bookingService.getAllBookingsByClientId(clientId);
    }

//    no exception on endpoint
    @PostMapping
    public BookingModel saveNewBooking(@RequestBody BookingDTO bookingDTO) throws Exception {
        return bookingService.saveNewBooking(bookingDTO);
    }

//    @PutMapping("{bookingId}/{newBookingStatus}")
//    public BookingModel updateStatusBooking(@PathVariable int bookingId, @PathVariable String newBookingStatus) throws IllegalArgumentException{
//        return bookingService.updateStatusBooking(bookingId,newBookingStatus);
//    }

}