load data
infile 'animal.csv'
insert into table animal
fields terminated by ','
(code, category, name, location)
