package com.grid.dynamics.demoprojecthospital.models.enums;

public enum PdmURL{
    GET_ALL_PATIENTS_BY_DOCTOR("/doctor/%d/patients")
    ,GET_DOCTOR("/doctor/%d")
    ,GET_PATIENT("/patient/%d")
    ,ROLE("auth/role");

    private String path;

    PdmURL(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}