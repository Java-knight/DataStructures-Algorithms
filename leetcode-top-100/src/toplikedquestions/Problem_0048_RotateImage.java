package toplikedquestions;


// 旋转图像
// https://leetcode.cn/problems/rotate-image/
public class Problem_0048_RotateImage {

    // 正方形图像, 画出一个4x4就能找下标变化规律
    public void rotate(int[][] matrix) {
        // 正方形 matrix.length == matrix[0].length
        int tLR = 0;  // 左上角(横坐标)topLeftRow
        int tLC = 0;  // 左上角(纵坐标)topLeftCol
        int lRR = matrix.length - 1;  // 右下角(横坐标)lowRightRow
        int lRC = matrix[0].length - 1;  // 右下角(纵坐标)lowRightCol
        while (tLR < lRR) {  // 解决一层一层环进行旋转(不==是因为中间不需要旋转)
            rotateEdge(matrix, tLR++, tLC++, lRR--, lRC--);  // 始终是每个正方形的左上角和右下角
        }
    }

    // 每层环的旋转
    public void rotateEdge(int[][] m, int tLR, int tLC, int lRR, int lRC) {
        int times = lRC - tLC;
        int tmp = 0;
        for (int i = 0; i < times; i++) {
            tmp = m[tLR][tLC + i];  // 左上角
            m[tLR][tLC + i] = m[lRR - i][tLC];  // 左下角
            m[lRR - i][tLC] = m[lRR][lRC - i];  // 右下角
            m[lRR][lRC - i] = m[tLR + i][lRC];  // 右上角
            m[tLR + i][lRC] = tmp;
        }
    }
}
