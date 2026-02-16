CREATE TABLE order_items (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             order_id BIGINT NOT NULL,
                             product_name VARCHAR(255),
                             quantity INT,
                             price DOUBLE,

                             CONSTRAINT fk_order
                                 FOREIGN KEY (order_id)
                                     REFERENCES orders(id)
);