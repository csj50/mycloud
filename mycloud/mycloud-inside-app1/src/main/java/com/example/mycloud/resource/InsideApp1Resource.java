package com.example.mycloud.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insideApp1")
public class InsideApp1Resource {

	@GetMapping("/remoteApi/{id}")
	public String remoteApi(@PathVariable String id) {
		return "hello:"+id;
	}
}
