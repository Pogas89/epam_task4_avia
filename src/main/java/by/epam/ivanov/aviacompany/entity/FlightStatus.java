package by.epam.ivanov.aviacompany.entity;

public enum FlightStatus {
    NEW("flightstatus.new"),
    OPENED("flightstatus.opened"),
    CLOSED("flightstatus.closed"),
    CANCELED("flightstatus.canceled");

    private String name;

    FlightStatus(String name){
        this.name = name;
    }

    public Integer getId(){
        return ordinal();
    }

    public String getName() {
        return name;
    }
}
