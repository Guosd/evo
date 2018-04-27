CREATE DATABASE evo_activiti DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

DROP USER 'evo_activiti'@'%';
CREATE USER 'evo_activiti'@'%' IDENTIFIED BY 'evo_activiti';
GRANT ALL ON evo_activiti.* TO 'evo_activiti'@'%';
FLUSH PRIVILEGES;
