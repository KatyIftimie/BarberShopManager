package com.codecool.barbershop.barbershop.booking;

import com.codecool.barbershop.barbershop.client.ClientModel;
import com.codecool.barbershop.barbershop.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private ClientService clientService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ClientService clientService) {
        this.bookingRepository = bookingRepository;
        this.clientService = clientService;
    }

    public List<BookingModel> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingModel saveNewBooking(BookingDTO bookingDTO) throws Exception {

//        Preparing the data
        Date today = new Date();
        BookingModel newBookingModel = new BookingModel();
        ClientModel client = clientService.getClientById(bookingDTO.getClientId());

//        Set the data & Save
        newBookingModel.setCreatedDate(today);
        newBookingModel.setUpdatedDate(today);
        newBookingModel.setBookingDate(bookingDTO.getBookingDate());
        newBookingModel.setClientModel(client);

        return bookingRepository.save(newBookingModel);
    }

//    public BookingModel updateStatusBooking(int bookingId, String newBookingStatus) {
//        Optional<BookingModel> booking = bookingRepository.findById(bookingId);
//
//        if (booking.isPresent()) {
//            booking.get().setStatus(BookingStatus.valueOf(newBookingStatus.toUpperCase(Locale.ROOT)));
//            return bookingRepository.save(booking.get());
//        } else {
//            return null;
//        }
//    }

    public List<BookingModel> getAllBookingsByClientId(Long clientId) {
        return bookingRepository.findAllByClientModel_ClientId(clientId);


    }
}
