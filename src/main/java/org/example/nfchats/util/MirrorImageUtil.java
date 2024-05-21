package org.example.nfchats.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @className MirrorImageUtil
 * @Description 镜像图片
 **/
public class MirrorImageUtil {
    /**
     * @Description 转图片镜像
     * @param fileByte - 图片byte
     * @return java.awt.image.BufferedImage
     **/
    public static BufferedImage getMirrorImage(byte[] fileByte) throws Exception{
        BufferedImage image = ImageIO.read(byte2Input(fileByte));
        return dealMirrorImage(image);
    }

    /**
     * @Description 转图片镜像
     * @param filePath - 图片路径
     * @return java.awt.image.BufferedImage
     **/
    public static BufferedImage getMirrorImage(String filePath) {
        File file = null;
        BufferedImage image = null;
        try {
            file = new File(filePath);
            image = ImageIO.read(file);
            image = dealMirrorImage(image);
            return image;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    /**
     * @Description 转图片镜像
     * @param image - BufferedImage对象
     * @return java.awt.image.BufferedImage
     **/
    private static BufferedImage dealMirrorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int j = 0; j < height; j++) {
            int l = 0, r = width - 1;
            while (l < r) {
                int pl = image.getRGB(l, j);
                int pr = image.getRGB(r, j);
                image.setRGB(l, j, pr);
                image.setRGB(r, j, pl);
                l++;
                r--;
            }
        }
        return image;
    }
    /**
     * @Description byte2Input
     * @param buf - buf数据
     * @return java.io.InputStream
     **/
    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }
}
