package com.rustem.simplecrudapplication.service;

import com.rustem.simplecrudapplication.model.Customer;
import com.rustem.simplecrudapplication.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getById(Long id) {
        log.info("In CustomerServiceImpl getById {}", id);
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public void save(Customer customer) {
        log.info("In CustomerServiceImpl save {}", customer);
        customerRepository.save(customer);
    }

    @Override
    public void update(Customer c) {

        Optional<Customer> customer = customerRepository.findById(c.getId());

        Integer update = null;
        if (customer.isPresent()) {
            update = customerRepository.update(c.getId(), c.getFirstName(), c.getLastName(), c.getAddress(), c.getBudget());
            log.info("Updated: {}", update);
        }
    }

    @Override
    public void delete(Long id) {
        log.info("In CustomerServiceImpl delete {}", id);
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() {
        log.info("In CustomerServiceImpl getAll");
        return customerRepository.findAll();
    }
}
