package com.spring.controller;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.AccommodationDTO;
import com.spring.domain.ReservationDTO;
import com.spring.domain.UserDTO;
import com.spring.service.AccommodationService;
import com.spring.service.LoginService;
import com.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Handles requests for the application home page.
 */
@Controller
@Slf4j
@RequestMapping(value = "/spring-project/*")
@RequiredArgsConstructor
public class HomeController {
	
	private final LoginService loginService;
	private final UserService userService;
	private final AccommodationService accService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/login")
	public String login() {
		log.info("------------------ login() ---------------");
		return "login";
	}
	

	@PostMapping("/login")
	public String loginPost( HttpSession session, Model model, 
							@RequestParam("username") String username, 
							@RequestParam("password") String password ) {
		if(session.getAttribute("login")!= null) {
			session.removeAttribute("login");
		}
		
		UserDTO user = new UserDTO();
		user.setUsername(username);
		user.setPassword(password);
		
		UserDTO checkUser = new UserDTO();
		checkUser = loginService.findUsernameAndPassword(user);
		
		
		if (checkUser != null) {
			session.setAttribute("user", checkUser);
			return "redirect:/spring-project/main";
		}else {
			return "alert/loginAlert";
		}
	}
	
	@PostMapping("/usernameCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam("username") String id) {
		
		boolean cnt = loginService.checkUsername(id);
		return cnt;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/spring-project/login";
	}
	
	
	
	@GetMapping("/main")
	public String main() {
		log.info("----------- main() -----------");
		return "main";
	}
	
	@PostMapping("/main")
	public String mainPost( HttpSession session, Model model, 
							@RequestParam("keyword") String keyword
							) {
		session.setAttribute("accList", accService.findAccommodationBySearch(keyword));	
		return "redirect:/spring-project/main";
	}
	
	@GetMapping("/register")
	public String register(@RequestParam(value = "username", defaultValue = "") String username) {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(
							@RequestParam("username") String username, 
							@RequestParam("password") String password,
							@RequestParam("age") String age,
							@RequestParam("phone") String phone,
							@RequestParam("address") String address,
							@RequestParam("email") String email,
							Model model) {
		
		UserDTO newUser = new UserDTO();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setAge(age);
		newUser.setPhone(phone);
		newUser.setAddress(address);
		newUser.setEmail(email);

		loginService.registerUser(newUser);
		return "redirect:/spring-project/login";
	}
	
	
	
	
	
	@GetMapping("/myPage")
	public String mypage() {
		return "myPage";
	}
	
	@PutMapping("/modify")
	@ResponseBody
	public ResponseEntity<UserDTO> modify(@RequestBody UserDTO dto, HttpServletRequest request){
		log.info("putmapping-----------------");
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		userService.updateUserInfo(dto);
		HttpSession session = request.getSession();
		session.setAttribute("user", dto);
		return new ResponseEntity<UserDTO>(dto, header, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove-user")
	public ResponseEntity<String> removeUser(@RequestBody UserDTO user){
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		// TODO 개발자 영역
		String resultJson = userService.removeUser(user);
		
		return new ResponseEntity<String>(resultJson, header, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public String detail( HttpSession session,
						 @RequestParam("acc_id") int acc_id,
						 Model model) {
		session.setAttribute("keyword", accService.findAccommodationById(acc_id));
		return "detail";
	}
	@PostMapping("/detail")
	public String detailPost(HttpServletRequest request,
							@RequestParam("check_in") String check_in, 
							@RequestParam("check_out") String check_out,
							Model model){
		
		HttpSession session = request.getSession();
		
		AccommodationDTO acc = (AccommodationDTO) session.getAttribute("keyword");
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		ReservationDTO reserveDto = new ReservationDTO();
		reserveDto.setUser_id(user.getUser_id());
		reserveDto.setCustomer_id(user.getUser_id());
		reserveDto.setAcc_id(acc.getAcc_id());
		reserveDto.setCheck_in(check_in);
		reserveDto.setCheck_out(check_out);
		
		boolean reserveSuccess = false;
		
		try {
			reserveSuccess = accService.reserveAccommodation(reserveDto);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(reserveSuccess) {
			return "alert/reservationSuccessAlert";
		}
		else {
			return "alert/reservationFailAlert";
		}
		
	}
	
	@GetMapping("/reservationSuccessAlert")
	public String resrvAlert() {
		return "";
	}

	
	@GetMapping("/reservedAccommodation")
	public String reservedAccommodation(HttpServletRequest request,
										Model model) {
		log.info("reservedAccommodation()------------get()");
		
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		model.addAttribute("reservedList", accService.findAllReservedAccommodation(user.getUser_id()));
		return "reservedAccommodation";
	}
	
	@GetMapping("/registerAccommodation")
	public String regAcc() {
		return "registerAccommodation";
//		return "upload/upload-form";
	}
	
	@PostMapping("/registerAccommodation")
	public String regAccPost(@RequestParam("imgFile") MultipartFile multipartFile) {
		
		String fileName = multipartFile.getOriginalFilename();
		log.info(fileName);
		
		log.info("---------------------registerAcc post()---------------------");
		return "redirect:/spring-project/registerAccommodation";
	}
	
	@GetMapping("/registeredAccommodation")
	public String registeredAccommodation(HttpServletRequest request,
										Model model) {
		log.info("registeredAccommodation()------------get()");
		
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		model.addAttribute("registeredList", accService.findAllRegisteredAccommodation(user.getUser_id()));
		return "registeredAccommodation";
	}
	
	@DeleteMapping("/remove-reservation")
	public ResponseEntity<String> removeResrv(@RequestBody ReservationDTO dto){
		
		log.info("remove-list() delete => list = " + dto);
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		// TODO 개발자 영역
		accService.removeReservation(dto);
		return new ResponseEntity<String>("", header, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove-registeredAcc")
	public ResponseEntity<String> removeRegAcc(@RequestBody ReservationDTO dto){
		
		log.info("removeRegAcc() delete => list = " + dto);
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		// TODO 개발자 영역
		accService.removeRegAcc(dto);
		return new ResponseEntity<String>("", header, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove-profile_img")
	public ResponseEntity<String> removeProImg(HttpServletRequest request,
												@RequestBody UserDTO dto){
		
		log.info("removeImg() delete => list = " + dto);
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		// TODO 개발자 영역
		userService.removeUserImg(dto);
		HttpSession session = request.getSession();
		session.setAttribute("user", dto);
		return new ResponseEntity<String>("", header, HttpStatus.OK);
	}
	

}
