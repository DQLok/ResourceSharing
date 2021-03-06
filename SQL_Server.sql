USE [CompanyLab1]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 7/13/2021 9:53:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [varchar](10) NOT NULL,
	[categoryname] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRequest]    Script Date: 7/13/2021 9:53:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRequest](
	[requestID] [int] IDENTITY(1,1) NOT NULL,
	[datebook] [date] NULL,
	[quantityReq] [int] NULL,
	[statusreqID] [varchar](10) NULL,
	[userID] [varchar](50) NULL,
	[itemID] [varchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblResource]    Script Date: 7/13/2021 9:53:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblResource](
	[itemID] [varchar](10) NOT NULL,
	[itemname] [nvarchar](30) NULL,
	[color] [varchar](10) NULL,
	[categoryID] [varchar](10) NULL,
	[quantity] [int] NULL,
	[dateresoure] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[itemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblStatus]    Script Date: 7/13/2021 9:53:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStatus](
	[statusID] [varchar](10) NOT NULL,
	[statusname] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblStatusRequest]    Script Date: 7/13/2021 9:53:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStatusRequest](
	[statusreqID] [varchar](10) NOT NULL,
	[statusreqname] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[statusreqID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 7/13/2021 9:53:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](50) NOT NULL,
	[password] [varchar](20) NULL,
	[phone] [char](10) NULL,
	[name] [nvarchar](30) NULL,
	[address] [nvarchar](50) NULL,
	[statusID] [varchar](10) NULL,
	[createdate] [date] NULL,
	[role] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblCategory] ([categoryID], [categoryname]) VALUES (N'c1', N'software')
INSERT [dbo].[tblCategory] ([categoryID], [categoryname]) VALUES (N'c2', N'technology')
INSERT [dbo].[tblCategory] ([categoryID], [categoryname]) VALUES (N'c3', N'animal')
INSERT [dbo].[tblCategory] ([categoryID], [categoryname]) VALUES (N'c4', N'subject')
GO
SET IDENTITY_INSERT [dbo].[tblRequest] ON 

INSERT [dbo].[tblRequest] ([requestID], [datebook], [quantityReq], [statusreqID], [userID], [itemID]) VALUES (1, CAST(N'2021-05-22' AS Date), 9, N'sr2', N'se2', N'i1')
INSERT [dbo].[tblRequest] ([requestID], [datebook], [quantityReq], [statusreqID], [userID], [itemID]) VALUES (2, CAST(N'2021-05-22' AS Date), 9, N'sr2', N'se2', N'i1')
INSERT [dbo].[tblRequest] ([requestID], [datebook], [quantityReq], [statusreqID], [userID], [itemID]) VALUES (3, CAST(N'2021-05-22' AS Date), 4, N'sr2', N'se2', N'i11')
INSERT [dbo].[tblRequest] ([requestID], [datebook], [quantityReq], [statusreqID], [userID], [itemID]) VALUES (4, CAST(N'2021-05-22' AS Date), 4, N'sr2', N'se2', N'i11')
INSERT [dbo].[tblRequest] ([requestID], [datebook], [quantityReq], [statusreqID], [userID], [itemID]) VALUES (5, CAST(N'2021-05-22' AS Date), 50, N'sr2', N'se2', N'i13')
INSERT [dbo].[tblRequest] ([requestID], [datebook], [quantityReq], [statusreqID], [userID], [itemID]) VALUES (6, CAST(N'2021-05-22' AS Date), 50, N'sr3', N'se2', N'i13')
SET IDENTITY_INSERT [dbo].[tblRequest] OFF
GO
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i1', N'netbean', N'black', N'c1', 1, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i10', N'it', N'black', N'c4', 2, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i11', N'history', N'white', N'c4', 1, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i12', N'chicken', N'yellow', N'c3', 70, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i13', N'fish', N'pink', N'c3', 10, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i14', N'cat', N'white', N'c3', 30, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i15', N'dog', N'black', N'c3', 10, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i16', N'lion', N'yellow', N'c3', 1, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i17', N'pig', N'pink', N'c3', 1, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i18', N'duck', N'white', N'c3', 7, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i19', N'math', N'blue', N'c4', 2, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i2', N'sqlserver', N'white', N'c1', 50, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i20', N'cea201', N'green', N'c4', 5, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i21', N'csi101', N'black', N'c4', 15, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i22', N'prf192', N'green', N'c4', 14, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i23', N'ssg101', N'white', N'c4', 20, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i24', N'dbi202', N'red', N'c4', 11, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i25', N'pro192', N'blue', N'c4', 7, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i26', N'facebook', N'blue', N'c1', 22, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i27', N'messenger', N'blue', N'c1', 21, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i28', N'fan', N'pink', N'c2', 55, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i29', N'tv', N'black', N'c2', 100, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i3', N'smartphone', N'red', N'c2', 1, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i30', N'usb', N'pink', N'c2', 10, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i4', N'laptop', N'black', N'c2', 0, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i5', N'headphone', N'white', N'c2', 11, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i6', N'mouse', N'pink', N'c2', 21, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i7', N'eclipse', N'yellow', N'c1', 42, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i8', N'google meet', N'green', N'c1', 2, CAST(N'2021-05-10' AS Date))
INSERT [dbo].[tblResource] ([itemID], [itemname], [color], [categoryID], [quantity], [dateresoure]) VALUES (N'i9', N'soud', N'black', N'c2', 2, CAST(N'2021-05-10' AS Date))
GO
INSERT [dbo].[tblStatus] ([statusID], [statusname]) VALUES (N's1', N'new')
INSERT [dbo].[tblStatus] ([statusID], [statusname]) VALUES (N's2', N'active')
GO
INSERT [dbo].[tblStatusRequest] ([statusreqID], [statusreqname]) VALUES (N'sr1', N'new')
INSERT [dbo].[tblStatusRequest] ([statusreqID], [statusreqname]) VALUES (N'sr2', N'accept')
INSERT [dbo].[tblStatusRequest] ([statusreqID], [statusreqname]) VALUES (N'sr3', N'delete')
INSERT [dbo].[tblStatusRequest] ([statusreqID], [statusreqname]) VALUES (N'sr4', N'inactive')
GO
INSERT [dbo].[tblUsers] ([userID], [password], [phone], [name], [address], [statusID], [createdate], [role]) VALUES (N'abc@gmail.com', N'1', N'0123456789', N'Diep Quoc Loc', N'Thu Duc', N's1', CAST(N'2021-05-21' AS Date), N'user')
INSERT [dbo].[tblUsers] ([userID], [password], [phone], [name], [address], [statusID], [createdate], [role]) VALUES (N'locdqse141081@fpt.edu.vn', N'***', N'***       ', N'locdqse141081@fpt.edu.vn', N'117132343922242176878', N's1', CAST(N'2021-05-21' AS Date), N'user')
INSERT [dbo].[tblUsers] ([userID], [password], [phone], [name], [address], [statusID], [createdate], [role]) VALUES (N'se1', N'1', N'0123456789', N'Diệp Quốc Lộc', N'Thủ Đức', N's2', CAST(N'2021-10-05' AS Date), N'admin')
INSERT [dbo].[tblUsers] ([userID], [password], [phone], [name], [address], [statusID], [createdate], [role]) VALUES (N'se2', N'2', N'9876543210', N'Bành Đức Hiếu', N'Quận 9', N's2', CAST(N'2021-10-05' AS Date), N'user')
INSERT [dbo].[tblUsers] ([userID], [password], [phone], [name], [address], [statusID], [createdate], [role]) VALUES (N'wkhhkw01@gmail.com', N'1', N'0123456789', N'Diep Quoc Loc', N'Thu Duc', N's1', CAST(N'2021-05-23' AS Date), N'user')
GO
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD FOREIGN KEY([itemID])
REFERENCES [dbo].[tblResource] ([itemID])
GO
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD FOREIGN KEY([itemID])
REFERENCES [dbo].[tblResource] ([itemID])
GO
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD FOREIGN KEY([itemID])
REFERENCES [dbo].[tblResource] ([itemID])
GO
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD FOREIGN KEY([statusreqID])
REFERENCES [dbo].[tblStatusRequest] ([statusreqID])
GO
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD FOREIGN KEY([statusreqID])
REFERENCES [dbo].[tblStatusRequest] ([statusreqID])
GO
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD FOREIGN KEY([statusreqID])
REFERENCES [dbo].[tblStatusRequest] ([statusreqID])
GO
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblResource]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblResource]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblResource]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
