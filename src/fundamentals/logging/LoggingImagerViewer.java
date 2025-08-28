package logging;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.OutputStream;
import java.util.logging.*;

public class LoggingImagerViewer {
    public static void main(String[] args) {
        //检查Java程序中是否没有指定java.util.logging配置类和配置文件
        if(System.getProperty("java.util.logging.config.class")==null
                &&System.getProperty("java.util.logging.config.file")==null){
            try{
                //获取日志记录器对象ImageViewer，设置日志记录器的日志级别为ALL
                Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL);
                //日志文件二点醉倒轮换输俩个，日志文件最多保存10个轮换文件。
                final int LOG_ROTATION_COUNT = 10;
                //创建日志文件处理器，处理器有FileHandler和SocketHandler，其都继承自ConsoleHandler。
                //将日志文件写入到用户的主目录下的LoggingImageViewer.log文件中，0表示不限制日志文件大小。
                var handler = new FileHandler("%h/LoggingImageViewer.log",0,LOG_ROTATION_COUNT);
                //为日志记录器设置日志处理器
                Logger.getLogger("com.horstmann.corejava").addHandler(handler);
            }catch (Exception e){
                Logger.getLogger("com.horstmann.corejava").log(Level.SEVERE, "Can't Set Up Logging", e);
            }
        }
        //invokeLater()方法，将任务提交给事件队列，并返回。
        EventQueue.invokeLater(() -> {
            //获得自定义的日志处理器WindowHandler
            var windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("com.horstmann.corejava").addHandler(windowHandler);
            //创建ImageViewerFrame对象
            var frame = new ImageViewerFrame();
            frame.setTitle("LoggingImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Logger.getLogger("com.horstmann.corejava").fine("Showing frame");
            frame.setVisible(true);
        });
    }
}

class ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    //使用指定类初始化日志对象。
    private static Logger logger = Logger.getLogger("com.horstmann.corejava");
    //构造器
    public ImageViewerFrame() {
        //记录方法条目，传参：发出日志记录请求的类的名称，正在输入的方法的名称
        logger.entering("ImageViewerFrame", "<init>");
        //设置窗口大小
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //设置放菜单栏的容器
        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //创建菜单
        var menu = new JMenu("File");
        menuBar.add(menu);
        //创建菜单项，表示一个菜单中的单个选项，菜单项的文本时Open
        var openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());
        //创建菜单项，表示一个菜单中的单个选项，菜单项的文本时Exit
        var exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        //添加ActionListener，用户点击事件，点击exit时，执行actionPerformed()方法
        exitItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                logger.fine("Exiting");
                System.exit(0);
            }
        });
        label = new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame", "<init>");
    }
    //内部类
    private class FileOpenListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            logger.entering("ImageViewerFrame.FileOpenListener", "ActionPerformed", event);
            //创建文件选择器
            var chooser = new JFileChooser();
            //设置当前目录
            chooser.setCurrentDirectory(new File("."));
            //创建文件过滤器
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                //判断文件是否是目录或者文件名是否以.gif结尾
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".gif");
                }
                //获取文件描述
                public String getDescription() {
                    return "GIF Images";
                }
            });
            //
            int r = chooser.showOpenDialog(ImageViewerFrame.this);
            if (r == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            }
            else logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
        }
    }
}
//StreamHandler是抽象类，继承自Handler，用于日志输出
class WindowHandler extends StreamHandler {
    //
    private JFrame frame;
    public WindowHandler(){
        //实例化一个窗口
        frame = new JFrame();
        //实例化窗口文本显示区域
        var output = new JTextArea();
        //设置文本不可编辑
        output.setEditable( false);
        //设置窗口区域大小
        frame.setSize(200,200);
        //添加文本区域超出内容使用滚动条展示
        frame.add(new JScrollPane(output));
        //设置窗口可见
        frame.setVisible(true);
        //调用了setOutputStream()方法，并传入了一个OutputStream类的匿名内部类，用于字节流的输出操作。
        setOutputStream(new OutputStream(){
            //重写OutputStream的write()方法
            public void write(int b){

            }
            //重写OutputStream的write()方法，将b写入流中
            public void write(byte[] b, int off, int len){
                output.append(new String(b, off, len));
            }
        });
    }
    //重写Handler的publish()方法
    public void publish(LogRecord record){
        //如果窗口不可见，则返回
        if(!frame.isVisible())return;
        //调用父类的publish()方法
        super.publish(record);
        //刷新流，将混村的数据强制输出
        flush();
    }
}