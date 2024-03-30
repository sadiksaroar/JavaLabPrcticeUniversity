public class DFS {
    static char[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'S'};
    static int e[] = {2, 2, 2, 2, 2, 2, 3, 3};
    static int list[][] = {{3, 7}, {4, 7}, {5, 7}, {0, 6}, {1, 6}, {2, 6}, {3, 4, 5}, {0, 1, 2}}; // adjacency list of graph figure 1.
    static int[] checked = new int[20];
    static int[] stk = new int[20];
    static int top = 0;

    public static void main(String[] args) {
        int i, n, f = 0; // f is a flag that tells when to pop
        push(7); // inserting the first node from where the traversal starts.
        while (top != 0) { // loop will execute till the stack is not empty.
            n = stk[top - 1];
            for (i = 0; i < e[n]; i++) {
                f = 0;
                if (notChecked(list[n][i]) == 1) {
                    push(list[n][i]);
                    f = 1;
                    break;
                }
            }
            if (f == 0) {
                pop();
            }
        }
    }

    static int notChecked(int n) { // this method checks if the node is visited or not
        if (checked[n] == 1) {
            return 0;
        }
        return 1;
    }

    static int pop() { // this method is used to pop a node from the stack
        // System.out.print(c[stk[top - 1]] + " "); // print popping sequence
        top--;
        return stk[top];
    }

    static void push(int n) { // this method is used to push a node onto the stack
        checked[n] = 1;
        System.out.print(c[n] + " ");
        stk[top] = n;
        top++;
    }
}
