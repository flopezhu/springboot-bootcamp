package com.api.rest.springboot.webflux.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Clients")
public class Client {

    @Id
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    @NotNull
    private String age;

    @NotNull
    private String salary;

    private String photo;
}
