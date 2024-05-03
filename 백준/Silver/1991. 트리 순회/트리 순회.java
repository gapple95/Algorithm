import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int N;

	static class Node {
		char node;
		Node left, right;

		public Node(char node) {
			super();
			this.node = node;
			left = null;
			right = null;
		}

		@Override
		public String toString() {
			return "Node [node=" + node + ", left=" + left + ", right=" + right + "]";
		}

	}
	
	static StringBuilder sb;
	static Node[] nodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();

		
		N = Integer.parseInt(br.readLine());

		nodes = new Node[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char node = st.nextToken().charAt(0);
			char node_left = st.nextToken().charAt(0);
			char node_right = st.nextToken().charAt(0);
			
			if(nodes[node - 'A'] == null)
				nodes[node - 'A'] = new Node(node);
			
			if(node_left != '.' && nodes[node_left - 'A'] == null) {
				nodes[node_left - 'A'] = new Node(node_left);
				nodes[node - 'A'].left = nodes[node_left - 'A'];
			}
			if(node_right != '.' && nodes[node_right - 'A'] == null) {
				nodes[node_right - 'A'] = new Node(node_right);
				nodes[node - 'A'].right = nodes[node_right - 'A'];
			}
		}
		preOrder(nodes[0]);
		sb.append("\n");
		inOrder(nodes[0]);
		sb.append("\n");
		postOrder(nodes[0]);
		
		System.out.println(sb);
	}

	// 전위 순회
	public static void preOrder(Node root) {
		if(root == null) 
			return;
		sb.append(root.node);
		preOrder(root.left);
		preOrder(root.right);
	}

	// 중위 순회
	public static void inOrder(Node root) {
		if(root == null) 
			return;
		inOrder(root.left);
		sb.append(root.node);
		inOrder(root.right);
	}

	// 후위 순회
	public static void postOrder(Node root) {
		if(root == null) 
			return;
		postOrder(root.left);
		postOrder(root.right);
		sb.append(root.node);
	}
}