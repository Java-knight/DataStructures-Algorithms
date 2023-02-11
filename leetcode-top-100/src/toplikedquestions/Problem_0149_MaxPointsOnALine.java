package toplikedquestions;


import java.util.HashMap;
import java.util.Map;

// 直线上最多的点数
// https://leetcode.cn/problems/max-points-on-a-line/
public class Problem_0149_MaxPointsOnALine {

    public int maxPoints(int[][] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }

        /**
         * Map<Integer, Map<Integer, Integer>>的含义: 斜率表(经过约分的), 也可以使用Map<String, Integer>表示
         * Map.key表示斜率的分子, Map.map.key表示斜率的分母, Map.map.val表示斜率的值
         */
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {  // 每个点求一个斜率
            map.clear();  // 这里的map不是斜率表, 是斜率表的value
            int samePosition = 1;  // 这个(x, y)上的点
            int sameX = 0;  // 斜率为0, 因为它是跟(x, y)是一个纵坐标的(异常斜率)
            int sameY = 0;  // 斜率为无穷大, 因为它是跟(x, y)是一个横坐标的(异常斜率)
            int line = 0;  // 斜率表中的最大值
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if (x == 0 && y == 0) {  // case1: 相同点
                    samePosition++;
                } else if (x == 0) {  // case2: 一个横坐标, 斜率无穷大
                    sameX++;
                } else if (y == 0) {  // case2: 一个纵坐标, 斜率为0
                    sameY++;
                } else {  // case3: 有斜率(可能是相同, 需要约分)
                    int gcb = gcb(x, y);
                    x /= gcb;
                    y /= gcb;
                    if (!map.containsKey(x)) {
                        map.put(x, new HashMap<Integer, Integer>());
                    }
//                    map.get(x).put(y, map.get(x).getOrDefault(y, 0) + 1);
                    if (!map.get(x).containsKey(y)) {
                        map.get(x).put(y, 0);
                    }
                    map.get(x).put(y, map.get(x).get(y) + 1);
                    line = Math.max(line, map.get(x).get(y));
                }
            }
            result = Math.max(result, Math.max(Math.max(sameX, sameY), line) + samePosition);
        }
        return result;
    }

    /**
     * 求公约数: 辗转相除法(x, y不可以为0)
     * @param x 分子
     * @param y 分母
     * @return
     */
    private int gcb(int x, int y) {
        return y == 0 ? x : gcb(y, x % y);
    }
}
