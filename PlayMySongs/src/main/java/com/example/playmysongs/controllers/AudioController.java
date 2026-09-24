package com.example.playmysongs.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("uploads")
public class AudioController {

    @GetMapping("/{nomeArquivo}")
    public ResponseEntity<Object> obterAudio(@PathVariable String nomeArquivo) {
        // Procura a pasta a partir do diretório de execução do projeto
        File arquivo = new File("src/main/resources/static/uploads/" + nomeArquivo);

        if (!arquivo.exists()) {
            return ResponseEntity.notFound().build();
        }

        try {
            byte[] bytes = Files.readAllBytes(arquivo.toPath());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + arquivo.getName() + "\"")
                    .contentType(MediaType.parseMediaType("audio/mpeg"))
                    .body(bytes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }
}
