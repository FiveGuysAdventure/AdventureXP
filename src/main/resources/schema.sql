DROP SCHEMA IF EXISTS adventurexp_db;
CREATE SCHEMA adventurexp_db;

CREATE TABLE activity_tag
(
    tag_id   TINYINT AUTO_INCREMENT PRIMARY KEY,
    tag_name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE activity_type
(
    activity_id      INT AUTO_INCREMENT PRIMARY KEY,
    activity_name    VARCHAR(60) NOT NULL,
    duration_min     INT         NOT NULL,
    price_per_person INT
);

CREATE TABLE activity_type_tag
(
    activity_id INT     NOT NULL,
    tag_id      TINYINT NOT NULL,
    PRIMARY KEY (activity_id, tag_id),
    FOREIGN KEY (activity_id) REFERENCES activity_type (activity_id),
    FOREIGN KEY (tag_id) REFERENCES activity_tag (tag_id)
);

CREATE TABLE equipment
(
    equipment_id     INT AUTO_INCREMENT PRIMARY KEY,
    activity_id      INT NOT NULL,
    equipment_name   VARCHAR(60) NOT NULL,
    out_of_service   BOOLEAN     NOT NULL DEFAULT FALSE,
    last_checked     DATE,
    FOREIGN KEY (activity_id) REFERENCES activity_type (activity_id)
);

CREATE TABLE role
(
    role_id   INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE employee
(
    employee_id  INT AUTO_INCREMENT PRIMARY KEY,
    emp_name     VARCHAR(60)  NOT NULL,
    emp_phone_nr VARCHAR(60)  NOT NULL UNIQUE,
    emp_email    VARCHAR(100) NOT NULL UNIQUE,
    role_id      INT          NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);

CREATE TABLE booking
(
    booking_id          INT AUTO_INCREMENT PRIMARY KEY,
    num_of_participants INT,
    contact_email       VARCHAR(100),
    contact_nr          VARCHAR(60),
    start_time          DATETIME,
    end_time            DATETIME,
    booking_price       INT,
    activity_id         INT NOT NULL,
    employee_id         INT,
    FOREIGN KEY (activity_id) REFERENCES activity_type (activity_id),
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

-- CREATE TABLE activity_equipment
-- (
--     activity_id         INT NOT NULL,
--     equipment_id        INT NOT NULL,
--     qnt_per_participant INT,
--     PRIMARY KEY (activity_id, equipment_id),
--     FOREIGN KEY (activity_id) REFERENCES activity_type (activity_id),
--     FOREIGN KEY (equipment_id) REFERENCES equipment (equipment_id)
-- );
--
-- CREATE TABLE booking_equipment
-- (
--     booking_id   INT NOT NULL,
--     equipment_id INT NOT NULL,
--     qnt_reserved INT,
--     PRIMARY KEY (booking_id, equipment_id),
--     FOREIGN KEY (booking_id) REFERENCES booking (booking_id),
--     FOREIGN KEY (equipment_id) REFERENCES equipment (equipment_id)
-- );