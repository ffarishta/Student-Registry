import java.util.*;
import java.util.TreeMap;
/**
 * The Registry class Scheduler is responsible for scheduling active courses
*/
public class Scheduler 
{
	 private TreeMap<String,ActiveCourse> schedule;
	 private ArrayList<String >days = new ArrayList<String>();
	 private ArrayList<Integer> times = new ArrayList<Integer>(); 
	 private String[][] s = new String[9][5];
	 
	 
	 /**
	    * Constructor method initializes list of courses and adds days of the week and time periods to a list  
	    * @param The Map of active courses 
	    */
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses; 
	  days.add("Mon");days.add("Tue"); days.add("Wed"); days.add("Thu");days.add("Fri");
	  times.add(800);times.add(900);times.add(1000);times.add(1100);times.add(1200);times.add(1300);times.add(1400);times.add(1500); times.add(1600);
	  
	  for(String[] arr : s) {
          for(int i = 0; i < arr.length ; i++) 
          {
        		  arr[i] = "";
          }
      }
	  
	}
	
	 /**
	    * A method that sets the day, start time, and duration of a class, then adds it to the appropriate time slot  
	    * @param courseCode, day, start time , and duration 
	  */
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws RuntimeException
	{
		
		try {
			String day1 = day.substring(0,1).toUpperCase()+day.substring(1);
			String course1 = courseCode.substring(0,3).toUpperCase()+courseCode.substring(3);
			checkExceptions(course1, day1, startTime, duration);
			for (ActiveCourse a : schedule.values()) 
			{
				if (a.getCode().equalsIgnoreCase(courseCode)) 
				{
					a.checkMax(duration);
					a.setSchedule(startTime, duration , day1);
					putSchedule(a); 
				}
			}
		
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	 /**
	    * A method that places classes into a schedule 
	    * @param course
	 */
	public void putSchedule(ActiveCourse course) 
	{
		int indexTime = times.indexOf(course.getStart());
		int indexDay = days.indexOf(course.getDay());
		int duration = course.getDuration();
	
	    if (checkTime(indexTime,indexDay,duration)) 
		{
	    	for (int i = 0 ; i < duration ; i ++) 
	    	{
				s[indexTime+i][indexDay] = course.getCode();
			}
		}
		else 
		{
				throw new LectureTimeCollisionException("Lecture Time Collision"); 
		}
	}
		
	

	 /**
	    * A method to clear a class from schedule 
	    * @param course code 
	  */
	public void clearSchedule(String courseCode)
	{
		// see Assignment doc
		for(String[] arr : s) {
	          for(int i = 0; i < arr.length ; i++) 
	          {
	        		if (arr[i].equalsIgnoreCase(courseCode)) 
	        		{
	        			arr[i] = "";
  
	        		} 
	          }
	          
	      }
	}
	
	 /**
	    * Prints schedule 
	    * @param The list of active courses 
	 */
	public void printSchedule()
	{
		String output = "\t ";
		for (String i : days) 
		{
			output += " " + i + "\t";
		}
		System.out.println(output);
		
		for(int x = 0; x < s.length; x++)
		{
			
			if (Integer.toString(times.get(x)).length() == 3) 
			{
				System.out.print("0" + times.get(x));
			}
			else 
			{
				System.out.print(times.get(x));
			}
		
			for(int b = 0 ; b < s[x].length; b++)
			{
				 System.out.print("\t" + s[x][b]);
			}
			
			System.out.println();
		}
	}
	
	 /**
	    * Automatically places class into a random place in schedule
	    * @param course code and duration of course 
	 */
	public void autoSchedular(String courseCode, int duration) 
	{	
		
		try {
		Random generator = new Random();
		boolean foundSpot = false;
		
		while (!foundSpot) 
		{
			int randTime = generator.nextInt(times.size());
			int randday = generator.nextInt(days.size());
			ActiveCourse course = schedule.get(courseCode);
			if (checkTime(randTime,randday,duration)) 
			{
				checkExceptions(courseCode, days.get(randday), times.get(randTime), duration);
				course.checkMax(duration);
				for (int i = 0 ; i < duration ; i ++) 
				{
					s[randTime+i][randday] = course.getCode();
					foundSpot = true;
				}
			}
		}
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	    * Checks if time slots are available 
	    * @param index of time, index of date and duration of class
	 */
	public boolean checkTime(int time , int date, int duration) 
	{
		for (int i = 0 ; i < duration ; i ++) 
		{
			if (time + i > times.size()-1) 
			{
				return false;
			}
			
			if (s[time+i][date] != "") 
			{
				return false;
			}
		}
		return true;
	}
	/**
	    * A method that checks for user exceptions
	 */
	public void checkExceptions(String courseCode, String day, int startTime, int duration) 
	{
		boolean contains = days.stream().anyMatch(day::equalsIgnoreCase);	
		if (!contains)
		{
			throw new InvalidDayException("Invalid Lecture Day: "+ day);
		}
		
		int value = startTime+(duration*100);
		if (((startTime < 800 || startTime > 1600) || value > 1700))
		{
			throw new InvalidTimeException("Invalid Lecture Start Time or End Time");
		}
		
		if (duration < 1 || duration > 3 )
		{
			throw new InvalidDurationParameterException("Invalid Duration");
		} 
		
		if (!schedule.containsKey(courseCode))
		{
			throw new UnknownCourseException("Unknown course: "+ courseCode);
		}
	}
	
}