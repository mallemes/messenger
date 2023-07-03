
-- file: 2023_07_03_add_column_is_banned_to_users.sql

-- change table users add column is_banned
ALTER TABLE t_users ADD COLUMN is_banned BOOLEAN DEFAULT FALSE;