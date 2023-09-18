package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderShippingUseCase;
import br.ada.customer.crud.usecases.IOrderShippingNotifierUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

/*
 * 1 - Entrega só pode acontecer depois do pedido ter sido pago (order.status == OrderStatus.PAID)
 * 2 - Pedido deve passar a ter o status igual a OrderStatus.FINISH
 * 3 - Notificar o cliente e agradecer pela compra
 */

public class OrderShippingUseCaseImpl implements IOrderShippingUseCase {

    private OrderRepository orderRepository;
    private IOrderShippingNotifierUseCase notifier;

    public OrderShippingUseCaseImpl(OrderRepository orderRepository, IOrderShippingNotifierUseCase notifier){
        this.orderRepository = orderRepository;
        this.notifier = notifier;
    }

    @Override
    public void shipping(Order order) {
        if (order.getStatus() != OrderStatus.PAID) {
            throw new RuntimeException("Pagamento ainda não foi efetuado, despacho não pode ser iniciado.");
        }
        order.setStatus(OrderStatus.FINISH);
        orderRepository.save(order);
        notifier.notify(order); // "Sua compra foi concluída com sucesso e seu pedido saiu para entrega! Muito obrigada pela confiança, volte sempre!"
    }

}
