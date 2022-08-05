-- 1. Hiển thị toàn bộ tên nhân viên và tên phòng ban làm việc sắp xếp theo tên phòng ban.
SELECT ENAME, DEPT.DNAME
FROM EMP,
     DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO;

-- 2. Hiển thị tên nhân viên, vị trí địa lý, tên phòng với điều kiện lương >1500.
SELECT ENAME, DEPT.LOC, DNAME
FROM EMP,
     DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO
  AND EMP.SAL > 1500;

-- 3. Hiển thị tên nhân viên, nghề nghiệp, lương và mức lương.
SELECT ENAME, JOB, SAL, GRADE
FROM EMP,
     SALGRADE
WHERE EMP.SAL BETWEEN SALGRADE.LOSAL AND SALGRADE.HISAL;

--4. Hiển thị tên nhân viên, nghề nghiệp, lương và mức lương, với điều kiện mức lương = 3.
SELECT ENAME, JOB, SAL, GRADE
FROM EMP,
     SALGRADE
WHERE EMP.SAL BETWEEN SALGRADE.LOSAL AND SALGRADE.HISAL
  AND SALGRADE.GRADE = 3;

--5. Hiển thị những nhân viên tại DALLAS
SELECT ENAME, LOC, SAL
FROM EMP
         JOIN DEPT ON EMP.DEPTNO = DEPT.DEPTNO
WHERE DEPT.LOC = 'DALLAS';

--6. Hiển thị tên nhân viên , nghề nghiệp, lương, mức lương, tên phòng làm việc trừ nhân viên có nghề là cleck và sắp xếp theo chiều giảm.
SELECT ENAME, JOB, SAL, GRADE, DNAME
FROM EMP,
     SALGRADE,
     DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO
  AND EMP.SAL BETWEEN SALGRADE.LOSAL AND SALGRADE.HISAL
  AND EMP.JOB != 'CLERK'
ORDER BY DNAME DESC;

--7. Hiển thị chi tiết về những nhân viên kiếm được 36000 $ 1 năm hoặc nghề là cleck. (gồm các trường tên, nghề, thu nhập, mã phòng, tên phòng, mức lương)
SELECT ENAME, JOB, SAL * 12, DNAME, GRADE
FROM EMP,
     SALGRADE,
     DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO
  AND EMP.SAL BETWEEN SALGRADE.LOSAL AND SALGRADE.HISAL
  AND (SAL * 12 >= 36000
    OR JOB = 'CLERK');
-- 8. Hiển thị những phòng không có nhân viên nào làm việc.
SELECT *
FROM DEPT
WHERE DEPTNO NOT IN (SELECT DISTINCT EMP.DEPTNO FROM EMP WHERE EMP.DEPTNO IS NOT NULL);

--9. Hiển thị mã nhân viên, tên nhân viên, mã người quản lý, tên người quản lý
SELECT e.EMPNO,
       e.ENAME,
       m.MGR,
       m.ENAME
FROM EMP e,
     EMP m
WHERE e.mgr = m.empno
  AND e.sal < m.sal;

--10. Như câu 9 hiển thị thêm thông tin về ông KING

--11. Hiển thị nghề nghiệp được tuyển dụng vào năm 1981 và không được tuyển dụng vào năm 1994.
SELECT DISTINCT JOB
FROM EMP
WHERE TO_CHAR(HIREDATE, 'yyyy') = 1981
  AND JOB NOT IN (SELECT DISTINCT JOB FROM EMP WHERE TO_CHAR(HIREDATE, 'yyyy') = 1994);

--12. Tìm những nhân viên gia nhập công ty trước giám đốc của họ.
SELECT *
FROM EMP
WHERE HIREDATE < ALL (SELECT HIREDATE FROM EMP WHERE JOB = 'MANAGER')
  AND JOB != 'MANAGER';

--13. Tìm tất cả các nhân viên, ngày gia nhập công ty, tên nhân viên, tên người giám đốc và ngày gia nhập công ty của người giám đốc ấy.
SELECT DISTINCT E.HIREDATE, E.ENAME, M.HIREDATE, M.ENAME
FROM EMP E,
     EMP M
WHERE E.JOB != 'MANAGER'
  AND M.JOB = 'MANAGER'

--14. Tìm những nhân viên kiếm được lương cao nhất trong mỗi loại nghề nghiệp.
SELECT DISTINCT JOB, SAL
FROM EMP
WHERE SAL IN (SELECT DISTINCT MAX(SAL)
              FROM EMP
              GROUP BY JOB);
--15. Tìm mức lương cao nhất trong mỗi phòng ban, sắp xếp theo thứ tự phòng ban.
SELECT D.DEPTNO, DNAME, MAX(SAL)
FROM EMP
         JOIN DEPT D on EMP.DEPTNO = D.DEPTNO
GROUP BY D.DEPTNO, DNAME;

--16. Tìm nhân viên gia nhập vào phòng ban sớm nhất

--17. Hiển thị những nhân viên có mức lương lớn hơn lương TB của phòng ban mà họ làm

--18. Hiển thị tên nhân viên, mã nhân viên, mã giám đốc, tên giám đốc, phòng ban làm  việc của giám đốc, mức lương của giám đốc.
SELECT *
FROM EMP
where JOB = 'MANAGER';
SELECT *
FROM DEPT;
SELECT *
FROM SALGRADE;
