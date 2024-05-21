package org.example.nfchats.util;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description 基于昵称生成头像
 */

public class NickNameGenerateAvatarUtil {

    //字体
    private static String FONT_FAMILY_WRYH = "微软雅黑";
    //字体大小
    private static Integer FONT_SIZE = 48;
    /**
     * 绘制字体头像，如果是英文名，只显示首字母大写，
     * @return BufferedImage
     **/
    public static BufferedImage generateAvatarImg(String nickName) {
        Random rand = new Random();
        int red = rand.nextInt(256); // 随机红色分量
        int green = rand.nextInt(256); // 随机绿色分量
        int blue = rand.nextInt(256); // 随机蓝色分量
        return generateAvatarImg(nickName,100, new Color(red, green, blue));
    }

    /**
     * 绘制字体头像，如果是英文名，只显示首字母大写，
     * @param nickName - 昵称
     * @param sideLength - 正方形边长PX
     * @param bgColor - 背景色
     * @return BufferedImage
     **/
    public static BufferedImage generateAvatarImg(String nickName,Integer sideLength,Color bgColor) {
        int width = sideLength;
        int height = sideLength;
        String nameWritten;
        if(null==nickName||"".equals(nickName)){
            nickName = "无";
        }
        if (isChinese(nickName)) {
            // 截取倒数两位汉字
            nameWritten = nickName.substring(0,1);
        } else {
            nameWritten = nickName.substring(0,1).toUpperCase();
        }
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setBackground(bgColor);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.WHITE);
        Font font;
        AttributedString ats = new AttributedString(nameWritten);
        font = new Font(FONT_FAMILY_WRYH, Font.PLAIN, FONT_SIZE);
        g2.setFont(font);
        /* 消除java.awt.Font字体的锯齿 */
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        /* 消除java.awt.Font字体的锯齿 */
        ats.addAttribute(TextAttribute.FONT, font, 0, nameWritten.length());
        AttributedCharacterIterator iter = ats.getIterator();
        // 中文
        if(isChinese(nameWritten)) {
            g2.drawString(iter, 25, 70);
        } else {
            g2.drawString(iter, 33, 70);
        }
       return bufferedImage;
    }

    /**
     * 判断字符串是否为中文
     **/
    private static boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
