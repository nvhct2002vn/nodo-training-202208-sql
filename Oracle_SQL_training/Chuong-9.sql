--1. Tạo Index trên cột PROJID cho bảng ASSIGNMENT.
CREATE INDEX INDEX_PROJID_HIENNV ON ASSIGNMENTS_HIENNV (PROJID);

--2. Hiển thị danh sách của nhân viên thuộc sự quản lý của người có tên là 1 biến được nhập từ bàn phím
SELECT E.EMPNO,
       E.ENAME,
       E.JOB,
       M.MGR,
       E.HIREDATE,
       E.SAL,
       E.COMM,
       M.DEPTNO
FROM EMP E,
     EMP M
WHERE E.MGR = M.EMPNO
  AND M.ENAME = 'CLARK';
