package com.example.socksStorage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Не найден товар с указанным артикулом")
public class NotFoundSocksException extends RuntimeException {
}
