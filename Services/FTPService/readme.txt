服务名称：FtpService
类全称：ftpservice.runner.FtpService

在表ACT_DATADICTIONARY中添加配置数据，例如：
3	3	test	{'type':'up','ip':'192.168.88.128','port':21,'username':'administrator','password':'TGtg123','ftppath':'/home/d5000/dmail/sysrec/','localpath':'/D:/tianjing/Desktop/send/','names':['a1','a2']}	FtpService	<NULL>
4	4	test2	{'type':'up','ip':'192.168.88.32','port':22,'username':'tianjing','password':'tianjing','ftppath':'/home/tianjing/桌面/rec/','localpath':'/D:/tianjing/Desktop/send/','names':['a3','a4']}	FtpService	<NULL>
5	5	test3	{'type':'down','ip':'192.168.88.32','port':22,'username':'tianjing','password':'tianjing','ftppath':'/home/tianjing/send/','localpath':'/D:/tianjing/Desktop/rec/','names':['a3','a4']}	FtpService	<NULL>
6	6	test4	{'type':'down','ip':'192.168.88.128','port':21,'username':'administrator','password':'TGtg123','ftppath':'/home/d5000/dmail/syssend/','localpath':'/D:/tianjing/Desktop/rec/','names':['a1','a2']}	FtpService	<NULL>
7	7	test4	{'type':'down','ip':'192.168.88.128','port':21,'username':'administrator','password':'TGtg123','ftppath':'/home/d5000/dmail/syssend/','localpath':'/D:/tianjing/Desktop/rec/','names':[],'alldata':true}	FtpService	<NULL>
8	8	test4	{'type':'up','ip':'192.168.88.128','port':21,'username':'administrator','password':'TGtg123','ftppath':'/home/d5000/dmail/syssend/','localpath':'/D:/tianjing/Desktop/rec/','names':[],'alldata':true}	FtpService	<NULL>
8	8	test4	{'ftptype':'sftp''type':'up','ip':'192.168.88.128','port':22,'username':'administrator','password':'TGtg123','ftppath':'/home/d5000/dmail/syssend/','localpath':'/D:/tianjing/Desktop/rec/','names':[],'alldata':true}	FtpService	<NULL>

本服务是通过读取字典表中类型为FtpService的配置信息，逐条处理。
names 为起始文件名
'alldata':true 为忽略names 下载或者上传所有文件。
'ftptype':可选，可填写sftp或者ftp