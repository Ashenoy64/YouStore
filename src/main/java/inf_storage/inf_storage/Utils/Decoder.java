package inf_storage.inf_storage.Utils;
import java.io.File;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.Frame;
import java.io.FileWriter;
import java.io.IOException;
public class Decoder {
    public static File Decode(File file) {
        List<BufferedImage> frames = vidToFrames(file);
        String data = framesToData(frames, 1280, 720, 4);
        File outputFile = new File("output.txt");
        try {
            FileWriter writer = new FileWriter(outputFile);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }


    public static String framesToData(List<BufferedImage> frames, int width, int height, int pixelSize) {
        StringBuilder binaryData = new StringBuilder();
        for (BufferedImage img : frames) {
            for (int y = 0; y < height / pixelSize; y++) {
                for (int x = 0; x < width / pixelSize; x++) {
                    int color = img.getRGB(x * pixelSize, y * pixelSize);
                    int luminance = (color & 0xFF) < 128 ? 1 : 0; // black pixel represents '1' and white pixel represents '0'
                    binaryData.append(luminance);
                }
            }
        }
        // Convert the binary data to text
        String text = binaryToText(binaryData.toString());

        // check for the EOF character
        int eofIndex = text.indexOf("123456789012345678901234567890");
        if (eofIndex != -1) {
            System.out.println("EOF character found at index: " + eofIndex);
            text = text.substring(0, eofIndex);
        }
        return text;

        
    }

    public static String binaryToText(String binaryData) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binaryData.length(); i += 8) {
            String byteString = binaryData.substring(i, Math.min(i + 8, binaryData.length()));
            int byteValue = Integer.parseInt(byteString, 2);
            text.append((char) byteValue);
        }
        return text.toString();
    }

    public static List<BufferedImage> vidToFrames(File file) {
            List<BufferedImage> frames = new ArrayList<>();
        try {
            // Create a frame grabber
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(file);
            grabber.start();
            // Convert each frame to BufferedImage
            Java2DFrameConverter converter = new Java2DFrameConverter();
            Frame frame;
            int frameCount = 0;
            File frameFolder = new File("frames/");
            if (!frameFolder.exists()) {
                frameFolder.mkdirs();
            }
    
            // Process frames until null is returned
            while ((frame = grabber.grabImage()) != null) {
                BufferedImage image = converter.convert(frame);
                if (image != null) {
                    // Save the frame in its own folder
                    frames.add(image);
                } else {
                    System.out.println("Frame " + frameCount + " is null");
                }
                frameCount++;
            }
            frameCount++;
    
            frame = grabber.grabImage();
            if (frame != null) {
                BufferedImage image = converter.convert(frame);
                if (image != null) {
                    frames.add(image);
                } else {
                    System.out.println("Frame " + frameCount + " is null");
                }
            }
    
            // Release the grabber
            grabber.stop();
            grabber.close();
            converter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return frames;
    }    

}
