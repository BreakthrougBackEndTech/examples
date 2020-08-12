-- 数据库实例inst01
CREATE TABLE tb_account_one (
  acct_id varchar(16),
  amount double(10, 2),
  frozen double(10, 2),
  PRIMARY KEY (acct_id)
) ENGINE=InnoDB;

insert into tb_account_one (acct_id, amount, frozen) values('1001', 100, 0.00);

-- 数据库实例inst02
CREATE TABLE tb_account_two (
  acct_id varchar(16),
  amount double(10, 2),
  frozen double(10, 2),
  PRIMARY KEY (acct_id)
) ENGINE=InnoDB;

insert into tb_account_two (acct_id, amount, frozen) values('2001', 10000.00, 0.00);