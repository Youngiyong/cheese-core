package com.cheese.admin.helper;

import com.cheese.core.exception.CheeseCode;
import com.cheese.core.exception.CustomException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

public class PaginationHelper {

    public static Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    public static List<Order> orderByConvert(String[] sort){

        try {
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            return orders;
        } catch (Exception e) {
            throw new CustomException(CheeseCode.INTERNAL_ORDER_CONVERT_SERVER_ERROR);
        }

    }
}
