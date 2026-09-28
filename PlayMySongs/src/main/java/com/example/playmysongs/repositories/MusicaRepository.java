package com.example.playmysongs.repositories;


import com.example.playmysongs.entities.Musica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicaRepository extends MongoRepository<Musica, String>{
    List<Musica> findByEstiloIgnoreCase(String estilo);
}
