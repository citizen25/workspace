/*�ֻ��� ī�װ�*/
CREATE TABLE topcategory(
	topcategory_id NUMBER
	, name VARCHAR(25)
	, PRIMARY KEY(topcategory_id)
);

CREATE SEQUENCE seq_topcategory
INCREMENT BY 1
START WITH 1;

/*���� ī�װ�*/
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

/*��ǰ �� ���̺�*/
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


/*topcategory ������ �ֱ�*/
INSERT INTO topcategory (topcategory_id, name) VALUES(seq_topcategory.nextval, '����');
INSERT INTO topcategory (topcategory_id, name) VALUES(seq_topcategory.nextval, '����');
INSERT INTO topcategory (topcategory_id, name) VALUES(seq_topcategory.nextval, '�׼�����');
INSERT INTO topcategory (topcategory_id, name) VALUES(seq_topcategory.nextval, '�Ź�');

/*subcategory ������ �ֱ�*/
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 1, '�����');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 1, '����');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 1, 'Ƽ����');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 1, '��Ʈ');

INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 2, 'û����');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 2, 'ġ��');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 2, '������');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 2, '�����');

INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 3, '�Ͱ���');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 3, '����');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 3, '�����');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 3, '����');

INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 4, '����');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 4, '����');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 4, '������');
INSERT INTO subcategory(subcategory_id, topcategory_id, name) VALUES(seq_subcategory.nextval, 4, '�ȭ');


SELECT * FROM topcategory;
SELECT * FROM subcategory;


COMMIT;


SELECT * FROM subcategory WHERE topcategory_id=3;


SELECT * FROM product WHERE product_name LIKE '%��%';
SELECT * FROM product WHERE brand LIKE '%P%';

--ù��° �����ڽ��� ���õ� ī�׷α� ���ϱ�
SELECT * FROM topcategory WHERE topcategory_id=(
	SELECT topcategory_id FROM subcatgory WHERE subcategory_id=����id
);
SELECT * FROM subcategory;

SELECT * FROM subcategory WHERE subcategory_id = �����Ѱ�;








