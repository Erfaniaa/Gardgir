package gardgir;

import java.util.ArrayList;
import java.util.HashMap;

public class GardgirDirectedGraph {
	public static final int NOT_FOUND_INT = -1;
	public static final String NOT_FOUND_STRING = "";
	private int nodesCount;
	private int edgesCount;
	private ArrayList<String> nodeNames;
	private ArrayList<Integer> nodeWeights;
	private HashMap<String, Integer> stringToInteger;
	private ArrayList<ArrayList<Integer> > adjacencyList;

	public GardgirDirectedGraph() {
		nodesCount = 0;
		edgesCount = 0;
		stringToInteger = new HashMap<String, Integer>();
		nodeNames = new ArrayList<String>();
		nodeWeights = new ArrayList<Integer>();
		adjacencyList = new ArrayList<ArrayList<Integer> >();
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
			nodeNames.add(nodeName);
			nodeWeights.add(Integer.valueOf(nodeWeight));
			adjacencyList.add(new ArrayList<Integer>());
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
	}

	public int getNodesCount() {
		return nodesCount;
	}
	
	public int getEdgesCount() {
		return edgesCount;
	}
}