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
	
	/**
	 *Overriding the default way in which this item is put into a hash map so that the items are stored by their name instead of their instance
	 */
	 @Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 *Overriding the default way in which this item determines equality, this will compare against names, not instances
	 */
	 @Override
	public boolean equals(Object obj) {
		Variable otherVar = (Variable) obj;
		return name.equals(otherVar.getName());
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