public class Rule {
	private String expression, var;
	
	public Rule() {
		
	}
	public Rule(String expression, String var) {
		this.expression = expression;
		this.var = var;
	}
	
	public String getExpression() {
		return expression;
	}
	
	public String getVar() {
		return var;
	}
}