import java.util.*;

class TreeNode
{
   private Object value; 
   private ArrayList<TreeNode> children;
   
   /**
	*Default Constructor
	*/
   public TreeNode() {
	   value = null;
	   children = new ArrayList<TreeNode>();
   }
   
   /**
    * Constructor from expression alone
	*/
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      children = new ArrayList<TreeNode>(); 
   }
   
   /**
	*Constructor from expression and children
	*/
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      children.add(initLeft);
      children.add(initRight);
   }
   
   /**
	* Constructor from expression and single children
	*/
   public TreeNode(Object initValue, TreeNode initSingle)
   { 
      value = initValue; 
      children.add(initSingle);
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return children.get(0); 
   }
   
   public TreeNode getRight() 
   { 
      return children.get(1); 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      children.set(0, theNewLeft);
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      children.set(1, theNewRight);
   }
}