/******************************************************************************
<pre>

           =============================================================
           -   ____ _  _ ____ _  _ ____  _ _ ____ _  _ ____ _  _ ____  -
           -    __] |__| |__| |\ | | __  | | |__| |\ | [__  |__| |___  -
           -   [___ |  | |  | | \| |__| _| | |  | | \| ___] |  | |___  -
           -           http://hi.baidu.com/zhangjianshe                -
           =============================================================

</pre>
 *******************************************************************************/
package cn.mapway.server.common.convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.mapl.Mapl;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;

/**
 * JSON对象转换
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public class JsonMessageConvertor<T> implements HttpMessageConverter<T> {

	// 此转换器支持的媒体类型
	List<MediaType> mediaTypes = new ArrayList<MediaType>();

	public JsonMessageConvertor() {
		mediaTypes.add(MediaType.APPLICATION_JSON);
		mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		mediaTypes.add(MediaType.TEXT_PLAIN);
		mediaTypes.add(MediaType.TEXT_HTML);
		mediaTypes.add(MediaType.MULTIPART_FORM_DATA);

	}

	/**
	 * 判断是否可以对媒体进行解读
	 */
	public boolean canRead(Class<?> arg0, MediaType media) {
		if (media == null) {
			return true;
		}
		for (MediaType mt : mediaTypes) {
			if (mt.isCompatibleWith(media)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否可以输出某种类型
	 */
	public boolean canWrite(Class<?> arg0, MediaType media) {
		if (media == null) {
			return true;
		}
		for (MediaType mt : mediaTypes) {
			if (mt.isCompatibleWith(media)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回支持的媒体类型
	 */
	public List<MediaType> getSupportedMediaTypes() {
		return mediaTypes;
	}

	/**
	 * 读取输入的消息转换成对象
	 */
	public T read(Class<? extends T> arg0, HttpInputMessage arg1)
			throws IOException, HttpMessageNotReadableException {
		MediaType media = arg1.getHeaders().getContentType();
		System.out.println("request media type>" + media.toString());

		InputStream in = arg1.getBody();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in,
				"UTF-8"));

		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String data = sb.toString();
		System.out.println("receive data:" + data);
		if (media.isCompatibleWith(MediaType.APPLICATION_FORM_URLENCODED)) {
			data = URLDecoder.decode(data, "UTF-8");
			data = data.trim();

			if (data.startsWith("{")) {
				data = data.substring(0, data.length() - 1);
				T obj = Json.fromJson(arg0, data);
				return obj;
			} else {
				String[] pairs = StringUtils.tokenizeToStringArray(data, "&");

				Map<String, String> map = new HashMap<String, String>();
				for (String pair : pairs) {
					int idx = pair.indexOf('=');
					if (idx == -1) {
						map.put(pair, null);
					} else {
						String name = URLDecoder.decode(pair.substring(0, idx),
								"UTF-8");
						String value = URLDecoder.decode(
								pair.substring(idx + 1), "UTF-8");
						map.put(name, value);
					}
				}

				@SuppressWarnings("unchecked")
				T obj = (T) Mapl.maplistToObj(map, arg0);
				return obj;
			}
		} else if (media.isCompatibleWith(MediaType.APPLICATION_JSON)) {
			data = data.trim();
			T obj = Json.fromJson(arg0, data);
			return obj;
		} else if (media.isCompatibleWith(MediaType.TEXT_PLAIN)) {
			data = data.trim();
			T obj = Json.fromJson(arg0, data);
			return obj;
		} else {
			return null;
		}
	}

	public void write(T arg0, MediaType arg1, HttpOutputMessage arg2)
			throws IOException, HttpMessageNotWritableException {
		System.out.println("response media type>" + arg1.toString());

		arg2.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		arg2.getBody().write(
				Json.toJson(arg0, JsonFormat.full()).getBytes("UTF-8"));
	}

}
