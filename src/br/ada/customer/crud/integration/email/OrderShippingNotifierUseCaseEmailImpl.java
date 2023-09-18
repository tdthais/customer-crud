package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.IOrderShippingNotifierUseCase;

public class OrderShippingNotifierUseCaseEmailImpl implements IOrderShippingNotifierUseCase {

    private SendEmail sendEmail;

    public OrderShippingNotifierUseCaseEmailImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), "Sua compra foi concluída" +
                " com sucesso e seu pedido saiu para entrega! Muito obrigada pela confiança, volte sempre!");
    }
}
