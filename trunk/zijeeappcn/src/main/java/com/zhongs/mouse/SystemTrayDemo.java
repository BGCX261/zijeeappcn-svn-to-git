package com.zhongs.mouse;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SystemTrayDemo extends JFrame {
    private TrayIcon trayIcon = null;

    public SystemTrayDemo() {
        this.setTitle("系统托盘的例子");
        jbInit();
        this.setSize(200, 250);
        this.setLocation(300, 300);
    }

    public void jbInit() {
        if (SystemTray.isSupported()) { // 检查当前系统是否支持系统托盘
            SystemTray tray = SystemTray.getSystemTray();// 获取表示桌面托盘区的
                                                         // SystemTray 实例。
            Image image = Toolkit.getDefaultToolkit().getImage("D:\\kissjava.gif");
            PopupMenu popupMenu = new PopupMenu();
            MenuItem exitItem = new MenuItem("关闭");
            MenuItem menuItema = new MenuItem("menu a");
            MenuItem menuItemb = new MenuItem("menu b");
            MenuItem menuItemc = new MenuItem("menu c");
            MenuItem menuItemd = new MenuItem("menu d");
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        System.exit(0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            popupMenu.add(menuItema);
            popupMenu.add(menuItemb);
            popupMenu.add(menuItemc);
            popupMenu.add(menuItemd);
            popupMenu.add(exitItem);
            trayIcon = new TrayIcon(image, "系统托盘{kissjava}", popupMenu);
            trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        showIT(true);
                    }
                }
            });
            try {
                tray.add(trayIcon); // 将 TrayIcon 添加到 SystemTray。
            } catch (AWTException e) {
                System.err.println(e);
            }
        } else {
            System.out.println("你的系统不支持系统托盘");
        }
        ImageIcon icon = new ImageIcon();
        try {
            URL url = new URL("http://img1.qq.com/ent/pics/11436/11436218.jpg");
            icon = new ImageIcon(url);
        } catch (Exception e) {

        }
        JLabel label = new JLabel();
        label.setIcon(icon);
        this.add(label);
    }

    public void showIT(boolean visable) {
        if (this.isVisible() != visable)
            this.setVisible(visable);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SystemTrayDemo().setVisible(true);
            }
        });
    }
}