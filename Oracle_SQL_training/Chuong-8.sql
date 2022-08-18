--1. Thêm dữ liệu vào bảng PROJECTS.
INSERT INTO PROJECT_HIENNV
(PROJID, P_DESC, P_START_DATE, P_END_DATE, BUDGET_AMOUNT, MAX_NO_STAFF, COMMENTS)
VALUES (1, 'WRITE C030 COURSE 1', '09-JAN-88', '11-JAN-88', 700, 1, 'COMMENTS');

INSERT INTO PROJECT_HIENNV (PROJID, P_DESC, P_START_DATE, P_END_DATE, BUDGET_AMOUNT, MAX_NO_STAFF, COMMENTS)
VALUES (2, 'PROOF READ NOTES 2', '11-JAN-89', '21-JAN-89', 100, 1, 'COMMENTS');

--2. Thêm dữ liệu vào bảng ASSIGNMENTS.

INSERT INTO ASSIGNMENTS_HIENNV (PROJID, EMPNO, A_START_DATE, A_END_DATE, BILL_AMOUNT, ASSIGN_TYPE, HOURS)
VALUES (3, 7369, '01-01-88', '03-01-88', 50.00, 'WR', 15);
INSERT INTO ASSIGNMENTS_HIENNV (PROJID, EMPNO, A_START_DATE, A_END_DATE, BILL_AMOUNT, ASSIGN_TYPE, HOURS)
VALUES (3, 7902, '04-01-88', '07-01-88', 55.00, 'WR', 20);
INSERT INTO ASSIGNMENTS_HIENNV (PROJID, EMPNO, A_START_DATE, A_END_DATE, BILL_AMOUNT, ASSIGN_TYPE, HOURS)
VALUES (4, 7844, '01-01-88', '03-01-88', 45.50, 'PF', 30);

--3. Cập nhật trường ASIGNMENT_TYPE từ WT thành WR.
UPDATE ASSIGNMENTS_HIENNV
SET ASSIGN_TYPE = 'WT'
WHERE ASSIGN_TYPE = 'WR';

--4. Nhập thêm số liệu vào bảng ASSIGNMENTS.
INSERT INTO ASSIGNMENTS_HIENNV (PROJID, EMPNO, A_START_DATE, A_END_DATE, BILL_AMOUNT, ASSIGN_TYPE, HOURS)
VALUES (4, 7844, '01-01-88', '03-01-88', 45.50, 'WT', 30);

SELECT *
FROM ASSIGNMENTS_HIENNV;
SELECT *
FROM PROJECT_HIENNV;