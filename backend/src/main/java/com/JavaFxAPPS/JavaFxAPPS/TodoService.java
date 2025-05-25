package com.JavaFxAPPS.JavaFxAPPS;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class TodoService {
    private final Map<Long, Todo> todos = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong();

    public List<Todo> findAll() {
        return new ArrayList<>(todos.values());
    }
    public Todo add(Todo todo) {
        long id = idGen.incrementAndGet();
        todo.setId(id);
        todos.put(id, todo);
        return todo;
    }

    public Todo update(Long id, Todo todo) {
        if (todos.containsKey(id)) {
            todo.setId(id);
            todos.put(id, todo);
            return todo;
        } else {
            throw new NoSuchElementException("Todo with id " + id + " not found");
        }
    }
    public void delete(Long id) {
            todos.remove(id);

    }

}
