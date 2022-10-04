package toplikedquestions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 单词接龙
// https://leetcode.cn/problems/word-ladder/
public class Problem_0127_WordLadder {

    // 思路: 从beginWord开始变化, 每次变化一个字母, 然后在 wordList 中找是否存在, 如果存在就表示变化后的单词是beginWord的一个子分支
    // 从子分支开始继续每次变化一个字母开始找, 注意: 需要保存一个 visitedSet(集合) 去重, 否则永远都找不完。
    // 比如: abc——>(bbc、cbc、dbc、... zbc); bbc——>(bac、bcc、bdc、... bzc);...;zbc——>(zba、zbb、zbd、... zbz)
    //      然后需要每次用当前单词去wordList里面找一下, 是否存在, 不存在表示该分支下的结果都不用找了(走不到)
    //      最后的结果就是找到了经过变化的单词和 endWord 一样, 表示找到了, 当前单词从beginWord 变化到 endWord 的层数就是最小值
    // 所以, 上面这种思路每次去wordList中查找的时间复杂度O(N * k), N是wordList的长度, k是单词的长度
    // 优化1: 加入将 wordList 提前放入到Set中, 每次去查找Set, 时间复杂度O(K), 因为Set底层在求hashCode的时候也是将单词的每个字符遍历
    // 优化2: 双向查找, 从startWord 变成 endWord, 每个单词都会生成子分支, 每次从分支最小的方向向下找(可以最小化的遍历), 直到相遇返回结果
    //       优化2的时间复杂度并没有降低, 但是可以想到平均时间复杂度肯定时降低了很多
    // 代码实现:
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);  // wordList的字典 Set
        Set<String> startSet = new HashSet<>();  // startWord的分支Set
        Set<String> endSet = new HashSet<>();   // endWord的分支Set
        Set<String> visitedSet = new HashSet<>();  // 全局性的, 将startWord和 endWord 每次的生成的合法新单词都记录在这个Set中(防止重复分支循环)
        startSet.add(beginWord);
        if (!dict.contains(endWord)) {  // 字典中都没有endWord, 怎么变化都没有结果
            return 0;
        }
        endSet.add(endWord);

        for (int len = 2; !startSet.isEmpty(); len++) {  // 从2开始, 因为已经加过beginWord和endWord了
            Set<String> nextSet = new HashSet<>();  // 当前word的子分支集合
            for (String word : startSet) {
                for (int i = 0; i < word.length(); i++) {  // 单词每个字母的遍历(单词长度是k)
                    char[] w = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        w[i] = c;
                        String next = String.valueOf(w);
                        if (endSet.contains(next)) {  // 看看 startWord和endWord的变化有没有相遇, 如果相遇就可以返回结果了
                            return len;
                        }
                        if (dict.contains(next) && !visitedSet.contains(next)) {  // 表示存在字典中, 且还没有访问过(去重)
                            nextSet.add(next);
                            visitedSet.add(next);
                        }
                    }
                }
            }
            // 优化2: startSet和endSet(层数)哪个小下次就从哪个开始
            startSet = nextSet.size() < endSet.size() ? nextSet : endSet;
            endSet = startSet == nextSet ? endSet : nextSet;
            System.out.println();
        }
        return 0;
    }
}
