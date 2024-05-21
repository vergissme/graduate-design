package org.example.nfchats.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description Java生成九宫格图
 */

public class GongGeUtil {
    //默认每行切割的数量
    private static final Integer DEFAULT_GRID_NUM = 3;
    /**
     * @Description
     * @param filePath - 文件本地路径
     * @return java.util.List<java.awt.image.BufferedImage>
     **/
    public static List<BufferedImage> generateNineGongGe(String filePath) throws Exception {
        return generateNineGongGe(filePath,DEFAULT_GRID_NUM);
    }

    /**
     * @Description
     * @param filePath - 文件本地路径
     * @param gridNum - 宫格数
     * @return java.util.List<java.awt.image.BufferedImage>
     **/
    public static List<BufferedImage> generateNineGongGe(String filePath,int gridNum) throws Exception{
        List<BufferedImage> gongGeImage = new ArrayList<>();
        BufferedImage read = ImageIO.read(new File(filePath));
        int width = read.getWidth()/gridNum;
        int height = read.getHeight()/gridNum;

        int startX = 0;
        int startY = 0;
        for (int i = 0; i < gridNum; i++) {
            for (int j = 0; j < gridNum; j++) {
                BufferedImage subImage = read.getSubimage(startX,startY,
                         width,height);
                gongGeImage.add(subImage);
                startX += width;
            }
            startX = 0;
            startY += height;
        }
        return gongGeImage;
    }
}
