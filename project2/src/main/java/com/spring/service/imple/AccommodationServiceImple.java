package com.spring.service.imple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.AccommodationDTO;
import com.spring.domain.ReservationDTO;
import com.spring.domain.UserDTO;
import com.spring.persistence.AccommodationRepository;
import com.spring.persistence.LoginRepository;
import com.spring.persistence.UserRepository;
import com.spring.service.AccommodationService;
import com.spring.service.LoginService;
import com.spring.service.UserService;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationServiceImple implements AccommodationService{
	
	private final AccommodationRepository accRepository;

	@Override
	public List<AccommodationDTO> getAllAccommodation() {
		return accRepository.selectAllAccommodation();
	}

	@Override
	public List<AccommodationDTO> findAccommodationBySearch(String keyword) {
		return accRepository.selectAccommodationByKeyword(keyword);
	}

	@Override
	public AccommodationDTO findAccommodationById(int acc_id) {
		return accRepository.selectAccommodationByAccId(acc_id);
	}

	@Override
	public boolean reserveAccommodation(ReservationDTO reserveDto){
		
		List<ReservationDTO> reserveList = new ArrayList<>();
		String newCheckIn = reserveDto.getCheck_in();
		String newCheckOut = reserveDto.getCheck_out();
		reserveList = accRepository.selectReservationByAccId(reserveDto.getAcc_id());

		
		boolean isAvailable = true;
		
		for (ReservationDTO reservation : reserveList) {
		    String existCheckIn = reservation.getCheck_in();
		    String existCheckOut = reservation.getCheck_out();

		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		    Date newCheckInDate;
		    Date newCheckOutDate;
		    Date existCheckInDate; 
		    Date existCheckOutDate; 
			try {
				newCheckInDate = dateFormat.parse(newCheckIn);
				newCheckOutDate = dateFormat.parse(newCheckOut);
				existCheckInDate = dateFormat.parse(existCheckIn);
				existCheckOutDate = dateFormat.parse(existCheckOut);
				
				if ((newCheckInDate.before(existCheckOutDate) && newCheckOutDate.after(existCheckInDate)) ||
				        (newCheckInDate.equals(existCheckOutDate) || newCheckOutDate.equals(existCheckInDate))) {
				        isAvailable = false;
				        break;
				    }
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(isAvailable) {
			accRepository.insertReservationDate(reserveDto);
		}
		return isAvailable;
	}

	@Override
	public List<ReservationDTO> findAllReservedAccommodation(int user_id) {
		return accRepository.selectReservedAccommodation(user_id);
	}

	@Override
	public void registerAccommodation(AccommodationDTO accDto) {
		accRepository.insertNewAccommodation(accDto);
	}

	@Override
	public void removeReservation(ReservationDTO dto) {
		accRepository.deleteReservation(dto);
	}

	@Override
	public List<ReservationDTO> findAllRegisteredAccommodation(int user_id) {
		return accRepository.selectRegisteredAccommodation(user_id);
	}

	@Override
	public void removeRegAcc(ReservationDTO dto) {
		accRepository.deleteRegAcc(dto);
	}

}
