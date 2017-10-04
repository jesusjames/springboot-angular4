package com.uscosoft.angular4springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.uscosoft.angular4springboot.model.Persona;
import com.uscosoft.angular4springboot.service.PersonaService;
import com.uscosoft.angular4springboot.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class PersonaController {
	
	public static final Logger logger = LoggerFactory.getLogger(PersonaController.class);
	
	@Autowired
    PersonaService personaService; //Service which will do all data retrieval/manipulation work
	
	
	// -------------------Retrieve All Personas---------------------------------------------
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/persona", method = RequestMethod.GET)
    public List findAll() { 	
		return personaService.findAllPersonas();
    }
    
	
	// -------------------Retrieve Single Persona------------------------------------------	 
    @RequestMapping(value = "/persona/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPersona(@PathVariable("id") long id) {
    	
        logger.info("Fetching Persona with id {}", id);
        Persona persona = personaService.findById(id);
        
        if (persona == null) {
            logger.error("Persona with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Persona with id " 
            		+ id + " not found"), HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }
    
    
    // -------------------Create a Persona-------------------------------------------  
    @RequestMapping(value = "/persona/", method = RequestMethod.POST)
    public ResponseEntity<?> createPersona(@RequestBody Persona persona, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Persona : {}", persona.getNombre()+" "+persona.getApellido());
        Persona currentPersona = new Persona();
        currentPersona.setNombre(persona.getNombre());
        currentPersona.setApellido(persona.getApellido());
        if (personaService.isPersonaExist(persona)) {
            logger.error("Unable to create. A Persona with name {} already exist", persona.getNombre());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Persona with name " + 
            persona.getNombre() + " already exist."),HttpStatus.CONFLICT);
        }
        personaService.createPersona(persona);
 
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/persona/{id}").buildAndExpand(persona.getId()).toUri());
        return new ResponseEntity<Persona>(currentPersona, HttpStatus.CREATED);
        //return new ResponseEntity<String>("{Usuario creado con exito}", HttpStatus.CREATED);
    }
    
    
    // ------------------- Update a Persona ------------------------------------------------
    @RequestMapping(value = "/persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePersona(@PathVariable("id") long id, @RequestBody Persona persona) {
        logger.info("Updating Persona with id {}", id);
 
        Persona currentPersona = personaService.findById(id);
 
        if (currentPersona == null) {
            logger.error("Unable to update. Persona with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Persona with id " 
            		+ id + " not found."), HttpStatus.NOT_FOUND);
        }
 
        currentPersona.setNombre(persona.getNombre());
        currentPersona.setApellido(persona.getApellido());
 
        personaService.updatePersona(currentPersona);
        return new ResponseEntity<Persona>(currentPersona, HttpStatus.OK);
    }
    
    
    // ------------------- Delete a Persona-----------------------------------------
    @RequestMapping(value = "/persona/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePersona(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Persona with id {}", id);
 
        Persona currentPersona = personaService.findById(id);
        
        if (currentPersona == null) {
            logger.error("Unable to delete. Persona with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Persona with id " 
            		+ id + " not found."), HttpStatus.NOT_FOUND);
        }
        
        personaService.deletePersonaById(id);
        
        return new ResponseEntity<Persona>(currentPersona, HttpStatus.NO_CONTENT);
    }
	
    
}
