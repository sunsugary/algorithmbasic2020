package class08;

public class Code01_Trie {

	// 测试链接 : https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
	// 提交Trie类可以直接通过
	// 原来代码是对的，但是既然找到了直接测试的链接，那就直接测吧
	// 这个链接上要求实现的功能和课上讲的完全一样
	// 该前缀树的路用数组实现
	static class Trie {

		static class Node {
			public int pass;
			public int end;
			public Node[] nexts;

			public Node() {
				pass = 0;
				end = 0;
				nexts = new Node[26];
			}
		}

		private Node root;

		public Trie() {
			root = new Node();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] str = word.toCharArray();
			Node node = root;
			node.pass++;
			int path = 0;
			for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
				path = str[i] - 'a'; // 由字符，对应成走向哪条路
				if (node.nexts[path] == null) {
					node.nexts[path] = new Node();
				}
				node = node.nexts[path];
				node.pass++;
			}
			node.end++;
		}

		public void erase(String word) {
			if (countWordsEqualTo(word) != 0) {
				char[] chs = word.toCharArray();
				Node node = root;
				node.pass--;
				int path = 0;
				for (int i = 0; i < chs.length; i++) {
					path = chs[i] - 'a';
					if (--node.nexts[path].pass == 0) {
						node.nexts[path] = null;
						return;
					}
					node = node.nexts[path];
				}
				node.end--;
			}
		}

		public int countWordsEqualTo(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}

		public int countWordsStartingWith(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("apple");               // 插入 "apple"。
		trie.insert("apple");               // 插入另一个 "apple"。
		System.out.println(trie.countWordsEqualTo("apple"));    // 有两个 "apple" 实例，所以返回 2。
		System.out.println(trie.countWordsStartingWith("app")); // "app" 是 "apple" 的前缀，所以返回 2。
		trie.erase("apple");                // 移除一个 "apple"。
		System.out.println(trie.countWordsEqualTo("apple"));    // 现在只有一个 "apple" 实例，所以返回 1。
		System.out.println(trie.countWordsStartingWith("app")); // 返回 1
		trie.erase("apple");                // 移除 "apple"。现在前缀树是空的。
		System.out.println(trie.countWordsStartingWith("app")); // 返回 0
	}

}
