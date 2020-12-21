package gardgir;

import java.util.ArrayList;
import java.util.HashMap;

public class GardgirDirectedGraph {
	public static final int NOT_FOUND_INT = -1;
	public static final String NOT_FOUND_STRING = "";
	public static final int NOT_MARKED = -1;
	private int nodesCount;
	private int edgesCount;
	private ArrayList<String> nodeNames;
	private ArrayList<Integer> nodeWeights;
	private HashMap<String, Integer> stringToInteger;
	private ArrayList<ArrayList<Integer> > adjacencyList;
	private ArrayList<ArrayList<Integer> > reverseAdjacencyList;
	private ArrayList<Integer> dfsMark;
	private ArrayList<Integer> dfsList;
	private ArrayList<GardgirStronglyConnectedComponent> stronglyConnectedComponents;

	public GardgirDirectedGraph() {
		nodesCount = 0;
		edgesCount = 0;
		stringToInteger = new HashMap<String, Integer>();
		nodeNames = new ArrayList<String>();
		nodeWeights = new ArrayList<Integer>();
		adjacencyList = new ArrayList<ArrayList<Integer> >();
		reverseAdjacencyList = new ArrayList<ArrayList<Integer> >();
		dfsMark = new ArrayList<Integer>();
		dfsList = new ArrayList<Integer>();
		stronglyConnectedComponents = new ArrayList<GardgirStronglyConnectedComponent>();
	}

	public String getNodeName(int nodeIndex) {
		if (nodeIndex < 0 || nodeIndex >= nodesCount)
			return NOT_FOUND_STRING;
		else
			return nodeNames.get(nodeIndex);
	}

	public int getNodeIndex(String nodeName) {
		if (stringToInteger.containsKey(nodeName))
			return stringToInteger.get(nodeName);
		else
			return NOT_FOUND_INT;
	}

	public int addAndGetNodeIndex(String nodeName) {
		return addAndGetNodeIndex(nodeName, 0);
	}

	public int addAndGetNodeIndex(String nodeName, int nodeWeight) {
		if (stringToInteger.containsKey(nodeName))
			return stringToInteger.get(nodeName).intValue();
		else {
			nodesCount++;
			dfsMark.add(NOT_MARKED);
			nodeNames.add(nodeName);
			nodeWeights.add(Integer.valueOf(nodeWeight));
			adjacencyList.add(new ArrayList<Integer>());
			reverseAdjacencyList.add(new ArrayList<Integer>());
			stringToInteger.put(nodeName, Integer.valueOf(nodesCount - 1));
			return nodesCount - 1;
		}
	}

	public void setNodeWeight(int nodeIndex, int nodeWeight) {
		if (nodeIndex < 0 || nodeIndex >= nodesCount)
			return;
		else
			nodeWeights.set(nodeIndex, Integer.valueOf(nodeWeight));
	}

	public int getNodeWeight(int nodeIndex) {
		if (nodeIndex < 0 || nodeIndex >= nodesCount)
			return NOT_FOUND_INT;
		else
			return nodeWeights.get(nodeIndex).intValue();
	}

	public void addEdge(String node1Name, String node2Name) {
		addEdge(addAndGetNodeIndex(node1Name), addAndGetNodeIndex(node2Name));
	}

	public void addEdge(int node1Index, int node2Index) {
		if (node1Index < 0 || node1Index >= nodesCount)
			return;
		if (node2Index < 0 || node2Index >= nodesCount)
			return;
		edgesCount++;
		adjacencyList.get(node1Index).add(Integer.valueOf(node2Index));
		reverseAdjacencyList.get(node2Index).add(Integer.valueOf(node1Index));
	}

	public int getNodesCount() {
		return nodesCount;
	}
	
	public int getEdgesCount() {
		return edgesCount;
	}

	public ArrayList<ArrayList<Integer> > findStronglyConnectedComponents() {
		dfsList.clear();
		for (int i = 0; i < nodesCount; i++)
			dfsMark.set(i, NOT_MARKED);
		for (int i = 0; i < nodesCount; i++)
			if (dfsMark.get(i) == NOT_MARKED)
				dfs1(i);
		for (int i = 0; i < nodesCount; i++)
			dfsMark.set(i, false);
		ArrayList<ArrayList<Integer> > stronglyConnectedComponents = new ArrayList<ArrayList<Integer> >();
		for (int i = nodesCount - 1; i >= 0; i--) {
			if (dfsMark.get(dfsList.get(i).intValue()) == false) {
				stronglyConnectedComponents.add(new ArrayList<Integer>());
				dfs2(dfsList.get(i).intValue(), stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1));
			}
			System.out.println("______");
		}
		return stronglyConnectedComponents;
	}

	private void dfs1(int nodeIndex) {
		dfsMark.set(nodeIndex, true);
		for (Integer neighborNodeIndex: adjacencyList.get(nodeIndex)) {
			if (dfsMark.get(neighborNodeIndex.intValue()) == false)
				dfs1(neighborNodeIndex);
		}
		dfsList.add(nodeIndex);
	}

	private void dfs2(int nodeIndex, ArrayList<Integer> stronglyConnectedComponent) {
		dfsMark.set(nodeIndex, true);
		stronglyConnectedComponent.add(Integer.valueOf(nodeIndex));
		System.out.println(nodeIndex);
		for (Integer neighborNodeIndex: reverseAdjacencyList.get(nodeIndex)) {
			if (dfsMark.get(neighborNodeIndex.intValue()) == false)
				dfs2(neighborNodeIndex, stronglyConnectedComponent);
		}
	}

	public ArrayList<GardgirStronglyConnectedComponent> getSinkStronglyConnectedComponents() {
		return null;
	}
}