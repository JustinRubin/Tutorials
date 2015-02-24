class Averager {

	public static void main(String[] args) {
		
		int sum = 0;
		
		if (args.length > 0) {  // makes sure there's at least one input
			for (int i = 0; i < args.length; i++) {
				sum += Integer.parseInt(args[i]); // use parseInt since all command-line args are passed in as string objects
			} // end for loop
			
			System.out.println("Sum is: " + sum);
			System.out.println("Average is: " + (float) (sum/args.length));
		} // end if
	} // end main
} // end Averager
