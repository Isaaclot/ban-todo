package com.ban.todo;

import lombok.Getter;

/**
 * @author liangqian@vvic.com
 * @version 1.0
 * @since 2021/8/8
 */
@Getter
public class AddTodoItemParameter {
    private String content;

    public AddTodoItemParameter(final String content) {
        this.content = content;
    }
}
