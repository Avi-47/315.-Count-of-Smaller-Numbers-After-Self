class Solution {
    int n;
    int[] ans;
    int[] index;
    public List<Integer> countSmaller(int[] nums) {
        this.n = nums.length;
        this.ans = new int[n];
        index = new int[n];
        for(int i=0;i<n;i++) index[i]=i;
        func(nums,0,n-1);
        List<Integer> li = new ArrayList<>();
        for(int num:ans) li.add(num);
        return li;
    }
    public void func(int[] nums, int l, int r){
        if(l>=r) return;
        int m = (l+r)/2;
        func(nums,l,m);
        func(nums,m+1,r);
        int[] temp = new int[r-l+1];
        int[] tempidx = new int[r-l+1];
        int i = l;
        int j = m+1;
        int k = 0;
        int rc = 0;
        while(i<=m && j<=r){
            if(nums[i]<=nums[j]){
                ans[index[i]] += rc;
                temp[k] = nums[i];
                tempidx[k++] = index[i++];
            }
            else{
                temp[k] = nums[j];
                tempidx[k++] = index[j++];
                rc++;
            }
        }
        while(i<=m){
            ans[index[i]] += rc;
            temp[k] = nums[i];
            tempidx[k++] = index[i++];
        }
        while(j<=r){
            temp[k] = nums[j];
            tempidx[k++] = index[j++];
        }
        for(int p=0;p<temp.length;p++){
            nums[p+l] = temp[p];
            index[p+l] = tempidx[p];
        }
    }
}
