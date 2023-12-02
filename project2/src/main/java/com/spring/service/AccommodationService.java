package com.spring.service;

import java.text.ParseException;
import java.util.List;

import com.spring.domain.AccommodationDTO;
import com.spring.domain.ReservationDTO;
import com.spring.domain.UserDTO;
import com.spring.util.PageMaker;

public interface AccommodationService {

	public List<AccommodationDTO> getAllAccommodation();

	public List<AccommodationDTO> findAccommodationBySearch(String keyword);

	public AccommodationDTO findAccommodationById(int acc_id);

	public boolean reserveAccommodation(ReservationDTO reserveDto) throws ParseException;

	public List<ReservationDTO> findAllReservedAccommodation(int user_id);

	public void registerAccommodation(AccommodationDTO accDto);

	public void removeReservation(ReservationDTO dto);

	public List<ReservationDTO> findAllRegisteredAccommodation(int user_id);

	public void removeRegAcc(ReservationDTO dto);

}
