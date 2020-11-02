package main;
/**
 * @author Gruppe 1
 * 
 *Type to display Status from DB
 */
public class Status {
	private int id;
	private String description;
	
	public Status(int id, String description) {
		this.id = id; 
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String
				.format("Status [id=%s, description=%s]",
						id, description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
