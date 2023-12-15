package class01;

import java.util.HashMap;

/**
 * 给定一个非负整数 num，如何不用循环语句，返回 >= num，并且离 num 最近的，2的某次方
 * @author knight
 * @desc
 * @date 2023/12/16
 */
public class Code03_Near2Power {

    /**
     * 已知 num 是正数[|是二进制与]
     * 返回大于等于, 且最接近num的, 2的某次方的值
     * 用途: HashMap指定初始容量使用, 比如你给个80, HashMap的初始容量必是2^N, 最后初始容量就是128
     * java.util.HashMap#tableSizeFor(int)
     * num:   0000 0000 0000 0000 0101 0000
     * res:   0000 0000 0000 0000 1000 0000
     *
     * num--:            0000 0000 0000 0000 0100 1111
     * num | num >>> 1:  0000 0000 0000 0000 0110 1111
     * num | num >>> 2:  0000 0000 0000 0000 0111 1111
     * num | num >>> 4:  0000 0000 0000 0000 0111 1111
     * num | num >>> 8:  0000 0000 0000 0000 0111 1111
     * num | num >>> 16: 0000 0000 0000 0000 0111 1111
     * num + 1:          0000 0000 0000 0000 1000 0000
     */
    public static int tableSizeFor(int num) {
        num--;  // 为了应对num就是2的次方
        // 将2的次方后面变为全1
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        return (num < 0) ? 1 : num + 1;
    }

    public static void main(String[] args) {
        int capity = 120;
        System.out.println(tableSizeFor(capity));
    }
}
