package class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * @author knight
 * @desc
 * @date 2023/12/16
 */
public class Code02_CountFiles {

    /**
     * 宽度优先搜索(BFS); 时间复杂度: O(N)
     * 使用栈实现, 遇到文件返回1, 遇到文件夹将其加入 stack 中
     * @param folderPath
     * @return
     */
    public static int getFileCountByStack(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        int fileCount = 0;
        Stack<File> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            if (Objects.isNull(folder.listFiles())) {  // 空文件夹[windows需要加]
                continue;
            }
            for (File next : folder.listFiles()) {
                if (next.isFile()) {  // 文件
                    fileCount++;
                }
                if (next.isDirectory()) {  // 文件夹
                    stack.push(next);
                }
            }
        }
        return fileCount;
    }

    /**
     * 宽度优先搜索(BFS); 时间复杂度: O(N)
     * 使用队列实现, 遇到文件返回1, 遇到文件夹将其加入 queue 中
     * @param folderPath
     * @return
     */
    public static int getFileCountByQueue(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        int fileCount = 0;
        Queue<File> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            File folder = queue.poll();
            if (Objects.isNull(folder.listFiles())) {  // 空文件夹[windows需要加]
                continue;
            }
            for (File next : folder.listFiles()) {
                if (next.isFile()) {  // 文件
                    fileCount++;
                } else if (next.isDirectory()) {  // 文件夹
                    queue.offer(next);
                }
            }
        }
        return fileCount;
    }

    public static void main(String[] args) {
        String path = "D:\\";
        int fileCountByStack = getFileCountByStack(path);
        int fileCountByQueue = getFileCountByQueue(path);
        if (fileCountByQueue != fileCountByStack) {
            System.out.printf("fileCountByStack: [%d], fileCountByQueue: [%d] \nfail", fileCountByStack, fileCountByQueue);
        } else {
            System.out.printf("fileCountByStack: [%d], fileCountByQueue: [%d] \nsuccess", fileCountByStack, fileCountByQueue);
        }
    }
}
