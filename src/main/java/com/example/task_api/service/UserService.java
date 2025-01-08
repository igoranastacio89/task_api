package com.example.task_api.service;

import com.example.task_api.model.User;
import com.example.task_api.exception.UserNotFoundException; 
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    @Autowired
    private Firestore firestore;

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        WriteResult writeResult = firestore.collection("users").document(user.getId()).set(user).get();
        return writeResult.getUpdateTime().toString();
    }

    public User getUser(String id) throws ExecutionException, InterruptedException {
        return firestore.collection("users").document(id).get().get().toObject(User.class);
    }

    public String updateUser(String id, User updatedUser) throws ExecutionException, InterruptedException {
        if (firestore.collection("users").document(id).get().get().exists()) {
            WriteResult writeResult = firestore.collection("users").document(id).set(updatedUser).get();
            return "Usuário atualizado com sucesso em: " + writeResult.getUpdateTime().toString();
        } else {
            throw new UserNotFoundException("Usuário com ID " + id + " não encontrado.");
        }
    }

    public String deleteUser(String id) throws ExecutionException, InterruptedException {
        if (firestore.collection("users").document(id).get().get().exists()) {
            firestore.collection("users").document(id).delete().get();
            return "Usuario com ID " + id + " deletado com sucesso.";
        } else {
            throw new UserNotFoundException("Usuario com ID " + id + " nao encontrado.");
        }
    }
}

