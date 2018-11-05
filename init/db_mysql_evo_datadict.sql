CREATE DATABASE evo_datadict DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_datadict'@'%' IDENTIFIED BY 'evo_datadict';
GRANT ALL ON evo_datadict.* TO 'evo_datadict'@'%';

FLUSH PRIVILEGES;
