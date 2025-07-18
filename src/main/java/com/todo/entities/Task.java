package com.todo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título não pode estar vazio")
    @Size(min = 3, max = 100, message = "O título precisa ter entre 3 a 100 caracteres")
    private String title;

    @Size(max = 500, message = "A descrição não pode exceder 500 caracteres")
    private String description;
    private Status status;

    public Task() {}

    public Task(Long id, String title, String description, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task(Long id, String title, Status status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public boolean isCompleted() {
        return status == Status.COMPLETED;
    }
}
