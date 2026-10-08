-- Role
INSERT INTO role (role_name) VALUES ('EMPLOYEE');

-- Activities
INSERT INTO activity_type (activity_name, duration_minutes, price_per_person) VALUES ('Gokart', 60, 100);
INSERT INTO activity_type (activity_name, duration_minutes, price_per_person) VALUES ('Minigolf', 45, 75);
INSERT INTO activity_type (activity_name, duration_minutes, price_per_person) VALUES ('Bowling', 60, 120);

-- Original Employees
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Anna', '11111111', 'anna@example.com', 'anna-test', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Emil', '22222222', 'emil@example.com', 'emil-test', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Freja', '33333333', 'freja@example.com', 'freja-test', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Noah', '44444444', 'noah@example.com', 'noah-test', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Sofie', '55555555', 'sofie@example.com', 'sofie-test', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, emp_password, role_id) VALUES ('Oliver', '66666666', 'oliver@example.com', 'oliver-test', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
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

INSERT INTO booking (booking_date, contact_email, contact_number, num_of_guests, price, start_time, end_time, activity_id, employee_id) VALUES ('2026-10-08', 'test1@example.com', '12345678', 2, 200, '2026-10-08 10:00:00', '2026-10-08 11:30:00', (SELECT activity_id FROM activity_type WHERE activity_name = 'Gokart'), (SELECT employee_id FROM employee WHERE emp_name = 'Anna'));
INSERT INTO booking (booking_date, contact_email, contact_number, num_of_guests, price, start_time, end_time, activity_id, employee_id) VALUES ('2026-10-08', 'test2@example.com', '87654321', 3, 225, '2026-10-08 10:00:00', '2026-10-08 12:45:00', (SELECT activity_id FROM activity_type WHERE activity_name = 'Minigolf'), (SELECT employee_id FROM employee WHERE emp_name = 'Freja'));
INSERT INTO booking (booking_date, contact_email, contact_number, num_of_guests, price, start_time, end_time, activity_id, employee_id) VALUES ('2026-10-08', 'test1@example.com', '12345678', 2, 200, '2026-10-08 11:30:00', '2026-10-08 11:45:00', (SELECT activity_id FROM activity_type WHERE activity_name = 'Gokart'), (SELECT employee_id FROM employee WHERE emp_name = 'Noah'));
INSERT INTO booking (booking_date, contact_email, contact_number, num_of_guests, price, start_time, end_time, activity_id, employee_id) VALUES ('2026-10-08', 'test2@example.com', '87654321', 3, 225, '2026-10-08 11:00:00', '2026-10-08 12:45:00', (SELECT activity_id FROM activity_type WHERE activity_name = 'Minigolf'), (SELECT employee_id FROM employee WHERE emp_name = 'Emil'));