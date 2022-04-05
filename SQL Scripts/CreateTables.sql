create table lfg_user_creds (
userID serial primary key,
userLogin varchar unique not null,
userPass varchar not null
);

create table lfg_user_profile(
columnID serial primary key,
userID integer unique not null references lfg_user_creds(userid),
firstName text ,
lastName text,
email varchar unique not null
);

create table lfg_games(
gameID serial primary key,
platformkey integer,
gameTitle varchar unique not null,
imgLink varchar
);

create table lfg_socials (
userID integer references lfg_user_creds(userID),
gameID integer references lfg_games(gameId),
gamertag varchar not null,
primary key(userID, gameID)
);

create table lfg_group_information(
groupID serial primary key,
gameID integer references lfg_games(gameID),
maxUsers integer not null check(maxUsers > 1),
currentUsers integer not null default 1 check(currentUsers > 0),
description text
);

create table lfg_group_sessions(
userID integer references lfg_user_creds(userID),
hostID integer references lfg_user_creds(userID),
groupID integer references lfg_group_information(groupID),
inSession boolean,
primary key(userID, hostID)
);

create table lfg_tags(
tagID serial primary key,
value varchar
);

create table tag_bridge_table(
groupID integer references lfg_group_information(groupID),
tagID integer references lfg_tags(tagID),
primary key (groupID, tagID)
);
