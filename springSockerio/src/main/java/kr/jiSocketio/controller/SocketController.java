package kr.jiSocketio.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocketController {

	
	@RequestMapping("/")
	public String mainPage() {
		System.out.println("main page");
		return "index.html";
	}
	@RequestMapping("/test")
	public String testPage() {
		System.out.println("test page");
		return "test.html";
	}
}
