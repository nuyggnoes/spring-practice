package com.spring.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.domain.AccommodationDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/kakao")
@Slf4j

public class KakaoController {
	/*
	 * @Autowired VueDao vdao;
	 */
	
	@GetMapping("/kakaoAjax")
	public String test() {
		log.info("-------------kakao-------------");
		return "import/kakaoAjax";
	}
	
	@RequestMapping("/pay")
	public ModelAndView serve(ModelAndView mv, HttpSession session, RedirectView rv) {
		mv.setViewName("jq/serve");
		return mv;
	}
	
	@RequestMapping("/kakaopay")
	@ResponseBody
	public String kakaopay(@RequestParam("acc_name") String name,
							@RequestParam("acc_price") String price,
							@RequestParam("acc_id") String acc_id,
							@RequestParam("cust_id") String cust_id) {
		
		try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection connServ = (HttpURLConnection) url.openConnection();
			connServ.setRequestMethod("POST");
			connServ.setRequestProperty("Authorization", "KakaoAK 3ce629a72d3bf7008674f18a5008dc00");
			connServ.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			connServ.setDoOutput(true);
			String param = "cid=TC0ONETIME&partner_order_id=partner_order_id"+
							"&partner_user_id=" + cust_id + 
							"&item_name=" + URLEncoder.encode(name,"UTF-8") + 
							"&quantity=1&total_amount=" + price + 
							"&vat_amount=200&tax_free_amount=0"
							+ "&approval_url=" + URLEncoder.encode("http://localhost:8080/kakao/paySuccess?item_name="+ URLEncoder.encode(name, "UTF-8") + "&item_price=" + URLEncoder.encode(price, "UTF-8"), "UTF-8")
							+ "&fail_url=http://localhost:8080/kakao/payFail"
							+ "&cancel_url=" + URLEncoder.encode("http://localhost:8080/kakao/payCancel?item_name=" + URLEncoder.encode(name, "UTF-8") + "&item_price=" + URLEncoder.encode(price, "UTF-8"), "UTF-8");
			log.info(param);
			OutputStream sender = connServ.getOutputStream();
			DataOutputStream dataSender = new DataOutputStream(sender);
			dataSender.writeBytes(param);
			dataSender.close();
			
			int result = connServ.getResponseCode();
			
			InputStream reciever;
			if(result == 200) {
				reciever = connServ.getInputStream();
			}
			else {
				reciever = connServ.getErrorStream();
			}
			InputStreamReader reader = new InputStreamReader(reciever);
			BufferedReader phase = new BufferedReader(reader);
			return phase.readLine();
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return "{\"result\":\"NO\"}";
	}
	
	@GetMapping("/paySuccess")
	public String paySuccess(HttpServletRequest req,
			 				@RequestParam("item_name") String item_name,
			 				@RequestParam("item_price") String item_price) {
		req.setAttribute("item_name", item_name);
		req.setAttribute("item_price", item_price);
		return "kakaoPay/paySuccess";
	}
	
	@GetMapping("/payFail")
	public String payFail(HttpServletRequest req,
						  @RequestParam("item_name") String item_name,
						  @RequestParam("item_price") String item_price) {
		log.info(item_name);
		log.info(item_price);
		req.setAttribute("item_name", item_name);
		req.setAttribute("item_price", item_price);
		return "kakaoPay/payFail";
	}
	
	@GetMapping("/payCancel")
	public String payCancel(HttpServletRequest req,
						  @RequestParam("item_name") String item_name,
						  @RequestParam("item_price") String item_price
						  ) {
		return "kakaoPay/payCancel";
	}
}