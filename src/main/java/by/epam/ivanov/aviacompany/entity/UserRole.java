package by.epam.ivanov.aviacompany.entity;

public enum UserRole {
    ADMIN("userrole.admin"),
    DISPETCHER("userrole.dispetcher");

    private final String name;

    UserRole(String name){
        this.name = name;
    }

    public Integer getId(){
        return ordinal();
    }

    public String getName() {
        return name;
    }
}

