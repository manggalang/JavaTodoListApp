package org.galangcode.todolistapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.galangcode.todolistapp.helper.Helper;

import java.util.Objects;

public class EditorActivity extends AppCompatActivity {

    private EditText editTitle, editDescription;
    private Button btnSave;
    private Helper db = new Helper(this);
    private String id, title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_desc);
        btnSave = findViewById(R.id.btn_save);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");

        if (id == null || id.equals("")) {
            setTitle("Add Activity");
        } else {
            setTitle("Edit Activity");
            editTitle.setText(title);
            editDescription.setText(description);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(id == null || id.equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Saving", Objects.requireNonNull(e.getMessage()));
                }
            }
        });
    }

    private void save() {
        if (String.valueOf(editTitle.getText()).equals("") || String.valueOf(editDescription.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill the all data", Toast.LENGTH_SHORT).show();
        } else {
            db.addList(editTitle.getText().toString(), editDescription.getText().toString());
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(editTitle.getText()).equals("") || String.valueOf(editDescription.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill the all data", Toast.LENGTH_SHORT).show();
        } else {
            db.updateList(Integer.parseInt(id), editTitle.getText().toString(), editDescription.getText().toString());
            finish();
        }
    }
}