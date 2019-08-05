#Simple book store tables
 
DROP TABLE ACCOUNT; 
DROP TABLE BOOK_STOCK;
 DROP TABLE BOOK;


CREATE TABLE BOOK (
ISBN VARCHAR(20)   primary key,
    BOOK_NAME    VARCHAR(20)   NOT NULL,
PRICE  double
(8,1));



CREATE TABLE BOOK_STOCK (
    ISBN     VARCHAR(50)    primary key,
  STOCK    INT  NOT NULL
);



CREATE TABLE ACCOUNT (
email    VARCHAR(20)   primary key,
    BALANCE     double(8,1)  NOT NULL   );


# A trigger before insert for checking book price >= 0
drop trigger chk_stats_ins;
 delimiter $$
    create trigger chk_stats_ins before insert on test_jpa.book 
      for each row 
       begin  
      if  NEW.price < 100 then
       SIGNAL SQLSTATE '45000'   
       SET MESSAGE_TEXT = 'Book Price must be >= 100';
       end if; 
    end; 
    $$
delimiter ;
insert into book values('abc','a1',20);

# A trigger before update for checking book price >= 0
drop trigger chk_stats_update;
 delimiter $$
    create trigger chk_stats_update before update on test_jpa.book 
      for each row 
       begin  
      if  new.price < 100 then
       SIGNAL SQLSTATE '45000'   
       SET MESSAGE_TEXT = 'Book Price must be >= 100';
       end if; 
    end; 
    $$
delimiter ;
update book set price=10 where isbn='1';

# A trigger before insert for checking stock > 0
drop trigger chk_stock_ins;
 delimiter $$
    create trigger chk_stock_ins before insert on test_jpa.book_stock 
      for each row 
       begin  
      if  NEW.stock <= 0 then
       SIGNAL SQLSTATE '45000'   
       SET MESSAGE_TEXT = 'Book Stock must be >0';
       end if; 
    end; 
    $$
delimiter ;
insert into book_stock values('1',50);
# A trigger before update for checking stock > 0
drop trigger chk_stock_update;
 delimiter $$
    create trigger chk_stock_update before update on test_jpa.book_stock 
      for each row 
       begin  
      if  NEW.stock <= 0 then
       SIGNAL SQLSTATE '45000'   
       SET MESSAGE_TEXT = 'Book Stock must be >0';
       end if; 
    end; 
    $$
delimiter ;
update book_stock set stock=-1; // error

# A trigger before insert for checking bal > min bal (5000)
drop trigger chk_bal_ins;
 delimiter $$
    create trigger chk_bal_ins before insert on test_jpa.account 
      for each row 
       begin  
      if  new.balance <= 5000 then
       SIGNAL SQLSTATE '45000'   
       SET MESSAGE_TEXT = 'Account balance must be > 5000';
       end if; 
    end; 
    $$
delimiter ;


# A trigger before update for checking bal > min bal (5000)
drop trigger chk_bal_update;
 delimiter $$
    create trigger chk_bal_update before update on test_jpa.account 
      for each row 
       begin  
      if  new.balance <= 5000 then
       SIGNAL SQLSTATE '45000'   
       SET MESSAGE_TEXT = 'Account balance must be > 5000';
       end if; 
    end; 
    $$
delimiter ;



INSERT INTO BOOK VALUES( '1', 'Book1', 500);

INSERT INTO BOOK_STOCK VALUES('1', 20) ;

INSERT INTO ACCOUNT VALUES('user1',5500);
