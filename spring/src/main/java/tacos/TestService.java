package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.beans.ConstructorProperties;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-16 10:22
 **/
@Service
public class TestService {
    public String value = "123";

    public TestService() {
    }

    public TestService(String value) {
        this.value = value;
    }
}

