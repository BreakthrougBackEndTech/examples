package scanner;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author: zhegong
 **/
public class ScannerDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()){
            String oneLine = scan.nextLine();


            if(oneLine.equals("0")){
                break;
            }


          Stream.of(oneLine.split(" ")).map(Integer::valueOf).reduce( (a,b)->a+b).ifPresent(System.out::println);



        }

//        System.out.println(scan.next());
//        System.out.println(scan.nextLine());
    }

    /**
     * 输入描述第一行是一个正整数n，表示二维数组有n行n列。
     */
    private static void scanerInt() {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();//定义需要的阶层数n
        int[][]array=new int[n][n];//定义一个n*n的数组array
        System.out.println("输入数组的各个元素：");
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++)
                array[i][j] = scan.nextInt();
        }
    }


    /**
     * 输入描述：第一行是一个正整数T，表示有T组测试数据。
     之后每组数据有三行，第一行为n（1<=n<=10000），第二行有n个正整数，第三行也有n个正整数；都在整数范围内。
     输入：
     3
     3
     1 2 3
     1 2 3
     4
     4 3 2 1
     1 1 1 1
     2
     1 2
     10 20
     */
    public static void scannerMultiInt(){
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T>0){
            T--;//为了保证输出的第一行会换行
            //a和b分别放每组测试用例的第二行和第三行
            int[] a = new int[10000];
            int[] b = new int[10000];
            int n = in.nextInt();
            for(int i=1; i<=n; i++){
                a[i] = in.nextInt();
            }
            for(int i=1 ;i<=n; i++){
                b[i] = in.nextInt();
            }
        }//将输入带到function()方法里去运算
//        System.out.println(function(a,b))
    }


}
