package br.com.gracilianoog;

import br.com.gracilianoog.model.Board;
import br.com.gracilianoog.model.Cell;
import br.com.gracilianoog.util.BoardTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private final static int BOARD_SIZE = 9;
    private static Board board;

    public static void main(String[] args) {
        final Map<String, String> positions = Stream.of(args).collect(toMap(
                k -> k.split(";")[0],
                v -> v.split(";")[1]
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

    private static void enterNumber() {
        if(isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.println("Informe a coluna para inserir o número: ");
        int col = getValidNumber(0, BOARD_SIZE - 1);

        System.out.println("Informe a linha para inserir o número: ");
        int row = getValidNumber(0, BOARD_SIZE - 1);

        System.out.printf("Informe o número que será inserido na posição (%s, %s)", col, row);
        int val = getValidNumber(1, BOARD_SIZE);

        if(!board.changeValue(col, row, val)) {
            System.out.printf("A posição (%s, %s) tem um valor fixo!\n", col, row);
        }
    }

    private static void removeNumber() {
        if(isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.println("Informe a coluna para inserir o número: ");
        int col = getValidNumber(0, BOARD_SIZE - 1);

        System.out.println("Informe a linha para inserir o número: ");
        int row = getValidNumber(0, BOARD_SIZE - 1);

        if(!board.clearValue(col, row)) {
            System.out.printf("A posição (%s, %s) tem um valor fixo!\n", col, row);
        }
    }

    private static void showBoard() {
        if(isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        Object[] args = new Object[BOARD_SIZE * BOARD_SIZE];
        int argPos = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for(var col : board.getCells()) {
                args[argPos++] = " " + ((isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
            }
        }

        System.out.println("Seu jogo se encontra da seguinte forma: ");
        System.out.printf((BoardTemplate.BOARD_TEMPLATE) + "%n", args);
    }

    private static void showGameStatus() {
        if(isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.printf("O jogo se encontra com o status %s\n", board.getStatus().getLabel());

        if(board.hasAnyError()) {
            System.out.println("O jogo contém erros!");
        } else {
            System.out.println("O jogo não contém erros!");
        }
    }

    private static void clearGameBoard() {
        if(isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu tabuleiro? (sim) (nao)");
        String confirm = scanner.next();

        while(!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("nao")) {
            System.out.println("Informe 'sim' ou 'nao'!");
            confirm = scanner.next();
        }

        if(confirm.equalsIgnoreCase("sim")) {
            board.reset();
        }
    }

    private static void finishGame() {
        if(isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        if(board.isGameFinished()) {
            System.out.println("Parabéns! Você concluiu o jogo!");
            showBoard();
            board = null;
        } else if(board.hasAnyError()) {
            System.out.println("Seu jogo contém erros! Verifique a faça os ajustes necessários!");
        } else {
            System.out.println("Você ainda precisa preencher algum espaço!");
        }
    }

    private static int getValidNumber(int min, int max) {
        int current = scanner.nextInt();
        while(current < min || current > max) {
            System.out.printf("Informe o número entre %s e %s\n", min, max);
            current = scanner.nextInt();
        }
        return current;
    }
}