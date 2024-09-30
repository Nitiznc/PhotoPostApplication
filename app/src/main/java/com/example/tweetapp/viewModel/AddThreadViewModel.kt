package com.example.tweetapp.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tweetapp.model.PostModel
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.storage
import java.util.UUID

class AddThreadViewModel: ViewModel() {

    private val db = FirebaseDatabase.getInstance()
    val userRef = db.getReference("posts")

    private  val storageRef = Firebase.storage.reference
    private val imageRef = storageRef.child("posts/${UUID.randomUUID()}.jpg")

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted: LiveData<Boolean> = _isPosted


    fun saveImage(
        post: String,
        userId: String,
        imageUri: Uri
    ) {
        val uploadTask = imageRef.putFile(imageUri)
        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                saveData(post, userId, it.toString())
            }
        }
    }

    fun saveData(
        post: String,
        userId: String,
        image: String
    ){

        val postData = PostModel(post, image , userId, System.currentTimeMillis().toString())

        userRef.child(userRef.push().key!!).setValue(postData)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener {
                _isPosted.postValue(false)
            }
    }

}