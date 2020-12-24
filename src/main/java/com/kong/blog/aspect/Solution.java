package com.kong.blog.aspect;

import java.util.Arrays;
import java.util.TreeMap;

public class Solution {
    public int numSplits(String s) {
       int[] arr=new int[26];
       int sizeR=0;
       int sizeL=0;
       int res=0;
       for(char ch:s.toCharArray()){
           if (arr[ch-'a']==0) {
               sizeR++;
           }
           arr[ch-'a']++;
       }
       int[] arr2=new int[26];
       for(char ch:s.toCharArray()){
           arr2[ch-'a']++;
           if (arr2[ch-'a']==1){
               sizeL++;
           }
           if (arr[ch-'a']>1){
               arr[ch-'a']--;
           }
           else {
               sizeR--;
           }
           if (sizeL>sizeR) break;
           if (sizeL==sizeR) res++;
       }
        return res;
    }
}
