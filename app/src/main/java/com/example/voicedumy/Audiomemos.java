package com.example.voicedumy;

import java.sql.Blob;

public class Audiomemos {
    private String filename;

    public Audiomemos(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
