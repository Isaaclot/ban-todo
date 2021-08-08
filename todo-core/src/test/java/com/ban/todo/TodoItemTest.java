package com.ban.todo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author liangqian@vvic.com
 * @version 1.0
 * @since 2021/8/8
 */
public class TodoItemTest {
    @Test
    public void should_todo_item_match_index() {
        TodoItem todoItem = new TodoItem(1, "foo");

        assertThat(todoItem).isNotNull();
        assertThat(todoItem.matchIndex(1)).isTrue();

    }
}
