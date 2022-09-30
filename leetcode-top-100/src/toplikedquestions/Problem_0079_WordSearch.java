package toplikedquestions;


// 单词搜索
// https://leetcode.cn/problems/word-search/
public class Problem_0079_WordSearch {

    public boolean exist(char[][] board, String word) {
        char[] path = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, path, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 从(i, j)位置开始, index 表示从path[index...]是否可以在board上找到符合的路径
    public boolean process(char[][] board, char[] path, int index, int i, int j) {
        if (index == path.length) {  // 收集答案
            return true;
        }
        // 越界(剪枝)
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return false;
        }
        // 不符合要求(剪枝)
        if (board[i][j] != path[index]) {
            return false;
        }

        // 保存现场
        char tmp = board[i][j];
        board[i][j] = 0;
        // 进入下层决策(上、右、下、左)
        boolean flag = process(board, path, index + 1, i - 1, j) ||
                process(board, path, index + 1, i, j + 1) ||
                process(board, path, index + 1, i + 1, j) ||
                process(board, path, index + 1, i, j - 1);
        // 恢复现场
        board[i][j] = tmp;
        return flag;
    }
}
