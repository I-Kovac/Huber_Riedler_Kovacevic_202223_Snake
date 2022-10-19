package com.example.huber_riedler_kovacevic_202223_snake.model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Playfield Klasse, die das Spielfeld repräsentiert
 * Konstanten:
 *  SNAKECOLOR: die Farbe der Schlange
 *  BACKGROUNDCOLOR: die Farbe des Hintergrunds auf dem sich dei Schlange bewegt
 *  FOODCOLOR: die Farbe des Essens
 *  COLS: Wieviele Spalten das Spielfeld besitzt
 *  ROWS: Wieviele Zeilen das Spielfeld besitzt
 *  EMPTY: Der Zustand eines Feldes wenn es nicht mit Essen oder der Schlange befüllt ist
 *  SNAKE: Der Zustand eines Feldes wenn sich die Schlange darauf bewegt
 *  FOOD: Der Zustand eines Feldes wenn sich Essen darauf befindet
 * Eigenschaften:
 *  snake: die Schlange
 *  foodPosition: die Position auf der sich gerade Essen befindet
 *  rectangles: ein zweidimensionales Array mit den Rechtecken(eigentlich Quadraten), mit denen das Spielfeld visualisiert wird
 *  playfield: ein zweidimensionales Array aus Positions(der logische Aufbau des Spielfelds)
 */
public class Playfield {
    public static final Paint SNAKECOLOR = Color.BLACK;
    public static final Paint BACKGROUNDCOLOR = Color.GREEN;
    public static final Paint FOODCOLOR = Color.RED;
    public static final int COLS = 45;
    public static final int ROWS = 35;
    public static final int EMPTY = 0;
    public static final int SNAKE = 1;
    public static final int FOOD = 2;
    public Snake snake;
    public Position foodPosition;
    public boolean foodSpawned;
    Rectangle[][] rectangles;
    //
    private int playfield[][];

    /**
     * Konstruktor
     * @param cols wieviele Spalten das Spielfeld haben soll
     * @param rows wieviele Zeilen das Spielfeld haben soll
     * @param snake die Schlange mit der gespielt werden soll
     */
    public Playfield(int cols, int rows, Snake snake) {
        this.snake = snake;
        playfield = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                playfield[i][j] = EMPTY;
            }
        }
    }

    /**
     * baut das Spielfeld zum ersten mal auf und legt die Rechtecke zur visualisierung an
     * @return ein javafx Gridpane, welches rectangles[][] beinhaltet
     */
    public GridPane buildPlayfield() {

        rectangles = new Rectangle[Playfield.COLS][Playfield.ROWS];

        GridPane gridPane = new GridPane();
        for (int i = 0; i < Playfield.COLS; i++) {
            for (int j = 0; j < Playfield.ROWS; j++) {
                rectangles[i][j] = new Rectangle(18, 18, BACKGROUNDCOLOR);
                gridPane.add(rectangles[i][j], i, j);
            }
        }
        return gridPane;
    }

    /**
     * Checkt den Zustand des logischen Spielfelds und ändert die Farben der Rechtecke dementsprechend ab
     */
    public void updatePlayfield() {

        for (int i = 0; i < Playfield.COLS; i++) {
            for (int j = 0; j < Playfield.ROWS; j++) {
                if (playfield[i][j] == Playfield.EMPTY) {
                    if (i==Playfield.COLS-1 || i==0){
                        rectangles[i][j].setFill(Color.DARKOLIVEGREEN);
                    } else if (j==Playfield.ROWS-1 || j==0){
                        rectangles[i][j].setFill(Color.DARKOLIVEGREEN);
                    } else {
                        rectangles[i][j].setFill(BACKGROUNDCOLOR);
                    }
                } else if (playfield[i][j] == Playfield.FOOD) {
                    rectangles[i][j].setFill(FOODCOLOR);
                } else if (playfield[i][j] == Playfield.SNAKE) {
                    rectangles[i][j].setFill(SNAKECOLOR);
                }
            }
        }
    }

    /**
     * generiert eine zufällige Zahl
     * @param min minimum
     * @param max maximum
     * @return die zufällige Zahl
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * gibt das logische Spielfeld zurück
     * @return das Spielfeld
     */
    public int[][] getPlayfield() {
        return playfield;
    }

    /**
     * Checkt ob das Feld, welches die Schlange als nächstes betritt, noch den Zustand SNAKE besitzt
     * @return true... wenn ja
     *         false.. wenn nein
     */
    public boolean checkForSnake() {
        boolean ret = false;

        try {
            if (snake.getDirection() == Snake.UP) {
                if (playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow() - 1] == SNAKE) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.RIGHT) {
                if (playfield[snake.getCurrentPosition().getCol() + 1][snake.getCurrentPosition().getRow()] == SNAKE) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.DOWN) {
                if (playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow() + 1] == SNAKE) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.LEFT) {
                if (playfield[snake.getCurrentPosition().getCol() - 1][snake.getCurrentPosition().getRow()] == SNAKE) {
                    ret = true;
                }
            }
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * Checkt ob sich Essen im Feld, welches die Schlange als nächstes betritt, befindet
     * @return true...  wenn ja
     *         false... wenn nein
     */
    public boolean checkForFood() {
        boolean ret = false;

        try {
            if (snake.getDirection() == Snake.UP) {
                if (playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow() - 1] == FOOD) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.RIGHT) {
                if (playfield[snake.getCurrentPosition().getCol() + 1][snake.getCurrentPosition().getRow()] == FOOD) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.DOWN) {
                if (playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow() + 1] == FOOD) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.LEFT) {
                if (playfield[snake.getCurrentPosition().getCol() - 1][snake.getCurrentPosition().getRow()] == FOOD) {
                    ret = true;
                }
            }
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * setzt foodPosition auf eine zufällige, freie Position
     */
    public void setFoodposition() {

        int col = getRandomNumber(1, Playfield.COLS - 2);
        int row = getRandomNumber(1, Playfield.ROWS - 2);

        while (getPlayfield()[col][row] == SNAKE) {
            col = getRandomNumber(1, Playfield.COLS - 2);
            row = getRandomNumber(1, Playfield.ROWS - 2);
        }
        foodPosition = new Position(col, row);
    }

    /**
     * setzt die Felder auf denen sich der Schwanz der Schlange befindet auf SNAKE
     * @param i der Index vom lastPositions Array
     */
    public void setSnakeState(int i) {
        playfield[snake.getLastPositions().get(i).getCol()][snake.getLastPositions().get(i).getRow()] = SNAKE;
    }

    /**
     * setzt die Position foodPosition im playyfield auf FOOD
     */
    public void setFoodState() {
        playfield[foodPosition.getCol()][foodPosition.getRow()] = FOOD;
    }

    /**
     * setzt das Feld direkt hinter der Schlange von SNAKE wieder auf EMPTY
     */
    public void setEmptyState() {
        playfield[snake.getLastPositions().get(0).getCol()][snake.getLastPositions().get(0).getRow()] = EMPTY;
    }

    /**
     * setzt das Feld, auf dem sich der Kopf der Schlange befindet, auf SNAKE
     */
    public void setSnakefirstState() {
        playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow()] = SNAKE;
    }
}
