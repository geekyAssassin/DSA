public class HeapSort {
    public void sort(int[] arr) {
        int n = arr.length;
        for(int i = n/2-1; i>=0;i--)
            heapifyArr(arr,n,i);
        for(int i = n-1; i>=0;i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapifyArr(arr, i, 0);
        }
    }
    public void heapifyArr(int[] arr, int n, int i) {
        int largest = i;
        int leftChild = 2*i +1;
        int rightChild = 2*i + 2;
        if(leftChild < n && arr[leftChild] > arr[largest])
            largest = leftChild;
        if(rightChild < n && arr[rightChild]> arr[largest])
            largest = rightChild;

        if(largest!=i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapifyArr( arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int arr[] = { 12, 11, 13, 5, 6, 7 };
        HeapSort hs = new HeapSort();
        hs.sort(arr);

        System.out.println("Sorted array");
        for (int i : arr) {
           System.out.print(i + " "); 
        }
    }
}
