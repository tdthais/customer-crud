package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderPayNotifierUseCase;
import br.ada.customer.crud.usecases.IOrderPayUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

/*
    * 1 - Pedido precisa estar com status == OrderStatus.PENDING_PAYMENT
    * 2 - Pedido deve passar a ter o status igual a OrderStatus.PAID
    * 3 - Notificar o cliente sobre o pagamento com sucesso
 */

public class OrderPayUseCaseImpl implements IOrderPayUseCase {
    private OrderRepository orderRepository;

    private IOrderPayNotifierUseCase notifier;

    public OrderPayUseCaseImpl(OrderRepository orderRepository, IOrderPayNotifierUseCase notifier) {
        this.orderRepository = orderRepository;
        this.notifier = notifier;
    }

    @Override
    public void pay(Order order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT){
            throw new RuntimeException("O pedido ainda não foi finalizado. Conclua a compra para poder fazer o pagamento.");
        }
        order.setStatus(OrderStatus.PAID);
        orderRepository.update(order);
        notifier.notify(order); // "Pagamento aprovado! Aguarde as próximas informações sobre o envio."
    }
}
