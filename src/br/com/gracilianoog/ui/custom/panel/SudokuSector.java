package br.com.gracilianoog.ui.custom.panel;

import br.com.gracilianoog.ui.custom.input.NumberText;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class SudokuSector extends JPanel {
    public SudokuSector(List<NumberText> textFields) {
        Dimension dimension = new Dimension(170, 170);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBorder(new LineBorder(Color.black, 2, true));
        this.setVisible(true);
        textFields.forEach(this::add);
    }
}
