package inf_storage.inf_storage.Youtube.LIB;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestVideoFileDownload;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.Format;
import com.github.kiulian.downloader.model.videos.formats.VideoFormat;

import java.io.File;
import java.util.List;


public class Download {

    private static List<VideoFormat> getVideoFormats (YoutubeDownloader downloader ,String id) {
        RequestVideoInfo request = new RequestVideoInfo(id);
        Response<VideoInfo> response = downloader.getVideoInfo(request);
        VideoInfo video = response.data();
        List<VideoFormat> videoFormats = video.videoFormats();
        return videoFormats;
    }

    public static void __download(String id) {
        
        YoutubeDownloader downloader = new YoutubeDownloader();
        List<VideoFormat> videoFormats;
        File outputDir = new File("my_videos");
        try
        {
            videoFormats = getVideoFormats(downloader, id);
        }
        catch (Exception e)
        {
            System.out.println("Error Failed to get video info: " );
            return;
        }

        try{
            Format format = videoFormats.get(0);
            // RequestVideoFileDownload request = new RequestVideoFileDownload(format)   
            // .saveTo(outputDir) 
            // .overwriteIfExists(true);
        }
        catch (Exception e)
        {
            System.out.println("Error Failed to get video: " );
            return;
        }

    }
}
