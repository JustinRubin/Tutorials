import java.io.*;
import nu.xom.*;

public class DomainWriter {
    public static void main(String[] arguments) throws IOException {
        try {
            // Create a tree from an XML document
            // specified as a command-line argument
            Builder builder = new Builder();
            Document doc = builder.build("feed2.rss");

            // Create a comment with the current time and date
            Comment timestamp = new Comment("File created "
                + new java.util.Date());

            // Add the comment above everything else in the
            // document
            doc.insertChild(timestamp, 0);

            // Create a file output stream to a new file
            FileOutputStream fos = new FileOutputStream("feed3.rss");

            // Using a serializer with indention set to 2 spaces,
            // write the XML document to the file
            Serializer output = new Serializer(fos, "ISO-8859-1");
            output.setIndent(2);
            output.write(doc);
        } catch (ParsingException pe) {
            System.out.println("Error parsing document: " + pe.getMessage());
            pe.printStackTrace();
            System.exit(-1);
        }
    }
}
