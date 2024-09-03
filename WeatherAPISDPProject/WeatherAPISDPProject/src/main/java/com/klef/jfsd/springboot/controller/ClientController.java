package com.klef.jfsd.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Contact;
import com.klef.jfsd.springboot.model.User;
import com.klef.jfsd.springboot.model.WeatherData;
import com.klef.jfsd.springboot.repository.UserRepository;
import com.klef.jfsd.springboot.repository.WeatherDataRepository;
import com.klef.jfsd.springboot.service.AdminService;
import com.klef.jfsd.springboot.service.EmailService;
import com.klef.jfsd.springboot.service.UserService;
import com.klef.jfsd.springboot.service.WeatherService;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClientController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String main() {
		
		return "index";
	}
	
	@GetMapping("userreg")
	public ModelAndView userregistraion() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userreg");
		return mv;
		
	}
	@GetMapping("userlogin")
	public ModelAndView userlogin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userlogin");
		return mv;
		
	}
	@PostMapping("checkuserlogin")
	public  ModelAndView checkuserlogin(HttpServletRequest request) {
	     ModelAndView mv = new ModelAndView();
	     
	     String email = request.getParameter("email");
	     String pwd = request.getParameter("pwd");
	     
	     User user = userService.checkuserlogin(email, pwd);
	     if(user!=null) {
	    	 
	    	 HttpSession session = request.getSession();
	    	 
	    	 session.setAttribute("eid", user.getId());
	    	 session.setAttribute("ename", user.getName());
	    	 
	    	 
	    	 
	    	 mv.setViewName("userhome");
	     }
	     else {
	    	 mv.setViewName("userlogin");
	    	 mv.addObject("message","Login Failed");
	     }

	     return mv;
	}
	@GetMapping("userhome")
	   public ModelAndView userhome(HttpServletRequest request)
	   {
	     ModelAndView mv = new ModelAndView();
	     mv.setViewName("userhome");
    	 HttpSession session = request.getSession();
    	  
    	 int eid = (int)session.getAttribute("eid"); // eid is a session variable 
    	 String ename =(String)session.getAttribute("ename");  // ename is a session variable
    	 
    	 mv.addObject("eid",eid);
    	 mv.addObject("ename",ename);

	     
	     return mv;
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
	
	@PostMapping("insertuser")
	  public ModelAndView insertaction(HttpServletRequest request) {
	       ModelAndView mv = new ModelAndView();
	       String msg = null;
	       try 
	       {
	         String name = request.getParameter("name");
	         String email = request.getParameter("email");
	         String pwd = request.getParameter("pwd");
	         String location = request.getParameter("location");
	         String contact = request.getParameter("contact");
	         
	         User user = new User();
	          user.setName(name);
	            user.setEmail(email);
	            user.setPassword(pwd);
	            user.setLocation(location);
	            user.setContact(contact);
	            user.setActive(true);
	         
	            msg = userService.adduser(user);
	            emailService.sendEmail(user.getEmail(),"Demo mail","Hi You are successfully registered in weatherapi application");
	            mv.setViewName("displaymsg");
	            mv.addObject("message",msg);
	       } 
	       catch (Exception e) 
	       {
	      mv.setViewName("displayerror");
	      
	      msg = e.getMessage();
	          mv.addObject("message",msg);
	       }
	       return mv;
	  }
	
	@GetMapping("adminhome")
	   public ModelAndView adminhome()
	   {
	     ModelAndView mv = new ModelAndView();
	     mv.setViewName("adminhome");
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
		return "redirect:/deleteuser";
	}
	@GetMapping("updateprofile")
	   public ModelAndView updateuser(HttpServletRequest request)
	   {
	     ModelAndView mv = new ModelAndView();
	     
	     HttpSession session = request.getSession();
	     
	     mv.setViewName("updateprofile");
	     
	     mv.addObject("eid", session.getAttribute("eid"));
	     mv.addObject("ename", session.getAttribute("ename"));
	     
	     int id = (int) session.getAttribute("eid");
	     
	     User user = userService.viewuserbyid(id);
	     
	     mv.addObject("user", user);
	     
	     return mv;
	   }
	   
	   @PostMapping("update")
	   public ModelAndView updateaction(HttpServletRequest request)
	   {
	     String msg = null;
	     
	     ModelAndView mv = new ModelAndView();
	     
	       HttpSession session = request.getSession();
	     
	     mv.addObject("eid", session.getAttribute("eid"));
	     mv.addObject("ename", session.getAttribute("ename"));
	     
	     int id = (int) session.getAttribute("eid");
	     
	    try
	    {
	      String name = request.getParameter("name");
	      String email = request.getParameter("email");
	      String pwd = request.getParameter("pwd");
	      String location = request.getParameter("location");
	      String contact = request.getParameter("contact");
	      
	      User user = new User();
	       user.setId(id);
	       user.setName(name);
	       user.setEmail(email);
	       user.setPassword(pwd);
	       user.setLocation(location);
	       user.setContact(contact);
	       
	       
	       msg = userService.updateuser(user);
	       
	       mv.setViewName("userlogin");
	       mv.addObject("message",msg);
	      
	    }
	    catch(Exception e)
	    {
	      msg = e.getMessage();
	      
	      mv.setViewName("updateerror");
	       mv.addObject("message",msg);
	    }
	     
	    
	     return mv;

	   }
		@GetMapping("about")
		   public ModelAndView about()
		   {
		     ModelAndView mv = new ModelAndView();
		     mv.setViewName("about");
		     return mv;
		   }
		@GetMapping("services")
		   public ModelAndView services()
		   {
		     ModelAndView mv = new ModelAndView();
		     mv.setViewName("services");
		     return mv;
		   }
		@GetMapping("contact")
		   public ModelAndView contact(HttpServletRequest request)
		   {
			 HttpSession session = request.getSession();
	    	  
	    	 int eid = (int)session.getAttribute("eid"); // eid is a session variable 
	    	 String ename =(String)session.getAttribute("ename");  // ename is a session variable
		     ModelAndView mv = new ModelAndView();
		     mv.setViewName("contact");
		     return mv;
		   }
		@GetMapping("weather")
		   public ModelAndView weather(HttpServletRequest request)
		   {
			HttpSession session = request.getSession();
	    	  
	    	 int eid = (int)session.getAttribute("eid"); // eid is a session variable 
	    	 String ename =(String)session.getAttribute("ename");  // ename is a session variable
			
		     ModelAndView mv = new ModelAndView();
		     mv.setViewName("weather");
		     return mv;
		   }
		@GetMapping("map")
		   public ModelAndView map(HttpServletRequest request)
		   {
			HttpSession session = request.getSession();
	    	  
	    	 int eid = (int)session.getAttribute("eid"); // eid is a session variable 
	    	 String ename =(String)session.getAttribute("ename");  // ename is a session variable
		     ModelAndView mv = new ModelAndView();
		     mv.setViewName("map");
		     return mv;
		   }
		@GetMapping("news")
		   public ModelAndView news(HttpServletRequest request)
		   {
			HttpSession session = request.getSession();
	    	  
	    	 int eid = (int)session.getAttribute("eid"); // eid is a session variable 
	    	 String ename =(String)session.getAttribute("ename");  // ename is a session variable
		     ModelAndView mv = new ModelAndView();
		     mv.setViewName("news");
		     return mv;
		   }
		@PostMapping("insertcontact")
	    public ModelAndView insertcontact(HttpServletRequest request) {
	         ModelAndView mv = new ModelAndView();
	         String msg = null;
	         try 
	         {
	           String name = request.getParameter("name");
	           String email = request.getParameter("email");
	           String msgs=request.getParameter("message");
	           
	           Contact contact=new Contact();
	           
	           contact.setName(name);
	           contact.setEmail(email);
	           contact.setMessage(msgs);
	           
	              msg = userService.insertcontact(contact);
	              mv.setViewName("contact");
	              mv.addObject("message",msg);
	         } 
	         catch (Exception e) 
	         {
	        mv.setViewName("contact");
	        
	        msg = e.getMessage();
	            mv.addObject("message",msg);
	         }
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
	    private WeatherService weatherService;
	    @Autowired
	    private WeatherDataRepository weatherDataRepository;

	    @GetMapping("weatherdb")
	    public String weatherdb(Model model) {
	        // Fetch and display weather data from the database
	        List<WeatherData> weatherDataList = weatherDataRepository.findAll();
	        model.addAttribute("weatherDataList", weatherDataList);
	        return "weatherdb";
	    }

	  
	    @PostMapping("search")
	    public String searchWeather(@RequestParam("city") String city, Model model) {
	        WeatherData weatherData = weatherService.fetchWeatherData(city);
	        model.addAttribute("weatherData", weatherData);
	        return "weatherdb";
	    }
	    
	   
	    
	    @Autowired
	    private EmailService emailService;

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

	         return new ModelAndView("redirect:/send-email");	
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
