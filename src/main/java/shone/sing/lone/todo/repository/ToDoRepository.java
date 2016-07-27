package shone.sing.lone.todo.repository;

import shone.sing.lone.todo.model.ToDoItem;

import java.util.List;

/**
 * Created by XingLong on 2016/7/27.
 */
public interface ToDoRepository {
    List<ToDoItem> findAll();
    ToDoItem findById(Long id);
    Long insert(ToDoItem toDoItem);
    void update(ToDoItem toDoItem);
    void delete(ToDoItem toDoItem);
}
