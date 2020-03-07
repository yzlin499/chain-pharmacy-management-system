package top.yzlin.chainpharmacymanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;
import top.yzlin.chainpharmacymanagementsystem.entity.User;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiFormField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiFormFieldImpl;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableFieldImpl;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommonTest {

    ObjectMapper objectMapper = new ObjectMapper();

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

    @Test
    public void formTest() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("status", 200);
        List<ObjectNode> fieldList = new ArrayList<>();
        Class<?> aClass = Medicine.class;
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            LayuiTableField ltf = Optional.ofNullable(field.getAnnotation(LayuiTableField.class))
                    .orElse(new LayuiTableFieldImpl(field.getName()));
            LayuiFormField lff = Optional.ofNullable(field.getAnnotation(LayuiFormField.class))
                    .orElse(new LayuiFormFieldImpl(ltf));
            if (!lff.enable()) {
                continue;
            }
            fieldList.add(layuiTableFormConversion(ltf, lff, field));
        }
        objectNode.putPOJO("data", fieldList);
        System.out.println(objectNode);
    }

    private ObjectNode layuiTableFormConversion(LayuiTableField ltf, LayuiFormField lff, Field field) {
        ObjectNode node = objectMapper.createObjectNode();
        String title = "".equals(lff.value()) ? "".equals(ltf.value()) ? field.getName() : ltf.value() : lff.value();
        node.put("title", title);
        String name = "".equals(lff.name()) ? "".equals(ltf.field()) ? field.getName() : ltf.field() : lff.name();
        node.put("name", name);
        node.put("placeholder", lff.placeholder());
        if ("".equals(lff.type())) {
            if (Number.class.isAssignableFrom(field.getType())
                    || field.getType().equals(short.class)
                    || field.getType().equals(int.class)
                    || field.getType().equals(long.class)
                    || field.getType().equals(float.class)
                    || field.getType().equals(double.class)) {
                node.put("type", "number");
            } else {
                node.put("type", "text");
            }
        } else {
            node.put("type", lff.type());
        }
        if (lff.verify().length == 0) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            if (annotation != null) {
                node.put("verify", LayuiFormField.VERIFY_REQUIRED);
            }
        } else {
            node.put("verify", String.join("|", lff.verify()));
        }
        return node;
    }
}
