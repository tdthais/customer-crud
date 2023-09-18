package br.ada.customer.crud.factory;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.integration.email.*;
import br.ada.customer.crud.integration.memoryrepository.OrderEntityMerge;
import br.ada.customer.crud.integration.memoryrepository.OrderMemoryRepositoryImpl;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.usecases.*;
import br.ada.customer.crud.usecases.impl.*;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderFactory {

    public static IOrderCreateUseCase createUseCase() {
        return new OrderCreateUseCaseImpl(
                createRepository(),
                CustomerFactory.createRepository()
        );
    }

    public static IOrderItemUseCase orderItemUseCase() {
        return new OrderItemUseCaseImpl(createRepository());
    }

    public static IOrderPlaceUseCase placeOrderUseCase() {
        return new OrderPlaceUseCaseImpl(createRepository(), createOrderPlaceNotifier());
    }

    public static IOrderPayUseCase payOrderUseCase() {
        return new OrderPayUseCaseImpl(createRepository(),
                createOrderPayNotifier());
    }

    public static IOrderShippingUseCase shippingUseCase() {
        return new OrderShippingUseCaseImpl(createRepository(), createOrderShippingNotifier());
    }

    public static OrderRepository createRepository() {
        return new OrderMemoryRepositoryImpl(
                MemoryDatabase.getInstance(),
                new OrderEntityMerge(MemoryDatabase.getInstance()));
    }

    public static IOrderPlaceNotifierUseCase createOrderPlaceNotifier() {
        return new OrderPlaceNotifierUseCaseEmailImpl(new SendEmail());
    }

    public static IOrderPayNotifierUseCase createOrderPayNotifier() {
        return new OrderPayNotifierUseCaseEmailImpl(new SendEmail());
    }

    public static IOrderShippingNotifierUseCase createOrderShippingNotifier() {
        return new OrderShippingNotifierUseCaseEmailImpl(new SendEmail());
    }
}
