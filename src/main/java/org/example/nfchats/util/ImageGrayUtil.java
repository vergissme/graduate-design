package org.example.nfchats.util;

import org.example.nfchats.exception.ImageTypeException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Description 图片灰度处理
 * Version 1.0
 */
public class ImageGrayUtil {
    /**
     * @param imgPath - 图片本地路径
     * @return java.awt.image.BufferedImage
     **/
    public static BufferedImage grayImageByteGRAY(String imgPath) throws Exception,ImageTypeException{
        BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
        return grayImage(bufferedImage,BufferedImage.TYPE_BYTE_GRAY);
    }

    public static BufferedImage grayImage3ByteBGR(String imgPath) throws Exception,ImageTypeException{
        BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
        return grayImage(bufferedImage,BufferedImage.TYPE_3BYTE_BGR);
    }
    /**
     * @param grayType - 灰度处理类型
     **/
    public static BufferedImage grayImage(String imgPath,Integer grayType) throws Exception,ImageTypeException{
        BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
        return grayImage(bufferedImage,grayType);
    }
    /**
     * @param bufferedImage - 图片对象
     * @return java.awt.image.BufferedImage
     **/
    public static BufferedImage grayImage(BufferedImage bufferedImage, Integer grayType) throws ImageTypeException{

        switch (grayType) {
            case BufferedImage.TYPE_INT_RGB:
            case BufferedImage.TYPE_INT_ARGB:
            case BufferedImage.TYPE_INT_ARGB_PRE:
            case BufferedImage.TYPE_INT_BGR:
            case BufferedImage.TYPE_3BYTE_BGR:
            case BufferedImage.TYPE_4BYTE_ABGR:
            case BufferedImage.TYPE_4BYTE_ABGR_PRE:
            case BufferedImage.TYPE_BYTE_GRAY:
            case BufferedImage.TYPE_USHORT_GRAY:
            case BufferedImage.TYPE_BYTE_BINARY:
            case BufferedImage.TYPE_BYTE_INDEXED:
            case BufferedImage.TYPE_USHORT_555_RGB:
            break;
            default:
                throw new ImageTypeException("Unknown image type " +
                        grayType,"未知的图片类型"+grayType);
        }
        BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                grayType);
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                Color color = new Color(bufferedImage.getRGB(i, j));
                int gray = (int) (color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);
                Color color_end = new Color(gray, gray, gray);
                grayImage.setRGB(i, j, color_end.getRGB());
            }
        }
        return grayImage;
    }
}
