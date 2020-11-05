package chess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton {
    private boolean black = true;
    private boolean blue = false;
    private int sequence;
    private int position[];
    static public int step = 0;

    public boolean isBlue() {
        return blue;
    }

    public void setBlue(boolean blue) {
        if(blue){
            if(getSequence() < 0) {
                this.setBackground(Color.blue);
                this.blue = true;
            }
        }else{
            if(this.black){
                this.setBackground(Color.black);
            }else{
                this.setBackground(Color.gray);
            }
            this.blue = false;
        }
    }

    public int[] getPosition() {
        return position;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
        if(this.sequence > 0){
            this.setText(Integer.toString(this.sequence));
            this.setForeground(Color.RED);
        }
    }


    public int getSequence() {
        return sequence;
    }
    public boolean isActivated(){
        if (step == 0) return true;
        if (getSequence() > 0 || !isBlue()) return false;
        if(isBlue()) return true;
        return false;
    }
    public void reset(){
        step = 0;
        setSequence(-1);
        this.setText("");
        setBlue(false);
    }
    Square(boolean black, int row, int column){
        super();
        this.position = new int[2];
        this.position[0] = row;
        this.position[1] = column;
        this.sequence = -1;
        this.black = black;
        this.setEnabled(true);
        if(this.black){
            this.setBackground(Color.black);
        }else{
            this.setBackground(Color.gray);
        }
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFont(new Font("Arial", Font.PLAIN, 25));
        this.setFont(new Font("Arial", Font.PLAIN, 25));
    }
}
