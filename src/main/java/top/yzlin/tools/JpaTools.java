package top.yzlin.tools;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public final class JpaTools {

    public static Sort createSort(String sort) {
        if ("".equals(Optional.ofNullable(sort).orElse(""))) {
            return Sort.unsorted();
        } else {
            Sort orders;
            String[] split = sort.split(",");
            String sortBy = split[0];
            if (split.length >= 2 && "DESC".equals(split[1].toUpperCase())) {
                orders = Sort.by(Sort.Direction.DESC, sortBy);
            } else {
                orders = Sort.by(Sort.Direction.ASC, sortBy);
            }
            return orders;
        }
    }

    public static Pageable createPageable(int page, int size) {
        return PageRequest.of(page > 0 ? page - 1 : 0, size);
    }

    public static Pageable createPageable(int page, int size, String sort) {
        return PageRequest.of(page > 0 ? page - 1 : 0, size, JpaTools.createSort(sort));
    }

}
