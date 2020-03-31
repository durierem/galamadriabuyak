package galamadriabuyak.util;

class Command {

	// ATTRIBUTES

	private final String name;
	private final boolean isTargeted;
	private int targetID;

	// CONSTRUCTORS

	Command(String name) {
		this(name, false, 0);
	}

	Command(String name, boolean isTargeted, int targetID) {
		if (name == null) {
			throw new AssertionError();
		}
		this.name = name;
		this.isTargeted = isTargeted;
		this.targetID = targetID;
	}

	// REQUESTS

	String getName() {
		return name;
	}

	boolean isTargeted() {
		return isTargeted;
	}

	int getTargetID() {
		return targetID;
	}

	void setTargetID(int targetID) {
		this.targetID = targetID;
	}
}