package org.example.nfchats.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * @Description 透明背景上色
 * @className PngColoringUtil
 **/
public class PngColoringUtil {
    /**
     * @Description 给PNG图片增加背景色
     * @param sourceImage 原始图片 最好是PNG透明的
     * @param targetImage 修改后的图片
     * @param backgroudColor 背景色
     **/
    public static void changePNGBackgroudColor(String sourceImage, String targetImage, Color backgroudColor) {
        try {
            BufferedImage result = changePNGBackgroudColor(sourceImage, backgroudColor);
            File output = new File(targetImage);
            ImageIO.write(result, "jpg", output);
        } catch (IOException e) {
            System.out.println("有问题了" + e.getMessage());
        }
    }
    /**
     * @Description 给PNG图片增加背景色 返回base64
     * @param sourceImage 原始图片 最好是PNG透明的
     * @param backgroudColor 背景色
     * @return java.lang.String
     **/
    public static String changePNGBackgroudColorByBase64(String sourceImage, Color backgroudColor){
        try {
            String base64 = "";
            BufferedImage result = changePNGBackgroudColor(sourceImage, backgroudColor);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(result, "jpg", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            final Base64.Encoder encoder = Base64.getEncoder();
            base64 = encoder.encodeToString(imageInByte);
            return base64;
        }catch (Exception e){
            System.out.println("有问题了" + e.getMessage());
            return null;
        }
    }
    /**
     * @Description 给PNG图片增加背景色 返回BufferedImage
     * @param sourceImage 原始图片 最好是PNG透明的
     * @param backgroudColor 背景色
     * @return BufferedImage
     **/
    public static BufferedImage changePNGBackgroudColor(String sourceImage, Color backgroudColor) {
        try {
            File input = new File(sourceImage);
            BufferedImage image = ImageIO.read(input);

            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0, 0, backgroudColor, null);
            graphic.dispose();
            return result;
        } catch (IOException e) {
            System.out.println("有问题了" + e.getMessage());
            return null;
        }
      }
}
