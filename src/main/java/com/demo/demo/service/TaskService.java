package com.demo.demo.service;

import com.demo.demo.dto.TaskCreationDto;
import com.demo.demo.entity.Task;
import com.demo.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Obtiene todas las tareas
     * @return Lista de todas las tareas
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Obtiene una tarea por su ID
     * @param id ID de la tarea
     * @return Tarea encontrada o Optional vacío
     */
    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Crea una nueva tarea
     * @param request DTO con los datos de la tarea
     * @return Tarea creada
     */
    public Task createTask(TaskCreationDto request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .build();
        return taskRepository.save(task);
    }

    /**
     * Actualiza una tarea existente
     * @param id ID de la tarea a actualizar
     * @param request DTO con los datos actualizados
     * @return Tarea actualizada o Optional vacío si no existe
     */
    public Optional<Task> updateTask(Long id, TaskCreationDto request) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(request.getTitle());
                    task.setDescription(request.getDescription());
                    task.setDueDate(request.getDueDate());
                    return taskRepository.save(task);
                });
    }

    /**
     * Elimina una tarea por su ID
     * @param id ID de la tarea a eliminar
     * @return true si se eliminó, false si no existe
     */
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Obtiene todas las tareas completadas
     * @return Lista de tareas completadas
     */
    @Transactional(readOnly = true)
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompleted(true);
    }

    /**
     * Obtiene todas las tareas incompletas
     * @return Lista de tareas incompletas
     */
    @Transactional(readOnly = true)
    public List<Task> getIncompleteTasks() {
        return taskRepository.findByCompleted(false);
    }

    /**
     * Obtiene tareas que vencen antes de una fecha específica
     * @param date Fecha límite
     * @return Lista de tareas
     */
    @Transactional(readOnly = true)
    public List<Task> getTasksDueBefore(LocalDate date) {
        return taskRepository.findByDueDateBefore(date);
    }

    /**
     * Busca tareas por título
     * @param title Título a buscar
     * @return Lista de tareas que coinciden
     */
    @Transactional(readOnly = true)
    public List<Task> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
}

