package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.AbstractList;
import java.util.List;

import static java.lang.System.exit;

public abstract class Frame extends JFrame {
    public Frame() {
        initComponents();
    }

    // 设置并显示窗口
    public void showWindow(int width, int height) {
        SwingUtilities.invokeLater(() -> {
            setLocation(width, height);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        });
    }

    // 初始化窗口内容
    public abstract void initComponents();
}