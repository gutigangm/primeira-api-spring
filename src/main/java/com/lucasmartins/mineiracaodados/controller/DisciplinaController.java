package com.lucasmartins.mineiracaodados.controller;

import com.lucasmartins.model.Disciplina;
import com.lucasmartins.mineiracaodados.repository.DisciplinaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaRepository repository;

    public DisciplinaController(DisciplinaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Disciplina> listarTodas() {
        return repository.findAll();
    }

    @PostMapping
    public Disciplina criar(@RequestBody Disciplina disciplina) {
        return repository.save(disciplina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long id, @RequestBody Disciplina disciplinaAtualizada) {
        return repository.findById(id)
                .map(d -> {
                    d.setDescricao(disciplinaAtualizada.getDescricao());
                    d.setHorasAula(disciplinaAtualizada.getHorasAula());
                    d.setCurso(disciplinaAtualizada.getCurso());
                    d.setProfessor(disciplinaAtualizada.getProfessor());
                    d.setTurma(disciplinaAtualizada.getTurma());
                    repository.save(d);
                    return ResponseEntity.ok(d);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
