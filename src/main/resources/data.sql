create schema project_two;
set schema project_two;
create table project_two.lfg_user_creds (
userID serial primary key,
userLogin varchar unique not null,
userPass varchar not null
);
create sequence lfg_user_creds_user_id_seq start with 1 increment by 1;

create table project_two.lfg_user_profile(
columnID serial primary key,
userID integer,
firstName text ,
lastName text,
email varchar unique not null,
foreign key (userID) references lfg_user_creds(userid)
);
create sequence lfg_user_profile_columnid_seq start with 1 increment by 1;

create table project_two.lfg_games(
gameID serial primary key,
platformkey integer,
gameTitle varchar unique not null,
imgLink varchar
);
create sequence lfg_games_gameid_seq start with 1 increment by 1;

create table project_two.lfg_socials (
userID integer,
gameID integer ,
gamertag varchar not null,
primary key(userID, gameID),
foreign key (userID) references lfg_user_creds(userID),
foreign key (gameID) references lfg_games(gameId)
);

create table project_two.lfg_group_information(
groupID serial primary key,
gameID integer,
maxUsers integer not null check(maxUsers > 1),
currentUsers integer not null default 1 check(currentUsers > 0),
description text,
foreign key (gameID) references lfg_games(gameID)
);
create sequence lfg_group_information_groupid_seq start with 1 increment by 1;

create table project_two.lfg_group_sessions(
userID integer,
hostID integer,
groupID integer,
inSession boolean,
primary key(userID, hostID),
foreign key (userID) references lfg_user_creds(userID),
foreign key (hostID) references lfg_user_creds(userID),
foreign key (groupID) references lfg_group_information(groupID)

);

create table project_two.lfg_tags(
tagID serial primary key,
value varchar
);
create sequence lfg_tags_tagid_seq start with 1 increment by 1;

create table project_two.tag_bridge_table(
groupId integer,
tagid integer,
foreign key (tagId) references lfg_tags(tagid),
foreign key (groupID) references lfg_group_information(groupID),
primary key(groupId, tagId)
);

--insert into lfg_user_creds
--values
--	(default, 'user1', 'pass1'),
--	(default, 'user2', 'pass2'),
--	(default, 'user3', 'pass3'),
--	(default, 'user4', 'pass4'),
--	(default, 'user5', 'pass5');
----
--insert into lfg_user_profile
--	values
--	(default, 1, 'John1', 'Doe1', 'email11@email.com'),
--	(default, 2, 'John2', 'Doe2', 'email12@email.com'),
--	(default, 3, 'John3', 'Doe3', 'email13@email.com'),
--	(default, 4, 'John4', 'Doe4', 'email14@email.com'),
--	(default, 5, 'John5', 'Doe5', 'email15@email.com');
--
insert into lfg_tags
values
	(default, 'ranked'),
	(default, 'casual'),
	(default, 'grind'),
	(default, 'short');
--
insert into project_two.lfg_games
values
	(default, 1172470 ,'Apex Legends', 'https://media.contentapi.ea.com/content/dam/apex-legends/images/2019/01/apex-featured-image-16x9.jpg.adapt.crop16x9.1023w.jpg');
insert into project_two.lfg_games
    values
	(default, 570, 'Dota 2', 'https://cdn.akamai.steamstatic.com/steam/apps/570/capsule_616x353.jpg?t=1639608963');

insert into project_two.lfg_games
    values
    (default, 440, 'Team Fortress 2', 'https://cdn.akamai.steamstatic.com/steam/apps/440/header.jpg?t=1592263852');
insert into project_two.lfg_games
    values
	(default, 730, 'Counter Strike: Global Offensive', 'https://cdn.akamai.steamstatic.com/steam/apps/730/capsule_616x353.jpg?t=1635269541');
--
--insert into lfg_group_information
--values
--	(default, 1, 4, 1, 'This is a testing group for Apex Legends'),
--	(default, 2, 3, 2, 'This is a testing group for League of Legends');
--
--insert into lfg_group_sessions
--values
--	(1, 1, 1, TRUE),
--	(2, 1, 1, FALSE),
--	(3, 3, 2, FALSE),
--	(4, 3, 2, TRUE),
--	(5, 1, 1, FALSE);

--insert into lfg_socials
--values
--	(1, 1, 'apexGamer1'),
--	(1, 2, 'lolGamer1'),
--	(2, 1, 'apexGamer2'),
--	(3, 2, 'lolGamer3'),
--	(4, 1, 'apexGamer4'),
--	(4, 2, 'lolGamer4'),
--	(5, 2, 'lolGamer5');
--
--insert into tag_bridge_table
--values
--	(1, 3),
--	(2, 1),
--	(2, 4);
--
--
