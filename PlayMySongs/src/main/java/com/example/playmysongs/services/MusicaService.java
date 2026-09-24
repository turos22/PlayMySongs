package com.example.playmysongs.services;


import com.example.playmysongs.entities.Musica;
import com.example.playmysongs.repositories.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {
    @Autowired
    private MusicaRepository musicaRepository;

    public List<Musica> findAll(){
        return musicaRepository.findAll();
    }
}
