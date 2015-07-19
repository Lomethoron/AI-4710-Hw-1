import java.util.*;

public class ExpertSystemShell {
	
	private HashMap<Variable, Boolean> knownFacts;
	
	public ExpertSystemShell() {
		knownFacts = new HashMap<Variable, Boolean>();
	}
	
	//determines which command to run
	private boolean exec(String[] parsedUserCommand) { //this may end up returning a void
		//switch or if/else list of each command
		if(parsedUserCommand[0].matches("quit|q|exit|ex|e")) {
			System.exit(0);
		}
		//for teach -args..., the dash is treated as its own place to break the string using .split(), this makes these numbers 1 larger than one might think
		else if (parsedUserCommand[0].equalsIgnoreCase("teach")&& parsedUserCommand.length == 5) {
			System.out.println("Big teach triggered");
			teach(parsedUserCommand[2], parsedUserCommand[3], parsedUserCommand[4]);
		}	
		else if(parsedUserCommand[0].equalsIgnoreCase("teach")&& parsedUserCommand.length == 3){
			teach(parsedUserCommand[1], parsedUserCommand[2]);
		}
		else if (parsedUserCommand[0].equalsIgnoreCase("list")) {
			list();
		}
		return true;
	}
	
	/**
	 *This command teaches the system a new variable of the form variable = string.
	 */
	private boolean teach(String arg, String var, String expression){
		System.out.println("arg: "+arg+" var: "+var+" exp "+expression);
		boolean isRoot = false;
		if (arg.equalsIgnoreCase("R")) {
			isRoot = true;
		}
		Variable newVar = new Variable(isRoot, var, expression);
		knownFacts.put(newVar, true); //need to look up specified behaviour here
		
		newVar = new Variable();
		newVar.setName(var);
		
		System.out.println("Was the value added to the database: "+knownFacts.get(newVar));
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
		for(Map.Entry<Variable, Boolean> entry: knownFacts.entrySet()){
			System.out.println("The value of: "+entry.getKey().getName()+" is: "+entry.getValue());
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
			String[] parsedUserCommand = userCommand.split("\\W");
			System.out.println("Command length: "+parsedUserCommand.length+" First token: "+parsedUserCommand[0]);
			program.exec(parsedUserCommand);
		}
	}
}
