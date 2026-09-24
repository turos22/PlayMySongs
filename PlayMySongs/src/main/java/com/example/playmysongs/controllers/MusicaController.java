package com.example.playmysongs.controllers;


import com.example.playmysongs.entities.Musica;
import com.example.playmysongs.repositories.MusicaRepository;
import com.example.playmysongs.services.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @PostMapping(value = "add-music")
    public ResponseEntity<Object>  addMusic(@RequestParam("estilo") String estilo,
                                            @RequestParam("titulo") String titulo,
                                            @RequestParam("artista") String artista,
                                            @RequestParam("audio") MultipartFile audio){

        final String UPLOAD_FOLDER = "src/main/resources/static/uploads/";
        String novoFileName = titulo.toLowerCase() + "_" + estilo.toLowerCase() + "_" + artista.toLowerCase() + ".mp3";
        try {
            File uploadFolder = new File(UPLOAD_FOLDER);
            if (!uploadFolder.exists()) uploadFolder.mkdir();
            audio.transferTo(new File(uploadFolder.getAbsolutePath() + "\\"+novoFileName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao armazenar o arquivo mp3, Musica nao salva " + e.getMessage());
        }

        Musica musica = new Musica(estilo, titulo, artista, novoFileName);
        Musica mu = musicaService.AddMusica(musica);
        if (mu != null){
            return ResponseEntity.ok().body(mu);
        }
        return ResponseEntity.badRequest().body("Ocorreu um erro");
    }


}
