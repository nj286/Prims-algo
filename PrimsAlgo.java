package Graph;

import java.util.*;

public class PrimsAlgo {
	public static HashMap<Integer, Character> map = new HashMap<>();

	public int findMinWeightVertex(int[] weight, boolean[] part_of_tree) {
		int min = Integer.MAX_VALUE;
		int min_index = -1;
		for (int i = 0; i < weight.length; i++) {
			if (!part_of_tree[i] && weight[i] < min) {
				min = weight[i];
				min_index = i;
			}
		}

		return min_index;

	}

	void printMST(int parent[], int graph[][]) {
		System.out.println("Edge \tWeight");
		for (int i = 1; i < parent.length; i++)
			System.out.println(map.get(parent[i]) + " - " + map.get(i) + "\t" + graph[i][parent[i]]);
	}

	public void primMST(int[][] graph) {
		int vertices = graph.length;
		int[] weight = new int[vertices];
		int[] parent = new int[vertices];
		boolean[] part_of_tree = new boolean[vertices];

		for (int i = 0; i < vertices; i++) {
			weight[i] = Integer.MAX_VALUE;
		}

		weight[0] = 0;
		parent[0] = -1;
		for (int i = 0; i < vertices - 1; i++) {
			int u = findMinWeightVertex(weight, part_of_tree);
			part_of_tree[u] = true;

			for (int v = 0; v < vertices; v++) {
				if (!part_of_tree[v] && graph[u][v] != 0 && graph[u][v] < weight[v]) {
					weight[v] = graph[u][v];
					parent[v] = u;
				}
			}

		}
		printMST(parent, graph);
	}

	public static void main(String[] args) {
		char[] vertices = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 's' };

		for (int i = 0; i < 9; i++) {
			map.put(i, vertices[i]);
		}

		PrimsAlgo t = new PrimsAlgo();
		int graph[][] = new int[][] { { 0, 0, 1, 6, 0, 0, 0, 0, 1 }, { 0, 0, 3, 5, 0, 0, 0, 0, 2 },
				{ 1, 3, 0, 0, 3, 2, 0, 0, 0 }, { 6, 5, 0, 0, 1, 0, 2, 0, 0 }, { 0, 0, 3, 1, 0, 8, 6, 0, 0 },
				{ 0, 0, 2, 0, 8, 0, 0, 9, 0 }, { 0, 0, 0, 2, 6, 0, 0, 3, 0 }, { 0, 0, 0, 0, 0, 9, 3, 0, 0 },
				{ 1, 2, 0, 0, 0, 0, 0, 0, 0 } };

		// Print the solution
		t.primMST(graph);
	}

}
