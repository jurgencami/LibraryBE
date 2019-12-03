INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'User role', 'USER');

INSERT INTO user (username, password, role_id) VALUES
  ('admin', '$2a$10$dGBC88poqx969922IRmWlOCVm1ICH/1t7d0qAsnJGoVzU514CzhXm', 1) ;
INSERT INTO user (username, password, role_id) VALUES
  ('user','$2a$10$3Kxsif8mp2LBglrZxQdmJuWG6/3bG3hm0YXFGQ0/t6IU3/hSkuV2K', 2);
INSERT INTO user (username, password, role_id) VALUES
  ('user1','$2a$10$wntdHA4ypFJ1URRFPiWzUOcm874EbKcK2SYqAfq/fkInmKM/dfpAG', 2);
