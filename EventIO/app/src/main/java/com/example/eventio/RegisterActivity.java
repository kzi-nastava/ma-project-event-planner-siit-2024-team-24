package com.example.eventio;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    private static final int IMAGE_PICK_CODE = 1000; // Kôd za aktivnost odabira slike
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        ImageView profileImageView = findViewById(R.id.profileImageView);
        // Postavljanje onClickListener na sliku
        profileImageView.setOnClickListener(v -> {
            // Proveravamo da li imamo dozvolu
            if (ContextCompat.checkSelfPermission(RegisterActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Ako nemamo dozvolu, prikazujemo custom dijalog
                showPermissionDialog();
            } else {
                // Ako imamo dozvolu, otvaramo galeriju
                openGallery();
            }
        });


        TextView loginText = findViewById(R.id.register_login_text);
        // Postavi onClickListener na Login tekst
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });


        // Find the "Next" button and set an OnClickListener
        Button nextButton = findViewById(R.id.register_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to go to ActivityRole
                Intent intent = new Intent(RegisterActivity.this, RoleActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showPermissionDialog() {
        // Kreiraj custom dijalog
        new AlertDialog.Builder(this)
                .setTitle("Dozvola za galeriju")
                .setMessage("Da li želite da dozvolite aplikaciji pristup galeriji?")
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kada korisnik pritisne "Da", zatraži dozvolu
                        openGallery();
                    }
                })
                .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kada korisnik pritisne "Ne", samo zatvori dijalog
                        dialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Pristup galeriji je odbijen", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    private void openGallery() {
        // Otvaramo galeriju
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Ako je dozvola odobrena, otvori galeriju
                openGallery();
            } else {
                // Ako je dozvola odbijena, obavesti korisnika
                Toast.makeText(this, "Dozvola odbijena", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            Uri imageUri = data.getData();
            ImageView profileImageView = findViewById(R.id.profileImageView);
            profileImageView.setImageURI(imageUri); // Postavi sliku u ImageView
        }
    }
}