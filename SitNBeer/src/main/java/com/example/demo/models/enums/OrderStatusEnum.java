package com.example.demo.models.enums;

public enum OrderStatusEnum {
    CLOSE, OPEN, IN_PROCESS, REJECTED;

    @Override
    public String toString() {
        return this.name();
    }
}