package br.com.gracilianoog.ui.custom.button;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class ResetButton extends JButton {
    public ResetButton(ActionListener actionListener) {
        this.setText("Concluir");
        this.addActionListener(actionListener);
    }
}
