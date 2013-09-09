package org.saiku.admin.repository;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CubeRepository {
	 static final Logger logger = LoggerFactory.getLogger(CubeRepository.class);
	 
	 public List<String> getRoleCube(String schema){
		 List<String> rolles = null;
		 File fXmlFile = null;
		 DocumentBuilderFactory dbFactory = null;
		 DocumentBuilder dBuilder = null;
		 Document doc = null;
		 NodeList nList = null;
	 try {
		 	rolles =  new ArrayList<String>();
		 	fXmlFile = new File(schema);
	        dbFactory = DocumentBuilderFactory.newInstance();
	        dBuilder = dbFactory.newDocumentBuilder();
	        doc = dBuilder.parse(fXmlFile);
	        doc.getDocumentElement().normalize();
	        nList = doc.getElementsByTagName("Role");
	   
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                rolles.add(eElement.getAttribute("name"));
	                logger.debug(rolles.toString());
	            }
	        }
	        
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.equals(e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.equals(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.equals(e);
		}finally{
		
		}
	 return rolles;
	 }
	 
	 public static void main(String[] args) {
		CubeRepository cube_ = new CubeRepository();
		cube_.getRoleCube("/home/sowe/Descargas/eclipse/Role.mondrina.xml");
	}
}
