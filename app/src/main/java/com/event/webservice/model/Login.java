package com.event.webservice.model;

import java.util.List;


public class Login {

    /**
     * status : 200
     * message : Welcome abc
     * data : {"id":"1","name":"abc","email":"test@gmail.com","auth_token":"4Kha7HY2pbing3DCoy1B","created_on":"2021-01-30 13:27:36"}
     * errors : []
     */

    private String status;
    private String message;
    private DataDTO data;
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

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public List<?> getErrors() {
        return errors;
    }

    public void setErrors(List<?> errors) {
        this.errors = errors;
    }

    public static class DataDTO  {
        /**
         * id : 1
         * name : abc
         * email : test@gmail.com
         * auth_token : 4Kha7HY2pbing3DCoy1B
         * created_on : 2021-01-30 13:27:36
         */

        private String id;
        private String name;
        private String email;
        private String auth_token;
        private String created_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAuth_token() {
            return auth_token;
        }

        public void setAuth_token(String auth_token) {
            this.auth_token = auth_token;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }




}
