package by.epam.ivanov.aviacompany.entity;

public enum FlightStatus {
    OPENED("flightstatus.opened"),
    CLOSED("flightstatus.closed"),
    CANCELED("flightstatus.canceled");

    private String name;

    FlightStatus(String name){
        this.name = name;
    }

    public Integer getId(){
        return Integer.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }
}
