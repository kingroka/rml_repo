package com.gh.vars;

public class BoolV extends Variable {
	public BoolV(String name,Boolean val) {
		this.name = name;
		value = val;

	}

	public Boolean getValue() {
		if (value instanceof String) {
			return Boolean.parseBoolean((String) value);
		} else
			return (Boolean) value;
	}

	public void setValue(Object value) {
		if (value instanceof Boolean) {
			this.value = value;
		} else if (value instanceof String) {
			try {
				this.value = Boolean.parseBoolean((String) value);
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
