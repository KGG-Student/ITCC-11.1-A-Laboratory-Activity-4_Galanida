//"Tic Tac Toe is fun!!!"
import java.awt.*;
import java.awt.event.*;

public class TicTacToeAWT extends Frame implements ActionListener {
    private Button[][] buttons = new Button[3][3];
    private boolean xTurn = true;
    private Label statuslabel;
    private Button resetbutton;

    public TicTacToeAWT() {
        setTitle("AWT TIC TAC TOE");
        setSize(300, 350);
        setLayout(new BorderLayout());

    //Layout
        Panel gridPanel = new Panel();
        gridPanel.setLayout(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 40);

    //Rows And Columns
    for (int row = 0; row < 3; row++) {
    for (int col = 0; col < 3; col++) {
        buttons[row][col] = new Button("");
        buttons[row][col].setFont(font);
        buttons[row][col].addActionListener(this);
        gridPanel.add(buttons[row][col]);
}
        }
        //Status
        statuslabel = new Label("Player X's turn");
        statuslabel.setAlignment(Label.CENTER);
        add(statuslabel, BorderLayout.NORTH);

        //Reset
        resetbutton = new Button("New Game");
        resetbutton.addActionListener(e -> resetGame());
        add(resetbutton, BorderLayout.SOUTH);

        add(gridPanel, BorderLayout.CENTER);

        //Closes The Program When X is Clicked
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
         });

        setVisible(true);
    }

    //Actions
    @Override
    public void actionPerformed(ActionEvent e) {
        Button clicked = (Button) e.getSource();
        if (!clicked.getLabel().equals("")) return;

        String current = xTurn ? "X" : "O";
        clicked.setLabel(current);

        if (checkWinner(current)) {
            statuslabel.setText("Player " + current + " wins!");
            disableButtons();
        } else if (isBoardFull()) {
            statuslabel.setText("It's a draw!");
        } else {
            xTurn = !xTurn;
            statuslabel.setText("Player " + (xTurn ? "X" : "O") + "'s turn");
        }
    }
    //Checks the Winner
    private boolean checkWinner(String player) {
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getLabel().equals(player) &&
                 buttons[i][1].getLabel().equals(player) &&
                 buttons[i][2].getLabel().equals(player)) ||
                (buttons[0][i].getLabel().equals(player) &&
                 buttons[1][i].getLabel().equals(player) &&
                 buttons[2][i].getLabel().equals(player))) {
                return true;
            }
        }
        return (buttons[0][0].getLabel().equals(player) &&
                buttons[1][1].getLabel().equals(player) &&
                buttons[2][2].getLabel().equals(player)) ||
               (buttons[0][2].getLabel().equals(player) &&
                buttons[1][1].getLabel().equals(player) &&
                buttons[2][0].getLabel().equals(player));
    }
    //Method called when the Board is Full
    private boolean isBoardFull() {
        for (Button[] row : buttons)
            for (Button btn : row)
                if (btn.getLabel().equals(""))
                    return false;
        return true;
    }
    //Disable Buttons when the board is full 
    private void disableButtons() {
        for (Button[] row : buttons)
            for (Button btn : row)
                btn.setEnabled(false);
    }
    //Reset the Game
    private void resetGame() {
        for (Button[] row : buttons) {
            for (Button btn : row) {
                btn.setLabel("");
                btn.setEnabled(true);
            }
        }
        xTurn = true;
        statuslabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        new TicTacToeAWT();
    }
}
