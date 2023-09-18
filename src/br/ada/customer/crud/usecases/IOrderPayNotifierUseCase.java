package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;


public interface IOrderPayNotifierUseCase {

    void notify(Order order);
}
