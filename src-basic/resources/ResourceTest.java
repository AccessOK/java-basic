package resources;

import javax.swing.*;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ResourceTest {
    public static void main(String[] args) throws Exception {
        Class cl= ResourceTest.class; //获得拥有资源的Class类
        URL url= cl.getResource("about.gif"); //接受资源的位置
        var icon=new ImageIcon(url); //获得图标
        InputStream stream= cl.getResourceAsStream("data/about.txt"); //得到一个输入流来读取文件中的数据
        var about= new String(stream.readAllBytes(),"UTF-8");
        InputStream stream2= cl.getResourceAsStream("data/title.txt");
        var title= new String(stream2.readAllBytes(), StandardCharsets.UTF_8).strip();
        JOptionPane.showMessageDialog(null,about,title,JOptionPane.INFORMATION_MESSAGE,icon);
    }
}
