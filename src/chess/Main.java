package chess;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){
        ChessBoard textFieldFrame = new ChessBoard();
        textFieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textFieldFrame.setSize(600,700);
        textFieldFrame.setVisible(true);
    }
}