CREATE DATABASE evo_sys DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE evo_datadict DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE evo_activiti DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_sys'@'%' IDENTIFIED BY 'evo_sys';
CREATE USER 'evo_datadict'@'%' IDENTIFIED BY 'evo_datadict';
CREATE USER 'evo_activiti'@'%' IDENTIFIED BY 'evo_activiti';
GRANT ALL ON evo_sys.* TO 'evo_sys'@'%';
GRANT ALL ON evo_datadict.* TO 'evo_datadict'@'%';
GRANT ALL ON evo_activiti.* TO 'evo_activiti'@'%';
