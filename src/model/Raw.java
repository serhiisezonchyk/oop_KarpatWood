package model;



public class Raw extends Abstract {

	protected float diameter;
	protected float length;

	public Raw(Wood wood, float diameter, float length) throws Exception {
		super();
		this.wood = wood;
		this.diameter = diameter;
		this.length = length;
		this.weight = weight();

	}

	private float weight;
	public float weight()  {
		return wood.getDensity() * volume();
	}

	public float volume() {
		return (float) (Math.PI * Math.pow(diameter / 2, 2) * length);
	}
	@Override
	public String toString() {
		return "Raw " + wood.getName() + "\tweight: " + weight;

	}
	public float getDiameter() {
		return diameter;
	}

	public float getLength() {
		return length;
	}
}
