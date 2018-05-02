package by.epam.ivanov.aviacompany.entity;

public enum UserRole {
    ADMIN("Admin"),
    DISPETCHER("Dispetcher");

    private String name;

    UserRole(String name){
        this.name = name;
    }

    public Integer getId(){
        return Integer.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }
}

