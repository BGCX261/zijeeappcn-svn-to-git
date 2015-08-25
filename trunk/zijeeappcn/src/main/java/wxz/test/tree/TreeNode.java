package wxz.test.tree;

public class TreeNode {
	
	private TreeNode parent = null;
	private TreeNode leftChild = null;
	private TreeNode rightChild = null;
	private Object info = null;
	
	private int index = -1;
	
	public TreeNode(){
		
	}
	public TreeNode(Object info){
		this.info = info;
	}
	
	public void setRoot(){
		this.parent = null;
		this.index = 0;
	}

	public int getIndex(){
		index = parent.getIndex() + 1;
		return index;
	}


	public String toString(){
		return info+",";
	}
	
	public TreeNode getParent() {
		return parent;
	}
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	public TreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}
	public TreeNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	
	
}
