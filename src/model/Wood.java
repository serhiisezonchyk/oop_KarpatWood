package model;

import java.util.Objects;

public class Wood {
	private String name;
	private float density;
	public Wood(String name, float density) {
		super();
		this.name = name;
		this.density = density;
	}
	public String toString() {
		return "name=" + name + ", density=" + density;
	}


	public String getName() {
		return name;
	}

	public float getDensity() {
		return density;
	}
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) return true;
	     if (obj == null || getClass() != obj.getClass()) return false;
	     Wood wood = (Wood) obj;
	     return Float.floatToIntBits(density) == Float.floatToIntBits(wood.density) && Objects.equals(name, wood.name);
	}
}
