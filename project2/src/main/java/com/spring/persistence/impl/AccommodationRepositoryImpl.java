package com.spring.persistence.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.domain.AccommodationDTO;
import com.spring.domain.ReservationDTO;
import com.spring.domain.UserDTO;
import com.spring.mapper.ProjectMapper;
import com.spring.persistence.AccommodationRepository;
import com.spring.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
@RequiredArgsConstructor
public class AccommodationRepositoryImpl implements AccommodationRepository{
	
	//private final TestMapper mapper;
	private final ProjectMapper mapper;

	@Override
	public List<AccommodationDTO> selectAllAccommodation() {
		return mapper.selectAllAccommodation();
	}

	@Override
	public List<AccommodationDTO> selectAccommodationByKeyword(String keyword) {
		return mapper.selectAccommodationByKeyword(keyword);
	}

	@Override
	public AccommodationDTO selectAccommodationByAccId(int acc_id) {
		return mapper.selectAccommodationByAccId(acc_id);
	}

	@Override
	public List<ReservationDTO> selectReservationByAccId(int acc_id) {
		return mapper.selectReservationByAccId(acc_id);
	}

	@Override
	public void insertReservationDate(ReservationDTO reserveDto) {
		mapper.insertReservationDate(reserveDto);
	}

	@Override
	public List<ReservationDTO> selectReservedAccommodation(int user_id) {
		return mapper.selectReservedAccommodation(user_id);
	}

	@Override
	public void insertNewAccommodation(AccommodationDTO accDto) {
		log.info("-------------repository()----------------");
		mapper.insertNewAccommodation(accDto);
	}

	@Override
	public void deleteReservation(ReservationDTO dto) {
		log.info("-------------remove repository()----------------");
		mapper.deleteReservation(dto);
	}

	@Override
	public List<ReservationDTO> selectRegisteredAccommodation(int user_id) {
		log.info("-------------registered repository()----------------");
		return mapper.selectRegisteredAccommodation(user_id);
	}

	@Override
	public void deleteRegAcc(ReservationDTO dto) {
		mapper.deleteRegAcc(dto);
	}
}
