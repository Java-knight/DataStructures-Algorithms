package toplikedquestions;


// 跳跃游戏
// https://leetcode.cn/problems/jump-game/
public class Problem_0055_JumpGame {

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }

        int max = nums[0];  // 表示能到达的最大下标
        for (int i = 1; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

//    public boolean canJump(int[] nums) {
//        if (nums == null || nums.length < 2) {
//            return true;
//        }
//
//        int max = nums[0];  // 表示能到达的最大下标
//        for (int i = 1; i < nums.length; i++) {
//            if (max > nums.length - 1) {  // 优化:已经提前直到可以达到size-1, 可以直接退出
//                break;
//            }
//            if (i > max) {
//                return false;
//            }
//            max = Math.max(max, i + nums[i]);
//        }
//        return true;
//    }
}
