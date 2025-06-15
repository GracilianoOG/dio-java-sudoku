package br.com.gracilianoog;

import br.com.gracilianoog.model.Board;
import br.com.gracilianoog.model.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private final static int BOARD_SIZE = 9;
    private static Board board;

    public static void main(String[] args) {
        final Map<String, String> positions = Stream.of(args).collect(toMap(
                k -> k.split(";")[0],
                v -> v.split("")[1]
        ));

        int option = -1;

        while(true) {
            System.out.println("Selecione a opção desejada:");
            System.out.println("1 - Iniciar Novo Jogo");
            System.out.println("2 - Colocar Novo Número");
            System.out.println("3 - Remover um Número");
            System.out.println("4 - Visualizar Tabuleiro");
            System.out.println("5 - Verificar Status do Jogo");
            System.out.println("6 - Limpar Tabuleiro");
            System.out.println("7 - Finalizar Jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> startGame(positions);
                case 2 -> enterNumber();
                case 3 -> removeNumber();
                case 4 -> showBoard();
                case 5 -> showGameStatus();
                case 6 -> clearGameBoard();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void startGame(Map<String, String> positions) {
        if(nonNull(board)) {
            System.out.println("O jogo já foi iniciado!");
            return;
        }

        List<List<Cell>> cells = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            cells.add(new ArrayList<>());

            for (int j = 0; j < BOARD_SIZE; j++) {
                String positionConfig = positions.get("%s,%s".formatted(i, j));
                int expected = Integer.parseInt(positionConfig.split(",")[0]);
                boolean isFixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                Cell currCell = new Cell(expected, isFixed);
                cells.get(i).add(currCell);
            }
        }

        board = new Board(cells);
        System.out.println("O jogo está pronto para começar!");
    }

    private static void finishGame() {
    }

    private static void clearGameBoard() {
    }

    private static void showGameStatus() {
    }

    private static void showBoard() {
    }

    private static void removeNumber() {
    }

    private static void enterNumber() {
    }
}