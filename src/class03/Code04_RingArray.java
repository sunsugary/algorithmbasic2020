package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code04_RingArray {

	public static class MyQueue {
		private int[] arr;
		private int pushi;// end
		private int polli;// begin
		private int size;
		private final int limit;

		public MyQueue(int limit) {
			arr = new int[limit];
			pushi = 0;
			polli = 0;
			size = 0;
			this.limit = limit;
		}

		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("队列满了，不能再加了");
			}
			size++;
			arr[pushi] = value;
			pushi = nextIndex(pushi);
		}

		public int pop() {
			if (size == 0) {
				throw new RuntimeException("队列空了，不能再拿了");
			}
			size--;
			int ans = arr[polli];
			polli = nextIndex(polli);
			return ans;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		// 如果现在的下标是i，返回下一个位置
		private int nextIndex(int i) {
			return i < limit - 1 ? i + 1 : 0;
		}

	}

	public static boolean isEqual(Integer o1, Integer o2) {

		if (o1 == null && o2 != null) {
			return false;
		}
		if (o1 != null && o2 == null) {
			return false;
		}

		if (o1 == null && o2 == null) {
			return true;
		}
		return o1.equals(o2);
	}

	public static void main(String[] args) {
		int testTimes = 10000;
		int maxValue = 1000;
		int oneTestDataNum = 1000;

		System.out.println("test start !!!");
		for (int i = 0; i < testTimes; i++) {
			Queue<Integer> queue = new LinkedList<>();
			MyQueue myQueue = new MyQueue(oneTestDataNum);

			for (int j = 0; j < oneTestDataNum; j++) {
				int numq = (int) (Math.random() * maxValue);
				if(queue.size() == 0){
					queue.add(numq);
					myQueue.push(numq);
				} else if (Math.random() < 0.5) {
					queue.add(numq);
					myQueue.push(numq);
				}else{
					if (!isEqual(queue.poll(), myQueue.pop())) {
						System.out.println("Oops2!!!");
					}
				}
			}

		}
		System.out.println("test end !!!");


	}

}
