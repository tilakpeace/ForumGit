package tilak.sample.com.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {


    public String getDateAndTime() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
       // c.add(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(c.getTime());
    }

    public static void main(String[] args) {
       Utility utility=new Utility();
        System.out.println(utility.getDateAndTime());
    }


/*	public String getDateTime() {
        String format = "HH:mm:ss";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return getTodaysDateNepali() + " " + sdf.format(cal.getTime());
	}

	public String getTime() {
		String format = "HH:mm";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	public String formatToOracle(String englishDate) {
		java.sql.Date d = java.sql.Date.valueOf(englishDate);
		SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yy");
		return sdf.format(d);
	}

	public String getTodaysDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yy");
		return sdf.format(d);
	}

	public String getMonth() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(d);
	}

	public String getNepaliYear() {
		String td = getTodaysDateNepali();
		// String ny = td.substring(0,4);
		// System.out.println(ny);
		return td.substring(0, 4);
	}

	public String getNepaliMonth() {
		String td = getTodaysDateNepali();
		return td.substring(5, 7);
	}

	public String getYear() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(d);
	}*/



	/*public String getSoDate() {
        Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	public String getTodaysDateNepali() {

		return getNepaliDate(getTodaysDate());

	}

	public String getNepaliDate(String date) {
		String sql = "SELECT cmmn1.to_bs(?) as nep FROM dual";
		List lst = this.queryForList(sql, new Object[] { date });
		Map m = (Map) lst.get(0);
		return m.get("NEP").toString();

	}

	public String getEnglishDate(String nepaliDate) {
		String sql = "SELECT trunc(cmmn1.to_ad(?)) as eng FROM dual";
		List lst = this.queryForList(sql, new Object[] { nepaliDate });
		Map m = (Map) lst.get(0);
		String englishDate = m.get("ENG").toString();
		String[] date = englishDate.split(" ");
		return date[0].trim();
	}

	public String getDateAndTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(d);
	}



	@Override
	public String inWord(String amt) {
		// TODO Auto-generated method stub
		String sql = "select to_string(?) word from dual";

		List lst = this.queryForList(sql, new Object[] { amt });
		Map m = (Map) lst.get(0);
		// return Integer.parseInt(m.get("SSS").toString());
		return m.get("WORD").toString();

	}*/
}
