package com.nihar.learning.videoservice;

import java.io.File;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nihar.learning.videoservice.model.Video;
import com.nihar.learning.videoservice.model.VideoResponseStatus;

@RestController
public class VideoServiceController {

	// An post to upload a video
	// A get to get video list
	@GetMapping(path = "/video/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Video> getVideoList(@RequestHeader(name = "id") int validId) throws Exception {
		if (validId < 0) {
			throw new Exception("Incorrect id specified");
		}
		if (validId == VideoService.uniqueIdentificationNumber) {
			return VideoService.getVideoList();
		} else {
			throw new Exception("You are not authorised");
		}
	}

	@PostMapping(path = "/video", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public VideoResponseStatus postMetaVideo(@RequestBody Video videoMetaToPost) {
		System.out.println("In post meta of video");
		VideoResponseStatus responseStatus = new VideoResponseStatus();
		try {
			if (videoMetaToPost instanceof Video) {
				String uniqGenId = VideoService.uniqueStringGenerator();
				videoMetaToPost.setId(uniqGenId);
				VideoService.addVideo(videoMetaToPost);
				responseStatus.setStatusCode(HttpStatus.OK);
				responseStatus.setPreviousActionTaken("Video added to the list");
				responseStatus.setVideoId(uniqGenId);
			} else {
				responseStatus.setStatusCode(HttpStatus.BAD_REQUEST);
				responseStatus.setPreviousActionTaken("Object was not a video object");
			}
		} catch (Exception e) {
			System.out.println("Unable to add the video " + e.getMessage());
			responseStatus.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			responseStatus
					.setPreviousActionTaken("Exception while trying to add video meta to database " + e.getMessage());
		}
		return responseStatus;
	}

	@PostMapping(path = "/video/{id}/data")
	@ResponseBody
	public VideoResponseStatus postDataVideo(@RequestParam(name = "data") MultipartFile multipartFile,
			@PathVariable(name = "id") String id) throws Exception {
		if (multipartFile == null) {
			throw new Exception("Multipart file data was null");
		}
		if (!checkIfIdIsAllocated(id)) {
			throw new Exception("Given id " + id
					+ " dosent exists; ensure you have acquired a unique id first by hitting meta data api");
		}
		try {
			multipartFile.transferTo(new File("C:\\Users\\welcome\\Desktop\\myVid.mp4"));
		} catch (Exception e) {
			System.out.println("Error while transfering multipart file");
		}

		VideoResponseStatus responseStatus = new VideoResponseStatus();
		responseStatus.setStatusCode(HttpStatus.OK);
		return responseStatus;
	}

	@GetMapping(path = "/video/{id}/data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public Response getVideo(@RequestHeader(name = "id") int id, @PathVariable(name = "id") String videoId)
			throws Exception {
		if (id != VideoService.uniqueIdentificationNumber || videoId.equals("0")) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		if (VideoService.getVideoList().stream().filter(e -> e.getId().equals(videoId)).count() != 1) {
			return Response.status(Status.NOT_FOUND).build();
		}
		File file = new File("C:\\Users\\welcome\\Desktop\\myVid.mp4");
		System.out.println("File exists " + file.exists());
		ResponseBuilder responseBuilder = Response.ok((Object) file);
		responseBuilder.header("Content-Disposition", "attachment; filename=test.mp4");
		return responseBuilder.build();
	}

	private boolean checkIfIdIsAllocated(String id) {
		return VideoService.getVideoList().stream().filter(e -> e.getId().equals(id)).count() == 1L;
	}

}
