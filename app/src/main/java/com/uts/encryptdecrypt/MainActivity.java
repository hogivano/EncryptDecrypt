package com.uts.encryptdecrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kel5.encryptdecrypt.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView hasil, textHasil;
    Button encrypt, decrypt, copy;
    EditText input, key, key2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textHasil = (TextView) findViewById(R.id.textHasil);
        hasil = (TextView) findViewById(R.id.hasil);
        input = (EditText) findViewById(R.id.input);
        key = (EditText) findViewById(R.id.key);
        key2 = (EditText) findViewById(R.id.key2);

        encrypt = (Button) findViewById(R.id.encrypt);
        decrypt = (Button) findViewById(R.id.decrypt);
        copy = (Button) findViewById(R.id.copy);

        encrypt.setOnClickListener(this);
        decrypt.setOnClickListener(this);
        copy.setOnClickListener(this);
    }

    public boolean checkInput(){
        if (input.getText().toString().equals("") || key.getText().toString().equals("") || key2.getText().toString().equals("")){
            Toast.makeText(this, "input/key tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Integer.valueOf(key2.getText().toString()) < 2){
            Toast.makeText(this, "Key2 minimal 2", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (copy.getVisibility() == View.GONE){
            copy.setVisibility(View.VISIBLE);
        }
        return true;
    }

    public void clipboard(String text){
        ClipboardManager clipboardManager = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(this, "copied", Toast.LENGTH_SHORT).show();
    }

    public void encryption(boolean b){
        if (b){
            Kriptografi kript = new Kriptografi();
            kript.checkKarakter(kript.deleteKarakter(key.getText().toString()).toString());
            String str = kript.encrypt(key.getText().toString(), key2.getText().toString(), input.getText().toString());
            hasil.setText(str);
            textHasil.setText("Enkripsi :");
        }
    }

    public void decription(boolean b){
        if (b){
            Kriptografi kript = new Kriptografi();
            kript.checkKarakter(kript.deleteKarakter(key.getText().toString()).toString());
            String str = kript.decrypt(key.getText().toString(), key2.getText().toString(), input.getText().toString());
            hasil.setText(str);
            textHasil.setText("Deskripsi :");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.encrypt:
                encryption(checkInput());
                break;
            case R.id.decrypt:
                decription(checkInput());
                break;
            case R.id.copy:
                clipboard(hasil.getText().toString());
                break;
        }
    }
}
