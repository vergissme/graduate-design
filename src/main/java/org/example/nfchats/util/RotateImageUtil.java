package org.example.nfchats.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @ClassName: RotateImageUtil
 * @description: 图片旋转工具类
 **/
public class RotateImageUtil {
    //全角度
    private static final int FULL_ANAGEL = 360;
    /**
     * 将图片进行指定角度旋转
     * @param bufferedImage 原图
     * @param angel 旋转角度
     * @return BufferedImage 旋转后的图片
     */
    public static BufferedImage rotateImage(BufferedImage bufferedImage, int angel) {
        if (bufferedImage == null) {
            return null;
        }
        if (angel < 0) {
            // 将负数角度，纠正为正数角度
            angel = angel + FULL_ANAGEL;
        }
        int imageWidth = bufferedImage.getWidth(null);
        int imageHeight = bufferedImage.getHeight(null);
        // 计算重新绘制图片的尺寸
        Rectangle rectangle = calculatorRotatedSize(new Rectangle(new Dimension(imageWidth, imageHeight)), angel);
        // 获取原始图片的透明度
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage newImage = null;
        newImage = new BufferedImage(rectangle.width, rectangle.height, type);
        Graphics2D graphics = newImage.createGraphics();
        // 平移位置
        graphics.translate((rectangle.width - imageWidth) / 2, (rectangle.height - imageHeight) / 2);
        // 旋转角度
        graphics.rotate(Math.toRadians(angel), imageWidth / 2, imageHeight / 2);
        // 绘图
        graphics.drawImage(bufferedImage, null, null);
        return newImage;
    }
    /**
     * 给图片指定区域增加矩形
     * @param sourcePath 原图路径
     * @param targetPath 保存图片路径
     * @param x x坐标 对应left
     * @param y y坐标 对应top
     * @param width 框的宽度
     * @param height 框的高度
     * @param rotate 旋转角度
     * @param lineColor 线条颜色
     * @param lineWidth 线条宽度
     */
    public static void addFrameImage(String sourcePath,String targetPath,int x,int y,int width,int height,int rotate,Color lineColor,int lineWidth) throws Exception {
        InputStream inputStream = new FileInputStream(sourcePath);
        BufferedImage image = ImageIO.read(inputStream);
        Graphics2D g = image.createGraphics();
        g.setColor(lineColor);
        //线条宽度
        float thickness = lineWidth;
        g.setStroke(new BasicStroke(thickness));
        g.drawRect(x,y,width,height);
        // 旋转角度
        g.rotate(Math.toRadians(rotate));
        FileOutputStream out = new FileOutputStream(targetPath);//输出图片的地址
        ImageIO.write(image, "jpeg", out);
    }
    /**
     * 计算旋转后的尺寸
     * @param src
     * @param angel
     * @return
     */
    private static Rectangle calculatorRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }
}
