INSERT INTO users (username, password, enabled, apikey, email) VALUES ('henk', '$2y$10$Wr0ip.86.8CpX4LHZVfsYes0DaNg0/91xvgdHTsIJlmOMLa5PN5SS', true, '7847493', 'test@testy.tst');
INSERT INTO authorities (username, authority) VALUES ('henk', 'ROLE_ADMIN');

INSERT INTO users (username, password, enabled, apikey, email) VALUES ('reni', '$2y$10$yAJE2LDKcOYxg8wPsXDUoOnfQiYWfU8SQGZBpM4HR5Kt9MNx08JR.', true, '78474931', 'reni@reni.nl');
INSERT INTO authorities (username, authority) VALUES ('reni', 'ROLE_OWNER');

INSERT INTO users (username, password, enabled, apikey, email) VALUES ('tommy', '$2y$10$2X30aT3je.SwLQJHIbQ7JO/uDHPqEBpQTlo0VvVXo6y6ucH/pxfG6', true, '78474932', 'tommy@tommy.nl');
INSERT INTO authorities (username, authority) VALUES ('tommy', 'ROLE_CUSTOMER');

