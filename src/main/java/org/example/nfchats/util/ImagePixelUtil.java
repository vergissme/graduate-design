package org.example.nfchats.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Description 图片转像素风
 */

public class ImagePixelUtil {

    //间距5 比较像  像素风 可能图片越大 此值越大越像
    private static final int STEP = 5;

    /**
     * @Author vergissme
     * @Description 图片转像素风
     * @Date  2024/1/21 16:43
     * @param imagePath - 图片本地路径
     * @return java.awt.image.BufferedImage
     **/
    public static BufferedImage getPixelImage(String imagePath) throws Exception {
        return getPixelImage(imagePath,STEP);
    }

    public static BufferedImage getPixelImage(String imagePath,int customStep) throws Exception {
        BufferedImage bi = ImageIO.read(new File(imagePath));
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minX = bi.getMinX();
        int minY = bi.getMinY();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();
        for (int i = minX; i < width; i = i + customStep) {
            for (int j = minY; j < height; j = j + customStep) {
                int pixelRGB = bi.getRGB(i, j);
                int red = (pixelRGB >> 16) & 0xff;
                int green = (pixelRGB >> 8) & 0xff;
                int blue = (pixelRGB >> 0) & 0xff;
                graphics.setColor(new Color(red, green, blue));//设置画笔的颜色
                //graphics.fillRect(i,j,k,k);//填充矩形
                graphics.fillRoundRect(i, j, customStep, customStep, -1, -1);//填充圆形矩形
            }
        }
        return img;
    }
}
