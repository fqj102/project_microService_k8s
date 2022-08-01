create database db_course;
use db_course;
create table pe_course
(
    id          int          not null
        primary key,
    title       varchar(100) null,
    description varchar(100) null
);

-- auto-generated definition
create table pr_teacher_course
(
    user_id   int null,
    course_id int null
);


create database db_user;
use db_user;

-- auto-generated definition
create table pe_teacher
(
    id    int          not null
        primary key,
    intro varchar(100) null,
    stars varchar(100) null
);

create table pe_user
(
    id            int auto_increment
        primary key,
    USER_NAME     varchar(32) not null,
    USER_PASSWORD varchar(32) not null,
    REAL_NAME     varchar(32) null,
    USER_MOBILE   varchar(32) null,
    USER_EMAIL    varchar(32) null
);


INSERT INTO db_course.pe_course (id, title, description) VALUES (1, '1', 'java');
INSERT INTO db_course.pe_course (id, title, description) VALUES (2, '2', 'c++');

INSERT INTO db_course.pr_teacher_course (user_id, course_id) VALUES (8, 1);
INSERT INTO db_course.pr_teacher_course (user_id, course_id) VALUES (8, 2);

INSERT INTO db_user.pe_teacher (id, intro, stars) VALUES (8, 'intro11', '5');

INSERT INTO db_user.pe_user (id, USER_NAME, USER_PASSWORD, REAL_NAME, USER_MOBILE, USER_EMAIL) VALUES (1, 'fang', '202cb962ac59075b964b07152d234b70', '1', '1', '1');
INSERT INTO db_user.pe_user (id, USER_NAME, USER_PASSWORD, REAL_NAME, USER_MOBILE, USER_EMAIL) VALUES (6, 'fang1', '202cb962ac59075b964b07152d234b70', null, '1111111111', null);
INSERT INTO db_user.pe_user (id, USER_NAME, USER_PASSWORD, REAL_NAME, USER_MOBILE, USER_EMAIL) VALUES (8, 'fang2', '85033f330c02b9acb89699429a9b9dc6', 'Jerry', '1111', null);


