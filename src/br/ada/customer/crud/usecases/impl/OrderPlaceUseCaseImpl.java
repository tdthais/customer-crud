package br.ada.customer.crud.usecases.impl;


/*
    * 1 - Pedido precisa estar com status == OrderStatus.OPEN
    * 2 - Pedido precisa ter no mínimo um item
    * 3 - Valor deve ser maior que zero
    * 4 - Notificar o cliente que esta aguardando o pagamento
    * 5 - Pedido deve passar a ter o status igual OrderStatus.PENDING_PAYMENT
 */


import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderPlaceNotifierUseCase;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;

public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {

    private OrderRepository orderRepository;
    private IOrderPlaceNotifierUseCase notifier;

    public OrderPlaceUseCaseImpl(OrderRepository orderRepository, IOrderPlaceNotifierUseCase notifier){
        this.orderRepository = orderRepository;
        this.notifier = notifier;
    }


    @Override
    public void placeOrder(Order order) {
        if (order.getStatus() != OrderStatus.OPEN){
            throw new RuntimeException("Pedido ainda não está aberto.");
        }

        if (order.getItems() == null || order.getItems().isEmpty()){
            throw new RuntimeException("Não há itens nesse pedido, ele não pode ser finalizado.");
        }

        BigDecimal OrderValue = BigDecimal.ZERO;
        for (OrderItem item : order.getItems()){
            OrderValue = OrderValue.add(item.getSaleValue().multiply(BigDecimal.valueOf(item.getAmount())));
        }
        if (OrderValue.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Erro: o valor total do carrinho é nulo. Entre em contato com o suporte.");
        }

        notifier.notify(order); // "Pedido realizado, aguardando pagamento."
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        orderRepository.update(order);
    }
}
