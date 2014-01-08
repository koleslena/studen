
DROP DATABASE IF EXISTS students;
 
CREATE DATABASE students DEFAULT CHARACTER SET 'utf8';
 
USE students;
 
 
CREATE TABLE ST_ROLE
(
   ROLE_ID            int(10) NOT NULL AUTO_INCREMENT,
   ROLE_NAME          varchar(256),
   ROLE_SPRING_NAME   varchar(256) NOT NULL,
   primary key (ROLE_ID)
);



CREATE TABLE ST_USER
(
   USER_ID         int(10) NOT NULL AUTO_INCREMENT,
   USER_NAME       varchar(256) NOT NULL,
   USER_ROLE       int(10),
   USER_PASSWORD   varchar(256) NOT NULL,
   primary key (USER_ID)
);

ALTER TABLE ST_USER
ADD KEY fk_user_role (USER_ROLE);


ALTER TABLE ST_USER
ADD CONSTRAINT FK_USER_ROLE
FOREIGN KEY (USER_ROLE)
REFERENCES ST_ROLE (ROLE_ID)
ON UPDATE NO ACTION
ON DELETE CASCADE;


CREATE TABLE ST_GROUP
(
  GROUP_ID int(10) unsigned not null auto_increment,
  GROUP_NAME varchar(255) not null,
  GROUP_CURATOR varchar(255) not null,
  GROUP_SPECIALITY varchar(255) not null,
  primary key (GROUP_ID)
) engine=InnoDB;

CREATE TABLE ST_STUDENT
(
  STUDENT_ID int(10) unsigned not null auto_increment,
  STUDENT_FIRST_NAME varchar(255) not null,
  STUDENT_SUR_NAME varchar(255) not null,
  STUDENT_PATRONYMIC varchar(255) not null,
  STUDENT_BIRTH_DATE date not null,
  STUDENT_SEX char(1),
  STUDENT_GROUP int(10) unsigned not null,
  STUDENT_EDUCATION_YEAR int not null,
  primary key (STUDENT_ID)
) engine=InnoDB;

ALTER TABLE ST_STUDENT
ADD CONSTRAINT FK_STUDENT_GROUP
FOREIGN KEY (STUDENT_GROUP)
REFERENCES ST_GROUP (GROUP_ID)
ON UPDATE NO ACTION
ON DELETE CASCADE;
 
 
set names 'utf8';
 
insert into groups (groupName, curator, speciality) 
values ('Первая', 'Доктор Борменталь', 'Создание собачек из человеков');
insert into groups (groupName, curator, speciality) 
values ('Вторая', 'Профессор Преображенский', 'Создание человеков из собачек');
 
insert into students (firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)
values ('Иван', 'Сергеевич', 'Степанов', 'М', '1990-03-20', 1, 2006);
 
insert into students (firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)
values ('Наталья', 'Андреевна', 'Чичикова', 'Ж', '1990-06-10', 1, 2006);
 
insert into students (firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)
values ('Виктор', 'Сидорович', 'Белов', 'М', '1990-01-10', 1, 2006);
 
insert into students (firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)
values ('Петр', 'Викторович', 'Сушкин', 'М', '1991-03-12', 2, 2006);
 
insert into students (firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)
values ('Вероника', 'Сергеевна', 'Ковалева', 'Ж', '1991-07-19', 2, 2006);
 
insert into students (firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)
values ('Ирина', 'Федоровна', 'Истомина', 'Ж', '1991-04-29', 2, 2006);

END;