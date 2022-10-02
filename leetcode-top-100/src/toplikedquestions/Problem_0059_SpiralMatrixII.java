package toplikedquestions;

// 螺旋矩阵II
// https://leetcode.cn/problems/spiral-matrix-ii/
public class Problem_0059_SpiralMatrixII {

    // 思路: 搞一个左上角点和右下角点, 两个点同时向中间靠拢, 一层层打印(nxn肯定是正方形)
    public int[][] generateMatrix(int n) {
        if (n == 0) {
            return new int[0][0];
        }
        int[][] matrix = new int[n][n];
        int a = 0;
        int b = 0;
        int c = n - 1;
        int d = n - 1;
        int start = 1;
        int end = n * n;
        while (a <= c && b <= d) {
            start = addEdge(matrix, a++, b++, c--, d--, start, end);
        }
        return matrix;
    }

    public int addEdge(int[][] matrix, int a, int b, int c, int d, int start, int end) {
        if (start == end) {  // matrix已经遍历结束了(填写最中间的)
            matrix[a][b] = start;
        } else {
            int curR = a;
            int curC = b;
            while (curC != d) {  // top, 结束后在右上角点
                matrix[a][curC++] = start;
                start++;
            }
            while (curR != c) {  // right, 结束后在右下角点
                matrix[curR++][d] = start;
                start++;
            }
            while (curC != b) {  // down, 结束后在左下角点
                matrix[c][curC--] = start;
                start++;
            }
            while (curR != a) {  // left, 结束后在左上角点
                matrix[curR--][b] = start;
                start++;
            }
        }
        return start;
    }
}
