INSERT INTO role (role_name)
VALUES ('Manager'), ('Employee');

INSERT INTO employee (emp_name, emp_phone_nr, emp_email, role_id)
VALUES ('Joakim', '22003344', 'Joakim@mail.com', 1),
       ('Malthe', '23003344', 'Malthe@mail.com', 1),
       ('Chibuike', '24003344', 'Cibuike@mail.com', 2),
       ('Emil', '25003344', 'Emil@mail.com', 2),
       ('Simon', '26003344', 'Simon@mail.com', 2);

INSERT INTO activity_tag (tag_name)
VALUES ('Speed'),
       ('Shooting'),
       ('Wrestling'),
       ('Golf'),
       ('Gokart'),
       ('Paintball'),
       ('Sumo'),
       ('Minigolf');

INSERT INTO equipment (equipment_name, total_quantity)
VALUES ('Gokarts', 12),
       ('Paintball guns', 20),
       ('Sumo suit', 4),
       ('Golf putters', 24);

INSERT INTO activity_type (activity_name, duration_min, price_per_person)
VALUES ('Gokart', 30, 400),
       ('Paintball', 30, 350),
       ('Sumo Wrestling', 30, 150),
       ('Minigolf', 1, 200);

INSERT INTO activity_equipment (activity_id, equiptment_id, qnt_per_participant)
VALUES (1, 1, 1);

INSERT INTO booking_equipment (booking_id, equipment_id, qnt_reserved)
VALUES (1, 1, 12), // Booking where maximum equipment is reserved
       (2, 2, 20),
       (3, 3, 4),
       (4, 4, 24),

       (1, 1, 2), // Booking where minimum requirement is reserved
       (2, 2, 4),
       (3, 3, 2),
       (4, 4, 1);

INSERT INTO booking (num_of_participants, contact_email, contact_nr, start_time, end_time, booking_price, activity_id, employee_id)
VALUES
    // GOKART
            (12,
            'customer1@email.com',
            22334400,
            '2026-10-01 14:30:00',
            '2026-10-01 15:00:00',
             4800,
             1,
             3),

    // Paintball
            (20,
            'customer2@email.com',
            22334400,
            '2026-10-01 14:30:00',
            '2026-10-01 15:00:00',
            7000,
            2,
            4),

    // Sumo Wrestling
            (4,
            'customer3@email.com',
            22334400,
            '2026-10-01 14:30:00',
            '2026-10-01 15:00:00',
            600,
            3,
            5),

    // Minigolf
            (24,
            'customer4@email.com',
            22334400,
            '2026-10-01 14:30:00',
            '2026-10-01 15:30:00',
            4800,
            4,
            5);






