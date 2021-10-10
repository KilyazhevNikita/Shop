package com.example.shop.service.impl;

import com.example.shop.entity.Customer;
import com.example.shop.entity.Product;
import com.example.shop.entity.Purchase;
import com.example.shop.exception.CustomerNotFound;
import com.example.shop.exception.NotEnoughFunds;
import com.example.shop.exception.ProductNotFound;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.PurchaseRepository;
import com.example.shop.service.CustomerService;
import com.example.shop.web.dto.CustomerBuyProductDto;
import com.example.shop.web.dto.CustomerDto;
import com.example.shop.web.dto.CustomerMoneyDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final PurchaseRepository purchaseRepository;

    @Override
    public Product buyProduct(CustomerBuyProductDto customerBuyProductDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerBuyProductDto.getIdCustomer());
        if (customerOptional.isPresent()) {
            Optional<Product> productOptional = productRepository.findById(customerBuyProductDto.getIdProduct());
            if (productOptional.isPresent()) {
                int quantity = customerBuyProductDto.getQuantity();
                Customer customer = customerOptional.get();
                Product product = productOptional.get();
                double money = customer.getMoney();
                double price = product.getPrice();
                if (money > price * quantity) {
                    customer.setMoney(money - price * quantity);
                    Purchase purchase = new Purchase();
                    purchase.setCustomer(customer);
                    purchase.setProduct(product);
                    purchase.setQuantity(quantity);
                    purchaseRepository.save(purchase);
                    return product;
                } else throw new NotEnoughFunds("Insufficient funds");
            } else throw new ProductNotFound("Product not found in catalog");
        } else throw new CustomerNotFound("Customer not found by id");
    }

    @Override
    public Customer addCustomer(CustomerDto customerDto) {
        if (customerDto.getName() == null) {
            throw new CustomerNotFound("Customer name empty");
        }
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setMoney(customerDto.getMoney());
        return customerRepository.save(customer);
    }

    @Override
    public Customer addMoneyCustomer(CustomerMoneyDto customerMoneyDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerMoneyDto.getCustomerId());
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setMoney(customer.getMoney() + customerMoneyDto.getMoney());
            return customerRepository.save(customer);
        } else throw new CustomerNotFound("Customer not found by id");
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFound("Customer not found"));
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
