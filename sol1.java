class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> li = new ArrayList<>();
        List<Integer> sortli = new ArrayList<>();
        for(int i=n-1;i>=0;i--){
            int idx = func(sortli,nums[i]);
            li.add(idx);
            sortli.add(idx,nums[i]);
        }
        Collections.reverse(li);
        return li;
    }
    public int func(List<Integer> li, int a){
        int l = 0;
        int r = li.size()-1;
        while(l<=r){
            int m = (l+r)/2;
            if(li.get(m)>=a) r=m-1;
            else l=m+1;
        }
        return l;
    }
}
