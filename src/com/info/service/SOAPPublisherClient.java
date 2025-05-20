package com.info.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.info.model.Person;

public class SOAPPublisherClient {
	
	public static void main(String[] args) throws MalformedURLException {
		
		URL wsdlURL= new URL("http://localhost:8888/ws/person?wsdl");
		//check above URL in browser, you should see wsdlfile
		
		//create QName using targetNamespace and name
		QName qname = new QName("http://service.info.com/", "PersonServiceImplService");
		Service service = Service.create(wsdlURL, qname);
		
		//we need to pass interface and model beans to client
		PersonService ps = service.getPort(PersonService.class);
		
		Person p1 = new Person(); p1.setName("chamseddine doula"); p1.setId(1); p1.setAge(20);
		Person p2 = new Person(); p2.setName("chokri yassine"); p2.setId(2); p2.setAge(20);
		Person p3 = new Person(); p3.setName("yaacoub eya"); p3.setId(3); p3.setAge(41);
		
		//add Person
		System.out.println("Add Person Status="+ps.addPerson(p1));
		System.out.println("Add Person Status="+ps.addPerson(p2));
		System.out.println("Add Person Status="+ps.addPerson(p3));
	}

}
