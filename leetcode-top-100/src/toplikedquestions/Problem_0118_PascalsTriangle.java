package toplikedquestions;

import java.util.ArrayList;
import java.util.List;

// 杨辉三角
// https://leetcode.cn/problems/pascals-triangle/
public class Problem_0118_PascalsTriangle {

    /**
     * 杨辉三角, 当 level 大于2时, 左右两边都是1, 而中间的值是由自己上方+左上方的和
     *  nums[i][j] = nums[i - 1][j] + nums[i - 1][j - 1]
     * 1
     * 1  1
     * 1  2  1
     * 1  3  3  1
     * 1  4  6  4  1
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        // (1) nums[i][0] = 1
        for (int i = 0; i < numRows; i++) {
            result.add(new ArrayList<>());
            result.get(i).add(1);
        }
        // (2) 将每level 的中间值和左右边的值填充
        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < i; j++) {  // nums[i][j] = nums[i-1][j] + nums[i-1][j-1]
                result.get(i).add(result.get(i - 1).get(j) + result.get(i - 1).get(j - 1));
            }
            result.get(i).add(1);  // 每一行的level填写1 nums[i][i] = 1
        }
        return result;
    }
}
