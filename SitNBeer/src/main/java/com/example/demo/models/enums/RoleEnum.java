package com.example.demo.models.enums;

public enum RoleEnum {
    ENTERPRISE, USER, VISITOR;

    @Override
    public String toString() {
        return this.name();
    }
}