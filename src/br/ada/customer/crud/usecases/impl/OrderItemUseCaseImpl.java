package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.*;
import br.ada.customer.crud.usecases.IOrderItemUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;


import java.math.BigDecimal;
import java.util.*;

public class OrderItemUseCaseImpl implements IOrderItemUseCase {

//    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    /*
     * 1 - Pedido precisa estar com status == OrderStatus.OPEN
     * 2 - Lembrar de atualizar o banco através do repository
     */

    public OrderItemUseCaseImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount) {
        if (order.getStatus() != OrderStatus.OPEN){
            throw new RuntimeException("Pedido ainda não está aberto.");
        }
        OrderItem orderItem = new OrderItem();
        List<OrderItem> items = order.getItems();
        //        orderItem.setId(order.getId());
//        public void save(Order order) throws RepositoryException {
//            order.setId(database.nextId());   => database: private MemoryDatabase database
        orderItem.setProduct(product);
        orderItem.setSaleValue(price);
        orderItem.setAmount(amount);
        items.add(orderItem);
        orderRepository.update(order);
        return orderItem;
    }


    /*
     * 1 - Pedido precisa estar com status == OrderStatus.OPEN
     * 2 - Trocar a quantidade que foi vendida desse produto
     * 3 - Lembrar de atualizar o banco através do repository
     */
    @Override
    public OrderItem changeAmount(Order order, Product product, Integer amount) {
        if (order.getStatus() != OrderStatus.OPEN){
            throw new RuntimeException("Pedido ainda não está aberto.");
        }
        OrderItem orderItem = new OrderItem();
        for (OrderItem item : order.getItems()){
            if (item.getProduct() == product){
                orderItem.setAmount(amount);
            }
        }
        orderRepository.update(order);
        return orderItem;
    }


    /*
     * 1 - Pedido precisa estar com status == OrderStatus.OPEN
     * 2 - Lembrar de atualizar o banco através do repository
     */
    @Override
    public void removeItem(Order order, Product product) {
        if (order.getStatus() != OrderStatus.OPEN){
            throw new RuntimeException("Pedido ainda não está aberto.");
        }
//        OrderItem orderItem = new OrderItem();
        List<OrderItem> items = order.getItems();
        for (OrderItem item : order.getItems()){
            if (item.getProduct() == product){
                items.remove(item);
            }
        }
        orderRepository.update(order);
    }
}
