package auxiliary;

/**
 * 
 * This class is a way to get a unique ID number for every entity in this project, it uses the singleton pattern.
 * For simplicity, i centralised the getting of an ID number on the class ED (representing an emergency departement).
 *
 */


public  class ID{
	private static ID instance = null;
	private int num;
		
	private ID() {};
		
	public static synchronized ID getinstance() {
		if(instance==null) {
				instance = new ID();
		}
		return instance;
	}
		
	public int Next() {
		return num++;
	}
		
	public Object readResolve() {
		return instance;
	}
}

