create user 'pos'@'%' identified by 'pos';

create database pos_db
	default character set utf8mb4
    collate utf8mb4_general_ci
    default encryption='n'
;

grant all privileges on pos_db.* to 'pos'@'%' ;

