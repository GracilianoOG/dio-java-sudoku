package br.com.gracilianoog.ui.custom.input;

import br.com.gracilianoog.model.Cell;
import br.com.gracilianoog.service.EventEnum;
import br.com.gracilianoog.service.EventListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NumberText extends JTextField implements EventListener {
    private final Cell cell;

    public NumberText(Cell cell) {
        this.cell = cell;
        Dimension dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!cell.isFixed());

        if(cell.isFixed()) {
            this.setText(cell.getActual().toString());
        }

        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeCell();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeCell();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeCell();
            }

            private void changeCell() {
                if(getText().isEmpty()) {
                    cell.clearCell();
                    return;
                }
                cell.setActual(Integer.parseInt(getText()));
            }
        });
    }

    @Override
    public void update(EventEnum eventType) {
        if(eventType.equals(EventEnum.CLEAR_SPACE) && (this.isEnabled())) {
            this.setText("");
        }
    }
}
