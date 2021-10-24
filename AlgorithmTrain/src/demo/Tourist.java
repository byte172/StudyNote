package demo;

public class Tourist {
		int age;
		int ticketPrice;
		public void setAge(int age) {
			this.age=age;
		}
		public void ticket() {
			if(age>0&&age<12) {
				ticketPrice=20;
			}
			else if (age>=12&&age<40) {
				ticketPrice=40;
			}
			System.out.println("门票价格:"+ticketPrice);
		}
		
	}

