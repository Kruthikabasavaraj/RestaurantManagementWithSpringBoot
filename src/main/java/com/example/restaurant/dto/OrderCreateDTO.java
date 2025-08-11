package com.example.restaurant.dto;
import lombok.Data;
import java.util.List;

@Data
public class OrderCreateDTO {
    private Integer bookingId;
    private String waiterName;
    private List<OrderItemDTO> items;
    @Data public static class OrderItemDTO {
        private Integer menuId;
        private Integer quantity;
    }
}
