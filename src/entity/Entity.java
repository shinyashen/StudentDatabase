package entity;

import javax.swing.*;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Entity {
    public enum searchType{
        ALL, SNO, CNO, MNO, MNAME, TARGETMNO, SPECIFIC, ALLNO1, ALLNO2
    }

    public <T> boolean isPrintListEmpty(List<T> list){
        if (list == null || list.isEmpty()) {
            showMessageDialog(null,"查询内容为空！","警告", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }
}
