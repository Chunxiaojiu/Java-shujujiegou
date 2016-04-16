<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=default"></script>
# Java-数据结构及算法</br>
### 代码实现第一发——AVL树</br>
###### 作者：TheSeed     
*版权所有，要用要给原版链接哦，么么哒(づ￣ 3￣)づ*
***
AVL树（*Adelson-Velskii* 和 *Landis*）是带有平衡的二叉查找树，而且这个**平衡条件必须要容易保持**，而且它的深度需要是*O(logN)*
一棵AVL树要满足的条件用简单点的语言来描述就是，每个节点对应的左右子树的高度相差不能超过1，通过这个我们也可以粗略的计算出每棵AVL树的高度最多\\(1.44*\log(N+2)-1.328\\)

```Java
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
```
