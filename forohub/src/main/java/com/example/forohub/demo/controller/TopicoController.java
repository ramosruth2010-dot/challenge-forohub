package com.example.forohub.demo.controller;

import com.example.forohub.demo.dto.DatosRegistroTopico;
import com.example.forohub.demo.entity.Topico;
import com.example.forohub.demo.infra.errores.NotFoundException;
import com.example.forohub.demo.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> registrar(@RequestBody @Valid DatosRegistroTopico datos){

        if(repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Topico duplicado"
            );
        }

        Topico topico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                LocalDateTime.now(),
                "ABIERTO",
                datos.autor(),
                datos.curso()
        );

        Topico guardado = repository.save(topico);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping
    public Page<Topico> listar(Pageable pageable){
        return repository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Topico> detalle(@PathVariable Long id){
        var topico = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico no encontrado"));
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public Topico actualizar(@PathVariable Long id,
                             @RequestBody @Valid DatosRegistroTopico datos){

        Topico topico = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Topico no encontrado"));

        if(repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Topico duplicado"
            );
        }

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(datos.autor());
        topico.setCurso(datos.curso());

        return topico;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        if(!repository.existsById(id)){
            throw new NotFoundException("Topico no encontrado");
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
