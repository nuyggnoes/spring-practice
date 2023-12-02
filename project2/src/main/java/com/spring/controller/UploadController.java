package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spring.domain.AccommodationDTO;
import com.spring.domain.UserDTO;
import com.spring.service.AccommodationService;
import com.spring.service.UserService;
import com.spring.util.MediaUtils;

import lombok.RequiredArgsConstructor;
//import com.spring.util.MediaUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/upload/*")
@Slf4j
@RequiredArgsConstructor
public class UploadController {
	
	@Value("#{application_properties['multipart.filesystem.path']}")
	private String uploadFolder;
	

	private final AccommodationService accService;
	private final UserService userService;
	
	@GetMapping("/uploadForm")
	public String uploadForm(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		UserDTO user= (UserDTO) session.getAttribute("user");
		log.info("----------username--------------" + user.getUsername());
		log.info("upload form");
		log.info(uploadFolder);
		return "upload/upload-form";
	}

	@RequestMapping("/uploadFormAction")
	public String uploadFormPost(@RequestParam("uploadFile") MultipartFile[] uploadFile, Model model,
								 @RequestParam("user_id") int user_id,
								 @RequestParam("accName") String name,
								 @RequestParam("category") String category,
								 @RequestParam("price") int price,
								 @RequestParam("location") String location,
								 @RequestParam("roomCnt") int roomCnt,
								 @RequestParam("toilCnt") int toilCnt
								 ) {
		AccommodationDTO accDto = new AccommodationDTO();
		accDto.setUser_id(user_id);
		accDto.setName(name);
		accDto.setCategory(category);
		accDto.setPrice(price);
		accDto.setLocation(location);
		accDto.setRoomCnt(roomCnt);
		accDto.setToilCnt(toilCnt);
		
		
		for (MultipartFile multipartFile : uploadFile) {
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(saveFile);
				accDto.setImage_name(multipartFile.getOriginalFilename());
				model.addAttribute("msg", "success file uploaded-" + multipartFile.getOriginalFilename());
				model.addAttribute("originalFileName", multipartFile.getOriginalFilename());
			} catch (Exception e) {
				model.addAttribute("msg", "fail file uploaded-" + multipartFile.getOriginalFilename());
			}
		}
		accService.registerAccommodation(accDto);
		return "redirect:/spring-project/main";
	}
	
	@RequestMapping("/uploadProfileImg")
	public String uploadProfileImg(HttpServletRequest request,
									@RequestParam("uploadFile") MultipartFile[] uploadFile, Model model,
									@RequestParam("user_id") int user_id
								 ) {
		
		log.info("uploadProfileImg - " + uploadFolder);
		
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		
		for (MultipartFile multipartFile : uploadFile) {
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(saveFile);
				user.setProfile_img(multipartFile.getOriginalFilename());
				userService.setUserImg(user);
				model.addAttribute("msg", "success file uploaded-" + multipartFile.getOriginalFilename());
				model.addAttribute("originalFileName", multipartFile.getOriginalFilename());
			} catch (Exception e) {
				model.addAttribute("msg", "fail file uploaded-" + multipartFile.getOriginalFilename());
			}
		}
		return "redirect:/spring-project/myPage?user_id=" + user.getUser_id();
	}
	
	
	@ResponseBody
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		log.info("FILE NAME: " + fileName);
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			Path path = Paths.get(uploadFolder, fileName);
			File file = new File(path.toString());
			in = new FileInputStream(file);
			if (mType != null) {
				headers.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

			}
			byte[] buffer = new byte[(int) file.length()];
			IOUtils.readFully(in, buffer);
			entity = new ResponseEntity<byte[]>(buffer, headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	@GetMapping("uploaded-image")
	   public void uploadedImage(String fileName, Model model) {
	      log.info("uploaded-image() - "+fileName);
	      model.addAttribute("fileName", fileName);
	   }
}