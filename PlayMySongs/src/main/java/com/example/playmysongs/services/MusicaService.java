package com.example.playmysongs.services;


import com.example.playmysongs.entities.Musica;
import com.example.playmysongs.repositories.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaService {
    @Autowired
    private MusicaRepository musicaRepository;

    public List<Musica> findAll(){
        return musicaRepository.findAll();
    }

    public Musica AddMusica(Musica musica){
        return musicaRepository.save(musica);
    }

    public List<Musica> porEstilos(String estilos){
        return musicaRepository.findByEstiloIgnoreCase(estilos);
    }

    public List<String> listarEstilos(){
        return musicaRepository.findAll().stream().map(Musica::getEstilo).distinct().collect(Collectors.toList());

    }
}
