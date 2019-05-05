package com.sky.spider.advance;
import java.util.concurrent.ForkJoinPool;  
import java.util.concurrent.Future;  
import java.util.concurrent.RecursiveTask;  
  
  
/**
 * ForkJoin 框架
 *@ClassName:SumTask.java
 *@ClassDescribe: 
 *@createPerson:SKY
 *@createDate:2018年6月8日 下午4:20:17
 *@version
 */
public class SumTask extends RecursiveTask<Long>{  
  
    private Long start;  
  
    private Long end;  
  
    private static Long threshHold = 10000000L;  //一千万
  
    public SumTask(Long start, Long end) {  
        this.start = start;  
        this.end = end;  
    }  
  
    @Override  
    protected Long compute() {  
        Long sum = 0L;  
        if (end - start <= threshHold) {  //差值小于1千万时
            for (long i = start; i <= end; i++) {  
                sum += i;  
            }  
        } else {  
            long middle = (end + start)/2;  
            SumTask taskLeft = new SumTask(start, middle);  
            SumTask taskRight = new SumTask(middle + 1, end);  
            taskLeft.fork();  
            taskRight.fork();  
  
            Long leftResult = taskLeft.join();  
            Long rightResult = taskRight.join();  
            sum = leftResult + rightResult;  
        }  
        return sum;  
    }  
  
    public static void main(String[] args) throws Exception{  
        Long num = 100000000L;  //1亿
        long start = System.currentTimeMillis();  
        ForkJoinPool forkJoinPool = new ForkJoinPool();  
        SumTask task = new SumTask(1L, num);  
        Future<Long> future = forkJoinPool.submit(task);  
        System.out.println("result = "+future.get());  
        long end = System.currentTimeMillis();  
        System.out.println("fork join 耗时="+(end - start));  
  
        //------- 直接累加
        start = System.currentTimeMillis();  
        long sum = 0L;  
        for(long i=1;i<=num;i++){  
            sum += i;  
        }  
        end = System.currentTimeMillis();  
        System.out.println("result = "+sum +", for 循环耗时="+(end - start));  
    }  
}  