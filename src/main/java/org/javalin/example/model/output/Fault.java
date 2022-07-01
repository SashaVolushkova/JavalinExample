package org.javalin.example.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fault {
    private int status;
    private String error;
}
