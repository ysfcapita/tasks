package com.example.tasks.service;

import com.example.tasks.model.Task;
import com.example.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){

        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){

        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(long id){

        return taskRepository.findById(id);
    }

    public void createTask(Task task){

        taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id,Task updatedTask){

        return taskRepository.findById(id).map(t-> {
            t.setTitle(updatedTask.getTitle());
            t.setDescription(updatedTask.getDescription());
            t.setCompleted(updatedTask.isCompleted());

            return taskRepository.save(t);
        });

    }

    public boolean deleteTask(long id){

        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
