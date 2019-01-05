package com.example.marianna.vivinerasmus;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//

public class PostActivity extends AppCompatActivity {

    private final static String DB_USER = "Users";

    private ImageButton imageBtn;
    private static final int GALLERY_REQUEST_CODE = 2;
    private Uri uri = null;
    private EditText mTitolo;
    private EditText mDesc;
    private Button mPost;
    // private StorageReference storage;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        // initializing objects
        mPost = (Button) findViewById(R.id.btnPost);
        mDesc = (EditText) findViewById(R.id.textDesc);
        mTitolo = (EditText) findViewById(R.id.textTitolo);
        //storage = FirebaseStorage.getInstance().getReference();
        databaseRef = database.getInstance().getReference().child("VivInErasmus");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());
        // imageBtn = (ImageButton)findViewById(R.id.imageBtn);

        /*ottengo l'immagine dalla galleria
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image");
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
 }
        });*/
        // posting su Firebase
        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PostActivity.this, "POSTING…", Toast.LENGTH_LONG).show();
                final String TitoloPost = mTitolo.getText().toString().trim();
                final String DescPost = mDesc.getText().toString().trim();
                //check
                if (!TextUtils.isEmpty(DescPost) && !TextUtils.isEmpty(TitoloPost)) {
 /*StorageReference filepath = storage.child("post_images").child(uri.getLastPathSegment());
 filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
 @Override
 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
 @SuppressWarnings("VisibleForTest")
 //ottengo l'url dell'immagine
 final Uri downloadUrl = taskSnapshot.getDownloadUrl;
 Toast.makeText(getApplicationContext(), "Succesfully Uploaded", Toast.LENGTH_SHORT).show();*/
                    final DatabaseReference newPost = databaseRef.push();
                    //aggiungo contenuti del post con la reference del database
                    mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            newPost.child("titolo").setValue(TitoloPost);
                            newPost.child("desc").setValue(DescPost);
                            //newPost.child("imageUrl").setValue(downloadUrl.toString());
                            newPost.child("uid").setValue(mCurrentUser.getUid());
                            newPost.child("username").setValue(dataSnapshot.child("name").getValue());
 /*.addOnCompleteListener(new OnCompleteListener<Void>() {
 @Override
 //TODO:elimina commento dopo avere a ggiunto le altre activity
 public void onComplete(@NonNull Task<Void> task) {
 if (task.isSuccessful()){
 Intent intent = new Intent(PostActivity.this, MainActivity.class);
 startActivity(intent);
 }}});*/
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }});
        }
    }
/*});
        }
@Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 super.onActivityResult(requestCode, resultCode, data);
 //image from gallery result
 if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK){
 uri = data.getData();
 imageBtn.setImageURI(uri); }

    }
}*/
