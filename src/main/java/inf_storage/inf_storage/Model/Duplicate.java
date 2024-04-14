package inf_storage.inf_storage.Model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "duplicate")
public class Duplicate {
    Boolean video_encode = true;
    String video_encode_path = "/";
    Boolean video_decode = true;
    String video_decode_path = "/";

    public Boolean getVideo_encode() {
        return video_encode;
    }

    public String getVideo_encode_path() {
        return video_encode_path;
    }

    public String getVideo_decode_path() {
        return video_decode_path;
    }

    public Boolean getVideo_decode() {
        return video_decode;
    }
}