import java.util.*;

public class ExpertSystemShell {
	
	private HashMap<Variable, Boolean> knownFacts;
	
	public ExpertSystemShell() {
		knownFacts = new HashMap<Variable, Boolean>();
	}
	
	//determines which command to run
	private boolean exec(String userCommand) { //this may end up returning a void
		//switch or if/else list of each command
		if(userCommand.matches("quit|q|exit|ex|e")) {
			System.exit(0);
		}
		return true;
	}
	
	/**
	 *This command teaches the system a new variable of the form variable = string.
	 */
	private boolean teach(String arg, String var, String expression){
		return true;
	}
	
	/**
	 *Teaches the system that a defined root variable has been observed to be true(or false). 
	 */
	private boolean teach(String var, boolean truth) {
		return true;
	}
	
	/**
	 *Teaches  the system  a  new  rule.
	 */
	private boolean teach(String expression, String var) {
		return true;
	}
	
	/**
	 * Lists out all of the fact and rules currently known by the system.
	 */
	private String list() {
		return "";
	}
	
	/**
	 *Uses forward chaining to apply all of the rules of the system to 
	 *the facts of the system to create newly formed facts.
	 */
	private String learn() {
		return "";
	}
	
	/**
	 *Returns true if an only if the given expression is
	 *true given the rules and facts within the system.
	 */
	private String query(String exp){
		return "";
	}
	
	public static void main(String args[]){
		ExpertSystemShell program = new ExpertSystemShell();
		//get user commands
		Scanner in = new Scanner(System.in);
		while(true){ //would normally have an exit condition here, but I see none listed in the assignment
			String userCommand = in.nextLine();
			program.exec(userCommand);
		}
	}
}


class TreeNode
{
   private Object value; 
   private ArrayList<TreeNode> children;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      ArrayList<TreeNode> children = new ArrayList<TreeNode>(); 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      children.add(initLeft);
      children.add(initRight);
   }
   
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
