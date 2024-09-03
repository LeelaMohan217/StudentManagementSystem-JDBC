package com.jsf.model.country;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsf.db_connection.DB_Connection;

@ManagedBean(name = "country")
@SessionScoped
public class Country {
	private int countryId;
	private String countryCode;
	private String countryName;
	private ArrayList<String> countryCodes;

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	// retrieve data
	public ArrayList<Country> getGet_all() {
		ArrayList<Country> al = new ArrayList<Country>();

		try {
			Connection connection = null;
			DB_Connection dbcon = new DB_Connection();
			connection = dbcon.getConnection();

			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM public.country");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Country obj = new Country();
				obj.setCountryId(rs.getInt("countryId"));
				obj.setCountryCode(rs.getString("countryCode"));
				obj.setCountryName(rs.getString("countryName"));
				al.add(obj);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return al;
	}

	public ArrayList<String> getAllCountryCodes() {
		if (countryCodes == null) {
			countryCodes = new ArrayList<String>();

			try {
				Connection connection = null;
				DB_Connection dbcon = new DB_Connection();
				connection = dbcon.getConnection();

				PreparedStatement pstmt = connection
						.prepareStatement("SELECT DISTINCT \"countryCode\" FROM public.country");
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					countryCodes.add(rs.getString("countryCode"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return countryCodes;
	}

	// method to insert data
	public String add_country() {
		try {
			Connection connection = null;
			DB_Connection dbcon = new DB_Connection();
			connection = dbcon.getConnection();

			PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO public.country (countryCode, countryName) VALUES (?, ?)");
			pstmt.setString(1, countryCode);
			pstmt.setString(2, countryName);

			pstmt.executeUpdate();

			System.out.println("Country added successfully!");

			return "country";

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
