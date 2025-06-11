use pos_db;

show tables;
select * from information_schema.columns where table_name = 'payment';

show columns from category;
show columns from menu;
show full columns from payment;

select * from category;
select * from menu;
select * from payment;

select order_no,
    count,
    payment,
    pay_date,
    table_id,
    menu_id
from payment
where pay_date = '20200320'
;


                    select a.order_no,
                           a.table_id,
                           b.menu_name,
                           a.count,
                           a.payment,
                           a.pay_date
                      from payment a
                      	join menu b
                          on a.menu_id = b.menu_id
;

select * from payment;

select * from menu;

select * from category;

select *
from menu a
    join category b
    on a.category_id = b.category_id
;

select a.menu_id,
					    b.category_name,
					    a.menu_name,
					    a.unit_price
					from menu a
					join category b
					  on a.category_id = b.category_id
					 and menu_show != '숨기기'
					 and menu_delete != '삭제됨'
