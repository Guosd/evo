package com.github.framework.evo.base;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class Order implements Serializable {
    private static final long serialVersionUID = 8138022018100161833L;
    private Order.Direction direction;
    private String property;
    private String orderExpr;
    private static final String INJECTION_REGEX = "[A-Za-z0-9\\_\\-\\+\\.]+";

    public Order(String property, Order.Direction direction, String orderExpr) {
        this.direction = direction;
        this.property = property;
        this.orderExpr = orderExpr;
    }

    public Order.Direction getDirection() {
        return this.direction;
    }

    public String getProperty() {
        return this.property;
    }

    public String getOrderExpr() {
        return this.orderExpr;
    }

    public void setDirection(Order.Direction direction) {
        this.direction = direction;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setOrderExpr(String orderExpr) {
        this.orderExpr = orderExpr;
    }

    public static boolean isSQLInjection(String str) {
        return !Pattern.matches("[A-Za-z0-9\\_\\-\\+\\.]+", str);
    }

    public String toString() {
        if (isSQLInjection(this.property)) {
            throw new IllegalArgumentException("SQLInjection property: " + this.property);
        } else if (this.orderExpr != null && this.orderExpr.indexOf(63) != -1) {
            String[] exprs = this.orderExpr.split("\\?");
            return exprs.length == 2 ? String.format(this.orderExpr.replaceAll("\\?", "%s"), this.property) + (this.direction == null ? "" : " " + this.direction.name()) : String.format(this.orderExpr.replaceAll("\\?", "%s"), this.property, this.direction == null ? "" : " " + this.direction.name());
        } else {
            return this.property + (this.direction == null ? "" : " " + this.direction.name());
        }
    }

    public static List<Order> formString(String orderSegment) {
        return formString(orderSegment, (String)null);
    }

    public static List<Order> formString(String orderSegment, String orderExpr) {
        if (orderSegment != null && !"".equals(orderSegment.trim())) {
            List<Order> results = new ArrayList();
            String[] orderSegments = orderSegment.trim().split(",");

            for(int i = 0; i < orderSegments.length; ++i) {
                String sortSegment = orderSegments[i];
                Order order = innerFormString(sortSegment, orderExpr);
                if (order != null) {
                    results.add(order);
                }
            }

            return results;
        } else {
            return new ArrayList(0);
        }
    }

    private static Order innerFormString(String orderSegment, String orderExpr) {
        if (orderSegment != null && !"".equals(orderSegment.trim()) && !orderSegment.startsWith("null.") && !orderSegment.startsWith(".")) {
            String[] array = orderSegment.trim().split("\\.");
            if (array.length != 1 && array.length != 2) {
                throw new IllegalArgumentException("orderSegment pattern must be {property}.{direction}, input is: " + orderSegment);
            } else {
                return create(array[0], array.length == 2 ? array[1] : "asc", orderExpr);
            }
        } else {
            return null;
        }
    }

    public static Order create(String property, String direction) {
        return create(property, direction, (String)null);
    }

    public static Order create(String property, String direction, String orderExpr) {
        return new Order(property, Order.Direction.fromString(direction), orderExpr);
    }

    public static enum Direction {
        ASC,
        DESC;

        private Direction() {
        }

        public static Order.Direction fromString(String value) {
            try {
                return valueOf(value.toUpperCase(Locale.US));
            } catch (Exception var2) {
                return ASC;
            }
        }
    }
}
