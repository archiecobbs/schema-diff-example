
create table Patient (
    id bigint not null auto_increment,
    createTime datetime not null,
    updateTime datetime not null,
    ZIP varchar(16),
    birthDate date,
    sex varchar(1),
    firstName varchar(255),
    lastName varchar(255),
    middleName varchar(255),
    activationStatus enum ('ACTIVATED','BLOCKED','PENDING') not null,
    number varchar(64),
    primary key (id)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin;

create table PatientAnnotation (
    patient bigint not null,
    value mediumtext not null,
    name varchar(180) not null,
    primary key (patient, name)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin;

create table Pid (
    patient bigint not null,
    pid varchar(32) not null,
    type enum ('CERNER','EPIC','MPI','TEMP') not null,
    primary key (patient, type)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin;

create index idx_Patient_lastName
   on Patient (lastName);

create index idx_Patient_number
   on Patient (number);

create index idx_PatientAnnotation_name
   on PatientAnnotation (name);

alter table Pid
   add constraint UKov21uy8ka3a3p94g40s1ljl3l unique (pid, type);

alter table PatientAnnotation
   add constraint FK287AFF4A0F6ED11
   foreign key (patient)
   references Patient (id);

alter table Pid
   add constraint FK5wteflfdggheps2n5qx7rj05l
   foreign key (patient)
   references Patient (id);
