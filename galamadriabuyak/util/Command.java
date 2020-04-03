package galamadriabuyak.util;

/**
 * The representation of a command.
 */
class Command {

	// ATTRIBUTES

	private final String name;
	private final boolean isTargeted;
	private int targetID;

	// CONSTRUCTORS

	/**
	 * Creates a new command that doesn't require a target.
	 * @pre
	 * 		name != null
	 * @post
	 * 		getName() == name
	 * 		&& getTargetID() == 0
	 *		&& isTargeted() == false
	 */
	Command(String name) {
		this(name, false);
	}

	/**
	 * Creates a new commmand.
	 * @pre
	 * 		name != null
	 * @post
	 * 		getName() == name
	 * 		&& getTargetID() == 0
	 *		&& isTargeted() == true
	 */
	Command(String name, boolean isTargeted) {
		if (name == null) {
			throw new AssertionError();
		}
		this.name = name;
		this.isTargeted = isTargeted;
		this.targetID = 0;
	}

	// REQUESTS

	/**
	 * The name of this command.
	 */
	String getName() {
		return name;
	}

	/**
	 * Reflects if the command requires a target to be used.
	 */
	boolean isTargeted() {
		return isTargeted;
	}

	/**
	 * The target ID of this command.
	 */
	int getTargetID() {
		return targetID;
	}

	/**
	 * Sets the target ID of this command to n.
	 * @post
	 *		getTargetID() == n
	 */
	void setTargetID(int n) {
		this.targetID = n;
	}
}
