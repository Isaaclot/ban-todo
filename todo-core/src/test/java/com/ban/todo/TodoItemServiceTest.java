package com.ban.todo;

import static com.google.common.collect.ImmutableList.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ban.todo.repository.TodoItemRepository;

/**
 * @author liangqian@vvic.com
 * @version 1.0
 * @since 2021/8/8
 */
public class TodoItemServiceTest {
    TodoItemRepository todoItemRepository;
    TodoItemService service;

    @BeforeEach
    public void setUp() {
        todoItemRepository = Mockito.mock(TodoItemRepository.class);
        service = new TodoItemService(todoItemRepository);

    }

    @Test
    public void should_add_todo_item() {
        when(todoItemRepository.save(any())).then(returnsFirstArg());
        service = new TodoItemService(todoItemRepository);
        TodoItem todoItem = service.addTodoItem(new AddTodoItemParameter("foo"));
        assertThat(todoItem.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_add_empty_todo_item_content() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> service.addTodoItem(null));
    }

    @Test
    public void should_mark_todo_item_done() {
        when(todoItemRepository.findAll()).thenReturn(of(new TodoItem(1, "foo")));
        when(todoItemRepository.save(any())).then(returnsFirstArg());

        final Optional<TodoItem> todoItemOptional = service.markTodoItemDone(1);

        assertThat(todoItemOptional).isPresent();

        assertThat(todoItemOptional.get().isDone()).isTrue();

    }

    @Test
    public void should_mark_todo_item_done_illegal_parameter() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> service.markTodoItemDone(-1));
    }

    @Test
    public void should_mark_todo_done_index_no_match() {
        when(todoItemRepository.findAll()).thenReturn(of(new TodoItem(2, "foo")));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> service.markTodoItemDone(1));
    }

    @Test
    public void should_find_item_by_index() {
        when(todoItemRepository.findAll()).thenReturn(of(new TodoItem(1, "foo")));

        final Optional<TodoItem> todoItem = service.findItemByIndex(1);

        assertThat(todoItem).isPresent();
        assertThat(todoItem.get().getIndex()).isEqualTo(1);
    }

    @Test
    public void should_list_all_todo_item_include_done_item() {
        when(todoItemRepository.findAll()).thenReturn(of(new TodoItem(1, "foo")));
        List<TodoItem> todoItemList = service.listAllTodoItem(true);
        assertThat(todoItemList).isNotEmpty();
    }

    @Test
    public void should_list_todo_item() {
        when(todoItemRepository.findAll()).thenReturn(of(new TodoItem(1, "foo"), new TodoItem(2, "bar")));
        List<TodoItem> todoItemList = service.listAllTodoItem(false);
        assertThat(todoItemList).isNotEmpty();
        assertThat(todoItemList.stream().noneMatch(TodoItem::isDone)).isTrue();
    }

}
