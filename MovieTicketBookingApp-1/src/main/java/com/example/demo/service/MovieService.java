package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BookingResponseDto;
import com.example.demo.dto.MovieRequestDto;
import com.example.demo.dto.MovieResponseDto;
import com.example.demo.exception.InvalidMoveIdException;

public interface MovieService {

	MovieResponseDto saveMovie(MovieRequestDto movieDto);

	MovieResponseDto getMovieById(int id) throws InvalidMoveIdException;

	List<BookingResponseDto> getAllTicketsForAMovie(int id) throws InvalidMoveIdException;

}
