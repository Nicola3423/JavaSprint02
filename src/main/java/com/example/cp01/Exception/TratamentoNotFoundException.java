package com.example.cp01.Exception;

public class TratamentoNotFoundException extends RuntimeException {
    public TratamentoNotFoundException(String message) {
        super(message);
    }
}
