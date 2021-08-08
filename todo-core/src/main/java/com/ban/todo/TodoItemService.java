package com.ban.todo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ban.todo.repository.TodoItemRepository;

/**
 * @author liangqian@vvic.com
 * @version 1.0
 * @since 2021/8/8
 */
public class TodoItemService {
    private TodoItemRepository todoItemRepository;

    public TodoItemService(final TodoItemRepository todoItemRepository) {

        this.todoItemRepository = todoItemRepository;
    }

    public TodoItem addTodoItem(final AddTodoItemParameter parameter) {
        if (Objects.isNull(parameter)) {
            throw new IllegalArgumentException("Null Or Empty content is not allowed");
        }
        return todoItemRepository.save(new TodoItem(parameter.getContent()));
    }

    public Optional<TodoItem> markTodoItemDone(final int index) {
        if (index < 0) {
            throw new IllegalArgumentException("mark todo item done: parameter index illegal");
        }
        Optional<TodoItem> todoItem = findItemByIndex(index);

        if (!todoItem.isPresent()) {
            throw new RuntimeException("mark todo item done: parameter index no exist");
        }

        todoItem.get().markDone();

        return Optional.of(todoItemRepository.save(todoItem.get()));
    }

    public Optional<TodoItem> findItemByIndex(final int index) {
        List<TodoItem> todoItemList = todoItemRepository.findAll();

        return todoItemList.stream().filter(todoItem -> todoItem.matchIndex(index)).findFirst();
    }

    public List<TodoItem> listAllTodoItem(boolean all) {
        List<TodoItem> todoItems = todoItemRepository.findAll();
        return all ? todoItems : todoItems.stream().filter(todoItem -> !todoItem.isDone()).collect(Collectors.toList());
    }
}
