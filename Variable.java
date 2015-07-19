public class Variable {
	private String name, expression;
	private boolean isRoot, state;
	
	public Variable() {
		name = "";
		expression = "";
		isRoot = false;
		state = false;
	}
	
	public Variable(boolean isRoot, String name, String expression, boolean state) {
		this.isRoot = isRoot;
		this.name = name;
		this.expression = expression;
		this.state = state;
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
	
	public boolean getState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
}