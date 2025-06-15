package br.com.gracilianoog.model;

import java.util.Collection;
import java.util.List;

import static br.com.gracilianoog.model.GameStatus.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {
    private final List<List<Cell>> cells;

    public Board(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public GameStatus getStatus() {
        if (this.cells.stream().flatMap(Collection::stream).noneMatch(c -> !c.isFixed() && nonNull(c.getActual()))) {
            return NOT_STARTED;
        }

        return this.cells.stream().flatMap(Collection::stream).anyMatch(c -> isNull(c.getActual())) ? INCOMPLETE : COMPLETE;
    }

    public boolean hasAnyError() {
        if (this.getStatus().equals(NOT_STARTED)) {
            return false;
        }

        return this.cells.stream().flatMap(Collection::stream)
                .anyMatch(c -> nonNull(c.getActual()) && !c.getActual().equals(c.getExpected()));
    }

    public boolean changeValue(int col, int row, int value) {
        Cell cell = this.cells.get(col).get(row);

        if(cell.isFixed()) {
            return false;
        }

        cell.setActual(value);
        return true;
    }

    public boolean clearValue(int col, int row) {
        Cell cell = this.cells.get(col).get(row);

        if(cell.isFixed()) {
            return false;
        }

        cell.clearCell();
        return true;
    }

    public void reset() {
        this.cells.forEach(c -> c.forEach(Cell::clearCell));
    }

    public boolean isGameFinished() {
        return !this.hasAnyError() && this.getStatus().equals(COMPLETE);
    }
}
