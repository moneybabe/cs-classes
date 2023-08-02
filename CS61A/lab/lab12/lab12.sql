.read data.sql


CREATE TABLE bluedog AS
  SELECT color, pet FROM students
  WHERE color = 'blue' AND pet = 'dog';

CREATE TABLE bluedog_songs AS
  SELECT color, pet, song FROM students
  WHERE color = 'blue' AND pet = 'dog';


CREATE TABLE matchmaker AS
  SELECT t1.pet, t1.song, t1.color, t2.color
  FROM students AS t1, students AS t2
  WHERE t1.pet = t2.pet AND t1.song = t2.song AND t1.time != t2.time AND t1.time < t2.time;


CREATE TABLE sevens AS
  SELECT seven FROM students, numbers
  WHERE number = 7 AND "7" = "True" AND students.time = numbers.time;


CREATE TABLE smallest_int_having AS
  SELECT time, smallest from students
  GROUP BY smallest HAVING COUNT(*) = 1;
