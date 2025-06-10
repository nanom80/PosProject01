-- 카테고리 
insert into category
values (null, '분식류', '★', '어릴적 먹던 추억의 맛' );
insert into category
values (null, '디저트류', '◆', '달달하고 맛있는 맛' );
insert into category
values (null, '주류', '▲', '시원하고 맛있는 맛' );

-- 메뉴
insert into menu 
values (null, '떡볶이', 5000, '숨기기', '삭제됨', 1);
insert into menu 
values (null, '케이크', 3500, null, '삭제됨', 2);
insert into menu 
values (null, '맥주', 4000, null, null, 3);

-- 결제
insert into payment
values (null, 2, '결제여부', '2020-03-20', 4, 1);
insert into payment
values (null, 4, null, '2020-03-21', 3, 4);
insert into payment
values (null, 2, null, '2020-03-20', 1, 3);

select *
from category;

select*
from menu;

select*
from payment;



