package com.healthcare.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.help.Message;
import com.healthcare.model.User;
import com.healthcare.repo.UserRepository;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("title", "Home-CareEase");
		return "home";
	}
	
	
	@GetMapping("/plans")
	public String plans(Model m) {
		m.addAttribute("title", "Plans-CareEase");
		return "plan";
	}
	
	
	
	
	@GetMapping("/signup")
	public String signup(Model m) {
		m.addAttribute("title","signup-CareEase");
		m.addAttribute("user",new User());
		return "signup";
	}
	
	
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user")User user,BindingResult result,
			@RequestParam(value="agreement",defaultValue="false")boolean  agreement,
			Model m ,HttpSession s) 
	{
		
		try {
			
			if(!agreement) {
				System.out.println("you have not agreed to terms and conditions");
				throw new Exception("you have not agreed to terms and conditions");
			}
			
			if(result.hasErrors()) {
				System.out.println("Errors"+result.toString());
				m.addAttribute("user", user);
				return "signup";
			}
			
			user.setRole("ROLE_USER");
		
		    user.setPassword(passwordEncoder.encode(user.getPassword()));
			//user.setPassword(user.getPassword());
			
			System.out.println(agreement);
			System.out.println(user);
			
			User r=this.userRepository.save(user);
			m.addAttribute("user",new User());
			s.setAttribute("message", new Message("successfully registerd","alert-success"));
			return "signup";
			
			
		}catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("user",user);
			s.setAttribute("message", new Message("something went wrong"+e.getMessage(),"alert-danger"));
			return "signup";
		}
		
		
		
	}
	
	@GetMapping("/signin")
	public String customlogin(Model m) {
		m.addAttribute("title","Login-Smart Contact Manager");
		
		return "login";
		
		
	}

}
