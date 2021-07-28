drop table if exists tvshow CASCADE;
create table tvshow (
	
	id integer PRIMARY KEY AUTO_INCREMENT, 
	episodes integer not null,
	genre varchar(255),
 	name varchar(255),
  	rating integer not null
  	);