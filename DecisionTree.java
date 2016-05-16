import java.util.*;

/**
 * A sublass of BinaryTree which plays a simple guessing game
 *
 * @author Silvana Saca
 * @version 16 Nov 2015
 */

public class DecisionTree extends BinaryTree<String>{
    
    /** Constructor creates a leaf node */
    public DecisionTree(String data){
        super(data);
    }


    /** Constructor creates a branch node */
    public DecisionTree( String data, DecisionTree left, DecisionTree right) {
        super(data, left, right);
    }

    /** Manipulator takes only Decision Tree node */
    public void setLeft(DecisionTree left){
        super.setLeft(left);
    }

    /** Override unwanted parent method */
    public void setLeft(BinaryTree<String> left){
        throw new UnsupportedOperationException();
    }

    /** Accessor returns proper type */
    public DecisionTree getLeft(){
        return (DecisionTree)super.getLeft();
    }

    /** Manipulator takes only Decision Tree node */
    public void setRight(DecisionTree right){
        super.setRight(right);
    }

    /** Override unwanted parent method */
    public void setRight(BinaryTree<String> right){
        throw new UnsupportedOperationException();
    }

    /** Accessor returns proper type */
    public DecisionTree getRight(){
        return (DecisionTree)super.getRight();
    }

    /** 
     * followPath method takes a string like YNNYNNYYY and returns node
     * which corresponds to an animal
     *
     * @param String nodes
     * @return DecisionTree animal 
     */
    public DecisionTree followPath(String path){
        DecisionTree animal= null;
        if (path.length()== 0){
            animal= this;
        } else {
            if (path.charAt(0)== 'y') {

                animal=getLeft().followPath(path.substring(1));
                
            } else if (path.charAt(0)== 'n') {

                animal= getRight().followPath(path.substring(1));
            }
        }
        return animal;
        
    }

    /** 
     * createTree method creates a tree using a given path and data
     *
     * @param String path, String data
     * @return DecisionTree tree
     */
    public DecisionTree createTree(String path, String data){
        if (path.length()== 0){
            
            setData(data);
        } else {
            if (path.charAt(0)== 'Y'){
                if (getLeft() == null) {
                    setLeft( new DecisionTree(null));
                }
                getLeft().createTree(path.substring(1), data);
                
            }

            if (path.charAt(0)== 'N'){
                if (getRight() == null) {
                    setRight( new DecisionTree(null));
                }
                getRight().createTree(path.substring(1), data);
            }
        }

        return this;
    }
    
    
}
