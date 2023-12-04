IF DB_ID (N'SENG2050_IT_SERVICE') IS NOT NULL
USE [master];

IF DB_ID (N'SENG2050_IT_SERVICE') IS NOT NULL
DROP DATABASE [SENG2050_IT_SERVICE];

CREATE DATABASE [SENG2050_IT_SERVICE];
GO
USE [SENG2050_IT_SERVICE];

IF SUSER_ID(N'group18') IS NOT NULL
BEGIN
	DROP LOGIN [group18];
END
CREATE LOGIN group18
WITH PASSWORD = 'mySecur3Passw0rd!';

IF USER_ID(N'group18') IS NOT NULL
BEGIN
	DROP USER [group18];
END
CREATE USER group18
FOR LOGIN group18;



GRANT SELECT, INSERT, UPDATE, DELETE TO group18;


IF OBJECT_ID(N'person', N'U') IS NOT NULL
BEGIN
	DROP TABLE [person];
END


CREATE TABLE person
(
	personID			  UNIQUEIDENTIFIER,
	Fname				  varchar(20),
	Lname				  varchar(20),
	email				VARCHAR(30),
	userPassword		 VARCHAR(100),
	phoneNo    			VARCHAR(15),
	roleInSystem         VARCHAR(20),

	PRIMARY KEY (personID),
)
go

IF OBJECT_ID(N'Issues', N'U') IS NOT NULL
BEGIN
	DROP TABLE [Issues];
END


CREATE TABLE Issues
(
	issueID				 UNIQUEIDENTIFIER PRIMARY KEY,
	personID			 UNIQUEIDENTIFIER,
	issueState			 varchar(20),
	category			 varchar(30),
	subCategory          varchar(30),
	title				 varchar(300),
	resolutionDetails     varchar(MAX),
	dateReported		 varchar(30),
	timeReported	   	varchar(30),
	dateSolved			varchar(30),
	description      varchar(MAX),
	FOREIGN KEY(personID) REFERENCES person(personID)
	ON DELETE CASCADE
)
go

IF OBJECT_ID(N'Comments', N'U') IS NOT NULL
BEGIN
	DROP TABLE [Comments];
END

CREATE TABLE Comments
(
	commentsId      UNIQUEIDENTIFIER,
	personID			  UNIQUEIDENTIFIER,
	description    varchar(MAX),
	issueID				 UNIQUEIDENTIFIER,
	PRIMARY KEY (commentsId),
	FOREIGN KEY(issueID) REFERENCES Issues(issueId)
	ON DELETE NO ACTION,
	FOREIGN KEY(personID) REFERENCES person(personID)
	ON DELETE CASCADE

)
go

 -- LOADING TO Person TABLE
-- --Admins
INSERT INTO person (personID, Fname, Lname, email, userPassword, phoneNo, roleInSystem)
VALUES ('76bce47c-9fea-4cc9-86aa-c42551f45a57', 'Hayden', 'Cheers', 'hayden.cheers@gmail.com', '4d1b6189a6365bd1fee22b16edc549483e8d774194cdb89ce8ee2200', '0472943839', 'Admin')

INSERT INTO person (personID, Fname, Lname, email, userPassword, phoneNo, roleInSystem)
VALUES ('73c9279c-c02b-4afa-a788-414ff0758bdd', 'Yuqing', 'Lin', 'yuqing.lin@gmail.com', 'd798dbf801dc3040d6e2d45e5473496121fbd7165d856d51871dc568', '0459349300', 'Admin')

INSERT INTO person (personID, Fname, Lname, email, userPassword, phoneNo, roleInSystem)
VALUES ('5b957c88-dda7-4a87-9349-c27212aee8c8', 'Jack', 'BigMac', 'Jack@gmail.com', '9f63079bc4c80492c0c291064717f560a4a03119241128bb803d962c', '0459349300', 'Admin')

INSERT INTO person (personID, Fname, Lname, email, userPassword, phoneNo, roleInSystem)
VALUES ('b81906c0-3c35-4956-b365-a45565f60c7d', 'Mike', 'Hunt', 'Mike@gmail.com', '5053ff28c15a2e4c8836d049d9e39e7a9d554fefac962aa97b2cd9c8', '0412348656', 'Admin')


-- --Normal users
INSERT INTO person (personID, Fname, Lname, email, userPassword, phoneNo, roleInSystem)
VALUES ('694c3ec2-1726-48ff-8dbd-ece905a59e12', 'Grand', 'Hunter', 'Grand@gmail.com', '6ca27bb1d25ca6d7881610c8d19cc918c924decf345d337608b22620', '0491749356', 'User')

INSERT INTO person (personID, Fname, Lname, email, userPassword, phoneNo, roleInSystem)
VALUES ('b8f8eb4c-cf32-47ba-9b17-a8a57fcbc74d', 'Ana', 'Teaching', 'Ana@gmail.com', 'c806c782b539eed5122141f60f3654d04daf13302afdcec99965ed67', '0498899872', 'User')

INSERT INTO person (personID, Fname, Lname, email, userPassword, phoneNo, roleInSystem)
VALUES ('c24f934b-0e49-4881-bbd6-937eedc8541c', 'Games', 'BugHead', 'Games@gmail.com', '1b73c8119fe09f03841e59a6f59bcc8519631e32a8827b02a601938c', '0437655786', 'User')






