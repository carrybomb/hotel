package hotelServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class hotelDTO {
	private int hotelNum;
	private String hotelName;
	private String hotelImagePath;
	private double star;
	private String hotelLocation;
	private int hotelGrade;

	
	public hotelDTO(String name, String img, String location, int grade) {
		this.hotelName=name;
		this.hotelImagePath=img;
		this.hotelLocation=location;
		this.hotelGrade=grade;
	}
}