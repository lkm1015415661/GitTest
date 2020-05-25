package com.example.demo.redis;

public interface KeyPrefix {
    public int expireSecconds();
    public String getPrefix();
}
