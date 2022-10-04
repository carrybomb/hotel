package hotelServiceDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class memberDTO {
	private int memberNum;
	private String id;
	private String pw;
	private String memberName;
	private String tel;
	private String email;
	private int memberGrade;
	private String authority;
	
	public memberDTO(String name, String id, String pw, String tel, String email) {
		this.memberName = name;
		this.id = id;
		this.pw = pw;
		this.tel = tel;
		this.email = email;
	}
	
	public memberDTO(String id, String name, String tel, String email, int grade) {
		this.id = id;
		this.memberName = name;
		this.tel = tel;
		this.email = email;
		this.memberGrade = grade;
	}
}
