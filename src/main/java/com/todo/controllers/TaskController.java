package com.todo.controllers;

import com.todo.entities.Status;
import com.todo.entities.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Estudar Spring Boot", "Revisar conceitos", Status.PENDING));
        tasks.add(new Task(2L, "Implementar endpoints", Status.PENDING));
    }

    // GET: Listar todas as tarefas
    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false) boolean status) {
        if (status) {
            return tasks.stream()
                    .filter(task -> task.isCompleted() == status)
                    .collect(Collectors.toList());
        }
        return tasks;
    }

    // GET: Listar tarefas por ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(task);
    }

    // POST: Criar uma tarefa
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        task.setId((long) (tasks.size() + 1)); // Simula ID incremental
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    // DELETE: Excluir uma tarefa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));
        if (removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}
