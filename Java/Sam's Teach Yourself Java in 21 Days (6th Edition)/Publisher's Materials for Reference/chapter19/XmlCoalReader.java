import nu.xom.*;

public class XmlCoalReader {
    
    public XmlCoalReader() {
        try {
            Builder bob = new Builder();
            Document doc = bob.build("energy.xml");

            // Get the file's root element
            Element root = doc.getRootElement();

            // Get all of the <coal> elements and loop through them
            Elements coals = root.getChildElements("coal");
            for (int i = 0; i < coals.size(); i++) {
                // Get a <coal> element
                Element coal = coals.get(i);
                
                // Get all child elements of <coal>
                Elements children = coal.getChildElements();
                for (int j = 0; j < children.size(); j++) {
                	Element child = children.get(j);
                	Text childText = (Text) child.getChild(0);
                	if (childText != null) {
                		System.out.print(child.getLocalName());
                		System.out.print(": ");
                		System.out.print(childText.getValue());
                		System.out.println("");
                    }
                }
                System.out.println("");
            }
        } catch (Exception exc) {
            System.out.println("Error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }
       
    public static void main(String[] arguments) {
       XmlCoalReader xc = new XmlCoalReader();
    }
}