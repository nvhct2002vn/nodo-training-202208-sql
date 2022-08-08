--1. Tạo view có hiển thị như sau:
CREATE VIEW VIEW_AGGREDATES_HIENNV AS
SELECT *
FROM AGGREDATES;

--2. Tạo view để nhập số liệu vào bảng ASIGNMENT với các điều kiện sau:
CREATE VIEW VIEW_ASSINGMENTS_HIENNV AS
SELECT *
FROM ASSIGNMENTS_HIENNV
WHERE PROJID < 2000
  AND ASSIGNMENTS_HIENNV.A_START_DATE < ASSIGNMENTS_HIENNV.A_END_DATE
  AND (
            ASSIGNMENTS_HIENNV.ASSIGN_TYPE = 'PS' OR
            ASSIGNMENTS_HIENNV.ASSIGN_TYPE = 'WT' OR
            ASSIGNMENTS_HIENNV.ASSIGN_TYPE = 'ED'
    )
  AND EMPNO IS NOT NULL
  AND ((ASSIGNMENTS_HIENNV.BILL_AMOUNT < 50 AND ASSIGNMENTS_HIENNV.ASSIGN_TYPE = 'PS') OR
       (ASSIGNMENTS_HIENNV.BILL_AMOUNT < 60 AND ASSIGNMENTS_HIENNV.ASSIGN_TYPE = 'WT') OR
       (ASSIGNMENTS_HIENNV.BILL_AMOUNT < 70 AND ASSIGNMENTS_HIENNV.ASSIGN_TYPE = 'ED')
    )
WITH CHECK OPTION CONSTRAINT CHECK_ASSIGNMENTS_HIENNV;

INSERT INTO VIEW_ASSINGMENTS_HIENNV (PROJID, EMPNO, A_START_DATE, A_END_DATE, BILL_AMOUNT, ASSIGN_TYPE, HOURS)
VALUES (4, 7369, '01-01-88', '03-01-88', 49, 'PS', 15);

--3. Định nghĩa bảng MESSAGES có cấu trúc.
CREATE TABLE MESSAGES_HIENNV
(
    NUMCOL1  NUMBER(9, 2),
    NUMCOL2  NUMBER(9, 2),
    CHARCOL1 VARCHAR2(60),
    CHARCOL2 VARCHAR2(60),
    DATECOL1 DATE,
    DATECOL2 DATE
);

CREATE VIEW VIEW_MESSAGES_HIENNV AS
SELECT *
FROM MESSAGES_HIENNV;

SELECT *
FROM VIEW_MESSAGES_HIENNV