package br.com.gracilianoog.ui.custom.frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(Dimension dimension, JPanel mainPanel) {
        super("Sudoku");
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(mainPanel);
    }
}
