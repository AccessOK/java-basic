package corejava.v1ch02;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class ImageViewer {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame frame = new ImageViewerFrame();
            frame.setTitle("ImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

    }
}