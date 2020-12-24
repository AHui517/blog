package com.kong.blog.aspect;

public class dpDemo {
    public boolean isSum(int[] arr, int i, int s) {
        if (s == 0) return true;
        if (i == 0) return arr[0] == s;
        if (s < 0) isSum(arr, i - 1, s);
        return isSum(arr, i - 1, s - arr[i]) || isSum(arr, i - 1, s);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 8, 5, 9};
        System.out.println(new dpDemo().isSum(arr, arr.length - 1, 41);
    }
}
