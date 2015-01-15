import nu.xom.*;

public class BookFilter {
    
    public BookFilter() {
    	// no initialization needed
    }
    
    public void read() {    	
        try {
            Builder bob = new Builder();
            Document doc = bob.build("books.xml");

            // Get the file's root element
            Element root = doc.getRootElement();

            // Get all of the <book> elements and loop through them
            Elements books = root.getChildElements("Book");
            for (int i = 0; i < books.size(); i++) {
                // Get an <book> element
                Element book = books.get(i);
                
                // Look for an <author> element inside it
                Element author = book.getFirstChildElement("Author");
                
                // If found, look for its contents
                if (author != null) {
                    Text authorText = (Text) author.getChild(0);
                    String authorName = authorText.getValue();
                    
                    if (authorName.equals("Joseph Heller")) {
                    	System.out.println("\nAuthor: Joseph Heller");
                    	// Display the other elements
                    	Element title = book.getFirstChildElement("Title");
                    	display("Title", title);
                    	Element pubDate = book.getFirstChildElement("PubDate");
                    	display("Publication Date", pubDate);
                    	Attribute edition = pubDate.getAttribute("edition");
                    	displayAttribute("Edition", edition);
                    	Attribute isbn = pubDate.getAttribute("isbn");
                    	displayAttribute("ISBN", isbn);
                    	Element publisher = book.getFirstChildElement("Publisher");
                    	display("Publisher", publisher);
                    	Element subject = book.getFirstChildElement("Subject");
                    	display("Subject", subject);
                    	Element review = book.getFirstChildElement("Review");
                    	display("Review", review);
                    }
                }
            }
        } catch (Exception exc) {
            System.out.println("Error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }
    
    // display the text inside an element (if it exists)
    void display(String label, Element element) {
    	if (element != null) {
    		Text elementText = (Text) element.getChild(0);
    		String elementString = elementText.getValue();
    		System.out.println(label + ": " + elementString);
    	}
    }    		
    
    // display an attribute's value (if it exists)
    void displayAttribute(String label, Attribute attribute) {
    	if (attribute != null) {
    		String attributeString = attribute.getValue();
    		System.out.println(label + ": " + attributeString);
    	}
    }
    
    public static void main(String[] arguments) {
       BookFilter bf = new BookFilter();
       bf.read();
    }
}
