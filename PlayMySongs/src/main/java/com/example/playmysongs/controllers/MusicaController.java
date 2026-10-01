package com.example.playmysongs.controllers;


import com.example.playmysongs.entities.Erro;
import com.example.playmysongs.entities.Musica;
import com.example.playmysongs.services.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
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
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.ok().body(musicaService.findAll());
    }

    @PostMapping(value = "music-upload")
    public ResponseEntity<Object>  addMusic(@RequestParam("estilo") String estilo,
                                            @RequestParam("titulo") String titulo,
                                            @RequestParam("artista") String artista,
                                            @RequestParam("audio") MultipartFile audio){
        try {
            Musica musica = musicaService.salvarMusica(estilo, titulo, artista, audio);
            return ResponseEntity.ok().body(musica);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Erro("Erro ao armazenar a musica: " + e.getMessage()));
        }
    }

    @GetMapping(value = "find-musics")
    public ResponseEntity<Object> buscar(@RequestParam(value = "titulo", defaultValue = "") String titulo,
                                         @RequestParam(value = "estilo", defaultValue = "") String estilo,
                                         @RequestParam(value = "artista", defaultValue = "") String artista)
    {
        List<Musica> musicas = musicaService.buscar(titulo, estilo, artista);
        if (musicas.isEmpty())
            return ResponseEntity.badRequest().body(new Erro("Nenhuma musica encontrada"));
        return ResponseEntity.ok(musicas);
    }
    @GetMapping(value = "get-music-styles")
    public ResponseEntity<Object> listaEstilos(){
        return ResponseEntity.ok(musicaService.listarEstilos());
    }


}
