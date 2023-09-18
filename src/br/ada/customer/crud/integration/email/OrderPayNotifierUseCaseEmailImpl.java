package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.IOrderPayNotifierUseCase;

public class OrderPayNotifierUseCaseEmailImpl implements IOrderPayNotifierUseCase {

    private SendEmail sendEmail;

    public OrderPayNotifierUseCaseEmailImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), "Pagamento aprovado! Aguarde as próximas informações sobre o envio.");
    }
}
