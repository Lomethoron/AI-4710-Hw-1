import java.util.*;

public class Tree{
	public Tree() {
		
	}
	public TreeNode treeify(String exp)
   {
      if(!(exp.contains("|") || exp.contains("&") || exp.contains("!")))
         return new TreeNode(exp);
      if(exp.substring(0,1).equals("("))
      {
         int tok = 0;
         char[] cha = exp.toCharArray();
         for(int x=cha.length-1; x>=0; x--)
         {
            if(cha[x]==')')
            {
               tok = x;
               x=-1;;
            }
         }
         exp = exp.substring(1, tok);
      }
      ArrayList<String> token = new ArrayList<String>();
      char[] ch = exp.toCharArray();
      int parenthesis = 0;
      int or = -1;
      int and = -1;
      int not = -1;
      int mid = 0;
      String value = "";
      for(int x=0; x<ch.length; x++)
      {
         if(or != -1)
         {
            mid = or;
            break;
         }
         if(or != -1 && and != -1 && not != -1)
            break;
         if(ch[x]=='(')
            parenthesis++;
         else if(parenthesis>0 && ch[x]==')')
            parenthesis--;
         else if(ch[x]=='|' && parenthesis==0)
            or = x;
         else if(ch[x]=='&' && and==-1 && parenthesis==0)
            and = x;
         else if(ch[x]=='!' && not==-1 && parenthesis==0)
            not = x;
         else
         {}
      }
      
      if(or == -1 && and == -1)
      {
         mid = not;
         value = "!";
      }
      else if(or==-1)
      {
         mid = and;
         value = "&";
      }
      else
         value = "|";
      if(mid==0)
      {
         String left = exp.substring(1);
         return new TreeNode(value, treeify(left));
      }
      String left = exp.substring(0, mid);
      String right = exp.substring(mid+1, exp.length());
      return new TreeNode(value, treeify(left), treeify(right));
   }
   
   public boolean tokenize(String exp)
   {
      if(exp.contains("|") || exp.contains("&") || exp.contains("!"))
         return solveTree(treeify(exp));
      return solveTree(new TreeNode(exp));
   }   
  
   public boolean solveTree(TreeNode root)
   {
      char letter = ("" + root.getValue()).charAt(0);
      if(Character.isLetter(letter))
         return trueOrFalse("" + root.getValue());
      else if("|".equals("" + root.getValue()))
         return solveTree(root.getLeft()) || solveTree(root.getRight());
      else if("&".equals("" + root.getValue()))
         return solveTree(root.getLeft()) && solveTree(root.getRight());
      else
         return !solveTree(root.getSingle());    
   }
   
   public boolean trueOrFalse(String symbol)
   {
      String upper = symbol.toUpperCase();
      if(upper.equals(symbol))
         return true;
      return false;
   }

}