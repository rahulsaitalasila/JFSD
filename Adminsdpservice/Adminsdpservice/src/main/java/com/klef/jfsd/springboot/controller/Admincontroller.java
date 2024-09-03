package com.klef.jfsd.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Contact;
import com.klef.jfsd.springboot.model.User;
import com.klef.jfsd.springboot.repository.UserRepository;
import com.klef.jfsd.springboot.service.AdminService;
import com.klef.jfsd.springboot.service.EmailService;
import com.klef.jfsd.springboot.service.UserService;


import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class Admincontroller {
	
	@Autowired
	private AdminService adminService;

	@GetMapping("/")
	@ResponseBody
	public String Admin()
	{
		return "Admin sdp microservice";
	}
	
	@GetMapping("adminlogin") 
	   public ModelAndView adminlogin(){
	     ModelAndView mv = new ModelAndView();
	     mv.setViewName("adminlogin");
	     return mv;
	   }
	@PostMapping("checkadminlogin")
	public ModelAndView checkadminlogin(HttpServletRequest request) {
	     String uname = request.getParameter("uname");
    	 String pwd = request.getParameter("pwd");
    	 
    	 Admin a = adminService.checkadminlogin(uname, pwd);
	     ModelAndView mv = new ModelAndView();

	     if(a!=null) {
	    	 mv.setViewName("adminhome");
	     }
	     else {
	    	 mv.setViewName("adminlogin");
	    	 mv.addObject("message","Login Failed");
	     }
	     return mv;

	}
	@GetMapping("viewallusers")
	public ModelAndView viewallusers() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewallusers");
		
		List<User> userlist = adminService.viewallusers();
		mv.addObject("userdata", userlist);
		
		return mv;
	}
	
	@GetMapping("adminhome")
    public ModelAndView adminhome()
    {
    long empcount=adminService.usercount();
      ModelAndView mv = new ModelAndView();
      mv.setViewName("adminhome");
      mv.addObject("ecount", empcount);
      return mv;
    }
	@GetMapping("view")
	   public ModelAndView viewuserdemo(@RequestParam("id") int eid)
	   {
	     User user = adminService.viewuserbyid(eid);
	     ModelAndView mv = new ModelAndView();
	     mv.setViewName("viewuserbyid");
	     mv.addObject("user", user);
	     return mv;
	   }
	@GetMapping("deleteuser")
	   public ModelAndView deleteuser()
	   {
	     ModelAndView mv = new ModelAndView();
	     mv.setViewName("deleteuser");
	     
	     List<User> userlist =  adminService.viewallusers();
	     
	     mv.addObject("userdata", userlist);
	     
	     return mv;
	   }
	
	@GetMapping("delete/{id}")
	public String deleteaction(@PathVariable("id") int eid) {
		adminService.deleteuser(eid);
		return "redirect:/admin/deleteuser";
	}
	
	  
		@GetMapping("about")
		   public ModelAndView about()
		   {
		     ModelAndView mv = new ModelAndView();
		     mv.setViewName("about");
		     return mv;
		   }
		@GetMapping("contact")
		   public ModelAndView contact()
		   {
		     ModelAndView mv = new ModelAndView();
		     mv.setViewName("contact");
		     return mv;
		   }
		
		
		
		@GetMapping("viewallcontact")
		public ModelAndView viewallcontact() {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("viewallcontact");
			List<Contact> contactlist = adminService.viewallcontact();
			mv.addObject("contactdata", contactlist);
			
			return mv;
		}
		
		@Autowired
		private EmailService emailService;
		
		@Autowired
		
		private UserService userService;  
		
	      @GetMapping("/alertemail")
	      public String showSendEmailPage(Model model) {
	          return "alertemail";
	      }
	      @PostMapping("sendWeatherAlerts")
	       public ModelAndView sendWeatherAlerts(RedirectAttributes redirectAttributes, @RequestParam("subject") String subject, @RequestParam("message") String message) {
	           String msg = null;
	           try {
	               List<User> userList = userService.getAllUsers();
	               for (User user : userList) {
	                   String userEmail = user.getEmail();
	                   emailService.sendEmail(userEmail, subject, message);
	               }
	               msg = "Weather alerts sent successfully to all users.";
	           } catch (Exception e) {
	               e.printStackTrace();
	               msg = "Failed to send weather alerts. Please try again later.";
	           }

	           redirectAttributes.addFlashAttribute("message", msg);

	           return new ModelAndView("redirect:/admin/send-email");  
	           }
		    
		    
		    @Autowired
		    private UserRepository  userRepository;
		    @GetMapping("/send-email")
		    public String showSendEmailPagee(Model model) {
		        List<String> locations = userRepository.findDistinctLocations();
		        model.addAttribute("locations", locations);
		        return "sendEmail";
		    }
		    @Autowired
		    private JavaMailSender javaMailSender;
		    @PostMapping("/send-email")
		    public String sendEmailToLocation(@RequestParam String location, @RequestParam String subject, @RequestParam String message, Model model) {
		        List<User> users = userRepository.findByLocation(location);

		        for (User user : users) {
		            if (user.isActive()) {
		                // Create and send an email to each user
		                try {
		                    SimpleMailMessage mailMessage = new SimpleMailMessage();
		                    mailMessage.setTo(user.getEmail());
		                    mailMessage.setSubject(subject);
		                    mailMessage.setText(message);
		                    javaMailSender.send(mailMessage);
		                } catch (Exception e) {
		                    // Handle any exceptions that may occur during email sending
		                    // You can log the error or take appropriate action
		                    e.printStackTrace();
		                }
		            }
		        }

		        // You may want to add a success message or handle email sending logic here
		        model.addAttribute("users", users);
		        model.addAttribute("emailSent", true); // You can use this to display a success message in the JSP
		        return "sendEmail";
		    }

}
