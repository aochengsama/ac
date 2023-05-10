package com.hohai.aocheng;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 直接设置JPanle的背景图片
 *
 * @author hgg
 *
 */
public class BeiJinTest {

    JFrame jframe;

    public static JPanel imgPanel;

    public BeiJinTest() {
        initFrame();
    }

    // 初始化窗口
    public void initFrame() {
        // 利用JPanel添加背景图片
        jframe = new JFrame();

        imgPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(getClass().getResource("src/main/resources/images/beijin1.png"));
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());
                jframe.setSize(icon.getIconWidth(), icon.getIconHeight());
            }
        };

        jframe.setTitle("测试jpanel图片");
        jframe.add(imgPanel);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new BeiJinTest();
    }
}