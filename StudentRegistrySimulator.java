import java.util.Scanner;


public class StudentRegistrySimulator 
{
  public static void main(String[] args)
  {
	  try {
	  Registry registry = new Registry();

	  Scheduler schedular = new Scheduler(registry.courses);
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) 
		  {
			  System.out.println("No command was given");
			  System.out.println(">");
			  continue;
		  } 
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) 
		  {
			  System.out.println("Command not recognized");
			  System.out.println(">");
			  continue;
		  }
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))
		  {
			  // register a new student in registry
			  // get name and student id string 
			  // e.g. reg JohnBoy 74345
			  // Checks:
			  //  ensure name is all alphabetic characters
			  //  ensure id string is all numeric characters
			  String name = "";
			  String id = "";
			  if (commandLine.hasNextLine())
			  {
				  name = commandLine.next();
			  }
			  if (commandLine.hasNextLine())
			  {
				  id = commandLine.next();
			  }
			  
			  if (!name.isEmpty() && !id.isEmpty())
			  {
				  
				  
				  if (!isStringOnlyAlphabet(name))
				  {
					  System.out.println("Name contains illegal characters! Please try again");
					  continue;
				  }
				   if (!isNumeric(id))
				  {
					  System.out.println("ID contains illegal characters! Please try again");
					  continue;
				  }
				  if (id.length() != 5)
				  {
					  System.out.println("Error: \n Please enter a 5 digit ID");
					  continue;
				  }
				  if(registry.addNewStudent(name, id))
				  {
						System.out.println("Student " + name + " has been registered.");
				  }
					
				  else
				  {
					    System.out.println("Student " + name + " is already registered");
				  }
			  }
			  
			  else 
			  {
				  System.out.println("Please enter values!");
			  }
			  
			  
		  }
		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  // delete a student from registry
			  // get student id
			  // ensure numeric
			  // remove student from registry
			  String id = "";
			  if (commandLine.hasNextLine())
			  {
				  id = commandLine.next();
			  }
			  
			  if (!id.isEmpty())
			  {
				  if (!isNumeric(id))
				  {
					  System.out.println("ID contains illegal characters! Please try again.");
					  continue;
				  }
				 
				  else
				  {
					  registry.removeStudent(id);
					  System.out.println("Student was removed");
				  }
			  }
			  
			  else
			  {
				  System.out.print("Please enter all values!");
			  }
			  
		  }
		  
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			 // add a student to an active course
			 // get student id and course code strings
			 // add student to course (see class Registry)
			  String id = "";
			  String courseCode = "";
			  if (commandLine.hasNextLine())
			  {
				  id = commandLine.next();
			  }
			  if (commandLine.hasNextLine())
			  {
				  courseCode = commandLine.next();
			  }
			  if (!courseCode.isEmpty() && !id.isEmpty())
			  {
				  if (!isNumeric(id))
				  {
					  System.out.println("ID contains illegal characters! Please try again");
					  continue;
				  }
				  if (id.length() != 5)
				  {
					  System.out.println("Error: \n Please enter a 5 digit ID");
					  continue;
				  }
				  else
				  {
				  	registry.addCourse(id,courseCode);
				  }
			  }
			  
			  else 
			  {
				  System.out.println("Please enter values!");
			  }
			 
			 
		  }
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  // get student id and course code strings
			  // drop student from course (see class Registry)
			  String id = "";
			  String courseCode = "";
			  if (commandLine.hasNextLine())
			  {
				  id = commandLine.next();
			  }
			  if (commandLine.hasNextLine())
			  {
				  courseCode = commandLine.next();
			  }
			  
			  if (!courseCode.isEmpty() && !id.isEmpty())
			  {
				 
				  if (!isNumeric(id))
				  {
					  System.out.println("ID contains illegal characters! Please try again");
					  continue;
				  }
				  if (id.length() != 5)
				  {
					  System.out.println("Error: \n Please enter a 5 digit ID");
					  continue;
				  }
				  else
				  {
					registry.dropCourse(id,courseCode);
				  }
			  }
			  else
			  {
				  System.out.println("Please enter all values!");
			  }
			  
			 
			 registry.printClassList(courseCode);
		  }
		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  // print all active courses
			  registry.printActiveCourses();
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  // get course code string
			  // print class list (i.e. students) for this course
			  String courseCode = "";
			  if (commandLine.hasNextLine())
			  {
				  courseCode = commandLine.next();
				  registry.printClassList(courseCode);
				  
			  }
			  else
			  {
				  System.out.println("Please enter all values!");
			  }
		  }
		  else if (command.equalsIgnoreCase("PGR"))
		  {
			  // get course code string
			  // print name, id and grade of all students in active course
			  String courseCode = "";
			  if (commandLine.hasNextLine())
			  {
				  courseCode = commandLine.next();
				  registry.printGrades(courseCode); 
			  }
			  else
			  {
				  System.out.println("Please enter all values!");
			  }
		  }
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  // get student id string
			  // print all credit courses of student
			  String id = "";
			  if (commandLine.hasNextLine())
			  {
				  id = commandLine.next();
				  
			  }
			  if (!id.isEmpty())
			  {
				  if (!isNumeric(id))
				  {
					  System.out.println("ID contains illegal characters! Please try again");
					  continue;
				  }
				  if (id.length() != 5)
				  {
					  System.out.println("Error: \n Please enter a 5 digit ID");
					  continue;
				  }
				  else 
				  {
					  registry.printStudentCourses(id);
				  }
			  }
			  else
			  {
				  System.out.println("Please enter all values!");
			  }  
			  
		  }
		  else if (command.equalsIgnoreCase("PST"))
		  {
			  // get student id string
			  // print student transcript
			  String id = "";
			  if (commandLine.hasNextLine())
			  {
				  id = commandLine.next();
				 
			  }
			  if (!id.isEmpty())
			  {
				  if (!isNumeric(id))
				  {
					  System.out.println("ID contains illegal characters! Please try again");
					  continue;
				  }
				  if (id.length() != 5)
				  {
					  System.out.println("Error: \n Please enter a 5 digit ID");
					  continue;
				  }
				  else 
				  {
					  registry.printStudentTranscript(id);
				  }
			  }
			  else
			  {
				  System.out.println("Please enter all values!");
			  }
		  }
		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  // set final grade of student
			  // get course code, student id, numeric grade
			  // use registry to set final grade of this student (see class Registry)
			  String courseCode = "";
			  String id = "";
			  double grade = 0.0;
			  if (commandLine.hasNextLine())
			  {
				  courseCode = commandLine.next();
			  }
			  if (commandLine.hasNextLine())
			  {
				  id = commandLine.next();
			  }
			  if (commandLine.hasNextDouble())
			  {
				  grade = commandLine.nextDouble();
			  }
			  
			  if (!courseCode.isEmpty() && !id.isEmpty()) 
			  {
				  if (!isNumeric(id))
				  {
					  System.out.println("ID contains illegal characters! Please try again.");
					  continue; 
				  }
				 
				  else 
				  {
					  registry.setFinalGrade(courseCode, id, grade);
					  System.out.println("Final grade was set");
				  } 
			  }
			  else
			  {
				  System.out.print("Please enter all values!");
			  }
		  }
		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  // get course code
			  // sort list of students in course by name (i.e. alphabetically)
			  // see class Registry
			  String courseCode = "";
			  if (commandLine.hasNextLine())
			  {
				  courseCode = commandLine.next();
			  }
			  registry.sortCourseByName(courseCode);
		  }
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			// get course code
			// sort list of students in course by student id
			// see class Registry
			String courseCode = "";
			  if (commandLine.hasNextLine())
			  {
				  courseCode = commandLine.next();
			  }
			  registry.sortCourseById(courseCode);
		  }
		  else if (command.equalsIgnoreCase("SCH"))
		  {
			  String courseCode = "";
			  String day = "";
			  int start = 0;
			  int duration = 0;
			  String[] line = commandLine.nextLine().trim().split("\\s+");
			  			  
			  if (line.length == 4) 
			  {
				  courseCode = line[0];
				  day = line[1];
				  start = Integer.parseInt(line[2]);
				  duration = Integer.parseInt(line[3]);
				  schedular.setDayAndTime(courseCode, day, start, duration);
			  }
			  
			  else
			  {
				  System.out.print("Please enter all values!");
			  }

			
		  }
		  
		  else if (command.equalsIgnoreCase("ASCH")) 
		  {
			  String courseCode = "";
			  int duration = 0;
			 
			  if (commandLine.hasNextLine()) 
			  {
				  courseCode = commandLine.next();
			  }
			  if (commandLine.hasNextLine()) 
			  {
				  duration =  commandLine.nextInt();
			  }
				  	  
			  if (!courseCode.isEmpty() && duration != 0)
			  {
				  schedular.autoSchedular(courseCode,duration);
			  }
			  else
			  {
				  System.out.println("Please enter all values!");
			  } 
			  
		  }
		  else if (command.equalsIgnoreCase("CSCH"))
		  {
			  String courseCode = "";
			  if (commandLine.hasNextLine())
			  {
				  courseCode = commandLine.next();
			  }
			  if (!courseCode.isEmpty()) 
			  {
				  schedular.clearSchedule(courseCode);  
			  }
			  else 
			  {
				  System.out.println("Please enter all values!");
			  }
		  }
		  
		  else if (command.equalsIgnoreCase("PSCH")) 
		  {
			  schedular.printSchedule();
		  }
		  
		  else 
		  {
			  System.out.println("Command not recognized! Please try again");
		  }
		  System.out.print("\n>");
	  }
	  }catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
	
  }
  /**
   * A method that checks if a string only contains letters 
   * @return True if all characters are letters, false if not
  */  
  private static boolean isStringOnlyAlphabet(String str) 
  { 
      // write method to check if string str contains only alphabetic characters
	  for (int i = 0; i < str.length(); i++)
	  {
		  if (!Character.isLetter(str.charAt(i)))
		  {
			  return false;
		  }
	  }
	  return true;
  } 
  
  /**
   * A method that checks if a string only contains digits or if the string is 5 characters long
   * @return True if all characters are letters, false if not
  */
  public static boolean isNumeric(String str)
  {
      // write method to check if string str contains only numeric characters
	  for (int i = 0; i < str.length(); i++)
	  {
		  if (!Character.isDigit(str.charAt(i)))
		  {
			  return false;
		  }
	  }
	  return true;
  }
  
  
}