package de.rauldev.masterspring.orderapi.services;

import de.rauldev.masterspring.orderapi.dtos.OrderDTO;
import de.rauldev.masterspring.orderapi.entities.OrderEntity;
import de.rauldev.masterspring.orderapi.entities.OrderLineEntity;
import de.rauldev.masterspring.orderapi.entities.ProductEntity;
import de.rauldev.masterspring.orderapi.exceptions.GeneralServiceException;
import de.rauldev.masterspring.orderapi.exceptions.NotDataFoundException;
import de.rauldev.masterspring.orderapi.exceptions.ValidateServiceException;
import de.rauldev.masterspring.orderapi.repository.IOrderLineRepository;
import de.rauldev.masterspring.orderapi.repository.IOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    private static final String NOT_ORDER_FOUND = "Not found Order";
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderLineRepository orderLineRepository;
    @Transactional(readOnly = true)
    public List<OrderEntity> findAll(Pageable pageable){
        try {
            log.debug("finAllOrders => ");
            return orderRepository.findAll(pageable).toList();
        } catch (ValidateServiceException | NotDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
    @Transactional(readOnly = true)
    public OrderEntity findOrderById(Long id){
        try {
            log.debug("findOrderById => " + id);
            return orderRepository.findById(id).orElseThrow(()->new NotDataFoundException(NOT_ORDER_FOUND));
        } catch (ValidateServiceException | NotDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    @Transactional
    public void deleteOrder(Long id){
        try {
            log.debug("deleteOrder => " + id);
            OrderEntity orderEntity = orderRepository.findById(id)
                                                     .orElseThrow(()->new NotDataFoundException(NOT_ORDER_FOUND));
           orderRepository.delete(orderEntity);
        } catch (ValidateServiceException | NotDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public OrderEntity save(OrderEntity order){
        try {
            order.getLines().forEach(l->l.setOrder(order));
            log.debug("saveOrder => " + order.toString());
            if(order.getId()==null){
                //Create new Order
                 order.setCreatedAt(LocalDateTime.now());
                return orderRepository.save(order);
            }
            //update order
            OrderEntity orderSaved = orderRepository.findById(order.getId())
                                                    .orElseThrow(()-> new NotDataFoundException(NOT_ORDER_FOUND));
            //remove lines
            List<OrderLineEntity> orderLineEntities = orderSaved.getLines();
            orderLineEntities.removeAll(order.getLines());
            order.setCreatedAt(orderSaved.getCreatedAt());
            orderLineRepository.deleteAll(orderLineEntities);
            return orderRepository.save(order);
        } catch (ValidateServiceException | NotDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

}
