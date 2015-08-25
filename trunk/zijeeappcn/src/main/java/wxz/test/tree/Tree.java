package wxz.test.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree {
	
	TreeNode root = null;
	public Tree(TreeNode root ){
		this.root = root;
	}
	// ǰ��
	private List front(TreeNode current) {
		Stack stack = new Stack();
		stack.add(current);
		List result = new ArrayList();
		do {
			if (current == null) {
				current = (TreeNode) stack.pop();
				current = current.getRightChild();
			} else {
				result.add(current);
				current = current.getLeftChild();
			}
			if (!(current == null)){
				stack.push((Object) current);
			}
		} while (!(stack.isEmpty()));
		return result;
	}
	
	//�������������
	public List front(){
		List result = new ArrayList();
		front(root,result); 
		return result;
	}
	private void front(TreeNode current, List result) {
		if (current == null)
			return;
		result.add(current);
		front(current.getLeftChild(), result);
		front(current.getRightChild(), result);
	}
	
	
	
	//�������������
	public List middle(){
		List result = new ArrayList();
		middle(root,result); 
		return result;
	}
	private void middle(TreeNode current,List result ) { 
	if (current == null) return; 
		middle(current.getLeftChild(),result); 
//		asString += current.toString();
		result.add(current);
		middle(current.getRightChild(),result); 
	} 

	//��������������ĵݹ��㷨 
//	private void rear(Node2 current) { 
//	if (current == null) return; 
//	rear(current.left); 
//	rear(current.right); 
//	asString += current.ch; 
//	} 

	
	
	
	// ��α�����
	private void findNodeByLevel(){
		List list = new ArrayList();
		List result = new ArrayList();
		list.add(root);
		findNodeByLevel(list, 0,result);
		
		System.out.println("-----");
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}
	private void findNodeByLevel(List<TreeNode> nodes,int level,List result) {
		if (nodes == null || nodes.size() == 0) {
			return;
		}
		ArrayList<TreeNode> temp = new ArrayList();
		for (TreeNode node : nodes) {
			System.out.println("��" + level + "��:" + node.getInfo());
			result.add(node);
			if (node.getLeftChild() != null) {
				temp.add(node.getLeftChild());
			}
			if (node.getRightChild() != null) {
				temp.add(node.getRightChild());
			}
		}
		nodes.removeAll(nodes);
		findNodeByLevel(temp, level + 1,result);
	}
	
//	private void findNodeByLevel(List<TreeNode> nodes, int level, List result) {
//		if (nodes == null || nodes.size() == 0) {
//			return;
//		}
//		ArrayList<TreeNode> temp = new ArrayList();
//		for (TreeNode node : nodes) {
//			result.add(node);
//			if (node.getLeftChild() != null) {
//				temp.add(node.getLeftChild());
//			}
//			if (node.getRightChild() != null) {
//				temp.add(node.getRightChild());
//			}
//		}
//		nodes.removeAll(nodes);
//		findNodeByLevel(temp, level + 1, result);
//	}
	
	
	
	public static void main(String[] a){
		TreeNode nodeRoot = new TreeNode();
		nodeRoot.setRoot();
		nodeRoot.setInfo("root");
		
		TreeNode nodeL1 = new TreeNode();
		nodeL1.setInfo("nodeL1");
		
		TreeNode nodeR1 = new TreeNode();
		nodeR1.setInfo("nodeR1");
		
		nodeRoot.setLeftChild(nodeL1);
		nodeRoot.setRightChild(nodeR1);
		
		Tree tree = new Tree(nodeRoot);
		
//		tree.findNodeByLevel();
		
//		List list = tree.front(nodeRoot);
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		tree.middle(nodeRoot);
//		System.out.println(asString);
		List list = tree.front();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		
	}
	
	

}
