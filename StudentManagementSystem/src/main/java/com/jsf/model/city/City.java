package com.jsf.model.city;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class City {
	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	

}
