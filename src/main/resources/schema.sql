create table if not exists transactions
(
  id uuid default random_uuid() primary key,
  user_id varchar(255),
  reference varchar(255),
  bank_slogan varchar(255),
  amount int,
  timestamp datetime
);
