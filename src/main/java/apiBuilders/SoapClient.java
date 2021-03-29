package webdriverpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.jboss.netty.handler.timeout.ReadTimeoutException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;





public class SoapClient {
	String xmlRequestFilePath;
	static String endPointUrl;
private SOAPConnectionFactory soapConnectionFactory;
private SOAPConnection soapConnection;
private MessageFactory messageFactory;
private SOAPMessage soapResponse = null;
private SOAPMessage soapRequest = null;

private SOAPBody body;

private void establishConnection(){
	try{
		soapConnectionFactory = SOAPConnectionFactory.newInstance();
		soapConnection = soapConnectionFactory.createConnection();
	}catch(SOAPException e){
		
	}
}
public int executeSoapRequest(){
	try{
		createSoapRequestFromFile();
		if(soapConnection != null &&equals(soapRequest != null)){
			soapResponse = soapConnection.call(soapRequest, endPointUrl);
			soapResponse.writeTo(System.out);
			body = soapResponse.getSOAPBody();
		}
	}catch (SOAPException e) {
			// TODO: handle exception
	}catch(FileNotFoundException fileNotFoundException){
		
	}catch(IOException ioException){
			

}
	return 0;
}
private int createSoapRequestFromFile() throws SOAPException, IOException{
	int error =-1;
	messageFactory = MessageFactory.newInstance();
	soapRequest = messageFactory.createMessage();
	SOAPPart soapPart = soapRequest.getSOAPPart();
	if(!xmlRequestFilePath.isEmpty()){
		soapPart.setContent(new StreamSource(new FileInputStream(xmlRequestFilePath)));
		soapRequest.saveChanges();
		soapRequest.writeTo(System.out);
		
	}
	return error;
}

}
 class ParseXML{
	 String xmlRequestFilePath;
	 private Document document;
	 
	 public int parseXML() throws IOException{
		 File smlFileRequest = new File(xmlRequestFilePath);
		 FileInputStream sStreamRequest = new FileInputStream(smlFileRequest);
		 DocumentBuilderFactory documentBuilderFactory = null;
		 DocumentBuilder documentBuilder = null;
		 try{
			 documentBuilderFactory=DocumentBuilderFactory.newInstance();
			 documentBuilder = documentBuilderFactory.newDocumentBuilder();
			 document = documentBuilder.parse(smlFileRequest);
			 document.getDocumentElement().normalize();
			 
		 }catch(Exception e){
			 
		 }
		return 0;
	 }
	 public void setAttributeValue(String attributeName){
		 Element ele;
		 NodeList nodeList = document.getElementsByTagName(attributeName);
		 Node node = nodeList.item(0);
		 node.setTextContent("");
	 }
}
