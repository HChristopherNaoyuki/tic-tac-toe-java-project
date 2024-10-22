package Solutions;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe
{
    // Dimensions for the game window
    int boardWidth = 600;
    int boardHeight = 650; // 50px for the text panel on top

    // GUI components
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3]; // 3x3 grid for the game board

    // Player symbols and game state variables
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int turns = 0;

    // Constructor to set up the game
    TicTacToe()
    {
        // Set up the main frame
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set up the text label
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        // Add the text panel to the frame
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Set up the board panel
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        // Create the game buttons
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                // Set button properties
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                // Add action listener to handle button clicks
                tile.addActionListener((ActionEvent e) ->
                {
                    if (gameOver) return; // Ignore clicks if the game is over
                    JButton tile1 = (JButton) e.getSource();
                    if (tile1.getText().isEmpty())
                    {
                        tile1.setText(currentPlayer); // Set current player's symbol
                        turns++; // Increment turn count
                        checkWinner(); // Check for a winner
                        if (!gameOver)
                        {
                            currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX; // Switch player
                            textLabel.setText(currentPlayer + "'s turn."); // Update the label
                        }
                    }
                });
            }
        }
    }

    // Method to check for a winner or tie
    void checkWinner()
    {
        // Check horizontal lines
        for (int r = 0; r < 3; r++)
        {
            if (!board[r][0].getText().isEmpty() &&
                board[r][0].getText().equals(board[r][1].getText()) &&
                board[r][1].getText().equals(board[r][2].getText()))
            {
                for (int i = 0; i < 3; i++)
                {
                    setWinner(board[r][i]); // Highlight winning tiles
                }
                return;
            }
        }

        // Check vertical lines
        for (int c = 0; c < 3; c++)
        {
            if (!board[0][c].getText().isEmpty() &&
                board[0][c].getText().equals(board[1][c].getText()) &&
                board[1][c].getText().equals(board[2][c].getText()))
            {
                for (int i = 0; i < 3; i++)
                {
                    setWinner(board[i][c]); // Highlight winning tiles
                }
                return;
            }
        }

        // Check diagonals
        if (!board[0][0].getText().isEmpty() &&
            board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText()))
        {
            for (int i = 0; i < 3; i++)
            {
                setWinner(board[i][i]); // Highlight winning tiles
            }
            return;
        }

        // Check anti-diagonal
        if (!board[0][2].getText().isEmpty() &&
            board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText()))
        {
            for (int i = 0; i < 3; i++)
            {
                setWinner(board[i][2 - i]); // Highlight winning tiles
            }
            return;
        }

        // Check for tie
        if (turns == 9)
        {
            for (int r = 0; r < 3; r++)
            {
                for (int c = 0; c < 3; c++)
                {
                    setTie(board[r][c]); // Highlight all tiles
                }
            }
            gameOver = true; // Mark game as over
        }
    }

    // Method to highlight the winning tiles
    void setWinner(JButton tile)
    {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!"); // Update message
        gameOver = true; // End the game
    }

    // Method to highlight tied tiles
    void setTie(JButton tile)
    {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!"); // Update message
        gameOver = true; // End the game
    }

    // Main method to start the game
    public static void main(String[] args)
    {
        TicTacToe ticTacToe = new TicTacToe(); // Create a new game instance
    }
}
