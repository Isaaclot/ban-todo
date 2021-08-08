package com.ban.todo.repository;

import java.util.List;

import com.ban.todo.TodoItem;

/**
 * @author liangqian@vvic.com
 * @version 1.0
 * @since 2021/8/8
 */
public interface TodoItemRepository {
    TodoItem save(TodoItem todoItem);

    List<TodoItem> findAll();
}
