package toplikedquestions;


// 有效的数独
// https://leetcode.cn/problems/valid-sudoku/
public class Problem_0036_ValidSudoku {

    // 暴力判断
    public boolean isValidSudoku(char[][] board) {
        // row[i][num] 表示在第i行num是否存在, col同理
        // 因为num是0~9, row[i][0]位置不用, 方便运算, 所以是10个
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + (j / 3);  // 小3x3宫格位置
                if (board[i][j] != '.') {  // 是数字进入判断
                    int num = board[i][j] - '0';  // 这里就实现了row\col设置10的方便了
                    // 3个判断条件
                    if (row[i][num] || col[j][num] || bucket[bid][num]) {
                        return false;
                    }
                    // 设置值
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
        return true;
    }
}
