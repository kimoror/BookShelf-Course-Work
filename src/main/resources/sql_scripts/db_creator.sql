create table product_types(
                              id BIGSERIAL not null primary key,
                              name varchar(100)
);

create table makers(
                       id BIGSERIAL not null primary key,
                       name varchar(100)
);

create table products(
                        id BIGSERIAL not null primary key,
                        name varchar(100),
                        cost int,
                        img_path text,
                        short_description text,
                        maker_id bigint,
                        constraint fk_maker_id
                            foreign key (maker_id)
                                references makers(id),
                        product_type bigint,
                        constraint fk_product_type
                            foreign key (product_type)
                                references product_types(id)
);

create table users(
                      id bigserial not null primary key,
                      name varchar(100),
                      age int,
                      phone_number varchar(12),
                      address text,
                      have_account boolean
);

create table orders(
                       id bigserial not null primary key,
                       product_id bigint,
                       user_id bigint,
                       order_time date,
                       constraint fk_product_id
                           foreign key (product_id)
                               references product(id),
                       constraint fk_user_id
                           foreign key (user_id)
                               references users(id)
);

create table products_in_order(
    id bigserial primary key,
    order_id bigint,
    product_id bigint,
    num_of_product int,
    constraint fk_order_id
        foreign key (order_id)
            references orders(id),
    constraint fk_product_id
        foreign key (product_id)
            references products(id)
);

alter table orders drop column product_id

drop table products_in_order;