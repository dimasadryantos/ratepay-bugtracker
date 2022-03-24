create table if not exists issue
(
	issue_id bigint not null
		constraint issue_pkey
			primary key,
	created_date timestamp,
	updated_date timestamp,
	description varchar(255),
	issue_type varchar(255),
	summary varchar(255)
);