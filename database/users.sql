create user 'william'@'%' identified by '12345';
grant all privileges on defaultdb.* to 'william'@'%';

create user 'barbara'@'%' identified by '12345';
grant all privileges on defaultdb.* to 'barbara'@'%';


create user 'joao_marcos'@'%' identified by '12345';
grant select on defaultdb.* to 'joao_marcos'@'%';

create user 'kauan'@'%' identified by '12345';
grant select on defaultdb.* to 'kauan'@'%';

create user 'joao_vitor'@'%' identified by '12345';
grant select on defaultdb.* to 'joao_vitor'@'%';