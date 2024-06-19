package ui;

import entity.Entity;
import entity.Major;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class MajorDataFrame extends Frame {
    private int actionType;

    public MajorDataFrame() {
        super();
    }

    private void frameDispose(ActionEvent e) {
        dispose();
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public void submitEdit(ActionEvent e) {
        Major major = new Major();
        String mno = textField1.getText();
        String mname = textField2.getText();
        resultExit(major.doEdit(mno,mname,actionType));
    }

    public void init() {
        String input = "";
        Major major = new Major();
        switch (actionType) {
            case 0: // 增加
                showWindow(600, 300, 0);
                break;
            case 1: // 删除，不弹出窗口直接操作
                while(input == null || input.isEmpty()) {
                    input = JOptionPane.showInputDialog(null, "请输入需要删除的专业编号：", "请输入", JOptionPane.INFORMATION_MESSAGE);
                    if (input == null || input.isEmpty())
                        showMessageDialog(null,"输入内容不能为空，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
                }
                resultExit(major.doEdit(input,null,actionType));
                break;
            case 2: // 为修改时需要初始化数据
                while(input == null || input.isEmpty()) {
                    input = JOptionPane.showInputDialog(null, "请输入需要修改的专业编号：", "请输入", JOptionPane.INFORMATION_MESSAGE);
                    if (input == null || input.isEmpty())
                        showMessageDialog(null,"输入内容不能为空，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
                }
                List<Major> list = major.doQuery(input, Entity.searchType.MNO);
                if (list == null) {
                    showMessageDialog(null, "未找到该学校！", "操作失败", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (Major item : list) { // just 1 item
                    textField1.setText(item.getMno());
                    textField2.setText(item.getMname());
                }
                showWindow(600, 300, 0);
                break;
            default:
                break; // do nothing
        }
    }

    public void editFromMenu(String input) {
        Major major = new Major();
        actionType = 2;
        List<Major> list = major.doQuery(input, Entity.searchType.MNO);
        if (list == null) {
            showMessageDialog(null, "未找到该学校！", "操作失败", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (Major item : list) { // just 1 item
            textField1.setText(item.getMno());
            textField2.setText(item.getMname());
        }
        showWindow(600, 300, 0);
    }

    public void deleteFromMenu(String input) {
        Major major = new Major();
        actionType = 1;
        resultExit(major.doEdit(input,null,actionType));
    }

    private void textField2KeyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER)
            submitEdit(null);
    }

    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        label3 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u4e13\u4e1a\u4fe1\u606f\u64cd\u4f5c");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8bf7\u8f93\u5165\u4e13\u4e1a\u4fe1\u606f\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 15), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u4e13\u4e1a\u7f16\u53f7");
        contentPane.add(label2);
        label2.setBounds(30, 50, 51, 25);
        contentPane.add(textField1);
        textField1.setBounds(90, 55, 110, textField1.getPreferredSize().height);

        //---- textField2 ----
        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textField2KeyPressed(e);
            }
        });
        contentPane.add(textField2);
        textField2.setBounds(90, 90, 110, textField2.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u4e13\u4e1a\u540d\u79f0");
        contentPane.add(label3);
        label3.setBounds(30, 85, label3.getPreferredSize().width, 20);

        //---- button1 ----
        button1.setText("\u63d0\u4ea4");
        button1.addActionListener(e -> submitEdit(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(35, 135), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.addActionListener(e -> frameDispose(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(140, 135), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(240, 190));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
