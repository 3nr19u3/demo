package com.demo.demo.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para la creación y actualización de tareas.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreationDto {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no puede exceder 255 caracteres")
    private String title;

    @Size(max = 10_000, message = "La descripción no puede exceder 10.000 caracteres")
    private String description;

    @FutureOrPresent(message = "La fecha de vencimiento debe ser presente o futura")
    private LocalDate dueDate;

}

