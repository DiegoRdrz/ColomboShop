package com.BackEnd.Colomboshop.Service;

import com.BackEnd.Colomboshop.Model.Transaction;
import com.BackEnd.Colomboshop.Model.User;
import com.BackEnd.Colomboshop.Repository.TransactionRepository;
import com.BackEnd.Colomboshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            transaction.setUserID(user.getId());
            User seller = userRepository.findById(transaction.getSellerID())
                    .orElseThrow(() -> new Exception("El vendedor especificado no existe"));
            transaction.setSellerID(seller.getId());
            transaction.setSellerPaymentReceiptMethod(seller.getPaymentReceiptMethod());
            return transactionRepository.save(transaction);

        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Transaction updateTransaction(String id, Transaction transaction) {
        if (transactionRepository.existsById(id)) {
            transaction.setTransactionID(id);
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    public void deleteTransaction(String id) {
        transactionRepository.deleteById(id);
    }
}
