package com.psp.sws.stringreversal.endpoint;

import org.springframework.ws.server.endpoint.AbstractDomPayloadEndpoint;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.psp.sws.stringreversal.service.StringReveralService;

//@Endpoint
public class StringReversalServiceEndPoint extends AbstractDomPayloadEndpoint{

	public static final String NAMESPACE = "https://javabeat.net/spring/spring-ws/articles/reversal-service";
	private StringReveralService stringReversalService;

	public void setStringReveralService(StringReveralService stringReveralService) {
		this.stringReversalService = stringReveralService;
	}

	protected Element invokeInternal(Element requestElement, Document document) throws Exception {
		String requestString = findRequestString(requestElement);
		String reversedString = invokeServiceAndReturnResponse(requestString);
		Element responseXml = prepareResponseXml(document, reversedString);
		return responseXml;
	}

	private static String findRequestString(Element requestElement) {
		NodeList childNodes = requestElement.getChildNodes();
		String requestString = null;
		for (int i = 0; i < childNodes.getLength(); i++) {
			if (childNodes.item(i).getNodeType() == Node.TEXT_NODE) {
				Text tempText = (Text) childNodes.item(i);
				requestString = tempText.getNodeValue();
				break;
			}
		}
		return requestString;
	}

	private String invokeServiceAndReturnResponse(String requestString) {
		String reversedString = stringReversalService.reverse(requestString);
		return reversedString;
	}

	private static Element prepareResponseXml(Document document, String responseString) {
		Element responseElement = document.createElementNS(NAMESPACE, "stringReveralResponse");
		Text responseText = document.createTextNode(responseString);
		responseElement.appendChild(responseText);
		return responseElement;
	}

}
