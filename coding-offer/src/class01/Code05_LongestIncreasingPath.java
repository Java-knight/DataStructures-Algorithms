package class01;

/**
 * https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
 * @desc
 * @author knight
 * @date 2023/12/23
 */
public class Code05_LongestIncreasingPath {

    /**
     * 暴力方法; 时间复杂度: O(row * col)
     * @param matrix
     * @return
     */
    public static int longestIncreasingPath1(int[][] matrix) {
        int result = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result = Math.max(result, process1(matrix, i, j));
            }
        }
        return result;
    }

    /**
     * 从 matrix[i][j] 开始走, 走出来的最长递增链, 返回
     */
    private static int process1(int[][] matrix, int i, int j) {
        int up = i > 0 && matrix[i][j] < matrix[i - 1][j] ? process1(matrix, i - 1, j) : 0;  // 往上走
        int down = i < matrix.length - 1 && matrix[i][j] < matrix[i + 1][j] ? process1(matrix, i + 1, j) : 0;  // 往下走
        int left = j > 0 && matrix[i][j] < matrix[i][j - 1] ? process1(matrix, i, j - 1) : 0;  // 往左走
        int right = j < matrix[0].length - 1 && matrix[i][j] < matrix[i][j + 1] ? process1(matrix, i, j + 1) : 0;  // 往右走

        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    /**
     * 动态规划/记忆化搜索; 时间复杂度: O(row * col)
     * 就是暴力计算有很多重复计算, 搞一个dp缓存表, 将重复计算的数据记录下来, 减少重复计算
     * 查缓存 —> 计算 —> 存缓存
     */
    public static int longestIncreasingPath2(int[][] matrix) {
        int result = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result = Math.max(result, process2(matrix, i, j, dp));
            }
        }
        return result;
    }

    private static int process2(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {  // 查缓存
            return dp[i][j];
        }

        // 计算
        int up = i > 0 && matrix[i][j] < matrix[i - 1][j] ? process2(matrix, i - 1, j, dp) : 0;  // 往上走
        int down = i < matrix.length - 1 && matrix[i][j] < matrix[i + 1][j] ? process2(matrix, i + 1, j, dp) : 0;  // 往下走
        int left = j > 0 && matrix[i][j] < matrix[i][j - 1] ? process2(matrix, i, j - 1, dp) : 0;  // 往左走
        int right = j < matrix[0].length - 1 && matrix[i][j] < matrix[i][j + 1] ? process2(matrix, i, j + 1, dp) : 0;  // 往右走
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        // 存缓存
        dp[i][j] = ans;
        return ans;
    }
}
