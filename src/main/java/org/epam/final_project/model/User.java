package org.epam.final_project.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.epam.final_project.model.enums.Role;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
    public class User {

    @Id
    @GeneratedValue
        private long id;

    @Column(unique = true,nullable = false,length = 40)
        private String email;
    @Column(nullable = false,length = 200)
        private String password;

        private boolean status;

        @Enumerated(EnumType.STRING)
        private Role role;

        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "info_id")
        private Info info;

    public User(String email, String password, boolean status, Role role, Info info) {

        this.email = email;
        this.password=password;
        this.status = status;
        this.role = role;
        this.info = info;
    }

    public User(String email,String password, Role role) {
        this.email = email;
        this.password=password;
        this.role = role;
    }

    public String getStatus() {
        if(status==true){
            return "active";
        }
        else {
            return "blocked";
        }
    }

    public boolean isStatus() {
       return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && status == user.status && Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role && Objects.equals(info, user.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, status, role, info);
    }
}
