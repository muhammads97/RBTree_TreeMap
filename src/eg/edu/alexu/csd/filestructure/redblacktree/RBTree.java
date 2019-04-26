package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RBTree<T extends Comparable<T>, V> implements IRedBlackTree<T, V> {
	private INode<T, V> root;
	private int size = 0; 
	class Node implements INode<T, V> {
		private INode<T, V> parent;
		private INode<T, V> l_child;
		private INode<T, V> r_child;
		private T key;
		private V value;
		private boolean color;

		public Node() {
			parent = NIL;
			l_child = NIL;
			r_child = NIL;
			value = null;
			key = null;
			color = INode.BLACK;
		}

		@Override
		public void setParent(INode<T, V> parent) {
			this.parent = parent;
		}

		@Override
		public INode<T, V> getParent() {
			return parent;
		}

		@Override
		public void setLeftChild(INode<T, V> leftChild) {
			this.l_child = leftChild;
		}

		@Override
		public INode<T, V> getLeftChild() {
			return l_child;
		}

		@Override
		public void setRightChild(INode<T, V> rightChild) {
			this.r_child = rightChild;
		}

		@Override
		public INode<T, V> getRightChild() {
			return r_child;
		}

		@Override
		public T getKey() {
			return key;
		}

		@Override
		public void setKey(T key) {
			this.key = key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public boolean getColor() {
			return color;
		}

		@Override
		public void setColor(boolean color) {
			this.color = color;
		}

		@Override
		public boolean isNull() {
			return value == null;
		}

	}

	private final INode<T, V> NIL = new Node();

	public RBTree() {
		root = NIL;
	}

	@Override
	public INode<T, V> getRoot() {
		return root;
	}

	@Override
	public boolean isEmpty() {
		return root == NIL;
	}

	@Override
	public void clear() {
		root = NIL;
		size = 0;
	}

	@Override
	public V search(T key) {
		if (key == null) {
			throw new RuntimeErrorException(new Error());
		}
		INode<T, V> x = root;
		return recursiveSearch(x, key);
	}

	private V recursiveSearch(INode<T, V> x, T key) {
		if (x == NIL) {
			return null;
		}
		if (x.getKey().equals(key)) {
			return x.getValue();
		} else if (x.getKey().compareTo(key) > 0) {
			x = x.getLeftChild();
			return recursiveSearch(x, key);
		} else {
			x = x.getRightChild();
			return recursiveSearch(x, key);
		}
	}

	private INode<T, V> DeleteRecursiveSearch(INode<T, V> x, T key) {
		if (x == NIL) {
			return NIL;
		}
		if (x.getKey().equals(key)) {
			return x;
		} else if (x.getKey().compareTo(key) > 0) {
			x = x.getLeftChild();
			return DeleteRecursiveSearch(x, key);
		} else {
			x = x.getRightChild();
			return DeleteRecursiveSearch(x, key);
		}
	}

	@Override
	public boolean contains(T key) {
		if (key == null) {
			throw new RuntimeErrorException(new Error());
		}
		V v = recursiveSearch(root, key);
		return v != null;
	}

	@Override
	public void insert(T key, V value) {
		if(key == null || value == null) {
			throw new RuntimeErrorException(new Error());
		}
		INode<T, V> y = NIL;
		INode<T, V> x = root;
		while (x != NIL) {
			y = x;
			if (key.compareTo(x.getKey()) < 0) {
				x = x.getLeftChild();
			} else if (key.compareTo(x.getKey()) > 0) {
				x = x.getRightChild();
			} else if (key.compareTo(x.getKey()) == 0) {
				x.setValue(value);
				return;
			}
				
		}
		size++;
		INode<T, V> z = new Node();
		z.setKey(key);
		z.setValue(value);
		z.setParent(y);
		if (y == NIL) {
			root = z;

		} else if (key.compareTo(y.getKey()) < 0) {
			y.setLeftChild(z);
		} else {
			y.setRightChild(z);
		}
		z.setLeftChild(NIL);
		z.setRightChild(NIL);
		z.setColor(INode.RED);
		insertFixup(z);
	}

	private void leftRotate(INode<T, V> x) {
		if (x == NIL || x.getRightChild() == NIL) {
			return;
		}
		INode<T, V> y = x.getRightChild();
		x.setRightChild(y.getLeftChild());
		if (y.getLeftChild() != NIL) {
			y.getLeftChild().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == NIL) {
			root = y;
		} else if (x == x.getParent().getLeftChild()) {
			x.getParent().setLeftChild(y);
		} else {
			x.getParent().setRightChild(y);
		}
		y.setLeftChild(x);
		x.setParent(y);
	}

	private void rightRotate(INode<T, V> x) {
		if (x == NIL || x.getLeftChild() == NIL) {
			return;
		}
		INode<T, V> y = x.getLeftChild();
		x.setLeftChild(y.getRightChild());
		if (y.getRightChild() != NIL) {
			y.getRightChild().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == NIL) {
			root = y;
		} else if (x == x.getParent().getLeftChild()) {
			x.getParent().setLeftChild(y);
		} else {
			x.getParent().setRightChild(y);
		}
		y.setRightChild(x);
		x.setParent(y);
	}

	private void insertFixup(INode<T, V> z) {
		if (root == z) {
			root.setColor(INode.BLACK);
			return;
		} else if (z.getParent().getColor() == INode.RED) {
			INode<T, V> uncle;
			if (z.getParent() == z.getParent().getParent().getLeftChild()) {
				uncle = z.getParent().getParent().getRightChild();
			} else {
				uncle = z.getParent().getParent().getLeftChild();
			}
			if (uncle.getColor() == INode.RED) {
				z.getParent().setColor(INode.BLACK);
				uncle.setColor(INode.BLACK);
				z.getParent().getParent().setColor(INode.RED);
				z = z.getParent().getParent();
				insertFixup(z);
			} else {
				if (z == z.getParent().getLeftChild() && z.getParent() == z.getParent().getParent().getLeftChild()) {
					INode<T, V> g = z.getParent().getParent();
					INode<T, V> p = z.getParent();
					rightRotate(z.getParent().getParent());
					g.setColor(INode.RED);
					p.setColor(INode.BLACK);

				} else if (z == z.getParent().getRightChild()
						&& z.getParent() == z.getParent().getParent().getLeftChild()) {
					INode<T, V> p = z.getParent();
					leftRotate(p);
					INode<T, V> g = z.getParent();
					rightRotate(g);
					g.setColor(INode.RED);
					z.setColor(INode.BLACK);

				} else if (z == z.getParent().getLeftChild()
						&& z.getParent() == z.getParent().getParent().getRightChild()) {
					INode<T, V> p = z.getParent();
					rightRotate(p);
					INode<T, V> g = z.getParent();
					leftRotate(g);
					g.setColor(INode.RED);
					z.setColor(INode.BLACK);

				} else {
					INode<T, V> g = z.getParent().getParent();
					INode<T, V> p = z.getParent();
					leftRotate(g);
					g.setColor(INode.RED);
					p.setColor(INode.BLACK);

				}
			}
		}

	}

	@Override
	public boolean delete(T key) {
		if (key == null) {
			throw new RuntimeErrorException(new Error());
		}
		INode<T, V> z = DeleteRecursiveSearch(root, key);
		if (z == NIL) {
			return false;
		}
		INode<T, V> y = z;
		boolean yOriginColor = y.getColor();
		INode<T, V> x;
		if (z.getLeftChild() == NIL) {
			x = z.getRightChild();
			Transplant(z, z.getRightChild());
		} else if (z.getRightChild() == NIL) {
			x = z.getLeftChild();
			Transplant(z, z.getLeftChild());
		} else {
			y = minimum(z.getRightChild());
			yOriginColor = y.getColor();
			x = y.getRightChild();
			if (y.getParent() == z) {
				x.setParent(y);
			} else {
				Transplant(y, y.getRightChild());
				y.setRightChild(z.getRightChild());
				y.getRightChild().setParent(y);
			}
			Transplant(z, y);
			y.setLeftChild(z.getLeftChild());
			y.getLeftChild().setParent(y);
			y.setColor(z.getColor());
		}
		if (yOriginColor == INode.BLACK) {
			deleteFixUp(x);
		}
		size--;
		return true;
	}

	private void deleteFixUp(INode<T, V> x) {
		while (x != root && x.getColor() == INode.BLACK) {
			if (x == x.getParent().getLeftChild()) {
				INode<T, V> w = x.getParent().getRightChild();
				if (w.getColor() == INode.RED) {
					w.setColor(INode.BLACK); // case1
					x.getParent().setColor(INode.RED); // case1
					leftRotate(x.getParent());// case1
					w = x.getParent().getRightChild();// case1
				}
				if (w.getLeftChild().getColor() == INode.BLACK && w.getRightChild().getColor() == INode.BLACK) {
					w.setColor(INode.RED);
					x = x.getParent();
					continue;
				} else if (w.getRightChild().getColor() == INode.BLACK) {
					w.getLeftChild().setColor(INode.BLACK);
					w.setColor(INode.RED);
					rightRotate(w);
					w = x.getParent().getRightChild();
				}
				if (w.getRightChild().getColor() == INode.RED) {
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(INode.BLACK);
					w.getRightChild().setColor(INode.BLACK);
					leftRotate(x.getParent());
					x = root;
				}
			} else {
				INode<T, V> w = x.getParent().getLeftChild();
				if (w.getColor() == INode.RED) {
					w.setColor(INode.BLACK); // case1
					x.getParent().setColor(INode.RED); // case1
					rightRotate(x.getParent());// case1
					w = x.getParent().getLeftChild();// case1
				}
				if (w.getRightChild().getColor() == INode.BLACK && w.getLeftChild().getColor() == INode.BLACK) {
					w.setColor(INode.RED);
					x = x.getParent();
					continue;
				} else if (w.getLeftChild().getColor() == INode.BLACK) {
					w.getRightChild().setColor(INode.BLACK);
					w.setColor(INode.RED);
					leftRotate(w);
					w = x.getParent().getLeftChild();
				}
				if (w.getLeftChild().getColor() == INode.RED) {
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(INode.BLACK);
					w.getLeftChild().setColor(INode.BLACK);
					rightRotate(x.getParent());
					x = root;
				}
			}
		}
		x.setColor(INode.BLACK);
	}

	/**
	 * @param x
	 */



	private INode<T, V> minimum(INode<T, V> x) {
		while (x.getLeftChild() != NIL) {
			x = x.getLeftChild();
		}
		return x;
	}

	private void Transplant(INode<T, V> u, INode<T, V> v) {
		if (u.getParent() == NIL) {
			root = v;
		} else if (u == u.getParent().getLeftChild()) {
			u.getParent().setLeftChild(v);
		} else {
			u.getParent().setRightChild(v);
		}
		v.setParent(u.getParent());
	}
	public INode<T, V> getNILNode(){
		return NIL;
	}
	public int size(){
		return size;
	}

}
