package org.usvm.spring.benchmarks.service;

import org.springframework.stereotype.Service;
import org.usvm.spring.benchmarks.model.GraphNode;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoolService {
	public int version;
	public GraphNode node;

	public GraphNode getGraphNode(int input) {
		return new GraphNode();
	}

	public int getIntValue(int input) {
		return 32;
	}

	public List<String> returnList(List<String> lst) {
		return lst;
	}

	public List<String> returnArrayList(ArrayList<String> lst) {
		return lst;
	}

	public List<String> returnWallet(ArrayList<String> lst) {
		return lst;
	}
}
