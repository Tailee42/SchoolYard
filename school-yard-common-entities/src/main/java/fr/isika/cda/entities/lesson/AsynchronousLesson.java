package fr.isika.cda.entities.lesson;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AsynchronousLesson extends Activity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7548133910399384522L;
	@OneToOne
	private Unit unit;

	public AsynchronousLesson() {
		super();
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
