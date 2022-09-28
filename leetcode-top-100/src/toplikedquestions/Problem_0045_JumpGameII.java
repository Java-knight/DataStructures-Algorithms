package toplikedquestions;

// 跳跃游戏II
// https://leetcode.cn/problems/jump-game-ii/
public class Problem_0045_JumpGameII {

    // 思想: 上帝视角, 提前就可以直到你 步数+1 的最远距离
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0;  // 最小跳跃步数
        int cur = 0;  // 当前result步可以到达的最远距离
        int next = nums[0];  // result+1可以达到的最远距离
        for (int i = 1; i < nums.length; i++) {
            if (i > cur) {  // 需要更新步数
                result++;
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
        }
        return result;
    }

//    public int jump(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        int result = 0;  // 最小跳跃步数
//        int cur = 0;  // 当前result步可以到达的最远距离
//        int next = nums[0];  // result+1可以达到的最远距离
//        for (int i = 1; i < nums.length; i++) {
//            if (next >= nums.length - 1) {  // next已经超过了数组最大了, 直接return
//                return result + 1;
//            }
//            if (i > cur) {
//                result++;
//                cur = next;
//            }
//            next = Math.max(next, i + nums[i]);
//        }
//        return result;
//    }
}
