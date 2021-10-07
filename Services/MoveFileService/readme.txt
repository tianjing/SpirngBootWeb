服务名称：MoveFileService
类全称：movefileservice.runner.MoveFileService

在表ACT_DATADICTIONARY中添加配置数据，例如：
2	2	test	{'source':'/D:/tianjing/Desktop/aaa','targe':'/D:/tianjing/Desktop/bbb','names':['a1','a2']}	MoveFileService	<NULL>

本服务是通过读取字典表中类型为MoveFileService的配置信息，逐条处理。