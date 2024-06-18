package ui;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public abstract class Frame extends JFrame {
    public Frame() {
        initComponents();
    }

    // 设置并显示窗口
    public void showWindow(int width, int height, int exitOnClose) {
        SwingUtilities.invokeLater(() -> {
            setLocation(width, height);
            if (exitOnClose == 1)
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        });
    }

    // 初始化窗口内容
    public abstract void initComponents();

    // 出错处理
    public void resultExit(int result) {
        switch (result) {
            case -1:
                showMessageDialog(null, "数据库连接失败！", "操作失败", JOptionPane.ERROR_MESSAGE);
                break;
            case -2:
                showMessageDialog(null, "执行该数据变更操作失败！", "操作失败", JOptionPane.ERROR_MESSAGE);
                break;
            case -3:
                showMessageDialog(null, "数据变更过程中发生错误！", "操作失败", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                showMessageDialog(null, "数据变更操作成功！", "操作成功", JOptionPane.INFORMATION_MESSAGE);
        }
        dispose();
    }
}