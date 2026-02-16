DROP TABLE IF EXISTS user_roles (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,

                            CONSTRAINT uq_user_role UNIQUE (user_id, role_id),
                            CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
                            CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id)
);