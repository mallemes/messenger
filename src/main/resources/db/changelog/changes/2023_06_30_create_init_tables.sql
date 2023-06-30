create table t_users
(
    id           bigserial primary key,
    created_at   timestamp(6),
    avatar       text,
    bio          text,
    email        varchar(255)
        constraint uk_1f8qpknpngd98342v0j2ceadc
            unique,
    first_name   varchar(50),
    job          varchar(255),
    last_name    varchar(50),
    last_online  timestamp(6),
    password     varchar(255),
    phone_number varchar(255),
    username     varchar(255)
        constraint uk_sp0e01od15gf4nu5ffu87qb9n
            unique
);


-- 2
create table t_permissions
(
    id         bigserial primary key,
    created_at timestamp(6),
    role       varchar(255)
);

-- 3
create table t_users_permissions
(
    user_id        bigint not null
        constraint fk1aqgc2651y14fjqdvq9ytfwc1
            references t_users,
    permissions_id bigint not null
        constraint fkpmfc242wvr5kwdv44324cdbna
            references t_permissions
);

-- 4

create table t_user_posts
(
    id         bigserial
        primary key,
    created_at timestamp(6),
    image      varchar(255),
    text       text,
    user_id    bigint
        constraint fkpmudt15q14tvr6nktbukxl959
            references t_users
);


-- 5
create table t_user_chats
(
    id           bigserial
        primary key,
    created_at   timestamp(6),
    image        varchar(255),
    message      text,
    from_user_id bigint
        constraint fk54e7wjodctc1pg9t46bsu5vx8
            references t_users,
    to_user_id   bigint
        constraint fk77xwg8m4x3uc49rubiyih6gex
            references t_users
);

-- 6

create table t_groups
(
    id          bigserial
        primary key,
    created_at  timestamp(6),
    description varchar(255),
    poster      varchar(255),
    name        varchar(255),
    slug        varchar(255) not null
        constraint uk_b67qid792y11yfx2fo5y4gcwy
            unique,
    status      varchar(255),
    author_id   bigint
        constraint fknvfbij1mednk6b4ky9frm6nmh
            references t_users
);


-- 7
create table t_group_posts
(
    id         bigserial
        primary key,
    created_at timestamp(6),
    image      varchar(255),
    text       varchar(255),
    author_id  bigint
        constraint fkf2n28fkqxprqnlw9gdm3rbarl
            references t_users,
    group_id   bigint
        constraint fkcvc9qgybpkh8g35tniaoeetoc
            references t_groups
);


-- 8
create table t_friends
(
    user_id   bigint not null
        constraint fkk8ujy1jccgkc4wu69xkkbsdxl
            references t_users,
    friend_id bigint not null
        constraint fkq2rchjdr8rlabo316o4k46kc5
            references t_users,
    primary key (user_id, friend_id)
);

-- 9
create table t_comments
(
    id         bigserial
        primary key,
    created_at timestamp(6),
    text       varchar(255),
    author_id  bigint
        constraint fkhap1hu5fnn0sdipo6hfijsrok
            references t_users,
    post_id    bigint
        constraint fkgqsl6l8f4xcoxd02ydyfote7l
            references t_group_posts
);

-- 10
create table t_users_groups
(
    users_id  bigint not null
        constraint fkgwi0iqh0r1wfpaibe6xr81eh9
            references t_users,
    groups_id bigint not null
        constraint fkoybpvvh28q2qlfrii8b7rvbgs
            references t_groups
);






