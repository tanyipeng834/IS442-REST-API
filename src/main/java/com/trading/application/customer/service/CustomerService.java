package com.trading.application.customer.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.trading.application.customer.entity.Customer;
import com.trading.application.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class CustomerService {

    Firestore fireStore = FirestoreClient.getFirestore();
    CustomerRepository customerRepo = new CustomerRepository();
    ApiFuture<WriteResult> apiFuture;

    // create new customer
    public String createCustomer(Customer customer) throws ExecutionException, InterruptedException {

        DocumentReference docReference = fireStore.collection("customer").document();
        customer.setId(docReference.getId());
        apiFuture = docReference.set(customer);
        return apiFuture.get().getUpdateTime().toDate().toString();
    }

    // get customer by id
    public Customer getCustomer(String id) throws  ExecutionException, InterruptedException {

        return customerRepo.getById(id, "customer");

    }

    // update customer name
    public String customerUpdateName(String id, String name) throws ExecutionException, InterruptedException {

        DocumentReference docReference = customerRepo.getReferenceById(id);
        apiFuture = docReference.update("name", name);
        return "Name updated: " + apiFuture.get();
    }

    // customer update email
    public String customerUpdateEmail(String id, String email) throws ExecutionException, InterruptedException {

        DocumentReference docReference = customerRepo.getReferenceById(id);
        apiFuture = docReference.update("email", email);
        return "Email Updated: " + apiFuture.get();
    }

}
