package com.kazafamily.homecontroller.objects;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import lombok.Data;
import lombok.ToString;
  
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "app")
@Data
@ToString
public class RokuApp implements Serializable{

	private static final long serialVersionUID = 5371081634407577754L;
	
    @XmlAttribute(name="id")
    private String id;
  
    @XmlAttribute(name="type")
    private String type;
  
    @XmlAttribute(name="version")
    private String version;
  
    @XmlValue
    private String name;
  
}
