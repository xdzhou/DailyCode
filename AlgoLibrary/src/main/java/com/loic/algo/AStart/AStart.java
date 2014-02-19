package com.loic.algo.AStart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AStart {
	int[][] map;
	int line, row;
	List<Node> closeList;
	List<Node> openList;
	private final int COST_STRAIGHT = 1;//垂直方向或水平方向移动的路径评分
    private final int COST_DIAGONAL = 14;//斜方向移动的路径评分
    NodeComparator comparator = new NodeComparator();
	
	public AStart(int[][] map, int line, int row) {
		this.map = map;
		this.line = line;
		this.row = row;
		closeList = new ArrayList<Node>();
		openList = new ArrayList<Node>();
	}
	
	public boolean search(int startX, int startY, int endX, int endY){
		if(!initCheck(startX, startY, endX, endY)) return false;
		Node startNode = new Node(startX, startY, null);
		startNode.setG(0);
		openList.add(startNode);
		Node endNode = null;
		while(openList.size()>0){
			Node node = openList.get(0);
			//closeList.add(node);
			if(node.getX()==endX && node.getY()==endY){
				endNode = node;
				break;
			}
			System.out.println("current: "+node);
			if(node.getX()-1>=0){
				treatNewNode(node, node.getX()-1, node.getY(), COST_STRAIGHT, endX, endY);
			}
			if (node.getX()+1<=line-1) {
				treatNewNode(node, node.getX()+1, node.getY(), COST_STRAIGHT, endX, endY);
			}
			if (node.getY()-1>=0) {
				treatNewNode(node, node.getX(), node.getY()-1, COST_STRAIGHT, endX, endY);
			}
			if (node.getY()+1<=row-1) {
				treatNewNode(node, node.getX(), node.getY()+1, COST_STRAIGHT, endX, endY);
			}
			openList.remove(0);
			closeList.add(node);
			Collections.sort(openList, comparator);
		}
		if(endNode == null) {
			System.out.println("Not found!");
			return false;
		}else {
			printTrace(endNode);
			return true;			
		}
		
	}
	
	private boolean treatNewNode(Node parentNode, int x, int y, int cost, int endX, int endY){	
		Node tempNode = new Node(x,y,parentNode);
		/*if(x==endX && y==endY){ //get the target, return true
			openList.clear();
			closeList.add(tempNode);
			return true;
		}*/
		if(closeList.contains(tempNode)) return false;
		else if(openList.contains(tempNode)){
			Node node = getNodeEqualTo(tempNode);
			int newG = parentNode.getG()+cost;
			if(newG < node.getG()){
				node.setG(newG);
				node.updateF();
				node.setParentNode(parentNode);
			}
		}else{
			if(map[x][y]==1){
				int estimeLen = estimeFunction(x,y,endX,endY);
				tempNode.setG(parentNode.getG()+cost);
				tempNode.setH(estimeLen);
				tempNode.updateF();
				System.out.println(tempNode);
				openList.add(tempNode);
			}else {
				closeList.add(tempNode);
			}
		}
		//Collections.sort(openList, comparator);
		return false;
	}
	
	private void printTrace(Node endNode){
		do{
			System.out.print("<--("+endNode.getX()+","+endNode.getY()+")");
			map[endNode.getX()][endNode.getY()] = 8;
			endNode = endNode.getParentNode();
		}while(endNode!=null);
		System.out.println();
		for(int i=0; i<line; i++){
			for(int j=0; j<row; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
			
	}
	
	private int estimeFunction(int sx, int sy, int ex, int ey){
		return Math.abs(ex-sx)+Math.abs(ey-sy);
	}
	
	private Node getNodeEqualTo(Node node) {
		for(Node n : openList){
			if(n.equals(node)) return n;
		}
		return null;
	}
	
	private boolean initCheck(int startX, int startY, int endX, int endY){
		if(startX<0 || startX>line-1) return false;
		else if(startY<0 || startY>row-1) return false;
		else if(endX<0 || endX>line-1) return false;
		else if(endY<0 || endY>row-1) return false;
		else if (map[startX][startY]==0 || map[endX][endY]==0) { // 0: can't pass
			return false;
		}else {
			return true;
		}
	}
	//comparator for node
	private class NodeComparator implements Comparator<Node>{
		public int compare(Node o1, Node o2) {
			return o1.getF()-o2.getF();
		}

	}
}
