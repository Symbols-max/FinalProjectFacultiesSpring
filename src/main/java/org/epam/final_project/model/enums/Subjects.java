package org.epam.final_project.model.enums;

public enum Subjects {
    UKRAINIAN("Украинский язык и литература"),HISTORY("История Украины"),MATH("Математика"),GEOGRAPHY("География"),PHYSICS("Физика"),
    BIOLOGY("Биология"),CHEMISTRY("Химия"),ENGLISH("Английский"),SPANISH("Испанский"),GERMAN("Немецкий"),FRENCH("Французкий");

    private String value;

    public String value() {
        return value;
    }

    Subjects(String value) {
        this.value = value.intern();
    }

}
