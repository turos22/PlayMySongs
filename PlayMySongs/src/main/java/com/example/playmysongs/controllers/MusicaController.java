package com.example.playmysongs.controllers;


import com.example.playmysongs.entities.Musica;
import com.example.playmysongs.repositories.MusicaRepository;
import com.example.playmysongs.services.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "mysong")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping(value = "health")
    public ResponseEntity<Object> health() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "allmusics")
    public ResponseEntity<List<Musica>> findAll(){
        return ResponseEntity.ok().body(musicaService.findAll());
    }


}
