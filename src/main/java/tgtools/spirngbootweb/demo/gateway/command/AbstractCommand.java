package tgtools.spirngbootweb.demo.gateway.command;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:38
 */
public abstract class AbstractCommand<T> extends tgtools.web.develop.command.AbstractCommand<T> {

    @Override
    public String getType() {
        return "rest";
    }


}
