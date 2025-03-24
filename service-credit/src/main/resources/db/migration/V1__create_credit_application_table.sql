CREATE TABLE credit_application (
    id SERIAL PRIMARY KEY,           -- используем SERIAL для автоинкремента
    loan_amount DECIMAL(15,2) NOT NULL,
    loan_term INT NOT NULL,
    user_income DECIMAL(15,2) NOT NULL,
    current_debt_load DECIMAL(15,2) NOT NULL,
    credit_rating INT NOT NULL,      -- INT без (15,2) для целых чисел
    status VARCHAR(20) DEFAULT 'IN_PROCESS'
);