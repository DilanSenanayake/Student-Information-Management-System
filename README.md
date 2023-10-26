Student Information Management System

Creating a REST API for a student information system with two entities, Student and Course, where a 
student can enroll in many courses, involves designing endpoints for performing various CRUD (Create, 
Read, Update, Delete) operations on both entities. Below, I describe the REST endpoints and their 
associated parameters for this system: 

Tasks 
1. Develop a Spring REST API with the given endpoints. 
2. You can define attributes of the Student and Course entities as you wish. 
3. Use a MongoDB as the storage. 
Evaluation Criteria: 
1. Completeness of work done. 
2. Code Quality 
3. Validation 
4. Best Practices 

PS: (Please make sure to follow these criteria right from the start of our implementation. It's important 
to focus on criteia rather than leaving things unfinished and trying to cover the whole project at once) 

Student Endpoints: 

1. Create a Student 
• Endpoint: POST /students 
• Parameters: None in the URL; Student data in the request body (e.g., JSON) 
• Description: Create a new student record with the provided student data.

2. Get All Students 
• Endpoint: GET /students 
• Parameters: None 
• Description: Retrieve a list of all students.

3. Get Student by ID 
• Endpoint: GET /students/{studentId} 
• Parameters: {studentId} - The unique identifier of the student. 
• Description: Retrieve the details of a specific student by their ID.

4. Update Student Information 
• Endpoint: PUT /students/{studentId} 
• Parameters: {studentId} - The unique identifier of the student in the URL; Student data in 
the request body (e.g., JSON) 
• Description: Update the information of an existing student using their ID.

5. Delete Student 
• Endpoint: DELETE /students/{studentId} 
• Parameters: {studentId} - The unique identifier of the student to be deleted. 
• Description: Delete a student record by their ID.

Course Endpoints: 

6. Create a Course 
• Endpoint: POST /courses 
• Parameters: None in the URL; Course data in the request body (e.g., JSON) 
• Description: Create a new course with the provided course data.

7. Get All Courses 
• Endpoint: GET /courses 
• Parameters: None 
• Description: Retrieve a list of all courses.

8. Get Course by ID 
• Endpoint: GET /courses/{courseId} 
• Parameters: {courseId} - The unique identifier of the course. 
• Description: Retrieve the details of a specific course by its ID.

9. Update Course Information 
• Endpoint: PUT /courses/{courseId} 
• Parameters: {courseId} - The unique identifier of the course in the URL; Course data in the 
request body (e.g., JSON) 
• Description: Update the information of an existing course using its ID.

10. Delete Course 
• Endpoint: DELETE /courses/{courseId} 
• Parameters: {courseId} - The unique identifier of the course to be deleted. 
• Description: Delete a course record by its ID. 
Enrollment Endpoints (Many-to-Many Relationship):

11. Enroll Student in a Course 
• Endpoint: POST /students/{studentId}/enrollments/{courseId} 
• Parameters: {studentId} - The unique identifier of the student in the URL; Course ID in the 
request body (e.g., JSON) 
• Description: Enroll a student in a specific course by providing the student's ID and the course 
ID. 

12. Get Courses Enrolled by a Student 
• Endpoint: GET /students/{studentId}/enrollments 
• Parameters: {studentId} - The unique identifier of the student. 
• Description: Retrieve a list of courses in which a specific student is enrolled. 
13. Get Students Enrolled in a Course 
• Endpoint: GET /courses/{courseId}/enrollments 
• Parameters: {courseId} - The unique identifier of the course. 
• Description: Retrieve a list of students who are enrolled in a specific course.

14. Unenroll Student from a Course 
• Endpoint: DELETE /students/{studentId}/enrollments/{courseId} 
• Parameters: {studentId} - The unique identifier of the student; {courseId} - The unique 
identifier of the course. 
• Description: Remove a student's enrollment from a specific course by providing both the 
student's ID and the course ID. 

Swagger: localhost:8080/swagger-ui/index.html