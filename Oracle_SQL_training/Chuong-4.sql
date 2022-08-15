--1 Liệt kê tên nhân viên, mã phòng ban và lương nhân viên được tăng 15% (PCTSAL)
SELECT DEPTNO, ENAME, SAL + (SAL * 0.15) AS PCTSAL
FROM EMP;

--2 Viết câu lệnh hiển thị như sau
SELECT RPAD(ENAME, 10, '*') || LPAD(JOB, 10, '*') AS EMPLOYEE_AND_JOB
FROM EMP;

--3 Viết câu lệnh hiển thị như sau
SELECT ENAME || '(' || EMP.JOB || ')'
FROM EMP;

--4. Viết câu lệnh hiển thị như sau
SELECT ENAME, DEPTNO, INITCAP(JOB)
FROM EMP;

--5 Tìm ngày thứ 6 đầu tiên cách 2 tháng so với ngày hiện tại hiển thị ngày dưới dạng 09 February 1990
SELECT TO_CHAR(NEXT_DAY(ADD_MONTHS(CURRENT_DATE, 2), 'Thứ Sáu'), 'dd Month yyyy') AS DAY
FROM DUAL

--6. Tìm thông itn về tên nhân viên, ngày gia nhập công ty của nhân viên phòng số 20, sao cho hiển thị như sau:
SELECT ENAME, TO_CHAR(HIREDATE, ' month, ddspth , yyyy') AS DATE_HIRED, DEPTNO
FROM EMP
WHERE DEPTNO = 20
  AND HIREDATE IS NOT NULL;

--7. Hiển thị tên nhân viên, ngày gia nhập công ty, ngày xét nâng lương (sau ngày gia  nhập công ty 1 năm), sắp xếp theo thứ tự ngày xét nâng lương.
SELECT ENAME, HIREDATE, ADD_MONTHS(HIREDATE, 12) AS REVIEW
FROM EMP
WHERE HIREDATE IS NOT NULL;

--8. Hiển thị tên nhân viên và lương dưới dạng
SELECT ENAME,
       (CASE
            WHEN SAL < 1500 THEN 'BELOW 1500'
            WHEN SAL = 1500 THEN 'ON TARGET'
            ELSE TO_CHAR(SAL) END
           )
FROM EMP
WHERE SAL IS NOT NULL;

-- 9. Cho biết thứ của ngày hiện tại
SELECT TO_CHAR(CURRENT_DATE,'Day') as Day FROM DUAL;

--10. Đưa chuỗi dưới dạng nn/nn, kiểm tra nếu khúng khuôn dạng trả lời là YES, ngược lại là NO. Kiểm tra với các chuỗi 12/34, 01/1a, 99\88
-- SELECT ENAME, DECODE(JOB, 'SALESMAN', 'CO SALESMAN', 'MANAGER', 'CO MANAGER', 'KHONG')
-- FROM EMP;
SELECT DECODE("nn/nn", 'YES', 'NO')
FROM DUAL
--11. Hiển thị tên nhân viên, ngày gia nhập công ty, ngày lĩnh lương sao cho ngày lĩnh lương phải vào thứ 6,
-- nhân viên chỉ được nhận lương sau ít nhất 15 ngày làm việc tại công ty, sắp xếp theo thứ tự ngày gia nhập công ty.
SELECT ENAME, HIREDATE, TO_CHAR(NEXT_DAY(HIREDATE + 15, 'Thứ Sáu'), 'dd - month') AS NgayNhanLuong
FROM EMP
WHERE CURRENT_DATE - HIREDATE >= 15
ORDER BY HIREDATE ASC;

--4.5.2. Hàm trên nhóm dữ liệu
--1. Tìm lương thấp nhất, lớn nhất và lương trung bình của tất cả các nhân viên
SELECT MIN(SAL) AS Sal_min
FROM EMP;

SELECT MAX(SAL) AS Sal_max
FROM EMP;

SELECT AVG(SAL) AS Sal_average
FROM EMP;

--2. Tìm lương nhỏ nhất và lớn của mỗi loại nghề nghiệp
SELECT JOB, MIN(SAL) AS Sal_max, MAX(SAL) AS Sal_max
FROM EMP
WHERE JOB IS NOT NULL
GROUP BY JOB;

--3. Tìm xem có bao nhiêu giám đốc trong danh sách nhân viên.
SELECT COUNT(JOB)
FROM EMP
WHERE JOB = 'MANAGER';

--4. Tìm tất cả các phòng ban mà số nhân viên trong phòng >3
SELECT DEPTNO, COUNT(EMPNO) AS SoLuongNhanVien
FROM EMP
GROUP BY DEPTNO
HAVING COUNT(EMPNO) > 3;

--5. Tìm ra mức lương nhỏ nhất của mỗi nhân viên làm việc cho một giám đốc nào đó sắp xếp theo thứ tự tăng dần của mức lương.
SELECT DEPTNO, MIN(SAL) as luongNhoNhat
FROM EMP
WHERE DEPTNO IN (SELECT DEPTNO FROM EMP WHERE JOB = 'MANAGER')
GROUP BY DEPTNO;
