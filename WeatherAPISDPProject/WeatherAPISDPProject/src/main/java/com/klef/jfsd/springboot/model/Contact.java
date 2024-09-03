package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="contact_table")
public class Contact
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="contact_id")
  private int id;
  @Column(name="contact_name",nullable=false,length = 50)
  private String name;
  @Column(name="contact_email",nullable=false,length = 30)
  private String email;
  @Column(name="contact_message",nullable=false,length = 100)
  private String message;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  @Override
  public String toString() {
    return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", message=" + message + "]";
  }
  
}