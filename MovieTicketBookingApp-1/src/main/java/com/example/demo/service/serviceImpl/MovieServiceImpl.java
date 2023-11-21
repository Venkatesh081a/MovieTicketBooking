package com.example.demo.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookingResponseDto;
import com.example.demo.dto.MovieRequestDto;
import com.example.demo.dto.MovieResponseDto;
import com.example.demo.entity.MovieEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.exception.InvalidMoveIdException;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public MovieResponseDto saveMovie(MovieRequestDto movieDto) {
		MovieEntity movie = new MovieEntity();
		movie.setTitle(movieDto.getTitle());
		movie.setDirector(movieDto.getDirector());
		movie.setLanguage(movieDto.getLanguage());
		movie.setYear(movieDto.getYear());
		MovieEntity savedMovie = movieRepository.save(movie);
		MovieResponseDto movieResponseDto = new MovieResponseDto();
		movieResponseDto.setId(savedMovie.getId());
		movieResponseDto.setTitle(savedMovie.getTitle());
		movieResponseDto.setDirector(savedMovie.getDirector());
		movieResponseDto.setLanguage(savedMovie.getLanguage());
		return movieResponseDto;

	}

	@Override
	public MovieResponseDto getMovieById(int id) throws InvalidMoveIdException {
		Optional<MovieEntity> movie = movieRepository.findById(id);
		if (movie.isPresent()) {
			MovieEntity existingMovie = movie.get();
			MovieResponseDto movieResponseDto = new MovieResponseDto();
			movieResponseDto.setId(existingMovie.getId());
			movieResponseDto.setTitle(existingMovie.getTitle());
			movieResponseDto.setDirector(existingMovie.getDirector());
			movieResponseDto.setLanguage(existingMovie.getLanguage());
			return movieResponseDto;
		}
		throw new InvalidMoveIdException("Movie Id not found");
	}

	@Override
	public List<BookingResponseDto> getAllTicketsForAMovie(int id) throws InvalidMoveIdException {
		Optional<MovieEntity> movie = movieRepository.findById(id);
		List<BookingResponseDto> ticketsList = new ArrayList<>();
		if (movie.isPresent()) {
			MovieEntity existingMovie = movie.get();
			List<TicketEntity> existingTickets = existingMovie.getTickets();
			for (int i = 0; i < existingTickets.size(); i++) {
				TicketEntity existingTicket = existingTickets.get(i);
				BookingResponseDto ticket = new BookingResponseDto();
				ticket.setId(existingTicket.getId());
				ticket.setTitle(existingTicket.getMovie().getTitle());
				ticket.setType(existingTicket.getType());
				ticket.setUnitPrice(existingTicket.getUnitPrice());
				ticket.setNoOfTickets(existingTicket.getNoOfTickets());
				ticket.setPrice(existingTicket.getPrice());
				ticketsList.add(ticket);
			}
			return ticketsList;
		}
		throw new InvalidMoveIdException("Movie Id not found");
	}

}
