package com.ban.todo;

import java.util.Objects;

import lombok.Getter;

/**
 * @author liangqian@vvic.com
 * @version 1.0
 * @since 2021/8/8
 */
@Getter
public class TodoItem {
    private Integer index;
    private String content;
    private boolean done;

    public TodoItem(final String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public TodoItem(final Integer index, final String content) {
        this.index = index;
        this.content = content;
    }

    public void markDone() {
        this.done = true;
    }

    public boolean matchIndex(int index) {
        return Objects.equals(this.index, index);
    }
}
