CREATE TABLE employee
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    salary        INT          NOT NULL,
    age           INT          NOT NULL,
    profile_image VARCHAR(255)
);
