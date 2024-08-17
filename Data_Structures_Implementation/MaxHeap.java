public class MaxHeap {
    
    int[] heapArr;
    int maxSize;
    int heapSize; // No of elements currently in the heap

    MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        heapArr = new int[maxSize];
        heapSize = 0;
    }

    int getLeftChild(int rootPosition) {
        return (2*rootPosition)+1;
    }

    int getRightChild(int rootPosition) {
        return (2*rootPosition)+2;
    }

    int getParent(int childPosition) {
        return (childPosition-1)/2;
    }

    // Heapify the sub-tree based on the given index
    void heapify(int heapifiedIndex) {
        int largestNumberIndex = heapifiedIndex;
        int leftChildIndex = getLeftChild(heapifiedIndex);
        int rightChildIndex = getRightChild(heapifiedIndex);

        if(leftChildIndex < heapSize && heapArr[leftChildIndex] > heapArr[largestNumberIndex])
            largestNumberIndex = leftChildIndex;
        if(rightChildIndex < heapSize && heapArr[rightChildIndex] > heapArr[largestNumberIndex])
            largestNumberIndex = rightChildIndex;

        if(largestNumberIndex != heapifiedIndex) {
            int temp = heapArr[heapifiedIndex];
            heapArr[heapifiedIndex] = heapArr[largestNumberIndex];
            heapArr[largestNumberIndex] = temp;

            heapify(largestNumberIndex);
        }
        
    }

    int removeMaxElement() {
        if(heapSize == 0)
            return Integer.MIN_VALUE;
        if(heapSize == 1) {
            heapSize--;
            return heapArr[0];
        }

        int root = heapArr[0];
        heapArr[0] = heapArr[heapSize-1];
        heapSize--;

        heapify(0);
        return root;
    }

    void insert(int value) {
        if(heapSize == maxSize) {
            System.out.println("Overflow");
            return;
        }

        heapSize++;
        int i = heapSize -1;
        heapArr[i] = value;

        while(i!=0 && heapArr[getParent(i)] < heapArr[i]) {
            int temp = heapArr[i];
            heapArr[i] = heapArr[getParent(i)];
            heapArr[getParent(i)] = temp;

            i = getParent(i);
        }
    }
}
 