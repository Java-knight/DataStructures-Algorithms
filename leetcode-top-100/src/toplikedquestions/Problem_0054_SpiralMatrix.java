package toplikedquestions;


import java.util.ArrayList;
import java.util.List;

// 螺旋矩阵
// https://leetcode.cn/problems/spiral-matrix/
public class Problem_0054_SpiralMatrix {

    // 思路: 搞一个左上角点和右下角点, 两个点同时向中间靠拢, 一层层打印(矩形需要注意可能会出现是中间层为横线或竖线)
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        // 左上角点(a, b); 右下角点(c, d)
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a <= c && b <= d) {
            addEdge(matrix, a++, b++, c--, d--, result);
        }
        return result;
    }

    public void addEdge(int[][] matrix, int a, int b, int c, int d, List<Integer> result) {
        if (a == c) {  // 横线
            for (int i = b; i <= d; i++) {
                result.add(matrix[a][i]);
            }
        } else if (b == d) {  // 竖线
            for (int i = a; i <= c; i++) {
                result.add(matrix[i][b]);
            }
        } else {  // 矩形环, 左上角点开始(curR, curC)
            int curR = a;
            int curC = b;
            while (curC != d) {  // top, 结束后在右上角点
                result.add(matrix[a][curC++]);
            }
            while (curR != c) {  // right, 结束后在右下角点
                result.add(matrix[curR++][d]);
            }
            while (curC != b) {  // low, 结束后在左下角点
                result.add(matrix[c][curC--]);
            }
            while (curR != a) {  // left, 结束后在右上角点
                result.add(matrix[curR--][b]);
            }
        }
    }
}
