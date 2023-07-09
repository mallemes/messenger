INSERT INTO t_permissions (role, created_at)
VALUES ('ROLE_USER', now()),
       ('ROLE_ADMIN', now());

INSERT INTO t_users (username, created_at, password)
VALUES ('admin', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),   --password: password
       ('maksat', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),  --2
       ('madi', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),    --3
       ('nurik', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),   --4
       ('talgat', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),--5
       ('serik', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),   --6
       ('erik', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),    --7
       ('zhandos', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'), --8
       ('nurzhan', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'), --9
       ('samal', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),--10
       ('Aiko', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),    --11
       ('Sam', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),     --12
       ('Messi', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),   --13
       ('Ronaldo', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'), --14
       ('Neymar', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),  --15
       ('Toni', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'),    --16
       ('Karim', now(), '$2a$10$9yUfakyXgQFuFPy6R0V0NevmBS1l9Rf6e8yqdQGz9V3zxBcSbemjO'); --17

INSERT INTO t_users_permissions (user_id, permissions_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1);


INSERT INTO t_groups (name, slug, created_at, author_id)
VALUES ('Java', 'java', now(), 1),
       ('Python', 'python', now(), 2),
       ('C++', 'c++', now(), 3),
       ('C#', 'c-sharp', now(), 5),
       ('JavaScript', 'javascript', now(), 1),
       ('PHP', 'php', now(), 2),
       ('Ruby', 'ruby', now(), 4),
       ('Go', 'go', now(), 7),
       ('Swift', 'swift', now(), 8),
       ('Kotlin', 'kotlin', now(), 1),
       ('Rust', 'rust', now(), 2),
       ('Scala', 'scala', now(), 3),
       ('TypeScript', 'typescript', now(), 8),
       ('Dart', 'dart', now(), 10),
       ('R', 'r', now(), 11),
       ('Perl', 'perl', now(), 4),
       ('Haskell', 'haskell', now(), 6),
       ('Lua', 'lua', now(), 6),
       ('Julia', 'pengrvin', now(), 6),
       ('Elixir', 'elixir', now(), 6),
       ('Clojure', 'clojure', now(), 4),
       ('Groovy', 'duck', now(), 4),
       ('CoffeeScript', 'coffeescript', now(), 2),
       ('Objective-C', 'objective-c', now(), 5),
       ('Assembly', 'assembly', now(), 8),
       ('Shell', 'shell', now(), 9),
       ('PowerShell', 'powershell', now(), 10),
       ('HTML', 'html', now(), 1),
       ('CSS', 'css', now(), 4),
       ('SQL', 'sql', now(), 8),
       ('Dockerfile', 'dockerfile', now(), 6),
       ('Makefile', 'makefile', now(), 3),
       ('Vim script', 'vim-script', now(), 2),
       ('TeX', 'tex', now(), 12),
       ('Emacs Lisp', 'emacs-lisp', now(), 13),
       ('Roff', 'roff', now(), 14),
       ('Perl 6', 'perl-6', now(), 15),
       ('Erlang', 'erlang', now(), 16),
       ('Crystal', 'crystal', now(), 17),
       ('Objective-C++', 'objective-c++', now(), 17),
       ('Pascal', 'pascal', now(), 16),
       ('D', 'd', now(), 15),
       ('Julia', 'julia', now(), 14),
       ('Nim', 'nim', now(), 13),
       ('Vala', 'vala', now(), 14),
       ('Haxe', 'haxe', now(), 12),
       ('Groovy', 'groovy', now(), 11),
       ('ClojureScript', 'math', now(), 1);


INSERT INTO t_users_groups (users_id, groups_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (2, 8),
       (2, 9),
       (2, 10),
       (2, 11),
       (2, 12),
       (2, 13),
       (2, 4),
       (2, 5),
       (3, 6),
       (4, 7),
       (5, 8),
       (6, 1),
       (4, 2),
       (5, 2),
       (8, 12),
       (9, 3),
       (10, 4),
       (5, 5);

insert into t_group_posts (group_id, author_id, text, created_at)
VALUES (1, 1,
        'By clicking “Post Your Answer”, you agree to our terms of understand our privacy policy and code of conduct.',
        now()),
       (2, 1, 'How Bloomberg’s engineers built a culture of knowledge sharing', now()),
       (1, 9,
        'Not the answer you''re looking for? Browse other questions tagged postgresqlpropertiesh2liquibasechangeset or ask your own question.',
        now()),
       (4, 8, 'Encrypt some text. The result shown will be a Bcrypt encrypted hash.', now()),
       (5, 6, 'Java — Sun Microsystems компаниясының жасап шығарған объектіге-бағытталған бағдарламалау тілі. ', now()),
       (6, 5,
        'Table of Contents ... How to Download and Install Java for 64 bit machine? ... How to Download and Install Eclipse on Windows?',
        now()),
       (7, 4,
        'By clicking “Post Your Answer”, you agree to our terms of understand our privacy policy and code of conduct.',
        now()),
       (2, 3,
        'The place to collaborate on an open-source implementation of the Java Platform, Standard Edition, and related projects. Duke plugging, Download and install ',
        now()),
       (6, 2, 'Is Java free for programming?', now());


insert into t_friends(user_id, friend_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (1, 11),
       (2, 12),
       (2, 13),
       (2, 14),
       (9, 5),
       (12, 6),
       (9, 7),
       (7, 8),
       (6, 9),
       (6, 5);

