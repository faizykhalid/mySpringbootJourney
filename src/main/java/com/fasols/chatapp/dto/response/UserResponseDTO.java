package com.fasols.chatapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO implements Serializable {
    private String id;
    private String name;
    private String email;

    public void setId(UUID id) {
        this.id = id.toString();
    }

    public void setId(String id) {
        this.id = id;
    }
}
