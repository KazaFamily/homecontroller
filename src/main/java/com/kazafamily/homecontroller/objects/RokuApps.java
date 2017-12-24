package com.kazafamily.homecontroller.objects;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;
  
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "apps")
@Data
@ToString
public class RokuApps {

    @XmlElement(name="app")
    private ArrayList<RokuApp> apps;
	
}
