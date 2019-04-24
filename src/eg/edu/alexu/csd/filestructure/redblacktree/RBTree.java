package eg.edu.alexu.csd.filestructure.redblacktree;

public class RBTree<T extends Comparable<T>, V> implements IRedBlackTree<T, V> {
	private INode<T, V> root;

	public RBTree() {
		root = new Node<T, V>();
	}

	@Override
	public INode<T, V> getRoot() {
		return root;
	}

	@Override
	public boolean isEmpty() {
		return root.isNull();
	}

	@Override
	public void clear() {
		root = new Node<T, V>();
	}

	@Override
	public V search(T key) {
		INode<T, V> x = root;
		return recursiveSearch(x, key);
	}

	private V recursiveSearch(INode<T, V> x, T key) {
		if (x == null || x.isNull()) {
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
		if (x == null || x.isNull()) {
			return null;
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
		V v = recursiveSearch(root, key);
		return v != null;
	}

	@Override
	public void insert(T key, V value) {
		INode<T, V> y = null;
		INode<T, V> x = root;
		while (x != null && !x.isNull()) {
			y = x;
			if (key.compareTo(x.getKey()) < 0)
				x = x.getLeftChild();
			else
				x = x.getRightChild();
		}
		INode<T, V> z = new Node<T, V>();
		z.setKey(key);
		z.setValue(value);
		z.setParent(y);
		if (y == null || y.isNull()) {
			root = z;

		} else if (key.compareTo(y.getKey()) < 0) {
			y.setLeftChild(z);
		} else {
			y.setRightChild(z);
		}
		z.setLeftChild(new Node<T, V>());
		z.setRightChild(new Node<T, V>());
		z.setColor(INode.RED);
		insertFixup(z);
	}

	private void leftRotate(INode<T, V> x) {
		if (x == null || x.isNull() || x.getRightChild() == null || x.getRightChild().isNull()) {
			return;
		}
		INode<T, V> y = x.getRightChild();
		x.setRightChild(y.getLeftChild());
		if (y.getLeftChild() != null && !y.getLeftChild().isNull()) {
			y.getLeftChild().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == null || x.getParent().isNull()) {
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
		if (x == null || x.isNull() || x.getLeftChild() == null || x.getLeftChild().isNull()) {
			return;
		}
		INode<T, V> y = x.getLeftChild();
		x.setLeftChild(y.getRightChild());
		if (y.getRightChild() != null && !y.getRightChild().isNull()) {
			y.getRightChild().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == null || x.getParent().isNull()) {
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
		INode<T, V> z = DeleteRecursiveSearch(root, key);
		if (z == null || z.isNull()) {
			return false;
		}
		INode<T, V> y = z;
		boolean yOriginColor = y.getColor();
		INode<T, V> x;
		if (z.getLeftChild() == null || z.getLeftChild().isNull()) {
			x = z.getRightChild();
			Transplant(z, z.getRightChild());
		} else if (z.getRightChild() == null || z.getLeftChild().isNull()) {
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
		return true;
	}

	private void deleteFixUp(INode<T, V> x) {
		while (x != null && !x.isNull() && x != root && x.getColor() == INode.BLACK) {
			if (x == x.getParent().getLeftChild()) {
				INode<T, V> w = x.getParent().getRightChild();
				if (w.getColor()) {
					w.setColor(INode.BLACK); // case1
					w.getParent().setColor(INode.RED); // case1
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
					x.getRightChild().setColor(INode.BLACK);
					leftRotate(x.getParent());
					x = root;
				}
			} else {
				INode<T, V> w = x.getParent().getLeftChild();
				if (w.getColor() == INode.RED) {
					w.setColor(INode.BLACK); // case1
					w.getParent().setColor(INode.RED); // case1
					rightRotate(x.getParent());// case1
					w = x.getParent().getLeftChild();// case1
				}
				if (w.getRightChild().getColor() == INode.BLACK && w.getLeftChild().getColor()  == INode.BLACK ) {
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
					x.getLeftChild().setColor(INode.BLACK);
					rightRotate(x.getParent());
					x = root;
				}
			}
		}
		x.setColor(INode.BLACK);
	}

	private INode<T, V> minimum(INode<T, V> x) {
		while (x.getLeftChild() != null) {
			x = x.getLeftChild();
		}
		return x;
	}

	private void Transplant(INode<T, V> u, INode<T, V> v) {
		if (v == null) {
			u = null;
			return;
		}
		if (u.getParent() == null) {
			root = v;
		} else if (u == u.getParent().getLeftChild()) {
			u.getParent().setLeftChild(v);
		} else {
			u.getParent().setRightChild(v);
		}
		v.setParent(u.getParent());
	}

}
