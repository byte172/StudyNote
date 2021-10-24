package demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calendar {

	public static void main(String[] args) throws Exception {
		String str = "2019-10-3";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(str);
		GregorianCalendar c = new GregorianCalendar();

		c.setTime(date);

		System.out.println("日\t一\t二\t三\t四\t五\t六");
		c.set(java.util.Calendar.DAY_OF_MONTH,1);

		for(int i=0;i<c.get(java.util.Calendar.DAY_OF_WEEK)-1;i++) {
			System.out.print("\t");
		}

		int day = c.get(java.util.Calendar.DAY_OF_MONTH);
		int days = c.getActualMaximum(java.util.Calendar.DATE);

		for(int i=1;i<=days;i++) {

			if(day==c.get(java.util.Calendar.DAY_OF_MONTH)) {
				System.out.print(c.get(java.util.Calendar.DAY_OF_MONTH)+"*\t");
			}else {
				System.out.print(c.get(java.util.Calendar.DAY_OF_MONTH)+"\t");
			}

			if(c.get(java.util.Calendar.DAY_OF_WEEK)==java.util.Calendar.SATURDAY) {
				System.out.println();
			}

			c.add(java.util.Calendar.DAY_OF_MONTH, 1);
		}

		
	}
}
