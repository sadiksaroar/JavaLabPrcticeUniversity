import java.util.Scanner;

public class Knapsack12 {

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int knapSack(int W, int wt[], int val[], int n) {
        int i, w;
        int P[][] = new int[n + 1][W + 1];

        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    P[i][w] = 0;
                } else if (wt[i - 1] <= w) {
                    P[i][w] = max(val[i - 1] + P[i - 1][w - wt[i - 1]], P[i - 1][w]);
                } else {
                    P[i][w] = P[i - 1][w];
                }
            }
        }

        return P[n][W];
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter No. of Items");
        int n = sc.nextInt();
        System.out.println("Enter size of Knapsack");
        int W = sc.nextInt();
        int val[] = new int[n];
        int wt[] = new int[n];
        System.out.println("Enter the values of items");
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }
        System.out.println("Enter the weights of items");
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }
        System.out.println("Maximum total profit = " + knapSack(W, wt, val, n));
    }
}
