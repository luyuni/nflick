import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MonitorApp {
    static class Solution {
        class Tuple3 {
            int p;
            String source;
            String target;
        }
        public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
            Tuple3[] sortedIndices = new Tuple3[indices.length];
            for (int i = 0 ; i < sortedIndices.length; i ++) {
                Tuple3 t = new Tuple3();
                t.p = indices[i];
                t.source = sources[i];
                t.target = targets[i];
                sortedIndices[i] = t;
            }
            Arrays.sort(sortedIndices, (o1, o2) -> {return o1.p - o2.p == 0 ? 1 : o1.p - o2.p; });

            for (int i = indices.length - 1; i >= 0; i --) {
                Tuple3 tuple3 = sortedIndices[i];
                int p = tuple3.p;
                String source = tuple3.source;
                boolean needRepalce = false;
                int matchNum = 0;
                for (int j = 0; j < source.length() && p + j < s.length(); j ++) {
                    if (source.charAt(j) != s.charAt(p + j)) {
                        break;
                    }
                    matchNum ++;
                }
                if (matchNum == source.length()) {
                    needRepalce = true;
                }
                if (needRepalce) {
                    String target = tuple3.target;
                    s = s.substring(0, p) + target + s.substring(p + source.length());
                }
            }
            return s;
        }
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findReplaceString("abcde", new int[]{2, 2}, new String[] {"cdef","bc"}, new String[] {"f","fe"}));
    }
}
