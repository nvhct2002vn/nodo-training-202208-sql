-- 1. Tạo bảng PROJECT với các column được chỉ ra dưới đây, PROJID là promary key, và P_END_DATE > P_START_DATE.
CREATE TABLE PROJECT_HIENNV
(
    PROJID        NUMBER(4) NOT NULL
        CONSTRAINT PK_PROJID_HIENNV PRIMARY KEY,
    P_DESC        VARCHAR2(20),
    P_START_DATE  DATE,
    P_END_DATE    DATE,
    BUDGET_AMOUNT NUMBER(7, 2),
    MAX_NO_STAFF  NUMBER(2),
    CHECK ( P_END_DATE > P_START_DATE )
)

-- 2. Tạo bảng ASSIGNMENTS với các column được chỉ ra dưới đây, đồng thời cột PROJID là foreign key tới bảng PROJECT, cột EMPNO là foreign key tới bảng EMP.
CREATE TABLE ASSIGNMENTS_HIENNV
(
    PROJID       NUMBER(4) NOT NULL
        CONSTRAINT FK_PROJID_HIENNV REFERENCES PROJECT_HIENNV,
    EMPNO        NUMBER(4) NOT NULL
        CONSTRAINT FK_EMPNO_HIENNV REFERENCES EMP,
    A_START_DATE DATE,
    A_END_DATE   DATE,
    BILL_AMOUNT  NUMBER(4, 2),
    ASSIGN_TYPE  VARCHAR2(2)
);

-- 3. Thêm column COMMENTS kiểu LONG vào bảng PROJECTS. Thêm column HOURS kiểu NUMBER vào bảng ASSIGNMENTS.
ALTER TABLE PROJECT_HIENNV
    ADD (COMMENTS LONG);

ALTER TABLE ASSIGNMENTS_HIENNV
    ADD (HOURS NUMBER);

-- 4. Sử dụng view USER_OBJECTS hiển thị tất cả các đối tượng user sở hữu.
SELECT *
FROM SCOTT.USER_OBJECTS;

-- 5. Thêm ràng buộc duy nhất (UNIQUE) cho 2 column PROJECT_ID và EMPNO của bảng ASSIGNMENTS.
ALTER TABLE ASSIGNMENTS_HIENNV
    ADD(CONSTRAINT UQ_ASSIGNMENTS_HIENNV UNIQUE (PROJID, EMPNO));

-- 6. Xem các thông tin về các ràng buộc trong USER_CONSTRAINTS.
SELECT *
FROM USER_CONSTRAINTS
WHERE TABLE_NAME = 'ASSIGNMENTS_HIENNV';

-- 7. Xem trong USER hiện tại có tất cả bao nhiêu bảng.
SELECT COUNT(TABLE_NAME) AS SLBANG
FROM DICTIONARY;

SELECT *
FROM PROJECT_HIENNV;
SELECT *
FROM ASSIGNMENTS_HIENNV;