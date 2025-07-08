package src.services;

public class ShippingService implements src.interfaces.ShippingService {
    @Override
    public void send(String address) {
        System.out.println("Shipping to address: " + address);
    }
    
}
