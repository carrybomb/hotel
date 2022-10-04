package hotelServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class roomDTO {
	private int roomNum;
	private int hotelNum;
	private String roomName;
	private String roomImage;
	private String roomState;
	private int maxGuestNum;
	private int price;
	private String category;
	
	public roomDTO(int roomnum,int hotelnum, String roomname, String img, int max_guest, int price, String category) {
		this.roomNum=roomnum;
		this.hotelNum=hotelnum;
		this.roomName=roomname;
		this.roomImage=img;
		this.maxGuestNum=max_guest;
		this.price=price;
		this.category=category;
	}
	public roomDTO(int hotelnum, String roomname, String img, int max_guest, int price, String category) {
		this.hotelNum=hotelnum;
		this.roomName=roomname;
		this.roomImage=img;
		this.maxGuestNum=max_guest;
		this.price=price;
		this.category=category;
	}

}

