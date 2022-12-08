create table administrador
(
    id       bigint auto_increment
        primary key,
    password varchar(255) null,
    usuario  varchar(255) null
);

create table cuenta
(
    tipo_cuenta varchar(31)  not null,
    CuentaId    bigint auto_increment
        primary key,
    password    varchar(255) null,
    usuario     varchar(255) null
);

create table factoremision
(
    id              bigint auto_increment
        primary key,
    unidadDivisible varchar(255) null,
    valor           double       null
);

create table lineatransporte
(
    ID_LINEA_TRANSPORTE bigint auto_increment
        primary key,
    nombre              varchar(255) null,
    tipoTransporte      varchar(255) null
);

create table miembro
(
    ID_MIEMBRO      bigint auto_increment
        primary key,
    APELLIDO        varchar(255) null,
    NOMBRE          varchar(255) null,
    NRO_DOCUMENTO   int          null,
    TIPO_DOCUMENTO  int          null,
    cuenta_CuentaId bigint       null,
    constraint FK_64hyci0edq9syaxkd06pbsapr
        foreign key (cuenta_CuentaId) references cuenta (CuentaId)
);

create table puntoubicacion
(
    ID_PUNTO_UBICACION bigint auto_increment
        primary key,
    ALTURA             int          null,
    CALLE              varchar(255) null,
    ID_LOCALIDAD       int          null
);

create table parada
(
    ID_PARADA          bigint auto_increment
        primary key,
    KM_ACTUAL          int    null,
    ID_PUNTO_UBICACION bigint null,
    constraint FK_c2aqbu1e3u8ivye0on9sv89wd
        foreign key (ID_PUNTO_UBICACION) references puntoubicacion (ID_PUNTO_UBICACION)
);

create table paradas_por_linea
(
    ID_LINEA  bigint not null,
    ID_PARADA bigint not null,
    constraint FK_h9qx3myavqfulof6xlwpk73j1
        foreign key (ID_PARADA) references parada (ID_PARADA),
    constraint FK_px8p35hmygk690e9gdwoyufjp
        foreign key (ID_LINEA) references lineatransporte (ID_LINEA_TRANSPORTE)
);

create table sectorterritorial
(
    id                    bigint auto_increment
        primary key,
    nombre                varchar(255) null,
    tipoSectorTerritorial int          null
);

create table agenteterritorial
(
    id              bigint auto_increment
        primary key,
    nombre          varchar(255) null,
    cuenta_CuentaId bigint       null,
    sector_id       bigint       null,
    constraint FK_oppklftpgq8klbtq4mivh56nf
        foreign key (sector_id) references sectorterritorial (id),
    constraint FK_pqstq81uglu5hcfv6lq0uloog
        foreign key (cuenta_CuentaId) references cuenta (CuentaId)
);

create table organizacion
(
    id                    bigint auto_increment
        primary key,
    clasificacion         varchar(255) null,
    razonSocial           varchar(255) null,
    tipo                  varchar(255) null,
    ubicacionGeografica   varchar(255) null,
    cuenta_CuentaId       bigint       null,
    sector_territorial_id bigint       null,
    constraint FK_dqkgsi2o9r398bap1d5dhp0r9
        foreign key (sector_territorial_id) references sectorterritorial (id),
    constraint FK_mmjcbbj50u50yeah01o2gb8a
        foreign key (cuenta_CuentaId) references cuenta (CuentaId)
);

create table contacto
(
    id              bigint auto_increment
        primary key,
    mail            varchar(255) null,
    nombreContacto  varchar(255) null,
    nroCelular      varchar(255) null,
    organizacion_id bigint       null,
    constraint FK_7r117ujoxonqhyj6to4npqss
        foreign key (organizacion_id) references organizacion (id)
);

create table sector
(
    id              bigint auto_increment
        primary key,
    nombre          varchar(255) null,
    organizacion_id bigint       null,
    constraint FK_sb9xwaiuxouphx7ju2ej3fnxq
        foreign key (organizacion_id) references organizacion (id)
);

create table sector_miembro
(
    Sector_id           bigint not null,
    miembros_ID_MIEMBRO bigint not null,
    constraint FK_g8c5fufonula6j4b2wdluye7g
        foreign key (miembros_ID_MIEMBRO) references miembro (ID_MIEMBRO),
    constraint FK_p45adye5mu1gv3lqc3c0ftrje
        foreign key (Sector_id) references sector (id)
);

create table solicitud
(
    ID_SOLICITUD                  bigint auto_increment
        primary key,
    procesada                     bit    not null,
    miembroSolicitante_ID_MIEMBRO bigint null,
    sectorSolicitado_id           bigint null,
    constraint FK_7vsphb980mdd72im9dv0howso
        foreign key (sectorSolicitado_id) references sector (id),
    constraint FK_caekya36gygjb64bsj1exuos7
        foreign key (miembroSolicitante_ID_MIEMBRO) references miembro (ID_MIEMBRO)
);

create table tipoconsumo
(
    id               bigint auto_increment
        primary key,
    actividad        int          null,
    alcance          int          null,
    nombre           varchar(255) null,
    unidad           varchar(255) null,
    factorEmision_id bigint       null,
    constraint FK_1ql2ttxuvwuikxtpsm8iv0lin
        foreign key (factorEmision_id) references factoremision (id)
);

create table combustible
(
    ID_COMBUSTIBLE bigint auto_increment
        primary key,
    tipoConsumo_id bigint null,
    constraint FK_p5f178c1gyeyy4468e506hqs4
        foreign key (tipoConsumo_id) references tipoconsumo (id)
);

create table medicion
(
    PERIORICIDAD    varchar(31) not null,
    id              bigint auto_increment
        primary key,
    fecha           date        null,
    valor           double      not null,
    organizacion_id bigint      null,
    tipoConsumo_id  bigint      null,
    constraint FK_6w9obfgx1a0byjwgrfkxvu4sr
        foreign key (organizacion_id) references organizacion (id),
    constraint FK_ovicthytyl7jjxmuf4sjx8q79
        foreign key (tipoConsumo_id) references tipoconsumo (id)
);

create table transporte
(
    TRANSPORTE_UTILIZADO varchar(31)  not null,
    ID_TRANSPORTE        bigint auto_increment
        primary key,
    CONSUMO_POR_KM       double       null,
    TIPO_TRANSPORTE      varchar(255) null,
    nombre               varchar(255) null,
    ID_COMBUSTIBLE       bigint       null,
    ID_LINEA             bigint       null,
    constraint FK_pfyemgmuio79vhk8tk032eviw
        foreign key (ID_LINEA) references lineatransporte (ID_LINEA_TRANSPORTE),
    constraint FK_qiau71as7sknudo2vvo63g8x7
        foreign key (ID_COMBUSTIBLE) references combustible (ID_COMBUSTIBLE)
);

create table tramo
(
    ID_TRAMO         bigint auto_increment
        primary key,
    ID_PUNTO_DESTINO bigint null,
    ID_PUNTO_ORIGEN  bigint null,
    ID_TRANSPORTE    bigint null,
    constraint FK_3autubdwyegsi5xuqf6bojhm1
        foreign key (ID_PUNTO_DESTINO) references puntoubicacion (ID_PUNTO_UBICACION),
    constraint FK_4kcr2wjnjsejjqix0rfvctyb7
        foreign key (ID_PUNTO_ORIGEN) references puntoubicacion (ID_PUNTO_UBICACION),
    constraint FK_766wun0dg57t888gopwqo9u4j
        foreign key (ID_TRANSPORTE) references transporte (ID_TRANSPORTE)
);

create table trayecto
(
    ID_TRAYECTO bigint auto_increment
        primary key
);

create table miembro_por_trayecto
(
    ID_MIEMBRO  bigint not null,
    ID_TRAYECTO bigint not null,
    constraint FK_488wqypv4chfui51ap2mjkj55
        foreign key (ID_MIEMBRO) references miembro (ID_MIEMBRO),
    constraint FK_thenyrhqwq33nxj4c32pe5twd
        foreign key (ID_TRAYECTO) references trayecto (ID_TRAYECTO)
);

create table tramo_por_trayecto
(
    ID_TRAYECTO bigint not null,
    ID_TRAMO    bigint not null,
    primary key (ID_TRAYECTO, ID_TRAMO),
    constraint FK_1et2dbuplx0yuqfem5tnwbtvl
        foreign key (ID_TRAYECTO) references trayecto (ID_TRAYECTO),
    constraint FK_dbw42gfn61t7q3nwj7p7dfbbc
        foreign key (ID_TRAMO) references tramo (ID_TRAMO)
);


