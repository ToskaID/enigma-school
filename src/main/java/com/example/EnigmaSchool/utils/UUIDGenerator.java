package com.example.EnigmaSchool.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator implements  IRandomStringGenerate{
    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }
}
