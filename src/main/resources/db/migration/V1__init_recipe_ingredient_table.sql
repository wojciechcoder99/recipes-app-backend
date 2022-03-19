create table recipes
(
    recipe_id bigint primary key auto_increment,
    name varchar(255) not null,
    description varchar(255) null,
    image_path varchar(255) null
);

create table ingredients
(
        ing_id bigint primary key auto_increment,
        name varchar(255) not null,
        amount int(10) not null
);
alter table ingredients add column recipe_id bigint null;
alter table ingredients add foreign key (recipe_id) references recipes (recipe_id);