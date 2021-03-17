package model;

public class Waste implements IWeight {

	public Waste(float weight) throws Exception {
		super();
		this.weight = weight;
	}

	private float weight;

	public float weight() {

		return weight;
	}


	public String toString() {
		return "Відходи\t\t\t\tвага=" + weight + "";
	}

}
