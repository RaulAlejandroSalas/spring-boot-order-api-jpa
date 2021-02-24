package de.rauldev.masterspring.orderapi.controller;

import de.rauldev.masterspring.orderapi.converters.OrderConverter;
import de.rauldev.masterspring.orderapi.dtos.OrderDTO;
import de.rauldev.masterspring.orderapi.dtos.ProductDTO;
import de.rauldev.masterspring.orderapi.entities.OrderEntity;
import de.rauldev.masterspring.orderapi.entities.ProductEntity;
import de.rauldev.masterspring.orderapi.services.OrderService;
import de.rauldev.masterspring.orderapi.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private static final String SUCCESS = "SUCCESS";
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderConverter converter;

    @GetMapping(value = "/orders")
    public ResponseEntity<WrapperResponse<List<OrderDTO>>> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                   @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<OrderEntity> orders = orderService.findAll(pageable);
        List<OrderDTO> orderDTOList = converter.fromEntity(orders);
        return new WrapperResponse<>(true, SUCCESS, orderDTOList)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<WrapperResponse<OrderDTO>> findOrderById(@PathVariable("id") Long id) {
        OrderEntity order = orderService.findOrderById(id);
        OrderDTO orderDTO = converter.fromEntity(order);
        return new WrapperResponse<>(true, SUCCESS, orderDTO)
                .createResponse(HttpStatus.CREATED);
    }


    @PostMapping(value = "/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> createOrder(@RequestBody OrderDTO order) {
        OrderEntity orderEntity = orderService.save(converter.fromDTO(order));
        OrderDTO orderDTO = converter.fromEntity(orderEntity);
        return new WrapperResponse<>(true, SUCCESS, orderDTO)
                .createResponse(HttpStatus.CREATED);
    }

    @PutMapping(value = "/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> updateOrder(@RequestBody OrderDTO order) {
        OrderEntity orderEntity = orderService.save(converter.fromDTO(order));
        OrderDTO orderDTO = converter.fromEntity(orderEntity);
        return new WrapperResponse<>(true, SUCCESS, orderDTO)
                .createResponse(HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return new WrapperResponse<ProductDTO>(true, SUCCESS, null)
                .createResponse(HttpStatus.OK);
    }

}
