import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * The class ActiveCourse is a subclass of Course
 * Stores information on semester and a list of students enrolled in a class
*/
 
public class ActiveCourse extends Course
{
	private ArrayList<Student> students; 
	private String             semester;
	private int lectureStart;
	private int lectureDuration;
	private String lectureDay;
	private int totalDuration;
	
	
	/**
	   * Constructor initializes super variables
	   * Initializes additional semester variable and creates a copy of the list of students enrolled into a class
	     from given parameters
	 */
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name,code,descr,fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>();
	   for(Student s : students)
	   {
		   this.students.add(s);
	   }
	   
	   lectureDay = "";
   }
   
   /**
	   * Method to get the semester 
	   * @return the semester of the course
	 */
   public String getSemester()
   {
	   return semester;
   }
   
   /**
	   * Prints all the students in a course (name and id)
	 */
   public void printClassList()
   {
	   for (Student s : students)
	   {
		   System.out.println("ID: " + s.getId() + " Name: " + s.getName());
	   }
   }
   /**
	   * Prints the name, id and grade of each student in a course
	 */
   public void printGrades()
   {
	   for (Student s : this.students)
	   {
		   System.out.println(s.getId() + " " +s.getName() + " " + this.getGrade(s.getId()));
	   }
   }
   
   /**
	   * Gets numeric grade for a student for a course
	   * @param Student id 
	   * @return if student found, the grade for a student in a class. Else, return 0 
	 */

   public double getGrade(String studentId)
   {
	  for (Student s : this.students)
	  {
		  if (s.getId().equals(studentId))
		  {
			  return s.getGrade(getCode()); 
		  }
	  }
	  
	  return 0; 
   }
   
   /**
	   * get description of the course from super class and adds semester and number of students
	   * @return A string of course information, semester, and number of students
	 */

   public String getDescription()
   {
	   return super.getDescription() +" semester :"+ semester +" enrolled: " + students.size();
   }
    
   
   
   /**
    * Sorts list of students by name 
    */
  
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }
   
   /**
    * Defines name comparator  
    */
  
   private class NameComparator implements Comparator<Student>
   {
		public int compare(Student s1, Student s2)
		{
			return s1.getName().compareTo(s2.getName());
		}
   }
   
   /**
    * Sorts the students in a course by id 
    */
   // Sort the students in the course by student id using the Collections.sort() method with appropriate arguments
   // Make use of a private Comparator class below
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }
   /**
    * Defines name comparator 
    */
   private class IdComparator implements Comparator<Student>
   {
		public int compare(Student s1, Student s2)
		{
			return s1.getId().compareTo(s2.getId());
		}
   }
   
  
   /**
    * Adds a student to the list of students in a course
    * @param student to add to a course
    */
   public void addStudent(Student s)
   {
	   students.add(s);
   }
   /**
    * Removes a student from the list of students in a course
    * @param student to remove from a course
    */
   public void removeStudent(Student s)
   {
	   students.remove(s);
   }
   /**
    * A method to check if a student is already enrolled
    * @param The student to check 
    * @return True if student is already enrolled, false if 
    */
   public boolean checkEnrolled(Student s1)
   {
	   for (Student s : students)
	   {
		   if (s1.equals(s))
		   {
			   return true;
		   }
	   }
	   return false;
   }
   
   
   public void setSchedule(int start1, int duration1, String day1) 
   {
	  this.lectureStart = start1;
	  this.lectureDuration = duration1;
	  this.lectureDay = day1;
   }
   
   
   public int getStart() 
   {
	   return lectureStart;
   }
   public int getDuration() 
   {
	   return lectureDuration;
   }
   public String getDay() 
   {
	   return lectureDay;
   }
   
   public void checkMax(int amount) throws RuntimeException
   {
	   totalDuration +=amount;
	   if(totalDuration > 3) 
	   {
		   throw new OverMaxException("Maximum Time Exceeded for " + super.getName());
	   }
   }
   
   
}
