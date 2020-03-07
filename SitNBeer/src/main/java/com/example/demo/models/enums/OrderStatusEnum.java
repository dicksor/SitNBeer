package com.example.demo.models.enums;

public enum OrderStatusEnum {
    CLOSE, OPEN, IN_PROCESS;

    @Override
    public String toString() {
        return this.name();
    }
}