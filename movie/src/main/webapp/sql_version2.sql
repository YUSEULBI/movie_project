drop database if exists movie;
create database movie;
use movie;

-- 회원 테이블
drop table if exists member;
create table member(
	mno int auto_increment primary key,-- 회원번호
    mid varchar(20),-- ID
    mpwd varchar(20), -- 비밀번호
    memail varchar(20),-- 이메일
    mimg varchar(20)-- 프로필 이미지
);
insert into member(mid,mpwd,memail,mimg) values ('admin','admin1','admin@admin.com',null);
insert into member(mid,mpwd,memail,mimg) values ('qwert','qwert1','admin@admin.com',null);
insert into member(mid,mpwd,memail,mimg) values ('asdfg','asdfg1','admin@admin.com',null);
insert into member(mid,mpwd,memail,mimg) values ('zxcvb','zxcvb1','admin@admin.com',null);


-- 영화 테이블
drop table if exists movie;
create table movie(
	mno	int auto_increment primary key,	-- 영화번호
	title varchar(30),					-- 영화제목
    openDt date,						-- 개봉일
    directors varchar(20),				-- 감독
    genre varchar(20), 					-- 장르
    audience int,						-- 누적관객수
    mstate boolean						-- 상영여부 [ true : 상영중, false= 상영중지 ]
);

insert into movie(title,openDt,directors,genre,audience,mstate) values ('존 윅 4',20230308,'신카이 마코토','애니메이션',2207371,true);
insert into movie(title,openDt,directors,genre,audience,mstate) values ('더 퍼스트 슬램덩크',20230104,'이노우에 다케히코','애니메이션',4190218,true);
insert into movie(title,openDt,directors,genre,audience,mstate) values ('킬링 로맨스',20230322,'박성광','코미디',54783,true);
insert into movie(title,openDt,directors,genre,audience,mstate) values ('귀를 기울이면',20230315,'민용근','드라마',147846,true);
insert into movie(title,openDt,directors,genre,audience,mstate) values ('스즈메의 문단속',20230322,'스티븐 스필버그','드라마',12876,true);
insert into movie(title,openDt,directors,genre,audience,mstate) values ('옥수역 귀신',20230322,'애나 릴리 아미푸르','판타지',8995,true);
insert into movie(title,openDt,directors,genre,audience,mstate) values ('위대한 쇼맨',20230301,'다니엘 콴','액션',74222,true);
insert into movie(title,openDt,directors,genre,audience,mstate) values ('드림',20230302,'소토자키 하루오','애니메이션',507505,true);
insert into movie(title,openDt,directors,genre,audience,mstate) values ('멍뭉이',20230315,'장-프랑소 와 리셰','액션',33046,true);
insert into movie(title,openDt,directors,genre,audience,mstate) values ('거울 속 외딴 성',20230301,'오윤동','다큐멘터리',218529,true);

-- 영화관 테이블
drop table if exists screen;
create table screen(
	sno int auto_increment primary key,		-- 고유번호
    seat int 								-- 좌석수
);
insert into screen(seat) values (144);
insert into screen(seat) values (144);
insert into screen(seat) values (144);
insert into screen(seat) values (144);
insert into screen(seat) values (144);

-- 상영정보 테이블
drop table if exists playinglist;
create table playinglist(
	pno int auto_increment primary key,				-- 고유번호
    pstate boolean default true,					-- 예약가능상태
    pseat int default 0,							-- 현재예약좌석수
    pprice int,										-- 가격
    playtime datetime, 								-- 상영시간
	mno int,										-- 영화(FK)
    sno int,										-- 영화관번호(FK)
    foreign key (mno) references movie(mno),
    foreign key (sno) references screen(sno)
);
-- 영화 상영정보 랜덤 50개 생성
INSERT INTO playinglist (playtime, mno, sno, pprice)
SELECT
  DATE_FORMAT(
    DATE_ADD(
      NOW(),
      INTERVAL FLOOR(RAND() * 5) DAY
    ),
    CONCAT('%Y-%m-%d ', LPAD(FLOOR(RAND() * 17) + 6, 2, '0'), ':00:00')
  ) + INTERVAL (FLOOR(RAND() * 6) * 10) MINUTE AS playtime,
  m.mno,
  s.sno,
  CASE FLOOR(RAND() * 3)
    WHEN 0 THEN 10000
    WHEN 1 THEN 12000
    ELSE 14000
  END AS pprice
FROM
  movie M,
  screen s
ORDER BY RAND()
LIMIT 50;

select *from playinglist;


-- 예약 테이블
drop table if exists reservation;
create table reservation(
   rno int auto_increment primary key, 		-- 예약고유번호
   seatnum varchar(10),           			-- 선택한 좌석
    mno int,                			    -- 예약회원번호(FK)
    pno int,                     			-- 상영번호(FK)
   foreign key (mno) references member(mno),
   foreign key (pno) references playinglist(pno)
);

INSERT INTO reservation ( mno, pno, seatnum)
SELECT
  2 AS mno,
  FLOOR(RAND() * 50) + 1 AS pno,
  CONCAT(
    CHAR(FLOOR(RAND() * 9) + 97),
    FLOOR(RAND() * 16) + 1
  ) AS seatnum
FROM
  INFORMATION_SCHEMA.TABLES t1,
  INFORMATION_SCHEMA.TABLES t2
LIMIT 50;

UPDATE playinglist p
SET p.pseat = (SELECT MAX(p.pseat) FROM reservation r WHERE r.pno = p.pno) + 1
WHERE p.pno IN (SELECT pno FROM reservation);

select *from reservation;

-- 게시물 테이블 [ 번호 , 영화 , 내용 , 작성일 , 작성자 , 카테고리번호 ]
create table board(
	bno			int auto_increment primary key , 
    bmovie 		varchar(1000) not null ,
    bcontent	longtext	not null ,
    bdate 		datetime default now() ,
	bscore		int not null ,
    mno			int , -- 회원번호 fk 
    foreign key ( mno ) references member( mno ) on delete set null  	-- [회원]pk가 삭제되면 게시물fk는 null 변경
);


-- 댓글 테이블 [ 댓글번호 , 내용 , 작성일 , 인덱스(계층구분) , 작성자 , 게시물번호 ]
create table reply(
	rno			int auto_increment primary key , 
    rcontent	longtext , 
    rdate		datetime default now(),
    rmovie		varchar(1000) not null,
	mid  varchar(100),
	rindex		int , 
    mno			int ,
    bno			int ,
    foreign key( mno ) references member(mno) on delete set null ,
    foreign key( bno ) references board(bno) on delete cascade 
);
