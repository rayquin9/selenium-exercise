package com.selenium.exercise.constants;

public enum LoginForm {
    FIRST_NAME("First Name"),
    LAST_NAME("Last Name"),
    PHONE("Phone"),
    EMAIL("Email"),
    ADDRESS("Address"),
    ADDRESS_LINE2("Address2"),
    CITY("City"),
    STATE("State"),
    ZIP("Zip"),
    USER_ID("UserId"),
    PASS("Password"),
    INVALID("")
    ;

    public String featureFileId;
    LoginForm(String id) {
        this.featureFileId = id;
    }
    
    public static LoginForm from(String s) {
        for (LoginForm loginFormEnum : LoginForm.values()) {
            if (loginFormEnum.featureFileId.equalsIgnoreCase(s)) {
                return loginFormEnum;
            }
        }
        return INVALID;
    }
}
