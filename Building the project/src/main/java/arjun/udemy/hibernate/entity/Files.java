package arjun.udemy.hibernate.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity(name="files")
@Table(name = "files")
public class Files {
	@Id
	@Column(name = "id")
	int id;
	@Column(name = "filename")
	String filename;
	@Column(name = "label")
	String label;
	@Column(name = "caption")
	String caption;
	
	public Files() {
		
	}
	
	public Files(int id, String filename, String label, String caption) {
		super();
		this.id = id;
		this.filename = filename;
		this.label = label;
		this.caption = caption;
	}

	public Files(int id, String label, String caption) {
		super();
		this.id = id;
		this.label = label;
		this.caption = caption;
	}

	public Files(String filename) {
		super();
		this.filename = filename;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return "Files [id=" + id + ", filename=" + filename + ", label=" + label + ", caption=" + caption + "]";
	}
	
}
