package com.codecool.barbershop.barbershop.booking.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardData {

    private int totalClients;
    private int totalUpcomingBookings;
    private int totalUpcomingBookingsCurrentMonth;
    private int totalConfirmedBookingsCurrentMonth;
    private List<SevenDayReport> sevenDayReportList;

}

