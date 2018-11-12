package com.example.whiteboardfall2018shaivlpatelmiddletierjava.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.whiteboardfall2018shaivlpatelmiddletierjava.models.User;

public interface UserRepository extends CrudRepository <User, Integer>{

	
    public List<User> findByusername(String username);
    

}
