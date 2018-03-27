package tgtools.spirngbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tgtools.data.DataTable;
import tgtools.exceptions.APPErrorException;
import tgtools.spirngbootweb.demo.mybatis.db1.MyUserDao;
import tgtools.spirngbootweb.demo.mybatis.db1.MyUserDO;
import tgtools.spirngbootweb.demo.mybatis.db2.UserDao;
import tgtools.spirngbootweb.demo.mybatis.db2.UserDO;

import java.util.List;

@SpringBootApplication
public class SpirngBootWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpirngBootWebApplication.class, args);
		System.out.println("==============================================================");
		try {
			DataTable dt= tgtools.db.DataBaseFactory.get("MapTileDATAACCESS").Query("select 1");
			System.out.println(dt.toJson());
		} catch (APPErrorException e) {
			e.printStackTrace();
		}
		tgtools.util.LogHelper.info("","fdsafdasfdsa","main");
		testDao();
	}
	private static void testDao()
	{

		SpirngBootWebApplication dd =new SpirngBootWebApplication();
		MyUserDao dUserDao=(MyUserDao)tgtools.web.platform.Platform.getBean("myUserDao");

		UserDao userDao=(UserDao)tgtools.web.platform.Platform.getBean("userDao");
		List<UserDO> res1= userDao.lista();
		List<MyUserDO> res2=dUserDao.lista();
		System.out.println("res1:"+res1.size());
		System.out.println("res2:"+res2.size());

//		try {
//			DataSource dataSource = tgtools.web.platform.Platform.getBean("dataSource");
//			Connection conn = dataSource.getConnection();
//			PreparedStatement pre=conn.prepareStatement("select id,cron_expression,method_name,is_concurrent,description,update_by,bean_class,create_date,job_status,job_group,update_date,create_by,spring_bean,job_name from QRTZ_TASK  order by id desc   limit ?, ?");
//			pre.setObject(1,0);
//			pre.setObject(2,10);
//			pre.executeQuery();
//			System.out.println("fdsafdsa");
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}

}
