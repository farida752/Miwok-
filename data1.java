package com.example.miwok;

public class data1 {
    String miwok_text;
    String english_text;
    int image;

    public data1( String english_text,String miwok_text, int image) {
        this.miwok_text = miwok_text;
        this.english_text = english_text;
        this.image = image;
    }

    public String getMiwok_text() {
        return miwok_text;
    }

    public String getEnglish_text() {
        return english_text;
    }

    public int getImage() {
        return image;
    }
}
