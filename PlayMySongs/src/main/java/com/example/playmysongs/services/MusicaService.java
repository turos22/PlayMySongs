package com.example.playmysongs.services;


import com.example.playmysongs.entities.Musica;
import com.example.playmysongs.repositories.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaService {
    private static final String UPLOAD_FOLDER = "src/main/resources/static/uploads/";

    @Autowired
    private MusicaRepository musicaRepository;

    public List<Musica> findAll(){
        return musicaRepository.findAll();
    }

    public List<Musica> porEstilos(String estilos){
        return musicaRepository.findByEstiloIgnoreCase(estilos);
    }

    public List<Musica> buscar(String titulo, String estilo, String artista){
        List<Musica> musicas = musicaRepository
                .findByTituloContainingIgnoreCaseAndArtistaContainingIgnoreCase(titulo.trim(), artista.trim());
        // estilo vem do combo: compara exato, vazio = todos
        if (!estilo.isEmpty())
            musicas = musicas.stream().filter(m -> estilo.equalsIgnoreCase(m.getEstilo())).collect(Collectors.toList());
        return musicas;
    }

    public List<String> listarEstilos(){
        List<String> estilos = musicaRepository.findAll().stream().map(Musica::getEstilo).distinct().collect(Collectors.toList());
        // banco vazio: devolve os estilos padrao
        if (estilos.isEmpty())
            return List.of("Rock", "Pop", "Pop Internacional", "Sertanejo", "MPB",
                    "Eletrônica", "Samba", "Funk", "Rap", "Jazz");
        return estilos;
    }

    public Musica salvarMusica(String estilo, String titulo, String artista, MultipartFile audio) throws IOException {
        String extensao = getExtensao(audio.getOriginalFilename());
        if (!extensao.equals("mp3"))
            throw new IOException("o arquivo deve ser mp3");

        String novoFileName = titulo.trim().toLowerCase() + "_" + estilo.trim().toLowerCase() + "_" + artista.trim().toLowerCase() + "." + extensao;

        File uploadFolder = new File(UPLOAD_FOLDER);
        if (!uploadFolder.exists()) uploadFolder.mkdirs();
        audio.transferTo(new File(uploadFolder.getAbsolutePath(), novoFileName));

        return musicaRepository.save(new Musica(estilo, titulo, artista, novoFileName));
    }

    private String getExtensao(String nomeArquivo) {
        if (nomeArquivo == null || !nomeArquivo.contains(".")) return "";
        return nomeArquivo.substring(nomeArquivo.lastIndexOf('.') + 1).toLowerCase();
    }
}
