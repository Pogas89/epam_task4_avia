package by.epam.ivanov.aviacompany.entity;

public enum Department {
    PILOT("Pilot"),
    NAVIGATOR("Navigator"),
    RADIOMAN("Radioman"),
    ATTENDANT("Attendant");

    private String name;

    Department(String name) {
        this.name = name;
    }

    public Integer getId(){
        return Integer.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }
}
