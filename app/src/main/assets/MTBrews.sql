BEGIN TRANSACTION;
CREATE TABLE "android_metadata" ("locale" TEXT DEFAULT 'en_US');
INSERT INTO `android_metadata` VALUES ('en_US');
CREATE TABLE "Town" (
	`_id`	TEXT NOT NULL,
	`state`	TEXT,
	`zipCode`	TEXT,
	PRIMARY KEY(_id)
);
INSERT INTO `Town` VALUES ('Billings','59101','Montana');
INSERT INTO `Town` VALUES ('Bozeman','59715','Montana');
INSERT INTO `Town` VALUES ('Helena','59601','Montana');
INSERT INTO `Town` VALUES ('Missoula','59801','Montana');
INSERT INTO `Town` VALUES ('Wibaux','59353','Montana');
CREATE TABLE "State" (
	`_id`	TEXT NOT NULL,
	PRIMARY KEY(_id)
);
INSERT INTO `State` VALUES ('Alabama');
INSERT INTO `State` VALUES ('Alaska');
INSERT INTO `State` VALUES ('Arizona');
INSERT INTO `State` VALUES ('Arkansas');
INSERT INTO `State` VALUES ('California');
INSERT INTO `State` VALUES ('Colorado');
INSERT INTO `State` VALUES ('Connecticut');
INSERT INTO `State` VALUES ('Delaware');
INSERT INTO `State` VALUES ('Florida');
INSERT INTO `State` VALUES ('Georgia');
INSERT INTO `State` VALUES ('Hawaii');
INSERT INTO `State` VALUES ('Idaho');
INSERT INTO `State` VALUES ('Illinois');
INSERT INTO `State` VALUES ('Indiana');
INSERT INTO `State` VALUES ('Iowa');
INSERT INTO `State` VALUES ('Kansas');
INSERT INTO `State` VALUES ('Kentucky');
INSERT INTO `State` VALUES ('Louisiana');
INSERT INTO `State` VALUES ('Maine');
INSERT INTO `State` VALUES ('Maryland');
INSERT INTO `State` VALUES ('Massachusetts');
INSERT INTO `State` VALUES ('Michigan');
INSERT INTO `State` VALUES ('Minnesota');
INSERT INTO `State` VALUES ('Mississippi');
INSERT INTO `State` VALUES ('Missouri');
INSERT INTO `State` VALUES ('Montana');
INSERT INTO `State` VALUES ('Nebraska');
INSERT INTO `State` VALUES ('Nevada');
INSERT INTO `State` VALUES ('New Hampshire');
INSERT INTO `State` VALUES ('New Jersey');
INSERT INTO `State` VALUES ('New Mexico');
INSERT INTO `State` VALUES ('New York');
INSERT INTO `State` VALUES ('North Carolina');
INSERT INTO `State` VALUES ('North Dakota');
INSERT INTO `State` VALUES ('Ohio');
INSERT INTO `State` VALUES ('Oklahoma');
INSERT INTO `State` VALUES ('Oregon');
INSERT INTO `State` VALUES ('Pennsylvania');
INSERT INTO `State` VALUES ('Rhode Island');
INSERT INTO `State` VALUES ('South Carolina');
INSERT INTO `State` VALUES ('South Dakota');
INSERT INTO `State` VALUES ('Tennessee');
INSERT INTO `State` VALUES ('Texas');
INSERT INTO `State` VALUES ('Utah');
INSERT INTO `State` VALUES ('Vermont');
INSERT INTO `State` VALUES ('Virginia');
INSERT INTO `State` VALUES ('Washington');
INSERT INTO `State` VALUES ('Washington DC');
INSERT INTO `State` VALUES ('West Virginia');
INSERT INTO `State` VALUES ('Wisconsin');
INSERT INTO `State` VALUES ('Wyoming');
CREATE TABLE "Seasons" (
	`name_of_beer`	TEXT,
	`_id`	TEXT,
	`date_available`	TEXT,
	`end_date`	TEXT
);
INSERT INTO `Seasons` VALUES ('Dump Truck','Summer','NULL','NULL');
INSERT INTO `Seasons` VALUES ('Face Plant','Winter','NULL','NULL');
INSERT INTO `Seasons` VALUES ('Killarney','Spring','NULL','NULL');
INSERT INTO `Seasons` VALUES ('Oktoberfest','Fall','NULL','NULL');
INSERT INTO `Seasons` VALUES ('St. Wilbur Weizen','Summer','NULL','NULL');
CREATE TABLE "Regions" (
	`_id`	TEXT NOT NULL,
	`state`	TEXT,
	PRIMARY KEY(_id)
);
INSERT INTO `Regions` VALUES ('Custer','Montana');
INSERT INTO `Regions` VALUES ('Glacier','Montana');
INSERT INTO `Regions` VALUES ('Gold-West','Montana');
INSERT INTO `Regions` VALUES ('Missouri River','Montana');
INSERT INTO `Regions` VALUES ('Russell','Montana');
INSERT INTO `Regions` VALUES ('Yellowstone','Montana');
CREATE TABLE "Nutrition" (
	`name_of_beer`	TEXT,
	`serving_size`	TEXT,
	`calories`	TEXT,
	`fat`	TEXT,
	`carbs`	TEXT,
	`fullness_factor`	TEXT
);
INSERT INTO `Nutrition` VALUES ('Dancing Trout Ale (formerly Tr','12','168','15','NULL','NULL');
INSERT INTO `Nutrition` VALUES ('Dragons Breath Dark Heff Ale','12','168','15','NULL','NULL');
INSERT INTO `Nutrition` VALUES ('Dump Truck','12','180','16','NULL','NULL');
INSERT INTO `Nutrition` VALUES ('Oktoberfest','12','170','0','NULL','NULL');
INSERT INTO `Nutrition` VALUES ('St. Wilbur Weizen','12','168','15','NULL','NULL');
CREATE TABLE "Breweries" (
	`_id`	TEXT NOT NULL,
	`address`	TEXT,
	`name_of_town`	TEXT,
	`zipCode`	TEXT,
	`has_food`	TEXT,
	PRIMARY KEY(_id)
);
INSERT INTO `Breweries` VALUES ('406 Brewing Company','101 E Oak St #D','59715','Bozeman','NULL');
INSERT INTO `Breweries` VALUES ('Angry Hanks Microbrewry','20 N 30th St','59101','Billings','NULL');
INSERT INTO `Breweries` VALUES ('Bayern Brewing','1507 Montana St','59801','Missoula','NULL');
INSERT INTO `Breweries` VALUES ('Beaver Creek Brewery','104 Orgain Ave','59353','Wibaux','NULL');
INSERT INTO `Breweries` VALUES ('Blackfoot River Brewing','66 S Park Ave','59601','Helena','NULL');
CREATE TABLE "Beer" (
	`_id`	TEXT,
	`type_of_beer`	TEXT,
	`ABV`	TEXT,
	`brewery_name`	TEXT,
	`draft_bottle`	TEXT,
	`in_stock`	TEXT
);
INSERT INTO `Beer` VALUES ('2009 Brewer’s Reserve Barley W','Barley Wine','10.8','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Amber','American Dark L','5.3','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Bah Humbock','Dunkler Bock','6.8','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Belgian Tripel','Abbey Tripel','5','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Blonde Ale','Golden Ale/Blon','4','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Blue Collar Bitter','Premium Bitter/','5.8','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Bourbon Barrel Tartanic Strong','Scottish Ale','5','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Castor Mexican Amber','Amber Ale','4.6','Beaver Creek Brewery','NULL','NULL');
INSERT INTO `Beer` VALUES ('Choke Cherry Wheat','Fruit Beer','5','Beaver Creek Brewery','NULL','NULL');
INSERT INTO `Beer` VALUES ('Cream Ale','Cream Ale','4.6','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Dancing Trout Ale (formerly Tr','German Kristall','5.6','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Dark Wheat','Dunkelweizen','5.6','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Dirty Girl Dunkel Weizen','Dunkelweizen','5.5','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Dog Slobber Brown Ale','Brown Ale','5','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Doppelbock','Doppelbock','8.4','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Dortmunder Uber Gans','Dortmunder/Hell','5','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Double Black Diamond Extreme S','Dry Stout','6.9','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Dragons Breath Dark Heff Ale','Dunkelweizen','5.6','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Dump Truck','Heller Bock','6','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('English Pale','English Pale Al','5','406 Brewing Company','NULL','NULL');
INSERT INTO `Beer` VALUES ('English Pale Ale','English Pale Al','5','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('English Pale(406)','English Pale Al','5','406 Brewing Company','NULL','NULL');
INSERT INTO `Beer` VALUES ('Espresso Porter','Porter','5.3','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Face Plant','Doppel weizen','7.5','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Fried Dynamite Stout','Sweet Stout','5.8','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Frost Killer Scottish Ale','Scottish Ale','5.5','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Gold (Organic Pale Ale)','American Pale A','5.2','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Grizz Whizz','Wheat Ale','4','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Head Trauma India Pale Ale','India Pale Ale','6','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Imperial Stout 2008','Imperial Stout','5','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('JAMBER Ale','Amber Ale','5','406 Brewing Company','NULL','NULL');
INSERT INTO `Beer` VALUES ('Killarney','American Dark L','5.7','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Lewis and Clark Dark','Brown Ale','5.4','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Lightfoot Pilsner','Pilsener','5.2','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('MacKenzie River Driftboat Ambe','Ameber Ale','4.5','Blackfoot River Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Maibock','Maibock','6.5','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('May Fest','Amber Ale','5.5','406 Brewing Company','NULL','NULL');
INSERT INTO `Beer` VALUES ('Mood Swing','Brown Ale','5','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Münchner Helles','Premium Lager','4.5','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Octoberfest','Oktoberfest/Mär','5','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Oktoberfest','Oktoberfest/Mär','6','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Paddlefish Stout','Stout','6.3','Beaver Creek Brewery','NULL','NULL');
INSERT INTO `Beer` VALUES ('Pale Ale','American Pale A','6.6','Beaver Creek Brewery','NULL','NULL');
INSERT INTO `Beer` VALUES ('Pastey White Boy Prohibition S','Cream Ale','4','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Pilsener','Pilsener','5','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Porter','Porter','5','406 Brewing Company','NULL','NULL');
INSERT INTO `Beer` VALUES ('Redheaded IPA','India Pale Ale','7.3','Beaver Creek Brewery','NULL','NULL');
INSERT INTO `Beer` VALUES ('Rough Rider Wheat','Wheat Ale','4','Beaver Creek Brewery','NULL','NULL');
INSERT INTO `Beer` VALUES ('Rusty Beaver Wheat','Wheat Ale','7.6','Beaver Creek Brewery','NULL','NULL');
INSERT INTO `Beer` VALUES ('Schwarzbier','Schwarzbier','4.8','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Session Pale','American Pale A','4.6','406 Brewing Company','NULL','NULL');
INSERT INTO `Beer` VALUES ('Sippin Pumpkin Ale','Spice/Herb/Vege','5','Beaver Creek Brewery','NULL','NULL');
INSERT INTO `Beer` VALUES ('Smoked Porter','Porter','5','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('St. Wilbur Weizen','German Hefeweiz','5.6','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Street Fight Imperial Red Ale','Amber Ale','6.5','Angry Hanks Microbrewry','NULL','NULL');
INSERT INTO `Beer` VALUES ('Traditional Dark','Dunkel','5.3','Bayern Brewing','NULL','NULL');
INSERT INTO `Beer` VALUES ('Wibaux Gold','Golden Ale/Blon','5.5','Beaver Creek Brewery','NULL','NULL');
INSERT INTO `Beer` VALUES ('406 Brewing Company','101 E Oak St #D','59715','Bozeman','NULL',NULL);
INSERT INTO `Beer` VALUES ('Angry Hanks Microbrewry','20 N 30th St','59101','Billings','NULL',NULL);
INSERT INTO `Beer` VALUES ('Bayern Brewing','1507 Montana St','59801','Missoula','NULL',NULL);
INSERT INTO `Beer` VALUES ('Beaver Creek Brewery','104 Orgain Ave','59353','Wibaux','NULL',NULL);
INSERT INTO `Beer` VALUES ('Blackfoot River Brewing','66 S Park Ave','59601','Helena','NULL',NULL);
INSERT INTO `Beer` VALUES ('Dancing Trout Ale (formerly Tr','12','168','15','NULL','NULL');
INSERT INTO `Beer` VALUES ('Dragons Breath Dark Heff Ale','12','168','15','NULL','NULL');
INSERT INTO `Beer` VALUES ('Dump Truck','12','180','16','NULL','NULL');
INSERT INTO `Beer` VALUES ('Oktoberfest','12','170','0','NULL','NULL');
INSERT INTO `Beer` VALUES ('St. Wilbur Weizen','12','168','15','NULL','NULL');
INSERT INTO `Beer` VALUES ('Custer','Montana',NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Glacier','Montana',NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Gold-West','Montana',NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Missouri River','Montana',NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Russell','Montana',NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Yellowstone','Montana',NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Dump Truck','Summer','NULL','NULL',NULL,NULL);
INSERT INTO `Beer` VALUES ('Face Plant','Winter','NULL','NULL',NULL,NULL);
INSERT INTO `Beer` VALUES ('Killarney','Spring','NULL','NULL',NULL,NULL);
INSERT INTO `Beer` VALUES ('Oktoberfest','Fall','NULL','NULL',NULL,NULL);
INSERT INTO `Beer` VALUES ('St. Wilbur Weizen','Summer','NULL','NULL',NULL,NULL);
INSERT INTO `Beer` VALUES ('Alabama',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Alaska',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Arizona',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Arkansas',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('California',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Colorado',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Connecticut',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Delaware',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Florida',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Georgia',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Hawaii',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Idaho',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Illinois',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Indiana',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Iowa',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Kansas',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Kentucky',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Louisiana',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Maine',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Maryland',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Massachusetts',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Michigan',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Minnesota',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Mississippi',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Missouri',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Montana',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Nebraska',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Nevada',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('New Hampshire',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('New Jersey',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('New Mexico',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('New York',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('North Carolina',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('North Dakota',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Ohio',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Oklahoma',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Oregon',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Pennsylvania',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Rhode Island',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('South Carolina',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('South Dakota',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Tennessee',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Texas',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Utah',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Vermont',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Virginia',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Washington',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Washington DC',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('West Virginia',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Wisconsin',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Wyoming',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('English Pale','English Pale Ale','','406 Brewing Company','101 E Oak St #D','Bozeman');
INSERT INTO `Beer` VALUES ('JAMBER Ale','Amber Ale','','406 Brewing Company','','');
INSERT INTO `Beer` VALUES ('May Fest','Amber Ale','5.5','406 Brewing Company','','');
INSERT INTO `Beer` VALUES ('Porter','Porter','','406 Brewing Company','','');
INSERT INTO `Beer` VALUES ('Session Pale','American Pale Ale','4.6','406 Brewing Company','','');
INSERT INTO `Beer` VALUES ('Bah Humbock','Dunkler Bock','6.8','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Blonde Ale','Golden Ale/Blond Ale','4','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Dirty Girl Dunkel Weizen','Dunkelweizen','5.5','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Dog Slobber Brown Ale','Brown Ale','5','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('English Pale Ale','English Pale Ale','5','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Espresso Porter','Porter','5.3','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Fried Dynamite Stout','Sweet Stout','5.8','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Frost Killer Scottish Ale','Scottish Ale','5.5','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Grizz Whizz','Wheat Ale','4','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Head Trauma India Pale Ale','India Pale Ale (IPA)','6','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Mood Swing','Brown Ale','5','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Münchner Helles','Premium Lager','4.5','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Octoberfest','Oktoberfest/Märzen','5','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Pastey White Boy Prohibition Style Ale','Cream Ale','4','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Smoked Porter','Porter','','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Street Fight Imperial Red Ale','Amber Ale','6.5','Angry Hanks Microbrewry','','');
INSERT INTO `Beer` VALUES ('Amber','American Dark Lager','5.3','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Dancing Trout Ale (formerly Trout Slayer)','German Kristallweizen','5.6','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Dark Wheat','Dunkelweizen','5.6','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Doppelbock','Doppelbock','8.4','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Dortmunder Uber Gans','Dortmunder/Helles','','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Dragons Breath Dark Heff Ale','Dunkelweizen','5.6','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Dump Truck','Heller Bock','6','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Face Plant','Doppel weizen','7.5','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Killarney','American Dark Lager','5.7','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Maibock','Maibock','6.5','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Oktoberfest','Oktoberfest/Märzen','6','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Pilsener','Pilsener','5','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Schwarzbier','Schwarzbier','4.8','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('St. Wilbur Weizen','German Hefeweizen','5.6','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Traditional Dark','Dunkel','5.3','Bayern Brewing','','');
INSERT INTO `Beer` VALUES ('Castor Mexican Amber','Amber Ale','4.6','Beaver Creek Brewery','','');
INSERT INTO `Beer` VALUES ('Choke Cherry Wheat','Fruit Beer','','Beaver Creek Brewery','','');
INSERT INTO `Beer` VALUES ('Paddlefish Stout','Stout','6.3','Beaver Creek Brewery','','');
INSERT INTO `Beer` VALUES ('Pale Ale','American Pale Ale','6.6','Beaver Creek Brewery','','');
INSERT INTO `Beer` VALUES ('Redheaded IPA','India Pale Ale (IPA)','7.3','Beaver Creek Brewery','','');
INSERT INTO `Beer` VALUES ('Rough Rider Wheat','Wheat Ale','4','Beaver Creek Brewery','','');
INSERT INTO `Beer` VALUES ('Rusty Beaver Wheat','Wheat Ale','7.6','Beaver Creek Brewery','','');
INSERT INTO `Beer` VALUES ('Sippin Pumpkin Ale','Spice/Herb/Vegetable','','Beaver Creek Brewery','','');
INSERT INTO `Beer` VALUES ('Wibaux Gold','Golden Ale/Blond Ale','5.5','Beaver Creek Brewery','','');
INSERT INTO `Beer` VALUES ('2009 Brewer’s Reserve Barley Wine Ale','Barley Wine','10.','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Belgian Tripel','Abbey Tripel','','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Blue Collar Bitter','Premium Bitter/ESB','5.8','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Bourbon Barrel Tartanic Strong Scottish Ale','Scottish Ale','','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Cream Ale','Cream Ale','4.6','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Double Black Diamond Extreme Stout','Dry Stout','6.9','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Gold (Organic Pale Ale)','American Pale Ale','5.2','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Imperial Stout 2008','Imperial Stout','','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Lewis and Clark Dark','Brown Ale','5.4','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Lightfoot Pilsner','Pilsener','5.2','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('MacKenzie River Driftboat Amber','Ameber Ale','4.5','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Maibock','Heller Bock','7.2','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Mardi Gras Red','Amber Ale','5.7','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Missouri River Steamboat Lager','California Common','5.6','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('North Fork Organic Porter','Porter','6.5','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Octoberfest','Oktoberfest/Märzen','5.6','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Pale Bock','Heller Bock','7.2','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Single Malt IPA','India Pale Ale (IPA)','6.9','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Tartanic Strong Scottish Ale','Scottish Ale','7.7','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Woollybugger Wheat','German Hefeweizen','6','Blackfoot River Brewing','','');
INSERT INTO `Beer` VALUES ('Billings','59101','Montana',NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Bozeman','59715','Montana',NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Helena','59601','Montana',NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Missoula','59801','Montana',NULL,NULL,NULL);
INSERT INTO `Beer` VALUES ('Wibaux','59353','Montana',NULL,NULL,NULL);
COMMIT;
