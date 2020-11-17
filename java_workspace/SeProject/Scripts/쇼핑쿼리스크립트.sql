/*최상위 카테고리*/
CREATE TABLE topcategory(
	topcategory_id NUMBER
	, name VARCHAR(25)
	, PRIMARY KEY(topcategory_id)
);

CREATE SEQUENCE seq_topcategory
INCREMENT BY 1
START WITH 1;

/*하위 카테고리*/
CREATE TABLE subcategory(
	subcategory_id NUMBER
	, topcategory_id NUMBER
	, name VARCHAR(25)
	, PRIMARY KEY(subcategory_id)
	, CONSTRAINT fk_topcategory  FOREIGN KEY (topcategory_id) REFERENCES topcategory(topcategory_id)
);

CREATE SEQUENCE seq_subcategory
INCREMENT BY 1
START WITH 1;

/*제품 상세 테이블*/
CREATE TABLE product(
	product_id NUMBER
	, subcategory_id NUMBER
	, product_name VARCHAR(30)
	, brand VARCHAR(20)
	, price NUMBER DEFAULT 0
	, filename VARCHAR(20)
	, PRIMARY KEY(product_id)
	, CONSTRAINT fk_subcategory FOREIGN KEY (subcategory_id) REFERENCES subcategory(subcategory_id)
);

CREATE SEQUENCE seq_product
INCREMENT BY 1
START WITH 1;


/*topcategory 데이터 넣기*/
INSERT INTO topcategory (topcategory_id, name) VALUES(seq_topcategory.nextval, '상의');
INSERT INTO topcategory (topcategory_id, name) VALUES(seq_topcategory.nextval, '하의');
INSERT INTO topcategory (topcategory_id, name) VALUES(seq_topcategory.nextval, '액세서리');
INSERT INTO topcategory (topcategory_id, name) VALUES(seq_topcategory.nextval, '신발');

/*subcategory 데이터 넣기*/
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 1, '가디건');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 1, '셔츠');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 1, '티셔츠');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 1, '니트');

INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 2, '청바지');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 2, '치마');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 2, '슬랙스');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 2, '면바지');

INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 3, '귀걸이');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 3, '팔찌');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 3, '목걸이');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 3, '반지');

INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 4, '구두');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 4, '샌들');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 4, '슬리퍼');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 4, '운동화');


SELECT * FROM topcategory;
SELECT * FROM subcategory;


COMMIT;


SELECT * FROM subcategory WHERE topcategory_id=3;


SELECT * FROM product WHERE product_name LIKE '%폴%';
SELECT * FROM product WHERE brand LIKE '%P%';

--첫번째 셀렉박스에 선택될 카테로기 구하기
SELECT * FROM topcategory WHERE topcategory_id=(
	SELECT topcategory_id FROM subcatgory WHERE subcategory_id=선택id
);
SELECT * FROM subcategory;

SELECT * FROM subcategory WHERE subcategory_id = 선택한값;








