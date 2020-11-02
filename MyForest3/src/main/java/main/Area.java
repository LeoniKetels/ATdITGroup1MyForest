package main;
/**
 * @author Gruppe 1
 * 
 *Type to display Area from DB
 */
public class Area {
	private int id;
	private String description;
	
	public Area(int id, String description) {
		this.id = id; 
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String
				.format("Area [id=%s, description=%s]",
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
