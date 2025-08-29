# 🧮 LeetCode 315 - Count of Smaller Numbers After Self

## 📌 Problem Statement
Given an integer array `nums`, return an integer array `counts` where `counts[i]` is the number of smaller elements to the **right** of `nums[i]`.

### Example 1
Input: nums = [5,2,6,1]
Output: [2,1,1,0]

Explanation:

To the right of 5 → [2,6,1] → 2 elements smaller (2,1).

To the right of 2 → [6,1] → 1 element smaller (1).

To the right of 6 → [1] → 1 element smaller (1).

To the right of 1 → [] → 0 elements smaller.

### Example 2
Input: nums = [-1, -1]
Output: [0, 0]


---

## ⚙️ Constraints
- `1 <= nums.length <= 10^5`  
- `-10^4 <= nums[i] <= 10^4`

---

## 💡 Approach
There are multiple approaches to solve this problem:

### 🔹 1. Merge Sort (O(n log n))
- Use **modified merge sort** to count elements while sorting.  
- Maintain an index array to track original positions.  
- During merging:
  - If `nums[i] <= nums[j]`, add `rc` (right counter) to `ans[index[i]]`.  
  - Otherwise, increment `rc` because we found a smaller element on the right.  

✅ Efficient for large inputs (`n = 10^5`).  

---

### 🔹 2. Binary Search + Sorted List (O(n log n))
- Iterate from **right to left**.  
- Maintain a **sorted list** of processed numbers.  
- For each `nums[i]`, use binary search to find insertion index = number of smaller elements.  
- Insert element at correct position in sorted list.  

⚠️ Slower than merge sort due to `ArrayList` insertions but easier to implement.  

---

## 📝 Code

### ✅ Approach 1: Merge Sort (Efficient)
```java
class Solution {
    int n;
    int[] ans;
    int[] index;

    public List<Integer> countSmaller(int[] nums) {
        this.n = nums.length;
        this.ans = new int[n];
        index = new int[n];
        for (int i = 0; i < n; i++) index[i] = i;
        func(nums, 0, n - 1);
        List<Integer> li = new ArrayList<>();
        for (int num : ans) li.add(num);
        return li;
    }

    public void func(int[] nums, int l, int r) {
        if (l >= r) return;
        int m = (l + r) / 2;
        func(nums, l, m);
        func(nums, m + 1, r);

        int[] temp = new int[r - l + 1];
        int[] tempidx = new int[r - l + 1];
        int i = l, j = m + 1, k = 0, rc = 0;

        while (i <= m && j <= r) {
            if (nums[i] <= nums[j]) {
                ans[index[i]] += rc;
                temp[k] = nums[i];
                tempidx[k++] = index[i++];
            } else {
                temp[k] = nums[j];
                tempidx[k++] = index[j++];
                rc++;
            }
        }
        while (i <= m) {
            ans[index[i]] += rc;
            temp[k] = nums[i];
            tempidx[k++] = index[i++];
        }
        while (j <= r) {
            temp[k] = nums[j];
            tempidx[k++] = index[j++];
        }
        for (int p = 0; p < temp.length; p++) {
            nums[p + l] = temp[p];
            index[p + l] = tempidx[p];
        }
    }
}
```
✅ Approach 2: Binary Search + List (Simpler but slower)
```
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> li = new ArrayList<>();
        List<Integer> sortli = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            int idx = func(sortli, nums[i]);
            li.add(idx);
            sortli.add(idx, nums[i]);
        }
        Collections.reverse(li);
        return li;
    }

    public int func(List<Integer> li, int a) {
        int l = 0, r = li.size() - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (li.get(m) >= a) r = m - 1;
            else l = m + 1;
        }
        return l;
    }
}
```
⏱️ Complexity

Merge Sort:

Time: O(n log n)

Space: O(n)

Binary Search + List:

Time: O(n log n) for searching + O(n^2) worst-case for insertions

Space: O(n)
