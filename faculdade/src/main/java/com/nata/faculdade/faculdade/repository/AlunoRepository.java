package com.nata.faculdade.faculdade.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nata.faculdade.faculdade.model.AlunoModel;

public interface AlunoRepository extends JpaRepository<AlunoModel,UUID> {
    
}
