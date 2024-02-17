alter table appointments add column reason_cancellation varchar(255);
alter table appointments add column cancelled tinyint(1) default 0 not null;