package com.spring.mapper;

import java.util.List;

import com.spring.domain.AccommodationDTO;
import com.spring.domain.ReservationDTO;
import com.spring.domain.UserDTO;
import com.spring.util.PageMaker;

public interface ProjectMapper {
	
	public String selectTime();
	
	//project
	public UserDTO selectUsernameAndPassword(UserDTO dto);
	
	public int insertUser(UserDTO user);
	public int deleteUser(UserDTO user);
	
	public int selectDuplUsername(String username);
	
	public int updateUserInfo(UserDTO user);
	
	public List<AccommodationDTO> selectAllAccommodation();

	public List<AccommodationDTO> selectAccommodationByKeyword(String keyword);
	
	public AccommodationDTO selectAccommodationByAccId(int acc_id);

	public List<ReservationDTO> selectReservationByAccId(int acc_id);

	public void insertReservationDate(ReservationDTO reserveDto);
	
	public List<ReservationDTO> selectReservedAccommodation(int user_id);
	
	public void insertNewAccommodation(AccommodationDTO accDto);

	public void deleteReservation(ReservationDTO dto);

	public List<ReservationDTO> selectRegisteredAccommodation(int user_id);

	public void deleteRegAcc(ReservationDTO dto);

	public void insertUserImg(UserDTO user);

	public void deleteUserImg(UserDTO dto);
	
}
