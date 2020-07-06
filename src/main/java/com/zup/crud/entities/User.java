package com.zup.crud.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tabUser")
public class User{


    private Long id;

    private String nome;
    private Integer idade;
    private String CPF;
    private String email;
    private Integer telefone;
    private String endereco;

    public User() {
    }

    public User(Long id, String nome, Integer idade, String CPF, String email, Integer telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.CPF = CPF;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome", nullable = false)
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "idade", nullable = false)
    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Column(name = "CPF", nullable = false)
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "telefone", nullable = false)
    public Integer getTelefone() {
        return telefone;
    }
    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }
    @Column(name = "endereco", nullable = false)
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
