package com.lucasmartins.model;

import jakarta.persistence.*;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int quantidadeAlunos;

    //Removido relacionamento com Disciplina para simplificar

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidadeAlunos() { return quantidadeAlunos; }
    public void setQuantidadeAlunos(int quantidadeAlunos) { this.quantidadeAlunos = quantidadeAlunos; }
}
