package com.service;

import org.springframework.data.redis.core.RedisOperations;

import java.beans.PropertyEditorSupport;

public class NameEditor  extends PropertyEditorSupport {
    public NameEditor() {
    }

    public void setAsText(String str){
        setValue(str.toUpperCase());
    }
}
