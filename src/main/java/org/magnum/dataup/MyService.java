package org.magnum.dataup;

import org.magnum.dataup.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Component
public class MyService
{
    public static AtomicLong last_id;
    private LinkedList<Video> addedVids;
    VideoFileManager fileManager;

    @Autowired
    public MyService() throws IOException {
        addedVids= new LinkedList<>();
        fileManager = VideoFileManager.get();
        last_id = new AtomicLong(1);
    }

    public LinkedList<Video> getVidsLis()
    {
        return addedVids;
    }

    public boolean addVideoInf(Video vid)
    {
        return addedVids.add(vid);
    }

    public boolean getStreamById(Long id, InputStream in) throws IOException {
        Video searchedVid = null;
        for(Video vid: addedVids)
        {
            if(vid.getId() == id)
            {
                searchedVid = vid; break;
            }
        }
        if(searchedVid != null)
        {
            System.out.println("vid id"+searchedVid.getId());
            fileManager.getVideoData(searchedVid, in);
            return true;
        }
        return false;

    }

    public boolean storeFile(long id, MultipartFile fi) throws IOException
    {
        Video searchedVid = null;
        for(Video vid: addedVids)
        {
            if(vid.getId() == id)
            {
                searchedVid = vid; break;
            }
        }
        if(searchedVid!= null)
        {
            fileManager.saveVideoData(searchedVid, fi.getInputStream());
            return true;
        }
        return false;
//        throw new IOException();
//        return false;
    }
}
