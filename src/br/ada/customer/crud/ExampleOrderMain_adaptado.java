package br.ada.customer.crud;

import br.ada.customer.crud.factory.CustomerFactory;
import br.ada.customer.crud.factory.OrderFactory;
import br.ada.customer.crud.factory.ProductFactory;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.*;

import java.math.BigDecimal;

public class ExampleOrderMain_adaptado {

    public static void main(String[] args) {
        IProductUseCase productUseCase = ProductFactory.createUseCase();
        ICustomerUseCase customerUseCase = CustomerFactory.createUseCase();
        IOrderCreateUseCase orderUseCase = OrderFactory.createUseCase();
        IOrderItemUseCase orderItemUseCase = OrderFactory.orderItemUseCase();
        IOrderPlaceUseCase orderPlaceUseCase = OrderFactory.placeOrderUseCase();
        IOrderPayUseCase orderPayUseCase = OrderFactory.payOrderUseCase();
        IOrderShippingUseCase orderShipping = OrderFactory.shippingUseCase();

        Customer customer1 = new Customer();
        customer1.setName("William");
        customer1.setDocument("13246");
        customerUseCase.create(customer1);

//        Customer customer2 = new Customer();
//        customer2.setName("Marcos");
//        customer2.setDocument("14523");
//        customerUseCase.create(customer2);

        Product productOne = new Product();
        productOne.setDescription("023");
        productUseCase.create(productOne);

        Product productTwo = new Product();
        productTwo.setDescription("1546");
        productUseCase.create(productTwo);

        Product productThree = new Product();
        productThree.setDescription("516");
        productUseCase.create(productThree);

        Order order = orderUseCase.create(customer1);
        orderItemUseCase.addItem(order, productOne, BigDecimal.TEN, 1);
        orderItemUseCase.addItem(order, productTwo, BigDecimal.TEN, 2);
        orderItemUseCase.changeAmount(order, productTwo, 5);
        //orderItemUseCase.removeItem(order, productOne);
        orderPlaceUseCase.placeOrder(order);
        orderPayUseCase.pay(order);
        orderShipping.shipping(order);
    }

}
