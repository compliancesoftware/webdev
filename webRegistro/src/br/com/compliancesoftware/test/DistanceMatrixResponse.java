package br.com.compliancesoftware.test;


/**
 * Bean usado para consumir o webservice de Distance Matrix do google.
 * @author Compliance Software *by Douglas Fernandes*
 *
 */
public class DistanceMatrixResponse 
{
	private String status;
	private String origin_address;
	private String destination_address;
	private Row row;
	
	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public String getDestination_address() {
		return destination_address;
	}

	public void setDestination_address(String destination_address) {
		this.destination_address = destination_address;
	}

	public String getOrigin_address() {
		return origin_address;
	}

	public void setOrigin_address(String origin_address) {
		this.origin_address = origin_address;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
		return this.status;
	}
	
	public class Duration
	{
		private String text;
		private String value;
		
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public class Distance
	{
		private String text;
		private String value;
		
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public class Element
	{
		private Duration duration;
		private Distance distance;
		private String status;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Duration getDuration() {
			return duration;
		}
		public void setDuration(Duration duration) {
			this.duration = duration;
		}
		public Distance getDistance() {
			return distance;
		}
		public void setDistance(Distance distance) {
			this.distance = distance;
		}
	}
	
	public class Row
	{
		private Element element;
		
		public void setElement(Element element)
		{
			this.element = element;
		}
		
		public Element getElement()
		{
			return this.element;
		}
	}
}
