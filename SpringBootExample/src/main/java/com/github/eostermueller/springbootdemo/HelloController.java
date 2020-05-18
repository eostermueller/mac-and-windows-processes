package com.github.eostermueller.springbootdemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/root")
@RestController
public class HelloController {
	@Autowired
	MyService  myService;
	
	@GetMapping(value="hello/{name}")
	public String hello(@PathVariable("name") String name){
		return myService.getMessage(name);
	}
	@PostMapping(value="save")
	public String save(@RequestBody String user){
		return myService.saveUser(user);
	}	
}