package com.codecool.barbershop.barbershop.booking;

import com.codecool.barbershop.barbershop.client.ClientModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
public class BookingModel {

    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.NONE)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "booking_status_id", referencedColumnName = "Id")
    private BookingStatus status;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler", "fieldHandler"})
    private ClientModel clientModel;
}
