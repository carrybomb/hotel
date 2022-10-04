package hotelServiceDTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class bookingListDTO {
	private int bookingNum;
	private int memberNum;
	private int roomNum;
	private int guestNum;
	private String checkin_date;
	private String checkout_date;
	private Date booked_date;
	private Date booked_cancel_date;
	private String member_name;
	
	
	public bookingListDTO(int memberNum ,int roomNum, int guestNum, String checkIn, String checkOut) {
		this.memberNum = memberNum;
		this.roomNum = roomNum;
		this.guestNum = guestNum;
		this.checkin_date = checkIn;
		this.checkout_date = checkOut;
	}


	
}
