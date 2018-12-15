import com.Application;
import com.model.Boss;
import com.model.Car;
import com.service.CustomCarEditor;
import com.service.JavaBeanInfoUtil;
import com.service.NameEditor;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.PropertyDescriptor;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class BeanInfoUtilTest {

    @Test
    public void 测试CustomCarEditor(){
        CustomCarEditor customCarEditor = new CustomCarEditor();
        customCarEditor.setAsText("BMW,Black,200m/s,2000");
        Car car = new Car();
        car = (Car) customCarEditor.getValue();
        TestCase.assertEquals("BMW",car.getBrand());
        TestCase.assertEquals("Black",car.getColor());
        TestCase.assertEquals("200m/s",car.getMaxSpeed());
        TestCase.assertEquals(2000,car.getWeight().intValue());
    }

    @Test
    public void 测试得到属性描述器() throws Exception {
        Boss boss = new Boss();
        JavaBeanInfoUtil beanInfoUtil = new JavaBeanInfoUtil();
        PropertyDescriptor propertyDescriptor = beanInfoUtil.getPropertyDescriptor(Boss.class,boss,"name",null);
        propertyDescriptor.getWriteMethod().invoke(boss,"hxy");
        TestCase.assertEquals("hxy",propertyDescriptor.getReadMethod().invoke(boss));
    }

    @Test
    public void 测试NameEditorSetValue(){
        NameEditor nameEditor = new NameEditor();
        nameEditor.setAsText("hangxiaoyan");
        TestCase.assertEquals("HANGXIAOYAN",nameEditor.getValue());
    }

    @Test
    public void 测试通过内省器对属性值操作() throws Exception {
        Boss boss = new Boss();
        JavaBeanInfoUtil beanInfoUtil = new JavaBeanInfoUtil();
        beanInfoUtil.setPropertyByIntrospector(Boss.class,boss,"name","hangxiaoyan");
        String name = (String) beanInfoUtil.getPropertyByIntrospector(Boss.class,boss,"name");
        TestCase.assertEquals("hangxiaoyan",name);
    }

}
