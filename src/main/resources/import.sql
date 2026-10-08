-- Role
INSERT INTO role (role_id, role_name) VALUES (1, 'MANAGER');
INSERT INTO role (role_id, role_name) VALUES (2, 'EMPLOYEE');

-- Activities
INSERT INTO activity_type (activity_name, duration_minutes, price_per_person) VALUES ('Gokart', 30, 450);
INSERT INTO activity_type (activity_name, duration_minutes, price_per_person) VALUES ('Minigolf', 60, 175);
INSERT INTO activity_type (activity_name, duration_minutes, price_per_person) VALUES ('Sumo wrestling', 60, 150);

-- Two managers
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Camilla', '99999999', 'camilla@example.com', 'camilla123', (SELECT role_id FROM role WHERE role_name = 'MANAGER'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Jonas', '10101010', 'jonas@example.com', 'jonas123', (SELECT role_id FROM role WHERE role_name = 'MANAGER'));

-- Eight Employees
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Laura', '77777777', 'laura@example.com', 'laura123', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Mikkel', '88888888', 'mikkel@example.com', 'mikkel123', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Anna', '11111111', 'anna@example.com', 'anna123', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Emil', '22222222', 'emil@example.com', 'emil123', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Freja', '33333333', 'freja@example.com', 'freja123', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Noah', '44444444', 'noah@example.com', 'noah123', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Sofie', '55555555', 'sofie@example.com', 'sofie123', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Oliver', '66666666', 'oliver@example.com', 'oliver123', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));

-- Gokart equipment
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Gokart 1', (SELECT activity_id FROM activity_type WHERE activity_name = 'Gokart'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Gokart 2', (SELECT activity_id FROM activity_type WHERE activity_name = 'Gokart'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Gokart 3', (SELECT activity_id FROM activity_type WHERE activity_name = 'Gokart'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Gokart 4', (SELECT activity_id FROM activity_type WHERE activity_name = 'Gokart'), FALSE);

-- Minigolf equipment
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Minigolf putter 1', (SELECT activity_id FROM activity_type WHERE activity_name = 'Minigolf'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Minigolf putter 2', (SELECT activity_id FROM activity_type WHERE activity_name = 'Minigolf'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Minigolf putter 3', (SELECT activity_id FROM activity_type WHERE activity_name = 'Minigolf'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Minigolf putter 4', (SELECT activity_id FROM activity_type WHERE activity_name = 'Minigolf'), FALSE);

-- Sumo wrestling equipment
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Sumo suit 1', (SELECT activity_id FROM activity_type WHERE activity_name = 'Sumo wrestling'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Sumo suit 2', (SELECT activity_id FROM activity_type WHERE activity_name = 'Sumo wrestling'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Sumo suit 3', (SELECT activity_id FROM activity_type WHERE activity_name = 'Sumo wrestling'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Sumo suit 4', (SELECT activity_id FROM activity_type WHERE activity_name = 'Sumo wrestling'), FALSE);

-- Shop products
INSERT INTO product (product_name, price, active) VALUES ('T-shirt', 150, TRUE);
INSERT INTO product (product_name, price, active) VALUES ('Cola', 20, TRUE);
INSERT INTO product (product_name, price, active) VALUES ('Fanta', 20, TRUE);
INSERT INTO product (product_name, price, active) VALUES ('Haribo', 15, TRUE);
INSERT INTO product (product_name, price, active) VALUES ('Chips', 25, TRUE);

INSERT INTO booking (booking_date, contact_email, contact_number, num_of_guests, price, start_time, end_time, activity_id, employee_id) VALUES ('2026-10-08', 'test1@example.com', '12345678', 2, 200, '2026-10-08 10:00:00', '2026-10-08 11:00:00', (SELECT activity_id FROM activity_type WHERE activity_name = 'Gokart'), (SELECT employee_id FROM employee WHERE emp_name = 'Anna'));
INSERT INTO booking (booking_date, contact_email, contact_number, num_of_guests, price, start_time, end_time, activity_id, employee_id) VALUES ('2026-10-08', 'test2@example.com', '87654321', 3, 225, '2026-10-08 13:00:00', '2026-10-08 13:45:00', (SELECT activity_id FROM activity_type WHERE activity_name = 'Minigolf'), (SELECT employee_id FROM employee WHERE emp_name = 'Emil'));