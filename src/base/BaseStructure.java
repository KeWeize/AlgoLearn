package base;

public class BaseStructure {

    public static void main() {

    }


    private static class Queue{
        private int[] data = new int[1000];
        private int head = 1;
        private int tail = 1;

        public void insert(int num) {
            data[++tail] = num;
        }

    }

}
