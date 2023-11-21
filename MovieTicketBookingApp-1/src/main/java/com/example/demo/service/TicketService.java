package com.example.demo.service;

import com.example.demo.dto.BookingRequestDto;
import com.example.demo.dto.BookingResponseDto;
import com.example.demo.exception.InvalidTicketIdException;

public interface TicketService {

	BookingResponseDto bookMovieTickets(BookingRequestDto ticketBookingDto);

	BookingResponseDto getTicketInfo(int id) throws InvalidTicketIdException;

}
