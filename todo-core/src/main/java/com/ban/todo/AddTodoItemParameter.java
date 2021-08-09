package com.ban.todo;

import com.google.common.base.Strings;

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

    public static AddTodoItemParameter of(String content) {
        if (Strings.isNullOrEmpty(content)) {
            throw new IllegalArgumentException("Empty content is not allowed");
        }
        return new AddTodoItemParameter(content);
    }
}
