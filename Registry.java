import java.util.*;
import java.io.*;
/**
 * The Registry class contains all the methods that perform all the function of the registry
*/
public class Registry
{
  // private ArrayList<Student>      students = new ArrayList<Student>();
   public TreeMap<String,ActiveCourse> courses = new TreeMap<String,ActiveCourse>(String.CASE_INSENSITIVE_ORDER);
   private TreeMap<String,Student> students = new TreeMap<String,Student>();
   /**
	   * A constructor method that adds students and courses into different maps from a file 
	 */
   public Registry() throws Exception 
   {
	// Add some students
	   // in A2 we will read from a file
	   
	   
	   try {
		   Scanner in = new Scanner(new File("students.txt"));
		   String name = ""; String id = ""; String line; int count = 0;
	   
	   while (in.hasNextLine())
	   {
		   if (count == 1) 
		   {
			   throw new Exception("A value is missing from courses.txt");
		   }
		   line = in.nextLine();
		   Scanner scanner = new Scanner(line);
		   count = 0;
		   while (scanner.hasNext())
		   {
			   if (count == 0)
			   {
				   name = scanner.next();
			   }
			   if (count == 1)
			   {
				   id = scanner.next();;
			   }
			   count++;
		   }
		   students.put(id,new Student(name,id));
		   scanner.close();
		 }
	   in.close();  
	   }
	   catch (FileNotFoundException e)
		 {
			 System.out.println("File not found");
			 System.exit(0);
		 }
	   
	   
	     try {
			   Scanner in = new Scanner(new File("courses.txt"));
			   String courseName = ""; String courseCode = ""; String descr = "";  String format = "";
			   Random random = new Random(); String randomKey;
			   ArrayList<Student> list = new ArrayList<Student>();
		   
		   while (in.hasNextLine())
		   {
				   if (in.hasNext())
				   {
					   courseName = in.nextLine();
				   }
				   if (in.hasNext())
				   {
					   courseCode = in.nextLine();
				   }
				   if (in.hasNext()) 
				   {
					   descr = in.nextLine();
				   }
				   if (in.hasNext()) 
				   {
					   format = in.nextLine();
				   }
				   else 
				   {
					   throw new Exception("A value is missing from courses.txt");
				   }
			 
			   
			  
			  
				  List<String> keys = new ArrayList<String>(students.keySet()); 
				  while (list.size() <= 3) // adds random students into classes 
				   {
					   randomKey = keys.get(random.nextInt(keys.size()));
					   students.get(randomKey).addCourse(courseName, courseCode, descr, format, "W2020", 0.0);
					   keys.remove(randomKey);
					   list.add(students.get(randomKey));
				   }
			  
			  
			   
			
			   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
			   list.clear();
			 }
		   in.close();  
		   }
		   catch (FileNotFoundException e)
			 {
				 System.out.println("File not found");
			 }
	     
	     
	   
   }
   
   
   
   /**
	   * Adds student to registry   
	   * @param Student name and id
	   * @return false if the student is already enrolled, true if not enrolled 
	 */

   public boolean addNewStudent(String name, String id)
   {
	   if (findStudent(id) == null) 
	   {
		   Student newStudent = new Student(name,id);
		   students.put(id,newStudent);
		   return true;
	   }
	   return false;
   }
   
   /**
	   * Finds and removes student from registry 
	   * @param Student id
	   * @return true if student is found, false if not found
	 */
   public boolean removeStudent(String studentId)
   {
	   if (findStudent(studentId) != null) 
	   {
		   students.remove(studentId);
		   return true;
	   }
	   return false;
   }
   /**
	   * Print all students in registry 
	 */
   // Print all registered students
   public void printAllStudents()
   {
	   for (Student s : students.values())
	   {
		   System.out.println("ID: " + s.getId() + " Name: " + s.getName() );   
	   }
	   
   }
   
   /**
	   * Adds student to an active course
	   * @param Student name and and course code
	 */
   
   public void addCourse(String studentId, String courseCode) throws Exception
   {
	   Student stu = findStudent(studentId);
	   ActiveCourse course = findCourse(courseCode);
	   
	   if (stu == null) 
	   {
		   System.out.println("Student was not found");
		   return;
	   }
	   else if (course == null) 
	   {
		   System.out.println("Course was not found");
		   return;
	   }
	   
	   else if (course.checkEnrolled(stu)) 
	   {
		   System.out.println("Student has already taken class");
		   return;
	   }
	   else if (!course.checkEnrolled(stu) && stu != null && course != null) 
	   {
		   course.addStudent(stu);
		   stu.addCourse(course.getName(), course.getCode(), course.onlydesc(), course.getFormat(), course.getSemester(), 0.0);
	   }
	   
   }
   /**
	   * removes student from active course
	   * @param Student id and course code
	 */
   public void dropCourse(String studentId, String courseCode)
   {
	   Student stu = findStudent(studentId);
	   ActiveCourse course = findCourse(courseCode);
	   
	   if (stu == null) 
	   {
		   System.out.println("Student was not found");
		   return;
	   }
	   else if (course == null) 
	   {
		   System.out.println("Course was not found");
		   return;
	   }
	   
	   else if (!course.checkEnrolled(stu)) 
	   {
		   System.out.println("Student is not enrolled in class");
		   return;
	   }
	   else 
	   {
		   course.removeStudent(stu);
		   stu.removeActiveCourse(courseCode);
	   }
	   
   }
   /**
	   * Prints all active courses
	 */
   // Print all active courses
   public void printActiveCourses()
   {
	   for (ActiveCourse ac : courses.values())
	   {
		   System.out.println(ac.getDescription() + "\n");
	   }
   }
   
   /**
	   * Prints a list of students in an active course
	 */
   // Print the list of students in an active course
   public void printClassList(String courseCode)
   {
	   ActiveCourse course = findCourse(courseCode);
	   
	   if (course == null) 
	   {
		   System.out.println("Course was not found");
		   return;
	   }
	   
	   course.printClassList();
   }
   /**
	   * Sorts class list by student name 
	   * @param  Course code
	 */
   // Given a course code, find course and sort class list by student name
   public void sortCourseByName(String courseCode)
   {
	   
  ActiveCourse course = findCourse(courseCode);
	   
	   if (course == null) 
	   {
		   System.out.println("Course was not found");
		   return;
	   }
	   
	   course.sortByName();
   }
   
   /**
	   * Sorts class list by student id
	   * @param  Course code
	 */
   // Given a course code, find course and sort class list by student name
   public void sortCourseById(String courseCode)
   {
	   ActiveCourse course = findCourse(courseCode);
	   
	   if (course == null) 
	   {
		   System.out.println("Course was not found");
		   return;
	   }
	   
	   course.sortById(); 
   }
   /**
	   * Finds course and prints student names and grades
	   * @param  Course code
	 */
   public void printGrades(String courseCode)
   {
	   ActiveCourse course = findCourse(courseCode);
	   
	   if (course == null) 
	   {
		   System.out.println("Course was not found");
		   return;
	   }
	   
	   course.printGrades();;
   }
   /**
	   * Prints all active courses of a student
	   * @param  student id
	 */
   public void printStudentCourses(String studentId)
   {
	   Student stu = findStudent(studentId);
	   if (stu == null) 
	   {
		   System.out.println("Student was not found");
		   return;
	   }
	   stu.printActiveCourses();
	   
   }
   /**
	   * Prints student transcript of inactive courses   
	   * @param  student id
	 */
   public void printStudentTranscript(String studentId)
   {   
	   Student stu = findStudent(studentId);
	   if (stu == null) 
	   {
		   System.out.println("Student was not found");
		   return;
	   }
	   stu.printTranscript();
   }
   /**
	   * Sets final grade for a class and sets course as inactive 
	   * @param  Course code, Student id and grade for the course
	 */

   public void setFinalGrade(String courseCode, String studentId, double grade)
   {   
	   Student stu = findStudent(studentId);
	   ActiveCourse course = findCourse(courseCode);
	   
	   if (stu == null) 
	   {
		   System.out.println("Student was not found");
		   return;
	   }
	   else if (course == null) 
	   {
		   System.out.println("Course was not found");
		   return;
	   }
	   for (CreditCourse c : stu.courses)
	   {
		   if (c.getCode().equalsIgnoreCase(courseCode))
		   {
			   c.setGrade(grade);
			   c.setInactive();
		   }
	   }
   }
   
   /**
	   * Looks through students map and finds students
	   * @param  student id
	   * @return null if student not found and student if found
	 */
   
   public Student findStudent(String id) 
   {
	   for (String s : students.keySet()) 
	   {
		   if (s.equalsIgnoreCase(id)) 
		   {
			  return students.get(id); 
		   }
	   }
	   return null;
   }
   /**
	   * Looks through courses map and finds course
	   * @param  course code
	   * @return null if course not found and student if found
	 */
   public ActiveCourse findCourse(String courseCode) 
   {
	   
	   String code = courseCode.substring(0,3).toUpperCase() + courseCode.substring(3) ;
	   for (String c : courses.keySet()) 
	   {
		   if (c.equals(code)) 
		   {
			   
			  return courses.get(code); 
		   }
	   }
	   return null;
   }
   
  
}
