package top.yzlin.chainpharmacymanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.hateoas.server.core.AnnotationAttribute;
import top.yzlin.chainpharmacymanagementsystem.entity.User;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableFieldImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommonTest {

    @Test
    public void test() {
        ObjectMapper objectMapper = new ObjectMapper();
        Class<User> userClass = User.class;
        ObjectNode objectNode = objectMapper.createObjectNode();
        Field[] declaredFields = userClass.getDeclaredFields();

        List<ObjectNode> fieldList = new ArrayList<>();
        for (Field field : declaredFields) {
            LayuiTableField ltf = Optional.ofNullable(field.getAnnotation(LayuiTableField.class))
                    .orElse(new LayuiTableFieldImpl(field.getName()));
            if (!ltf.enable()) {
                continue;
            }
            ObjectNode node = objectMapper.createObjectNode();
            node.put("title", "".equals(ltf.value()) ? field.getName() : ltf.value());
            node.put("field", "".equals(ltf.field()) ? field.getName() : ltf.field());
            node.put("width", ltf.width());
            node.put("minWidth", ltf.minWidth());
            node.put("sort", ltf.sort());
            node.put("totalRow", ltf.totalRow());
            fieldList.add(node);
        }
        objectNode.putPOJO("data", fieldList);
        System.out.println(objectNode.toString());

    }

}
