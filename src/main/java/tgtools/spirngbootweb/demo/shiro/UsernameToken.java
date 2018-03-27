package tgtools.spirngbootweb.demo.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 15:12
 */
public class UsernameToken extends UsernamePasswordToken {

    public String getToken()
    {
        return new String(getPassword());
    }
}
