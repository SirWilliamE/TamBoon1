package com.example.novafreeze.tamboon1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Getting {

    @SerializedName("data")
    public List<Data> data = null;

    public class Data {

        private int id;

        private String name;

        private String logo_url;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLogo_url() {
            return logo_url;
        }

    }
}
