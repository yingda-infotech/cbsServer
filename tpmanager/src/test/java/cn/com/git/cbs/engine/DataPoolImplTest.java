package cn.com.git.cbs.engine;

import org.junit.Test;

import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.test.BaseTest;

public class DataPoolImplTest extends BaseTest {
	
	@Test
	public void testgetStringList(){
		DataObject dataObject = new DataObject();
		dataObject.set("fld", "{ title= trancode= fld=Ccy~~~~~ fld=Ticket~~~~~"
				+ " fld=TranAmt~~~~~ fld=DenomNum~~~~~ fld=OthAmt~~~~~ fld=DenomNumF~~~~~ }"
				+ " '01'~'001'~100.00~0~0~&&'02'~'001'~200.00~0~0~&&'03'~'001'~300.00~0~0~");
		DataPoolImpl datapool = new DataPoolImpl();
		datapool.setDataObject(dataObject);
		
		datapool.getStringList("fld", DataObject.class);
		
		
		DataObject data = new DataObject();
		data.set("key1", "heihei1");
		data.set("key2", "heihei2");
		data.set("key3", "heihei3");
		data.set("key4", "heihei4");
		data.set("key5", "heihei5");
		
		datapool.setValues(data, "key1","key2","key3");
		
		DataObject target = new DataObject();
		datapool.getValues(target, "key1","key2","key3","key4","key5");
	}
	
}
