package com.uscosoft.angular4springboot.service;

import java.util.List;

import com.uscosoft.angular4springboot.model.Persona;

public interface PersonaService {
	
	Persona findById(long id);
    
    Persona findByName(String name);
     
    Persona createPersona(Persona persona);
     
    Persona updatePersona(Persona persona);
     
    void deletePersonaById(long id);
 
    List<Persona> findAllPersonas();
     
    boolean isPersonaExist(Persona persona);
}

