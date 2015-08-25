package com.zhongs.mouse;

// 公司的机器入域之后，十分钟屏保一次，然后还要输入密码。
// 同事用C#写了一个每隔一段时间就让鼠标移动的程序，借助钩子，果然强大。
// 自己试着用Java写了一个小程序，不过简单不少，所以功能也不足。
// 受限于J2SE要借助JNI才能与底层交互，Swing组件失去焦点后，
// 后台监测不到鼠标和键盘事件。唉，什么时候Java能和C/C#强大就好了。
// 现在看来J2EE的地位也受到.NET的冲击啊~~~
// 借助多线程和Swing以及AWT实现，有很多不足，代码规约也不是很好：
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

public class AutoMoveMouse extends JFrame implements Runnable, KeyListener, MouseInputListener {
    private static final long serialVersionUID = 1L;
    private static long TIME_LIMITE = 10;// 时间限制，超过后移动鼠标
    private long timeCount = 0;// 时间计数器(是不是用Java的计时器更好呢？)
    private boolean canMouseAutoMove = true;// 控制移动标志
    private Robot robot = null;
    private JLabel message = new JLabel();

    /**
     * Constructor
     * 
     * @throws AWTException
     */
    public AutoMoveMouse() throws AWTException {
        robot = new Robot();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(message, BorderLayout.CENTER);
        setLocation(Constants.INIT_FRAME_LOCATION_X, Constants.INIT_FRAME_LOCATION_Y);
        setSize(Constants.FRAME_WINDTH, Constants.FRAME_HEIGTH);
        setAlwaysOnTop(true);
        // setFocusableWindowState(true);
        // setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {// <--不是windowClosed，
                // 是windowClosing
                ((Window) e.getComponent()).dispose();
                // System.out.print("Closing");
                System.exit(0);
            }
        });
        setVisible(true);
    }

    /**
     * @param args
     * @throws AWTException 
     */
    public static void main(String[] args) throws AWTException {
        AutoMoveMouse moveMouse = new AutoMoveMouse();
        Thread mouseMove = new Thread(moveMouse);
        mouseMove.start();
    }

    /**
     * Run Tread
     */
    public void run() {
        while (true) {
            if (this.canMouseAutoMove) {
                int x = (int) (Math.random() * Constants.FRAME_WINDTH) + this.getLocation().x;
                int y = (int) (Math.random() * Constants.FRAME_HEIGTH) + this.getLocation().y;
                this.message.setText("(" + x + "," + y + ")");
                robot.mouseMove(x, y);// 移动鼠标到（x,y）
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.canMouseAutoMove = false;
            } else {
                this.message.setText(String.valueOf(this.timeCount++));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.timeCount > AutoMoveMouse.TIME_LIMITE) {
                    this.canMouseAutoMove = true;
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        this.timeCount = 0;
        this.canMouseAutoMove = false;
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        // this.timeCount = 0;
        // this.canMouseAutoMove = false;
        // this.setLocation(e.getX(), e.getY());
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        this.timeCount = 0;
        this.canMouseAutoMove = false;
    }

    /**
     * Constant Class
     * 
     * @author Mui
     * 
     */
    private class Constants {
        private static final int FRAME_WINDTH = 300;
        private static final int FRAME_HEIGTH = 300;
        private static final int INIT_FRAME_LOCATION_X = 0;
        private static final int INIT_FRAME_LOCATION_Y = 0;
    }
}