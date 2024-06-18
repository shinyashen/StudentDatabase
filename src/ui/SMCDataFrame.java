package ui;

import entity.Entity;
import entity.SMC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class SMCDataFrame extends Frame {
    private int actionType;

    public SMCDataFrame() {
        super();
    }

    private void frameDispose(ActionEvent e) {
        dispose();
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public void submitEdit(ActionEvent e) {
        SMC smc = new SMC();
        String sno = textField1.getText();
        String cno = textField2.getText();
        String mno = textField2.getText();
        resultExit(smc.doEdit(sno,cno,mno,actionType));
    }

    public void init() {
        String input = "";
        SMC smc = new SMC();
        switch (actionType) {
            case 0: // 增加
                showWindow(600, 300, 0);
                break;
            case 1: // 删除，不弹出窗口直接操作
                while(input == null || input.isEmpty()) {
                    input = JOptionPane.showInputDialog(null, "请输入需要删除的学生学号：", "请输入", JOptionPane.INFORMATION_MESSAGE);
                    if (input == null || input.isEmpty())
                        showMessageDialog(null,"输入内容不能为空，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
                }
                resultExit(smc.doEdit(input,null,null,actionType));
                break;
            case 2: // 为修改时需要初始化数据
                while(input == null || input.isEmpty()) {
                    input = JOptionPane.showInputDialog(null, "请输入需要修改的学生学号：", "请输入", JOptionPane.INFORMATION_MESSAGE);
                    if (input == null || input.isEmpty())
                        showMessageDialog(null,"输入内容不能为空，请重新输入！","警告",JOptionPane.WARNING_MESSAGE);
                }
                List<SMC> list = smc.doQuery(input, Entity.searchType.ALLNO);
                if (list == null) {
                    showMessageDialog(null, "未找到该学生信息！", "操作失败", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (SMC item : list) { // just 1 item
                    textField1.setText(item.getSname());
                    textField2.setText(item.getCname());
                    textField3.setText(item.getMname());
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
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u8003\u7814\u4fe1\u606f\u64cd\u4f5c");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8bf7\u8f93\u5165\u8003\u7814\u4fe1\u606f\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 15), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u5b66\u53f7");
        contentPane.add(label2);
        label2.setBounds(40, 50, label2.getPreferredSize().width, 20);
        contentPane.add(textField1);
        textField1.setBounds(90, 55, 110, textField1.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u5b66\u6821\u7f16\u53f7");
        contentPane.add(label3);
        label3.setBounds(30, 85, label3.getPreferredSize().width, 20);
        contentPane.add(textField2);
        textField2.setBounds(90, 90, 110, textField2.getPreferredSize().height);

        //---- label4 ----
        label4.setText("\u4e13\u4e1a\u7f16\u53f7");
        contentPane.add(label4);
        label4.setBounds(30, 120, 51, 25);
        contentPane.add(textField3);
        textField3.setBounds(90, 125, 110, textField3.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u63d0\u4ea4");
        button1.addActionListener(e -> submitEdit(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(35, 170), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.addActionListener(e -> frameDispose(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(140, 170), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(240, 225));
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
    private JLabel label4;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
