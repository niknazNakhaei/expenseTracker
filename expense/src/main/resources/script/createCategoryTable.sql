CREATE TABLE TBL_CATEGORY
(
    ID          NUMBER(20) not null,
    NAME        NVARCHAR2(100),
    PARENT_ID   NUMBER(20),
    USER_ID     NUMBER(20),
    DESCRIPTION NUMBER(20),
    CREATED_TIME  TIMESTAMP
)
/
create sequence SEQ_CATEGORY increment by 10 cache 1000