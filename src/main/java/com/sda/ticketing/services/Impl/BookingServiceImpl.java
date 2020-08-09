package com.sda.ticketing.services.Impl;

import com.sda.ticketing.Dto.BookingDto;
import com.sda.ticketing.exceptions.BookingException;
import com.sda.ticketing.exceptions.ErrorHandler;
import com.sda.ticketing.models.Booking;
import com.sda.ticketing.repository.BookingRepository;
import com.sda.ticketing.services.BookingService;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<Booking> getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public Mono<Booking> getBookingBySeatIdAndSessionId(String seatId,String sessionId, Boolean active) {
        return bookingRepository.findBySeatIdAndSessionIdAndActive(seatId,sessionId,active);
    }


    @Override
    public Mono<Booking> cancelBooking(String bookingId) {
        return bookingRepository.findById(bookingId)
                .flatMap(booking -> {
                    booking.setActive(false);
                    booking.setCancelledDate(System.currentTimeMillis());
                    return bookingRepository.save(booking);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Booking> bookSeat(BookingDto bookingDto) {
        return getBookingBySeatIdAndSessionId(bookingDto.getSeatId(),bookingDto.getSessionId(),true)
                .log()
                .doOnNext(booking -> {
                    if(booking != null){
                        throw new BookingException("This seat is already booked for this session");
                    }
                })
                .switchIfEmpty(book(bookingDto))
                .doOnError(ErrorHandler::handleError);
    }

    @Override
    public Mono<Booking> getBookingByTicketNo(String ticketNo) {
        return bookingRepository.findByTicketNo(ticketNo);
    }

    @Override
    public Flux<Booking> getBookings(String userId, Boolean active) {
        return bookingRepository.findByUserIdAndActive(userId,active);
    }

    @Override
    public Flux<Booking> getBookings(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public Flux<Booking> getBookingBySession(String sessionId) {
        return bookingRepository.findBySessionId(sessionId);
    }

    /**
     * Books a sets for a specific session
     * @param bookingDto
     * @return
     */

    private Mono<Booking> book(BookingDto bookingDto){
        Booking booking = modelMapper.map(bookingDto,Booking.class);
        booking.setBookingDate(System.currentTimeMillis());
        booking.setTicketNo(generateTicketNumber());
        return bookingRepository.save(booking);
    }

    /**
     * Generates a ticket number
     * @return String
     */

    private String generateTicketNumber(){
        return "TCK" +
                Calendar.getInstance().get(Calendar.YEAR) +
                Calendar.getInstance().get(Calendar.MONTH) +
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) +
                RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }
}
