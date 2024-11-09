package com.example.exception;

public class EmployeeUpdateException extends  RuntimeException{
    public EmployeeUpdateException(String msg){
        super(msg);
    }
}
