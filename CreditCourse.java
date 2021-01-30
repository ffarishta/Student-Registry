/**
    * The class CreditCourse is a subclass of Course.
    * Also stores information on semester, grade, and active status 
 */

public class CreditCourse extends Course 
{
	private String semester;
	private double grade;
	private boolean active;
	/**
	   * Constructor initializes super variables
	   * Initializes additional semester to parameter 
	   * Initializes grade to 0.0 and active to true 
	 */
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		super(name,code,descr,fmt);
		this.semester = semester;
		this.grade = 0.0;
		active = true;
	}
	
	/**
	   * Get the status of active 
	   * @return the status of active 
	 */
	public boolean getActive()
	{
		return active;
	}
	
	/**
	   * Set active to true 
	 */
	public void setActive()
	{
		active = true;
	}
	
	/**
	   * Set active to false
	 */
	public void setInactive()
	{
		// add code
		active = false;
	}
	/**
	   * Prints out info about this course plus which semester and the grade achieved 
	   * @return A string containing information of course, semester, and grade 
	 */
	public String displayGrade()
	{
		return getInfo() + " Semester - " + semester + " Grade - " + super.convertNumericGrade(grade) ;
	}
	
	/**
	   * Set the grade for a student in a class
	 */
	public void setGrade(double setGrade)
	{
		this.grade = setGrade;
	}
	/**
	   * Get the grade for a student in a class
	   * @return grade 
	 */
	public double getGrade()
	{
		return grade;
	}
	
	
}