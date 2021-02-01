package com.event.webservice.model;

import java.io.Serializable;
import java.util.List;

public class ImageList {


    /**
     * status : 200
     * message : success
     * data : [{"id":"1","file_name":"bommer_sleeping_with_sunglasses2.jpg","file_path":"https://genuinemark.org/piccollect/uploads/images/bommer_sleeping_with_sunglasses2.jpg","tags":"glow in dark 3 letter","created_on":"2021-01-30 14:16:58"},{"id":"2","file_name":"Brad_Pitt.jpg","file_path":"https://genuinemark.org/piccollect/uploads/images/Brad_Pitt.jpg","tags":"kek","created_on":"2021-01-30 15:35:19"},{"id":"3","file_name":"cm_pic.jpg","file_path":"https://genuinemark.org/piccollect/uploads/images/cm_pic.jpg","tags":"CM is here","created_on":"2021-01-30 18:27:04"},{"id":"4","file_name":"yarme.jpg","file_path":"https://genuinemark.org/piccollect/uploads/images/yarme.jpg","tags":"plants","created_on":"2021-01-31 21:43:09"},{"id":"5","file_name":"yarme1.jpg","file_path":"https://genuinemark.org/piccollect/uploads/images/yarme1.jpg","tags":"plants","created_on":"2021-01-31 23:01:10"},{"id":"6","file_name":"yarme2.jpg","file_path":"https://genuinemark.org/piccollect/uploads/images/yarme2.jpg","tags":"plants","created_on":"2021-01-31 23:02:22"},{"id":"7","file_name":"yarme3.jpg","file_path":"https://genuinemark.org/piccollect/uploads/images/yarme3.jpg","tags":"plants","created_on":"2021-02-01 00:35:38"}]
     */

    private String status;
    private String message;
    private List<DataDTO> data;

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

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        /**
         * id : 1
         * file_name : bommer_sleeping_with_sunglasses2.jpg
         * file_path : https://genuinemark.org/piccollect/uploads/images/bommer_sleeping_with_sunglasses2.jpg
         * tags : glow in dark 3 letter
         * created_on : 2021-01-30 14:16:58
         */

        private String id;
        private String file_name;
        private String file_path;
        private String tags;
        private String created_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }
}
