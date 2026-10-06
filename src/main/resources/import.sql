-- Role
INSERT INTO role (role_name) VALUES ('EMPLOYEE');

-- Activities
INSERT INTO activity_type (activity_name, duration_minutes, price_per_person) VALUES ('Gokart', 60, 100);
INSERT INTO activity_type (activity_name, duration_minutes, price_per_person) VALUES ('Minigolf', 45, 75);
INSERT INTO activity_type (activity_name, duration_minutes, price_per_person) VALUES ('Bowling', 60, 120);

-- Original two employees
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, role_id) VALUES ('Anna', '11111111', 'anna@example.com', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, role_id) VALUES ('Emil', '22222222', 'emil@example.com', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));

-- Four additional employees
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, role_id) VALUES ('Freja', '33333333', 'freja@example.com', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, role_id) VALUES ('Noah', '44444444', 'noah@example.com', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, role_id) VALUES ('Sofie', '55555555', 'sofie@example.com', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));
INSERT INTO employee (emp_name, emp_phone_nr, emp_email, role_id) VALUES ('Oliver', '66666666', 'oliver@example.com', (SELECT role_id FROM role WHERE role_name = 'EMPLOYEE'));

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

-- Bowling equipment
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Bowling ball 1', (SELECT activity_id FROM activity_type WHERE activity_name = 'Bowling'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Bowling ball 2', (SELECT activity_id FROM activity_type WHERE activity_name = 'Bowling'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Bowling ball 3', (SELECT activity_id FROM activity_type WHERE activity_name = 'Bowling'), FALSE);
INSERT INTO equipment (equipment_name, activity_id, out_of_service) VALUES ('Bowling ball 4', (SELECT activity_id FROM activity_type WHERE activity_name = 'Bowling'), FALSE);