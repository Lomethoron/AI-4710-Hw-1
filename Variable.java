public class Variable {
	private String name, expression;
	private boolean isRoot;
	
	public Variable() {
		name = "";
		expression = "";
		isRoot = false;
	}
	
	public Variable(boolean isRoot, String name, String expression) {
		this.isRoot = isRoot;
		this.name = name;
		this.expression = expression;
	}
	
	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	
	public boolean getIsRoot() {
		return isRoot;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getExpression() {
		return expression;
	}
}