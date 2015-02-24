class Passer {

	void toUpperCase(String[] text) {
		
		for (int i = 0; i < text.length; i++) {
			text[i] = text[i].toUpperCase();
		} // end for loop
	} // end toUpperCase
	
	public static void main(String[] args) {
		
		Passer passer = new Passer();
		passer.toUpperCase(args);
		
		for (int i = 0; i < args.length; i++) {
			System.out.print(args[i] + " ");
		} // end for loop
		
		System.out.println();
	} // end main
} // end Passer class
