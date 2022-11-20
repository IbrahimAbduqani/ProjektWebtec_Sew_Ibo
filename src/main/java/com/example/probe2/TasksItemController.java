package com.example.probe2;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todoitems")
public class TasksItemController {
    private final TasksItemRepository tasksItemRepository;

    public TasksItemController(TasksItemRepository todoItemRepository) {
        this.tasksItemRepository = todoItemRepository;
    }

    @GetMapping("")
    public List<TasksItem> findAllTodoItems() {
        return (List<TasksItem>) tasksItemRepository.findAll();
    }

    @PostMapping("")
    public TasksItem createTaskItem(@RequestBody TasksItem tasksItem) {

        TasksItem newItem = new TasksItem();
        newItem.setDescription(tasksItem.getDescription());
        newItem.setComplete(tasksItem.isComplete());
        newItem.setCreatedDate(Instant.now());
        newItem.setModifiedDate(Instant.now());
        newItem = tasksItemRepository.save(newItem);
        return newItem;
    }

    @GetMapping("/{id}")
    public Optional<TasksItem> getTaskItem(@PathVariable("id") long id) {
        return tasksItemRepository.findById(id);
    }

    @PutMapping("/{id}")
    public TasksItem updateTaskItem(@PathVariable("id") long id, @RequestBody TasksItem tasksItem) {

        Optional<TasksItem> item = tasksItemRepository.findById(id);
        TasksItem updatedItem = new TasksItem();
        if (item.isPresent()) {
            updatedItem = item.get();
            updatedItem.setDescription(tasksItem.getDescription());
            updatedItem.setComplete(tasksItem.isComplete());
            updatedItem.setModifiedDate(Instant.now());
            updatedItem = tasksItemRepository.save(updatedItem);
        }

        return updatedItem;
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable("id") long id) {
        // todoItemRepository.deleteById(id);
        Optional<TasksItem> item = tasksItemRepository.findById(id);
        if (item.isPresent()){
            tasksItemRepository.delete(item.get());
        }
    }
}
