package cn.rock.shirodemo;

public class QuickSortDemo {

    public void sort(int[] arr, int low, int hight) {
        if(low < hight){
            int part = partition(arr, low, hight);
            sort(arr, 0, part-1);
            sort(arr, part+1, hight);
        }

    }

    private int partition(int[] arr, int low, int higth) {
        int p = arr[low];
        while (low < higth) {
            while (low < higth && arr[higth] >= p) {
                higth--;
            }
            if (low < higth){
                arr[low] = arr[higth];
                low++;
            }
            while (low < higth && arr[low] <= p) {
                low++;
            }
            if (low < higth){
                arr[higth] = arr[low];
                higth--;
            }
            arr[low] = p;
        }
        return low;
    }

    private void swap(int[] arr, int low, int higth) {
        int i = arr[low];
        arr[low] = arr[higth];
        arr[higth]= i;
    }
    public static void main(String[] args) {
        QuickSortDemo sortDemo = new QuickSortDemo();
        int [] arr = {15,63,44,8,14,55,42};
        sortDemo.sort(arr,0,arr.length-1);
        System.out.println(arr);

    }
}
