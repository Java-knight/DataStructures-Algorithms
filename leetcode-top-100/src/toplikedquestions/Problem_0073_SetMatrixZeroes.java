package toplikedquestions;

// 矩阵置零
// https://leetcode.cn/problems/set-matrix-zeroes/
public class Problem_0073_SetMatrixZeroes {

    // 问题: 就是小时候玩的 Q版泡泡堂(放炸弹的)

    // 一个布尔变量标识(面试吹牛用, 极客精神)
    // (1) 这个时候指用一个 colFlag表示第0列在改变之前是否有0, 但是(0, 0)位置的数不决定第0列是否有0;
    // 第0行是否有0用(0, 0)位置表示, 如果有就将matrix[0][0]改成0, 没有保持不变就行;
    // (2) 接下来收集matrix[i][j]位置的0(i和j都是从1开始), 将其填入第0行和第0列
    // (3) 判断第0行和第0列中是0的行和列, 将matrix出现行和列的位置填充0
    // (4) 判断matrix(0, 0)位置决定第0行是否全部为0, 而colFlag决定第0列是否全部为0
    // 注意: (3)中是从matrix.length-1的位置开始判断, 因为如果从0位置开始, 可能第0行在未改变前有一个0,
    //       所以将(0, 0)位置改为0, 先判断0位置会导致, 将第0行和第0列全部置0的
    public void setZeroes(int[][] matrix) {
        boolean colFlag = false;
        int i = 0;
        int j = 0;
        for (i = 0; i < matrix.length; i++) {  // (1)和(2)一起进行, 只是(0, 0)位置表示第0行, 而第0列使用colFlag表示
            for (j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    if (j == 0) {
                        colFlag = true;
                    } else {
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        // (4) 必须先行, 后列; 判断第0行改变前是否需要全0
        for (i = matrix.length - 1; i >= 0; i--) {  // (3) 将第0行和第0列的填充到matrix中(第0行是从右向左)
            for (j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (colFlag) {  // (4) 判断第0列改变前是否需要全0
            for (i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

//    // 方法1: 使用两个集合(Set)记录需要置为0的行和列, 一个表示行, 一个表示列
//    public void setZeroes(int[][] matrix) {
//        Set<Integer> rowSet = new HashSet<>();
//        Set<Integer> colSet = new HashSet<>();
//
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                if (matrix[i][j] == 0) {
//                    rowSet.add(i);
//                    colSet.add(j);
//                }
//            }
//        }
//        Iterator<Integer> iterator1 = rowSet.iterator();
//        while (iterator1.hasNext()) {
//            Integer i = iterator1.next();
//            for (int j = 0; j < matrix[0].length; j++) {
//                matrix[i][j] = 0;
//            }
//        }
//        Iterator<Integer> iterator2 = colSet.iterator();
//        while (iterator2.hasNext()) {
//            Integer j = iterator2.next();
//            for (int i = 0; i < matrix.length; i++) {
//                matrix[i][j] = 0;
//            }
//        }
//    }

//    // 方法2:
//    // (1) 使用两个变量保存matrix的第0行和第0列的状态, 如果有一个是0, 标记位设置为true;
//    // (2) 将matrix[i][j] == 0(i和j都是从1开始), 就记录到matrix[0][j]和matrix[i][0]位置都改成0;
//    // (3) 查看matrix第0行中是0的位置, 该列全部改为0, matrix第0列中是0的位置, 该列全部改为0;
//    // (4) 判断(1)中的标记位, 是否需要将matrix的第0行和第0列全部改为0
//    public void setZeroes(int[][] matrix) {
//        int i = 0;
//        int j = 0;
//        boolean rowFlag = false;
//        boolean colFlag = false;
//        for (i = 0; i < matrix.length; i++) {  // (1) 判断第0列是否出现0
//            if (matrix[i][0] == 0) {
//                colFlag = true;
//                break;
//            }
//        }
//        for (j = 0; j < matrix[0].length; j++) {  // (1) 判断第0行是否出现0
//            if (matrix[0][j] == 0) {
//                rowFlag = true;
//                break;
//            }
//        }
//        for (i = 1; i < matrix.length; i++) {  // (2) 将matrix[i][j] == 0的行和列记录在0行0列的位置(i和j都是从1开始)
//            for (j = 1; j < matrix[0].length; j++) {
//                if (matrix[i][j] == 0) {
//                    matrix[i][0] = 0;
//                    matrix[0][j] = 0;
//                }
//            }
//        }
//        for (i = 1; i < matrix.length; i++) {  // (3) 第0行和第0列开始按行填充matrix
//            for (j = 1; j < matrix[0].length; j++) {
//                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
//                    matrix[i][j] = 0;
//                }
//            }
//        }
//        if (colFlag) {  // (4) 处理第0列(需要通过标记为来判断原本是否需要全0)
//            for (i = 0; i < matrix.length; i++) {
//                matrix[i][0] = 0;
//            }
//        }
//        if (rowFlag) {  // (4) 处理第0行(需要通过标记为来判断原本是否需要全0)
//            for (j = 0; j < matrix[0].length; j++) {
//                matrix[0][j] = 0;
//            }
//        }
//    }
}
