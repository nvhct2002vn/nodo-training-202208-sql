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

-- 1.	Vi???t script t???o c???u tr??c c??c b???ng. ?????i v???i b???ng M?????n S??ch c???n ????nh partition tr??n tr?????ng ng??y gi??? m?????n, v?? 2 local index: 1 index tr??n tr?????ng id b???n ?????c,
-- 1 index tr??n id s??ch. T??n indexes theo c???u tr??c : TENBANG_IDX_TENTRUONG
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

-- 2.	Vi???t script t???o sequence cho m???i b???ng. Sequence ???????c d??ng ????? insert tr?????ng Id cho c??c b???ng. T??n sequence theo c???u tr??c : TENBANG_SEQ
CREATE SEQUENCE VIETHIEN_NHA_XUAT_BAN_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;
CREATE SEQUENCE VIETHIEN_TAC_GIA_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;
CREATE SEQUENCE VIETHIEN_SACH_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;
CREATE SEQUENCE VIETHIEN_BAN_DOC_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;
CREATE SEQUENCE VIETHIEN_MUON_SACH_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE;


-- 3.	Vi???t script t???o unique index cho c??c b???ng n???u c??.
CREATE UNIQUE INDEX VIETHIEN_NHA_XUAT_BAN_UID ON VIETHIEN_NHA_XUAT_BAN (MA);
CREATE UNIQUE INDEX VIETHIEN_TAC_GIA_UID ON VIETHIEN_TAC_GIA (MA);
CREATE UNIQUE INDEX VIETHIEN_SACH_UID ON VIETHIEN_SACH (MA);
CREATE UNIQUE INDEX VIETHIEN_BAN_DOC_UID ON VIETHIEN_BAN_DOC (MA);

-- 4.	Vi???t script insert d??? li???u m???u cho t???t c??? c??c b???ng.
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1000', 'KIM ?????NG', 1, 'B???C NINH', 'MO TA NGAN GON VE NXB');
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1001', '?????C PH????NG', 0, 'V??NH PH??C', 'MO TA NGAN GON VE NXB');
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1002', 'MINH THU???', 1, 'H?? N???I', 'MO TA NGAN GON VE NXB');
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1003', 'T???T HO??', 0, 'THANH HO??', 'MO TA NGAN GON VE NXB');
INSERT INTO VIETHIEN_NHA_XUAT_BAN(ID, MA, TEN, TRANG_THAI, DIA_CHI, MO_TA)
VALUES (VIETHIEN_NHA_XUAT_BAN_SEQ.nextval, 'NXB1004', 'H???NG S??N', 1, 'H???I D????NG', 'MO TA NGAN GON VE NXB');


INSERT INTO VIETHIEN_TAC_GIA(ID, MA, TEN, SDT, DIA_CHI, MO_TA)
VALUES (VIETHIEN_TAC_GIA_SEQ.nextval, 'TG1000', 'NG?? T???T T???', '0911119291', 'H?? NAM', 'M?? T??? NG???N G???N');
INSERT INTO VIETHIEN_TAC_GIA(ID, MA, TEN, SDT, DIA_CHI, MO_TA)
VALUES (VIETHIEN_TAC_GIA_SEQ.nextval, 'TG1001', 'XU??N QU???NH', '0911119293', 'H?? N???I', 'M?? T??? NG???N G???N');

INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1000', 'S??NG', 2, 3, 'V??N H???C', TO_DATE('2021/09/02', 'yyyy/mm/dd'), 'M?? T???', 12,
        3,
        15);
INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1001', 'T???T ????N', 3, 2, 'V??N H???C', TO_DATE('2001/09/02', 'yyyy/mm/dd'), 'M?? T???',
        19,
        6,
        25);
INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1002', 'VI???T B???C', 3, 2, 'V??N H???C', TO_DATE('2001/09/02', 'yyyy/mm/dd'), 'M?? T???',
        19,
        6,
        25);
INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1003', 'T??Y TI???N', 3, 2, 'V??N H???C', TO_DATE('2001/09/02', 'yyyy/mm/dd'), 'M?? T???',
        19,
        6,
        25);
INSERT INTO VIETHIEN_SACH(ID, MA, TEN, ID_NXB, ID_TACGIA, CHU_DE, NAM_SAN_XUAT, MOTA, SO_LUONG_CON_LAI,
                          SO_LUONG_DANG_MUON, TONG_SO_SACH)
VALUES (VIETHIEN_SACH_SEQ.nextval, 'S1004', '??O??N THUY???N B???T CUA', 3, 2, 'V??N H???C', TO_DATE('2002/09/02', 'yyyy/mm/dd'),
        'M?? T???', 19,
        6,
        25);

INSERT INTO VIETHIEN_BAN_DOC(ID, MA, TEN, SDT, DIA_CHI, NGAY_SINH, NGAY_TAO_TAI_KHOAN, HANG)
VALUES (VIETHIEN_BAN_DOC_SEQ.nextval, 'BD1000', 'NGUY???N V??N A', '0999199999', 'H?? NAM',
        TO_DATE('2002/08/11', 'yyyy/mm/dd'),
        TO_DATE('2022/08/02', 'yyyy/mm/dd'), 1);

INSERT INTO VIETHIEN_BAN_DOC(ID, MA, TEN, SDT, DIA_CHI, NGAY_SINH, NGAY_TAO_TAI_KHOAN, HANG)
VALUES (VIETHIEN_BAN_DOC_SEQ.nextval, 'BD1001', 'NGUY???N V??N B', '0999199129', 'H?? GIANG',
        TO_DATE('2002/07/11', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 2);

INSERT INTO VIETHIEN_BAN_DOC(ID, MA, TEN, SDT, DIA_CHI, NGAY_SINH, NGAY_TAO_TAI_KHOAN, HANG)
VALUES (VIETHIEN_BAN_DOC_SEQ.nextval, 'BD1002', 'NGUY???N V??N C', '0999199129', 'H?? GIANG',
        TO_DATE('2009/07/11', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 2);

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 2, 4, 3, TO_DATE('2022/08/12', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 1, 'KH??NG');

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 3, 5, 6, TO_DATE('2022/08/02', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 1, 'KH??NG');

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 3, 4, 6, TO_DATE('2022/08/02', 'yyyy/mm/dd'),
        TO_DATE('2022/08/12', 'yyyy/mm/dd'), 1, 'KH??NG');

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 3, 4, 6, TO_DATE('2022/07/02', 'yyyy/mm/dd'),
        TO_DATE('2022/7/12', 'yyyy/mm/dd'), 1, 'KH??NG');

INSERT INTO VIETHIEN_MUON_SACH(ID, ID_BAN_DOC, ID_SACH, SO_LUONG, NGAY_GIO_MUON, NGAY_GIO_TRA, TRANG_THAI, GHI_CHU)
VALUES (VIETHIEN_MUON_SACH_SEQ.nextval, 4, 4, 6, TO_DATE('2022/07/02', 'yyyy/mm/dd'),
        TO_DATE('2022/7/12', 'yyyy/mm/dd'), 1, 'KH??NG');


SELECT *
FROM VIETHIEN_MUON_SACH;

-- 5.	Hi???n th??? d??ch s??ch t??c gi??? v?? t???ng s??? l?????ng s??ch c???a t??c gi??? g???m c??c tr?????ng th??ng tin:
-- M?? T??c Gi???, T??n T??c Gi???, Ch??? ?????, S??? L?????ng S??ch
-- S???p x???p theo s??? l?????ng s??ch gi???m d???n.
SELECT TG.MA MA_TG, TG.TEN TEN_TG, S.TEN TEN_SACH, S.CHU_DE CHU_DE, S.TONG_SO_SACH TONG_SO_SACH
FROM VIETHIEN_TAC_GIA TG
         JOIN VIETHIEN_SACH S on TG.ID = S.ID_TACGIA
ORDER BY S.TONG_SO_SACH DESC;
-- 6.	Hi???n th??? 10 quy???n s??ch c?? s??? l?????ng ???????c m?????n nhi???u nh???t g???m c??c tr?????ng th??ng tin:
-- M?? S??ch, T??n S??ch, T??n Nh?? Xu???t B???n, T??n T??c Gi???, Ch??? ?????, N??m Xu???t B???n, S??? L?????ng C??n L???i, S??? L?????ng ???? M?????n, T???ng S???
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

-- 7.	Hi???n th???  th??ng tin b???n ?????c v?? s??ch ???????c m?????n t??? ng??y ?????u th??ng hi???n t???i ?????n th???i ??i???m hi???n t???i.
-- M?? B???n ?????c, T??n B???n ?????c, M?? S??ch, T??n S??ch, Ng??y Gi??? M?????n, S??? l?????ng
-- S???p x???p theo ng??y gi??? m?????n gi???m d???n, T??n b???n ?????c t??ng d???n.
SELECT BD.MA MA_BAN_DOC, BD.TEN TEN_BAN_DOC, S.MA MA_SACH, S.TEN TEN_SACH, NGAY_GIO_MUON, SO_LUONG_DANG_MUON
FROM VIETHIEN_MUON_SACH MS
         JOIN VIETHIEN_BAN_DOC BD ON BD.ID = MS.ID_BAN_DOC
         JOIN VIETHIEN_SACH S ON MS.ID_SACH = S.ID
WHERE NGAY_GIO_MUON between to_char(trunc(sysdate, 'month')) and to_char(sysdate)
ORDER BY NGAY_GIO_MUON DESC, BD.TEN ASC;

-- 8.	Hi???n th??? 10 quy???n s??ch c?? s??? l?????ng ???????c m?????n nhi???u nh???t t??nh t??? ?????u n??m 2022
-- M?? S??ch, T??n S??ch, S??? L?????ng ???? ???????c M?????n.
SELECT S.MA MA_SACH, S.TEN TEN_SACH, SUM(MS.SO_LUONG)
FROM VIETHIEN_MUON_SACH MS
         JOIN VIETHIEN_SACH S on MS.ID_SACH = S.ID
WHERE TO_CHAR(NGAY_GIO_MUON, 'yyyy') >= 2022
  AND ROWNUM < 11
GROUP BY S.MA, S.TEN
ORDER BY SUM(SO_LUONG) DESC;

-- 9.	Hi???n th??? danh s??ch b???n ?????c v?? s??? l???n m?????n s??ch t??nh t??? ?????u n??m 2022 s???p x???p theo t??n b???n ?????c t??ng d???n:
-- M?? B???n ?????c, T??n B???n ?????c, S??? L???n M?????n
SELECT MA, TEN, COUNT(MA) SO_LAN_MUON
FROM VIETHIEN_MUON_SACH
         JOIN VIETHIEN_BAN_DOC ON VIETHIEN_MUON_SACH.ID_BAN_DOC = VIETHIEN_BAN_DOC.ID
WHERE TO_CHAR(NGAY_GIO_MUON, 'yyyy') >= 2022
GROUP BY MA, TEN
ORDER BY TEN ASC;

-- 10.	Hi???n th??? th??ng tin b???n ?????c g???m c??:
-- M?? B???n ?????c, T??n B???n ?????c, Tu???i (???????c t??nh d???a v??o tr?????ng ng??y sinh)
SELECT MA, TEN, TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY') AS TUOI_BAN_DOC
FROM VIETHIEN_BAN_DOC;

-- 11.	Th???ng k?? t???ng s??? b???n ?????c theo ????? tu???i
-- Tu???i, T???ng s??? B???n ?????c
SELECT TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY')        AS TUOI_BAN_DOC,
       COUNT(TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY')) AS SOLUONG
FROM VIETHIEN_BAN_DOC
group by TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY');

-- 12.	Th???ng k?? s??? l?????ng s??ch ???????c xu???t b???n theo Nh?? Xu???t B???n, Theo n??m xu???t b???n.
-- N??m Xu???t B???n, M?? Nh?? Xu???t B???n,T??n Nh?? Xu???t B???n, S??? L?????ng S??ch
-- S???p x???p theo N??m xu???t b???n gi???m d???n, T??n Nh?? xu???t b???n t??ng d???n.
SELECT TO_CHAR(NAM_SAN_XUAT, 'YYYY') AS NAMSB, SB.MA, SB.TEN, SUM(TONG_SO_SACH) AS TONG_SO_LUONG_SACH
FROM VIETHIEN_NHA_XUAT_BAN SB
         JOIN VIETHIEN_SACH VS on SB.ID = VS.ID_NXB
GROUP BY SB.TEN, SB.MA, NAM_SAN_XUAT
ORDER BY TO_CHAR(NAM_SAN_XUAT, 'YYYY') DESC, TEN ASC;

-- 13.	Hi???n th??? 5 quy???n s??ch ???????c c??c b???n ?????c c?? ????? tu???i t??? 18 ?????n 25 m?????n nhi???u nh???t t??nh t??? ?????u n??m 2022. (T??nh theo tr?????ng s??? l?????ng m?????n c???a s??ch)
-- M?? S??ch, T??n S??ch, S??? L?????ng ???????c M?????n
SELECT S.MA MA_SACH, S.TEN TEN_SACH, BD.TEN TEN_BAN_DOC, SUM(SO_LUONG_DANG_MUON) SO_LUONG_DANG_MUON
FROM VIETHIEN_MUON_SACH MS
         JOIN VIETHIEN_SACH S ON MS.ID_SACH = S.ID
         JOIN VIETHIEN_BAN_DOC BD on BD.ID = MS.ID_BAN_DOC
WHERE TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(NGAY_SINH, 'YYYY') BETWEEN 18 AND 25
  AND ROWNUM < 6
GROUP BY S.MA, S.TEN, BD.TEN;

-- 14.	Hi???n th??? to??n b??? b???n ?????c v?? s??ch m?? b???n ?????c ?????y m?????n, s??? c?? b???n ch??a m?????n v???n c???n hi???n th??? v?? t??n s??ch ????? l?? null.
-- M?? b???n ?????c, t??n ban ?????c, t??n s??ch
SELECT BD.MA MA_BAN_DOC, BD.TEN TEN_BAN_DOC, S.TEN TEN_SACH
FROM VIETHIEN_BAN_DOC BD
         LEFT JOIN VIETHIEN_MUON_SACH MS on BD.ID = MS.ID_BAN_DOC
         LEFT JOIN VIETHIEN_SACH S on MS.ID_SACH = S.ID