package com.nihar.learning.videoservice.model;

public class Video {
    private int duration;
    private String id;
    private String videoOwner;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoOwner() {
        return videoOwner;
    }

    public void setVideoOwner(String videoOwner) {
        this.videoOwner = videoOwner;
    }

    @Override
    public String toString() {
        return "Video{" +
                "duration=" + duration +
                ", id='" + id + '\'' +
                '}';
    }
}
