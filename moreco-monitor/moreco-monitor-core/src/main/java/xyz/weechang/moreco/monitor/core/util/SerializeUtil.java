package xyz.weechang.moreco.monitor.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

/**
 * @author zhangwei
 * date 2019/1/1
 * time 14:07
 */
@Slf4j
public class SerializeUtil {

    public static String serialize(Object obj) {
        String msg = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            msg = Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (Exception e) {
            log.error("object serialize error:", e);
        }
        return msg;
    }

    public static Object deserialize(String msg) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(msg));
            ObjectInputStream in = new ObjectInputStream(bis);
            obj = in.readObject();
        } catch (Exception e) {
            log.error("msg deserialize error:", e);
        }
        return obj;
    }
}
