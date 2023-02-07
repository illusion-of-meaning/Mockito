package org.asherbakov.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final String LOGIN;
    private String password;
}
