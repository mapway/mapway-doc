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
package cn.mapway.document.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * 自定义注解类.
 *
 * @author zhangjianshe@navinfo.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE,
		ElementType.FIELD })
public @interface Doc {

	/**
	 * 描述信息,对要描述的类和方法做出解释和说明/title.
	 *
	 * @return the string
	 */
	public String value();

	/**
	 * 描述信息.
	 *
	 * @return the string
	 */
	public String desc() default "";

	/**
	 * 作者.
	 *
	 * @return the string
	 */
	public String author() default "zhangjianshe@gmail.com";

	/**
	 * 接口分组信息 ,以 /为分隔符 进行树形分组，缺省归属为根节点.
	 *
	 * @return the string
	 */
	public String group() default "/";

	/**
	 * 接口在分组中的排序
	 * 
	 * @return
	 */
	public int order() default 0;

	/**
	 * 开发状态
	 * 
	 * @return
	 */
	public DevelopmentState state() default DevelopmentState.UNSTART;

}
