#create database lab5_map
use lab5_map;

create table Student( ID bigint auto_increment primary key,
                      firstName varchar(30),
		      lastName varchar(30),
                      totalCredits int
                      );
                      
create table Enrolled_Students (studentID bigint,
			        courseID bigint,
                                primary key(studentID,courseID));
                                
create table Course( ID bigint auto_increment primary key,
		     courseTitle varchar(60),
                     credits int,
                     maxEnrollment int
                    );
                     
create table Teacher( ID bigint auto_increment primary key,
		      firstName varchar(30),
		      lastName varchar(30)
                       );

create table Teacher_Courses ( teacherID  bigint,
			       courseID bigint,
                               primary key(teacherID,courseID));
ALTER TABLE enrolled_students
ADD foreign key (studentID) references student(ID) ;
ALTER TABLE enrolled_students
ADD foreign key (courseID) references course(ID) ;
ALTER TABLE teacher_courses
ADD foreign key (teacherID) references Teacher(ID) ;
ALTER TABLE teacher_courses
ADD foreign key (courseID) references course(ID) ;

INSERT INTO Student(ID,firstName,lastName,totalCredits)
Values (500,'Collins','Emma',11);
INSERT INTO Student(firstName,lastName,totalCredits)
Values ('Emily','Davis',5);
INSERT INTO Student(firstName,lastName,totalCredits)
Values ('Joshua','Walker',11);
INSERT INTO Student(firstName,lastName,totalCredits)
Values (507,'Liam','Walker',30);

INSERT INTO course(ID,courseTitle,credits,maxEnrollment)
Values (1,'Database',6,5);
INSERT INTO course(courseTitle,credits,maxEnrollment)
Values ('Statistics',5,2);
INSERT INTO course(courseTitle,credits,maxEnrollment)
Values ('Advanced programing methods',5,20);
INSERT INTO course(ID,courseTitle,credits,maxEnrollment)
Values (4,'OOP',30,1);

INSERT INTO teacher(ID,firstName,lastName)
Values(100,'Noah','Hall');
INSERT INTO teacher(firstName,lastName)
Values('Amanda','Morgan');

INSERT INTO enrolled_students(studentID,courseID)
VALUES(500,1);
INSERT INTO enrolled_students(studentID,courseID)
VALUES(500,2);
INSERT INTO enrolled_students(studentID,courseID)
VALUES(501,3);
INSERT INTO enrolled_students(studentID,courseID)
VALUES(502,1);
INSERT INTO enrolled_students(studentID,courseID)
VALUES(502,2);
INSERT INTO enrolled_students(studentID,courseID)
VALUES(507,4);


INSERT INTO teacher_courses(teacherID,courseID)
Values(100,1);
INSERT INTO teacher_courses(teacherID,courseID)
Values(100,2);
INSERT INTO teacher_courses(teacherID,courseID)
Values(101,3);
INSERT INTO teacher_courses(teacherID,courseID)
Values(101,4);

Select *
From student;
Select *
From course;	
Select *
From teacher;	

Select *
From enrolled_students;	
Select *
From teacher_courses;
