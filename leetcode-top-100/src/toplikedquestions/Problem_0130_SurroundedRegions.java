package toplikedquestions;

// 被围绕的区域
// https://leetcode.cn/problems/surrounded-regions/
public class Problem_0130_SurroundedRegions {
    // 思路: 将board看成是一个水坝, 四边如果是 'O' 的地方就能进水, 水只能上下左右的流,
    //      'X'就是石头, 遇到石头就停止, 被石头包围的水最终也会变成石头。
    // 代码实现: 先将四周水能流到的位置'O' 改为 'F'(染色/保护); 然后将整个board进行还原, 将'O'改为'X', 将'F'改为'O'
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int row = board.length;
        int col = board[0].length;
        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O') {  // top
                // 感染操作
                free(board, 0, j);
            }
            if (board[row - 1][j] == 'O') {  // down
                // 感染操作
                free(board, row - 1, j);
            }
        }
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {  // right
                // 感染操作
                free(board, i, 0);
            }
            if (board[i][col - 1] == 'O') {  // left
                // 感染操作
                free(board, i, col - 1);
            }
        }

        // 现在将board中的 'F'改为'O'(将该位置的'O' 保护起来), 将'O'改为'X'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char c = board[i][j];
                if (c == 'O') {
                    board[i][j] = 'X';
                }
                if (c == 'F') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // 四周进行判断(从入口的'O'是否可以到, 可以到就先将该位置的 'O' 改为 'F')
    public void free(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'F';
        free(board, i - 1, j);  // 上
        free(board, i, j + 1);  // 右
        free(board, i + 1, j);  // 下
        free(board, i, j - 1);  // 左
    }
}
