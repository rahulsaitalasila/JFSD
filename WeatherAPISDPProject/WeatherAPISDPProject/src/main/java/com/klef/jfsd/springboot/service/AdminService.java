package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Contact;
import com.klef.jfsd.springboot.model.User;

public interface AdminService 
{

	public List<User> viewallusers();
	public User viewuserbyid(int eid);
	public String deleteuser(int eid);
	public Admin checkadminlogin(String uname, String pwd);
	public List<Contact> viewallcontact();

}
