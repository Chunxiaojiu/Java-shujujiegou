package AVLtree;

public class AvlNodeTree<AnyType> {
	AnyType element;
	AvlNodeTree<AnyType> left;
	AvlNodeTree<AnyType> right;
	int height;

	AvlNodeTree(AnyType theElement) {
		this(theElement, null, null);
	}

	AvlNodeTree(AnyType theElement, AvlNodeTree<AnyType> lt,
			AvlNodeTree<AnyType> rt) {
		element = theElement;
		lt = left;
		rt = right;
		height = 0;
	}

	private int Height(AvlNodeTree<AnyType> t) {
		return t == null ? -1 : t.height;
	}

	private <AnyType extends Comparable> AvlNodeTree Insert(AnyType x,
			AvlNodeTree<AnyType> t) {
		if (t == null)
			return new AvlNodeTree<>(x, null, null);
		int compareResult = x.compareTo(t.element);
		if (compareResult < 0)
			t.left = Insert(x, t.left);
		else if (compareResult > 0)
			t.right = Insert(x, t.right);
		else
			;
		return null;
	}

	private AvlNodeTree<AnyType> Balance(AvlNodeTree<AnyType> t) {
		if (t == null)
			return t;
		if (Height(t.left) - Height(t.right) > 1) {
			if (Height(t.left.left) >= Height(t.left.right)) {
				t = RotateWithLeftChild(t);
			} else {
				t = TwiceRotateWithLeftChild(t);
			}
		} else {
			if (Height(t.right) - Height(t.left) > 1) {
				if (Height(t.right.right) >= Height(t.right.left)) {
					t = RotateWithRightChild(t);
				} else {
					t = TwiceRotateWithRightChild(t);
				}
			}
		}
		t.height = Math.max(Height(t.left), Height(t.right)) + 1;
		return t;

	}

	private AvlNodeTree<AnyType> RotateWithLeftChild(AvlNodeTree<AnyType> rl2) {
		AvlNodeTree<AnyType> rl1 = rl2.left;// 注意这里是整体赋值过去了一棵子树
		rl2.left = rl1.right;
		rl1.right = rl2;
		rl2.height = Math.max(Height(rl2.left), Height(rl2.right)) + 1;
		rl1.height = Math.max(Height(rl1.left), rl2.height) + 1;
		return rl1;
	}

	private AvlNodeTree<AnyType> RotateWithRightChild(AvlNodeTree<AnyType> rl1) {
		AvlNodeTree<AnyType> rl2 = rl1.right;// 注意这里是整体赋值过去了一棵子树
		rl1.right = rl2.left;
		rl2.left = rl1;
		rl1.height = Math.max(Height(rl1.left), Height(rl1.right)) + 1;
		rl2.height = Math.max(Height(rl2.left), rl1.height) + 1;
		return rl2;
	}

	private AvlNodeTree<AnyType> TwiceRotateWithLeftChild(
			AvlNodeTree<AnyType> rl3) {
		rl3.left = RotateWithRightChild(rl3.left);
		return RotateWithLeftChild(rl3);
	}

	private AvlNodeTree<AnyType> TwiceRotateWithRightChild(
			AvlNodeTree<AnyType> rl3) {
		rl3.right = RotateWithLeftChild(rl3.right);
		return RotateWithRightChild(rl3.left);
	}
}
