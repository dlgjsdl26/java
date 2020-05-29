package vo;


public class UserVO {
	//테이블이라고 생각하자! 테이블의 역할을 해줌
		private String id; // 아이디
		private String password; // 비밀번호
		private String name; //이름
		private int cash; // 캐쉬정보
		
		
		@Override
		public String toString() {
			return "UserVO [id=" + id + ", password=" + password + ", name="
					+ name + ", cash=" + cash + "]";
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getCash() {
			return cash;
		}
		public void setCash(int cash) {
			this.cash = cash;
		}
		
		
		
}
