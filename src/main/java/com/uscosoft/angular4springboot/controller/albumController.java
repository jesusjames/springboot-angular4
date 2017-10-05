package com.uscosoft.angular4springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uscosoft.angular4springboot.service.AlbumService;

@RestController
@RequestMapping("/album")
public class albumController {
	
	@Autowired
    AlbumService albumService;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/fotos", method = RequestMethod.GET)
    public List<?> findAll() { 	
		return albumService.ListAllPhotos();
    }

}
