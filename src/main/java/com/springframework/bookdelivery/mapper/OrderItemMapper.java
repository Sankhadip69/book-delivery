package com.springframework.bookdelivery.mapper;

import com.springframework.bookdelivery.dto.OrderItemDTO;
import com.springframework.bookdelivery.entity.Book;
import com.springframework.bookdelivery.entity.OrderItem;
import lombok.experimental.UtilityClass;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class OrderItemMapper {

    /**
     * Converts an {@link OrderItem} object to an {@link OrderItemDTO}.
     *
     * @param orderItem The {@link OrderItem} object to be converted.
     * @return An {@link OrderItemDTO} containing data from the source object.
     */
    public OrderItemDTO toDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .book(toBook(orderItem.getBook()))
                .build();
    }

    /**
     * Converts a list of {@link OrderItem} objects to a list of {@link OrderItemDTO}.
     *
     * @param orderItems The list of {@link OrderItem} objects to be converted.
     * @return A list of {@link OrderItemDTO} objects containing data from the source objects.
     */
    public List<OrderItemDTO> toDTO(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItemMapper::toDTO)
                .toList();

    }

    private OrderItemDTO.OrderItemBook toBook(Book source) {
        return OrderItemDTO.OrderItemBook.builder()
                .id(source.getId())
                .name(source.getName())
                .authorFullName(source.getAuthorFullName())
                .isbn(source.getIsbn())
                .price(source.getPrice())
                .build();
    }

    /**
     * Converts an {@link OrderItemDTO} object to an {@link OrderItem}.
     *
     * @param orderItemDTO The {@link OrderItemDTO} object to be converted.
     * @return An {@link OrderItem} object containing data from the source object.
     */
    public OrderItem toOrderItem(OrderItemDTO orderItemDTO) {

        return OrderItem.builder()
                .id(orderItemDTO.getId())
                .book(toBook(orderItemDTO.getBook()))
                .build();
    }

    /**
     * Converts a collection of {@link OrderItemDTO} objects to a list of {@link OrderItem}.
     *
     * @param orderItemDTOs The collection of {@link OrderItemDTO} objects to be converted.
     * @return A list of {@link OrderItem} objects containing data from the source objects.
     */
    public List<OrderItem> toOrderItem(Collection<OrderItemDTO> orderItemDTOs) {

        return orderItemDTOs.stream().map(OrderItemMapper::toOrderItem).toList();
    }

    private static Book toBook(OrderItemDTO.OrderItemBook orderItemBook) {

        return Book.builder()
                .id(orderItemBook.getId())
                .isbn(orderItemBook.getIsbn())
                .name(orderItemBook.getName())
                .authorFullName(orderItemBook.getAuthorFullName())
                .price(orderItemBook.getPrice())
                .build();

    }

    /**
     * Converts a list of {@link OrderItemDTO} objects to a list of {@link OrderItem}.
     *
     * @param orderItemDTOS The list of {@link OrderItemDTO} objects to be converted.
     * @return A list of {@link OrderItem} objects containing data from the source objects.
     */
    public List<OrderItem> toOrderItemSetList(List<OrderItemDTO> orderItemDTOS) {

        return orderItemDTOS.stream()
                .map(OrderItemMapper::toOrderItem)
                .toList();
    }

}
