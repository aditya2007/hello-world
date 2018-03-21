package com.java.core.dsl.graphs;

public class LookupTreeApp {
	
	public static void main(String[] args) {
		//Lets create nodes as given as an example in the article
		Node nh0 = new Node('h',false);
		Node na1 = new Node('a', false);
		Node ne1 = new Node('e',true);
		Node nt2 = new Node('t', true);
		Node ne2 = new Node('e', false);
		Node na2 = new Node('a', false);
		Node nc2 = new Node('c', false);
		Node ne3 = new Node('e', true);
		Node nl3 = new Node('l', true);
		Node nt3 = new Node('t', true);
		Node nk3 = new Node('k', true);

		//Create the graph, add nodes, create edges between nodes
		LookupTree g = new LookupTree();
		g.addNode(nh0);
		g.addNode(na1);
		g.addNode(ne1);
		g.addNode(nt2);
		g.addNode(ne2);
		g.addNode(na2);
		g.addNode(nc2);
		g.addNode(ne3);
		g.addNode(nl3);
		g.addNode(nt3);
		g.addNode(nk3);
		g.setRootNode(nh0);
		
		g.connectNode(nh0,na1);
		g.connectNode(nh0,ne1);
		
		g.connectNode(na1,nt2);
		
		g.connectNode(ne1,ne2);
		g.connectNode(ne1,na2);
		g.connectNode(ne1,nc2);
		
		g.connectNode(nt2,ne3);
		
		g.connectNode(ne2,nl3);
		g.connectNode(na2,nt3);
		g.connectNode(nc2,nk3);


		
		//Perform the traversal of the graph
		System.out.println("DFS Traversal of a tree is ###------------->");
		g.lookup(nh0, "heat");
	}

}
