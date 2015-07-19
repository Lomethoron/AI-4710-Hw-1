class TreeNode
{
   private Object value; 
   private ArrayList<TreeNode> children;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      children = new ArrayList<TreeNode>(); 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      children = new ArrayList<TreeNode>();
      value = initValue; 
      children.add(initLeft);
      children.add(initRight);
   }
   
   public TreeNode(Object initValue, TreeNode initSingle)
   { 
      children = new ArrayList<TreeNode>();
      value = initValue; 
      children.add(initSingle);
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      if(children.size()>0)
         return children.get(0); 
      return null;
   }
   
   public TreeNode getRight() 
   { 
      if(children.size()>1)
         return children.get(1); 
      return null;
   }
   
   public TreeNode getSingle()
   {
      if(children.size()>0)
         return children.get(0);
      return null;
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      if(children.size()>0)
         children.set(0, theNewLeft);
      children.add(theNewLeft);
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      if(children.size()>0)
         children.set(1, theNewRight);
      children.add(theNewRight);
   }
}