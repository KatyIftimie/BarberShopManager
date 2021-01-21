package com.codecool.barbershop.barbershop.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BookingRepository extends JpaRepository<BookingModel,Integer> {
}
