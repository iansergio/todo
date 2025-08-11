package api.todo.service;

import api.todo.entity.Todo;
import api.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo save(Todo todo) {
        if (todo.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "New todo must not have an id");
        }
        return todoRepository.save(todo);
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Todo getById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found with id " + id));
    }

    public Todo update(Todo todo) {
        if (todo.getId() == null || !todoRepository.existsById(todo.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo can not be updated, id does not exist.");
        }
        return todoRepository.save(todo) ;
    }

    public void delete(Long id) {
        if (todoRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found with id " + id);
        }
        todoRepository.deleteById(id);
    }
}
