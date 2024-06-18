package ui;

import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.System.exit;
import static javax.swing.JOptionPane.showMessageDialog;

public class LoginFrame extends Frame {
    public LoginFrame() {
        super();
    }

    private void frameExit(ActionEvent e) {
        exit(0);
    }

    private void passwordField1KeyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER)
            doLogin(null);
    }

    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        passwordField1 = new JPasswordField();

        //======== this ========
        setTitle("\u767b\u5f55\u7cfb\u7edf");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8d26\u53f7");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(30, 20), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(30, 60), label2.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(70, 20, 140, textField1.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u767b\u5f55");
        button1.addActionListener(e -> doLogin(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(30, 100), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u9000\u51fa");
        button2.addActionListener(e -> frameExit(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(150, 100), button2.getPreferredSize()));

        //---- passwordField1 ----
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                passwordField1KeyPressed(e);
            }
        });
        contentPane.add(passwordField1);
        passwordField1.setBounds(70, 60, 140, passwordField1.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(240, 155));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void doLogin(ActionEvent e) {
        String userid = textField1.getText();
        String password = String.valueOf(passwordField1.getPassword());
        User user = new User(userid, password);
        switch (user.checkIsValid()) {
            case -1:
                showMessageDialog(null,"用户名或密码不能为空！","登录失败",JOptionPane.ERROR_MESSAGE);
                break;
            case -2:
                showMessageDialog(null,"连接数据库失败！","登录失败",JOptionPane.ERROR_MESSAGE);
                break;
            case -3:
                showMessageDialog(null,"账号或密码错误！","登录失败",JOptionPane.ERROR_MESSAGE);
                break;
            default:
                showMessageDialog(null,"登录成功！","登录成功",JOptionPane.INFORMATION_MESSAGE);
                MainFrame mainFrame = new MainFrame();
                mainFrame.showWindow(600, 300,1);
                dispose();
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
