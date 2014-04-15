package com.sky.codingame.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TeadsQ1 {
	ResultDepthComparator resultDepthComparator = new ResultDepthComparator();
	ResultDistanceComparator resultDistanceComparator = new ResultDistanceComparator();
	
	public static void main(String args[]) {
		System.out.println("result = "+new TeadsQ1().start());
	}
	
	public int start(){
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		HashMap<Integer, Node> totalNodes = new HashMap<Integer, Node>();	
		int forroot = 0;
		
		for (int i = 0; i < n; i++) {
			String[] tempStrings = in.nextLine().split(" ");
			int a = Integer.parseInt(tempStrings[0]);
			int b = Integer.parseInt(tempStrings[1]);
			forroot = b;
			Node na, nb;
			if(totalNodes.containsKey(a)){
				na = totalNodes.get(a);
			}else {
				na = new Node(null);
				totalNodes.put(a, na);
			}
			if(totalNodes.containsKey(b)){
				nb = totalNodes.get(b);
			}else {
				nb = new Node(null);
				totalNodes.put(b, nb);
			}
			na.union(nb);
		}
		
		Node root = totalNodes.get(forroot).getRoot();
		Result r = getMaxDistance(root);
		return (r.maxDistance+1)/2;
	}
	
	public Result getMaxDistance(Node node){
		if(node.childre.size()==0){
			return new Result(0, 0);
		}
		List<Result> allResults = new ArrayList<Result>();
		for(Node n : node.childre){
			allResults.add(getMaxDistance(n));
		}
		Result r = new Result();
		Collections.sort(allResults, resultDepthComparator);
		r.maxDepth = allResults.get(0).maxDepth + 1;
		if(allResults.size()==1){
			r.maxDistance = getMax(allResults.get(0).maxDistance, r.maxDepth);
		}else {
			int tempDis = allResults.get(0).maxDepth+allResults.get(1).maxDepth+2;
			Collections.sort(allResults, resultDistanceComparator);
			r.maxDistance = getMax(allResults.get(0).maxDistance, tempDis);
		}
		return r;
	}
	
	private int getMax(int a, int b){
		return (a>b)?a:b;
	}
	
	private class Result{
		int maxDepth;
		int maxDistance;
		public Result(){
		}
		public Result(int maxDepth, int maxDistance) {
			this.maxDepth = maxDepth;
			this.maxDistance = maxDistance;
		}
		
	}
	private class ResultDepthComparator implements Comparator<Result>{
		@Override
		public int compare(Result arg0, Result arg1) {
			return -arg0.maxDepth + arg1.maxDepth;
		}	
	}
	private class ResultDistanceComparator implements Comparator<Result>{
		@Override
		public int compare(Result arg0, Result arg1) {
			return -arg0.maxDistance + arg1.maxDistance;
		}	
	}
	
	private class Node{
		Node parent = null;
		List<Node> childre = new ArrayList<Node>();
		
		public Node(Node parent){
			this.parent = parent;
		}
		
		public Node getRoot(){
			Node currentNode = this;
			while(currentNode.parent!=null){
				currentNode = currentNode.parent;
			}
			return currentNode;
		}
		
		//connect 2 nodes (union of 2 trees)
		public void union(Node n) {
			if(!(this.parent==n || n.parent==this)){
				if(parent == null){
					this.parent = n;
					n.childre.add(this);
				}else if (n.parent == null) {
					n.parent = this;
					this.childre.add(n);
				}else {
					int a = getHeight(this);
					int b = getHeight(n);
					if(a<b){
						this.resetRoot();
						this.parent = n;
						n.childre.add(this);
					}else {
						n.resetRoot();n.parent = this;
						this.childre.add(n);
					}
				}
			}
		}
		//change the root node of a tree
		private void resetRoot(){
			if(this.parent!=null){
				Node c = this;
				Node p = this.parent;
				while(p!=null){
					Node temp = p.parent;
					p.childre.remove(c);
					p.parent = c;
					c.childre.add(p);
					c = p;
					p = temp;
				}
				this.parent = null;
			}		
		}
		
		private int getHeight(Node n){
			int height = 0;
			while(n.parent!=null){
				height ++;
				n = n.parent;
			}
			return height;
		}
	}

}
