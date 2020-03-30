package com.selenium.exercise.constants;

public enum FlightForm {
    DEPART_CITY("Departing City"),
    DEPART_DATE("Departing Date"),
    ARRIVAL_CITY("Arriving City"),
    RETURN_DATE("Return Date"),
    INVALID("")
    ;

    public String featureFileId;
    FlightForm(String id) {
        this.featureFileId = id;
    }
    
    public static FlightForm from(String s) {
        for (FlightForm loginFormEnum : FlightForm.values()) {
            if (loginFormEnum.featureFileId.equalsIgnoreCase(s)) {
                return loginFormEnum;
            }
        }
        return INVALID;
    }
}
