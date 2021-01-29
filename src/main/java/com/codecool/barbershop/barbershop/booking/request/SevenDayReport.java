package com.codecool.barbershop.barbershop.booking.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SevenDayReport {

    private int day;
    private int numberOfBookings;
}
