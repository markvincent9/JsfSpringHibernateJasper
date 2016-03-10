CREATE TABLE "public".book (
	id                   serial  NOT NULL,
	book_title           varchar(100)  NOT NULL,
	book_author          varchar(100)  NOT NULL,
	stock                integer  NOT NULL,
	img_url              varchar(255)  ,
	price                numeric(0,2)  NOT NULL,
	description          text  ,
	CONSTRAINT pk_book PRIMARY KEY ( id )
 );

CREATE TABLE "public"."user" (
	id                   serial  NOT NULL,
	username             varchar(100)  NOT NULL,
	_password            varchar(100)  NOT NULL,
	email                varchar(100)  NOT NULL,
	CONSTRAINT pk_user PRIMARY KEY ( id )
 );

CREATE TABLE "public"."transaction" (
	user_id              integer  ,
	id                   serial  NOT NULL,
	CONSTRAINT pk_transaction PRIMARY KEY ( id )
 );

CREATE INDEX idx_transaction ON "public"."transaction" ( user_id );

CREATE TABLE "public".transaction_items (
	book_id              integer  NOT NULL,
	item_count           integer  NOT NULL,
	id                   serial  NOT NULL,
	transaction_id       integer  NOT NULL,
	CONSTRAINT pk_transaction_items_0 PRIMARY KEY ( id )
 );

CREATE INDEX idx_transaction_items ON "public".transaction_items ( book_id );

CREATE INDEX idx_transaction_items_0 ON "public".transaction_items ( transaction_id );

ALTER TABLE "public"."transaction" ADD CONSTRAINT fk_transaction FOREIGN KEY ( user_id ) REFERENCES "public"."user"( id );

ALTER TABLE "public".transaction_items ADD CONSTRAINT fk_transaction_items FOREIGN KEY ( book_id ) REFERENCES "public".book( id );

ALTER TABLE "public".transaction_items ADD CONSTRAINT fk_transaction_items_0 FOREIGN KEY ( transaction_id ) REFERENCES "public"."transaction"( id );

