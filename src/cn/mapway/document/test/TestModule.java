package cn.mapway.document.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mapway.document.annotation.Doc;
import cn.mapway.document.doc.ApiDocumentHelper;
import cn.mapway.document.doc.ParseType;
import cn.mapway.document.gen.ApiGenerator;
import cn.mapway.document.gen.module.GenContext;
import cn.mapway.document.meta.module.ApiDocument;

// TODO: Auto-generated Javadoc
/**
 * 测试.
 *
 * @author zhangjianshe
 */
@Doc(value = "ABCD", group = "/Test")
@RequestMapping(value = "/")
@RestController
public class TestModule {

	/**
	 * Test.
	 *
	 * @param req the req
	 * @return the adds the device info resp
	 */
	@RequestMapping("add")
	@Doc("saddas")
	public AddDeviceInfoResp test(GWAddDeviceReq req) {
		return null;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		GenContext context = new GenContext();
		context.setAuthor("zhangjsf@enn.com");
		context.setDocTitle("泛能云接口文档");
		context.setDomain("www.enn.cn");

		ApiDocumentHelper helper = new ApiDocumentHelper();
		ApiGenerator ge = new ApiGenerator();
		ApiDocument api = ge.parsePackage(ParseType.PT_SPRING,
				"cn.mapway.document.test", context);
		String html = helper.gendoc(api, context);

		System.out.println(html);
	}
}
