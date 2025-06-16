package br.com.gracilianoog.service;

import br.com.gracilianoog.model.Board;
import br.com.gracilianoog.model.Cell;
import br.com.gracilianoog.model.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService {
    private final static int BOARD_SIZE = 9;
    private final Board board;

    public BoardService(Map<String, String> gameConfig) {
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Cell>> getCells() {
        return this.board.getCells();
    }

    public void reset() {
        this.board.reset();
    }

    public boolean hasErrors() {
        return this.board.hasAnyError();
    }

    public GameStatus getGameStatus() {
        return this.board.getStatus();
    }

    public boolean isGameFinished() {
        return board.isGameFinished();
    }

    private List<List<Cell>> initBoard(Map<String, String> gameConfig) {
        List<List<Cell>> cells = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            cells.add(new ArrayList<>());

            for (int j = 0; j < BOARD_SIZE; j++) {
                String positionConfig = gameConfig.get("%s,%s".formatted(i, j));
                int expected = Integer.parseInt(positionConfig.split(",")[0]);
                boolean isFixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                Cell currCell = new Cell(expected, isFixed);
                cells.get(i).add(currCell);
            }
        }

        return cells;
    }
}
