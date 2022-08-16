create table VIETHIEN_NHA_XUAT_BAN
(
    ID         NUMBER(4) NOT NULL
        CONSTRAINT PK_ID_NHAXUATBAN PRIMARY KEY,
    MA         VARCHAR(10)
        CONSTRAINT UN_MA_NHAXUATBAN UNIQUE,
    TEN        VARCHAR2(54),
    TRANG_THAI NUMBER(1),
    DIA_CHI    VARCHAR2(255),
    MO_TA      VARCHAR2(255)
);
CREATE TABLE VIETHIEN_TAC_GIA
(
    ID      NUMBER(4) NOT NULL
        CONSTRAINT PK_ID_TAC_GIA PRIMARY KEY,
    MA      VARCHAR(10)
        CONSTRAINT UN_MA_TAC_GIA UNIQUE,
    TEN     VARCHAR2(54),
    SDT     VARCHAR(15),
    DIA_CHI VARCHAR2(255),
    MO_TA   VARCHAR2(255)
);
CREATE TABLE VIETHIEN_SACH
(
    ID                 NUMBER(4) NOT NULL
        CONSTRAINT PK_ID_SACH PRIMARY KEY,
    MA                 VARCHAR(10)
        CONSTRAINT UN_MA_SACH UNIQUE,
    TEN                VARCHAR2(54),
    ID_NXB             NUMBER(4)
        CONSTRAINT FK_ID_NXB REFERENCES VIETHIEN_NHA_XUAT_BAN (ID),
    ID_TACGIA          NUMBER(4)
        CONSTRAINT FK_ID_TACGIA REFERENCES VIETHIEN_TAC_GIA (ID),
    CHU_DE             VARCHAR2(30),
    NAM_SAN_XUAT       DATE,
    MOTA               VARCHAR(255),
    SO_LUONG_CON_LAI   NUMBER(4),
    SO_LUONG_DANG_MUON NUMBER(4),
    TONG_SO_SACH       NUMBER(5)
);

CREATE TABLE VIETHIEN_BAN_DOC
(
    ID                 NUMBER(4) NOT NULL
        CONSTRAINT PK_ID_BAN_DOC PRIMARY KEY,
    MA                 VARCHAR(10)
        CONSTRAINT UN_MA_BAN_DOC UNIQUE,
    TEN                VARCHAR2(54),
    SDT                VARCHAR(15),
    DIA_CHI            VARCHAR2(255),
    NGAY_SINH          DATE,
    NGAY_TAO_TAI_KHOAN DATE,
    HANG               NUMBER(1)
);

CREATE TABLE VIETHIEN_MUON_SACH
(
    ID            NUMBER(4) NOT NULL
        CONSTRAINT PK_ID_MUON_SACH PRIMARY KEY,
    ID_BAN_DOC    NUMBER(4)
        CONSTRAINT FK_ID_BAN_DOC REFERENCES VIETHIEN_BAN_DOC (ID),
    ID_SACH       NUMBER(4)
        CONSTRAINT FK_ID_SACH REFERENCES VIETHIEN_SACH (ID),
    SO_LUONG      NUMBER(1),
    NGAY_GIO_MUON DATE,
    NGAY_GIO_TRA  DATE,
    TRANG_THAI    NUMBER(1),
    GHI_CHU       VARCHAR2(255)
);

-- DROP TABLE VIETHIEN_NHA_XUAT_BAN;
-- DROP TABLE VIETHIEN_TAC_GIA;
-- DROP TABLE VIETHIEN_SACH;
-- DROP TABLE VIETHIEN_BAN_DOC;
-- DROP TABLE VIETHIEN_MUON_SACH;

-- ALTER TABLE VIETHIEN_NHA_XUAT_BAN MODIFY(MA VARCHAR(10));

-- ALTER TABLE VIETHIEN_MUON_SACH
--     ADD CHECK ( NGAY_GIO_TRA >= NGAY_GIO_MUON );

-- 1.	Viết script tạo cấu trúc các bảng. Đối với bảng Mượn Sách cần đánh partition trên trường ngày giờ mượn, và 2 local index: 1 index trên trường id bạn đọc,
-- 1 index trên id sách. Tên indexes theo cấu trúc : TENBANG_IDX_TENTRUONG
-- ALTER TABLE VIETHIEN_MUON_SACH ADD PARTITION by range collum(ngay_gio_muon)
CREATE TABLE VIETHIEN_MUON_SACH
(
    ID            NUMBER(4) NOT NULL
        CONSTRAINT PK_ID_MUON_SACH PRIMARY KEY,
    ID_BAN_DOC    NUMBER(4)
        CONSTRAINT FK_ID_BAN_DOC REFERENCES VIETHIEN_BAN_DOC (ID),
    ID_SACH       NUMBER(4)
        CONSTRAINT FK_ID_SACH REFERENCES VIETHIEN_SACH (ID),
    SO_LUONG      NUMBER(1),
    NGAY_GIO_MUON DATE,
    NGAY_GIO_TRA  DATE,
    TRANG_THAI    NUMBER(1),
    GHI_CHU       VARCHAR2(255)
)PARTITION BY RANGE (NGAY_GIO_MUON)(
    PARTITION PTN_1_NGAYGIOMUON_VH VALUES LESS THAN ('2022/04/01' , 'YYYY,MM,DD'),
    PARTITION PTN_2_NGAYGIOMUON_VH VALUES LESS THAN ('2022/07/01' , 'YYYY,MM,DD'),
    PARTITION PTN_3_NGAYGIOMUON_VH VALUES LESS THAN ('2022/10/01' , 'YYYY,MM,DD'),
    PARTITION PTN_4_NGAYGIOMUON_VH VALUES LESS THAN ('2022/01/01' , 'YYYY,MM,DD')
);


CREATE INDEX MUON_SACH_INDEX_ID_BAN_DOC ON VIETHIEN_MUON_SACH (ID_BAN_DOC);
CREATE INDEX MUON_SACH_INDEX_ID_SACH ON VIETHIEN_MUON_SACH (ID_SACH);

-- 2.	Viết script tạo sequence cho mỗi bảng. Sequence được dùng để insert trường Id cho các bảng. Tên sequence theo cấu trúc : TENBANG_SEQ
CREATE SEQUENCE VIETHIEN_NHA_XUAT_BAN_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;
CREATE SEQUENCE VIETHIEN_TAC_GIA_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;
CREATE SEQUENCE VIETHIEN_SACH_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;
CREATE SEQUENCE VIETHIEN_BAN_DOC_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;
CREATE SEQUENCE VIETHIEN_MUON_SACH_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;


-- 3.	Viết script tạo unique index cho các bảng nếu có.
CREATE UNIQUE INDEX VIETHIEN_NHA_XUAT_BAN_UID ON VIETHIEN_NHA_XUAT_BAN (MA);
CREATE UNIQUE INDEX VIETHIEN_TAC_GIA_UID ON VIETHIEN_TAC_GIA (MA);
CREATE UNIQUE INDEX VIETHIEN_SACH_UID ON VIETHIEN_SACH (MA);
CREATE UNIQUE INDEX VIETHIEN_BAN_DOC_UID ON VIETHIEN_BAN_DOC (MA);

-- 4.	Viết script insert dữ liệu mẫu cho tất cả các bảng.
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1000', 'KIM ĐỒNG', 1, 'BẮC NINH', 'MO TA NGAN GON VE NXB');
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1001', 'ĐỨC PHƯƠNG', 0, 'VĨNH PHÚC', 'MO TA NGAN GON VE NXB');
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1002', 'MINH THUỴ', 1, 'HÀ NỘI', 'MO TA NGAN GON VE NXB');
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1003', 'TẤT HOÀ', 0, 'THANH HOÁ', 'MO TA NGAN GON VE NXB');
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1004', 'HỒNG SƠN', 1, 'HẢI DƯƠNG', 'MO TA NGAN GON VE NXB');


INSERT INTO VIETHIEN_TAC_GIA(ID, MA, TEN, SDT, DIA_CHI, MO_TA)
VALUES (VIETHIEN_TAC_GIA_SEQ.nextval, 'TG1000', 'NGÔ TẤT TỐ', '0911119291', 'HÀ NAM', 'MÔ TẢ NGẮN GỌN');
INSERT INTO VIETHIEN_TAC_GIA(ID, MA, TEN, SDT, DIA_CHI, MO_TA)
VALUES (VIETHIEN_TAC_GIA_SEQ.nextval, 'TG1001', 'XUÂN QUỲNH', '0911119293', 'HÀ NỘI', 'MÔ TẢ NGẮN GỌN');

INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1000', 'SÓNG', 2, 3, 'VĂN HỌC', TO_DATE('2021/09/02', 'yyyy/mm/dd'), 'MÔ TẢ', 12,
        3,
        15);
INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1001', 'TẮT ĐÈN', 3, 2, 'VĂN HỌC', TO_DATE('2001/09/02', 'yyyy/mm/dd'), 'MÔ TẢ',
        19,
        6,
        25);
INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1002', 'VIỆT BẮC', 3, 2, 'VĂN HỌC', TO_DATE('2001/09/02', 'yyyy/mm/dd'), 'MÔ TẢ',
        19,
        6,
        25);
INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1003', 'TÂY TIẾN', 3, 2, 'VĂN HỌC', TO_DATE('2001/09/02', 'yyyy/mm/dd'), 'MÔ TẢ',
        19,
        6,
        25);
INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1004', 'ĐOÀN THUYỀN BẮT CUA', 3, 2, 'VĂN HỌC', TO_DATE('2002/09/02', 'yyyy/mm/dd'),
        'MÔ TẢ', 19,
        6,
        25);

INSERT INTO VIETHIEN_BAN_DOC(ID, MA, TEN, SDT, DIA_CHI, NGAY_SINH, NGAY_TAO_TAI_KHOAN, HANG)
VALUES (VIETHIEN_BAN_DOC_SEQ.nextval, 'BD1000', 'NGUYỄN VĂN A', '0999199999', 'HÀ NAM',
        TO_DATE('2002/08/11', 'yyyy/mm/dd'),
        TO_DATE('2022/08/02', 'yyyy/mm/dd'), 1);

INSERT INTO VIETHIEN_BAN_DOC(ID, MA, TEN, SDT, DIA_CHI, NGAY_SINH, NGAY_TAO_TAI_KHOAN, HANG)
VALUES (VIETHIEN_BAN_DOC_SEQ.nextval, 'BD1001', 'NGUYỄN VĂN B', '0999199129', 'HÀ GIANG',
        TO_DATE('2002/07/11', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 2);

INSERT INTO VIETHIEN_BAN_DOC(ID, MA, TEN, SDT, DIA_CHI, NGAY_SINH, NGAY_TAO_TAI_KHOAN, HANG)
VALUES (VIETHIEN_BAN_DOC_SEQ.nextval, 'BD1002', 'NGUYỄN VĂN C', '0999199129', 'HÀ GIANG',
        TO_DATE('2009/07/11', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 2);

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 2, 4, 3, TO_DATE('2022/08/12', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 1, 'KHÔNG');

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 3, 5, 6, TO_DATE('2022/08/02', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 1, 'KHÔNG');

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 3, 4, 6, TO_DATE('2022/08/02', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 1, 'KHÔNG');

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 3, 4, 6, TO_DATE('2022/07/02', 'yyyy/mm/dd'),
        TO_DATE('2022/7/12', 'yyyy/mm/dd'), 1, 'KHÔNG');

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 4, 4, 6, TO_DATE('2022/07/02', 'yyyy/mm/dd'),
        TO_DATE('2022/7/12', 'yyyy/mm/dd'), 1, 'KHÔNG');


SELECT *
FROM VIETHIEN_MUON_SACH;

-- 5.	Hiển thị dách sách tác giả và tổng số lượng sách của tác giả gồm các trường thông tin:
-- Mã Tác Giả, Tên Tác Giả, Chủ Đề, Số Lượng Sách
-- Sắp xếp theo số lượng sách giảm dần.
SELECT TG.MA MA_TG, TG.TEN TEN_TG, S.TEN TEN_SACH, S.CHU_DE CHU_DE, S.TONG_SO_SACH TONG_SO_SACH
FROM VIETHIEN_TAC_GIA TG
         JOIN VIETHIEN_SACH S on TG.ID = S.ID_TACGIA
ORDER BY S.TONG_SO_SACH DESC;
-- 6.	Hiển thị 10 quyển sách có số lượng được mượn nhiều nhất gồm các trường thông tin:
-- Mã Sách, Tên Sách, Tên Nhà Xuất Bản, Tên Tác Giả, Chủ Đề, Năm Xuất Bản, Số Lượng Còn Lại, Số Lượng Đã Mượn, Tổng Số
SELECT S.MA    MA_SACH,
       S.TEN   TEN_SACH,
       NXB.TEN TEN_NXB,
       TG.TEN  TEN_TG,
       CHU_DE,
       NAM_SAN_XUAT,
       SO_LUONG_CON_LAI,
       SO_LUONG_DANG_MUON,
       TONG_SO_SACH
FROM VIETHIEN_SACH S
         JOIN VIETHIEN_NHA_XUAT_BAN NXB on S.ID_NXB = NXB.ID
         JOIN VIETHIEN_TAC_GIA TG on S.ID_TACGIA = TG.ID
WHERE ROWNUM < 11
ORDER BY TONG_SO_SACH DESC;

-- 7.	Hiển thị  thông tin bạn đọc và sách được mượn từ ngày đầu tháng hiện tại đến thời điểm hiện tại.
-- Mã Bạn Đọc, Tên Bạn Đọc, Mã Sách, Tên Sách, Ngày Giờ Mượn, Số lượng
-- Sắp xếp theo ngày giờ mượn giảm dần, Tên bạn đọc tăng dần.
SELECT BD.MA MA_BAN_DOC, BD.TEN TEN_BAN_DOC, S.MA MA_SACH, S.TEN TEN_SACH, NGAY_GIO_MUON, SO_LUONG_DANG_MUON
FROM VIETHIEN_MUON_SACH MS
         JOIN VIETHIEN_BAN_DOC BD ON BD.ID = MS.ID_BAN_DOC
         JOIN VIETHIEN_SACH S ON MS.ID_SACH = S.ID
WHERE NGAY_GIO_MUON between to_char(trunc(sysdate, 'month')) and to_char(sysdate)
ORDER BY NGAY_GIO_MUON DESC, BD.TEN ASC;

-- 8.	Hiển thị 10 quyển sách có số lượng được mượn nhiều nhất tính từ đầu năm 2022
-- Mã Sách, Tên Sách, Số Lượng Đã Được Mượn.
SELECT S.MA MA_SACH, S.TEN TEN_SACH, SUM(MS.SO_LUONG)
FROM VIETHIEN_MUON_SACH MS
         JOIN VIETHIEN_SACH S on MS.ID_SACH = S.ID
WHERE TO_CHAR(NGAY_GIO_MUON, 'yyyy') >= 2022
  AND ROWNUM < 11
GROUP BY S.MA, S.TEN
ORDER BY SUM(SO_LUONG) DESC;

-- 9.	Hiển thị danh sách bạn đọc và số lần mượn sách tính từ đầu năm 2022 sắp xếp theo tên bạn đọc tăng dần:
-- Mã Bạn Đọc, Tên Bạn Đọc, Số Lần Mượn
SELECT MA, TEN, COUNT(MA) SO_LAN_MUON
FROM VIETHIEN_MUON_SACH
         JOIN VIETHIEN_BAN_DOC ON VIETHIEN_MUON_SACH.ID_BAN_DOC = VIETHIEN_BAN_DOC.ID
WHERE TO_CHAR(NGAY_GIO_MUON, 'yyyy') >= 2022
GROUP BY MA, TEN
ORDER BY TEN ASC;

-- 10.	Hiển thị thông tin bạn đọc gồm có:
-- Mã Bạn Đọc, Tên Bạn Đọc, Tuổi (được tính dựa vào trường ngày sinh)
SELECT MA, TEN, TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY') AS TUOI_BAN_DOC
FROM VIETHIEN_BAN_DOC;

-- 11.	Thống kê tổng số bạn đọc theo độ tuổi
-- Tuổi, Tổng số Bạn Đọc
SELECT TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY')        AS TUOI_BAN_DOC,
       COUNT(TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY')) AS SOLUONG
FROM VIETHIEN_BAN_DOC
group by TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY');

-- 12.	Thống kê số lượng sách được xuất bản theo Nhà Xuất Bản, Theo năm xuất bản.
-- Năm Xuất Bản, Mã Nhà Xuất Bản,Tên Nhà Xuất Bản, Số Lượng Sách
-- Sắp xếp theo Năm xuất bản giảm dần, Tên Nhà xuất bản tăng dần.
SELECT TO_CHAR(NAM_SAN_XUAT, 'YYYY') AS NAMSB, SB.MA, SB.TEN, SUM(TONG_SO_SACH) AS TONG_SO_LUONG_SACH
FROM VIETHIEN_NHA_XUAT_BAN SB
         JOIN VIETHIEN_SACH VS on SB.ID = VS.ID_NXB
GROUP BY SB.TEN, SB.MA, NAM_SAN_XUAT
ORDER BY TO_CHAR(NAM_SAN_XUAT, 'YYYY') DESC, TEN ASC;

-- 13.	Hiển thị 5 quyển sách được các bạn đọc có độ tuổi từ 18 đến 25 mượn nhiều nhất tính từ đầu năm 2022. (Tính theo trường số lượng mượn của sách)
-- Mã Sách, Tên Sách, Số Lượng Được Mượn
SELECT S.MA MA_SACH, S.TEN TEN_SACH, BD.TEN TEN_BAN_DOC, SUM(SO_LUONG_DANG_MUON) SO_LUONG_DANG_MUON
FROM VIETHIEN_MUON_SACH MS
         JOIN VIETHIEN_SACH S ON MS.ID_SACH = S.ID
         JOIN VIETHIEN_BAN_DOC BD on BD.ID = MS.ID_BAN_DOC
WHERE TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY') BETWEEN 18 AND 25
  AND ROWNUM < 6
GROUP BY S.MA, S.TEN, BD.TEN;

-- 14.	Hiển thị toàn bộ bạn đọc và sách mà bạn đọc đấy mượn, sẽ có bạn chưa mượn vẫn cần hiển thị và tên sách để là null.
-- Mã bạn đọc, tên ban đọc, tên sách
SELECT BD.MA MA_BAN_DOC, BD.TEN TEN_BAN_DOC, S.TEN TEN_SACH
FROM VIETHIEN_BAN_DOC BD
         LEFT JOIN VIETHIEN_MUON_SACH MS on BD.ID = MS.ID_BAN_DOC
         LEFT JOIN VIETHIEN_SACH S on MS.ID_SACH = S.ID