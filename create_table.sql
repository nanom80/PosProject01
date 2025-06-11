use pos_db;


-- category 테이블 생성
create table category(
	category_id		integer			primary key		auto_increment,
    category_name	varchar(20)		not null,
    category_emoji	varchar(10),
    category_desc	varchar(200)
);

-- menu 테이블 생성
create table menu(
	menu_id		integer			primary key		auto_increment,
    menu_name	varchar(20)		not null,
    unit_price	integer,
    menu_show	varchar(10),
    menu_delete	varchar(10),
    category_id	integer,
    CONSTRAINT menu_fk FOREIGN KEY (category_id)
	REFERENCES category(category_id)
);



-- payment 테이블 생성
create table payment(
    order_no	integer		primary key 	auto_increment,
    count		integer,
    payment		varchar(10),
    pay_date	datetime,
    table_id	integer,
    menu_id		integer,
    CONSTRAINT payment_fk FOREIGN KEY (menu_id)
	REFERENCES menu(menu_id)
);




commit;


-- 지우기
drop table category;
drop table menu;
drop table payment;


