package com.lucasmartins.mineiracaodados.controller;

import com.lucasmartins.model.Turma;
import com.lucasmartins.mineiracaodados.repository.TurmaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaRepository repository;

    public TurmaController(TurmaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Turma> criarTurma(@RequestBody Turma turma) {
        Turma novaTurma = repository.save(turma);
        return ResponseEntity.ok(novaTurma);
    }

    @GetMapping
    public List<Turma> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Long id, @RequestBody Turma turmaAtualizada) {
        return repository.findById(id)
                .map(t -> {
                    t.setNome(turmaAtualizada.getNome());
                    t.setQuantidadeAlunos(turmaAtualizada.getQuantidadeAlunos());
                    // Como a atividade permite, vamos ignorar relacionamentos
                    repository.save(t);
                    return ResponseEntity.ok(t);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
