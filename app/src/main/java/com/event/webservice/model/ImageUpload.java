package com.event.webservice.model;

import java.util.List;

public class ImageUpload {


    /**
     * status : 200
     * message : Image successfully saved.
     * errors : []
     */

    private String status;
    private String message;
    private List<?> errors;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getErrors() {
        return errors;
    }

    public void setErrors(List<?> errors) {
        this.errors = errors;
    }
}
