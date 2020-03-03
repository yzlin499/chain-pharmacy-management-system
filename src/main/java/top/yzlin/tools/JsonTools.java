package top.yzlin.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.domain.Page;

import java.util.List;

public final class JsonTools {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static ObjectNode customResultData(String nodeName, Page page) {
        ObjectNode res = OBJECT_MAPPER.createObjectNode();

        ObjectNode embedded = OBJECT_MAPPER.createObjectNode();
        res.putPOJO("_embedded", embedded);
        embedded.putPOJO(nodeName, page.toList());
        ObjectNode pageNode = OBJECT_MAPPER.createObjectNode();
        res.putPOJO("page", pageNode);
        pageNode.put("size", page.getSize());
        pageNode.putPOJO("totalElements", page.getTotalElements());
        pageNode.putPOJO("totalPages", page.getTotalPages());
        pageNode.putPOJO("number", page.getNumber());
        return res;
    }

    public static ObjectNode customResultData(String nodeName, List list) {
        ObjectNode res = OBJECT_MAPPER.createObjectNode();
        ObjectNode embedded = OBJECT_MAPPER.createObjectNode();
        res.putPOJO("_embedded", embedded);
        embedded.putPOJO(nodeName, list);
        return res;
    }

}
