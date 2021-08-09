package com.ban.todo.cli.repository;

import java.io.File;
import java.util.List;

import com.ban.todo.TodoItem;
import com.ban.todo.repository.TodoItemRepository;
import com.google.common.collect.Lists;

/**
 * @author liangqian@vvic.com
 * @version 1.0
 * @since 2021/8/9
 */
public class FileTodoItemRepository implements TodoItemRepository {
    private File file;

    public FileTodoItemRepository(File file) {
        this.file = file;
    }

    @Override
    public TodoItem save(TodoItem todoItem) {

        return todoItem;
    }

    @Override
    public List<TodoItem> findAll() {

        return Lists.newArrayList();
    }
}
