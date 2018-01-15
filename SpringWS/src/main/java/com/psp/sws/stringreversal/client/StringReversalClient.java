package com.psp.sws.stringreversal.client;

import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.ws.client.core.WebServiceTemplate;

public class StringReversalClient {

	public static void main(String[] args) throws Exception {

		String xmlRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<stringReveralRequest xmlns=\"https://javabeat.net/spring/spring-ws/articles/reversal-service\">Hello</stringReveralRequest>";

		String wsdlUrl = "http://localhost:8080/string-reversal/stringReversal.wsdl";
		StreamSource requestMessage = new StreamSource(new StringReader(xmlRequest));
		StreamResult responseMessage = new StreamResult(System.out);
		WebServiceTemplate template = new WebServiceTemplate();
		template.sendSourceAndReceiveToResult(wsdlUrl, requestMessage, responseMessage);
	}

}
