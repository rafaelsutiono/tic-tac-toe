import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    private JFrame window;
    private JPanel buttonPanel, messagePanel;
    private JLabel messageLabel;
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public TicTacToe() {
        window = new JFrame("Noob Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 500);

        buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBackground(new Color(226, 219, 208));
        messagePanel = new JPanel(new FlowLayout());
        messagePanel.setBackground(new Color(226, 219, 208));

        messageLabel = new JLabel("Player " + currentPlayer + "'s turn");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messagePanel.add(messageLabel);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[row][col].setBackground(new Color(236, 224, 198));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                buttonPanel.add(buttons[row][col]);
            }
        }

        window.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        window.getContentPane().add(messagePanel, BorderLayout.SOUTH);

        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("")) {
            clickedButton.setText(Character.toString(currentPlayer));
            if (checkForWin()) {
                JOptionPane.showMessageDialog(window, "Player " + currentPlayer + " wins!");
                resetButtons();
            } else if (checkForTie()) {
                JOptionPane.showMessageDialog(window, "Tie game!");
                resetButtons();
            } else {
                togglePlayer();
                messageLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkForWin() {
        // Check for horizontal wins
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText()) && buttons[row][1].getText().equals(buttons[row][2].getText()) && !buttons[row][0].getText().equals("")) {
                return true;
            }
        }
        // Check for vertical wins
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText()) && buttons[1][col].getText().equals(buttons[2][col].getText()) && !buttons[0][col].getText().equals("")) {
                return true;
            }
        }
        // Check for diagonal wins
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().equals("")) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[0][2].getText().equals("")) {
            return true;
        }
        return false;
    }

    private boolean checkForTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
        messageLabel.setText("Player " + currentPlayer + "'s turn");
    }

    private void togglePlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

