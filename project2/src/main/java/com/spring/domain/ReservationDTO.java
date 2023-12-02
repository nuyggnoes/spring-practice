package com.spring.domain;

import lombok.Data;

@Data
public class ReservationDTO extends AccommodationDTO{
	private int customer_id;
	private int acc_id;
	private String check_in;
	private String check_out;
}
