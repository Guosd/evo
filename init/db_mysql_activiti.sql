CREATE DATABASE evo_activiti DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

DROP USER 'evo_activiti'@'%';
CREATE USER 'evo_activiti'@'%' IDENTIFIED BY 'evo_activiti';
GRANT ALL ON evo_activiti.* TO 'evo_activiti'@'%';
FLUSH PRIVILEGES;

-- 5.X
CREATE DATABASE evo_demo_order DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE evo_demo_account DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE evo_demo_storage DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_demo_order'@'%' IDENTIFIED BY 'evo_demo_order';
CREATE USER 'evo_demo_account'@'%' IDENTIFIED BY 'evo_demo_account';
CREATE USER 'evo_demo_storage'@'%' IDENTIFIED BY 'evo_demo_storage';

GRANT ALL ON evo_demo_order.* TO 'evo_demo_order'@'%';
GRANT ALL ON evo_demo_account.* TO 'evo_demo_account'@'%';
GRANT ALL ON evo_demo_storage.* TO 'evo_demo_storage'@'%';
