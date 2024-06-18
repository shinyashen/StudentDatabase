package ui;

import entity.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainFrame extends Frame {
    public MainFrame() {
        super();
    }

    public Boolean inputIsNotEmpty(String input) {
        if(input == null || input.isEmpty()) {
            showMessageDialog(null,"特定查询的输入内容不能为空！","警告",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void doQuery(ActionEvent e) {
        int index = ((DefaultComboBoxModel<String>) comboBox1.getModel()).getIndexOf(comboBox1.getSelectedItem());
        String input = textField1.getText();
        switch (index) {
            case 0: // 所有学生信息
                Student student0 = new Student();
                student0.showStudentQuery(student0.doQuery(input, Entity.searchType.ALL), table1);
                break;
            case 1: // 所有专业信息
                Major major0 = new Major();
                major0.showMajorQuery(major0.doQuery(), table1);
                break;
            case 2: // 所有高等院校信息
                College college0 = new College();
                college0.showCollegeQuery(college0.doQuery(), table1);
                break;
            case 3: // 所有学生考研信息
                SMC smc0 = new SMC();
                smc0.showTotalQuery(smc0.doQuery(input, Entity.searchType.ALL), table1);
                break;
            case 4: // 给定学号查询学生信息
                Student student1 = new Student();
                if(inputIsNotEmpty(input))
                    student1.showStudentQuery(student1.doQuery(input, Entity.searchType.SNO), table1);
                break;
            case 5: // 给定学号查询学生考研信息
                SMC smc1 = new SMC();
                if(inputIsNotEmpty(input))
                    smc1.showTotalQuery(smc1.doQuery(input, Entity.searchType.SNO), table1);
                break;
            case 6: // 给定专业名查询学生考研信息
                SMC smc2 = new SMC();
                if(inputIsNotEmpty(input))
                    smc2.showTotalQuery(smc2.doQuery(input, Entity.searchType.MNAME), table1);
                break;
            case 7: // 涉及专业跨考的学生考研信息
                SMC smc3 = new SMC();
                smc3.showTotalQuery(smc3.doQuery(input, Entity.searchType.SPECIFIC), table1);
                break;
            default:
                break; // do nothing
        }
    }

    public void doAdd(ActionEvent e) {

    }

    public void doDelete(ActionEvent e) {

    }

    public void doUpdate(ActionEvent e) {

    }

    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        comboBox1 = new JComboBox<>();
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new Table();
        table1.getTableHeader().setReorderingAllowed(false);

        //======== this ========
        setTitle("\u5b66\u751f\u8003\u7814\u4fe1\u606f\u7ba1\u7406\u7cfb\u7edf");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u6240\u6709\u5b66\u751f\u4fe1\u606f",
            "\u6240\u6709\u4e13\u4e1a\u4fe1\u606f",
            "\u6240\u6709\u9ad8\u7b49\u9662\u6821\u4fe1\u606f",
            "\u6240\u6709\u5b66\u751f\u8003\u7814\u4fe1\u606f",
            "\u7ed9\u5b9a\u5b66\u53f7\u67e5\u8be2\u5b66\u751f\u4fe1\u606f",
            "\u7ed9\u5b9a\u5b66\u53f7\u67e5\u8be2\u5b66\u751f\u8003\u7814\u4fe1\u606f",
            "\u7ed9\u5b9a\u4e13\u4e1a\u540d\u67e5\u8be2\u5b66\u751f\u8003\u7814\u4fe1\u606f",
            "\u6d89\u53ca\u4e13\u4e1a\u8de8\u8003\u7684\u5b66\u751f\u8003\u7814\u4fe1\u606f"
        }));
        contentPane.add(comboBox1);
        comboBox1.setBounds(85, 20, 190, comboBox1.getPreferredSize().height);

        //---- label1 ----
        label1.setText("\u67e5\u8be2\u5185\u5bb9");
        contentPane.add(label1);
        label1.setBounds(20, 20, label1.getPreferredSize().width, 20);
        contentPane.add(textField1);
        textField1.setBounds(295, 20, 95, 25);

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.addActionListener(e -> doQuery(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(410, 20), button1.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 65, 450, 270);

        contentPane.setPreferredSize(new Dimension(490, 425));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox<String> comboBox1;
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private Table table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
