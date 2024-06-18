import ui.LoginFrame;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
        initGlobalFontSetting(new Font("微软雅黑", Font.PLAIN, 12)); // 全局窗口字体样式
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.showWindow(700, 300,1);
    }

    public static void initGlobalFontSetting(Font fnt) {
        // 设置全局窗口字体样式
        FontUIResource fontRes = new FontUIResource(fnt);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }
}
