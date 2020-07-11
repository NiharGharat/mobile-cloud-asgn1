# Had to rewrite entire code with maven beacuse was unable to build the project with gradle
- Maven project
- All get requests need a unique id which is generated when the poject main class is excuted.

# Steps
- A difference between request params and request header(no request body used as it is a GET request)
- A unique int is gen and sout at start of app, that is used to get all video list under header "id"

Consolidated doc of learning
GET request
- I sent this not in body, as get has body disabled.
- HEaders
	- id	->	 961528960
	- Content-type	->	application/json(Although this was not req)

- Its curl
curl -X GET \
  http://localhost:9876/video/listAll \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'id: 857141903' \
  -H 'postman-token: de258de1-a730-8cfd-356d-cab01ae7ce0d'

- Method signature
public List<Video> getVideoList(@RequestHeader(name = "ida") int hello, @RequestParam(name = "id") int validId)

POST meta
- In headers, set Content-type as application/json
- In body, I sent raw, with JSON(application/json) with payload as 
{
	"duration" : "5",
	"id" : "1",
	"videoOwner" : "niharGharat"
}

- Its curl
curl -X POST \
  http://localhost:9876/video \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 60af77b0-c214-2c63-070e-1f29eb66fd1d' \
  -d '{
	"duration" : "5",
	"id" : "2",
	"videoOwner" : "OhhHello"
}'

- Method signature
public VideoResponseStatus postMetaVideo(@RequestBody Video videoMetaToPost)

POST multipart data
-- To make it multipart compatible, I had to remove postmas content type which I had set manually to multipart/form-data
- Postmans correct curl
curl -X POST \
  http://localhost:9876/video/010720201593621845993/data \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -H 'postman-token: 919005ca-59ec-51ea-386d-6647f9f64431' \
  -F data=@video2.mpg

- Earlier incorrect curl
curl -X POST \
  http://localhost:9876/video/010720201593621845993/data \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -H 'postman-token: 837f5904-e566-0538-2553-853f1b5d5ae4' \
  -F data=@video2.mpg

- Method signature
public VideoResponseStatus postDataVideo(@RequestParam(name = "data") MultipartFile multipartFile, @PathVariable(name = "id") String id) throws Exception {

- @PostMapping(path = "/video/{id}/data")
- I had to UNSET the content type, and also, I had to include the multipart file under data, which I had mapped in the controller under "data" as a REQUEST PARAM and not as a request body.

--------------------------------------------------------------
- Steps to use, on the applciation on port 9876
- Get the unique id gen as a sout in spring app start
- Use that id under "id" for GET in headers to see the video lists
- For POST the file, first POST meta info, this will return a unique identifier whihc u shd use in /video/{unqId}/data
- We need the content-type in POST for meta, and dont need it in POST for data(multipart)
- The POST on data will only gen a file in desktop env.
- All the confs are set in postman
- POSTMAN correct cURLs
-- GET
curl -X GET \
  http://localhost:9876/video/listAll \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'id: 1525469793' \
  -H 'postman-token: 1d24715d-2de6-5b2b-6bcc-ff95c6a21bd8'
-- POST meta
curl -X POST \
  http://localhost:9876/video \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 19289a28-3a78-3a93-bf87-d7448a0220d3' \
  -d '{
	"duration" : "5",
	"videoOwner" : "OhhHello"
}'
-- POST data
curl -X POST \
  http://localhost:9876/video/010720201593621845993/data \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -H 'postman-token: b6fc363b-f105-060b-d8a9-16a9ac3914f2' \
  -F data=@video2.mpg
# end of setps

# Application To Upload/Download video To/From A Cloud Service

This project is my solution to Jules White's first assignement on
[coursera](https://www.coursera.org/learn/cloud-services-java-spring-framework).

## Running the Application

GET /video/{id}/data
   - Returns the binary mpeg data (if any) for the video with the given
     identifier. If no mpeg data has been uploaded for the specified video,
     then the server should return a 404 status code.
     
      
 The AutoGradingTest should be used as the ultimate ground truth for what should be 
 implemented in the assignment. If there are any details in the description above 
 that conflict with the AutoGradingTest, use the details in the AutoGradingTest 
 as the correct behavior and report the discrepancy on the course forums. Further, 
 you should look at the AutoGradingTest to ensure that
 you understand all of the requirements. It is perfectly OK to post on the forums and
 ask what a specific section of the AutoGradingTest does. Do not, however, post any
 code from your solution or potential solution.
 
 There is a VideoSvcApi interface that is annotated with Retrofit annotations in order
 to communicate with the video service that you will be creating. Your solution controller(s)
 should not directly implement this interface in a "Java sense" (e.g., you should not have
 YourSolution implements VideoSvcApi). Your solution should support the HTTP API that
 is described by this interface, in the text above, and in the AutoGradingTest. In some
 cases it may be possible to have the Controller and the client implement the interface,
 but it is not in this 
 
 Again -- the ultimate ground truth of how the assignment will be graded, is contained
 in AutoGradingTest, which shows the specific tests that will be run to grade your
 solution. You must implement everything that is required to make all of the tests in
 this class pass. If a test case is not mentioned in this README file, you are still
 responsible for it and will be graded on whether or not it passes. __Make sure and read
 the AutoGradingTest code and look at each test__!
 
 You should not modify any of the code in Video, VideoStatus, VideoSvcApi, AutoGrading,
 or AutoGradingTest. 

