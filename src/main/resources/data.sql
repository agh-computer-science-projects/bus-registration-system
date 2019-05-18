INSERT INTO `buses` (`id`, `type`, `registration_number`, `seats`, `assigned`, `bus_id`)
VALUES
       ('1', 'Large', 'KRA12345', '50', false , null),
       ('2', 'Medium', 'KRA1234', '35', false , null);


INSERT INTO `routes` (`id`, `name`, `from_city`, `to_city`, `duration`, `price`)
VALUES
       ('1', 'Krakow-Katowice', 'Krakow', 'Katowice', 120 , 22.50),
       ('2', 'Krakow-Warszawa', 'Krakow', 'Warszawa', 320 , 50.50);