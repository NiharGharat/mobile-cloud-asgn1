package com.nihar.learning.videoservice.model;

import org.springframework.http.HttpStatus;

public class   VideoResponseStatus {

    private HttpStatus statusCode;
    private String previousActionTaken;
    private String videoId;

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getPreviousActionTaken() {
        return previousActionTaken;
    }

    public void setPreviousActionTaken(String previousActionTaken) {
        this.previousActionTaken = previousActionTaken;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
