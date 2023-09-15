package com.nata.faculdade.faculdade.dtos;

import jakarta.validation.constraints.NotBlank;

public record AlunoRecordDto(
    @NotBlank String nome,
    @NotBlank String curso
    ) {

}
