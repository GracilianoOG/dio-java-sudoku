package br.com.gracilianoog.model;

public class Cell {
    private Integer actual;
    private final int expected;
    private final boolean isFixed;

    public Cell(int expected, boolean isFixed) {
        this.expected = expected;
        this.isFixed = isFixed;
        if (isFixed) {
            this.actual = expected;
        }
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(final Integer actual) {
        if (isFixed) return;
        this.actual = actual;
    }

    public int getExpected() {
        return expected;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void clearCell() {
        setActual(null);
    }
}
