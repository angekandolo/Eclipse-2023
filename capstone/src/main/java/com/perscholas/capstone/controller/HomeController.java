package com.perscholas.capstone.controller;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class HomeController {
	@GetMapping("/")
    public String home() {
        return "home";
    }
	
	@GetMapping("/index")
    public String index() {
        return "index";
    }
	
	
	@GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }
	
	@GetMapping("/SoloLeveling")
    public String sololeveling() {
		
        return "/SoloLeveling";
    }
	 @GetMapping("/naruto")
	    public String Naruto() {
	        return "/Naruto";
	 }
	 

	
	 @GetMapping("/OshiNoKo")
	    public String OshiNoKo() {
	        return "/OshinoKo";
	 }
	
	 
	 @GetMapping("/JujutsuKaisen")
	    public String JujutsuKaisen() {
	        return "/JujutsuKaisen";
	 }
	 
	 
	 @GetMapping("/KimetsunoYaiba")
	    public String KimetsunoYaiba() {
	        return "/KimetsunoYaiba";
	 }

}
