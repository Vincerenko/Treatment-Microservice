package com.grid.dynamics.demoprojecthospital.models.enums;

public enum PdmEndpoints {
    GET_ALL_PATIENTS_BY_DOCTOR("/doctor/%d/patients")
    ,GET_ROLE_BY_TOKEN("/auth/role");

    private String path;

    PdmEndpoints(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}