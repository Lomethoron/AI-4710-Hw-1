import java.util.*;

public class ExpertSystemShell {
	
	private HashMap<String, Variable> knownFacts;
	private ArrayList<Rule> knownRules;
   private String latestReasoning = "";
	
	public ExpertSystemShell() {
		knownFacts = new HashMap<String, Variable>();
		knownRules = new ArrayList<Rule>();
	}
	
	//determines which command to run
	private boolean exec(String userCommand) { //this may end up returning a void
		String[] parsedUserCommand = userCommand.split("\\W");
		//switch or if/else list of each command
		if(parsedUserCommand[0].matches("quit|q|exit|ex|e")) {
			System.exit(0);
		}
		else if (parsedUserCommand[0].equalsIgnoreCase("teach")) {
			//first teach command, for some reason I elected not to use a separate method
			if(userCommand.charAt(6) == '-'){
				boolean isRoot = false;
				if(userCommand.charAt(7) == 'r' || userCommand.charAt(7) == 'R') {
					isRoot = true;
				}
				String varAndExpressionSubstring = userCommand.substring(9); //trim off the front of the string
				String[] varAndExpressionSplit = varAndExpressionSubstring.split(" = ");
				varAndExpressionSplit[1] = varAndExpressionSplit[1].substring(1,varAndExpressionSplit[1].length()-1); //remove the quotations
				
				Variable newVar = new Variable(isRoot, varAndExpressionSplit[0], varAndExpressionSplit[1], false);
				knownFacts.put(varAndExpressionSplit[0], newVar);
			}
			//second teach command
			else if(userCommand.contains("=")){
				String varAndExpressionSubstring = userCommand.substring(6);
				String[] varAndExpressionSplit = varAndExpressionSubstring.split(" = "); //breaking along the = mark puts seperates expression from var
				teach(varAndExpressionSplit[0], Boolean.parseBoolean(varAndExpressionSplit[1]));
				
				
			}
			//final teach command
			else {
				String varAndExpressionSubstring = userCommand.substring(6);
				String[] varAndExpressionSplit = varAndExpressionSubstring.split(" -> ");//same as above w/ ->
				teach(varAndExpressionSplit[0], varAndExpressionSplit[1]);
			}
		}	
		else if (parsedUserCommand[0].equalsIgnoreCase("list")) {
			list();
		}
		else if (parsedUserCommand[0].equalsIgnoreCase("learn")) {
			learn();
		}
		else if (parsedUserCommand[0].equalsIgnoreCase("query")) {
			String[] reparsedUserCommand = userCommand.split(" ");
			query(reparsedUserCommand[1]);
		}
		else if (parsedUserCommand[0].equalsIgnoreCase("why")) {
			String[] reparsedUserCommand = userCommand.split(" ");
			why(reparsedUserCommand[1]);
		}
		return true;
	}
	
	
	/**
	 *Teaches the system that a defined root variable has been observed to be true(or false). 
	 */
	private boolean teach(String var, boolean truth) {//YOU CANT HANDLE THE TRUTH
		//we may assume that all learned variables are reset to false when we call this command
		boolean doesVarExist = false;
		for(Map.Entry<String,Variable> entry : knownFacts.entrySet()){
			String key = entry.getKey();
			Variable value = entry.getValue();
			
			if (!value.getIsRoot()) {
				value.setState(false);
				knownFacts.put(key,value);
			}
			
			//making sure the variable exists in the first place
			if(key.equals(var)) {
				doesVarExist = true;
			}
		}
		if(!doesVarExist) {
			throw new RuntimeException("The variable: "+var+" does not exist. Program exiting.");
		}
		Variable editVar = knownFacts.get(var);
		editVar.setState(truth);
		knownFacts.put(var, editVar);
		return true;
	}
	
	/**
	 *Teaches  the system  a  new  rule.
	 */
	private boolean teach(String expression, String var) {
		Rule newRule = new Rule(expression, var);
		knownRules.add(newRule);
		return true;
	}
	
	/**
	 * Lists out all of the fact and rules currently known by the system.
	 */
	private String list() {
		System.out.println("Root Variables:");
		for(Map.Entry<String, Variable> entry: knownFacts.entrySet()){
			if(entry.getValue().getIsRoot()){
				System.out.println("\t"+entry.getKey()+" = \""+entry.getValue().getExpression()+"\"");
			}
		}
		System.out.println("Learned Variables:");
		for(Map.Entry<String, Variable> entry: knownFacts.entrySet()){
			if(!entry.getValue().getIsRoot()){
				System.out.println("\t"+entry.getKey()+" = \""+entry.getValue().getExpression()+"\"");
			}
		}
		System.out.println("Facts:");
		for(Map.Entry<String, Variable> entry: knownFacts.entrySet()){
			if(entry.getValue().getState()){
				System.out.println("\t"+entry.getKey());
			}
		}
		System.out.println("Rules:");
		for(Rule rule: knownRules){
			System.out.println("\t"+rule.getExpression()+" -> "+rule.getVar());
		}
		return "";
	}
	
	/**
	 *Uses forward chaining to apply all of the rules of the system to 
	 *the facts of the system to create newly formed facts.
	 */
	private String learn() {
		boolean canLearnMore;
		//while changes are being made
		 do {
			 //System.out.println("whee");
			canLearnMore = false;
			//for every rule
			for(Rule rule: knownRules) {
				String ruleExp = rule.getExpression();
				String ruleVar = rule.getVar();
				//see if I know new things
				//if the expression is true, and not already known to be true
				if(tokenize(ruleExp)&&!knownFacts.get(ruleVar).getState()){
					String varName = rule.getVar();
					System.out.println(varName);
					Variable editFact = knownFacts.get(varName);
					editFact.setState(true);
					knownFacts.put(varName, editFact);
					canLearnMore = true;
				}
			}
		} while(canLearnMore);
		return "";
	}
	
	/**
	 *Returns true if an only if the given expression is
	 *true given the rules and facts within the system.
	 */
	private String query(String exp){
		System.out.println(tokenize(exp));
		return "";
	}
	/**
	 *the ‘Why’ command explains the reasoning behind the true or false claim to the user.
	 */
	private String why(String exp){
		latestReasoning = "";
		query(exp);
		System.out.println(latestReasoning);
		return "";
	}
	
	public TreeNode treeify(String exp)
   {
	   //System.out.println("Exp: "+exp);
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
      boolean returnBoolean;
	  String expRef = exp;
	   //System.out.println("Exp: "+exp);
      if(exp.contains("|") || exp.contains("&") || exp.contains("!"))
         returnBoolean = solveTree(treeify(exp));
      else returnBoolean = solveTree(new TreeNode(exp));
	  parse(exp);
      String parsed = parse(exp);
      if(returnBoolean&&expRef.length()>1){//if the connection is true, and is not a simply a truth lookup
         latestReasoning = latestReasoning + "I THUS KNOW THAT " + parsed + "\n";
		 //System.out.println("I THUS KNOW THAT (" + parsed + ")");
	  }
      else if(!returnBoolean&&expRef.length()>1){
         latestReasoning = latestReasoning + "THUS I CANNOT PROVE " + parsed + "\n";
		 //System.out.println("THUS I CANNOT PROVE (" + parsed + ")");
	  }
      return returnBoolean;
   } 
	
	public String parse(String exp){
		String parsed = "";
		while (exp.length()>0)
      {
		  //System.out.println("whee");
         String character = exp.substring(0,1);
         if(character.equals("&"))
            parsed = parsed + " AND ";
         else if(character.equals("|"))
            parsed = parsed + " OR ";
         else if(character.equals("!"))
            parsed = parsed + " NOT ";
         else if(character.equals("("))
            parsed = parsed + " ( ";
         else if(character.equals(")"))
            parsed = parsed + " ) ";
         else
            for(Map.Entry<String, Variable> entry : knownFacts.entrySet()) {
               String key = entry.getKey();
               Variable value = entry.getValue();
               if(character.equals(entry.getKey()))
                  parsed = parsed + entry.getValue().getExpression();
            }
		exp = exp.substring(1);
      }
	  return parsed;
	}
  
   public boolean solveTree(TreeNode root)
   {
	  //System.out.println("Root value: "+root.getValue());
      char letter = ("" + root.getValue()).charAt(0);
      if(Character.isLetter(letter)) {
		 //System.out.println("root.getValue() "+ root.getValue());
         return trueOrFalse("" + root.getValue());
	  }
      else if("|".equals("" + root.getValue()))
         return solveTree(root.getLeft()) || solveTree(root.getRight());
      else if("&".equals("" + root.getValue()))
         return solveTree(root.getLeft()) && solveTree(root.getRight());
      else
         return !solveTree(root.getSingle());    
   }
   
   public boolean trueOrFalse(String symbol)
   {
	   for(Map.Entry<String, Variable> entry : knownFacts.entrySet()) {
         String key = entry.getKey();
         Variable value = entry.getValue();
         //System.out.println("Key: "+key+" Symbol: "+symbol+" State: "+value.getState());
		 //System.out.println("First Condition: "+(key.equals(symbol)&&value.getState()));
         if(key.equals(symbol)&&value.getState()){ 
            latestReasoning = latestReasoning + "I KNOW THAT " + value.getExpression() + "\n";
			//System.out.println("I KNOW THAT " + value.getExpression());
            return true; //base case
         }
		 //System.out.println("Second Condition: " + (key.equals(symbol)&&!value.getState()));
		 if(key.equals(symbol)&&!value.getState()) {
			 for(Rule rule: knownRules) {
               String implicant = rule.getVar();
               String expression = rule.getExpression();
               if(implicant.equals(symbol)) {
                  boolean isExpTrue = tokenize(expression);
				  //recurse into a new expression
				  if(isExpTrue){
					  latestReasoning += "BECAUSE " + parse(expression) + " I KNOW THAT "+ parse(implicant);
					  //System.out.println("BECAUSE " + parse(expression)+ " I KNOW THAT "+ parse(implicant));
					  //tokenize(expression);
					  return true;
				  }
				  else {
					  latestReasoning += "BECAUSE IT IS NOT TRUE THAT " + parse(expression) + " I CANNOT PROVE THAT "+ parse(implicant);
					  //System.out.println("BECAUSE IT IS NOT TRUE THAT " + parse(expression)+ " I CANNOT PROVE THAT "+ parse(implicant));
					  //tokenize(expression);
					  return false;
				  }
               }
            }
			 latestReasoning += "I KNOW IT IS NOT TRUE THAT " + value.getExpression() + "\n";
			 //System.out.println("I KNOW IT IS NOT TRUE THAT " + value.getExpression());
			 return false;
		 }
	   }
      
	  //all variables should be known and this should never be encountered	
	  throw new RuntimeException("Unknown Variable \""+symbol+"\" encountered: program halting.");	
      /*for(Map.Entry<String, Variable> entry : knownFacts.entrySet()) {
         if(symbol.equals(entry.getKey()))
            latestReasoning = latestReasoning + "I KNOW IT IS NOT TRUE THAT " + entry.getValue() + "/n";
      }*/
      //return false;
   }
	
	public static void main(String args[]){
		ExpertSystemShell program = new ExpertSystemShell();
		//get user commands
		Scanner in = new Scanner(System.in);
		while(true){
			String userCommand = in.nextLine();
			program.exec(userCommand);
		}
	}
}
