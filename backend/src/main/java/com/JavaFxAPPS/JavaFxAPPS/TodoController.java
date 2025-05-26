package com.JavaFxAPPS.JavaFxAPPS;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todo")

public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.add(todo);
    }
}
