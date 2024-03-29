# Table `users`
    ## Insert
        INSERT INTO `users` (`login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `imagePath`, `role`)
        VALUES ("example", "pcztZ2JSKi/irUYAtxwYXfqsdfOTid1uf7f3fFmHwQnMdGXxuy5ilg/sjLX0Z50ZHIIUdKb0L9C4UsEmbgITHw==", "Кетевань", "Николахина", "Аркадьевна", "example@gmail.com", 572370606,"path/smth/img.jpg",1);
    ## Select   all
        SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `imagePath`, `role` FROM `users`;
    ## Select by `id`
        SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `imagePath`, `role` FROM `users` WHERE `id` = 1;
    ## Select by `login` and `password`
        SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `imagePath`, `role` FROM `users` WHERE `login` = "admin" AND password = "NHt98jzAzqNdGDlWvcqHy6UfFIQxm9mQ7sPS8o2Me/rqSjQ2qwzGFkhIkmIeVFVp7ZIChXlVd9g7gG3s838qFw==";
    ## Update
        UPDATE `users` SET `login` = "eyeblacks", `password` = "CC+nsAOlBsD1qp2nTj5W0hh6lj/avLt2LI8gu4Lg56XV+SxBgbF8Q8ZwQ79Xl/VCzdBP9Z+MMTb6m5RgSp/rCw==", `name`  = "Максим", `surname` = "Стульба", `patronymic` = "Валерьевич", `email` = "example@inbox.ru", `phone` = 12345612345, `imagePath` = "path", `role` = 1 WHERE `id` = 1;
    ## Delete
#         DELETE FROM `users` WHERE `id` = 2;

#Table `reservations`
    ## Insert
        INSERT INTO `reservations` (`offer_id`, `customer_id`, `employee_id`, `date`)
        VALUES
        (1, 10, 28, "1970-01-01");
    ## Select all
        SELECT
            reservations.id,
            offers.id AS offer_id,
            offers.name AS offer_name,
            offers.description AS offer_description,
            offers.price AS offer_price,
            offers.period AS offer_period,
            customers.id AS customer_id,
            customers.login AS customer_login,
            customers.password AS customer_password,
            customers.name AS customer_name,
            customers.surname AS customer_surname,
            customers.patronymic AS customer_patronymic,
            customers.email AS customer_email,
            customers.phone AS customer_phone,
            customers.imagePath AS customer_image_path,
            customers.role AS customer_role,

            employees.id AS employee_id,
            employees.login AS employee_login,
            employees.password AS employee_password,
            employees.name AS employee_name,
            employees.surname AS employee_surname,
            employees.patronymic AS employee_patronymic,
            employees.email AS employee_email,
            employees.phone AS employee_phone,
            employees.imagePath AS employee_image_path,
            employees.role AS employees_role,
            reservations.date
        FROM reservations
                 JOIN offers ON offers.id = reservations.offer_id
                 JOIN users AS customers ON customers.id = reservations.customer_id
                 JOIN users AS employees ON employees.id = reservations.employee_id;
    ## Select by customer
        SELECT
            reservations.id,
            offers.id AS offer_id,
            offers.name AS offer_name,
            offers.description AS offer_description,
            offers.price AS offer_price,
            offers.period AS offer_period,
            customers.id AS customer_id,
            customers.login AS customer_login,
            customers.password AS customer_password,
            customers.name AS customer_name,
            customers.surname AS customer_surname,
            customers.patronymic AS customer_patronymic,
            customers.email AS customer_email,
            customers.phone AS customer_phone,
            customers.imagePath AS customer_image_path,
            customers.role AS customer_role,

            employees.id AS employee_id,
            employees.login AS employee_login,
            employees.password AS employee_password,
            employees.name AS employee_name,
            employees.surname AS employee_surname,
            employees.patronymic AS employee_patronymic,
            employees.email AS employee_email,
            employees.phone AS employee_phone,
            employees.imagePath AS employee_image_path,
            employees.role AS employees_role,
            reservations.date
        FROM reservations
                 JOIN offers ON offers.id = reservations.offer_id
                 JOIN users AS customers ON customers.id = reservations.customer_id
                 JOIN users AS employees ON employees.id = reservations.employee_id
        WHERE reservations.customer_id = 1;
    ## Select by employee
        SELECT
            reservations.id,
            offers.id AS offer_id,
            offers.name AS offer_name,
            offers.description AS offer_description,
            offers.price AS offer_price,
            offers.period AS offer_period,
            customers.id AS customer_id,
            customers.login AS customer_login,
            customers.password AS customer_password,
            customers.name AS customer_name,
            customers.surname AS customer_surname,
            customers.patronymic AS customer_patronymic,
            customers.email AS customer_email,
            customers.phone AS customer_phone,
            customers.imagePath AS customer_image_path,
            customers.role AS customer_role,

            employees.id AS employee_id,
            employees.login AS employee_login,
            employees.password AS employee_password,
            employees.name AS employee_name,
            employees.surname AS employee_surname,
            employees.patronymic AS employee_patronymic,
            employees.email AS employee_email,
            employees.phone AS employee_phone,
            employees.imagePath AS employee_image_path,
            employees.role AS employees_role,
            reservations.date
        FROM reservations
                 JOIN offers ON offers.id = reservations.offer_id
                 JOIN users AS customers ON customers.id = reservations.customer_id
                 JOIN users AS employees ON employees.id = reservations.employee_id
        WHERE reservations.employee_id = 2;
    ## Update
        UPDATE `reservations`
        SET
            `offer_id` = 2,
            `customer_id` = 1,
            `employee_id` = 2,
            `date` = "2018-02-17"
        WHERE `id` = 1;
    ## Delete
        DELETE FROM `reservations` WHERE `id` = 2;
#Table `offers`
    ## Insert
        INSERT INTO `offers` (`name`, `description`, `price`, `period`)
        VALUES ("Мытьё волос", "Мытьё волос. Укладка волос", 9.99, 25);
    ## Select
        SELECT `id`, `name`, `description`, `price`, `period`
        FROM `offers`
        WHERE `id` = 1;
    ## Update
    UPDATE `offers`
    SET
        `name` = "Мытьё волос",
        `description` = "Мытьё волос. Укладка волос",
        `price` = 9.99,
        `period` = "25"
    WHERE `id` = 1;
    ## Delete
#     DELETE FROM `offers` WHERE `id` = 2;