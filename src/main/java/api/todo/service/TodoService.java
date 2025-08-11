package api.todo.service;

import api.todo.entity.Todo;

import java.util.List;

public interface TodoService {
    Todo save(Todo todo);
    List<Todo> getAll();
    Todo getById(Long id);
    Todo update(Todo todo);
    void delete(Long id);
}
