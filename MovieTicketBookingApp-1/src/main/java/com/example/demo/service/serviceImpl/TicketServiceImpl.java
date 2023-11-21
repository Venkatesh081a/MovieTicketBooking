package com.example.demo.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookingRequestDto;
import com.example.demo.dto.BookingResponseDto;
import com.example.demo.entity.MovieEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.exception.InvalidTicketIdException;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.TicketRepostiory;
import com.example.demo.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepostiory ticketRepostiory;

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public BookingResponseDto bookMovieTickets(BookingRequestDto ticketBookingDto) {
		Optional<MovieEntity> movie = movieRepository.findByTitle(ticketBookingDto.getTitle());
		if (movie.isPresent()) {
			MovieEntity existingMovie = movie.get();
			TicketEntity ticket = new TicketEntity();
			ticket.setType(ticketBookingDto.getType());
			int pricePerTicket = ticketBookingDto.getUnitPrice();
			int noOfTickets = ticketBookingDto.getNoOfTickets();
			ticket.setUnitPrice(pricePerTicket);
			ticket.setNoOfTickets(noOfTickets);
			ticket.setPrice(noOfTickets * pricePerTicket);
			ticket.setMovie(existingMovie);
			TicketEntity bookedTicket = ticketRepostiory.save(ticket);
			List<TicketEntity> ticketsList = existingMovie.getTickets();
			ticketsList.add(bookedTicket);
			movieRepository.save(existingMovie);

			BookingResponseDto bookingResponseDto = new BookingResponseDto();
			bookingResponseDto.setId(bookedTicket.getId());
			bookingResponseDto.setTitle(existingMovie.getTitle());
			bookingResponseDto.setType(bookedTicket.getType());
			bookingResponseDto.setUnitPrice(bookedTicket.getUnitPrice());
			bookingResponseDto.setNoOfTickets(bookedTicket.getNoOfTickets());
			bookingResponseDto.setPrice(bookedTicket.getPrice());
			return bookingResponseDto;
		}
		return null;
	}

	@Override
	public BookingResponseDto getTicketInfo(int id) throws InvalidTicketIdException {
		Optional<TicketEntity> ticket = ticketRepostiory.findById(id);
		if (ticket.isPresent()) {
			TicketEntity existingTicket = ticket.get();
			BookingResponseDto ticketDetails = new BookingResponseDto();
			ticketDetails.setId(existingTicket.getId());
			ticketDetails.setTitle(existingTicket.getMovie().getTitle());
			ticketDetails.setType(existingTicket.getType());
			ticketDetails.setUnitPrice(existingTicket.getUnitPrice());
			ticketDetails.setNoOfTickets(existingTicket.getNoOfTickets());
			ticketDetails.setPrice(existingTicket.getPrice());
			return ticketDetails;
		}
		throw new InvalidTicketIdException("Ticket Id not found");
	}

}
