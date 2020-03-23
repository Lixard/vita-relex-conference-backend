package ru.relex.rest.model;

public class ConstraintViolationModel {

    private String error;

    public ConstraintViolationModel(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
