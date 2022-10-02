# 数据结构与算法基础
日常练习，刷着娱乐

algorithmbasic class02 code03_km有问题

0005 回文串
0010 是正则表达式匹配
0028 是KMP算法
0070 斐波那契额数列


0061 旋转链表
0077 组合
0093 复原IP地址

'股票问题大集合leetcode 121、122、123、188、309、714'

### Morris遍历
>Morris遍历细节
> (1) 如果cur没有左孩子, cur向右移动(cur = cur.right)
> 
> (2) 如果cur有左孩子, 找到左子树上最右的节点mostRight:
> 
>     a: 如果mostRight的右指针指向空, 让其指想cur, 然后cur向左移动(cur = cur.left)
>    
>     b: 如果mostRight的右指针指向cur, 让其指向null, 然后cur向右移动(cur = cur.right)
>
>(3) cur为空停止遍历
```java
    // Morris遍历细节
    // (1) 如果cur没有左孩子, cur向右移动(cur = cur.right)
    // (2) 如果cur有左孩子, 找到左子树上最右的节点mostRight:
    //     a: 如果mostRight的右指针指向空, 让其指想cur, 然后cur向左移动(cur = cur.left)
    //     b: 如果mostRight的右指针指向cur, 让其指向null, 然后cur向右移动(cur = cur.right)
    // (3) cur为空停止遍历

    // 方法3: Morris遍历(时间复杂度O(N), 空间复杂度O(1))—>空间准确来说应该是O(2N)
    public void morris(TreeNode root) {
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {  // (3)
            mostRight = cur.left;
            if (mostRight != null) {  // (2)
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {  // (2) a
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {  // (2) b
                    mostRight.right = null;
                }
            }
            cur = cur.right;  // (1)和(2) b
        }
    }
```

