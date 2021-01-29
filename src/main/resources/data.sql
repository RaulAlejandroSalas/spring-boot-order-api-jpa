INSERT INTO products(product_name,product_price) VALUES ('Product 1', 100);
INSERT INTO products(product_name,product_price) VALUES ('Product 2', 78);
INSERT INTO products(product_name,product_price) VALUES ('Product 3', 11);
INSERT INTO products(product_name,product_price) VALUES ('Product 4', 22);
INSERT INTO products(product_name,product_price) VALUES ('Product 5', 78);
INSERT INTO products(product_name,product_price) VALUES ('Product 6', 99);
INSERT INTO products(product_name,product_price) VALUES ('Product 7', 345);
INSERT INTO products(product_name,product_price) VALUES ('Product 8', 44);
INSERT INTO products(product_name,product_price) VALUES ('Product 9', 20);
INSERT INTO products(product_name,product_price) VALUES ('Product 10', 33);
INSERT INTO products(product_name,product_price) VALUES ('Product 11', 4434);
INSERT INTO products(product_name,product_price) VALUES ('Product 12', 233);


INSERT INTO orders(created_at,total) VALUES(now(),600);
INSERT INTO order_lines(fk_order,fk_product,price,quantity,total) VALUES(1,1,100,1,100);
INSERT INTO order_lines(fk_order,fk_product,price,quantity,total) VALUES(1,2,200,1,200);
INSERT INTO order_lines(fk_order,fk_product,price,quantity,total) VALUES(1,3,300,1,300);

INSERT INTO orders(created_at,total) VALUES(now(),2100);
INSERT INTO order_lines(fk_order,fk_product,price,quantity,total) VALUES(2,6,600,1,600);
INSERT INTO order_lines(fk_order,fk_product,price,quantity,total) VALUES(2,7,700,1,700);
INSERT INTO order_lines(fk_order,fk_product,price,quantity,total) VALUES(2,8,800,1,800);
