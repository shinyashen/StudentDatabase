package ui;

import entity.College;
import entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class CollegeDataFrame extends Frame {
    private int actionType;

    public CollegeDataFrame() {
        super();
    }

    private void frameDispose(ActionEvent e) {
        dispose();
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public void submitEdit(ActionEvent e) {
        College college = new College();
        String cno = textField1.getText();
        String cname = textField2.getText();
        resultExit(college.doEdit(cno,cname,actionType));
    }

    public void init() {
        String input = "";
        College college = new College();
        switch (actionType) {
            case 0: // 增加
                showWindow(600, 300, 0);
                break;
            case 1: // 删除，不弹出窗口直接操作
                while(input == null || input.isEmpty()) {
                    input = JOptionPane.showInputDialog(null, "请输入需要删除的学校编号：", "请输入", JOptionPane.INFORMATION_MESSAGE);
                    if (input == null || input.isEmpty())
                        showMessageDialog(null,"输入内容不能为空，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
                }
                resultExit(college.doEdit(input,null,actionType));
                break;
            case 2: // 为修改时需要初始化数据
                while(input == null || input.isEmpty()) {
                    input = JOptionPane.showInputDialog(null, "请输入需要修改的学校编号：", "请输入", JOptionPane.INFORMATION_MESSAGE);
                    if (input == null || input.isEmpty())
                        showMessageDialog(null,"输入内容不能为空，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
                }
                List<College> list = college.doQuery(input, Entity.searchType.CNO);
                if (list == null) {
                    showMessageDialog(null, "未找到该学校！", "操作失败", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (College item : list) { // just 1 item
                    textField1.setText(item.getCno());
                    textField2.setText(item.getCname());
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
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u5b66\u6821\u4fe1\u606f\u64cd\u4f5c");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8bf7\u8f93\u5165\u5b66\u6821\u4fe1\u606f\uff1a");
        contentPane.add(label1);
        label1.setBounds(15, 15, 101, 16);

        //---- label2 ----
        label2.setText("\u5b66\u6821\u7f16\u53f7");
        contentPane.add(label2);
        label2.setBounds(30, 50, label2.getPreferredSize().width, 20);
        contentPane.add(textField1);
        textField1.setBounds(90, 50, 110, textField1.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u5b66\u6821\u540d\u79f0");
        contentPane.add(label3);
        label3.setBounds(30, 85, label3.getPreferredSize().width, 20);
        contentPane.add(textField2);
        textField2.setBounds(90, 90, 110, textField2.getPreferredSize().height);

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
    private JLabel label3;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
