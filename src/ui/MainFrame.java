package ui;

import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.System.exit;
import static javax.swing.JOptionPane.showMessageDialog;

public class MainFrame extends Frame {
    private String[] options = { "STUDENT", "COLLEGE", "MAJOR", "SMC" };
    private String prevOption = "";

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
                prevOption = options[0];
                break;
            case 1: // 所有专业信息
                Major major0 = new Major();
                major0.showMajorQuery(major0.doQuery(input, Entity.searchType.ALL), table1);
                prevOption = options[2];
                break;
            case 2: // 所有高等院校信息
                College college0 = new College();
                college0.showCollegeQuery(college0.doQuery(input, Entity.searchType.ALL), table1);
                prevOption = options[1];
                break;
            case 3: // 所有学生考研信息
                SMC smc0 = new SMC();
                smc0.showTotalQuery(smc0.doQuery(input, Entity.searchType.ALL), table1);
                prevOption = options[3];
                break;
            case 4: // 给定学号查询学生信息
                Student student1 = new Student();
                if(inputIsNotEmpty(input)) {
                    student1.showStudentQuery(student1.doQuery(input, Entity.searchType.SNO), table1);
                    prevOption = options[0];
                }
                break;
            case 5: // 给定学号查询学生考研信息
                SMC smc1 = new SMC();
                if(inputIsNotEmpty(input)) {
                    smc1.showTotalQuery(smc1.doQuery(input, Entity.searchType.SNO), table1);
                    prevOption = options[3];
                }
                break;
            case 6: // 给定专业名查询学生考研信息
                SMC smc2 = new SMC();
                if(inputIsNotEmpty(input)) {
                    smc2.showTotalQuery(smc2.doQuery(input, Entity.searchType.MNAME), table1);
                    prevOption = options[3];
                }
                break;
            case 7: // 涉及专业跨考的学生考研信息
                SMC smc3 = new SMC();
                smc3.showTotalQuery(smc3.doQuery(input, Entity.searchType.SPECIFIC), table1);
                prevOption = options[3];
                break;
            default:
                break; // do nothing
        }
    }

    public void doEdit(ActionEvent e) {
        // judge action type
        int actionType = -1;
        switch(e.getActionCommand()) {
            case "增加数据":
                actionType = 0;
                break;
            case "删除数据":
                actionType = 1;
                break;
            case "修改数据":
                actionType = 2;
                break;
            default:
                break; // do nothing
        }

        // select table type
        String tableType = (String) JOptionPane.showInputDialog(null,"请选择所要修改的表:", "选择表", JOptionPane.INFORMATION_MESSAGE, null, options, "STUDENT");
        if (tableType != null) {
            switch (tableType) {
                case "STUDENT":
                    StudentDataFrame studentDataFrame = new StudentDataFrame();
                    studentDataFrame.setActionType(actionType);
                    studentDataFrame.init();
                    break;
                case "COLLEGE":
                    CollegeDataFrame collegeDataFrame = new CollegeDataFrame();
                    collegeDataFrame.setActionType(actionType);
                    collegeDataFrame.init();
                    break;
                case "MAJOR":
                    MajorDataFrame majorDataFrame = new MajorDataFrame();
                    majorDataFrame.setActionType(actionType);
                    majorDataFrame.init();
                    break;
                case "SMC":
                    SMCDataFrame smcDataFrame = new SMCDataFrame();
                    smcDataFrame.setActionType(actionType);
                    smcDataFrame.init();
                    break;
                default:
                    break; // do nothing
            }
        }
    }

    private void frameExit(ActionEvent e) {
        exit(0);
    }

    private void table1MousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            // 获取鼠标右键选中的行
            int row = table1.rowAtPoint(e.getPoint());
            if (row == -1)
                return;
            // 获取已选中的行
            int[] rows = table1.getSelectedRows();
            boolean inSelected = false;
            // 判断当前右键所在行是否已选中
            for (int r : rows) {
                if (row == r) {
                    inSelected = true;
                    break;
                }
            }
            // 当前鼠标右键点击所在行不被选中则高亮显示选中行
            if (!inSelected)
                table1.setRowSelectionInterval(row, row);
        }
    }

    private void table1MouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e))
            popupMenu1.show(e.getComponent(), e.getX(), e.getY());
    }

    private void menuItem1MouseReleased(MouseEvent e) {
        String input;
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (table1.getSelectedRow() != -1)
                input = table1.getValueAt(table1.getSelectedRow(), 0).toString().trim();
            else
                return;
        } else
            return;
        switch (prevOption) {
            case "STUDENT":
                StudentDataFrame studentDataFrame = new StudentDataFrame();
                studentDataFrame.editFromMenu(input);
                break;
            case "COLLEGE":
                CollegeDataFrame collegeDataFrame = new CollegeDataFrame();
                collegeDataFrame.editFromMenu(input);
                break;
            case "MAJOR":
                MajorDataFrame majorDataFrame = new MajorDataFrame();
                majorDataFrame.editFromMenu(input);
                break;
            case "SMC":
                SMCDataFrame smcDataFrame = new SMCDataFrame();
                smcDataFrame.editFromMenu(input);
                break;
            default:
                break; // do nothing
        }
    }

    private void menuItem2MouseReleased(MouseEvent e) {
        String input;
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (table1.getSelectedRow() != -1)
                input = table1.getValueAt(table1.getSelectedRow(), 0).toString().trim();
            else
                return;
        } else
            return;
        switch (prevOption) {
            case "STUDENT":
                StudentDataFrame studentDataFrame = new StudentDataFrame();
                studentDataFrame.deleteFromMenu(input);
                break;
            case "COLLEGE":
                CollegeDataFrame collegeDataFrame = new CollegeDataFrame();
                collegeDataFrame.deleteFromMenu(input);
                break;
            case "MAJOR":
                MajorDataFrame majorDataFrame = new MajorDataFrame();
                majorDataFrame.deleteFromMenu(input);
                break;
            case "SMC":
                SMCDataFrame smcDataFrame = new SMCDataFrame();
                smcDataFrame.deleteFromMenu(input);
                break;
            default:
                break; // do nothing
        }
    }

    private void textField1KeyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER)
            doQuery(null);
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
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        popupMenu1 = new JPopupMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();

        //======== this ========
        setTitle("\u5b66\u751f\u8003\u7814\u4fe1\u606f\u7ba1\u7406\u7cfb\u7edf");
        setResizable(false);
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

        //---- textField1 ----
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textField1KeyPressed(e);
            }
        });
        contentPane.add(textField1);
        textField1.setBounds(295, 20, 105, 25);

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.addActionListener(e -> doQuery(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(420, 20), button1.getPreferredSize()));

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    table1MousePressed(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    table1MouseReleased(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 65, 460, 270);

        //---- button2 ----
        button2.setText("\u589e\u52a0\u6570\u636e");
        button2.addActionListener(e -> doEdit(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(20, 350), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u5220\u9664\u6570\u636e");
        button3.addActionListener(e -> doEdit(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(145, 350), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u4fee\u6539\u6570\u636e");
        button4.addActionListener(e -> doEdit(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(270, 350), button4.getPreferredSize()));

        //---- button5 ----
        button5.setText("\u9000\u51fa\u7cfb\u7edf");
        button5.addActionListener(e -> frameExit(e));
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(395, 350), button5.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(500, 405));
        pack();
        setLocationRelativeTo(getOwner());

        //======== popupMenu1 ========
        {
            popupMenu1.add(menuItem1);
            popupMenu1.add(menuItem2);
        }

        //---- menuItem1 ----
        menuItem1.setText("\u7f16\u8f91(E)");
        menuItem1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                menuItem1MouseReleased(e);
            }
        });

        //---- menuItem2 ----
        menuItem2.setText("\u5220\u9664(D)");
        menuItem2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                menuItem2MouseReleased(e);
            }
        });
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox<String> comboBox1;
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private Table table1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
