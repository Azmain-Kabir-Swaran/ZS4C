package gwt;

import java.util.ArrayList;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Element;

public class gwt_class_49 {
	private void putElementLinkIDsInList(BodyElement elt, ArrayList<String> list) {
	}
	
	private void rewriteLinksIterative() {
		ArrayList<String> links = new ArrayList<String>();
		putElementLinkIDsInList(Document.get().getBody(), links);
		for (int i = 0; i < links.size(); i++) {
			Element elt = Document.get().getElementById((String) links.get(i));
			rewriteLink(elt, "www.example.com");
		}
	}

	private void rewriteLink(Element element, String sitename) {
		String href = element.getPropertyString("href");
		if (null == href) {
			return;
		}

		if (href.startsWith("http://")
				&& !href.startsWith("http://" + sitename + "/")) {
			element.setPropertyString("href", "http://" + sitename + "/Blocked.html");
		}
	}
}