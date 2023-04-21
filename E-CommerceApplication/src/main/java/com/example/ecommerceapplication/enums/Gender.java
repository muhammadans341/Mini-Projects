package com.example.ecommerceapplication.enums;

public enum Gender {
    MALE("M"),
    FEMALE("F"),
    OTHER("O");

    final String genderValue;
    Gender(String gender) {
        genderValue=gender;
    }
    public String getGenderValue(){
        return this.genderValue;
    }
}
