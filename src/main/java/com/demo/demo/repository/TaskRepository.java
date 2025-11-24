package com.demo.demo.repository;

import com.demo.demo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Busca tareas por estado de completado
     * @param completed Estado de completado
     * @return Lista de tareas
     */
    List<Task> findByCompleted(Boolean completed);

    /**
     * Busca tareas por fecha de vencimiento
     * @param dueDate Fecha de vencimiento
     * @return Lista de tareas
     */
    List<Task> findByDueDate(LocalDate dueDate);

    /**
     * Busca tareas que vencen antes de una fecha específica
     * @param dueDate Fecha límite
     * @return Lista de tareas
     */
    List<Task> findByDueDateBefore(LocalDate dueDate);

    /**
     * Busca tareas que vencen después de una fecha específica
     * @param dueDate Fecha límite
     * @return Lista de tareas
     */
    List<Task> findByDueDateAfter(LocalDate dueDate);

    /**
     * Busca tareas por título (búsqueda case-insensitive)
     * @param title Título de la tarea
     * @return Lista de tareas
     */
    List<Task> findByTitleContainingIgnoreCase(String title);

    /**
     * Busca tareas incompletas que vencen antes de una fecha específica
     * @param dueDate Fecha límite
     * @return Lista de tareas
     */
    List<Task> findByCompletedFalseAndDueDateBefore(LocalDate dueDate);

    /**
     * Cuenta el número de tareas completadas
     * @return Número de tareas completadas
     */
    long countByCompletedTrue();

    /**
     * Cuenta el número de tareas incompletas
     * @return Número de tareas incompletas
     */
    long countByCompletedFalse();
}

