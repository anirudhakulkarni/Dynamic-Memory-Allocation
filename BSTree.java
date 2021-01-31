// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right; // Children.
    private BSTree parent; // Parent pointer.

    public BSTree() {
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root!
        // and left child will always be null.
    }

    public BSTree(int address, int size, int key) {
        super(address, size, key);
    }

    public BSTree Insert(int address, int size, int key) {
        BSTree new_node = new BSTree(address, size, key);
        BSTree current = this.getSent();
        // current is at head sentinal now
        if (current.right == null) {
            current.right = new_node;
            new_node.parent = current;
            return new_node;
        }
        // handled if node to be inserted is root and initial tree is empty with only
        // sentinel node
        current = current.right;
        // traverse till place is not found.
        while (true) {
            if (current.key < key || (current.key == key && current.address < address)) {
                if (current.right == null) {
                    current.right = new_node;
                    new_node.parent = current;
                    return new_node;
                }
                current = current.right;
                continue;
            }
            if (current.key > key || (current.key == key && current.address >= address)) {
                if (current.left == null) {
                    current.left = new_node;
                    new_node.parent = current;
                    return new_node;
                }
                current = current.left;
                continue;
            }
            if (current.key == key && current.address == address && current.size < size) {
                if (current.right == null) {
                    current.right = new_node;
                    new_node.parent = current;
                    return new_node;
                }
                current = current.right;
                continue;
            }
            if (current.key == key && current.address == address && current.size >= size) {
                if (current.left == null) {
                    current.left = new_node;
                    new_node.parent = current;
                    return new_node;
                }
                current = current.left;
                continue;
            }

        }

    }

    public boolean Delete(Dictionary e) {
        BSTree current = this;
        if (current.key == e.key && current.address == e.address && current.size == e.size && !current.isSentinal()) {
            if (current.successor() != null) {
                current.delHelperTWS();
                return true;
            }
            if (current.predesessor() != null) {
                current.delHelperTWP();
                return true;
            }
            current.address = -1;
            current.key = -1;
            current.size = -1;
            current.parent = null;
            return true;
        }
        int flag = 0;
        if (this != current.getSent()) {// BUG UPDATED
            flag = 1;
        }

        current = current.getSent();
        if (current.right == null) {// empty tree
            return false;
        }
        current = current.right;
        while (current != null) {
            // System.out.println("-----144");
            if (current.key == e.key && current.address == e.address && current.size == e.size) {

                if (flag == 1) {
                    current.delHelper(this);
                } else {
                    current.delHelper(null);
                }
                // System.out.println("Printting This right address "+current.right.address+"
                // key"+current.right.key);

                return true;
            }

            if (current.key > e.key || (current.key == e.key && current.address > e.address)) {
                current = current.left;
                continue;
            }
            if (current.key < e.key || (current.key == e.key && current.address < e.address)) {
                current = current.right;
                continue;
            }
        }

        return false;
    }

    public BSTree Find(int key, boolean exact) {
        BSTree current = this;
        current = current.getSent();
        if (current.right == null) {// empty tree
            return null;
        }
        current = current.right;
        if (exact == true) {
            while (current != null) {
                if (current.key == key) {
                    return current.findTrue(key);
                    // it is assumed that we are taking key as first preference and address as
                    // second preference. Current code will give min key among nodes with equal key.
                    // replace return curren.findTrue(key); with return current; to get first
                    // occurance
                }
                if (current.key > key) {
                    current = current.left;
                    continue;
                }
                if (current.key < key) {
                    current = current.right;
                    continue;
                }
            }
            return null;
        }
        if (exact == false) {
            int flag = 0;
            while (current != null) {
                if (current.key == key) {
                    return current.findTrue(key);
                }
                if (current.key < key) {
                    current = current.right;
                    continue;
                }
                if (current.key > key) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                return current.findFalse(key);
                /*
                 * more efficient implementation return current.left.findFalse(key);
                 */
            }
            return null;
        }
        return null;
    }

    public BSTree getFirst() {
        BSTree current = this.getSent();
        if (current.right == null) {
            return null;
        }
        // checked empty tree condition
        current = current.right;
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public BSTree getNext() {
        return this.successor();
    }

    public boolean sanity() {
        if (!this.rootforsanity()) {// checks for path to the root
            return false;
        }
        BSTree current = this.getSent();
        if (current.address != -1 || current.key != -1 || current.size != -1) {// invalid sentinel
            return false;
        }
        if (current.left != null) {
            return false;
        }
        if (current.right == null) {
            return true;
        }

        current = current.right;
        // now we have non sentinel node
        BSTree min = new BSTree(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        BSTree max = new BSTree(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

        return (pccheck(current)) && (bstproperty(current, min, max));
        // pccheck ensures acyclic so that bstproperty can work after it
    }

    // helper functions ahead
    private boolean rootforsanity() {// floyds like algo
        if (this.parent == null) {
            return true;
        }
        BSTree p1 = this;
        BSTree p2 = this;
        while (p1 != null && p2 != null && p2.parent != null) {
            // System.out.println("P1 "+p1.key);
            // System.out.println("P2 "+p2.key);
            p1 = p1.parent;
            p2 = p2.parent.parent;
            if (p1 == p2) {
                return false;
            }
        }
        return true;
    }

    private boolean pccheck(BSTree root) {// checks if everyone follows child parent relationship

        if (root == null) {
            return true;
        }
        if (root.left != null && root.right != null) {
            return (root.right.parent == root) && (root.left.parent == root) && pccheck(root.right)
                    && pccheck(root.left);
        }
        if (root.left == null && root.right != null) {
            return (root.right.parent == root) && pccheck(root.right) && pccheck(root.left);
        }
        if (root.left != null && root.right == null) {
            return pccheck(root.right) && pccheck(root.left) && (root.left.parent == root);
        }
        return true;

    }

    private boolean bstproperty(BSTree current, BSTree min, BSTree max) {// checks for bst

        if (current == null) {
            return true;
        }
        // System.out.println("cu "+current.address+" "+current.key);
        // System.out.println("MIN "+min.address+" "+min.key);
        if (current.key < min.key || current.key > max.key) {
            return false;
        }
        if (current.key == min.key && current.address <= min.address) {// nodes with same data should be at left child
                                                                       // hence <=
            return false;
        }
        if (current.key == max.key && current.address > max.address) {
            return false;
        }

        return bstproperty(current.left, min, current) && bstproperty(current.right, current, max);
    }

    private BSTree getroot() {
        return this.getSent().right;
    }

    private BSTree findTrue(int key) {
        BSTree current = this;
        if (current.key == key && current.left == null) {
            return current;// no need to return smallest. it is the smallest
        }
        if (current.key >= key) {
            if (current.predesessor().key != key) {
                return current;//
            }
            return current.left.findTrue(key);
        }
        if (current.key < key) {
            while (current.right != null && current.key < key) {
                current = current.right;
            }
            while (current.left != null && current.left.key >= key) {
                current = current.left;
            }
            return current.findTrue(key);//
        }

        return this;
    }

    private BSTree findFalse(int key) {
        BSTree current = this;
        if (current.key == key) {
            return current.findTrue(key);
        }
        if (current.key >= key && current.left == null) {
            return current;// no need to return smallest. it is the smallest
        }
        if (current.key < key) {
            while (current.right != null && current.key < key) {
                current = current.right;
            }
            while (current.left != null && current.left.key == key) {
                current = current.left;
            }
            return current.findFalse(key);//
        }
        if (current.key >= key) {
            if (current.predesessor().key < key) {
                return current;//
            }
            return current.left.findFalse(key);
        }

        return this;

    }

    private void delHelper(BSTree original) {
        BSTree current = this;
        if (current.right == null && current.left == null) {
            if (current.parent.right == current) {
                current.parent.right = null;
                return;
            }
            current.parent.left = null;
            return;
        }
        // garbage collection will remove it as no pointer to the node exists
        if (current.left == null && current.right != null) {
            if (current.parent.right == current) {
                current.right.parent = current.parent;
                current.parent.right = current.right;
                return;
            }
            current.right.parent = current.parent;
            current.parent.left = current.right;
            return;
        }
        if (current.right == null && current.left != null) {
            if (current.parent.left == current) {
                current.left.parent = current.parent;
                current.parent.left = current.left;
                return;
            }
            current.left.parent = current.parent;
            current.parent.right = current.left;
            return;
        }
        if (current.right != null && current.left != null) {
            BSTree suc = current.successor();
            if (original != null && suc == original) {
                suc = current.predesessor();
            }
            this.address = suc.address;
            this.key = suc.key;
            this.size = suc.size;
            suc.delHelper(null);
            return;
        }
    }

    private void delHelperTWS() {
        BSTree current = this;
        BSTree suc = current.successor();
        this.address = suc.address;
        this.key = suc.key;
        this.size = suc.size;
        suc.delHelper(null);
        return;

    }

    private void delHelperTWP() {
        BSTree current = this;
        BSTree suc = current.predesessor();
        this.address = suc.address;
        this.key = suc.key;
        this.size = suc.size;
        suc.delHelper(null);
        return;

    }
    // private void delHelperT(){
    // if(this.left==null &&this.right==null){
    // this.address=this.parent.address;
    // this.size=this.parent.size;
    // this.key=this.parent.key;
    // //System.out.println("This value= "+this.address+" "+this.key);
    // if(this.parent.right==this){
    // //System.out.println("This was right child");
    // this.left=this.parent.left;
    // this.parent.left.parent=this;
    // }
    // if(this.parent.left==this){
    // //System.out.println("This was left child");
    // //System.out.println("This value bet everthing= "+this.parent.address+"
    // "+this.parent.key);
    // this.right=this.parent.right;
    // this.parent.right.parent=this;
    // }
    // if(this.parent.parent.left==this.parent){
    // //System.out.println("This was parents left child");
    // this.parent.parent.left=this;
    // this.parent=this.parent.parent;
    // //System.out.println("This value after everthing= "+this.parent.address+"
    // "+this.parent.key);
    // return;
    // }
    // if(this.parent.parent.right==this.parent){
    // // System.out.println("This was parents right child");
    // this.parent.parent.right=this;//this is neccessary before else you will lose
    // parent
    // this.parent=this.parent.parent;

    // return;
    // }
    // return;
    // }
    // if(this.left!=null&&this.right==null){
    // this.delHelperTWS();
    // return;
    // }
    // if(this.right!=null){
    // this.delHelper(null);
    // return;
    // }
    // return;

    // }
    private boolean isSentinal() {
        if (this.parent == null) {
            return true;
        }
        return false;
    }

    private BSTree successor() {
        BSTree current = this;
        if (current.isSentinal()) {
            return null;
        }
        // checked for invalid input of sentinel

        if (current.right != null) {
            current = current.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
        while (current.parent != null && current.parent.left != current) {
            current = current.parent;
        }
        if (current.parent != null && !current.parent.isSentinal()) {
            return current.parent;
        }
        return null;
    }

    private BSTree predesessor() {
        BSTree current = this;
        if (current.isSentinal()) {
            return null;
        }
        // checked for invalid input of sentinel
        if (current.left != null) {
            current = current.left;
            while (current.right != null) {
                current = current.right;
            }
            return current;
        }
        while (current.parent != null && current.parent.right != current) {
            current = current.parent;
        }
        if (current.parent != null && !current.parent.isSentinal()) {
            return current.parent;
        }
        return null;
    }

    private BSTree getSent() {
        BSTree current = this;
        while (!current.isSentinal()) {
            current = current.parent;
        }
        return current;
    }

    public static void main(String[] args) {
        BSTree d = new BSTree();
        d = d.Insert(1, 2, 3);
        d = d.Insert(4, 5, 6);
        d = d.Insert(7, 8, 9);
        d = d.Insert(7, 8, 10);
        d = d.Insert(1, 2, 5);
        d = d.Insert(1, 2, 4);
        System.out.println(d.sanity());
        d.parent.parent.right.left = d;
        d.parent = d.parent.parent.right;
        System.out.println(d.sanity());
    }
}
