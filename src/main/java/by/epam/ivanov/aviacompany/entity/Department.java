package by.epam.ivanov.aviacompany.entity;

public enum Department {
    PILOT("department.pilot"),
    NAVIGATOR("department.navigator"),
    RADIOMAN("department.radioman"),
    ATTENDANT("department.attendant");

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
