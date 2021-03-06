package de.rauldev.masterspring.orderapi.converters;

import de.rauldev.masterspring.orderapi.dtos.OrderDTO;
import de.rauldev.masterspring.orderapi.dtos.OrderLineDTO;
import de.rauldev.masterspring.orderapi.entities.OrderEntity;
import de.rauldev.masterspring.orderapi.entities.OrderLineEntity;
import lombok.AllArgsConstructor;


import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderConverter implements IConverter<OrderEntity, OrderDTO>{

    private DateTimeFormatter dateTimeFormatter;
    private ProductConverter productConverter;
    private UserConverter userConverter;

    @Override
    public OrderDTO fromEntity(OrderEntity entity) {
        if(entity==null)return null;
        List<OrderLineDTO> lines = fromLineEntity(entity.getLines());

        return OrderDTO.builder()
                       .id(entity.getId())
                       .lines(lines)
                       .total(entity.getTotal())
                       .user(userConverter.fromEntity(entity.getUser()))
                       .createdAt(entity.getCreatedAt().format(dateTimeFormatter))
                       .build();

    }

    @Override
    public OrderEntity fromDTO(OrderDTO dto) {
        if (dto==null)return null;

        List<OrderLineEntity> lines = fromLineDTO(dto.getLines());
        return OrderEntity.builder()
                          .id(dto.getId())
                          .total(dto.getTotal())
                          .lines(lines)
                          .user(userConverter.fromDTO(dto.getUser()))
                          .build();
    }

    public List<OrderLineDTO> fromLineEntity(List<OrderLineEntity> lines){
        return lines.stream().map(l->OrderLineDTO.builder()
                                                .id(l.getId())
                                                .price(l.getPrice())
                                                .product(productConverter.fromEntity(l.getProduct()))
                                                .quantity(l.getQuantity())
                                                .total(l.getTotal())
                                                .build())
        .collect(Collectors.toList());
    }

    public List<OrderLineEntity> fromLineDTO(List<OrderLineDTO> lineDTOS){
        return lineDTOS.stream().map(l->OrderLineEntity.builder()
                                                       .id(l.getId())
                                                       .price(l.getPrice())
                                                       .product(productConverter.fromDTO(l.getProduct()))
                                                       .quantity(l.getQuantity())
                                                       .total(l.getTotal())
                                                       .build())
        .collect(Collectors.toList());
    }
}
