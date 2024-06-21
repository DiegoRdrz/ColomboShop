package com.BackEnd.Colomboshop.Controller;

import com.BackEnd.Colomboshop.DTO.TransactionDTO;
import com.BackEnd.Colomboshop.Model.Transaction;
import com.BackEnd.Colomboshop.Service.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colomboShop/user/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return transactions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable String id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = convertToEntity(transactionDTO);
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(createdTransaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable String id, @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = convertToEntity(transactionDTO);
        Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
        return ResponseEntity.ok(convertToDto(updatedTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    private TransactionDTO convertToDto(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        BeanUtils.copyProperties(transaction, transactionDTO);
        return transactionDTO;
    }

    private Transaction convertToEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDTO, transaction);
        return transaction;
    }
}

