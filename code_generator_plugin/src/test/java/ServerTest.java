import com.generator.AppBoot;
import com.generator.annotation.RequestMapping;
import com.generator.controller.TestController;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public class ServerTest {


    @Test
    public void testServer() {
        AppBoot.appStart();
//        while (true) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }


    @Test
    public void testParam() {
//        MethodUtils.getMethodsListWithAnnotation(TestController.)
        Method[] methods = TestController.class.getMethods();
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName());
            }
        }
    }

    @Test
    public void testParamName(){
        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        List<Method> methodList = MethodUtils.getMethodsListWithAnnotation(TestController.class.getClass(), RequestMapping.class);
        String[] params = u.getParameterNames(methodList.get(0));
        for (int i = 0; i < params.length; i++) {
            System.out.println(params[i]);
        }
    }
}
