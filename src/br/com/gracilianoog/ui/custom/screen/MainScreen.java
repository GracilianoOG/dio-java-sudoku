package br.com.gracilianoog.ui.custom.screen;

import br.com.gracilianoog.model.Cell;
import br.com.gracilianoog.model.GameStatus;
import br.com.gracilianoog.service.BoardService;
import br.com.gracilianoog.service.EventEnum;
import br.com.gracilianoog.service.NotifierService;
import br.com.gracilianoog.ui.custom.button.CheckGameStatusButton;
import br.com.gracilianoog.ui.custom.button.FinishGameButton;
import br.com.gracilianoog.ui.custom.button.ResetButton;
import br.com.gracilianoog.ui.custom.frame.MainFrame;
import br.com.gracilianoog.ui.custom.input.NumberText;
import br.com.gracilianoog.ui.custom.panel.MainPanel;
import br.com.gracilianoog.ui.custom.panel.SudokuSector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600, 600);

    private final BoardService boardService;
    private final NotifierService notifierService;

    private JButton finishGameButton;
    private JButton checkGameStatusButton;
    private JButton resetButton;

    public MainScreen(Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        for (int row = 0; row < 9; row += 3) {
            int endRow = row + 2;

            for (int col = 0; col < 9; col += 3) {
                int endCol = col + 2;
                var cells = getCellsFromSector(boardService.getCells(), col, endCol, row, endRow);
                JPanel sector = generateCells(cells);
                mainPanel.add(sector);
            }
        }
        addFinishGameButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addResetButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private JPanel generateCells(List<Cell> cells) {
        List<NumberText> fields = new ArrayList<>(cells.stream().map(NumberText::new).toList());
        fields.forEach(f -> notifierService.subscribe(EventEnum.CLEAR_SPACE ,f));
        return new SudokuSector(fields);
    }

    private List<Cell> getCellsFromSector(List<List<Cell>> cells, int initCol, int endCol, int initRow, int endRow) {
        List<Cell> cellSectors = new ArrayList<>();
        for (int row = initRow; row <= endRow; row++) {
            for (int col = initCol; col <= endCol; col++) {
                cellSectors.add(cells.get(col).get(row));
            }
        }
        return cellSectors;
    }

    private void addFinishGameButton(JPanel mainPanel) {
        this.finishGameButton = new FinishGameButton(e -> {
            if (boardService.isGameFinished()) {
                JOptionPane.showMessageDialog(null, "Parabéns! Você concluiu o jogo!");
                finishGameButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                resetButton.setEnabled(false);
            } else {
                String message = "Seu jogo possui alguma inconsistência! Arrume-o e tente novamente!";
                JOptionPane.showMessageDialog(null, message);
            }
        });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatusButton(JPanel mainPanel) {
        this.checkGameStatusButton = new CheckGameStatusButton(e -> {
            boolean hasErrors = boardService.hasErrors();
            GameStatus gameStatus = boardService.getGameStatus();
            String message = switch (gameStatus) {
                case NOT_STARTED -> "O jogo não foi iniciado";
                case INCOMPLETE -> "O jogo está incompleto";
                case COMPLETE -> "O jogo está completo";
            };
            message += hasErrors ? " e contém erros!" : " e não contém erros!";
            JOptionPane.showMessageDialog(null, message);
        });
        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        this.resetButton = new ResetButton(e -> {
            int dialogueResult = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja reiniciar o jogo?",
                    "Limpar o jogo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (dialogueResult == 0) {
                boardService.reset();
                notifierService.notify(EventEnum.CLEAR_SPACE);
            }
        });
        mainPanel.add(resetButton);
    }
}
