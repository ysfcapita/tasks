package com.example.tasks.controller;

import com.example.tasks.model.Task;
import com.example.tasks.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public void create(@RequestBody Task task){
        taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAll(){
        return taskService.getAllTasks();
    }

    //  return opt -> task|null -> if task -> responseentity::ok ->
    //      if not -> responseentity::notfound
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable long id){
        return taskService.getTaskById(id).map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){

        if (taskService.deleteTask(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable long id, @RequestBody Task task){

        return taskService.updateTask(id,task).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }



}
