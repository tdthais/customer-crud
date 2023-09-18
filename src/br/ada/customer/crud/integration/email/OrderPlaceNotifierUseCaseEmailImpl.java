package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.IOrderPlaceNotifierUseCase;

public class OrderPlaceNotifierUseCaseEmailImpl implements IOrderPlaceNotifierUseCase {

    private SendEmail sendEmail;

    public OrderPlaceNotifierUseCaseEmailImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }
    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), "Pedido realizado, aguardando pagamento.");
    }
}
