package com.example.usersregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditUser extends AppCompatActivity {

    private UserAccess  access;
    private User userName = null;
    private EditText nome;
    private EditText username;
    private EditText password;
    private EditText adress;
    private EditText email;
    private EditText bday;
    private EditText gender;
    private EditText personType;
    private EditText idNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        getSupportActionBar().setTitle("Editar Usuário");
        nome = findViewById(R.id.atualNome);
        username = findViewById(R.id.atualUser);
        password = findViewById(R.id.atualPass);
        adress = findViewById(R.id.atualEnd);
        email = findViewById(R.id.atualEmail);
        bday = findViewById(R.id.atualDate);
        gender = findViewById(R.id.atualSex);
        personType = findViewById(R.id.atualCpf);
        idNumber = findViewById(R.id.atualCpfNumber);
        access = new UserAccess(this);

        Intent i = getIntent();

        if(i.hasExtra("user")){
            userName = (User) i.getSerializableExtra("user");
            nome.setText(userName.getNome());
            username.setText(userName.getUserName());
            password.setText(userName.getPassWord());
            adress.setText(userName.getAddress());
            email.setText(userName.getEmail());
            bday.setText(userName.getDate());
            gender.setText(userName.getSex());
            personType.setText(userName.getIdType());
            idNumber.setText(userName.getIdNumb());
        }

    }

    public void editUser( View view){
        User u = new User();
        u.setNome(nome.getText().toString());
        u.setUserName(username.getText().toString());
        u.setNome(nome.getText().toString());
        u.setUserName(username.getText().toString());
        u.setPassWord(password.getText().toString());
        u.setAddress(adress.getText().toString());
        u.setEmail(email.getText().toString());
        u.setDate(bday.getText().toString());
        u.setSex(gender.getText().toString());
        u.setIdType(personType.getText().toString());
        u.setIdNumb(idNumber.getText().toString());
        access.editUser(u);
        Toast.makeText(this, "Usuário atualizado com sucesso!" , Toast.LENGTH_SHORT).show();

    }
}