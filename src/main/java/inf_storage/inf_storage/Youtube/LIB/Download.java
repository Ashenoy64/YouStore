package inf_storage.inf_storage.Youtube.LIB;

import java.io.File;
import java.net.URL;
import com.github.axet.vget.VGet;

public class Download {

    // private static List<VideoFormat> getVideoFormats (YoutubeDownloader downloader ,String id) {
    //     RequestVideoInfo request = new RequestVideoInfo(id);
    //     Response<VideoInfo> response = downloader.getVideoInfo(request);
    //     VideoInfo video = response.data();
    //     List<VideoFormat> videoFormats = video.videoFormats();
    //     return videoFormats;
    // }
    private static String path = "temp/";

    public static void __download(String id) {
        
        try {
            String url = "https://www.youtube.com/watch?v="+id;
            VGet v = new VGet(new URL(url), new File(path));
            v.download();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // YoutubeDownloader downloader = new YoutubeDownloader();
        // List<VideoFormat> videoFormats;
        // File outputDir = new File("my_videos");
        // try
        // {
        //     videoFormats = getVideoFormats(downloader, id);
        // }
        // catch (Exception e)
        // {
        //     System.out.println("Error Failed to get video info: " );
        //     return;
        // }

        // try{
        //     Format format = videoFormats.get(0);
        //     System.out.println("Downloading video in format");
        //     RequestVideoFileDownload request = new RequestVideoFileDownload(format)   
        //     .saveTo(outputDir) 
        //     .overwriteIfExists(true);
        // }
        // catch (Exception e)
        // {
        //     System.out.println("Error Failed to get video: " );
        //     return;
        // }

    }
}
