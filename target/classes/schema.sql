CREATE TABLE IF NOT EXISTS transactions
(
  id UUID DEFAULT random_uuid() PRIMARY KEY,
  user_id VARCHAR(255),
  reference VARCHAR(255),
  bank_slogan VARCHAR(255),
  amount DECIMAL(10, 2),
  timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
