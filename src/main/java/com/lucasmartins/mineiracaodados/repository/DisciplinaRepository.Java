package com.lucasmartins.mineiracaodados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lucasmartins.mineiracaodados.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
