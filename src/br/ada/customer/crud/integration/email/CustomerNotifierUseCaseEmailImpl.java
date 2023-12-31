package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.usecases.ICustomerNotifierUseCase;

public class CustomerNotifierUseCaseEmailImpl implements ICustomerNotifierUseCase<Customer> {

    private SendEmail sendEmail;

    public CustomerNotifierUseCaseEmailImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void registered(Customer customer) {
        sendEmail.send("comunicado@ada.com.br", customer.getEmail(), "Bem vindo. Click no link abaixo para confirmar seu cadastro.");
    }

    @Override
    public void updated(Customer customer) {
        sendEmail.send("comunicado@ada.com.br", customer.getEmail(), "Suas informações foram atualizadas. Caso tenha sido você mesmo, pode ignorar esse email.");
    }

    @Override
    public void deleted(Customer customer) {
        sendEmail.send("comunicado@ada.com.br", customer.getEmail(), "Sentiremos sua falta e esperamos seu retorno logo.");
    }

}
