
import java.io.StringReader;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class LogParser {
    public static Collection<Integer> getIdsByMessage(String xml, String message) throws Exception {
//	 List<Integer> messageIds = new ArrayList<>();
//      String idPlacePattern = "id=\"";
//      String searchString = xml;
//
//      while (true) {
//          int indexOfMessage = searchString.lastIndexOf(message);
//          if (indexOfMessage == -1) {
//              break;
//          }
//          int indexOfId = searchString.lastIndexOf(idPlacePattern) + idPlacePattern.length();
//
//          String entryString = searchString.substring(indexOfId, indexOfMessage);
//          StringBuilder id = new StringBuilder();
//          id.append(entryString.charAt(0));
//          for (int i = 1; i < entryString.length(); i++) {
//              id.append(entryString.charAt(i));
//              if (!id.toString().matches("\\d+")) {
//                  id.deleteCharAt(id.length() - 1);
//                  break;
//              }
//          }
//          searchString = searchString.substring(0, indexOfId - idPlacePattern.length()) + searchString.substring(indexOfMessage + message.length(), searchString.length() - 1);
//          messageIds.add(Integer.parseInt(id.toString()));
//      }
//
//      return messageIds;
    	List<Integer> messageIds = new ArrayList<>();
    	DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource src = new InputSource();
		src.setCharacterStream(new StringReader(xml));
		Document doc = builder.parse(src);
    	NodeList entryList = doc.getElementsByTagName("entry");
    	for (int i = 0; i < entryList.getLength(); i++) {
    		Element entryElement = (Element) entryList.item(i);
    		String idString = entryElement.getAttributes().getNamedItem("id").getNodeValue();
    		if(entryElement.getElementsByTagName("message").item(0).getTextContent().equals(message)) {
    				messageIds.add(Integer.parseInt(idString));
    			}
    		}
    	return messageIds;
  }
    
    public static void main(String[] args) throws Exception {
        String xml = 
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<log>\n" + 
                "    <entry id=\"1\">\n" + 
                "        <message>Application started</message>\n" + 
                "    </entry>\n" + 
                "    <entry id=\"2\">\n" + 
                "        <message>Application ended</message>\n" + 
                "    </entry>\n" + 
                "</log>";
        
        Collection<Integer> ids = getIdsByMessage(xml, "Application ended");
        for(int id: ids)
            System.out.println(id); 
    }
}