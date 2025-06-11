use pos_db;

-- 업데이트 안될 때
SET SQL_SAFE_UPDATES = 0;


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


select *
from payment
;

update payment
set payment = 'Y'
where order_no = 1
;

                    select a.table_id,
                        sum(b.unit_price) sum_price
                    from payment a
                    join menu b
                      on a.menu_id = b.menu_id
                    where a.payment = 'N'
                      and a.table_id = 1
                    group by a.table_id
;

                    select a.order_no,
                           a.table_id,
                           b.menu_name,
                           a.count,
                           a.payment,
                           date_format(a.pay_date,'%Y-%m-%d') pay_date
                    from payment a
                        join menu b
                          on a.menu_id = b.menu_id
                          ;



select a.menu_id,
					    b.category_name,
					    a.menu_name,
					    a.unit_price,
                        menu_show,
                        
                        menu_delete
					from menu a
					join category b
					  on a.category_id = b.category_id
					-- where menu_show != '숨기기'
					  -- and menu_delete != '삭제됨'
;

select *
from payment
;
select * from category;

alter table category add category_status varchar(10);

UPDATE category
SET category_status = '정상'
WHERE category_status IS NULL;

SET SQL_SAFE_UPDATES = 0;

