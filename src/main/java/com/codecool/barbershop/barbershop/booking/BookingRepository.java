package com.codecool.barbershop.barbershop.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface BookingRepository extends JpaRepository<BookingModel,Integer> {


    List<BookingModel> findAllByClientModel_ClientId(Long i);
}
