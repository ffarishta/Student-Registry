
public class Course 
{
	private String code;
	private String name;
	private String description;
	private String format;
	/**
	   * The initial constructor method that initializes code,name,description, and format to an empty string
	 */
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}
	 
	/**
	   * A constructor method that initializes code,name,description, and format the parameters
	  */
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}
	 
	/**
	   * A method that gets the course code
	   * @return A courses code
	  */
	public String getCode()
	{
	   return code;
	}
	/**
	   * A method that gets the course name
	   * @return A courses name
	  */  
	public String getName()
	{
	  return name;
	}
	  
	/**
	   * A methods that gets the course format (including times of labs, lectures and tutorials)
	   * @return A courses format
	  */
	public String getFormat()
	{
	  return format;
	}
	/**
	   * A methods that gets the initial description
	   * @return A courses description 
	  */
	public String onlydesc()
	{
	  return description;
	}
	
	/**
	   * A methods that gets the description (includes name and format)
	   * @return String that contain name description and format
	  */  
	public String getDescription()
	{
	  return code +" - " + name + "\n" + description + "\n" + format;
	}
	
	/**
	   * A methods that gets the course info
	   * @return return course code and name
	  */
	 public String getInfo()
	 {
	   return code +" - " + name;
	 }
	 
	 /**
	   * A methods that converts numeric score to a letter grade
	   * @return Letter grade of numeric score
	  */
	 
	 public static String convertNumericGrade(double score)
	 {
		 
		 // fill in code
		if (100 >= score && score >= 90)
		{
			return "A+";
		}
		if ( 89 >= score && score >= 85)
		{
			return "A";
		}
		
		if ( 84 >= score && score >= 80)
		{
			return "A-";
		}
		
		if (79 >= score && score >= 77)
		{
			return "B+";
		}
		
		if (76 >= score && score >= 73)
		{
			return "B";
		}
		
		if (72 >= score && score >= 70)
		{
			return "B-";
		}
		
		if (69 >= score && score >= 67)
		{
			return "C+";
		}
		
		if (66 >= score && score >= 63)
		{
			return "C";
		}
			
		if (62 >= score && score >= 60)
		{
			return "C-";
		}
		
		if (59 >= score && score >= 57)
		{
			return "D+";
		}
		
		
		if (56 >= score && score >= 53)
		{
			return "D";
		}
		
		if (52 >= score && score >= 50)
		{
			return "D-";
		}
		
		if (score < 50)
		{
			return "F";
		}
		return "Please enter a number between 0 and 100";
		 }
	 
}
