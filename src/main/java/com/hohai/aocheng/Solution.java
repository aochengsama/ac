package com.hohai.aocheng;

public class Solution {
    public static void main(String[] args) {
        System.out.println(findLongest("sjdaiwasdesdsfsdsedsawlh3u238dsahji1")); // false

    }

    //自定义的工具类
    private static int[] findP(String input, int left, int right) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            left--;
            right++;
        }
        return new int[]{left + 1, right};
    }
    //题目里定义的那个类
    public static String findLongest(String input) {
        if (input == null || input.length() <= 1) {
            return "-1";
        }

        int maxLength = 0;
        int start = 0;

        for (int i = 0; i < input.length(); i++) {
            int[] length1 = findP(input, i, i);
            int[] length2 = findP(input, i, i + 1);
            int maxL = Math.max(length1[1] - length1[0], length2[1] - length2[0]);

            if (maxL > maxLength) {
                maxLength = maxL;
                start = length1[1] - length1[0] > length2[1] - length2[0] ? length1[0] : length2[0];
            }
        }

        if (maxLength > 1) {
            return input.substring(start, start + maxLength);
        } else {
            return "-1";
        }
    }


}

