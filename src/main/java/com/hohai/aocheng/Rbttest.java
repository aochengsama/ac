package com.hohai.aocheng;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Random;

public class Rbttest {
    public static void main(String[] args) throws AWTException {

        Robot robot = new Robot();
        robot.delay(2000);


//        Point point = MouseInfo.getPointerInfo().getLocation();
//        System.out.println(point.x);
//        System.out.println(point.y);
        while (true){
            robot.delay(3000);
            robot.mouseMove(1658, 772);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(200);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_W);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_W);
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_SPACE);



//            robot.keyPress(KeyEvent.VK_W);
//            robot.delay(3000);W

//            int v = (int) (Math.random()*10);
//
//            if (v>=0&&v<=2){
//                robot.mouseMove(2145, 1030);
//                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//                robot.delay(200);
//                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//                robot.mouseMove(905, 875);
//                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
//                robot.delay(200);
//                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
//
//
//            }
//
//            if (v>2&&v<=4){
//
//                robot.keyPress(KeyEvent.VK_W);
//                robot.delay(500);
//                robot.keyRelease(KeyEvent.VK_W);
//
//                robot.keyPress(KeyEvent.VK_A);
//                robot.delay(500);
//                robot.keyRelease(KeyEvent.VK_A);
//            }
//
//            if (v>4&&v<=6)
//            {
//
//                robot.keyPress(KeyEvent.VK_S);
//                robot.delay(500);
//                robot.keyRelease(KeyEvent.VK_S);
//
//                robot.keyPress(KeyEvent.VK_D);
//                robot.delay(500);
//                robot.keyRelease(KeyEvent.VK_D);
//            }
//
//            if (v>6&&v<=8)
//            {
//                robot.keyPress(KeyEvent.VK_C);
//                robot.delay(500);
//                robot.keyRelease(KeyEvent.VK_C);
//            }
//
//            if (v==9){
//
//
//                robot.keyPress(KeyEvent.VK_Z);
//                robot.delay(500);
//                robot.keyRelease(KeyEvent.VK_Z);
//            }

        }

    }
}
