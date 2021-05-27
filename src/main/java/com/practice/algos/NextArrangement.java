package com.practice.algos;

public class NextArrangement {
    public static void main(String[] args) {
    }

    public void swap(int[] args, int pos1, int pos2) {
        int temp = args[pos1];
        args[pos1] = args[pos2];
        args[pos2] = temp;
    }

    public void reverseFromPos(int[] args, int left) {
        int right = args.length;
        while(left != right) {
            swap(args, left++, right--);
        }
    }

    public void nextPermutation(int[] args) {
        int pos = 0;
        for(int i = args.length - 2; i > 0; i--) {
            if(args[i + 1] > args[i]) {
                pos = i;
                for(int j = args.length - 1; j > i; j--) {
                    if(args[j] > args[i] && args[j - 1] < args[i]) {
                        swap(args, i, j - 1);
                    }
                }
                break;
            }
        }
        reverseFromPos(args, pos);
     }
}
