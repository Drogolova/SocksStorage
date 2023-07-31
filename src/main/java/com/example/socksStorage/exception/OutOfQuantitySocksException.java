package com.example.socksStorage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Указанноне количество превышает количество в наличии")
public class OutOfQuantitySocksException extends RuntimeException {
}
