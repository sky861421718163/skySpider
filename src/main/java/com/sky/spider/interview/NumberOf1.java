package com.sky.spider.interview;
public class NumberOf1 {
    /*
     * 输入一个整数，输出该数二进制表示中的１的个数
     * 结论：把一个整数减去１再和原整数与，就会把整数最右边一个１变成０
     */
    public int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }

        return count;
    }
    
    public static void main(String[] args) {
    	 String result = Integer.toBinaryString(4);

          System.out.println(result);
    	
    	
    	NumberOf1 t = new NumberOf1();
    	System.out.println(t.numberOf1(2));
    	
    	
    	
	}
} 