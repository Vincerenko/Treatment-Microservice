create sequence hibernate_sequence start 1 increment 1;

create sequence schedule_sequence start 1 increment 1;

create sequence treatment_sequence start 1 increment 1;

create table appointment
(
    id           int8         not null,
    count        int4,
    meet_date    timestamp,
    name         varchar(255) not null,
    price        float8       not null,
    treatment_id int8,
    primary key (id)
);
create table medicine
(
    id           int8         not null,
    number       int4         not null,
    name         varchar(255) not null,
    price        float8       not null,
    treatment_id int8,
    primary key (id)
);
create table reception_medicine_local_date_times
(
    reception_medicine_take_medicine_id int8 not null,
    local_date_times                    timestamp
);
create table reception_medicines
(
    take_medicine_id int8 not null,
    description      varchar(255),
    frequency_enums  varchar(255),
    name_medicine    varchar(255),
    timesaday_enums  varchar(255),
    schedule_id      int8,
    primary key (take_medicine_id)
);
create table schedules
(
    schedule_id int8 not null,
    doctor_id   int8,
    patient_id  int8,
    primary key (schedule_id)
);
create table treatments
(
    treatment_id   int8         not null,
    appointment_id int8         not null,
    currency       varchar(255) not null,
    description    varchar(255) not null,
    doctor_id      int8         not null,
    duration_days  int8         not null,
    end_date       date         not null,
    patient_id     int8         not null,
    price          float8       not null,
    room           varchar(255) not null,
    start_date     date         not null,
    status         varchar(255) not null,
    title          varchar(255) not null,
    primary key (treatment_id)
);
alter table appointment
    add constraint appointment_fk foreign key (treatment_id) references treatments;
alter table medicine
    add constraint medicine_fk foreign key (treatment_id) references treatments;
alter table reception_medicine_local_date_times
    add constraint reception_medicine_local_date_times_fk foreign key (reception_medicine_take_medicine_id) references reception_medicines;
alter table reception_medicines
    add constraint reception_medicines_fk foreign key (schedule_id) references schedules;