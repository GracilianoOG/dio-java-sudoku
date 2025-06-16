package br.com.gracilianoog.ui.custom.screen;

import br.com.gracilianoog.model.GameStatus;
import br.com.gracilianoog.service.BoardService;
import br.com.gracilianoog.ui.custom.button.CheckGameStatusButton;
import br.com.gracilianoog.ui.custom.button.FinishGameButton;
import br.com.gracilianoog.ui.custom.button.ResetButton;
import br.com.gracilianoog.ui.custom.frame.MainFrame;
import br.com.gracilianoog.ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600, 600);

    private final BoardService boardService;

    private JButton finishGameButton;
    private JButton checkGameStatusButton;
    private JButton resetButton;

    public MainScreen(Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        addFinishGameButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addResetButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
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
            }
        });
        mainPanel.add(resetButton);
    }
}
