package com.nihar.learning.videoservice;

import com.nihar.learning.videoservice.model.Video;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Known issues
 * 1. Unique id are not unique, can clash, get better unique number gen algo
 */
public class VideoService {

    public static final int uniqueIdentificationNumber;
    private static List<Video> videoList = new ArrayList<>();

    static {
        Random rand = new Random();
        uniqueIdentificationNumber = rand.ints(1, 1, Integer.MAX_VALUE).findFirst().getAsInt();
        Video video = new Video();
        video.setDuration(5);
        video.setId(rand.ints(1, 1, Integer.MAX_VALUE).findFirst().getAsInt() + "");
        video.setVideoOwner("nihar");
        videoList.add(video);
    }

    public static List<Video> getVideoList() {
        return videoList;
    }

    public static void addVideo(Video video) {
        videoList.add(video);
        System.out.println("Video added");
    }

    public void deleteVideo() {
        if (videoList.size() > 0) {
            videoList.remove(videoList.size() - 1);
            System.out.println("Video deleted");
        } else {
            System.out.println("No video to delete");
        }
    }

    public static String uniqueStringGenerator() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
        StringBuilder format = new StringBuilder(simpleDateFormat.format(date)).append(System.currentTimeMillis());
        return format.toString();
    }
}
