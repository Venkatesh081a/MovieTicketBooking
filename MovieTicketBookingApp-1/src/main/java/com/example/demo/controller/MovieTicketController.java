package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookingRequestDto;
import com.example.demo.dto.BookingResponseDto;
import com.example.demo.dto.MovieRequestDto;
import com.example.demo.dto.MovieResponseDto;
import com.example.demo.exception.InvalidMoveIdException;
import com.example.demo.exception.InvalidTicketIdException;
import com.example.demo.service.MovieService;
import com.example.demo.service.TicketService;

@RestController
@RequestMapping("/theater")
public class MovieTicketController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private TicketService ticketService;

	@PostMapping("/movie")
	public ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieRequestDto movieDto) {
		if (movieDto.getTitle().isBlank() || movieDto.getDirector().isBlank() || movieDto.getLanguage().isBlank()
				|| movieDto.getYear() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		MovieResponseDto movieResponseDto = movieService.saveMovie(movieDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(movieResponseDto);
	}

	@GetMapping("/movie/{movieId}")
	public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable(name = "movieId") int id) throws InvalidMoveIdException {
		MovieResponseDto movie = movieService.getMovieById(id);
		return ResponseEntity.status(HttpStatus.OK).body(movie);
	}

	@PostMapping("/movie/ticket/booking")
	public ResponseEntity<BookingResponseDto> bookMovieTickets(@RequestBody BookingRequestDto ticketBookingDto) {
		if (ticketBookingDto.getTitle().isBlank() || ticketBookingDto.getType().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		BookingResponseDto bookingResponseDto = ticketService.bookMovieTickets(ticketBookingDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponseDto);
	}

	@GetMapping("/movie/ticket/booking/{ticketId}")
	public ResponseEntity<BookingResponseDto> getTicketInfo(@PathVariable(name = "ticketId") int id) throws InvalidTicketIdException {
		BookingResponseDto ticketDetails = ticketService.getTicketInfo(id);
		return ResponseEntity.status(HttpStatus.OK).body(ticketDetails);
	}

	@GetMapping("/movie/{movieId}/ticket")
	public ResponseEntity<List<BookingResponseDto>> getAllMovieTickets(@PathVariable(name = "movieId") int id) throws InvalidMoveIdException {
		List<BookingResponseDto> ticketsList = movieService.getAllTicketsForAMovie(id);
		return ResponseEntity.status(HttpStatus.OK).body(ticketsList);
	}

}
