#include <iostream>
#include <vector>
using namespace std;

// Function to print the n-Queens matrix
void printBoard(const vector<vector<int>> &board)
{
    int n = board.size();
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

// Function to check if it's safe to place a queen at (row, col)
bool isSafe(const vector<vector<int>> &board, int row, int col)
{
    int n = board.size();

    // Check this column on upper side
    for (int i = 0; i < row; i++)
    {
        if (board[i][col] == 1)
            return false;
    }

    // Check upper left diagonal
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
    {
        if (board[i][j] == 1)
            return false;
    }

    // Check upper right diagonal
    for (int i = row, j = col; i >= 0 && j < n; i--, j++)
    {
        if (board[i][j] == 1)
            return false;
    }

    return true;
}

// Recursive function to solve n-Queens using backtracking
bool solveNQueens(vector<vector<int>> &board, int row)
{
    int n = board.size();

    // If all queens are placed, return true
    if (row >= n)
        return true;

    // Try placing a queen in each column of the current row
    for (int col = 0; col < n; col++)
    {
        if (isSafe(board, row, col))
        {
            // Place the queen at (row, col)
            board[row][col] = 1;

            // Recur to place the rest of the queens
            if (solveNQueens(board, row + 1))
                return true;

            // If placing the queen leads to no solution, backtrack
            board[row][col] = 0;
        }
    }
    return false;
}

int main()
{
    int n;
    cout << "Enter the size of the chessboard (n): ";
    cin >> n;

    // Create an n x n board initialized to 0
    vector<vector<int>> board(n, vector<int>(n, 0));

    // Input the position of the first queen
    int firstRow, firstCol;
    cout << "Enter the position of the first queen (row and column): ";
    cin >> firstRow >> firstCol;

    // Place the first queen
    board[firstRow][firstCol] = 1;

    // Start solving from the next row after placing the first queen
    if (solveNQueens(board, firstRow + 1))
    {
        cout << "Solution for the n-Queens problem:\n";
        printBoard(board);
    }
    else
    {
        cout << "No solution exists for the given board configuration.\n";
    }

    return 0;
}
