package toplikedquestions;


import java.util.Arrays;

// N 皇后II
// https://leetcode.cn/problems/n-queens-ii/
public class Problem_0052_NQueensII {
    int result = 0;
    public int totalNQueens(int n) {
        char[][] path = new char[n][n];
        for (char[] charArr : path) {
            Arrays.fill(charArr, '.');
        }
        process(path, 0);
        return result;
    }

    public void process(char[][] path, int row) {
        if (row == path.length) {  // 收集答案
            result++;
        } else {
            for (int col = 0; col < path.length; col++) {
                if (!visited(path, row, col)) {
                    continue;
                }
                // 标记现场
                path[row][col] = 'Q';
                // 进入下层决策
                process(path, row + 1);
                // 恢复现场
                path[row][col] = '.';
            }
        }
    }

    public boolean visited(char[][] path, int row, int col) {
        int size = path.length;
        // 正上方
        for (int i = 0; i < row; i++) {
            if (path[i][col] == 'Q') {
                return false;
            }
        }

        // 左上角
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (path[i][j] == 'Q') {
                return false;
            }
        }

        // 右上角
        for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++) {
            if (path[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
