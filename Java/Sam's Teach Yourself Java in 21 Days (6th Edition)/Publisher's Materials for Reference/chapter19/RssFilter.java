import nu.xom.*;

public class RssFilter {
    public static void main(String[] arguments) {

        if (arguments.length < 2) {
            System.out.println("Usage: java RssFilter rssFile searchTerm");
            System.exit(-1);
        }

        // Save the RSS location and search term
        String rssFile = arguments[0];
        String searchTerm = arguments[1];

        try {
            // Fill a tree with an RSS file's XML data
            // The file can be local or something on the
            // Web accessible via a URL.
            Builder bob = new Builder();
            Document doc = bob.build(rssFile);

            // Get the file's root element (<rss>)
            Element rss = doc.getRootElement();

            // Get the element's version attribute
            Attribute rssVersion = rss.getAttribute("version");
            String version = rssVersion.getValue();

            // Add the DTD for RSS 0.91 feeds, if needed
            if ( (version.equals("0.91")) & (doc.getDocType() == null) ) {
                DocType rssDtd = new DocType("rss",
                    "http://my.netscape.com/publish/formats/rss-0.91.dtd");
                doc.insertChild(rssDtd, 0);
            }

            // Get the first (and only) <channel> element
            Element channel = rss.getFirstChildElement("channel");

            // Get its <title> element
            Element title = channel.getFirstChildElement("title");
            Text titleText = (Text)title.getChild(0);

            // Change the title to reflect the search term
            titleText.setValue(titleText.getValue() + ": Search for " +
                searchTerm + " articles");

            // Get all of the <item> elements and loop through them
            Elements items = channel.getChildElements("item");
            for (int i = 0; i < items.size(); i++) {
                // Get an <item> element
                Element item = items.get(i);

                // Look for a <title> element inside it
                Element itemTitle = item.getFirstChildElement("title");

                // If found, look for its contents
                if (itemTitle != null) {
                    Text itemTitleText = (Text) itemTitle.getChild(0);

                    // If the search text is not found in the item,
                    // delete it from the tree
                    if (itemTitleText.toString().indexOf(searchTerm) == -1)
                        channel.removeChild(item);
                }
            }

            // Display the results with a serializer
            Serializer output = new Serializer(System.out);
            output.setIndent(2);
            output.write(doc);
        } catch (Exception exc) {
            System.out.println("Error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }
}
