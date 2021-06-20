insert into user (name,email,role) values (
'heba',
'heba.elgreatly@gmail.com',
'admin'
);

insert into user (name,email,role) values (
'test',
'test@gmail.com',
'user'
);

insert into operation (name,code) values (
'vodafone',
'010'
);

insert into operation (name,code) values (
'mobinil',
'012'
);

insert into operation (name,code) values (
'etisalat',
'011'
);

insert into simcard (sim_number, country,status,operation_id) values(
'008543123',
'Italy',
'waiting for activation',
(select id from operation where name = 'vodafone')
);

insert into simcard (sim_number, country,status,operation_id) values(
'008342123',
'USA',
'waiting for activation',
(select id from operation where name = 'mobinil')
);

insert into simcard (sim_number, country,status,operation_id) values(
'008342973',
'Canada',
'waiting for activation',
(select id from operation where name = 'etisalat')
);

insert into simcard (sim_number, country,status,operation_id) values(
'008543133',
'USA',
'waiting for activation',
(select id from operation where name = 'vodafone')
);

insert into simcard (sim_number, country,status,operation_id) values(
'008543144',
'Italy',
'waiting for activation',
(select id from operation where name = 'vodafone')
);

insert into simcard (sim_number, country,status,operation_id) values(
'008543623',
'Spain',
'Active',
(select id from operation where name = 'etisalat')
);

insert into simcard (sim_number, country,status,operation_id) values(
'008553144',
'Italy',
'Blocked',
(select id from operation where name = 'mobinil')
);

insert into simcard (sim_number, country,status,operation_id) values(
'008733144',
'Italy',
'Deactivated',
(select id from operation where name = 'vodafone')
);