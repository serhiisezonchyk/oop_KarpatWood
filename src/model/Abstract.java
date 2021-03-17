package model;


public abstract class Abstract implements IWeight{
	protected Wood wood;

	public abstract float volume();

	
	public Abstract() {
		super();
	}

	public Wood getWood() {
		return wood;
	}
}
