package com.klef.jfsd.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import com.klef.jfsd.springboot.model.Contact;

import com.klef.jfsd.springboot.model.User;

public interface UserService {
	
	public String adduser(User emp);
	public String updateuser(User emp);
	public User viewuserbyid(int eid);
	public User checkuserlogin(String email,String pwd);
	public String insertcontact(Contact contact);


    public List<User> getAllUsers();


}
