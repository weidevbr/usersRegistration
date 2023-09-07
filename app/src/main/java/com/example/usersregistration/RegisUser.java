package com.example.usersregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class RegisUser extends AppCompatActivity {
    private EditText nome;
    private EditText username;
    private EditText password;
    private EditText adress;
    private EditText email;
    private EditText bday;
    private EditText gender;
    private EditText personType;
    private EditText idNumber;
    private EditText idLabel;
    private UserAccess  access;
    //URL url = new URL("https://test.avaty.com.br/Desafio/rest/desafioRest");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis_user);

        getSupportActionBar().setTitle("Cadastrar Usuário");

        nome = findViewById(R.id.editNome);
        username = findViewById(R.id.editUser);
        password = findViewById(R.id.editPass);
        adress = findViewById(R.id.editEnd);
        email = findViewById(R.id.editEmail);
        bday = findViewById(R.id.editDate);
        gender = findViewById(R.id.editSex);
        personType = findViewById(R.id.editCpf);
        idNumber = findViewById(R.id.cpfNumber);
        idLabel =  findViewById(R.id.labelCp);

        idLabel.setText(" CPF/CNPJ:");

        access = new UserAccess(this);

        idNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String fullcpf = idNumber.getText().toString();

                switch (fullcpf.length()){
                    case 11:
                        idLabel.setText("CPF:");
                       // if (idLabel.getText().toString().equals("CPF:")){
                          //  String formatCpf = fullcpf.substring(0,3)+"."+fullcpf.substring(3,6)+
                          //          "."+fullcpf.substring(6,9)+"-"+fullcpf.substring(9,11);
                          //  idNumber.setText(formatCpf.toString());
                      //  }
                     //   idNumber.clearFocus();
                        break;
                    case 14:
                        idLabel.setText("CNPJ:");
                        //if (idLabel.getText().toString().equals("CNPJ:")){
                          //  String formatCnpj = fullcpf.substring(0,2)+"."+fullcpf.substring(2,5)+
                          //          "."+fullcpf.substring(5,8)+"/"+fullcpf.substring(8,12) + "-" + fullcpf.substring(12,14);
                          //  idNumber.setText(formatCnpj);
                      //  }

                        break;
                    default:
                        idLabel.setText("CPF/CNPJ:");
                        break;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }

    public void signIn( View view){

        String fullbday = bday.getText().toString();

        String byear = fullbday.substring(0, 4);

        String cYear = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());

        Integer bDateMonth = Integer.parseInt(byear + cYear);
        Integer bdayfulldate = Integer.parseInt(fullbday);
        Integer callAge = bDateMonth - bdayfulldate;

        if (nome.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o campo nome", Toast.LENGTH_SHORT).show();
        }
        if (nome.getText().length() <= 30) {
            Toast.makeText(this, "O nome deve possuir mais que 30 caracteres", Toast.LENGTH_SHORT).show();
        }
        if (username.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o usuário", Toast.LENGTH_SHORT).show();
        }
        if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha a senha", Toast.LENGTH_SHORT).show();
        }
        if (password.length() <= 8) {
            Toast.makeText(this, "Senha deve possuir 8 ou mais caracteres", Toast.LENGTH_SHORT).show();
        }
        if (!password.getText().toString().matches("^(?!(?:[A-Z]+|[0-9]+)$)[A-Z0-9]+$")) {
            Toast.makeText(this, "Senha deve possuir números e letras maiusculas", Toast.LENGTH_SHORT).show();
        }
        if (adress.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o endereço", Toast.LENGTH_SHORT).show();
        }
        if (email.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o email", Toast.LENGTH_SHORT).show();
        }
        if ( !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            Toast.makeText(this, "Por favor, formato do email incorreto", Toast.LENGTH_SHORT).show();
        }
        if (bday.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha a data de nascimento", Toast.LENGTH_SHORT).show();
        }
        if(callAge < 18 ){
            Toast.makeText(this, "Usuário deve ser maior de 18.", Toast.LENGTH_SHORT).show();
        }
        if (gender.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o sexo", Toast.LENGTH_SHORT).show();
        }
        if (personType.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o  tipo de pessoa", Toast.LENGTH_SHORT).show();
        }
        if (idNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o CPF ou CNPJ", Toast.LENGTH_SHORT).show();
        }
        else {
            User u = new User();
            u.setNome(nome.getText().toString());
            u.setUserName(username.getText().toString());
            u.setPassWord(password.getText().toString());
            u.setAddress(adress.getText().toString());
            u.setEmail(email.getText().toString());
            u.setDate(bday.getText().toString());
            u.setSex(gender.getText().toString());
            u.setIdType(personType.getText().toString());
            u.setIdNumb(idNumber.getText().toString());
            long id = access.input(u);
            Toast.makeText(this, "Usuário cadastrado com ID:" + id, Toast.LENGTH_SHORT).show();
        }
    }
}