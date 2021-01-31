
// Class: Height balanced AVL Tree
// Binary Search Tree
//10.36-12.45 12-6 = 8 hrs
public class AVLTree extends BSTree {

    private AVLTree left, right; // Children.
    private AVLTree parent; // Parent pointer.
    private int height; // The height of the subtree

    public AVLTree() {
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root!
        // and left child will always be null.

    }

    public AVLTree(int address, int size, int key) {
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions.
    // Some of the functions may be directly inherited from the BSTree class and
    // nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.

    public AVLTree Insert(int address, int size, int key) {
        AVLTree new_node = new AVLTree(address, size, key);
        AVLTree current = this.getSent();
        // current is at head sentinal now
        if (current.right == null) {
            current.right = new_node;
            new_node.parent = current;
            return new_node;
        } // no need of height update
          // handled if node to be inserted is root and initial tree is empty with only
          // sentinel node
        current = current.right;
        // traverse till place is not found.
        while (true) {
            if (current.key < key || (current.key == key && current.address < address)) {
                if (current.right == null) {
                    current.right = new_node;
                    new_node.parent = current;
                    break;
                }
                current = current.right;
                continue;
            }
            if (current.key > key || (current.key == key && current.address >= address)) {
                if (current.left == null) {
                    current.left = new_node;
                    new_node.parent = current;
                    break;
                }
                current = current.left;
                continue;
            }
            if (current.key == key && current.address == address && current.size < size) {
                if (current.right == null) {
                    current.right = new_node;
                    new_node.parent = current;
                    break;
                }
                current = current.right;
                continue;
            }
            if (current.key == key && current.address == address && current.size >= size) {
                if (current.left == null) {
                    current.left = new_node;
                    new_node.parent = current;
                    break;
                }
                current = current.left;
                continue;
            }

        }
        // possible cases of insertions:
        // parent is of height 1 or of height 0 initially
        // if 1 implies no change in hb
        // else change in height balance

        if (new_node.parent.height == 1) {
            return new_node;
        }
        if (new_node.parent.parent.isSentinal()) {
            new_node.parent.height = 1;
            return new_node;
        }
        // now grandparent exits
        if (new_node.parent.parent.height == 2) {
            new_node.parent.height = 1;
            return new_node;
        }
        updateh(new_node.parent);
        checkbalance(new_node.parent);
        return new_node;
        // for (AVLTree d = new_node.getFirst(); d != null; d = d.getNext()){
        // System.out.println(d.address+" "+d.size+" "+d.key+" height "+d.height);
        // }
        /*
         * AVLTree imbalance=firstub(new_node); if(imbalance==null){ return new_node; }
         * // System.out.println(imbalance.address+" lll "+imbalance.key);
         * if(imbalance.parent.left==imbalance&&imbalance.parent.parent.left==imbalance.
         * parent){ rightrotate(imbalance.parent.parent);
         * 
         * updateh(imbalance.parent.left); updateh(imbalance.parent.right);
         * updateh(imbalance.parent);
         * 
         * return new_node; }
         * if(imbalance.parent.left==imbalance&&imbalance.parent.parent.right==imbalance
         * .parent){ rightrotate(imbalance.parent); leftrotate(imbalance.parent);//new
         * new_node is parent now // System.out.println("MC"); // for (AVLTree d =
         * new_node.getFirst(); d != null; d = d.getNext()){ //
         * System.out.println(d.address+" "+d.size+" "+d.key+" height "+d.height); // }
         * updateh(imbalance.left); updateh(imbalance.right); updateh(imbalance); return
         * new_node; }
         * if(imbalance.parent.right==imbalance&&imbalance.parent.parent.right==
         * imbalance.parent){ leftrotate(imbalance.parent.parent);
         * updateh(imbalance.parent.left); updateh(imbalance.parent.right);
         * updateh(imbalance.parent); return new_node; }
         * if(imbalance.parent.right==imbalance&&imbalance.parent.parent.left==imbalance
         * .parent){ rightrotate(imbalance.parent); leftrotate(imbalance.parent);
         * updateh(imbalance.left); updateh(imbalance.right); updateh(imbalance); return
         * new_node; } return new_node;
         * 
         */

    }

    public boolean Delete(Dictionary e) {
        AVLTree current = this;
        if (current.key == e.key && current.address == e.address && current.size == e.size && !current.isSentinal()) {
            if (current.successor() != null) {
                AVLTree temp = current.delHelperTWS();
                updateh(temp);
                checkbalance(temp);
                return true;
            }
            if (current.predesessor() != null) {
                AVLTree temp = current.delHelperTWP();
                updateh(temp);
                checkbalance(temp);
                return true;
            } // if both are null then its root node and no other nodes in the tree. hence
              // make it sentinel and delete sentinel
            current.address = -1;
            current.key = -1;
            current.size = -1;
            current.parent = null;
            return true;
        }
        int flag = 0;
        if (this != current.getSent()) {
            flag = 1;
        }
        // BUG IN BSTREE
        current = current.getSent();
        if (current.right == null) {// empty tree
            return false;
        }
        current = current.right;
        while (current != null) {
            // System.out.println("-----144");
            if (current.key == e.key && current.address == e.address && current.size == e.size) {
                AVLTree temp;
                if (flag == 1) {
                    temp = current.delHelper(this);
                } else {
                    temp = current.delHelper(null);
                }
                // System.out.println("Printting This right address "+current.right.address+"
                // key"+current.right.key);
                if (temp.isSentinal())
                    return true;
                // System.out.println(temp.address);
                updateh(temp);
                checkbalance(temp);
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

    public boolean sanity() {
        if (!this.rootforsanity()) {// checks for path to the root
            return false;
        }
        AVLTree current = this.getSent();
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
        AVLTree min = new AVLTree(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        AVLTree max = new AVLTree(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

        return (pccheck(current)) && (bstproperty(current, min, max)) && isHeight(current.getSent().right);
        // pccheck ensures acyclic so that bstproperty can work after it
    }

    // private boolean hbalance(){
    // /*
    // 1. checks negative heights
    // 2. checks leaf node with height 1
    // 3. checks max difference lies between 0 and 1
    // 4. returns left height balance and right height balance
    // */
    // if(getheight(this)<0){
    // return false;
    // }
    // if(this.left==null&&this.right==null&&getheight(this)==0){
    // return true;
    // }
    // if(this.left==null&&this.right!=null&&this.right.height<=1){
    // return this.right.hbalance();
    // }
    // if(this.right==null&&this.left!=null&&this.left.height<=1){
    // return this.left.hbalance();
    // }
    // if(this.left!=null&this.right!=null){
    // if(this.left.height>=this.right.height&&this.left.height-this.right.height<=1){
    // return this.right.hbalance()&&this.left.hbalance();
    // }
    // if(this.left.height<this.right.height&&this.right.height-this.left.height<=1){
    // return this.right.hbalance()&&this.left.hbalance();
    // }
    // }
    // return false;
    // /* Alternate code - test it
    // if(getheight(this.left)>=getheight(this.right)&&getheight(this.left)-getheight(this.right)<=1){
    // return true;
    // }
    // if(getheight(this.left)<getheight(this.right)&&getheight(this.right)-getheight(this.left)<=1){
    // return true;
    // }
    // return false;
    // */
    // }
    private boolean isHeight(AVLTree node) {

        if (node == null) {
            return true;
        }
        if (node.left == null && node.right == null) {
            if (node.height == 0) {
                return true;
            }
            return false;
        }
        if (node.height == 1 + max(getheight(node.left), getheight(node.right))) {
            if (getheight(node.left) >= getheight(node.right) && getheight(node.left) - getheight(node.right) <= 1) {
                return isHeight(node.left) && isHeight(node.right);

            }
            if (getheight(node.left) < getheight(node.right) && getheight(node.right) - getheight(node.left) <= 1) {
                return isHeight(node.left) && isHeight(node.right);

            }
            System.out.println(getheight(node.left) + " " + getheight(node) + " " + getheight(node.right));
            return false;
        }
        return false;
    }

    private boolean rootforsanity() {// floyds like algo
        if (this.parent == null) {
            return true;
        }
        AVLTree p1 = this;
        AVLTree p2 = this;
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

    private boolean pccheck(AVLTree root) {// checks if everyone follows child parent relationship

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

    private boolean bstproperty(AVLTree current, AVLTree min, AVLTree max) {// checks for bst

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

    private AVLTree getSent() {
        AVLTree current = this;
        while (!current.isSentinal()) {
            current = current.parent;
        }
        return current;
    }

    private boolean isSentinal() {
        if (this.parent == null) {
            return true;
        }
        return false;
    }

    private int getheight(AVLTree node) {
        if (node != null) {
            return node.height;
        }
        return -1;
    }

    private void checkbalance(AVLTree node) {
        // System.out.println("check balancing add "+node.address+" key "+node.key);
        if (getheight(node.left) - getheight(node.right) > 1 || getheight(node.left) - getheight(node.right) < -1) {
            rebalance(node);
        }
        if (node.parent.isSentinal())
            return;
        checkbalance(node.parent);
    }

    private void rebalance(AVLTree node) {
        // System.out.println("rebalancing add "+node.address+" key "+node.key);
        if (getheight(node.left) - getheight(node.right) > 1) {
            if (getheight(node.left.left) >= getheight(node.left.right)) {
                node = rightrotate(node);
            } else {
                node.left = leftrotate(node.left);
                node = rightrotate(node);

            }
        } else {
            if (getheight(node.right.right) >= getheight(node.right.left)) {
                // System.out.println("this is happerning");
                node = leftrotate(node);
            } else {
                node.right = rightrotate(node.right);
                node = leftrotate(node);
            }
        }

        return;
    }
    // private AVLTree firstub(AVLTree node){
    // if(node.parent.parent.isSentinal()||node.parent.parent==null){
    // return null;
    // }
    // if(abs(getheight(node.parent.parent.right)-getheight(node.parent.parent.left))<=1){
    // return firstub(node.parent);
    // }
    // return node;

    // }
    private int abs(int a) {
        if (a > 0) {
            return a;
        }
        return -1 * a;
    }

    private void updateh(AVLTree node) {
        // genereally called on parent of inserted node
        if (node == null || node.isSentinal()) {
            return;
        }
        // if(getheight(node)==max(getheight(node.left),getheight(node.right))+1){
        // return;
        // }
        node.height = max(getheight(node.left), getheight(node.right)) + 1;
        updateh(node.parent);
        return;
    }

    private int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    private AVLTree rightrotate(AVLTree node) {
        // if(node.parent.right==node){
        // node.parent.right=node.left;
        // node.left.parent=node.parent;
        // }
        // else{
        // node.parent.left=node.left;
        // node.left.parent=node.parent;
        // }
        // node.parent=node.left;
        // if(node.left.right!=null){
        // node.left=node.left.right;
        // node.left.parent=node;
        // node.parent.right=node;
        // return ;
        // }
        // node.left=null;
        // node.parent.right=node;
        AVLTree temp = node.left;
        if (temp.right != null) {
            temp.right.parent = node;
        }
        node.left = temp.right;
        temp.right = node;
        if (node.parent.left == node) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.parent = node.parent;
        node.parent = temp;
        updateh(node);
        return temp;
    }

    private AVLTree leftrotate(AVLTree node) {
        // System.out.println("Left rotae called on addres "+node.address);
        // if(node.parent.right==node){
        // node.parent.right=node.right;
        // node.right.parent=node.parent;
        // }
        // else{
        // node.parent.left=node.right;
        // node.right.parent=node.parent;
        // }
        // node.parent=node.right;
        // if(node.right.left!=null){
        // node.right=node.right.left;
        // node.right.parent=node;
        // node.parent.left=node;
        // return;
        // }
        // node.right=null;
        // node.parent.left=node;
        AVLTree temp = node.right;
        if (temp.left != null) {
            temp.left.parent = node;
        }
        node.right = temp.left;
        temp.left = node;
        if (node.parent.left == node) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.parent = node.parent;
        node.parent = temp;
        updateh(node);
        return temp;

    }

    // private AVLTree leftright(AVLTree node){
    // leftrotate(node.left);
    // return rightrotate(node);
    // }
    // private AVLTree rightleft(AVLTree node){
    // rightrotate(node.right);
    // return leftrotate(node);
    // }
    private AVLTree delHelper(AVLTree original) {
        AVLTree current = this;
        if (current.right == null && current.left == null) {
            if (current.parent.right == current) {
                current.parent.right = null;
                return current.parent;
            }
            current.parent.left = null;
            return current.parent;
        }
        // garbage collection will remove it as no pointer to the node exists
        if (current.left == null && current.right != null) {
            if (current.parent.right == current) {
                current.right.parent = current.parent;
                current.parent.right = current.right;
                return current.parent;
            }
            current.right.parent = current.parent;
            current.parent.left = current.right;
            return current.parent;
        }
        if (current.right == null && current.left != null) {
            if (current.parent.left == current) {
                current.left.parent = current.parent;
                current.parent.left = current.left;
                return current.parent;
            }
            current.left.parent = current.parent;
            current.parent.right = current.left;
            return current.parent;
        }
        if (current.right != null && current.left != null) {
            AVLTree suc = current.successor();
            // System.out.println(suc.address);
            if (original != null && suc == original) {
                suc = current.predesessor();
            }
            this.address = suc.address;
            this.key = suc.key;
            this.size = suc.size;
            return suc.delHelper(null);

        }
        return this;
    }

    private AVLTree delHelperTWS() {
        AVLTree current = this;
        AVLTree suc = current.successor();
        this.address = suc.address;
        this.key = suc.key;
        this.size = suc.size;
        return suc.delHelper(null);

    }

    private AVLTree delHelperTWP() {
        AVLTree current = this;
        AVLTree suc = current.predesessor();
        this.address = suc.address;
        this.key = suc.key;
        this.size = suc.size;
        return suc.delHelper(null);

    }

    private AVLTree successor() {
        AVLTree current = this;
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

    private AVLTree predesessor() {
        AVLTree current = this;
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

    public AVLTree getFirst() {
        AVLTree current = this.getSent();
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

    public AVLTree getNext() {
        return this.successor();
    }

    private AVLTree findTrue(int key) {
        AVLTree current = this;
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

    private AVLTree findFalse(int key) {
        AVLTree current = this;
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

    public AVLTree Find(int key, boolean exact) {
        AVLTree current = this;
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
}
