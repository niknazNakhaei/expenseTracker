create table TBL_USER
(
    ID        NUMBER(20) not null,
    NAME      NVARCHAR2(100),
    USER_NAME NVARCHAR2(100),
    PASSWORD  NVARCHAR2(255),
    EMAIL     NVARCHAR2(1000),
    ACTIVE    NUMBER(1)
)
/
create sequence SEQ_USER increment by 10 cache 1000