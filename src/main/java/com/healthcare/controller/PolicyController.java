package com.healthcare.controller;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.healthcare.model.Policy;
import com.healthcare.model.User;
import com.healthcare.repo.PolicyRepository;
import com.healthcare.repo.UserRepository;



@Controller
@RequestMapping("/policy")
public class PolicyController {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private PolicyRepository policyRepository;
	
	
	@ModelAttribute //work for all handler
	public void addcommonData(Model m,Principal p) {
		
		String uname=p.getName();
		System.out.println(uname);
		
		User user=userrepo.getUserByUserName(uname);
		System.out.println(user);
		m.addAttribute("user", user);
		
		
	}
	
	
	@GetMapping("/index")
	public String index(Model m, Principal principal) {  
		//we can get unique identifier
		
		m.addAttribute("title", "Policy DashBoard");
		return "normal/policy_dashboard";
	}
	
	@GetMapping("/add_policy")
	public String openAddPolicyform(Model m) {
		
		m.addAttribute("title", "Add Policy");
		m.addAttribute("conact",new Policy());
		return "normal/add_policy";
		
	}
	
	
	@PostMapping("/process_policy")
	public  String handleContactForm(
			@ModelAttribute("contact") Policy policy,Principal principal,HttpSession session) {
		try {
			String name=principal.getName();
			User user=this.userrepo.getUserByUserName(name);
			
			policy.setUser(user);
			
			user.getPolicies().add(policy);
			
			
			
			
						
			this.userrepo.save(user);
			
			System.out.println(policy);
			System.out.println("Added to database");
			
			session.setAttribute("message",new com.healthcare.help.Message("You successfully subscribed to this policy","success"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
			//System.out.println(e.printStackTrace());
			session.setAttribute("message",new com.healthcare.help.Message("Something went wrong","danger"));
		}
		
		return "normal/add_policy";
		
		
	}
	
	
	
	@GetMapping("/show_policy/{page}")
	public String showPolicies(@PathVariable("page") Integer page, Model m,Principal principal) {
		m.addAttribute("title", "Show Policies");
		String uname=principal.getName();
		User user=this.userrepo.getUserByUserName(uname);
		//List<Contact> contacts=user.getContacts();
		
	//List<Contact> contacts=	contactRepository.findContactByUser(user.getId());
		
		Pageable  pageable=PageRequest.of(page, 3);
		Page<Policy> policies=policyRepository.findPolicyByUser(user.getUserid(), pageable);
		
	
		
	m.addAttribute("policies", policies);
	m.addAttribute("currentpage", page);
	m.addAttribute("totalpage",policies.getTotalPages());
		
		return "normal/show_policy";
		
	}
	
	
	
	
	@GetMapping("/{pid}/show")
	public String showpertdetail(@PathVariable("pid")Integer pid,Model m ,Principal principal) {
		m.addAttribute("title", "Show policy detail");
		
		Optional<Policy> policyoptional= this.policyRepository.findById(pid);
		Policy policy=policyoptional.get();
		
		
		
		String uname=principal.getName();
		User user=this.userrepo.getUserByUserName(uname);
		
		
		if(user.getUserid()==policy.getUser().getUserid()) {
			m.addAttribute("policy", policy);
		}
		
				
		return "normal/policy_detail";
	}

	
	
	@GetMapping("/delete/{pid}")
	public String deletePolicy(@PathVariable("pid") Integer pid,HttpSession session,Principal principal) { //th:href="@{'/user/delete/'+${c.cid}}"
		
		
		
		Policy policy=this.policyRepository.findById(pid).get();
		
		
		
		User user=this.userrepo.getUserByUserName(principal.getName());
		user.getPolicies().remove(policy);
		
		this.userrepo.save(user);
		
	
		
		//contact.setUser(null);
	
		
		//this.contactRepository.delete(contact);
		
		session.setAttribute("message", new com.healthcare.help.Message("contact deleted successfully","success"));
		
		return "redirect:/policy/show_policy/0";
		
	}
	
	
	@PostMapping("/update/{pid}")
	public String updatePolicy(@PathVariable("pid")Integer pid,Model m) {
		
		Policy policy=this.policyRepository.findById(pid).get();

		
		m.addAttribute("title", "Update");
		m.addAttribute("policy", policy);
		
		return "normal/updatepolicy.html";
		
	}
	
	
	
	
	@PostMapping("/update_policy")
	public String updateContacthandler(@ModelAttribute("policy") Policy policy,
			Model m,HttpSession s,Principal principal) {
		try {
			
			Policy oldpolicy=this.policyRepository.findById(policy.getPid()).get();
			
			
			
			
			
			
			User user=this.userrepo.getUserByUserName(principal.getName());
			policy.setUser(user);
			
			
		this.policyRepository.save(policy);
		s.setAttribute("message", new com.healthcare.help.Message("Policy updated successfully","success"));
		
		}catch(Exception e) {
		
			
		}
		
		
	
		
		return "redirect:/policy/"+policy.getPid()+"/show";
		
	}
	
	
	
	@GetMapping("/profile")
	public String profile(Model m) {
		m.addAttribute("title", "profile");
		
		
		return "normal/profile.html";
		
	}

	
	


}
