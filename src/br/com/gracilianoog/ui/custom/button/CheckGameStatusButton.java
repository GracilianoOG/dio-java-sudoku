package br.com.gracilianoog.ui.custom.button;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class CheckGameStatusButton extends JButton {
    public CheckGameStatusButton(ActionListener actionListener) {
        this.setText("Verificar jogo");
        this.addActionListener(actionListener);
    }
}
