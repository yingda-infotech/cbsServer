package cn.com.git.cbs.engine;

import org.springframework.beans.factory.annotation.Value;

import cn.com.git.cbs.client.Client;
import cn.com.git.cbs.common.utils.FileUtils;
import cn.com.git.cbs.message.Message;
import cn.com.git.cbs.message.MessageHeader;

/**
 * 交易报文测试公共类
 * @author xia
 *
 */
public class TranTestUtil {
	@Value("${testServiceIp}")
	private static String testServiceIp = "10.100.21.39"; //测试客户端ip
	
	/**
	 * 测试xml报文
	 * @param trancode 交易代码，如910001
	 * @param filePath	测试文件路径：如/910001RQ.xml
	 */
	public static void testXml(String trancode,String filePath) {
		//报文头
		MessageHeader header = new MessageHeader("RQ", "XML", trancode, "0",
				"################################", "CBS");
		Message req = new Message();
		req.setHeader(header);
//		String xmlData = FileUtils.readTextResource("/" + fileName + ".xml", "UTF-8");
		String xmlData = FileUtils.readTextResource(filePath, "UTF-8");
		req.setBodyData(xmlData);
		new Client(testServiceIp,req).start();
	}
	
	/**
	 * 通过json 报文测试交易
	 * @param trancode 交易代码，如910001
	 * @param filePath	测试文件路径：如/910001RQ.json
	 */
	public static void testTransactionByJSONFile(String trancode,String filePath) {
		//报文头
		MessageHeader header = new MessageHeader("RQ", "JSN", trancode, "0",
				"################################", "CBS");
		Message req = new Message();
		req.setHeader(header);
//		String jsonData = FileUtils.readTextResource("/" + fileName + ".json", "UTF-8");
		String jsonData = FileUtils.readTextResource(filePath, "UTF-8");
		req.setBodyData(jsonData);
		new Client(testServiceIp,req).start();
	}
	
	/**
	 * 通过json 报文测试子交易
	 * 直接拼数据，调用子交易
	 * @param beanId 子交易的beanID
	 * @param map 传入参数，key-value
	 */
//	public static void testSubtxByMap(String beanId,Map<String, Object> map) {	
//		
//		DataPoolImpl datapool = new DataPoolImpl();
//		datapool.getDataObject().setData(map);		
//		
//		SubTransaction subTransaction = SpringContextUtils.getBean(beanId, SubTransaction.class);
//		
//		if (subTransaction == null) {
//			throw ExceptionUtils.returnError("PL3003", null, beanId);
//		}
//		subTransaction.execute(datapool);
//	}

}
