package dao;

import java.io.Serializable;

/**
 * 
 * Class to store Address-Data.
 *  
 * @author Philipp
 *
 */
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (housenumber != other.housenumber)
			return false;
		if (streetname == null) {
			if (other.streetname != null)
				return false;
		} else if (!streetname.equals(other.streetname))
			return false;
		if (zipcode != other.zipcode)
			return false;
		return true;
	}
	
}
