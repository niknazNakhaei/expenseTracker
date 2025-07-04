CREATE TABLE TBL_EXPENSE
(
    ID           NUMBER(20) not null,
    CATEGORY_ID  NUMBER(20),
    AMOUNT       NUMBER(12,2),
    DESCRIPTION  NVARCHAR2(1000),
    EXPENSE_TIME TIMESTAMP,
    UPDATED_TIME TIMESTAMP,
    CREATED_TIME TIMESTAMP
)
    /
CREATE SEQUENCE SEQ_EXPENSE INCREMENT BY 10 cache 1000