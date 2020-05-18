package com.github.eostermueller.springbootdemo;

import org.springframework.stereotype.Component;

@Component
public class MyService {
	public String getMessage(String name) {
	  return "Hello " + name;
	}
	public String saveUser(String user) {
      System.out.println(user);
      return "success";
	}
}
