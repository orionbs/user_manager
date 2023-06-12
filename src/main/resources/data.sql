insert into user_manager_database.status (value)
values ('UNVERIFIED'),
       ('VERIFIED'),
       ('LOCKED')
on duplicate key update value = value;