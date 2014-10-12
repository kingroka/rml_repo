package com.gh.vars;

public class Int extends Variable {

	public Int(String name,double val) {
		this.name = name;
		value = val;

	}

	public Double getValue() {
		if (value instanceof String) {
			return Double.parseDouble((String) value);
		} else
			return (Double) value;
	}

	public void setValue(Object value) {
		if (value instanceof Double) {
			this.value = value;
		} else if (value instanceof String) {
			try {
				this.value = Double.parseDouble((String) value);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			try {
				throw new VariableMismatchException();
			} catch (VariableMismatchException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

	}
}
