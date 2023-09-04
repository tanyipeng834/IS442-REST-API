package com.trading.application.customer.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.trading.application.customer.entity.Customer;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ExecutionException;

@Repository
public class CustomerRepository {

    Firestore firestore = FirestoreClient.getFirestore();
    ApiFuture<DocumentSnapshot> documentSnapshotApiFuture;
    ApiFuture<WriteResult> writeResultApiFuture;

    public DocumentReference getReferenceById(String documentId){

        return firestore.collection("customer").document(documentId);

    }

    // add new customer
    public String addCustomer(Customer customer) throws ExecutionException, InterruptedException {

        DocumentReference docReference = firestore.collection("customer").document();
        customer.setId(docReference.getId());
        writeResultApiFuture = docReference.set(customer);
        return writeResultApiFuture.get().getUpdateTime().toDate().toString();

    }

    // Get document by documentId
    public Customer getById(String documentId, String collection) throws ExecutionException, InterruptedException {

        DocumentReference docReference = getReferenceById(documentId);
        documentSnapshotApiFuture = docReference.get();
        DocumentSnapshot document = documentSnapshotApiFuture.get();

        Customer customer = null;

        if(document.exists()){
            customer = document.toObject(Customer.class);
            return customer;
        }
        else
        {
            return null;
        }

    }

    // Update a document's field
    public String updateDocumentField(String documentId, String field, String fieldValue) throws ExecutionException, InterruptedException {

        DocumentReference docReference = getReferenceById(documentId);
        writeResultApiFuture = docReference.update(field, fieldValue);
        return "Result: " + writeResultApiFuture.get();
    }

}