Course Scheduler Tool
========

This is a Java tool used to schedule subjects, allowing a student to progress through a University course as quickly as 
possible given subject prerequisite requirements. 

###  Process 
1. Import course subject data from a text file.
2. Parse the data into a Map implementation of an adjacency list to define subject prerequisites.
3. Apply Kahn’s algorithm (priority queue modification).
4. Calculates each subject's longest prerequisite path length.
5. Apply greedy first-fit algorithm using results from Steps 3 & 4 to generate an optimized course schedule.  
Note: A course cannot be taken in the same study period as its prerequisite.

###  File format
The first line of the text file must list all subjects required to complete the course. 
All following lines will detail each subject and any required prerequisites. 

In the example below, MATH2032 must be completed before MATH1080 and MATH1080 must be completed before MATH1081. 
Example: MATH1081, MATH1080, MATH2032

### Instructions
A user will be prompted to enter a course file name for example “XBDA.txt” and how many subjects they would like to 
complete concurrently up to a maximum of 4 subjects. A schedule will be populated, and written to a text file. 

Created by Florence Sayavongsa #3056629

