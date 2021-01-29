package com.codecool.barbershop.barbershop.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
interface BookingRepository extends JpaRepository<BookingModel,Integer> {


    List<BookingModel> findAllByClientModel_ClientId(Long i);
    List<BookingModel> findAllByBookingDateBetweenAndBookingStatus(Date start, Date end, BookingStatus status);
    int countAllByBookingStatusEquals(BookingStatus status);
   int countAllByUpdatedDateBetweenAndBookingStatusEquals(Date timeStart, Date timeEnd,BookingStatus status);
}
