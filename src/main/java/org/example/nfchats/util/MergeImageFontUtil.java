package org.example.nfchats.util;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * Description 文字 图片合成 消除文字锯齿
 */

public class MergeImageFontUtil {
    /** 字体 */
    private static String FONT_FAMILY = "华文行楷";
    /** 字体大小 */
    private static Integer FONT_SIZE = 60;
    /** 颜色 */
    private static Color FONT_COLOR = new Color(26,160,225);
    /**
     * @Description 填充文字内容从左往右
     * @param content - 内容
     * @param g - Graphics2D对象
     * @param x - 坐标
     * @param y - 坐标
     * @param fontFamily  - 字体
     * @param fontSize  - 字体大小
     * @param fontColor - 字体颜色
     **/
    public static void setContentToImgLR(String content,Graphics2D g,Integer x,Integer y,String fontFamily,
                                        Integer fontSize,Color fontColor) {
        Font font = new Font(fontFamily, Font.PLAIN, fontSize);
        for (int i = 0; i < content.length(); i++) {
            Integer newX = x;
            char singleWordChart = content.charAt(i);
            if(i>0){
                //每个字的间距 默认用字体大小来分割。大家根据自己的实际情况进行修改
                newX = x+(i*fontSize);
            }
            AttributedString singleWord = new AttributedString(String.valueOf(singleWordChart));
            g.setStroke(new BasicStroke(2.5f));
            g.setColor(fontColor);
            g.setFont(font);
            /* 消除java.awt.Font字体的锯齿 */
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            /* 消除java.awt.Font字体的锯齿 */
            singleWord.addAttribute(TextAttribute.FONT, font, 0,
                    String.valueOf(singleWordChart).length());
            AttributedCharacterIterator singleWordIterator = singleWord.getIterator();
            g.drawString(singleWordIterator,newX,y);
        }
    }
    /**
     * @Description 填充文字内容
     * @param content - 内容
     * @param g - Graphics2D对象
     * @param x - 坐标
     * @param y - 坐标
     **/
    public static void setContentToImgLR(String content,Graphics2D g,Integer x,Integer y) {
        setContentToImgLR(content,g,x,y,FONT_FAMILY,FONT_SIZE,FONT_COLOR);
    }

}
