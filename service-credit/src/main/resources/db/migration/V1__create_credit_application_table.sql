CREATE TABLE credit_application (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    loan_amount DECIMAL(15,2) NOT NULL,
    loan_term INT NOT NULL,
    user_income DECIMAL(15,2) NOT NULL,
    current_debt_load DECIMAL(15,2) NOT NULL,
    credit_rating DECIMAL(15,2) NOT NULL,
    status VARCHAR(50) DEFAULT 'в обработке'
);