package org.epam.final_project.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private String password;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
