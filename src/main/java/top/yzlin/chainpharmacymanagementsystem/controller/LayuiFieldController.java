package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.*;
import top.yzlin.tools.StringTools;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.*;

@RestController
public class LayuiFieldController {
    private Map<String, ObjectNode> tableNodeMap = new HashMap<>();
    private Map<String, ObjectNode> formNodeMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/api/tableField/{table}")
    public ObjectNode getTableField(@PathVariable("table") String tableName) {
        String upCaseTableName = String.valueOf(StringTools.StringToUpperCaseFirstChar(tableName));
        if (tableNodeMap.containsKey(upCaseTableName)) {
            return tableNodeMap.get(upCaseTableName);
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        try {
            objectNode.put("status", 200);
            List<ObjectNode> fieldList = new ArrayList<>();
            Class<?> aClass = Class.forName("top.yzlin.chainpharmacymanagementsystem.entity." + upCaseTableName);

            LayuiTableHeader lth = aClass.getAnnotation(LayuiTableHeader.class);
            if (lth != null) {
                ObjectNode node = objectMapper.createObjectNode();
                node.put("type", lth.type());
                fieldList.add(node);
            }

            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                LayuiTableField ltf = Optional.ofNullable(field.getAnnotation(LayuiTableField.class))
                        .orElse(new LayuiTableFieldImpl(field.getName()));
                if (!ltf.enable()) {
                    continue;
                }
                fieldList.add(layuiTableFieldConversion(ltf, field));
            }
            ObjectNode node = objectMapper.createObjectNode();
            node.put("align", "center");
            node.put("fixed", "right");
            node.put("toolbar", "#" + tableName + "Bar");
            fieldList.add(node);
            objectNode.putPOJO("data", fieldList);
        } catch (ClassNotFoundException e) {
            objectNode.put("status", 404);
        }
        tableNodeMap.put(upCaseTableName, objectNode);
        return objectNode;
    }

    private ObjectNode layuiTableFieldConversion(LayuiTableField ltf, Field field) {
        ObjectNode node = objectMapper.createObjectNode();
        node.put("title", "".equals(ltf.value()) ? field.getName() : ltf.value());
        node.put("field", "".equals(ltf.field()) ? field.getName() : ltf.field());
        node.put("width", ltf.width());
        node.put("minWidth", ltf.minWidth());
        node.put("sort", ltf.sort());
        node.put("totalRow", ltf.totalRow());
        if (!"".equals(ltf.edit())) {
            node.put("edit", ltf.edit());
        }
        return node;
    }

    @GetMapping("/api/formField/{table}")
    public ObjectNode getFormField(@PathVariable("table") String tableName) {
        String upCaseTableName = String.valueOf(StringTools.StringToUpperCaseFirstChar(tableName));
        if (formNodeMap.containsKey(upCaseTableName)) {
            return formNodeMap.get(upCaseTableName);
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        try {
            objectNode.put("status", 200);
            List<ObjectNode> fieldList = new ArrayList<>();
            Class<?> aClass = Class.forName("top.yzlin.chainpharmacymanagementsystem.entity." + upCaseTableName);
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
        } catch (ClassNotFoundException e) {
            objectNode.put("status", 404);
        }
        formNodeMap.put(upCaseTableName, objectNode);
        return objectNode;
    }

    private ObjectNode layuiTableFormConversion(LayuiTableField ltf, LayuiFormField lff, Field field) {
        ObjectNode node = objectMapper.createObjectNode();
        String title = "".equals(lff.value()) ? "".equals(ltf.value()) ? field.getName() : ltf.value() : lff.value();
        node.put("title", title);
        String name = "".equals(lff.name()) ? "".equals(ltf.field()) ? field.getName() : ltf.field() : lff.name();
        node.put("name", name);
        node.put("placeholder", "".equals(lff.placeholder()) ? title : lff.placeholder());
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
