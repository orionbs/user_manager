insert into user_manager_database.status_type (value)
values ('UNVERIFIED'),
       ('VERIFIED'),
       ('LOCKED')
on duplicate key update value = value;

insert into user_manager_database.event_type (value)
values ('REGISTRATION'),
       ('AUTHENTICATION')
on duplicate key update value = value;