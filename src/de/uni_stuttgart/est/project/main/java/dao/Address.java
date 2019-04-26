package dao;

import java.io.Serializable;

public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9086172499141379023L;
	
	private String streetname;
	private int housenumber;
	private int zipcode;
	private String city;
	private String country;
	
	/**
	 * Generic constructor for the address-object.
	 * 
	 * @param streetname
	 * @param housenumber
	 * @param zipcode
	 * @param city
	 * @param country
	 */
	public Address(String streetname, int housenumber, int zipcode, String city, String country) {
		this.setStreetname(streetname);
		this.setHousenumber(housenumber);
		this.setZipcode(zipcode);
		this.setCity(city);
		this.setCountry(country);
	}

	/**
	 * @return the streetname
	 */
	public String getStreetname() {
		return streetname;
	}

	/**
	 * @param streetname the streetname to set
	 */
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	/**
	 * @return the housenumber
	 */
	public int getHousenumber() {
		return housenumber;
	}

	/**
	 * @param housenumber the housenumber to set
	 */
	public void setHousenumber(int housenumber) {
		this.housenumber = housenumber;
	}

	/**
	 * @return the zipcode
	 */
	public int getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	
	public boolean equals(Address address_1, Address address_2) {
		if(	
				address_1.streetname.equals(address_2.streetname)&&
				address_1.housenumber==address_2.housenumber&&
				address_1.zipcode==address_2.zipcode&&
				address_1.city.equals(address_2.city)&&
				address_1.country.equals(address_2.country)) 
		{
			return true;
		}	
		else {
			return false;
		}
	}
	
}
