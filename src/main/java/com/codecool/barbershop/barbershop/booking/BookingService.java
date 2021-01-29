package com.codecool.barbershop.barbershop.booking;

import com.codecool.barbershop.barbershop.booking.request.BookingDTO;
import com.codecool.barbershop.barbershop.booking.request.DashboardData;
import com.codecool.barbershop.barbershop.booking.request.SevenDayReport;
import com.codecool.barbershop.barbershop.client.ClientModel;
import com.codecool.barbershop.barbershop.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

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

    public BookingModel updateStatusBooking(int bookingId, String newBookingStatus) {
        Optional<BookingModel> booking = bookingRepository.findById(bookingId);
        if (booking.isEmpty()) return null;
        else {
            booking.get().setBookingStatus(BookingStatus.valueOf(newBookingStatus.toUpperCase(Locale.ROOT)));
            return bookingRepository.save(booking.get());
        }
    }

    public List<BookingModel> getAllBookingsByClientId(Long clientId) {
        return bookingRepository.findAllByClientModel_ClientId(clientId);
    }

    public DashboardData getDataForDashBoard() {
        DashboardData data = new DashboardData();

        Date today = new Date();
        Date todayPlusSevenDays = java.sql.Date.valueOf(LocalDate.now().plusDays(7));
        Date firstDayOfTheMonth = java.sql.Date.valueOf(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        Date lastDayOfTheMonth = java.sql.Date.valueOf(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));

        data.setTotalClients(clientService.getTotalClients());
        data.setTotalUpcomingBookings(bookingRepository.countAllByBookingStatusEquals(BookingStatus.UPCOMING));
        data.setTotalUpcomingBookingsCurrentMonth(bookingRepository.countAllByUpdatedDateBetweenAndBookingStatusEquals(firstDayOfTheMonth, lastDayOfTheMonth, BookingStatus.UPCOMING));
        data.setTotalConfirmedBookingsCurrentMonth(bookingRepository.countAllByUpdatedDateBetweenAndBookingStatusEquals(firstDayOfTheMonth, lastDayOfTheMonth, BookingStatus.CONFIRM));
        data.setSevenDayReportList(getSevenDayReportList(today, todayPlusSevenDays));

        return data;
    }

    private List<SevenDayReport> getSevenDayReportList(Date today, Date todayPlusSevenDays) {
        List<BookingModel> allSevenDaysUpcomingBookings = bookingRepository.findAllByBookingDateBetweenAndBookingStatus(today, todayPlusSevenDays, BookingStatus.UPCOMING);
        List<SevenDayReport> sevenDayReportList;

        List<Integer> tempDays = allSevenDaysUpcomingBookings.stream()
                                .mapToInt(booking -> booking.getBookingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().getDayOfMonth())
                                .boxed().collect(Collectors.toList());

        List<Integer> uniqSevenDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate days = LocalDate.now().plusDays(i);
            uniqSevenDays.add(days.getDayOfMonth());
        }

        sevenDayReportList = uniqSevenDays.stream()
                .map(uniqDay -> new SevenDayReport(uniqDay, Collections.frequency(tempDays, uniqDay)))
                .collect(Collectors.toList());

        return sevenDayReportList;
    }
}
