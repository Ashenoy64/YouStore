package inf_storage.Youtube;

import inf_storage.Youtube.LIB.Upload;

public class YoutubeController {
    public static void uploadVideo() {
        Upload.__upload("/output.mp4");
        System.out.println("Uploading video to Youtube");
    }
}
