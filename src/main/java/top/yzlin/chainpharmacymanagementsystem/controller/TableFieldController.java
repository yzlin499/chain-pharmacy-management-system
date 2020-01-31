package top.yzlin.chainpharmacymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.yzlin.chainpharmacymanagementsystem.entity.User;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableField;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableFieldImpl;
import top.yzlin.chainpharmacymanagementsystem.layuiannotations.LayuiTableHeader;

import java.lang.reflect.Field;
import java.util.*;

@RestController
public class TableFieldController {
    private Map<String, ObjectNode> nodeMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/api/tableField/{table}")
    public ObjectNode getTableField(@PathVariable("table") String tableName) {
        if (nodeMap.containsKey(tableName)) {
            return nodeMap.get(tableName);
        }
        char[] chars = tableName.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        ObjectNode objectNode = objectMapper.createObjectNode();
        try {
            objectNode.put("status", 200);
            List<ObjectNode> fieldList = new ArrayList<>();
            Class<?> aClass = Class.forName("top.yzlin.chainpharmacymanagementsystem.entity." + String.valueOf(chars));

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
        nodeMap.put(tableName, objectNode);
        return objectNode;
    }

    public ObjectNode layuiTableFieldConversion(LayuiTableField ltf, Field field) {
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

}
