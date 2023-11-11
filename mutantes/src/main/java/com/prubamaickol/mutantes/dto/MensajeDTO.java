package com.prubamaickol.mutantes.dto;

import lombok.Data;

@Data
public class MensajeDTO {
    private final String mensaje;

    public MensajeDTO(String mensaje) {
        this.mensaje = mensaje;
    }
}
