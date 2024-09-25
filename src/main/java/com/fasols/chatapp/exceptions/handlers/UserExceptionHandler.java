package com.fasols.chatapp.exceptions.handlers;

import com.fasols.chatapp.controller.UserController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {
}
