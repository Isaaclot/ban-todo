package com.ban.todo.cli;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.ban.todo.TodoItem;
import com.ban.todo.cli.repository.FileTodoItemRepository;

/**
 * @author liangqian@vvic.com
 * @version 1.0
 * @since 2021/8/9
 */
public class FileTodoItemRepositoryTest {

    @TempDir
    File tempDir;
    private File tempFile;
    private FileTodoItemRepository fileTodoItemRepository;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("file", "", tempDir);
        fileTodoItemRepository = new FileTodoItemRepository(tempFile);
    }

    @Test
    public void should_find_nothing_for_empty_repository() {
        final List<TodoItem> todoItems = fileTodoItemRepository.findAll();
        Assertions.assertThat(todoItems).isEmpty();
    }

    @Test
    public void should_save_item_then_return() {
        TodoItem foo = fileTodoItemRepository.save(new TodoItem("foo"));
        Assertions.assertThat(foo).isNotNull();
        Assertions.assertThat(foo.getContent()).isEqualTo("foo");
        Assertions.assertThat(foo.getIndex()).isNotNull();
        Assertions.assertThat(foo.getIndex()).isNotNegative();
    }

    @Test
    public void should_find_new_add_todo_item_for_not_empty_repository() {

    }

    @Test
    public void should_find_done_item_for_repository() {

    }

    @Test
    public void should_exception_add_empty_item_for_repository() {

    }

}
