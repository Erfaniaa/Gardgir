package gardgir;

import java.util.ArrayList;

public class GardgirStronglyConnectedComponent {
	private int totalWeight, totalInDegree, totalOutDegree;
	private ArrayList<String> nodeNames;

	public GardgirStronglyConnectedComponent() {
		totalWeight = 0;
		nodeNames = new ArrayList<String>();
	}

	public int getTotalWeight() {
		return totalWeight;
	}

	public ArrayList<String> getNodeNames() {
		return nodeNames;
	}

	public void setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
	}

	public void addNodeName(String nodeName) {
		nodeNames.add(nodeName);
	}

	public void increaseOutDegree() {
		totalOutDegree++;
	}

	public void increaseInDegree() {
		totalInDegree++;
	}
}