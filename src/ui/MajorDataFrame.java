package ui;

import java.awt.*;
import javax.swing.*;

public class MajorDataFrame extends Frame {
    public MajorDataFrame() {
        super();
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
        contentPane.add(textField2);
        textField2.setBounds(90, 90, 110, textField2.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u4e13\u4e1a\u540d\u79f0");
        contentPane.add(label3);
        label3.setBounds(30, 85, label3.getPreferredSize().width, 20);

        //---- button1 ----
        button1.setText("\u63d0\u4ea4");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(35, 135), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
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
