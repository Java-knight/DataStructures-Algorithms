package toplikedquestions;


import com.sun.org.apache.bcel.internal.generic.LUSHR;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// N 皇后
// https://leetcode.cn/problems/n-queens/
public class Problem_0051_NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] path = new char[n][n];
        for (char[] charArr : path) {
            Arrays.fill(charArr, '.');
        }
        process(result, path, 0);
        return result;
    }

    public void process(List<List<String>> result, char[][] path, int row) {
        if (row == path.length) {  // 收集答案、
            result.add(conversion(path));
        } else {
            for (int col = 0; col < path[row].length; col++) {
                if (!visited(path, row, col)) {  // 该位置是否可以尝试
                    continue;
                }
                // 标记现场
                path[row][col] = 'Q';
                // 进入下层决策
                process(result, path, row + 1);
                // 恢复现场
                path[row][col] = '.';
            }
        }
    }

    // 判断path[row][col]位置是否可以放  'Q'
    public boolean visited(char[][] path, int row, int col) {
        int size = path.length;
        // 判断正上方
        for (int i = 0; i < row; i++) {
            if (path[i][col] == 'Q') {
                return false;
            }
        }

        // 判断左上角
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (path[i][j] == 'Q') {
                return false;
            }
        }

        // 判断右上角
        for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++) {
            if (path[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    // 将char[][] 转换为List<String>
    public List<String> conversion(char[][] path) {
        List<String> list = new ArrayList<>();
        for (char[] charArr : path) {
            list.add(new String(charArr));
        }
        return list;
    }
}
