package com.nata.faculdade.faculdade.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nata.faculdade.faculdade.dtos.AlunoRecordDto;
import com.nata.faculdade.faculdade.model.AlunoModel;
import com.nata.faculdade.faculdade.repository.AlunoRepository;

import jakarta.validation.Valid;

@RestController
public class AlunoController{
    @Autowired
    AlunoRepository repository;

    @PostMapping("aluno")
    public ResponseEntity<AlunoModel> saveAluno(@RequestBody @Valid AlunoRecordDto alunoRecordDto){
        var alunoModel = new AlunoModel();
        //faz a conversão de DTO para Model
        BeanUtils.copyProperties(alunoRecordDto, alunoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(alunoModel));
    }

    @GetMapping("/aluno")
    public ResponseEntity<List<AlunoModel>> getAllAluno(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
    
    @GetMapping("/aluno/{id}")
    public ResponseEntity<Object> getOneAluno(@PathVariable(value = "id") UUID id){
        Optional<AlunoModel> aluno = repository.findById(id);
        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(aluno.get());
    }

    @PutMapping("/aluno/{id}")
    public ResponseEntity<Object> updateAluno(
        @PathVariable(value = "id") UUID id,
        @RequestBody @Valid AlunoRecordDto alunoRecordDto
        ){
            Optional<AlunoModel> aluno = repository.findById(id);

            if(aluno.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body("Aluno não encontrado");
            }
            var alunoModel = aluno.get();
            BeanUtils.copyProperties(alunoRecordDto, alunoModel);
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(alunoModel));
    }

    @DeleteMapping("aluno/{id}")
    public ResponseEntity<Object> deleteAluno(@PathVariable(value = "id") UUID id){
        Optional<AlunoModel> aluno = repository.findById(id);

        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        repository.delete(aluno.get());
        return ResponseEntity.status(HttpStatus.OK).body("Aluno excluido com sucesso!");


    }

}