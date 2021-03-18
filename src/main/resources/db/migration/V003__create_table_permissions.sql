create table permissions
(
    role_id     varchar(100) not null references roles on delete cascade,
    function_id varchar(100) not null references functions on delete cascade,
    has         boolean      not null default false
);
