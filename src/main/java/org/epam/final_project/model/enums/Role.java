package org.epam.final_project.model.enums;

public enum Role {
    ENTRANT, ADMIN;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
