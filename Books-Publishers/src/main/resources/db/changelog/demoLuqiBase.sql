-- liquibase formatted sql
-- changeset liquibase:1 runOnChange:true endDelimiter:#
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) FROM information_schema.tables where table_schema = DATABASE() AND table_name = "demo";

create table demo (
    id bigint not null auto_increment,
    2fa_code varchar(10) not null,
    created_time datetime,
    is_verified bit default 0,
    user_id bigint,
    primary key (id)
);#