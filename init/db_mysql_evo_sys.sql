CREATE DATABASE evo_sys DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_sys'@'%' IDENTIFIED BY 'evo_sys';
GRANT ALL ON evo_sys.* TO 'evo_sys'@'%';

FLUSH PRIVILEGES;
