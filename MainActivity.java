package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String[][] board = new String[3][3]; // Tic Tac Toe board
    private boolean isXTurn = true; // X starts first
    private int moveCount = 0; // Track number of moves made

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ""; // Empty string for empty cells
            }
        }

        // Set up onClickListeners for the buttons
        setupButtons();
    }

    private void setupButtons() {
        // Setup buttons with IDs: button00, button01, ..., button22
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int resId = getResources().getIdentifier("button" + i + j, "id", getPackageName());
                Button button = findViewById(resId);
                final int finalI = i;
                final int finalJ = j;
                button.setOnClickListener(v -> makeMove(finalI, finalJ, button));
            }
        }
    }

    private void makeMove(int row, int col, Button button) {
        if (board[row][col].isEmpty()) {
            board[row][col] = isXTurn ? "X" : "O"; // Set the current player's mark
            button.setText(board[row][col]);
            moveCount++;

            // Check for a win
            if (checkWin()) {
                Toast.makeText(this, (isXTurn ? "X" : "O") + " wins!", Toast.LENGTH_SHORT).show();
                navigateToResults(isXTurn ? "X" : "O"); // Pass the winner
            } else if (moveCount == 9) { // Check for a draw
                Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
                navigateToResults("Draw"); // Pass draw result
            } else {
                isXTurn = !isXTurn; // Switch turn
            }
        }
    }

    private boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].isEmpty() && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!board[0][i].isEmpty() && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return true;
            }
        }

        // Check diagonals
        if (!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return true;
        }
        if (!board[0][2].isEmpty() && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return true;
        }

        return false;
    }

    private void navigateToResults(String result) {
        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        intent.putExtra("RESULT", result);
        startActivity(intent);
        finish(); // Close MainActivity
    }
}
