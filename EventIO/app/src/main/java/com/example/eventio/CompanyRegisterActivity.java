package com.example.eventio;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class CompanyRegisterActivity extends AppCompatActivity {

    private static final int PICK_IMAGES_REQUEST = 1000;
    private static final int PERMISSION_CODE = 1001;

    private List<Uri> selectedImages = new ArrayList<>();
    private LinearLayout imageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);

        Button addImagesButton = findViewById(R.id.add_images_button);
        imageContainer = findViewById(R.id.image_container);

        // Postavi onClickListener za dugme za dodavanje slika
        addImagesButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Ako nemamo dozvolu, prikaži custom dijalog
                showPermissionDialog();
            } else {
                // Ako imamo dozvolu, otvori galeriju
                openGallery();
            }
        });
    }

    private void showPermissionDialog() {
        // Kreiraj custom dijalog za traženje dozvole
        new AlertDialog.Builder(this)
                .setTitle("Dozvola za galeriju")
                .setMessage("Da li želite da dozvolite aplikaciji pristup galeriji?")
                .setPositiveButton("Da", (dialog, which) -> {
                    // Zatraži dozvolu
                    openGallery();
                })
                .setNegativeButton("Ne", (dialog, which) -> {
                    // Ako korisnik odbije, zatvori dijalog
                    dialog.dismiss();
                    Toast.makeText(this, "Pristup galeriji je odbijen", Toast.LENGTH_SHORT).show();
                })
                .create()
                .show();
    }

    private void openGallery() {
        // Otvori galeriju sa mogućnošću odabira više slika
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Select Images"), PICK_IMAGES_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Ako je dozvola odobrena, otvori galeriju
                openGallery();
            } else {
                // Ako je dozvola odbijena, prikaži poruku
                Toast.makeText(this, "Dozvola odbijena", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGES_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.getClipData() != null) {
                    // Ako je više slika selektovano
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        selectedImages.add(imageUri);
                        addImageToContainer(imageUri);
                    }
                } else if (data.getData() != null) {
                    // Ako je jedna slika selektovana
                    Uri imageUri = data.getData();
                    selectedImages.add(imageUri);
                    addImageToContainer(imageUri);
                }
            }
        }
    }

    private void addImageToContainer(Uri imageUri) {
        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
        params.setMargins(0, 0, 16, 0);
        imageView.setLayoutParams(params);

        imageView.setImageURI(imageUri);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageContainer.addView(imageView);
    }
}