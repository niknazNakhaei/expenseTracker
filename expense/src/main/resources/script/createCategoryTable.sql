CREATE TABLE TBL_CATEGORY
(
    ID           NUMBER(20) not null,
    NAME         NVARCHAR2(100),
    TYPE         NVARCHAR2(20),
    USER_ID      NUMBER(20),
    DESCRIPTION  NVARCHAR2(1000),
    CREATED_TIME TIMESTAMP
)
    /
create sequence SEQ_CATEGORY increment by 10 cache 1000