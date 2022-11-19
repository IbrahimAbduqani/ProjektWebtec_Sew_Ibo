package com.example.probe2;
import com.example.probe2.TodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemReposetory extends CrudRepository<TodoItem, Long> {
}
