package inf_storage.inf_storage.Utils;

import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.Frame;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Decoder {
    public static void Decode(String fileName) {
        vidToFrames("temp/"+fileName+".mp4");
        List<BufferedImage> frames = Image2Binary("frames");
        framesToBinary(frames, 1280, 720, 4, fileName);
        File file = new File("frames/");
        for (File f : file.listFiles()) {
            f.delete();
        }

    }

    public static void framesToBinary(List<BufferedImage> frames, int width, int height, int pixelSize,String fileName) {
        StringBuilder binaryData = new StringBuilder();
        for (BufferedImage img : frames) {
            for (int y = 0; y < height / pixelSize; y++) {
                for (int x = 0; x < width / pixelSize; x++) {
                    int color = img.getRGB(x * pixelSize, y * pixelSize);
                    int luminance = (color & 0xFF) < 128 ? 1 : 0; // black pixel represents '1' and white pixel
                                                                  // represents '0'
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

        System.out.println("Original text retrieved ");
    
        try {
            File file = new File("Downloads/"+fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(Paths.get("Downloads/"+fileName), text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<BufferedImage> Image2Binary(String folderPath) {
        List<BufferedImage> images = new ArrayList<>();
        try {
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                // parse the file in the order they were created
                for (int i = 0; i < listOfFiles.length; i++) {
                    File file = new File(folderPath + "/frame_" + i + ".png");
                    BufferedImage img = ImageIO.read(file);
                    if (img != null) {
                        images.add(img);
                    } else {
                        System.out.println("Image not found , check the file path");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    public static String framesToData(List<BufferedImage> frames, int width, int height, int pixelSize) {
        StringBuilder binaryData = new StringBuilder();
        for (BufferedImage img : frames) {
            for (int y = 0; y < height / pixelSize; y++) {
                for (int x = 0; x < width / pixelSize; x++) {
                    int color = img.getRGB(x * pixelSize, y * pixelSize);
                    int luminance = (color & 0xFF) < 128 ? 1 : 0; // black pixel represents '1' and white pixel
                                                                  // represents '0'
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

    public static void vidToFrames(String videoPath) {
        try {
            // Create a frame grabber
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
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
                    File outputFile = new File(frameFolder, "frame_" + frameCount + ".png");
                    ImageIO.write(image, "png", outputFile);
                } else {
                    System.out.println("Frame " + frameCount + " is null");
                }
                frameCount++;
            }
            frameCount++;

            // Process one more frame to ensure the last frame is captured
            frame = grabber.grabImage();
            if (frame != null) {
                BufferedImage image = converter.convert(frame);
                if (image != null) {
                    // Save the last frame in its own folder
                    File outputFile = new File(frameFolder, "frame_" + frameCount + ".png");
                    ImageIO.write(image, "png", outputFile);
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
    }

}
