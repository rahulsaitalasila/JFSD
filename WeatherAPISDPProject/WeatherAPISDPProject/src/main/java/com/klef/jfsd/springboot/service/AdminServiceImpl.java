package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Contact;
import com.klef.jfsd.springboot.model.User;
import com.klef.jfsd.springboot.repository.AdminRepository;
import com.klef.jfsd.springboot.repository.ContactRepository;
import com.klef.jfsd.springboot.repository.UserRepository;

@Service

public class AdminServiceImpl implements AdminService
{
  @Autowired
  private AdminRepository adminRepository;
  
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ContactRepository contactRepository;
  @Override
  public List<User> viewallusers() {
    
    return userRepository.findAll();
  }

  @Override
  public User viewuserbyid(int eid) {
    
    Optional<User> obj =  userRepository.findById(eid);
        
        if(obj.isPresent())
        {
          User user = obj.get();
          
          return user;
        }
        else
        {
          return null;
        }
  }

  @Override
  public String deleteuser(int eid) {
    
    Optional<User> obj =  userRepository.findById(eid);
         
         String msg = null;
         
         if(obj.isPresent())
         {
           User user = obj.get();
           
           userRepository.delete(user);
           
           msg = "user Deleted Successfully";
         }
         else
         {
           msg = "user Not Found";
         }
         
         return msg;
  }

  @Override
  public Admin checkadminlogin(String uname, String pwd) {
    
    return adminRepository.checkadminlogin(uname, pwd);
  }

  @Override
  public List<Contact> viewallcontact() 
  {
    return contactRepository.findAll();
  }
  
  

}