/**
 * 
 */
package hsearchbug;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.momega.hsearchbug.SpringConfig;
import com.momega.hsearchbug.service.ParentService;

/**
 * @author martin
 *
 */
public class SimpleTest {

	@Test
	public void test() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
		ParentService service = ac.getBean(ParentService.class);
		
		service.inputData();
	}

}
