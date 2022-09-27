package toplikedquestions;


// 解数独
// https://leetcode.cn/problems/sudoku-solver/
public class Problem_0037_SudokuSolver {

    // DFS + 剪支
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        initMaps(board, row, col, bucket);
        process(board, 0, 0, row, col, bucket);
    }

    // 初始化row、col、bucket, 将目前board的状态设置进去
    public void initMaps(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucked) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + (j / 3);  // 3x3的小方格位置
                if (board[i][j] != '.') {  // 不为数字时设置值
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    bucked[bid][num] = true;
                }
            }
        }
    }

    // DFS + 剪支
    public boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        if (i == 9) {  // 收集答案
            return true;
        }
        // 换行逻辑
        int nexti = j != 8 ? i : i + 1;
        int nextj = j != 8 ? j + 1 : 0;
        if (board[i][j] != '.') {  // 已经是num, 没有操作性进入下层决策(剪支1)
            return process(board, nexti, nextj, row, col, bucket);
            } else {  // 是'.', 可以进行操作
            int bid = 3 * (i / 3) + (j / 3);
            for (int num = 1; num <= 9; num++) {
                // 只有三个判断条件都是false的情况下进入(剪支2)
                if ((!row[i][num]) && (!col[j][num]) && (!bucket[bid][num])) {
                    // 标记现场
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                    board[i][j] = (char) (num + '0');
                    // 进入下层决策
                    if (process(board, nexti, nextj, row, col, bucket)) {
                        return true;
                    }
                    // 恢复现场
                    row[i][num] = false;
                    col[j][num] = false;
                    bucket[bid][num] = false;
                    board[i][j] = '.';
                }
            }
            return false;
        }
    }

}
