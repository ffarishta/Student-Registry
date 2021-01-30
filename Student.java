import java.util.ArrayList;

/**
 * The Student class stores information on a students name, Id, 
 * and the courses they are taking or have already taken.
 */
// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student implements Comparable<Student>
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  /**
 
 * Constructor method to initialize courses to an empty list, also name and id to the parameters 
 */
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  
  /**
 * Gets the students Id
 * @return students Id
 */
  
  public String getId()
  {
	  return id;
  }
 /**
 * gets the students name
 * @return students name
 */
  public String getName()
  {
	  return name;
  }
  
 /**
 * Creates new credit course object
 * Adds credit course to the list of courses for a student
 */

  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  CreditCourse course = new CreditCourse(courseName,courseCode,descr,format,sem,grade);
	  course.setActive();
	  courses.add(course);
  }
  
  
 /**
 * Prints a students transcript for all inactive courses
 */
  public void printTranscript()
  {
	  for (CreditCourse c: courses)
	  {
		  if (!c.getActive())
		  {
			  System.out.println(c.displayGrade());
		  }
	  
	  }
  }
  
 /**
 * Prints all active courses student is currently enrolled in
 */
  public void printActiveCourses()
  {
	 for (CreditCourse e: courses)
	  {
		  if (e.getActive())
		  {
			  System.out.println(e.getDescription());
		  }
	  
	  }
  }
  
  /**
 * Removes a credit course from the list of courses for a student currently enrolled in
 * @param code of course to remove
 */
  // Drop a course (given by courseCode)
  // Find the credit course in courses arraylist above and remove it
  // only remove it if it is an active course
  public void removeActiveCourse(String courseCode)
  {
	 for (CreditCourse e: courses)
	  {
		  String c = e.getCode();
		  if (c.equalsIgnoreCase(courseCode) && e.getActive())
		  {
			  courses.remove(e);
			  break;
		  }
	  }
  }
  
  /**
 * returns student ID and name 
 * @return a string containing student ID and name
 */
  
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  /**
 * override equals method inherited from superclass Object
 * @return true if Student objects are the same 
 * @return false if Student objects are not the same
 */
  
  public boolean equals(Object other)
  {
	  Student otherStudent = (Student) other; 
	  if (name.equals(otherStudent.getName()) && id.equals(otherStudent.getId()))
	  {
		  return true;
	  }
	  return false;
  }
  
  /**
   * Searches through all courses and gets grade for a specific course
   * @return the grade if course is found and 0.0 if course not found
   */
    
  public double getGrade(String courseCode)
  {
	  for (CreditCourse c: courses)
	  {
		  if (c.getCode().equals(courseCode))
		  {
			  return c.getGrade();
		  }
	  }
	  return 0.0;
  }
  
  /**
   * Compares Names in the way required by the interface
   */
    
  public int compareTo(Student other)
  {
	  return this.name.compareTo(other.getName());
  }

  
  
}
