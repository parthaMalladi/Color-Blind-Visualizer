import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI implements ActionListener {
    // Window
    private JFrame frame;
    private JPanel panel;

    // label
    private JLabel filePickedLabel;
    private JLabel display;

    // Button
    private JButton monochrome;
    private JButton pickFile;
    private JButton deuteranomaly;
    private JButton protanomaly;
    private JButton tritanomaly;

    // Other
    private BufferedImage image;
    private BufferedImage newImage;
    private File f;
    private ImageIcon icon;
    public GUI(){
        // Window
        frame = new JFrame();
        panel = new JPanel();

        // button
        monochrome = new JButton("Monochromacy");
        deuteranomaly = new JButton("Deuteranomaly");
        protanomaly = new JButton("Protanomaly");
        tritanomaly = new JButton("Tritanomaly");
        pickFile = new JButton("Pick a File");

        pickFile.addActionListener(this);
        monochrome.addActionListener(this);
        deuteranomaly.addActionListener(this);
        protanomaly.addActionListener(this);
        tritanomaly.addActionListener(this);

        // label
        filePickedLabel = new JLabel("Choose File", SwingConstants.CENTER);

        // layout
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setLayout(null);

        panel.add(monochrome);
        monochrome.setBounds(10,60,125,30);

        panel.add(deuteranomaly);
        deuteranomaly.setBounds(155,60,125,30);

        panel.add(protanomaly);
        protanomaly.setBounds(300,60,125,30);

        panel.add(tritanomaly);
        tritanomaly.setBounds(445,60,125,30);

        panel.add(pickFile);
        pickFile.setBounds(450,15,120,30);

        panel.add(filePickedLabel);
        filePickedLabel.setBounds(10,15,430,30);
        filePickedLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        // building window
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Color Blind Visualizer");
        frame.setSize(600, 150);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pickFile){
            JFileChooser j = new JFileChooser("C:\\Users\\parth\\Downloads");
            j.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            j.setFileFilter(new FileNameExtensionFilter("JPEG Image", "jpg"));
            int returnVal = j.showOpenDialog(this.frame);

            if (returnVal == JFileChooser.APPROVE_OPTION){
                try{
                    this.image = ImageIO.read(j.getSelectedFile());
                    this.f = j.getSelectedFile();
                    String temp = this.f.toString();
                    temp = temp.substring(temp.lastIndexOf('\\') + 1);
                    filePickedLabel.setText("File Picked: "  + temp);
                } catch (IOException ioe){
                    filePickedLabel.setText("Error");
                }
            } else{
                filePickedLabel.setText("No File Chosen");
            }
        } else if (e.getSource() != pickFile){
            if (this.f != null){
                frame.setSize(1315, 870);
                monochrome.setBounds(10 + 340,60,125,30);
                deuteranomaly.setBounds(155 + 340,60,125,30);
                protanomaly.setBounds(300 + 340,60,125,30);
                tritanomaly.setBounds(445 + 340,60,125,30);
                pickFile.setBounds(450 + 340,15,120,30);
                filePickedLabel.setBounds(10 + 340,15,430,30);

                this.display = new JLabel();
                this.newImage = new BufferedImage(this.image.getWidth(), this.image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                panel.add(this.display);
                display.setBounds(10, 100, 1280, 720);
            } else {
                filePickedLabel.setText("No File Chosen");
                return;
            }

            if (e.getSource() == monochrome){
                for (int i = 0; i < this.image.getWidth(); i++) {
                    for (int j = 0; j < this.image.getHeight(); j++) {
                        Color c = new Color(this.image.getRGB(i, j));
                        int sum = (c.getRed() + c.getGreen() + c.getBlue())/3;
                        int a = 255;
                        int col = (a << 24) | (sum << 16) | (sum << 8) | sum;
                        this.newImage.setRGB(i, j, col);
                    }
                }

                this.icon = new ImageIcon(this.newImage.getScaledInstance(1280, 720, Image.SCALE_SMOOTH));
                this.display.setIcon(icon);

            } else if (e.getSource() == deuteranomaly){
                for (int i = 0; i < this.image.getWidth(); i++) {
                    for (int j = 0; j < this.image.getHeight(); j++) {
                        Color c = new Color(this.image.getRGB(i, j));
                        int r = c.getRed();
                        int g = 0;
                        int b = c.getBlue();
                        int a = 255;
                        int col = (a << 24) | (r << 16) | (g << 8) | b;
                        this.newImage.setRGB(i, j, col);
                    }
                }

                this.icon = new ImageIcon(this.newImage.getScaledInstance(1280, 720, Image.SCALE_SMOOTH));
                this.display.setIcon(icon);

            }  else if (e.getSource() == tritanomaly){
                for (int i = 0; i < this.image.getWidth(); i++) {
                    for (int j = 0; j < this.image.getHeight(); j++) {
                        Color c = new Color(this.image.getRGB(i, j));
                        int r = c.getRed();
                        int g = c.getGreen();
                        int b = 0;
                        int a = 255;
                        int col = (a << 24) | (r << 16) | (g << 8) | b;
                        this.newImage.setRGB(i, j, col);
                    }
                }

                this.icon = new ImageIcon(this.newImage.getScaledInstance(1280, 720, Image.SCALE_SMOOTH));
                this.display.setIcon(icon);

            }  else if (e.getSource() == protanomaly){
                for (int i = 0; i < this.image.getWidth(); i++) {
                    for (int j = 0; j < this.image.getHeight(); j++) {
                        Color c = new Color(this.image.getRGB(i, j));
                        int r = 0;
                        int g = c.getGreen();
                        int b = c.getBlue();
                        int a = 255;
                        int col = (a << 24) | (r << 16) | (g << 8) | b;
                        this.newImage.setRGB(i, j, col);
                    }
                }

                this.icon = new ImageIcon(this.newImage.getScaledInstance(1280, 720, Image.SCALE_SMOOTH));
                this.display.setIcon(icon);
            }
        }
    }
}
