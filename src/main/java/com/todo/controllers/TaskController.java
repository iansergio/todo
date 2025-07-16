package com.todo.controllers;

import com.todo.entities.Status;
import com.todo.entities.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<Task> getAllTasks() {
        return tasks;
    }

    // GET: Listar tarefas por ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return tasks.stream().filter(task -> task.getId()
                .equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
    }

    // POST: Criar uma tarefa
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        task.setId((long) tasks.size() + 1);
        tasks.add(task);
        return task;
    }


}
