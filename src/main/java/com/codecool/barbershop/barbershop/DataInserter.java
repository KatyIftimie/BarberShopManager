package com.codecool.barbershop.barbershop;

import com.codecool.barbershop.barbershop.booking.BookingStatus;
import com.codecool.barbershop.barbershop.booking.BookingStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataInserter {
    private BookingStatusRepository bookingStatusRepository;



    public void addStatuses(){
        BookingStatus bs1=new BookingStatus();
        bs1.setName(BookingStatus.Status.UPCOMING);
        bookingStatusRepository.save(bs1);

        BookingStatus bs2=new BookingStatus();
        bs1.setName(BookingStatus.Status.CONFIRM);
        bookingStatusRepository.save(bs2);

        BookingStatus bs3=new BookingStatus();
        bs1.setName(BookingStatus.Status.CANCELED);
        bookingStatusRepository.save(bs3);

        BookingStatus bs4=new BookingStatus();
        bs1.setName(BookingStatus.Status.MISSING);
        bookingStatusRepository.save(bs4);
    }
}
