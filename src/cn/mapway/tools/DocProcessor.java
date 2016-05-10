package cn.mapway.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import org.nutz.json.Json;

/**
 * JAVA APT 处理工具
 * 
 * @author zhangjianshe
 *
 */
@SupportedAnnotationTypes({ "cn.mapway.document.annotation.Doc",
		"cn.mapway.document.annotation.ApiField" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class DocProcessor extends AbstractProcessor {

	public DocProcessor() {
		super();
	}

	@SuppressWarnings("resource")
	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		
		return false;
	}

	private void handle(Set<? extends TypeElement> annotations) {
		FileWriter file = null;
		try {
			file = new FileWriter("d:\\annotations.txt", true);

			BufferedWriter out = new BufferedWriter(file);

			for (TypeElement e : annotations) {
					out.write(e.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
