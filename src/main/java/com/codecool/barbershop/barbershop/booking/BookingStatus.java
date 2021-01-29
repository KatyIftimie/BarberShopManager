package com.codecool.barbershop.barbershop.booking;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "booking_statuses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingStatus {
    @Id
    private int Id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status name = Status.UPCOMING;


    public enum Status {
        UPCOMING, CONFIRM, CANCELED, MISSING
    }
}
