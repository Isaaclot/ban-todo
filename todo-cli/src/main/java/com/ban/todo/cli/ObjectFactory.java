package com.ban.todo.cli;

import java.io.File;

import com.ban.todo.TodoItemService;
import com.ban.todo.cli.repository.FileTodoItemRepository;
import com.ban.todo.repository.TodoItemRepository;

import picocli.CommandLine;

public class ObjectFactory {
    public CommandLine createCommandLine(final File repositoryFile) {
        return new CommandLine(createTodoCommand(repositoryFile));
    }

    private TodoCommand createTodoCommand(final File repositoryFile) {
        final TodoItemService service = createService(repositoryFile);
        return new TodoCommand(service);
    }

    public TodoItemService createService(final File repositoryFile) {
        final TodoItemRepository repository = new FileTodoItemRepository(repositoryFile);
        return new TodoItemService(repository);
    }
}