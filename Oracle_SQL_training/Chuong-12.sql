--1. Viết đoạn chương trình tìm kiếm các hàng trong bảng EMP với biến được đưa từ ngoài vào
-- là &1 dạng JOb_type(emp.job%type) và đưa ra thông báo thích hợp vào bảng MESSAGES.
DECLARE
    CURSOR C_EMP IS SELECT ENAME, HIREDATE, SAL
                    FROM EMP
                    WHERE JOB = 'MANAGER';
    COUNT_EMP NUMBER := 0;
    V_EMP     C_EMP%ROWTYPE;
BEGIN
    OPEN C_EMP;
    LOOP
        FETCH C_EMP INTO V_EMP;
        COUNT_EMP := COUNT_EMP + 1;
        EXIT WHEN C_EMP%notfound;
    END LOOP;
    CLOSE C_EMP;
    INSERT INTO MESSAGES_HIENNV (CHARCOL2)
    VALUES (COUNT_EMP);
END;


--2. Viết đoạn chương trình ghi dữ liệu vào bảng MESSAGES với cột NUMCOL1 mang giá trị là 1 nếu là row 1 được Insert, 2 nếu row 2 được Insert... .
-- Không được Insert những row có giá trị là 6 hoặc 8, thoát khỏi vòng lặp insert sau giá trị 10. Commit sau vòng lặp.
DECLARE
    MESSAGES_N1 NUMBER := 0;
BEGIN
    WHILE MESSAGES_N1 < 10
        LOOP
            MESSAGES_N1 := MESSAGES_N1 + 1;
            IF MESSAGES_N1 = 6 OR MESSAGES_N1 = 8 THEN
                INSERT INTO MESSAGES_HIENNV(NUMCOL1)
                VALUES (NULL);
            ELSE
                INSERT INTO MESSAGES_HIENNV(NUMCOL1)
                VALUES (MESSAGES_N1);
            END IF;
        END LOOP;
END;

--3. Liệt kê các cột ENAME, HIREDATE, SAL Với điều kiện EMPNO bằng giá trị biến
-- &EMPLOYEE_NO được đưa vào, sau đó kiểm tra:
-- 1.1 Có phải mức lương lớn hơn 1200
-- 1.2 Tên nhân viên có phải có chứa chữ T
-- 1.3 ngày gia nhập cơ quan có phải là tháng 10 (DEC)
-- và đưa giá trị kiểm tra này vào bảng message cột charcol1 (thử với các giá trị 7654, 7369,
-- 7900, 7876)
DECLARE
    CURSOR C_EMP IS SELECT ENAME, HIREDATE, SAL
                    FROM EMP
                    WHERE EMPNO = 7369;
    V_EMP C_EMP%ROWTYPE;
BEGIN
    OPEN C_EMP;
    LOOP
        FETCH C_EMP INTO V_EMP;
        EXIT WHEN C_EMP%notfound;
    END LOOP;
    CLOSE C_EMP;
    IF V_EMP.SAL > 1200 AND V_EMP.ENAME LIKE '%T%' AND TO_CHAR(V_EMP.HIREDATE, 'MM') = 10
    THEN
        INSERT INTO MESSAGES_HIENNV (CHARCOL1)
        VALUES (V_EMP.ENAME || ' ' || V_EMP.HIREDATE || ' ' || V_EMP.SAL);
    END IF;
END;


-- 4. Đưa vào vòng lặp v từ 1 đến 10 lệnh:
-- UPDATE messages
-- SET numcol2=100
-- WHERE numcol1 = v;
-- nếu bất kỳ một lần update nào đó có số lượng row >1 thì exit khỏi vòng lặp.
DECLARE
    V      NUMBER := 0;
    CURSOR C_MESS IS SELECT NUMCOL1, COUNT(NUMCOL1) COUNT
                     FROM MESSAGES_HIENNV
                     WHERE NUMCOL1 IS NOT NULL
                     GROUP BY NUMCOL1
                     ORDER BY NUMCOL1 ASC;
    V_MESS C_MESS%rowtype;
BEGIN
    OPEN C_MESS;
    WHILE V < 11
        LOOP
            FETCH C_MESS INTO V_MESS;
            V := V + 1;
            IF V_MESS.COUNT > 1 THEN
                RETURN;
            ELSE
                UPDATE MESSAGES_HIENNV SET NUMCOL2 = 100 WHERE NUMCOL1 = V;
            END IF;
        END LOOP;
    CLOSE C_MESS;
END;


----------------------------------------------------------------------------------------------------

SELECT *
FROM MESSAGES_HIENNV;

TRUNCATE TABLE MESSAGES_HIENNV;