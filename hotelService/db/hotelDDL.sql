use scott;

drop table member cascade;
drop table hotel cascade;
drop table bookinglist cascade;
drop table room cascade;


create table member (
   member_num int primary key auto_increment,
   member_id varchar(20) not null,
   member_pw varchar(20) not null,
   member_name varchar(20) not null,
   tel varchar(20) not null,
   email varchar(255) not null,
   member_grade tinyint(2) default 1,
   authority varchar(10) default "member"
);

create table hotel(
   hotel_num int primary key auto_increment,
   hotel_name varchar(100) not null,
   hotel_image varchar(255) not null,
   star double default 0.0,
   hotel_location varchar(255) not null,
   hotel_grade tinyint(2) not null
);

create table bookinglist (
   booking_num int primary key auto_increment,
   member_num int not null,
   room_num int not null,
    guest_num int default 2,
   checkin_date date not null,
    checkout_date date not null,
    booked_date timestamp not null default now(),
    booked_cancel_date timestamp default null
);

create table room (
   room_num int primary key auto_increment,
   hotel_num int not null,
   room_name varchar(100) not null,
   room_image_path varchar(255) not null,
    room_state varchar(5) default "empty",
    max_guest_num int default 4,
   price int not null,
   category varchar(20) not null
);

alter table room add foreign key(hotel_num) references hotel(hotel_num) on delete cascade;
alter table bookinglist add foreign key(room_num) references room(room_num)  on delete cascade;
alter table bookinglist add foreign key(member_num) references member(member_num);