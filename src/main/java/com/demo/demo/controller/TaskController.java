package com.demo.demo.controller;

import com.demo.demo.dto.TaskCreationDto;
import com.demo.demo.entity.Task;
import com.demo.demo.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Obtiene todas las tareas
     * @return Lista de todas las tareas
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Obtiene una tarea por su ID
     * @param id ID de la tarea
     * @return Tarea encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva tarea
     * @param request DTO con los datos de la tarea
     * @return Tarea creada con código 201
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskCreationDto request) {
        Task createdTask = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**
     * Actualiza una tarea existente
     * @param id ID de la tarea a actualizar
     * @param request DTO con los datos actualizados
     * @return Tarea actualizada o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody TaskCreationDto request) {
        return taskService.updateTask(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina una tarea por su ID
     * @param id ID de la tarea a eliminar
     * @return 204 No Content si se eliminó, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Obtiene todas las tareas completadas
     * @return Lista de tareas completadas
     */
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        List<Task> tasks = taskService.getCompletedTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Obtiene todas las tareas incompletas
     * @return Lista de tareas incompletas
     */
    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getIncompleteTasks() {
        List<Task> tasks = taskService.getIncompleteTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Obtiene tareas que vencen antes de una fecha específica
     * @param date Fecha límite (formato: YYYY-MM-DD)
     * @return Lista de tareas
     */
    @GetMapping("/due-before")
    public ResponseEntity<List<Task>> getTasksDueBefore(@RequestParam LocalDate date) {
        List<Task> tasks = taskService.getTasksDueBefore(date);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Busca tareas por título
     * @param title Título a buscar
     * @return Lista de tareas que coinciden
     */
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasksByTitle(@RequestParam String title) {
        List<Task> tasks = taskService.searchTasksByTitle(title);
        return ResponseEntity.ok(tasks);
    }
}

