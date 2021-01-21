package com.codecool.barbershop.barbershop.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Date bookingDate;
    private int clientId;

}
