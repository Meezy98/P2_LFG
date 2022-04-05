insert into lfg_user_creds
values
	(default, 'user1', 'pass1'),
	(default, 'user2', 'pass2'),
	(default, 'user3', 'pass3'),
	(default, 'user4', 'pass4'),
	(default, 'user5', 'pass5');

insert into lfg_user_profile
	values
	(default, 1, 'John1', 'Doe1', 'email11@email.com'),
	(default, 2, 'John2', 'Doe2', 'email12@email.com'),
	(default, 3, 'John3', 'Doe3', 'email13@email.com'),
	(default, 4, 'John4', 'Doe4', 'email14@email.com'),
	(default, 5, 'John5', 'Doe5', 'email15@email.com');
	
insert into lfg_tags 
values
	(default, 'ranked'),
	(default, 'casual'),
	(default, 'grind'),
	(default, 'short');

insert into lfg_games 
values
	(default, 'Apex Legends', 'https://media.contentapi.ea.com/content/dam/apex-legends/images/2019/01/apex-featured-image-16x9.jpg.adapt.crop16x9.1023w.jpg'),
	(default, 'League of Legends', 'https://cdn1.epicgames.com/salesEvent/salesEvent/EGS_LeagueofLegends_RiotGames_S1_2560x1440-ee500721c06da3ec1e5535a88588c77f?h=270&resize=1&w=480');

insert into lfg_group_information 
values
	(default, 1, 4, 1, 'This is a testing group for Apex Legends'),
	(default, 2, 3, 2, 'This is a testing group for League of Legends');
	
insert into lfg_group_sessions 
values
	(1, 1, 1, TRUE),
	(2, 1, 1, FALSE),
	(3, 3, 2, FALSE),
	(4, 3, 2, TRUE),
	(5, 1, 1, FALSE);
	
insert into lfg_socials 
values
	(1, 1, 'apexGamer1'),
	(1, 2, 'lolGamer1'),
	(2, 1, 'apexGamer2'),
	(3, 2, 'lolGamer3'),
	(4, 1, 'apexGamer4'),
	(4, 2, 'lolGamer4'),
	(5, 2, 'lolGamer5');
	
insert into tag_bridge_table 
values
	(1, 3),
	(2, 1),
	(2, 4);

