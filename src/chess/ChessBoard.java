package chess;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ChessBoard extends JFrame{
    private Square[][] squares = new Square[8][8];
    private JButton resetButton;
    private JButton runHeuristicButton;
    private JButton runRandomButton;

    private final static int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    private final static int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};

    private final static int[][] accessibility = {{2,3,4,4},{3,4,6,6},{4,6,8,8},{4,6,8,8}};  // 1/4 fo board

    private static int currentRow, currentColumn;

    public int[][] possibleMoves(int row, int column){
        int positions[][] = new int[8][2];
        for(int i = 0; i < 8; i++){
            positions[i][0] = row;
            positions[i][1] = column;
            positions[i][0] += vertical[i];
            positions[i][1] += horizontal[i];
        }
        return positions;
    }
    public static int generateRandomNumber() { // between 0 to 7
        return (int)(Math.random() * 8);
    }
    ChessBoard() {
        super("Knight Moving");
        JPanel panel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        panel.setPreferredSize(new Dimension(600, 600));

        buttonsPanel.setLayout(new GridLayout(1, 3));
        setLayout(new FlowLayout());
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if ((i + j) % 2 != 0) {
                    squares[i][j] = new Square(false, i, j);
                }else{
                    squares[i][j] = new Square(true,  i, j);
                }
                Square finalButton = squares[i][j];

                // show blue squares
                finalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(finalButton.isActivated()){
                            finalButton.setSequence(++Square.step);

                            currentRow = finalButton.getPosition()[0];
                            currentColumn = finalButton.getPosition()[1];
                            // reset blue
                            for(Square squareRow[]: squares)
                                for(Square square : squareRow)
                                    square.setBlue(false);
                            for(int i = 0; i < 8; i++){
                                int row = possibleMoves(currentRow, currentColumn)[i][0];
                                int col = possibleMoves(currentRow, currentColumn)[i][1];
                                if(row >= 0 && row < 8 && col >= 0 && col < 8) {
                                    squares[row][col].setBlue(true);
                                }
                            }
                        }

                        //check program end;
                        boolean checkEnd = true;
                        for(int i = 0; i < 8 && checkEnd; i++ ) {
                            for (Square square : squares[i]) {
                                if (square.isBlue()) {
                                    checkEnd = false;
                                    break;
                                }
                            }
                        }
                        if(checkEnd) JOptionPane.showMessageDialog(null, Integer.toString(Square.step) + " Steps moved");

                    }
                });
                panel.add(squares[i][j]);
            }
        }
        add(panel);
        resetButton = new JButton("Reset");
        runHeuristicButton = new JButton("heuristic Run");
        runRandomButton = new JButton("Random Run");
        runRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean checkEnd = true;

                for(int i = 0; i < 8 && checkEnd; i++ ) {
                    for (Square square : squares[i]) {
                        if (square.isBlue()) {
                            checkEnd = false;
                            currentRow = square.getPosition()[0];
                            currentColumn = square.getPosition()[1];
                            break;
                        }
                    }
                }
                if(checkEnd){
                    currentRow = generateRandomNumber();
                    currentColumn = generateRandomNumber();
                    squares[currentRow][currentColumn].setSequence(++Square.step);
                    for(int i = 0; i < 8; i++) {
                        int row = possibleMoves(currentRow, currentColumn)[i][0];
                        int col = possibleMoves(currentRow, currentColumn)[i][1];
                        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
                            squares[row][col].setBlue(true);
                        }
                    }
                }else{
                    final int randomChoice = generateRandomNumber();
                    int index;
                    boolean pass = false;
                    for(int i = 0; i < 8 && !pass; i++) {
                        if (randomChoice + i >= 8)
                            index = Math.abs(randomChoice - i);
                        else
                            index = randomChoice + i;
                        System.out.println(index);
                        int row = possibleMoves(currentRow, currentColumn)[index][0];
                        int column = possibleMoves(currentRow, currentColumn)[index][1];
                        if((row >= 0 && row < 8 && column >= 0 && column < 8 ) && squares[row][column].isActivated()){
                            pass = true;
                            currentRow = row;
                            currentColumn = column;
                        }
                    }
                    if(pass){
                        for(Square squareRow[]: squares)
                            for(Square square : squareRow)
                                square.setBlue(false);
                        squares[currentRow][currentColumn].setSequence(++Square.step);
                        for(int i = 0; i < 8; i++) {
                            int row = possibleMoves(currentRow, currentColumn)[i][0];
                            int col = possibleMoves(currentRow, currentColumn)[i][1];
                            if (row >= 0 && row < 8 && col >= 0 && col < 8) {
                                squares[row][col].setBlue(true);
                            }
                        }
                    }else{
                        if(checkEnd) JOptionPane.showMessageDialog(null, Integer.toString(Square.step) + " Steps moved");
                    }

                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Square squareRow[]: squares)
                    for(Square square : squareRow){
                        square.reset();
                    }
            }
        });
        buttonsPanel.add(resetButton);
        buttonsPanel.add(runHeuristicButton);
        buttonsPanel.add(runRandomButton);
        add(buttonsPanel);
    }
}