package com.nitin.roomate;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomateController {
	
	private AtomicLong counter = new AtomicLong();
	private String template = "Hello, %s";
	
	@GetMapping("/hello")
	public Roomate hello(@RequestParam(name = "name", defaultValue = "World!")
			String name) {
		return new Roomate(counter.incrementAndGet(), String.format(template, name));
	}

}
