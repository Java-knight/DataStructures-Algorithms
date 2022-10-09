package toplikedquestions;

import java.util.LinkedList;
import java.util.Queue;

// 加油站
// https://leetcode.cn/problems/gas-station/
public class Problem_0134_GasStation {
    // gas: 加油量 cost: 消耗
    // 思路: gas[i]-cost[i]就是当前i位置的能量剩余,
    // sum: 使用一个sum记录一圈的能量累加和, 判断是否有能在某个位置可以跑一圈,
    //      如果sum累加结果<0, 直接返回-1(从任何位置开始都不能跑完一圈)
    // start, cur: 使用一个cur记录从start位置开始累加能量和, 如果到i位置cur<0了,
    //             表示start跑不到i位置, 那么将cur置空, start设置成i+1, 继续向右跑
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) {
            return -1;
        }

        int sum = 0;  // 一圈的能量累加和
        int cur = 0;  // 从start开始的能量累加和
        int start = 0;  // 结果
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            cur += gas[i] - cost[i];
            if (cur < 0) {  // 当cur < 0, 表示i位置没有剩余能量了或者从某个位置跑过来, 能量为负的了, 需要重新从下个位置开始
                start = i + 1;  // 当前i位置不行, 选择i+1
                cur = 0;
            }
        }
        if (sum < 0) {  // 判断是否有答案, 一圈的能量累加和 < 0, 表示无论从那个位置开始都跑不完一圈
            return -1;
        }
        return start;
    }
}
