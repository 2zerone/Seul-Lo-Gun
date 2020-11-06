package com.example.sns_project.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sns_project.ComentTradeinfo;
import com.example.sns_project.Comentinfo;
import com.example.sns_project.Postinfo;
import com.example.sns_project.R;
import com.example.sns_project.adapter.CommentAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Date;

public class CommentActivity extends BasicActivity {
    private static final String TAG = "CommentActivity";
    private Postinfo postinfo;
    private Comentinfo comentinfo;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private ArrayList<Comentinfo> commentList;

    private EditText addcomment;
    private TextView username;
    private ImageView image_profile;
    private ImageView post;
    private FirebaseUser user;
    private String profilePath;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_recycler);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("댓글");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());

        recyclerView = findViewById(R.id.comment_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(this, commentList);
        recyclerView.setAdapter(commentAdapter);
        postinfo = (Postinfo) getIntent().getSerializableExtra("postinfo");
        comentinfo = (Comentinfo) getIntent().getSerializableExtra("comentinfo");
        image_profile = findViewById(R.id.image_profile);
        addcomment = findViewById(R.id.add_comment);
        username = findViewById(R.id.username);
        post = findViewById(R.id.post);
        user = FirebaseAuth.getInstance().getCurrentUser();

        post.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
                final String comments = ((EditText) findViewById(R.id.add_comment)).getText().toString();
                final Date date = comentinfo == null ? new Date() : comentinfo.getCreatedAt();
                if ( addcomment.getText().toString().equals("")){
                    Toast.makeText(CommentActivity.this,"댓글을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    final DocumentReference docRef = firebaseFirestore.collection("users").document(user.getUid());
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                    if(document.getData().get("photoUrl")  == null){
                                        String name = document.getData().get("name").toString();
                                        Comentinfo comentinfo = new Comentinfo(name, comments,  user.getUid(),postinfo.getId(), null,date  );
                                        storeUpload(comentinfo);
                                        commentList.clear();
                                        readComments();
                                        addcomment.setText("");
                                    }
                                    if(document.getData().get("photoUrl") !=null){
                                        String name = document.getData().get("name").toString();
                                        Comentinfo comentinfo = new Comentinfo(name, comments,  user.getUid(),postinfo.getId(),document.getData().get("photoUrl").toString(),date  );
                                        storeUpload(comentinfo);
                                        commentList.clear();
                                        readComments();
                                        addcomment.setText("");
                                    }

                                    Toast.makeText(CommentActivity.this,"댓글이 등록되었습니다", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });

                }
            }
    });


        Log.d(TAG, "겟 이미지 들어간다 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        getImage();
        Log.d(TAG, "리드커맨드 들어간다 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        readComments();


}



    private void storeUpload(Comentinfo comentinfo) {
        firebaseFirestore.collection("comments")
                .add(comentinfo.getComentinfo())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        }






    private void getImage(){
        DocumentReference docRef = firebaseFirestore.collection("users").document(user.getUid());
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document != null) {
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        if(document.getData().get("photoUrl") != null){
                            Glide.with(this).load(document.getData().get("photoUrl")).centerCrop().override(500).into(image_profile);
                        }
                        if(document.getData().get("photoUrl")== null){
                            image_profile.setImageResource(R.drawable.ic_baseline_account_circle_24);
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
    }


    private void readComments() {
        CollectionReference commentRef = firebaseFirestore.collection("comments");
        commentRef.whereEqualTo("postid", postinfo.getId()).orderBy("createdAt", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (postinfo.getId() != null) {
                    if (task.isSuccessful()) {
                        commentList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (document.getData().get("photoUrl") != null) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                commentList.add(new Comentinfo(
                                        document.getData().get("name").toString(),
                                        document.getData().get("comment").toString(),
                                        document.getData().get("publisher").toString(),
                                        document.getData().get("postid").toString(),
                                        document.getData().get("photoUrl").toString(),
                                        new Date(document.getDate("createdAt").getTime()),
                                        document.getId()));
                                Log.d(TAG, "가져와쓰");
                            }
                            if (document.getData().get("photoUrl") == null) {
                                commentList.add(new Comentinfo(
                                        document.getData().get("name").toString(),
                                        document.getData().get("comment").toString(),
                                        document.getData().get("publisher").toString(),
                                        document.getData().get("postid").toString(),
                                        null,
                                        new Date(document.getDate("createdAt").getTime()),
                                        document.getId()));
                                Log.d(TAG, "Null가져와쓰");
                            }
                        }
                        commentAdapter.notifyDataSetChanged();
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                        Log.d(TAG, "못가져와쓰");
                    }
                }
                else{
                    Log.d(TAG, "postinfo.getId가 널이래 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ: ");

                }
            }
        });
    }



    private void myStartActivity(Class c, Comentinfo comentinfo) {
        Intent intent = new Intent(this, c);
        intent.putExtra("comentinfo", comentinfo);
        this.startActivity(intent);
    }
}

