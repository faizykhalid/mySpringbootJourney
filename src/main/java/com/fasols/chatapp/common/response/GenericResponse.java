package com.fasols.chatapp.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GenericResponse<T> {
    String status;
    String message;
    T data;
}
