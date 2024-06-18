package ui;

import java.awt.*;
import entity.Entity;
import entity.Student;

import java.util.List;
import java.awt.event.*;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class StudentDataFrame extends Frame {
    private int actionType;

    public StudentDataFrame() {
        super();
    }

    private void frameDispose(ActionEvent e) {
        dispose();
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public void resultExit(int result) {
        switch (result) {
            case -1:
                showMessageDialog(null, "执行该数据变更操作失败！", "操作失败", JOptionPane.ERROR_MESSAGE);
                break;
            case -2:
                showMessageDialog(null, "数据变更过程中发生错误！", "操作失败", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                showMessageDialog(null, "数据变更操作成功！", "操作成功", JOptionPane.INFORMATION_MESSAGE);
        }
        dispose();
    }

    public void init() {
        String input = "";
        Student student = new Student();
        switch (actionType) {
            case 0: // 增加
                showWindow(600, 300, 0);
                break;
            case 1: // 删除，不弹出窗口直接操作
                while(input == null || input.equals("")) {
                    input = JOptionPane.showInputDialog(null, "请输入需要修改的学生学号：", "请输入", JOptionPane.INFORMATION_MESSAGE);
                    if (input == null || input.equals(""))
                        showMessageDialog(null,"输入内容不能为空，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
                }
                resultExit(student.doEdit(input,null,null,null,actionType));
                break;
            case 2: // 为修改时需要初始化数据
                while(input == null || input.equals("")) {
                    input = JOptionPane.showInputDialog(null, "请输入需要修改的学生学号：", "请输入", JOptionPane.INFORMATION_MESSAGE);
                    if (input == null || input.equals(""))
                        showMessageDialog(null,"输入内容不能为空，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
                }
                List<Student> list = student.doQuery(input, Entity.searchType.SNO);
                if (student.doQuery(input, Entity.searchType.SNO) == null) {
                    showMessageDialog(null, "未找到该学生！", "操作失败", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (Student item : list) { // just 1 item
                    textField1.setText(item.getSno());
                    textField2.setText(item.getSname());
                    textField3.setText(item.getGender());
                    textField4.setText(item.getMno());
                }
                showWindow(600, 300, 0);
                break;
            default:
                break; // do nothing
        }
    }

    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        textField3 = new JTextField();
        label5 = new JLabel();
        textField4 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u5b66\u751f\u4fe1\u606f\u64cd\u4f5c");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8bf7\u8f93\u5165\u5b66\u751f\u4fe1\u606f\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 15), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u5b66\u53f7");
        contentPane.add(label2);
        label2.setBounds(40, 50, label2.getPreferredSize().width, 20);
        contentPane.add(textField1);
        textField1.setBounds(90, 50, 110, textField1.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u59d3\u540d");
        contentPane.add(label3);
        label3.setBounds(40, 85, label3.getPreferredSize().width, 20);
        contentPane.add(textField2);
        textField2.setBounds(90, 85, 110, textField2.getPreferredSize().height);

        //---- label4 ----
        label4.setText("\u6027\u522b");
        contentPane.add(label4);
        label4.setBounds(40, 120, label4.getPreferredSize().width, 20);
        contentPane.add(textField3);
        textField3.setBounds(90, 120, 110, textField3.getPreferredSize().height);

        //---- label5 ----
        label5.setText("\u4e13\u4e1a\u7f16\u53f7");
        contentPane.add(label5);
        label5.setBounds(30, 155, label5.getPreferredSize().width, 21);
        contentPane.add(textField4);
        textField4.setBounds(90, 155, 110, textField4.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u63d0\u4ea4");
        button1.addActionListener(e -> submitEdit(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(35, 200), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.addActionListener(e -> frameDispose(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(140, 200), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(240, 255));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void submitEdit(ActionEvent e) {
        Student student = new Student();
        String sno = textField1.getText();
        String sname = textField2.getText();
        String sgender = textField3.getText();
        String mno = textField4.getText();
        resultExit(student.doEdit(sno,sname,sgender,mno,actionType));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JLabel label3;
    private JTextField textField2;
    private JLabel label4;
    private JTextField textField3;
    private JLabel label5;
    private JTextField textField4;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}