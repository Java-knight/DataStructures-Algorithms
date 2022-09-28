package toplikedquestions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 合并区间
// https://leetcode.cn/problems/merge-intervals/
public class Problem_0056_MergeIntervals {

    // 思路: intervals比是一个Nx2的数组, 先按照 Range.start进行排序(比较器), 再根据两个base case 进行合并
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        List<Range> result = new ArrayList<>();
        Range[] arr = new Range[intervals.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Range(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(arr, new Comparator<Range>() {  // 按照 start 进行排序
            @Override
            public int compare(Range o1, Range o2) {
                return o1.start - o2.start;
            }
        });

        int s = arr[0].start;
        int e = arr[0].end;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start > e) {  // base case1 出现断开范围的数组
                result.add(new Range(s, e));  // 收集答案
                s = arr[i].start;
                e = arr[i].end;
            } else {  // base case2 更新范围
                e = Math.max(e, arr[i].end);
            }
        }
        result.add(new Range(s, e));  // 加最后一组
        return generateMatrix(result);
    }

    // 将装结果的 List<Range> 改为 int[][]
    public int[][] generateMatrix(List<Range> list) {
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = new int[] {list.get(i).start, list.get(i).end};
        }
        return result;
    }

    public class Range {
        int start;
        int end;

        public Range(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

}
