package tgtools.spirngbootweb.util;

import tgtools.util.StringUtil;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 15:28
 */
public class ModelHelper {

    public static @NotNull String getTableName(@NotNull Class<?> pClazz)
    {
       Table[] table= pClazz.getAnnotationsByType(Table.class);
       if(null!=table&&table.length>0)
       {
            return table[0].name();
       }
       return StringUtil.EMPTY_STRING;
    }

}
