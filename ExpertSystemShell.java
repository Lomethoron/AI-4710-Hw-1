import java.util.*;

public class ExpertSystemShell {
	
	private HashMap<String, Variable> knownFacts;
	
	public ExpertSystemShell() {
		knownFacts = new HashMap<String, Variable>();
	}
	
	//determines which command to run
	private boolean exec(String userCommand) { //this may end up returning a void
		String[] parsedUserCommand = userCommand.split("\\W");
		//switch or if/else list of each command
		if(parsedUserCommand[0].matches("quit|q|exit|ex|e")) {
			System.exit(0);
		}
		else if (parsedUserCommand[0].equalsIgnoreCase("teach")) {
			//first teach command, for some reason I elected not to use the method below here
			if(userCommand.charAt(6) == '-'){
				boolean isRoot = false;
				if(userCommand.charAt(7) == 'r' || userCommand.charAt(7) == 'R') {
					isRoot = true;
				}
				String varAndExpressionSubstring = userCommand.substring(9); //trim off the front of the string
				String[] varAndExpressionSplit = varAndExpressionSubstring.split(" = ");
				varAndExpressionSplit[1] = varAndExpressionSplit[1].substring(1,varAndExpressionSplit[1].length()-1); //remove the quotations
				System.out.println("a"+varAndExpressionSplit[0]+"a");
				
				Variable newVar = new Variable(isRoot, varAndExpressionSplit[0], varAndExpressionSplit[1], false);
				knownFacts.put(varAndExpressionSplit[0], newVar);
			}
			//second teach command
			else if(userCommand.contains("=")){
				String varAndExpressionSubstring = userCommand.substring(6);
				//System.out.println(varAndExpressionSubstring);
				String[] varAndExpressionSplit = varAndExpressionSubstring.split(" = ");
				//System.out.println("a"+varAndExpressionSplit[1]+"a");
				teach(varAndExpressionSplit[0], Boolean.parseBoolean(varAndExpressionSplit[1]));
				
				
			}
			//final teach command
			else {
				
			}
		}	
		else if (parsedUserCommand[0].equalsIgnoreCase("list")) {
			list();
		}
		else if (parsedUserCommand[0].equalsIgnoreCase("learn")) {
			learn();
		}
		else if (parsedUserCommand[0].equalsIgnoreCase("query")) {
			query(parsedUserCommand[1]);
		}
		return true;
	}
	
	/**
	 *This command teaches the system a new variable of the form variable = string.
	 */
	private boolean teach(String arg, String var, String expression){
		boolean isRoot = false;
		if (arg.equalsIgnoreCase("r")) {
			isRoot = true;
		}
		//Variable newVar = new Variable(isRoot, var, expression);
		//knownFacts.put(newVar, false); //need to look up specified behaviour here
		return true;
	}
	
	/**
	 *Teaches the system that a defined root variable has been observed to be true(or false). 
	 */
	private boolean teach(String var, boolean truth) {
		System.out.println(truth);
		Variable editVar = knownFacts.get(var);
		editVar.setState(truth);
		knownFacts.put(var, editVar);
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
		for(Map.Entry<String, Variable> entry: knownFacts.entrySet()){
			System.out.println("The value of: "+entry.getKey()+" is: "+entry.getValue().getExpression()+" and is currently: "+entry.getValue().getState());
		}
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
		while(true){
			String userCommand = in.nextLine();
			//System.out.println("Command length: "+parsedUserCommand.length+" First token: "+parsedUserCommand[0]);
			program.exec(userCommand);
		}
	}
}
