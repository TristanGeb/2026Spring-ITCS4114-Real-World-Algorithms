/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project3;

import bridges.connect.Bridges;
import java.nio.file.Path;
import java.nio.file.Paths;
import bridges.base.AVLTreeElement;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Trist
 */
public class Project3 {

    public static int getHeightAtNode(AVLTreeElement<Integer, Integer> node) {
        if (node == null) {
            return -1;
        }
        AVLTreeElement<Integer, Integer> left = node.getLeft();
        AVLTreeElement<Integer, Integer> right = node.getRight();
        if (left == null) {
            if (right == null) {
                return 0;//null,null
            } else {
                return getHeightAtNode(right) + 1;//null,present
            }
        } else {
            if (right == null) {
                return getHeightAtNode(left) + 1;//present,null
            } else {//prsent,present
                int leftHeight = getHeightAtNode(left);
                int rightHeight = getHeightAtNode(right);
                if (leftHeight >= rightHeight) {
                    return leftHeight + 1;
                } else {
                    return rightHeight + 1;
                }
            }
        }
    }

    public static int getBalanceFactorAtNode(AVLTreeElement<Integer, Integer> node) {
        if (node == null) {
            return 0;
        }
        return getHeightAtNode(node.getRight()) - getHeightAtNode(node.getLeft());
    }

    public static final boolean USEEXPANDEDLABAL = false;//flase= labable will be balance only. true= number and balance and height
    public static final boolean WRITETOFILE = false;//true to make log file- is imperfect since i only started adding logs halfway thorugh
    public static Path file = null;
    public static int tabs = 0;
    public static int urlnum = 13;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        if (WRITETOFILE) {
            file=Paths.get("output.txt");
            Files.deleteIfExists(file);
            Files.createFile(file);
        }
        int[] tree1keys = {5, 10, 13, 15, 3, 7};
        int[] tree2keys = {13, 7, 5, 3, 10, 15};
        int[] tree3keys = {5, 3, 13, 10, 7, 15};
        int[] tree4keys = {13, 5, 3, 7, 10, 15};
        int[] tree5keys = {41, 67, 34, 0, 69, 24, 78, 58, 62, 64, 5, 45, 81, 27, 61, 91, 95, 42, 36, 4, 2, 53, 92, 82, 21, 16, 18, 47, 26, 71, 38, 12, 99, 35, 94, 3, 11, 22, 33, 73, 68};
        /*AVLTreeElement<Integer, Integer> tree0Root = createAVLTree(tree0keys);
        setLabels(tree0Root);*/
        AVLTreeElement<Integer, Integer> treeroot;
        String finalOutput = "";
        writeFile("\n\ntree1");
        tabs++;
        treeroot = createBinaryTree(tree1keys);
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 2 Part 1 Original Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 2 Part 1 Tree Original", treeroot);
        writeFile(tabs, "rotated tree");
        //treeroot = createAVLTree(tree1keys);
        treeroot = rotateBinaryTree(treeroot);
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 2 Part 1 Left Rotation Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 2 Part 1 Tree Rotated Left", treeroot);
        tabs--;

        writeFile("\n\ntree2");
        tabs++;
        treeroot = createBinaryTree(tree2keys);
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 2 Part 2 Original Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 2 Part 2 Tree Original", treeroot);
        writeFile(tabs, "rotated tree");
        //treeroot = createAVLTree(tree2keys);
        treeroot = rotateBinaryTree(treeroot);
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 2 Part 2 Right Rotation Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 2 Part 2 Tree Rotated Right", treeroot);
        tabs--;

        writeFile("\n\ntree3");
        tabs++;
        treeroot = createBinaryTree(tree3keys);
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 2 Part 3 Original Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 2 Part 3 Tree Original", treeroot);
        writeFile(tabs, "rotated tree");
        //treeroot = createAVLTree(tree3keys);
        treeroot = rotateBinaryTree(treeroot);
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 2 Part 3 Right-Left Rotation Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 2 Part 3 Tree Rotated Right-Left", treeroot);
        tabs--;

        writeFile("\n\ntree4");
        tabs++;
        treeroot = createBinaryTree(tree4keys);
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 2 Part 4 Original Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 2 Part 4 Tree Original", treeroot);
        writeFile(tabs, "rotated tree");
        //treeroot = createAVLTree(tree4keys);
        treeroot = rotateBinaryTree(treeroot);
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 2 Part 4 Left-Right Rotation Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 2 Part 4 Tree Rotated Left-Right", treeroot);
        tabs--;

        writeFile("\n\ntree5");
        tabs++;
        treeroot = createBinaryTree(tree5keys);
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 3 Binary Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 3 Tree Binary", treeroot);
        writeFile(tabs, "rotated tree");
        int reservedUrlNum = urlnum;
        urlnum++;
        treeroot = createAVLTree(tree5keys);
        urlnum = reservedUrlNum;
        finalOutput = finalOutput + "\n" + urlnum + " is Url id for Task 3 AVL Tree";
        finalOutput=finalOutput+"\nhttp://assignments.bridgesuncc.org/assignments/"+urlnum+"/tristancat101";
        writeToBridge("Project 3 Task 3 AVL Tree", treeroot);
        tabs--;
        System.out.println(finalOutput);
        writeFile(tabs, "DONE FINAL");
    }

    public static void writeToBridge(String Title, AVLTreeElement<Integer, Integer> rootOfTree) throws Exception {
        writeFile(tabs, "writeing to File id=" + urlnum + "");
        setLabels(rootOfTree);
        Bridges bridge = new Bridges(urlnum, "tristancat101", "1276738718144");
        urlnum++;
        bridge.setTitle(Title);
        bridge.setDataStructure(rootOfTree);
        bridge.visualize();
    }

    public static AVLTreeElement<Integer, Integer> createAVLTree(int keysList[]) throws Exception {
        if (keysList.length == 0) {
            return null;
        }
        if (keysList.length == 1) {
            return new AVLTreeElement<>(keysList[0], 0);
        }
        AVLTreeElement<Integer, Integer> root = new AVLTreeElement<>(keysList[0], 0);
        int[] keysToAdd = Arrays.copyOfRange(keysList, 1, keysList.length);

        for (int key : keysToAdd) {
            tabs++;
            ArrayList<AVLTreeElement<Integer, Integer>> nodesToCheck = new ArrayList<>();
            boolean continueFlag = true;
            AVLTreeElement<Integer, Integer> referenceNode = root;
            while (continueFlag) {
                nodesToCheck.add(referenceNode);
                if (key >= referenceNode.getKey()) {//new key is equal or greater
                    if (referenceNode.getRight() == null) {//if no right node
                        referenceNode.setRight(new AVLTreeElement<>(key, 0));
                        continueFlag = false;//endloop
                    } else {//if right node present

                        referenceNode = referenceNode.getRight();
                    }
                } else {//new key is less
                    if (referenceNode.getLeft() == null) {//no left node
                        referenceNode.setLeft(new AVLTreeElement<>(key, 0));
                        continueFlag = false;//endLoop
                    } else {//has leftnode
                        referenceNode = referenceNode.getLeft();
                    }
                }
            }
            writeFile(tabs, "now checking tree for imbalance");
            while ((!nodesToCheck.isEmpty())/*&&(getBalanceFactorAtNode(nodeToCheck.getLast())=0)*/) {
                AVLTreeElement<Integer, Integer> checkingNode = nodesToCheck.removeLast();
                AVLTreeElement<Integer, Integer> temp;
                writeFile(tabs, "checking node =" + checkingNode.getKey() + "");
                if (getBalanceFactorAtNode(checkingNode) >= 2) {
                    if (getBalanceFactorAtNode(checkingNode.getRight()) >= 1) {//Left Rotation
                        writeFile(tabs, "Left Rotation");
                        temp = checkingNode.getRight();
                        checkingNode.setRight(temp.getLeft());
                        temp.setLeft(checkingNode);
                        //update parents path
                        if (nodesToCheck.isEmpty()) {//is root being changed
                            root = temp;
                        } else if (nodesToCheck.getLast().getLeft() == checkingNode) {//changed is a left child
                            nodesToCheck.getLast().setLeft(temp);
                        } else if (nodesToCheck.getLast().getRight() == checkingNode) {//changed is a right child
                            nodesToCheck.getLast().setRight(temp);
                        } else {
                            //error
                            writeFile(tabs, "ERROR");
                            System.out.println("error");
                        }
                    } else if (getBalanceFactorAtNode(checkingNode.getRight()) <= -1) {//Right-Left Rotation
                        writeFile(tabs, "Right Left Rotation");
                        //first right rotation
                        temp = checkingNode.getRight().getLeft();
                        checkingNode.getRight().setLeft(temp.getRight());
                        temp.setRight(checkingNode.getRight());
                        checkingNode.setRight(temp);//updating parent(current node) path
                        //then left rotation
                        temp = checkingNode.getRight();
                        checkingNode.setRight(temp.getLeft());
                        temp.setLeft(checkingNode);
                        //update parents path
                        if (nodesToCheck.isEmpty()) {//is root being changed
                            root = temp;
                        } else if (nodesToCheck.getLast().getLeft() == checkingNode) {//changed is a left child
                            nodesToCheck.getLast().setLeft(temp);
                        } else if (nodesToCheck.getLast().getRight() == checkingNode) {//changed is a right child
                            nodesToCheck.getLast().setRight(temp);
                        } else {
                            //error
                            writeFile(tabs, "ERROR");
                            System.out.println("error");
                        }
                    } else {
                        //error
                        writeFile(tabs, "ERROR");
                        System.out.println("error");
                    }
                } else if (getBalanceFactorAtNode(checkingNode) <= -2) {
                    if (getBalanceFactorAtNode(checkingNode.getLeft()) >= 1) {//Left-Right Rotation
                        writeFile(tabs, "left Right Rotation");
                        //first left Rotation
                        temp = checkingNode.getLeft().getRight();
                        checkingNode.getLeft().setRight(temp.getLeft());
                        temp.setLeft(checkingNode.getLeft());
                        checkingNode.setLeft(temp);//updating parent(current node)path
                        //then right rotation
                        temp = checkingNode.getLeft();
                        checkingNode.setLeft(temp.getRight());
                        temp.setRight(checkingNode);
                        //update parents path
                        if (nodesToCheck.isEmpty()) {//is root being changed
                            root = temp;
                        } else if (nodesToCheck.getLast().getLeft() == checkingNode) {//changed is a left child
                            nodesToCheck.getLast().setLeft(temp);
                        } else if (nodesToCheck.getLast().getRight() == checkingNode) {//changed is a right child
                            nodesToCheck.getLast().setRight(temp);
                        } else {
                            //error
                            writeFile(tabs, "ERROR");
                            System.out.println("error");
                        }
                    } else if (getBalanceFactorAtNode(checkingNode.getLeft()) <= -1) {//Right Rotation
                        writeFile(tabs, "Right Rotation");
                        temp = checkingNode.getLeft();
                        checkingNode.setLeft(temp.getRight());
                        temp.setRight(checkingNode);
                        //update parents path
                        if (nodesToCheck.isEmpty()) {//is root being changed
                            root = temp;
                        } else if (nodesToCheck.getLast().getLeft() == checkingNode) {//changed is a left child
                            nodesToCheck.getLast().setLeft(temp);
                        } else if (nodesToCheck.getLast().getRight() == checkingNode) {//changed is a right child
                            nodesToCheck.getLast().setRight(temp);
                        } else {
                            //error
                            writeFile(tabs, "ERROR");
                            System.out.println("error");
                        }
                    } else {
                        //error
                        writeFile(tabs, "ERROR");
                        System.out.println("error");
                    }
                }
            }
            tabs--;
        }
        return root;
    }

    public static AVLTreeElement<Integer, Integer> createBinaryTree(int keysList[]) {
        if (keysList.length == 0) {
            return null;
        }
        if (keysList.length == 1) {
            return new AVLTreeElement<>(keysList[0], 0);
        }
        AVLTreeElement<Integer, Integer> root = new AVLTreeElement<>(keysList[0], 0);
        int[] keysToAdd = Arrays.copyOfRange(keysList, 1, keysList.length);

        for (int key : keysToAdd) {
            boolean continueFlag = true;
            AVLTreeElement<Integer, Integer> referenceNode = root;
            while (continueFlag) {
                if (key >= referenceNode.getKey()) {//new key is equal or greater
                    if (referenceNode.getRight() == null) {//if no right node
                        referenceNode.setRight(new AVLTreeElement<>(key, 0));
                        continueFlag = false;//endloop
                    } else {//if right node present

                        referenceNode = referenceNode.getRight();
                    }
                } else {//new key is less
                    if (referenceNode.getLeft() == null) {//no left node
                        referenceNode.setLeft(new AVLTreeElement<>(key, 0));
                        continueFlag = false;//endLoop
                    } else {//has leftnode
                        referenceNode = referenceNode.getLeft();
                    }
                }
            }
        }
        return root;

    }

    /**
     * only works on the root passed
     *
     * @param root the root of the tree.
     * @return the new root as a AVLTreeElement<Integer,Integer>
     * @throws Exception just io for outputing to txt file in writeFile function
     */
    public static AVLTreeElement<Integer, Integer> rotateBinaryTree(AVLTreeElement<Integer, Integer> root) throws Exception {
        writeFile(tabs, "rotating Binary tree");
        tabs++;
        AVLTreeElement<Integer, Integer> checkingNode = root;
        AVLTreeElement<Integer, Integer> temp;
        writeFile(tabs, "checking node =" + checkingNode.getKey() + "");
        if (getBalanceFactorAtNode(checkingNode) >= 2) {
            if (getBalanceFactorAtNode(checkingNode.getRight()) >= 1) {//Left Rotation
                writeFile(tabs, "Left Rotation");
                temp = checkingNode.getRight();
                checkingNode.setRight(temp.getLeft());
                temp.setLeft(checkingNode);
                //update parents path
                root = temp;

            } else if (getBalanceFactorAtNode(checkingNode.getRight()) <= -1) {//Right-Left Rotation
                writeFile(tabs, "Right Left Rotation");
                //first right rotation
                temp = checkingNode.getRight().getLeft();
                checkingNode.getRight().setLeft(temp.getRight());
                temp.setRight(checkingNode.getRight());
                checkingNode.setRight(temp);//updating parent(current node) path
                //then left rotation
                temp = checkingNode.getRight();
                checkingNode.setRight(temp.getLeft());
                temp.setLeft(checkingNode);
                //update parents path
                root = temp;

            } else {
                //error
                writeFile(tabs, "ERROR");
                System.out.println("error");
            }
        } else if (getBalanceFactorAtNode(checkingNode) <= -2) {
            if (getBalanceFactorAtNode(checkingNode.getLeft()) >= 1) {//Left-Right Rotation
                writeFile(tabs, "left Right Rotation");
                //first left Rotation
                temp = checkingNode.getLeft().getRight();
                checkingNode.getLeft().setRight(temp.getLeft());
                temp.setLeft(checkingNode.getLeft());
                checkingNode.setLeft(temp);//updating parent(current node)path
                //then right rotation
                temp = checkingNode.getLeft();
                checkingNode.setLeft(temp.getRight());
                temp.setRight(checkingNode);
                //update parents path
                root = temp;
            } else if (getBalanceFactorAtNode(checkingNode.getLeft()) <= -1) {//Right Rotation
                writeFile(tabs, "Right Rotation");
                temp = checkingNode.getLeft();
                checkingNode.setLeft(temp.getRight());
                temp.setRight(checkingNode);
                //update parents path
                root = temp;
            } else {
                //error
                writeFile(tabs, "ERROR");
                System.out.println("error");
            }
        }
        tabs--;
        writeFile(tabs, "done rotating Binary Tree");
        return root;
    }

    /**
     *
     * @param node the node to start with will set this node and all its
     * children. also updates all height and balance factor values
     */
    public static void setLabels(AVLTreeElement<Integer, Integer> node) {
        String statment;
        if (USEEXPANDEDLABAL) {
            statment = node.getKey() + "=" + Integer.toString(getBalanceFactorAtNode(node)) + "," + Integer.toString(getHeightAtNode(node));
        } else {
            statment = Integer.toString(getBalanceFactorAtNode(node));
        }
        node.setLabel(statment);
        node.setBalanceFactor(getBalanceFactorAtNode(node));
        node.setHeight(getHeightAtNode(node));
        if (node.getRight() != null) {
            setLabels(node.getRight());
        }
        if (node.getLeft() != null) {
            setLabels(node.getLeft());
        }
    }

    public static void writeFile(String str) throws Exception {
        if (WRITETOFILE) {
            Files.writeString(file, str + "\n", StandardOpenOption.APPEND);
        }
    }

    public static void writeFile(int tabAmount, String str) throws Exception {
        if (WRITETOFILE) {
            for (int x = 0; x < tabAmount; x++) {
                str = "\t" + str;
            }
            Files.writeString(file, str + "\n", StandardOpenOption.APPEND);
        }
    }
}
