package com.swethavraj.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class VijayaRajuSwethaA7 extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("TicTacToe");
        GridPane grid = new GridPane();
        grid.setMinSize(600, 600);

        TicTacToe game = new TicTacToe();
        game.start('X');
        Label output = new Label(game.getCurrentPlayer() + "'s turn to play");
        output.setFont(new Font(15));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Label label = newGridLabel();
                int finalI = i;
                int finalJ = j;
                label.setOnMouseClicked(event -> {
                    game.next(finalI, finalJ);
                    label.setText(String.valueOf(game.getValue(finalI, finalJ)));
                    if (!game.isGameOver()) {
                        output.setText(game.getCurrentPlayer() + "'s turn.");
                    } else {
                        char winner = game.getWinner();
                        if (winner != 0) {
                            output.setText(winner + " won! The game is over.");
                        } else {
                            output.setText("It is a tie. The game is over.");
                        }
                    }
                });
                grid.add(label, j, i);
            }
        }

        VBox vBox = new VBox();
        vBox.getChildren().add(grid);
        vBox.getChildren().add(output);
        Scene scene = new Scene(vBox, 600, 620);
        stage.setScene(scene);
        stage.show();
    }

    private Label newGridLabel() {
        Label label = new Label();
        label.setStyle("-fx-border-color:#000000;");
        label.setAlignment(Pos.CENTER);
        label.fontProperty().setValue(Font.font("", FontWeight.THIN, 180));
        label.setMinSize(200, 200);
        return label;
    }
}

class TicTacToe {
    private final int rows = 3;
    private final int columns = 3;
    private char[][] cells = new char[rows][columns];
    private char currentPlayer;

    public void next(int pos1, int pos2) {
        if (this.isGameOver()) {
            return;
        }
        if (this.cells[pos1][pos2] == 0) {
            this.cells[pos1][pos2] = this.currentPlayer;
            if (this.currentPlayer == 'X') {
                this.currentPlayer = 'O';
            } else {
                this.currentPlayer = 'X';
            }
        }
    }

    public char getValue(int pos1, int pos2) {
        return this.cells[pos1][pos2];
    }

    public void start(char firstPlayer) {
        this.currentPlayer = firstPlayer;
    }

    public char getCurrentPlayer() {
        return this.currentPlayer;
    }

    public boolean isGameOver() {
        boolean hasWinner = getWinner() != 0;
        return hasWinner || !movesAvailable();
    }

    private boolean movesAvailable() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public char getWinner() {
        if (this.checkRows() != 0) {
            return this.checkRows();
        } else if (this.checkColumns() != 0) {
            return this.checkColumns();
        } else if (this.checkLeftDiagonal() != 0) {
            return this.checkLeftDiagonal();
        } else if (this.checkRightDiagonal() != 0) {
            return this.checkRightDiagonal();
        } else {
            return 0;
        }
    }

    private char checkRows() {
        for (int i = 0; i < rows; i++) {
            boolean same = true;
            for (int j = 1; j < columns; j++) {
                if ((cells[i][0] != cells[i][j])) {
                    same = false;
                    break;
                }
            }
            if (same) {
                return cells[i][0];
            }
        }
        return 0;
    }

    private char checkColumns() {
        for (int i = 0; i < columns; i++) {
            boolean same = true;
            for (int j = 1; j < rows; j++) {
                if ((cells[0][i] != cells[j][i])) {
                    same = false;
                    break;
                }
            }
            if (same) {
                return cells[0][i];
            }
        }
        return 0;
    }

    private char checkLeftDiagonal() {
        for (int i = 1; i < rows; i++) {
            if (cells[0][0] != cells[i][i]) {
                return 0;
            }
        }
        return cells[0][0];
    }

    private char checkRightDiagonal() {
        int col = 1;
        for (int i = rows - 2; i >= 0; i--) {
            if (cells[rows - 1][0] != cells[i][col]) {
                return 0;
            }
            col++;
        }
        return cells[rows - 1][0];
    }
}

/*
Sources used to complete this assignment:
    1. Class notes - Conceptual understanding
    2. Java documentation
    3. Stackoverflow - Syntax for font styling
    4. IntelliJ's autofill commands
 */

